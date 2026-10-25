package com.exsited.auth;

import java.util.function.Supplier;

/** Where the access and refresh tokens live between calls. */
public interface TokenStore {

    TokenRecord read(String namespace);

    void write(String namespace, TokenRecord record);

    void clear(String namespace);

    /**
     * Run {@code work} with exclusive access to {@code namespace}.
     *
     * <p>This serialises the store, not the mint: {@code TokenProvider} mints outside the lock
     * and holds it only for the {@link #write}, so two racing callers can still mint a token
     * each, but neither write loses the other.
     */
    <T> T withLock(String namespace, Supplier<T> work);
}
