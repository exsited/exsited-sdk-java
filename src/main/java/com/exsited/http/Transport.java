package com.exsited.http;

import com.exsited.Dto;
import com.exsited.Redaction;
import com.exsited.client.ApiResponse;
import com.exsited.client.ErrorType;
import com.exsited.client.RequestInfo;
import com.exsited.config.SdkConfig;
import com.exsited.errors.SdkError;
import com.exsited.errors.SdkUsageError;
import com.exsited.json.Json;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

/** HTTP execution: one request in, one decoded {@link ApiResponse} out, whatever the status. */
public final class Transport implements AutoCloseable {

    private static final String JSON_TYPE = "application/json; charset=utf-8";
    private static final String FORM_TYPE = "application/x-www-form-urlencoded";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    private final SdkConfig config;
    private final RetryPolicy retryPolicy;
    private final Consumer<String> onProgress;
    private final HttpClient client;
    private final boolean ownsClient;

    public Transport(SdkConfig config) {
        this(config, null, null, null);
    }

    /** {@code null} derives the policy from the config; progress lines arrive already redacted. */
    public Transport(SdkConfig config, RetryPolicy retryPolicy, Consumer<String> onProgress) {
        this(config, retryPolicy, onProgress, null);
    }

    /** The one substitution seam: a supplied {@code httpClient} replaces the configured one. */
    public Transport(SdkConfig config, RetryPolicy retryPolicy, Consumer<String> onProgress,
            HttpClient httpClient) {
        this.config = config;
        this.retryPolicy = retryPolicy != null ? retryPolicy : new RetryPolicy(
                config.getMaxRetries(),
                config.getRetryBackoffSeconds(),
                config.getRetryBackoffMaxSeconds(),
                config.getRetryableHttpCodes(),
                config.getUnsafeRetryableHttpCodes());
        this.onProgress = onProgress != null ? onProgress : message -> { };
        HttpClient supplied = httpClient != null ? httpClient : config.getHttpClient();
        this.ownsClient = supplied == null;
        this.client = supplied != null ? supplied : HttpClientFactory.create(config);
    }

    /** A 3xx is returned rather than followed; a 401 is returned for the client to replay. */
    public ApiResponse<Dto> sendHttpRequest(TransportRequest request) {
        String method = normaliseMethod(request.httpMethod());
        checkRequestHeaders(request.headers());
        // The captured maps, not the accessor copies: what is judged must be what is encoded.
        checkMultipartLabels(request.files, request.formData);
        URI url = buildUrl(request.url(), request.queryParams);
        // The first of two size verdicts, and the one that runs before the encoder copies.
        checkSourceBudget(method, url, request);
        return sendWithRetries(method, url, request);
    }

    private void checkSourceBudget(String method, URI url, TransportRequest request) {
        long sources = sourceBytesOf(request);
        if (sources > config.getMaxUploadBytes()) {
            throw new SdkUsageError(method + " " + endpoint(request, url)
                    + ": the request's sources add up to " + sources + " bytes, over the "
                    + config.getMaxUploadBytes() + "-byte maxUploadBytes ceiling, so they were "
                    + "not encoded - split the upload, or raise the ceiling with "
                    + "SdkConfig.builder(...).maxUploadBytes(bytes)");
        }
        if (sources > config.getMaxRequestBytes()) {
            throw new SdkUsageError(method + " " + endpoint(request, url)
                    + ": the request's sources add up to " + sources + " bytes, over the "
                    + config.getMaxRequestBytes() + "-byte maxRequestBytes ceiling before any "
                    + "framing is added, so they were not encoded - split the request, or "
                    + "raise the ceiling with SdkConfig.builder(...).maxRequestBytes(bytes)");
        }
    }

    /** Part content, form field names and values, and a JSON body already rendered to bytes. */
    private static long sourceBytesOf(TransportRequest request) {
        long total = partContentBytesOf(request);
        if (request.formData != null) {
            for (String name : request.formData.keySet()) {
                total += utf8Length(name);
            }
        }
        return total;
    }

    private static long partContentBytesOf(TransportRequest request) {
        long total = 0;
        if (request.jsonBody instanceof byte[] bytes) {
            total += bytes.length;
        }
        if (request.files != null) {
            for (Object value : request.files.values()) {
                total += partBytes(value).length;
            }
        }
        if (request.formData != null) {
            for (Object value : request.formData.values()) {
                total += utf8Length(String.valueOf(value));
            }
        }
        return total;
    }

    /** The exact bytes the connection would carry, framing included. */
    private void checkWireBudget(String method, URI url, TransportRequest request,
            EncodedBody body) {
        long wire = body == null ? 0 : body.bytes.length;
        if (wire > config.getMaxRequestBytes()) {
            throw new SdkUsageError(method + " " + endpoint(request, url)
                    + ": the encoded body is " + wire + " bytes, over the "
                    + config.getMaxRequestBytes() + "-byte maxRequestBytes ceiling, so it was "
                    + "not sent - split the request, or raise the ceiling with "
                    + "SdkConfig.builder(...).maxRequestBytes(bytes)");
        }
        if (body != null && body.multipart) {
            long framing = wire - partContentBytesOf(request);
            if (framing > config.getMaxMultipartOverheadBytes()) {
                throw new SdkUsageError(method + " " + endpoint(request, url)
                        + ": the multipart framing is " + framing + " bytes of boundaries and "
                        + "part headers, over the " + config.getMaxMultipartOverheadBytes()
                        + "-byte maxMultipartOverheadBytes ceiling, so the body was not sent - "
                        + "send fewer parts or shorter labels, or raise the ceiling with "
                        + "SdkConfig.builder(...).maxMultipartOverheadBytes(bytes)");
            }
        }
    }

