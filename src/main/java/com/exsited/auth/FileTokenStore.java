package com.exsited.auth;

import com.exsited.errors.SdkTokenCacheError;
import com.exsited.errors.SdkTokenLockError;
import com.exsited.errors.SdkUsageError;
import com.exsited.json.Json;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * Writes the token to a file guarded by a lock, so concurrent processes share one cached token.
 *
 * <p>The lock covers the write, not the mint (see {@link TokenStore#withLock}). Across
 * processes it is {@link FileChannel#tryLock()} on a sidecar file, an OS advisory lock the
 * kernel releases when the holder exits or dies, so there is no leftover to recover. A file
 * lock is owned by the process, so within one JVM a {@link ReentrantLock} carries the other
 * half. Both halves are per cache file, not per namespace, because a write rewrites the whole
 * file. A lock that cannot be taken raises {@link SdkTokenLockError} rather than running the
 * work without exclusion.
 */
public final class FileTokenStore implements TokenStore {

    public static final String DEFAULT_TOKEN_CACHE_FILENAME = "exsited_java_token.json";

    /**
     * The most of a token cache file this SDK will read.
     *
     * <p>Any process running as this user can grow the file, and an ordinary entry is under a
     * kilobyte, so one MiB holds a thousand of them.
     */
    public static final int MAX_TOKEN_CACHE_BYTES = 1024 * 1024;

    private static final long LOCK_WAIT_MILLIS = 10_000L;
    private static final long LOCK_POLL_MILLIS = 25L;

    /**
     * The in-process half of the lock, one per cache path and shared by every store in this JVM
     * naming it.
     *
     * <p>Static because a file lock belongs to the process: two stores over one path, each with
     * its own monitor, would both reach {@code tryLock} and the second would be answered
     * {@code OverlappingFileLockException} rather than made to wait. It also keeps at most one
     * channel open on a sidecar, which matters on POSIX, where closing any descriptor to a file
     * drops every lock this process holds on it. It never shrinks: a process opens a handful of
     * cache paths at most.
     */
    private static final ConcurrentHashMap<Path, Gate> GATES = new ConcurrentHashMap<>();

    /** The in-process mutex over one cache path, and its announcement latch. */
    private static final class Gate {
        final ReentrantLock lock = new ReentrantLock();
        /**
         * Whether the current unbroken run of refusals has already been announced.
         * On the gate rather than on the store, so two stores over one path do not
         * announce the same unlockable file twice.
         */
        boolean announced;
    }

    /**
     * Absolute and normalised, resolved once, so a later change of working directory cannot
     * move the I/O away from the sidecar and the gate.
     */
    private final Path path;
    private final Path lockPath;
    private final Gate gate;

    /** Written under the gate, read from any thread; see {@link #crossProcessLockingDegraded()}. */
    private volatile String degradedReason;

    public FileTokenStore(String path) {
        this.path = resolveOnce(path);
        this.lockPath = this.path.resolveSibling(this.path.getFileName() + ".lock");
        this.gate = GATES.computeIfAbsent(this.path, key -> new Gate());
    }

    /**
     * The absolute, normalised file a cache path names.
     *
     * <p>Not {@code toRealPath}: the file usually does not exist yet, and a
     * resolution that throws for that would refuse a first run.
     */
    private static Path resolveOnce(String raw) {
        Path asWritten = Paths.get(raw);
        try {
            return asWritten.toAbsolutePath().normalize();
        } catch (RuntimeException error) {
            // A name the platform will not absolutise; the raw form is still resolved once.
            return asWritten;
        }
    }

    /** The platform's per-user cache path for the token file. */
    public static String defaultCachePath() {
        String os = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        Path directory;
        if (os.contains("win")) {
            String local = System.getenv("LOCALAPPDATA");
            directory = Paths.get(local == null ? System.getProperty("java.io.tmpdir") : local, "exsited");
        } else if (os.contains("mac")) {
            directory = Paths.get(System.getProperty("user.home"), "Library", "Caches", "exsited");
        } else {
            String xdg = System.getenv("XDG_CACHE_HOME");
            directory = xdg == null
                    ? Paths.get(System.getProperty("user.home"), ".cache", "exsited")
                    : Paths.get(xdg, "exsited");
        }
        return directory.resolve(DEFAULT_TOKEN_CACHE_FILENAME).toString();
    }

    /**
     * Fingerprint a set of credentials, for keying a shared token file.
     *
     * <p>Derived from every value that decides <i>which principal the server mints
     * for</i>, so identical credentials share one cached token and any difference
     * gets its own entry. {@code redirectUri} is sent by the token request but
     * selects no token, so it is not included.
     *
     * <p>Each value is fed in <b>with its length</b> rather than joined on a
     * separator, which would be forgeable.
     *
     * <p>Cached entries are keyed by this digest, so any change to it orphans every cached
     * token: treat this method as frozen.
     */
    public static String buildCredentialsNamespace(
            String baseUrl, String clientId, String clientSecret, String grantType) {
        List<String> values = List.of(
                // The fingerprint scheme's version, not the API version.
                "v4",
                baseUrl == null ? "" : baseUrl,
                clientId == null ? "" : clientId,
                clientSecret == null ? "" : clientSecret,
                "",
                grantType == null ? "" : grantType);
        StringBuilder material = new StringBuilder();
        for (String value : values) {
            material.append(value.length()).append(':').append(value);
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashed = digest.digest(material.toString().getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : hashed) {
                hex.append(String.format("%02x", b));
            }
            return hex.substring(0, 16);
        } catch (NoSuchAlgorithmException error) {
            throw new IllegalStateException("SHA-256 is required and was not available", error);
        }
    }

    /**
     * This namespace's record, or null when there is none.
     *
     * @throws SdkTokenCacheError the file is there and cannot be understood,
     *     or this namespace's entry is there and cannot be understood. Neither is
     *     read as "no token": see {@link SdkTokenCacheError}.
     */
    @Override
    public TokenRecord read(String namespace) {
        Object found = readAll().get(namespace);
        if (!(found instanceof Map<?, ?> entry)) {
            // Absent, or written under this name by something whose entries are not
            // records. Nothing to read, and nothing wrong with the file either.
            return null;
        }
        Object accessToken = entry.get("accessToken");
        Object expiresAt = entry.get("expiresAt");
        if (accessToken == null && expiresAt == null) {
            return null;
        }
        if (!(expiresAt instanceof Number number) || !isFinite(number)) {
            throw corruptEntry("expiresAt is not a finite number of milliseconds");
        }
        Object refreshToken = entry.get("refreshToken");
        try {
            // Through the grammars rather than through a cast, so a JSON number
            // cannot become a Bearer credential by way of its decimal rendering.
            String access = TokenCredentials.checkAccessCredential(accessToken);
            String refresh = refreshToken == null
                    ? null
                    : TokenCredentials.checkRefreshCredential(refreshToken);
            return new TokenRecord(access, refresh, number.longValue());
        } catch (SdkUsageError error) {
            // Re-reported rather than propagated: the caller made no mistake, the
            // file did. What they need to hear is which file to repair.
            throw corruptEntry(error.getMessage());
        }
    }

    /**
     * @throws SdkTokenCacheError the file could not be read, so the other
     *     namespaces in it cannot be written back
     * @throws SdkTokenLockError the cross-process lock could not be taken
     */
    @Override
    public void write(String namespace, TokenRecord record) {
        withLock(namespace, () -> {
            Map<String, Object> all = readAll();
            Map<String, Object> entry = new LinkedHashMap<>();
            entry.put("accessToken", record.accessToken());
            entry.put("refreshToken", record.refreshToken());
            entry.put("expiresAt", record.expiresAt());
            all.put(namespace, entry);
            writeAll(all);
            return null;
        });
    }

    /**
     * Drop this namespace's entry, leaving every other one alone.
     *
     * <p><b>The lock sidecar is never removed</b>, only the entry. Unlinking it
     * would end mutual exclusion outright: a waiter goes on holding its lock on an
     * unlinked inode while the next process creates a fresh sidecar and locks
     * that, and both are then inside the critical section at once. An empty
     * sidecar costs nothing, and outliving the data it protected is the point of
     * it.
     */
    @Override
    public void clear(String namespace) {
        withLock(namespace, () -> {
            Map<String, Object> all = readAll();
            if (all.remove(namespace) == null) {
                return null;
            }
            writeAll(all);
            return null;
        });
    }

    /**
     * {@inheritDoc}
     *
     * <p>{@code namespace} names the entry, not the lock: the lock is over the
     * whole file, because a write rewrites the whole file. See the class note.
     *
     * @throws SdkTokenLockError the lock could not be taken
     */
    @Override
    public <T> T withLock(String namespace, Supplier<T> work) {
        gate.lock.lock();
        try {
            if (gate.lock.getHoldCount() > 1) {
                // Nested on one thread. The outer frame holds both halves already,
                // and asking the OS for a lock this JVM holds throws rather than
                // waits, so re-entering must not reach the channel at all.
                return work.get();
            }
            FileChannel channel = openSidecar();
            FileLock taken = null;
            try {
                taken = waitForSidecar(channel);
                // A later success must not keep reporting a refusal that has passed.
                degradedReason = null;
                gate.announced = false;
                return work.get();
            } finally {
                if (taken != null) {
                    try {
                        taken.release();
                    } catch (IOException ignored) {
                        // The close below drops it regardless.
                    }
                }
                try {
                    channel.close();
                } catch (IOException ignored) {
                    // Nothing is left to do about a sidecar that will not close.
                }
            }
        } finally {
            gate.lock.unlock();
        }
    }

    /**
     * Why the last cross-process lock attempt was refused, or {@code null} while the lock is in
     * force, for a caller who reports the condition rather than letting the
     * {@link SdkTokenLockError} travel.
     */
    public String crossProcessLockingDegraded() {
        return degradedReason;
    }

    /**
     * Open the sidecar to be locked.
     *
     * <p>Never the token file itself: it is replaced by an atomic move, which would
     * leave the lock held on an inode nobody else can reach.
     *
     * @throws SdkTokenLockError it could not be opened, so there is no lock to
     *     take
     */
    private FileChannel openSidecar() {
        try {
            Path parent = lockPath.getParent();
            if (parent != null) {
                // Null for a bare relative filename.
                Files.createDirectories(parent);
            }
            if (Files.isDirectory(lockPath)) {
                // A stale lock from a directory-based lock scheme: no channel opens on it.
                Files.delete(lockPath);
            }
            return FileChannel.open(lockPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException | RuntimeException error) {
            // RuntimeException as well as IOException: a read-only mount, a denied
            // directory or an unparseable name all arrive here, and none of them is
            // a lock.
            throw refuseTheLock("the lock file " + lockPath + " could not be opened ("
                    + describe(error) + ")");
        }
    }

    /**
     * Poll for the OS lock until the deadline.
     *
     * <p>The deadline is monotonic: on a wall clock, a step backwards from an NTP
     * correction would stretch the wait while the gate is held.
     *
     * @throws SdkTokenLockError the lock was not taken before the deadline, or
     *     the filesystem cannot lock at all
     */
    private FileLock waitForSidecar(FileChannel channel) {
        long deadline = System.nanoTime() + LOCK_WAIT_MILLIS * 1_000_000L;
        while (true) {
            try {
                FileLock taken = channel.tryLock();
                if (taken != null) {
                    return taken;
                }
            } catch (IOException | RuntimeException error) {
                // A filesystem with no working lock support answers here rather than
                // with null, and it will answer the same way on every poll, so
                // waiting out the deadline would only add ten seconds to the verdict.
                throw refuseTheLock("the lock file " + lockPath + " could not be locked ("
                        + describe(error) + ")");
            }
            if (System.nanoTime() - deadline >= 0) {
                throw refuseTheLock("another process held the lock for longer than "
                        + LOCK_WAIT_MILLIS / 1000 + "s");
            }
            try {
                Thread.sleep(LOCK_POLL_MILLIS);
            } catch (InterruptedException error) {
                // Restored, and the wait abandoned: with the flag set, every later sleep
                // would throw at once and the loop would spin.
                Thread.currentThread().interrupt();
                throw refuseTheLock("the wait for the lock file " + lockPath
                        + " was interrupted");
            }
        }
    }

    /**
     * The refusal, announced once per unbroken run of refusals.
     *
     * <p>Printed on {@code System.err} as well as raised because {@code TokenProvider} absorbs
     * the raise on a write, and a condition that refuses every write must not stay invisible.
     * The next success re-arms it.
     */
    private SdkTokenLockError refuseTheLock(String reason) {
        degradedReason = reason;
        SdkTokenLockError refusal = new SdkTokenLockError(
                "the token cache could not be locked across processes, so the operation was "
                        + "refused rather than run without exclusion: " + reason + ". Two "
                        + "processes sharing this file would otherwise write it at the same "
                        + "time and overwrite each other's entry. Call inMemoryToken() to keep "
                        + "the token in memory, or point tokenCachePath at a filesystem where "
                        + "locking works. Path: " + path);
        if (!gate.announced) {
            gate.announced = true;
            // System.err, so a diagnostic cannot corrupt piped output. The path is
            // the caller's own and no token is in this message.
            System.err.println("exsited: " + refusal.getMessage());
        }
        return refusal;
    }

    /** Whether a decoded JSON number is a stamp at all. NaN and the infinities are not. */
    private static boolean isFinite(Number value) {
        if (value instanceof Double asDouble) {
            return !asDouble.isNaN() && !asDouble.isInfinite();
        }
        return true;
    }

    /** A failure named without {@code toString()}, which repeats the path on some filesystems. */
    private static String describe(Throwable error) {
        return error.getClass().getSimpleName()
                + (error.getMessage() == null ? "" : ": " + error.getMessage());
    }

    /**
     * Every namespace in the file.
     *
     * <p>Two states answer "empty", and only two: the file is not there, and the
     * file holds nothing but whitespace. Everything else is a refusal, because
     * this is the value a write rebuilds the whole file from.
     *
     * <p>Foreign entries are carried through as parsed and are written back
     * unexamined - nothing here validates, coerces or drops another namespace's
     * record, so an entry this SDK could not use survives a write by this SDK.
     */
    private Map<String, Object> readAll() {
        if (!Files.exists(path)) {
            return new LinkedHashMap<>();
        }
        byte[] raw;
        try {
            raw = readWithinTheCeiling();
        } catch (IOException error) {
            throw refuseTheCacheFile("could not be read", error);
        }
        String content;
        try {
            content = StandardCharsets.UTF_8.newDecoder()
                    .onMalformedInput(CodingErrorAction.REPORT)
                    .onUnmappableCharacter(CodingErrorAction.REPORT)
                    .decode(ByteBuffer.wrap(raw))
                    .toString();
        } catch (CharacterCodingException error) {
            // Not UTF-8 text: PowerShell's ">" writes UTF-16, for one.
            throw refuseTheCacheFile("is not UTF-8 text", error);
        }
        if (content.isBlank()) {
            return new LinkedHashMap<>();
        }
        Object parsed;
        try {
            parsed = Json.parse(content);
        } catch (Json.JsonException error) {
            throw refuseTheCacheFile("does not hold valid JSON", error);
        }
        if (!(parsed instanceof Map<?, ?> map)) {
            throw refuseTheCacheFile("holds a JSON "
                    + (parsed == null ? "null" : parsed.getClass().getSimpleName())
                    + " where an object of credential namespaces was expected", null);
        }
        Map<String, Object> out = new LinkedHashMap<>();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            out.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        return out;
    }

    /**
     * At most one byte past the ceiling, so the ceiling bounds the read itself.
     *
     * <p>A check that reads the whole file and then complains has already spent
     * the memory the ceiling exists to refuse.
     */
    private byte[] readWithinTheCeiling() throws IOException {
        try (InputStream stream = Files.newInputStream(path)) {
            byte[] raw = stream.readNBytes(MAX_TOKEN_CACHE_BYTES + 1);
            if (raw.length > MAX_TOKEN_CACHE_BYTES) {
                throw refuseTheCacheFile("is larger than the " + MAX_TOKEN_CACHE_BYTES
                        + "-byte ceiling this SDK reads it to, so it was not loaded", null);
            }
            return raw;
        }
    }

    private SdkTokenCacheError refuseTheCacheFile(String problem, Throwable error) {
        return new SdkTokenCacheError("the token cache " + problem
                + ". It is not read as empty: this file is keyed by credentials namespace "
                + "and is meant to be shared, so rewriting it from an empty snapshot would "
                + "discard every other namespace in it. Repair or move the file, or call "
                + "inMemoryToken() to keep the token in memory. Path: " + path
                + (error == null ? "" : " (" + describe(error) + ")"));
    }

    private SdkTokenCacheError corruptEntry(String problem) {
        return new SdkTokenCacheError("the cached token record is not usable: " + problem
                + ". It is not read as absent: an entry that exists and cannot be understood "
                + "is a corrupt cache, and reading it as empty would hide that behind an "
                + "authentication on every call. Clear the entry, or call inMemoryToken() to "
                + "keep the token in memory. Path: " + path);
    }

    private void writeAll(Map<String, Object> all) {
        try {
            Path parent = path.getParent();
            if (parent != null) {
                // Null for a bare relative filename.
                Files.createDirectories(parent);
            }
            // Written to a sibling and moved, so a reader never sees half a file.
            Path temporary = path.resolveSibling(path.getFileName() + "." + ProcessHandle.current().pid() + ".tmp");
            Files.writeString(temporary, Json.write(all), StandardCharsets.UTF_8);
            try {
                Files.move(temporary, path,
                        StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException error) {
                Files.move(temporary, path, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException error) {
            // The token still works for this process; only the sharing is lost.
            // Distinct from the refusals above, which happen before a byte is
            // written and exist to stop this SDK destroying what it could not read.
        }
    }
}