    /** The clock starts here, so {@code elapsed} measures the exchange and nothing before. */
    private ApiResponse<Dto> sendWithRetries(String method, URI url, TransportRequest request) {
        long started = System.nanoTime();
        int attempt = 0;
        int requestsSent = 0;
        boolean ceilingReached = false;
        Exception lastException = null;
        // Encoded once for every attempt, since per-attempt encoding can differ; bounded by the
        // attempts' clock.
        long prepareBy = started + (long) (config.getTimeoutSeconds() * 1e9);
        EncodedBody body = encodeBody(method, request, prepareBy);
        refuseIfPreparationOverran(method, url, request, prepareBy, started);
        checkWireBudget(method, url, request, body);
        HttpRequest wire = buildRequest(method, url, request, body);

        while (attempt < retryPolicy.getMaxAttempts()) {
            // Cancellation is honoured between attempts and while waiting on one.
            if (Thread.currentThread().isInterrupted()) {
                throw interruptedSend(method, url, request, requestsSent, started, null);
            }
            attempt++;
            requestsSent++;
            // The endpoint, not the resolved URL, which would hand a customer id to this callback.
            onProgress.accept(method + " " + endpoint(request, url)
                    + " sending (attempt " + attempt + "/" + retryPolicy.getMaxAttempts() + ")");
            CompletableFuture<HttpResponse<byte[]>> call = client.sendAsync(wire, boundedBody());
            try {
                HttpResponse<byte[]> raw = awaitWithinTheCeiling(call);
                int status = raw.statusCode();
                byte[] bodyBytes = raw.body() == null ? new byte[0] : raw.body();
                String contentType = raw.headers().firstValue(CONTENT_TYPE_HEADER).orElse(null);

                if (attempt < retryPolicy.getMaxAttempts()
                        && retryPolicy.shouldRetryStatusCode(status, method)) {
                    if (!sleepSeconds(retryPolicy.computeBackoffSeconds(
                            attempt, raw.headers().firstValue("Retry-After").orElse(null)))) {
                        throw interruptedSend(method, url, request, requestsSent, started, null);
                    }
                    continue;
                }

                Object decoded = decodeResponseBody(status, bodyBytes, contentType);
                return buildResponse(raw, decoded, bodyBytes, contentType, method,
                        request, requestsSent, retryPolicy.getMaxAttempts(), started);
            } catch (InterruptedException stopped) {
                call.cancel(true);
                throw interruptedSend(method, url, request, requestsSent, started, stopped);
            } catch (TimeoutException ceiling) {
                // The whole-attempt ceiling: the JDK's own timeout stops at the response headers.
                call.cancel(true);
                ceilingReached = true;
                lastException = new HttpTimeoutException("the " + config.getTimeoutSeconds()
                        + "-second timeoutSeconds ceiling was reached");
                if (!retryAfterFailure(attempt, lastException, method, url, request,
                        requestsSent, started)) {
                    break;
                }
            } catch (IOException exception) {
                ResponseTooLarge oversize = oversizeIn(exception);
                if (oversize != null) {
                    // Refused for size is refused on every attempt, so it is never retried.
                    throw refuseOversizedBody(method, url, oversize, request, requestsSent,
                            retryPolicy.getMaxAttempts(), started);
                }
                lastException = exception;
                // Assigned, not accumulated, so an earlier ceiling cannot mislabel a later failure.
                ceilingReached = false;
                if (!retryAfterFailure(attempt, exception, method, url, request,
                        requestsSent, started)) {
                    break;
                }
            }
        }

        if (Thread.currentThread().isInterrupted()) {
            // The interrupt arrived somewhere this loop does not watch.
            throw interruptedSend(method, url, request, requestsSent, started, lastException);
        }
        boolean timedOut = ceilingReached || isTimeoutFailure(lastException);
        throw new SdkError(
                method + " " + endpoint(request, url)
                        + (timedOut ? " timed out" : " failed") + " after " + requestsSent
                        + " attempt(s)" + detailOf(lastException),
                null,
                timedOut ? ErrorType.TIMEOUT : ErrorType.NETWORK,
                null,
                null,
                requestInfo(method, request, requestsSent, retryPolicy.getMaxAttempts(), started),
                // Kept as the cause: only the chain tells a read timeout from a TLS or DNS failure.
                lastException);
    }

    /** Whether to go round again after a failed attempt, sleeping the backoff first. */
    private boolean retryAfterFailure(int attempt, Exception failure, String method, URI url,
            TransportRequest request, int requestsSent, long started) {
        if (attempt >= retryPolicy.getMaxAttempts()
                || !retryPolicy.shouldRetryAfterFailure(
                        RetryPolicy.classifyTransportFailure(failure), method)) {
            return false;
        }
        if (!sleepSeconds(retryPolicy.computeBackoffSeconds(attempt, null))) {
            // Interrupted mid-backoff.
            throw interruptedSend(method, url, request, requestsSent, started, failure);
        }
        return true;
    }

    /** One attempt, bounded end to end by {@code timeoutSeconds}. */
    private HttpResponse<byte[]> awaitWithinTheCeiling(CompletableFuture<HttpResponse<byte[]>> call)
            throws IOException, InterruptedException, TimeoutException {
        try {
            return call.get(HttpClientFactory.timeoutMillis(config.getTimeoutSeconds()),
                    TimeUnit.MILLISECONDS);
        } catch (ExecutionException failed) {
            Throwable cause = failed.getCause();
            if (cause instanceof IOException io) {
                throw io;
            }
            if (cause instanceof RuntimeException runtime) {
                throw runtime;
            }
            throw new IOException(cause == null ? failed : cause);
        }
    }

    /** Refuse a call whose preparation used the whole budget, before the first byte goes out. */
    private void refuseIfPreparationOverran(String method, URI url,
            TransportRequest request, long prepareBy, long started) {
        if (System.nanoTime() - prepareBy <= 0) {
            return;
        }
        throw new SdkError(
                method + " " + endpoint(request, url) + " was not sent: preparing the body "
                        + "took longer than the " + config.getTimeoutSeconds()
                        + "-second timeoutSeconds budget, which bounds the preparation and "
                        + "the first attempt together",
                null,
                ErrorType.TIMEOUT,
                null,
                null,
                requestInfo(method, request, 0, retryPolicy.getMaxAttempts(), started));
    }

    /** {@code UNKNOWN}, not {@code NETWORK}: a re-send would read the same oversized body. */
    private SdkError refuseOversizedBody(String method, URI url, ResponseTooLarge oversize,
            TransportRequest request, int requestsSent, int maxAttempts, long started) {
        return new SdkError(
                method + " " + endpoint(request, url)
                        + " answered with a body over the " + oversize.cap
                        + "-byte maxResponseBytes cap"
                        + (oversize.declared >= 0 ? " (Content-Length " + oversize.declared + ")" : "")
                        + ", so it was not buffered - raise the cap with "
                        + "SdkConfig.builder(...).maxResponseBytes(bytes) if this response is "
                        + "meant to be that large",
                null,
                ErrorType.UNKNOWN,
                null,
                null,
                requestInfo(method, request, requestsSent, maxAttempts, started));
    }

    /** Close the JDK client, but only one this transport built: a supplied one is the caller's. */
    public void closeTransport() {
        if (ownsClient) {
            client.close();
        }
    }

    @Override
    public void close() {
        closeTransport();
    }

    private static String normaliseMethod(String httpMethod) {
        if (httpMethod == null || httpMethod.strip().isEmpty()) {
            throw new SdkUsageError("httpMethod: expected a non-blank string like 'GET', "
                    + "got '" + httpMethod + "'");
        }
        String method = httpMethod.strip().toUpperCase(Locale.ROOT);
        if (!isHttpToken(method)) {
            throw new SdkUsageError("httpMethod: expected a single verb like 'GET', got '"
                    + shown(method) + "' - a method name is an RFC 9110 token, so it "
                    + "carries no spaces, no control characters and no separators");
        }
        return method;
    }

    /** A {@code token}, not a list of known verbs: {@code PURGE} and {@code MKCOL} are real. */
    public static boolean isHttpToken(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            char character = value.charAt(i);
            boolean allowed = (character >= 'A' && character <= 'Z')
                    || (character >= 'a' && character <= 'z')
                    || (character >= '0' && character <= '9')
                    || "!#$%&'*+-.^_`|~".indexOf(character) >= 0;
            if (!allowed) {
                return false;
            }
        }
        return true;
    }

    /** A refused name may carry the line break it was refused for, and this text reaches a log. */
    private static String shown(String label) {
        StringBuilder escaped = new StringBuilder(label.length());
        for (int i = 0; i < label.length() && i < 60; i++) {
            char character = label.charAt(i);
            if (character < 0x20 || character == 0x7f) {
                escaped.append(String.format("\\x%02x", (int) character));
            } else {
                escaped.append(character);
            }
        }
        return label.length() > 60 ? escaped + "..." : escaped.toString();
    }

    // Checked here too: the token grant builds its own headers. One of three identical copies,
    // see config/SdkConfig.

    private static void checkRequestHeaders(Map<String, String> headers) {
        if (headers == null) {
            return;
        }
        String refused = null;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String value = entry.getValue();
            if (!isSendableName(entry.getKey()) || !isSendableValue(value)
                    || hasEdgeWhitespace(value)) {
                refused = entry.getKey();
                break;
            }
        }
        if (refused != null) {
            throw new SdkUsageError("header '" + shown(refused) + "': not something the "
                    + "transport can send - a name that is not an RFC 9110 token, or a value "
                    + "carrying a carriage return, a newline, a leading or trailing space or "
                    + "tab, or a character outside printable ASCII. The value is not shown: "
                    + "it may be a credential");
        }
    }

    private static boolean isSendableName(String name) {
        return isHttpToken(name);
    }

    private static boolean isSendableValue(String value) {
        if (value == null) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            char character = value.charAt(i);
            if (character != '\t' && (character < 0x20 || character > 0x7e)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasEdgeWhitespace(String value) {
        if (value.isEmpty()) {
            return false;
        }
        char first = value.charAt(0);
        char last = value.charAt(value.length() - 1);
        return first == ' ' || first == '\t' || last == ' ' || last == '\t';
    }

    private static URI buildUrl(String url, Map<String, Object> queryParams) {
        URI parsed;
        try {
            parsed = new URI(url);
        } catch (java.net.URISyntaxException | NullPointerException invalid) {
            throw new SdkUsageError("not a valid http(s) URL: " + Redaction.redactText(url));
        }
        String scheme = parsed.getScheme() == null ? "" : parsed.getScheme().toLowerCase(Locale.ROOT);
        if (!scheme.equals("http") && !scheme.equals("https") || parsed.getHost() == null) {
            throw new SdkUsageError("not a valid http(s) URL: " + Redaction.redactText(url));
        }
        if (parsed.getRawFragment() != null) {
            // Also refused here, where the query is built; a fragment is never transmitted.
            throw new SdkUsageError("url: a URL fragment ('#...') is never sent, so this "
                    + "request would reach a different endpoint from the one given - strip "
                    + "the fragment, and pass query values as queryParams. The URL is not "
                    + "shown: it may be a server-supplied link");
        }
        if (queryParams == null || queryParams.isEmpty()) {
            return parsed;
        }
        StringBuilder query = new StringBuilder(url);
        char separator = parsed.getRawQuery() == null ? '?' : '&';
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            List<?> values = entry.getValue() instanceof List<?> list
                    ? list : List.of(String.valueOf(entry.getValue()));
            for (Object value : values) {
                query.append(separator).append(encodeQueryComponent(entry.getKey()))
                        .append('=').append(encodeQueryComponent(String.valueOf(value)));
                separator = '&';
            }
        }
        return URI.create(query.toString());
    }

    /** Percent-encoded, a space as {@code %20}: a {@code +} in a query can read back as a space. */
    static String encodeQueryComponent(String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8)
                .replace("+", "%20")
                .replace("*", "%2A")
                .replace("%7E", "~");
    }

    /** Built once, so every attempt writes the same bytes. {@code null} for a bodiless verb. */
    private static EncodedBody encodeBody(String method, TransportRequest request,
            long prepareBy) {
        EncodedBody body = buildBody(request, prepareBy);
        if (body == null && requiresRequestBody(method)) {
            // No content type, so the caller's own header stands.
            return new EncodedBody(new byte[0], null, false);
        }
        return body;
    }

    private static boolean requiresRequestBody(String method) {
        return method.equals("POST") || method.equals("PUT") || method.equals("PATCH")
                || method.equals("PROPPATCH") || method.equals("REPORT");
    }

    private HttpRequest buildRequest(String method, URI url, TransportRequest request,
            EncodedBody body) {
        HttpRequest.Builder builder = HttpRequest.newBuilder(url)
                .timeout(Duration.ofMillis(HttpClientFactory.timeoutMillis(config.getTimeoutSeconds())));
        String callerType = callerContentType(request.headers());
        try {
            if (request.headers() != null) {
                for (Map.Entry<String, String> header : request.headers().entrySet()) {
                    if (CONTENT_TYPE_HEADER.equalsIgnoreCase(header.getKey())) {
                        continue;
                    }
                    builder.header(header.getKey(), header.getValue());
                }
            }
        } catch (IllegalArgumentException restricted) {
            // The JDK client owns a few names (Host, Connection, Content-Length) and refuses them.
            throw new SdkUsageError("headers: the JDK HTTP client refuses one of the names given, "
                    + "because it sets that header itself: " + restricted.getMessage());
        }
        String contentType = body != null && body.multipart ? body.contentType
                : callerType != null ? callerType : body == null ? null : body.contentType;
        if (contentType != null) {
            builder.header(CONTENT_TYPE_HEADER, contentType);
        }
        HttpRequest.BodyPublisher publisher = body == null
                ? HttpRequest.BodyPublishers.noBody()
                : HttpRequest.BodyPublishers.ofByteArray(body.bytes);
        return builder.method(method, publisher).build();
    }

    /** Last spelling wins, matching the client's header merge, so the value is the one judged. */
    private static String callerContentType(Map<String, String> headers) {
        if (headers == null) {
            return null;
        }
        String found = null;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if (CONTENT_TYPE_HEADER.equalsIgnoreCase(entry.getKey())
                    && entry.getValue() != null) {
                found = entry.getValue();
            }
        }
        return found;
    }

    /** A mapping, a list, a scalar, or JSON text. */
    public static String encodeJsonBody(Object jsonBody) {
        if (jsonBody instanceof CharSequence text) {
            return text.toString();
        }
        try {
            return Json.write(jsonBody);
        } catch (Json.JsonException error) {
            throw new SdkUsageError(
                    "jsonBody is not JSON-serialisable: " + error.getMessage(), error);
        }
    }

    /** Every {@link Path} read into its bytes, so the content is settled before the call. */
    public static Map<String, Object> materialiseFileParts(Map<String, Object> files) {
        return materialiseFileParts(files, MAX_UPLOAD_PART_BYTES);
    }

    public static Map<String, Object> materialiseFileParts(
            Map<String, Object> files, long maxPartBytes) {
        return materialiseFileParts(files, maxPartBytes, Long.MAX_VALUE, Long.MAX_VALUE);
    }

    /** Under the config's ceilings: per part, in aggregate, and the preparation deadline. */
    public static Map<String, Object> materialiseFileParts(
            Map<String, Object> files, SdkConfig config) {
        return materialiseFileParts(files, MAX_UPLOAD_PART_BYTES, config.getMaxUploadBytes(),
                System.nanoTime() + (long) (config.getTimeoutSeconds() * 1e9));
    }

    /** Reads at most {@code min(perPart, remaining) + 1}, one past the first ceiling hit. */
    private static Map<String, Object> materialiseFileParts(Map<String, Object> files,
            long maxPartBytes, long maxAggregateBytes, long prepareBy) {
        if (files == null || files.isEmpty()) {
            return files;
        }
        Map<String, Object> materialised = new LinkedHashMap<>();
        long total = 0;
        for (Map.Entry<String, Object> entry : files.entrySet()) {
            refuseIfPastPreparationBudget(prepareBy);
            Object value = entry.getValue();
            if (value instanceof Path path) {
                byte[] content = readFilePart(path, maxPartBytes,
                        maxAggregateBytes - total, prepareBy);
                total += content.length;
                materialised.put(entry.getKey(),
                        List.of(String.valueOf(path.getFileName()), content));
            } else {
                // Bytes a caller already holds count too: the per-part bound is a bound on reading.
                total += partBytes(value).length;
                materialised.put(entry.getKey(), value);
            }
            if (total > maxAggregateBytes) {
                throw refuseOversizedUploadTotal(entry.getKey(), maxAggregateBytes, total);
            }
        }
        return materialised;
    }

    private static SdkUsageError refuseOversizedUploadTotal(
            String field, long cap, long total) {
        return new SdkUsageError("files: the uploads add up to more than the " + cap
                + "-byte maxUploadBytes ceiling by the time '" + field + "' is counted"
                + (total > cap + 1 ? " (" + total + " bytes)" : "")
                + ", so they were not all read - a multipart part is buffered whole, so the "
                + "total is what the heap has to hold. Split the upload, or raise the "
                + "ceiling with SdkConfig.builder(...).maxUploadBytes(bytes)");
    }

    private static void refuseIfPastPreparationBudget(long prepareBy) {
        if (prepareBy != Long.MAX_VALUE && System.nanoTime() - prepareBy > 0) {
            throw new SdkUsageError("files: reading the named uploads ran past the "
                    + "timeoutSeconds preparation budget, so the request was abandoned "
                    + "before anything was sent");
        }
    }

    /** 64 MiB per part: a part is buffered whole so a retry can replay it. */
    public static final long MAX_UPLOAD_PART_BYTES = 64L * 1024 * 1024;

    /** Once per logical call, so a leaf whose toString changes cannot alter the 401 replay. */
    public static Map<String, Object> materialiseFormFields(Map<String, ?> formData) {
        if (formData == null) {
            return null;
        }
        Map<String, Object> materialised = new LinkedHashMap<>();
        for (Map.Entry<String, ?> entry : formData.entrySet()) {
            Object value = entry.getValue();
            materialised.put(entry.getKey(), String.valueOf(value));
        }
        return materialised;
    }

    /** Chunked against a running total; {@code Files.readAllBytes} allocates before any check. */
    private static byte[] readFilePart(Path path, long maxPartBytes,
            long remainingAggregateBytes, long prepareBy) {
        long bound = Math.max(0, Math.min(maxPartBytes, remainingAggregateBytes));
        try {
            long declared = Files.isRegularFile(path) ? Files.size(path) : -1;
            if (declared > maxPartBytes) {
                throw refuseOversizedUpload(path, maxPartBytes, declared);
            }
            // Saturating: a bound of Long.MAX_VALUE means no ceiling, not a wrapped negative.
            long ceiling = bound == Long.MAX_VALUE ? Long.MAX_VALUE : bound + 1;
            ByteArrayOutputStream collected = new ByteArrayOutputStream();
            byte[] chunk = new byte[8192];
            try (java.io.InputStream stream = Files.newInputStream(path)) {
                while (collected.size() < ceiling) {
                    refuseIfPastPreparationBudget(prepareBy);
                    int wanted = (int) Math.min(chunk.length, ceiling - collected.size());
                    int read = stream.read(chunk, 0, wanted);
                    if (read < 0) {
                        break;
                    }
                    collected.write(chunk, 0, read);
                }
            }
            if (collected.size() > maxPartBytes) {
                throw refuseOversizedUpload(path, maxPartBytes, declared);
            }
            // Past the aggregate, not the per-part bound: the caller names the ceiling hit.
            return collected.toByteArray();
        } catch (IOException error) {
            throw new SdkUsageError("files: " + path + " could not be read: "
                    + error.getMessage(), error);
        }
    }

    private static SdkUsageError refuseOversizedUpload(Path path, long cap, long declared) {
        return new SdkUsageError("files: " + path + " is over the " + cap
                + "-byte per-part upload bound"
                + (declared >= 0 ? " (" + declared + " bytes)" : "")
                + ", so it was not read - a multipart part is buffered whole, because a "
                + "part that cannot be re-read cannot be replayed on a retry or a 401 "
                + "replay. Split the upload, or read it yourself and pass the bytes with "
                + "Transport.materialiseFileParts(files, bound)");
    }

    /** One encoded body and the label it goes out under. */
    private record EncodedBody(byte[] bytes, String contentType, boolean multipart) { }

    /** All replayable, so a retry or a 401 replay re-sends the same content. */
    private static EncodedBody buildBody(TransportRequest request, long prepareBy) {
        if (request.jsonBody != null) {
            return new EncodedBody(jsonBodyBytes(request), JSON_TYPE, false);
        }
        if (request.files != null && !request.files.isEmpty()) {
            String boundary = multipartBoundary(request, prepareBy);
            ByteArrayOutputStream multipart = new ByteArrayOutputStream();
            for (Map.Entry<String, Object> entry : request.files.entrySet()) {
                addFilePart(multipart, boundary, entry.getKey(), entry.getValue());
            }
            if (request.formData != null) {
                // A form field's name is a part label here, and is escaped like one.
                request.formData.forEach((name, value) -> writePart(multipart, boundary,
                        escapeMultipartLabel(name), null, null,
                        String.valueOf(value).getBytes(StandardCharsets.UTF_8)));
            }
            writeAscii(multipart, "--" + boundary + "--\r\n");
            return new EncodedBody(multipart.toByteArray(),
                    "multipart/form-data; boundary=" + boundary, true);
        }
        if (request.formData != null) {
            StringBuilder form = new StringBuilder();
            request.formData.forEach((name, value) -> {
                if (form.length() > 0) {
                    form.append('&');
                }
                form.append(encodeQueryComponent(name)).append('=')
                        .append(encodeQueryComponent(String.valueOf(value)));
            });
            return new EncodedBody(form.toString().getBytes(StandardCharsets.UTF_8),
                    FORM_TYPE, false);
        }
        return null;
    }

    /** A fresh array, so the bytes sent cannot be edited through the capture. */
    private static byte[] jsonBodyBytes(TransportRequest request) {
        if (request.jsonBody instanceof byte[] bytes) {
            return bytes.clone();
        }
        return encodeJsonBody(request.jsonBody).getBytes(StandardCharsets.UTF_8);
    }

    /** What every generated multipart boundary starts with, so one is recognisable. */
    private static final String BOUNDARY_PREFIX = "ExsitedSdkBoundary";

    /** Derived from the captured content and never drawn at random. */
    private static String multipartBoundary(TransportRequest request, long prepareBy) {
        for (int salt = 0; salt < 1000; salt++) {
            // Each iteration digests every part, so the loop is bounded by the deadline too.
            if (System.nanoTime() - prepareBy > 0) {
                throw new SdkUsageError("files: deriving a multipart boundary for this body "
                        + "ran past the timeoutSeconds preparation budget, so it was "
                        + "abandoned before anything was sent");
            }
            String candidate = BOUNDARY_PREFIX + digestOfParts(request, salt);
            if (!partsContain(request, candidate)) {
                return candidate;
            }
        }
        // A random boundary would reintroduce the per-attempt difference.
        throw new SdkUsageError("files: no multipart boundary could be derived that this "
                + "body does not already contain, so the parts could not be framed");
    }

    /** The salted digest of every part label and every byte the parts carry. */
    private static String digestOfParts(TransportRequest request, int salt) {
        java.security.MessageDigest digest;
        try {
            digest = java.security.MessageDigest.getInstance("SHA-256");
        } catch (java.security.NoSuchAlgorithmException impossible) {
            throw new IllegalStateException("SHA-256 is unavailable", impossible);
        }
        digest.update(new byte[] {(byte) salt, (byte) (salt >>> 8)});
        request.files.forEach((field, value) -> {
            digestLabel(digest, field);
            digestLabel(digest, multipartFilename(field, value));
            digestLabel(digest, declaredPartContentType(value));
            digest.update(partBytes(value));
            digest.update((byte) 0);
        });
        if (request.formData != null) {
            request.formData.forEach((name, value) -> {
                digestLabel(digest, name);
                digestLabel(digest, String.valueOf(value));
            });
        }
        byte[] hashed = digest.digest();
        StringBuilder hex = new StringBuilder(32);
        for (int index = 0; index < 16; index++) {
            hex.append(String.format("%02x", hashed[index]));
        }
        return hex.toString();
    }

    private static void digestLabel(java.security.MessageDigest digest, String label) {
        if (label != null) {
            digest.update(label.getBytes(StandardCharsets.UTF_8));
        }
        digest.update((byte) 0);
    }

    /** The captured array, deliberately: callers only read it, and a copy would copy the upload. */
    private static byte[] partBytes(Object value) {
        if (value instanceof byte[] bytes) {
            return bytes;
        }
        if (value instanceof List<?> tuple && tuple.size() >= 2
                && tuple.get(1) instanceof byte[] bytes) {
            return bytes;
        }
        return new byte[0];
    }

    private static boolean partsContain(TransportRequest request, String candidate) {
        byte[] needle = candidate.getBytes(StandardCharsets.UTF_8);
        for (Map.Entry<String, Object> entry : request.files.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            if (labelContains(field, candidate)
                    || labelContains(multipartFilename(field, value), candidate)
                    || labelContains(declaredPartContentType(value), candidate)
                    || indexOfBytes(partBytes(value), needle) >= 0) {
                return true;
            }
        }
        if (request.formData != null) {
            for (Map.Entry<String, Object> entry : request.formData.entrySet()) {
                if (labelContains(entry.getKey(), candidate)
                        || labelContains(String.valueOf(entry.getValue()), candidate)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean labelContains(String label, String candidate) {
        return label != null && label.contains(candidate);
    }

    private static int indexOfBytes(byte[] haystack, byte[] needle) {
        outer:
        for (int start = 0; start + needle.length <= haystack.length; start++) {
            for (int index = 0; index < needle.length; index++) {
                if (haystack[start + index] != needle[index]) {
                    continue outer;
                }
            }
            return start;
        }
        return -1;
    }

    private static void addFilePart(ByteArrayOutputStream multipart, String boundary,
            String field, Object value) {
        if (value instanceof byte[] bytes) {
            addBytesPart(multipart, boundary, field, multipartFilename(field, value), bytes, null);
        } else if (value instanceof List<?> tuple && tuple.size() >= 2
                && tuple.get(0) instanceof String filename && tuple.get(1) instanceof byte[] bytes) {
            String declared = tuple.size() > 2 && tuple.get(2) instanceof String given ? given : null;
            addBytesPart(multipart, boundary, field, filename, bytes, declared);
        } else {
            throw refuseFilePart(field);
        }
    }

    public static boolean isSendableFilePart(Object value) {
        if (value instanceof byte[] || value instanceof Path) {
            return true;
        }
        return value instanceof List<?> tuple && tuple.size() >= 2
                && tuple.get(0) instanceof String && tuple.get(1) instanceof byte[];
    }

    /** The value itself is never shown, because a file part is caller data. */
    public static SdkUsageError refuseFilePart(String field) {
        return new SdkUsageError("files: '" + field + "' must be byte[], a Path, or "
                + "[filename, byte[], contentType?] - a raw stream cannot be replayed on a "
                + "retry, so buffer it first");
    }

    private static void addBytesPart(ByteArrayOutputStream multipart, String boundary,
            String field, String filename, byte[] bytes, String declaredContentType) {
        writePart(multipart, boundary, escapeMultipartLabel(field), escapeMultipartLabel(filename),
                partContentType(field, filename, bytes, declaredContentType), bytes);
    }

    /** One part as the multipart grammar frames it: headers, a blank line, the content. */
    private static void writePart(ByteArrayOutputStream multipart, String boundary,
            String name, String filename, String contentType, byte[] content) {
        StringBuilder headers = new StringBuilder("--").append(boundary).append("\r\n")
                .append("Content-Disposition: form-data; name=\"").append(name).append('"');
        if (filename != null) {
            headers.append("; filename=\"").append(filename).append('"');
        }
        headers.append("\r\n");
        if (contentType != null) {
            headers.append("Content-Type: ").append(contentType).append("\r\n");
        }
        headers.append("Content-Length: ").append(content.length).append("\r\n\r\n");
        multipart.writeBytes(headers.toString().getBytes(StandardCharsets.UTF_8));
        multipart.writeBytes(content);
        writeAscii(multipart, "\r\n");
    }

    private static void writeAscii(ByteArrayOutputStream out, String text) {
        out.writeBytes(text.getBytes(StandardCharsets.US_ASCII));
    }

    public static void checkMultipartLabels(
            Map<String, Object> files, Map<String, Object> formData) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (String field : sortedLabels(files)) {
            refuseMultipartLabelBytes(
                    "a files part name of " + utf8Length(field) + " byte(s)", field);
            Object value = files.get(field);
            refuseMultipartLabelBytes("the filename on part '" + field + "'",
                    multipartFilename(field, value));
            refuseMultipartLabelBytes("the content type on part '" + field + "'",
                    declaredPartContentType(value));
        }
        if (formData != null) {
            for (String field : sortedLabels(formData)) {
                refuseMultipartLabelBytes(
                        "a formData name of " + utf8Length(field) + " byte(s)", field);
            }
        }
    }

    /** A CR, an LF or a NUL in a label would become part of a part header. */
    private static void refuseMultipartLabelBytes(String what, String value) {
        if (value == null) {
            return;
        }
        for (int index = 0; index < value.length(); index++) {
            String named = switch (value.charAt(index)) {
                case '\r' -> "a carriage return";
                case '\n' -> "a newline";
                case '\0' -> "a NUL";
                default -> null;
            };
            if (named != null) {
                throw new SdkUsageError(what + " carries " + named + " at byte "
                        + utf8Length(value.substring(0, index)) + ", which would forge a "
                        + "header on the multipart part this SDK sends. The value is not "
                        + "shown: it may be attacker-chosen. Strip CR, LF and NUL from an "
                        + "upload filename before passing it on");
            }
        }
    }

    private static String escapeMultipartLabel(String label) {
        return label == null ? null : label.replace("\\", "%5C").replace("\"", "%22");
    }

    private static String declaredPartContentType(Object value) {
        return value instanceof List<?> tuple && tuple.size() > 2
                && tuple.get(2) instanceof String declared ? declared : null;
    }

    /** A stable order, so a body with two bad labels names the same one every run. */
    private static List<String> sortedLabels(Map<String, Object> values) {
        List<String> labels = new java.util.ArrayList<>(values.keySet());
        labels.sort(java.util.Comparator.nullsFirst(java.util.Comparator.naturalOrder()));
        return labels;
    }

    private static int utf8Length(String label) {
        return label == null ? 0 : label.getBytes(StandardCharsets.UTF_8).length;
    }

    /** Public because the console's curl line renders the same answer. */
    public static String multipartFilename(String field, Object value) {
        if (value instanceof Path path) {
            return String.valueOf(path.getFileName());
        }
        if (value instanceof List<?> tuple && !tuple.isEmpty()
                && tuple.get(0) instanceof String filename) {
            return filename;
        }
        if (value instanceof byte[] bytes) {
            return extensionOf(field) == null ? field + sniffContent(bytes).extension() : field;
        }
        return field;
    }

    /** The caller's own content type, then the filename's extension, then the content. */
    private static String partContentType(String field, String filename, byte[] bytes,
            String declaredContentType) {
        if (declaredContentType != null) {
            if (!isPartMediaType(declaredContentType)) {
                // The value is withheld, as the header refusals withhold theirs.
                throw new SdkUsageError("files: '" + field + "' declared a content type that is "
                        + "not a media type - the third tuple slot takes a form like 'text/csv', "
                        + "or leave it out and the SDK derives one from the content");
            }
            return declaredContentType;
        }
        String extension = extensionOf(filename);
        String byExtension = extension == null ? null : CONTENT_TYPES_BY_EXTENSION.get(extension);
        return byExtension != null ? byExtension : sniffContent(bytes).contentType();
    }

    /** {@code type/subtype} of token characters, optionally followed by parameters. */
    private static boolean isPartMediaType(String value) {
        String head = MediaType.mediaTypeOf(value);
        int slash = head.indexOf('/');
        return slash > 0 && slash < head.length() - 1
                && isHttpToken(head.substring(0, slash)) && isHttpToken(head.substring(slash + 1))
                && isSendableValue(value);
    }

    private record SniffedContent(String extension, String contentType) {}

    private static final SniffedContent XLSX_CONTENT = new SniffedContent(".xlsx",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    private static final SniffedContent XLS_CONTENT =
            new SniffedContent(".xls", "application/vnd.ms-excel");
    private static final SniffedContent CSV_CONTENT = new SniffedContent(".csv", "text/csv");
    private static final SniffedContent BINARY_CONTENT =
            new SniffedContent(".bin", "application/octet-stream");

    /** The ZIP header every XLSX starts with: an XLSX is a zip of XML parts. */
    private static final byte[] ZIP_MAGIC = {'P', 'K', 0x03, 0x04};
    private static final byte[] OLE2_MAGIC = {(byte) 0xD0, (byte) 0xCF, 0x11, (byte) 0xE0};
    private static final int SNIFF_LIMIT = 512;

    private static final Map<String, String> CONTENT_TYPES_BY_EXTENSION = Map.of(
            ".xlsx", XLSX_CONTENT.contentType(),
            ".xls", XLS_CONTENT.contentType(),
            ".csv", CSV_CONTENT.contentType(),
            ".txt", "text/plain",
            ".json", "application/json",
            ".xml", "application/xml",
            ".zip", "application/zip",
            ".pdf", "application/pdf");

    private static SniffedContent sniffContent(byte[] bytes) {
        if (startsWith(bytes, ZIP_MAGIC)) {
            return XLSX_CONTENT;
        }
        if (startsWith(bytes, OLE2_MAGIC)) {
            return XLS_CONTENT;
        }
        return looksLikeText(bytes) ? CSV_CONTENT : BINARY_CONTENT;
    }

    private static boolean startsWith(byte[] bytes, byte[] magic) {
        if (bytes.length < magic.length) {
            return false;
        }
        for (int index = 0; index < magic.length; index++) {
            if (bytes[index] != magic[index]) {
                return false;
            }
        }
        return true;
    }

    /** No NUL and no C0 control other than tab, newline and carriage return. */
    private static boolean looksLikeText(byte[] bytes) {
        if (bytes.length == 0) {
            return false;
        }
        int limit = Math.min(bytes.length, SNIFF_LIMIT);
        for (int index = 0; index < limit; index++) {
            int value = bytes[index] & 0xFF;
            if (value < 0x20 && value != '\t' && value != '\n' && value != '\r') {
                return false;
            }
            if (value == 0x7F) {
                return false;
            }
        }
        return true;
    }

    private static String extensionOf(String filename) {
        if (filename == null) {
            return null;
        }
        int nameStart = Math.max(filename.lastIndexOf('/'), filename.lastIndexOf('\\')) + 1;
        String name = filename.substring(nameStart);
        int dot = name.lastIndexOf('.');
        if (dot <= 0 || dot == name.length() - 1) {
            return null;
        }
        return name.substring(dot).toLowerCase(Locale.ROOT);
    }

    /** Keeps {@code bodyBytes} so {@code getJson()} returns the API's own text. */
    private ApiResponse<Dto> buildResponse(
            HttpResponse<byte[]> raw, Object decoded, byte[] bodyBytes, String contentType,
            String method, TransportRequest request, int attempt, int maxAttempts, long started) {
        Map<String, String> headers = new LinkedHashMap<>();
        raw.headers().map().forEach((name, values) -> {
            if (!values.isEmpty()) {
                headers.put(name, values.get(values.size() - 1));
            }
        });
        return new ApiResponse<>(raw.statusCode(), decoded, headers, contentType,
                requestInfo(method, request, attempt, maxAttempts, started),
                ErrorType.classify(raw.statusCode()), bodyBytes, null, null);
    }

    /** The resolved URL, redacted, only when {@link SdkError#ERROR_DETAIL_PROPERTY} opts in. */
    private static String endpoint(TransportRequest request, URI url) {
        if (SdkError.showsFullDetail()) {
            return Redaction.redactText(url.toString());
        }
        if (request.pathTemplate() != null) {
            return request.pathTemplate();
        }
        return Redaction.reduceToEndpoint(url.getRawPath());
    }

    /** The lower layer's own text, appended only when detail is opted into. */
    private static String detailOf(Throwable failure) {
        if (failure == null || !SdkError.showsFullDetail()) {
            return "";
        }
        return ": " + Redaction.describeFailure(failure);
    }

    private RequestInfo requestInfo(
            String method, TransportRequest request,
            int attempt, int maxAttempts, long started) {
        // The URL before the query was baked on, so a diagnostic shows the filters once.
        return new RequestInfo(
                config.getClientName(),
                config.getApiVersion(),
                method,
                request.url(),
                // RequestInfo snapshots what it is given, so a copy here would be thrown away.
                request.queryParams,
                request.requestInfoBody,
                request.requestInfoHeaders == null ? Map.of() : request.requestInfoHeaders,
                attempt,
                maxAttempts,
                Math.round((System.nanoTime() - started) / 1e5) / 1e4,
                request.pathTemplate());
    }

    /** JSON to Maps/Lists, {@code text/*} to text, else raw bytes; empty or 204 is {@code null}. */
    public static Object decodeResponseBody(int status, byte[] body, String contentType) {
        if (status == 204 || body == null || body.length == 0) {
            return null;
        }
        String lowered = contentType == null ? "" : contentType.toLowerCase(Locale.ROOT);
        if (isJsonMediaType(lowered)) {
            String text = new String(body, StandardCharsets.UTF_8);
            try {
                return Json.parse(text);
            } catch (Json.JsonException error) {
                return text;
            }
        }
        if (lowered.startsWith("text/")) {
            Charset declared = declaredCharset(lowered);
            if (declared != null) {
                return new String(body, declared);
            }
            try {
                // Strict, so malformed bytes report rather than becoming an unrecoverable U+FFFD.
                return StandardCharsets.UTF_8.newDecoder()
                        .onMalformedInput(java.nio.charset.CodingErrorAction.REPORT)
                        .onUnmappableCharacter(java.nio.charset.CodingErrorAction.REPORT)
                        .decode(java.nio.ByteBuffer.wrap(body)).toString();
            } catch (java.nio.charset.CharacterCodingException notUtf8) {
                return new String(body, TEXT_FALLBACK_CHARSET);
            }
        }
        return body;
    }

    /** Includes RFC 6839 {@code +json} suffixes such as {@code application/problem+json}. */
    static boolean isJsonMediaType(String loweredContentType) {
        return MediaType.isJson(loweredContentType);
    }

    /** The RFC 2616 default for a charset-less {@code text/*} body; this codec cannot fail. */
    private static final Charset TEXT_FALLBACK_CHARSET = StandardCharsets.ISO_8859_1;

    private static Charset declaredCharset(String loweredContentType) {
        int marker = loweredContentType.indexOf("charset=");
        if (marker < 0) {
            return null;
        }
        String name = loweredContentType.substring(marker + "charset=".length()).split("[;\\s]")[0];
        try {
            return Charset.forName(name.replace("\"", ""));
        } catch (Exception error) {
            // An unusable declaration takes the undeclared ladder.
            return null;
        }
    }

    /** {@code false} means interrupted: the flag is restored, and the call must be abandoned. */
    private static boolean sleepSeconds(double seconds) {
        if (seconds <= 0) {
            return true;
        }
        try {
            Thread.sleep((long) (seconds * 1000));
            return true;
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    /** The whole chain: the JDK names a timeout {@code HttpTimeoutException}, a socket its own. */
    static boolean isTimeoutFailure(Throwable failure) {
        Set<Throwable> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        for (Throwable current = failure; current != null && seen.add(current);
                current = current.getCause()) {
            if (current instanceof SocketTimeoutException || current instanceof HttpTimeoutException) {
                return true;
            }
        }
        return false;
    }

    /** Not {@link ErrorType#NETWORK}: that is the retryable class, and the caller cancelled. */
    private SdkError interruptedSend(String method, URI url, TransportRequest request,
            int requestsSent, long started, Exception cause) {
        Thread.currentThread().interrupt();
        return new SdkError(
                method + " " + endpoint(request, url)
                        + " was interrupted after " + requestsSent + " attempt(s)"
                        + detailOf(cause),
                null,
                ErrorType.UNKNOWN,
                null,
                null,
                requestInfo(method, request, requestsSent, retryPolicy.getMaxAttempts(), started),
                // Which exception arrived says where the cancellation landed.
                cause);
    }

    /** Refuses a body over {@code maxResponseBytes}: a declared length before any byte is pulled. */
    private HttpResponse.BodyHandler<byte[]> boundedBody() {
        long cap = config.getMaxResponseBytes();
        return responseInfo -> new BoundedBodySubscriber(cap,
                declaredLength(responseInfo.headers().firstValue("content-length")));
    }

    /** A declared length, or -1 for absent, blank, negative or not a number. */
    private static long declaredLength(java.util.Optional<String> header) {
        if (header.isEmpty()) {
            return -1L;
        }
        try {
            long declared = Long.parseLong(header.get().strip());
            return declared < 0 ? -1L : declared;
        } catch (NumberFormatException notANumber) {
            return -1L;
        }
    }

    /** A body refused for size, travelling out of the JDK client as the IOException it must be. */
    private static final class ResponseTooLarge extends IOException {
        private static final long serialVersionUID = 1L;
        private final long cap;
        private final long declared;

        ResponseTooLarge(long cap, long declared) {
            super("response body over the " + cap + "-byte ceiling");
            this.cap = cap;
            this.declared = declared;
        }
    }

    private static ResponseTooLarge oversizeIn(Throwable error) {
        Set<Throwable> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        for (Throwable walked = error; walked != null && seen.add(walked);
                walked = walked.getCause()) {
            if (walked instanceof ResponseTooLarge oversize) {
                return oversize;
            }
        }
        return null;
    }

    /** One buffer at a time, each judged before it is kept, so nothing over the cap is held. */
    private static final class BoundedBodySubscriber
            implements HttpResponse.BodySubscriber<byte[]> {

        private final long cap;
        private final long declared;
        private final CompletableFuture<byte[]> result = new CompletableFuture<>();
        private final ByteArrayOutputStream held = new ByteArrayOutputStream();
        private java.util.concurrent.Flow.Subscription subscription;
        private long size;

        BoundedBodySubscriber(long cap, long declared) {
            this.cap = cap;
            this.declared = declared;
        }

        @Override
        public java.util.concurrent.CompletionStage<byte[]> getBody() {
            return result;
        }

        @Override
        public void onSubscribe(java.util.concurrent.Flow.Subscription subscription) {
            this.subscription = subscription;
            if (declared > cap) {
                // Cancelled with no demand signalled, so a hostile Content-Length costs nothing.
                subscription.cancel();
                result.completeExceptionally(new ResponseTooLarge(cap, declared));
                return;
            }
            subscription.request(1);
        }

        @Override
        public void onNext(List<java.nio.ByteBuffer> buffers) {
            for (java.nio.ByteBuffer buffer : buffers) {
                int remaining = buffer.remaining();
                if (size + remaining > cap) {
                    subscription.cancel();
                    result.completeExceptionally(new ResponseTooLarge(cap, declared));
                    return;
                }
                byte[] chunk = new byte[remaining];
                buffer.get(chunk);
                held.write(chunk, 0, remaining);
                size += remaining;
            }
            subscription.request(1);
        }

        @Override
        public void onError(Throwable error) {
            result.completeExceptionally(error);
        }

        @Override
        public void onComplete() {
            result.complete(held.toByteArray());
        }
    }

    /** The token layer's request shape: a mutable holder, sent with {@link #send(Request)}. */
    public static final class Request {
        public String method = "GET";
        public String url;
        /** A map or list to encode, or a String to send byte for byte. */
        public Object jsonBody;
        public Map<String, String> formData;
        public Map<String, String> headers = new LinkedHashMap<>();
    }

    /** One decoded exchange, as the token layer reads it. */
    public static final class Exchange {
        public final int status;
        public final Map<String, String> headers;
        public final String contentType;
        /** The body's text, or null for a binary or empty body. */
        public final String text;
        /** The decoded body: map/list for JSON, String for text, byte[] otherwise. */
        public final Object decoded;
        public final int attempts;
        public final long elapsedMillis;

        Exchange(int status, Map<String, String> headers, String contentType,
                 String text, Object decoded, int attempts, long elapsedMillis) {
            this.status = status;
            this.headers = headers;
            this.contentType = contentType;
            this.text = text;
            this.decoded = decoded;
            this.attempts = attempts;
            this.elapsedMillis = elapsedMillis;
        }
    }

    /** {@link #sendHttpRequest} for the token layer, whose own seam this is. */
    public Exchange send(Request request) {
        Map<String, Object> form = request.formData == null ? null : new LinkedHashMap<>(request.formData);
        TransportRequest built = TransportRequest.builder(request.method, request.url)
                .jsonBody(request.jsonBody)
                .formData(form)
                .headers(request.headers)
                .requestInfoHeaders(request.headers == null ? null
                        : Redaction.redactMapping(request.headers))
                .pathTemplate(request.url == null ? null : Redaction.reduceToEndpoint(request.url))
                .build();
        ApiResponse<Dto> response = sendHttpRequest(built);
        RequestInfo info = response.getRequestInfo();
        return new Exchange(response.getStatusCode(), response.getHeaders(),
                response.getContentType(), response.getJson(), response.getMap(),
                info.getAttemptCount(), Math.round(info.getElapsedSeconds() * 1000));
    }

    /** The captured request: a list query value is sent as one pair per element. */
    public record TransportRequest(
            String httpMethod,
            String url,
            Map<String, Object> queryParams,
            Object jsonBody,
            Map<String, Object> formData,
            Map<String, Object> files,
            Map<String, String> headers,
            Map<String, String> requestInfoHeaders,
            Object requestInfoBody,
            String pathTemplate) {

        /** The nine-argument form, with no path template. */
        public TransportRequest(
                String httpMethod,
                String url,
                Map<String, Object> queryParams,
                Object jsonBody,
                Map<String, Object> formData,
                Map<String, Object> files,
                Map<String, String> headers,
                Map<String, String> requestInfoHeaders,
                Object requestInfoBody) {
            this(httpMethod, url, queryParams, jsonBody, formData, files, headers,
                    requestInfoHeaders, requestInfoBody, null);
        }

        /** Capture the caller's collections, so every attempt sends one content. */
        public TransportRequest {
            queryParams = detach(queryParams);
            jsonBody = Redaction.detachValue(jsonBody);
            formData = formData instanceof Owned
                    ? formData : detach(materialiseFormFields(formData));
            files = files instanceof Owned ? files : detach(materialiseFileParts(files));
            headers = detach(headers);
            requestInfoHeaders = detach(requestInfoHeaders);
            requestInfoBody = Redaction.detachValue(requestInfoBody);
        }

        /** Keeps {@code null} distinct from empty: an absent body and an empty one differ. */
        private static <V> Map<String, V> detach(Map<String, V> values) {
            if (values == null) {
                return null;
            }
            return values instanceof Owned ? values : new Owned<>(Redaction.detachMapping(values));
        }

        /** A capture this type already made, so re-capturing it can be skipped. */
        private static final class Owned<V> extends java.util.AbstractMap<String, V> {
            private final Map<String, V> entries;

            Owned(Map<String, V> entries) {
                this.entries = entries;
            }

            @Override
            public Set<Map.Entry<String, V>> entrySet() {
                return entries.entrySet();
            }

            @Override
            public V get(Object key) {
                return entries.get(key);
            }

            @Override
            public boolean containsKey(Object key) {
                return entries.containsKey(key);
            }

            @Override
            public int size() {
                return entries.size();
            }

            @Override
            public boolean isEmpty() {
                return entries.isEmpty();
            }

            @Override
            public Set<String> keySet() {
                return entries.keySet();
            }

            @Override
            public java.util.Collection<V> values() {
                return entries.values();
            }

            @Override
            public void forEach(java.util.function.BiConsumer<? super String, ? super V> action) {
                entries.forEach(action);
            }
        }

        /** A copy: Java has no immutable array, so returning its own would leak the capture. */
        public Map<String, Object> files() {
            return files == null ? null : Redaction.detachMapping(files);
        }

        /** A fresh {@code byte[]} for a rendered document, else the unmodifiable capture. */
        public Object jsonBody() {
            return jsonBody instanceof byte[] bytes ? bytes.clone() : jsonBody;
        }

        /** A copy, because a value may be a {@code byte[]} and Java has no immutable array. */
        public Map<String, Object> queryParams() {
            return queryParams == null ? null : Redaction.detachMapping(queryParams);
        }

        public Object requestInfoBody() {
            return Redaction.detachValue(requestInfoBody);
        }

        /** How the 401 replay puts a fresh bearer token on an otherwise byte-identical request. */
        public TransportRequest withHeaders(
                Map<String, String> headers, Map<String, String> requestInfoHeaders) {
            return new TransportRequest(httpMethod, url, queryParams, jsonBody, formData,
                    files, headers, requestInfoHeaders, requestInfoBody, pathTemplate);
        }

        public static Builder builder(String httpMethod, String url) {
            return new Builder(httpMethod, url);
        }

        /** Not thread-safe; build then share. */
        public static final class Builder {
            private final String httpMethod;
            private final String url;
            private Map<String, Object> queryParams;
            private Object jsonBody;
            private Map<String, Object> formData;
            private Map<String, Object> files;
            private Map<String, String> headers;
            private Map<String, String> requestInfoHeaders;
            private Object requestInfoBody;
            private String pathTemplate;

            private Builder(String httpMethod, String url) {
                this.httpMethod = httpMethod;
                this.url = url;
            }

            public Builder queryParams(Map<String, Object> queryParams) {
                this.queryParams = queryParams;
                return this;
            }

            /** Rendered UTF-8 wire bytes are cloned and sent as they are; text is sent verbatim. */
            public Builder jsonBody(Object jsonBody) {
                this.jsonBody = jsonBody;
                return this;
            }

            public Builder formData(Map<String, Object> formData) {
                this.formData = formData;
                return this;
            }

            /** Each a {@link Path}, a {@code byte[]} or a {@code [filename, bytes, type?]} list. */
            public Builder files(Map<String, Object> files) {
                this.files = files;
                return this;
            }

            public Builder headers(Map<String, String> headers) {
                this.headers = headers;
                return this;
            }

            /** Already passed through redaction; the live headers must not be passed here. */
            public Builder requestInfoHeaders(Map<String, String> requestInfoHeaders) {
                this.requestInfoHeaders = requestInfoHeaders;
                return this;
            }

            /** Already passed through redaction; the live body must not be passed here. */
            public Builder requestInfoBody(Object requestInfoBody) {
                this.requestInfoBody = requestInfoBody;
                return this;
            }

            /** What a default diagnostic names: it identifies the endpoint and nobody on it. */
            public Builder pathTemplate(String pathTemplate) {
                this.pathTemplate = pathTemplate;
                return this;
            }

            public TransportRequest build() {
                return new TransportRequest(httpMethod, url, queryParams, jsonBody,
                        formData, files, headers, requestInfoHeaders, requestInfoBody,
                        pathTemplate);
            }
        }
    }
}
