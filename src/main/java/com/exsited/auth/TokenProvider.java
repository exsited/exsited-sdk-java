package com.exsited.auth;

import com.exsited.Redaction;
import com.exsited.errors.SdkAuthError;
import com.exsited.errors.SdkError;
import com.exsited.errors.SdkTokenCacheError;
import com.exsited.errors.SdkTokenLockError;
import com.exsited.errors.SdkUsageError;
import com.exsited.config.SdkConfig;
import com.exsited.http.Transport;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Obtaining and refreshing the OAuth2 access token.
 *
 * <p>Success means any 2xx: the token endpoint may answer 200 or 201. It issues a
 * refresh token and honours the {@code refresh_token} grant, so a renewal tries
 * that first and falls back to a full {@code client_credentials} request.
 *
 * <p>A v4 client authenticates at {@code /api/v4/oauth2/token}.
 */
public final class TokenProvider {

    public static final long DEFAULT_TOKEN_TTL_SECONDS = 3600L;
    /** Refreshed this long before expiry, so a token never expires mid-flight. */
    public static final long EXPIRY_SKEW_SECONDS = 60L;
    /**
     * The longest lifetime believed from the server. {@code expiresAt} is an
     * absolute stamp from whichever machine minted the token, so a clock running
     * ahead could make an expired one look fresh; bounding the window lets it reach
     * the API and be answered 401, which refresh-and-replay already handles.
     */
    public static final long MAX_TOKEN_TTL_SECONDS = 24 * 60 * 60L;

    private final SdkConfig config;
    private final TokenStore store;
    private final Transport transport;
    private final String namespace;

    public TokenProvider(SdkConfig config, TokenStore store, Transport transport) {
        this.config = config;
        this.store = store;
        this.transport = transport;
        this.namespace = FileTokenStore.buildCredentialsNamespace(
                config.baseUrl(),
                config.auth().clientId(),
                config.auth().clientSecret(),
                config.auth().grantType());
    }

    public String tokenUrl() {
        return config.baseUrl() + config.tokenPath();
    }

    /** A usable access token, fetched only if needed. */
    public String accessToken() {
        TokenRecord cached = store.read(namespace);
        if (cached != null && !cached.needsRefresh(EXPIRY_SKEW_SECONDS * 1000)) {
            return cached.accessToken();
        }
        return renew(null);
    }

    /**
     * Replace a token the API has just rejected.
     *
     * <p>If the store already holds a different one, another worker refreshed first
     * and that token is used rather than minting a third.
     */
    public String refresh(String staleAccessToken) {
        return renew(staleAccessToken);
    }

    /**
     * Drop a token the API has refused, if the store still holds that exact one.
     *
     * <p>Called when a 401 was read as a token failure and the refresh then failed; without it
     * the refused token stays cached, and {@link FileTokenStore} hands it to every later
     * process. Compare and clear, never a bare clear: another worker may have stored a good
     * token meanwhile, and on these credentials a new grant invalidates the previous one. Runs
     * under the lock {@link #renew} writes under, so no writer lands between the read and the
     * clear.
     *
     * @param deadAccessToken the token the API refused
     * @return whether this call removed it: false for a null or blank token, when the store
     *     holds something else or nothing, and when the store could not be reached
     */
    public boolean invalidateAccessToken(String deadAccessToken) {
        if (deadAccessToken == null || deadAccessToken.isBlank()) {
            return false;
        }
        try {
            return store.withLock(namespace, () -> {
                TokenRecord cached = store.read(namespace);
                if (cached == null || !deadAccessToken.equals(cached.accessToken())) {
                    return false;
                }
                store.clear(namespace);
                return true;
            });
        } catch (SdkTokenLockError | SdkTokenCacheError error) {
            // Absorbed: the caller is about to receive the failed refresh, and a cache
            // error here would replace that diagnosis. The store has already printed it.
            return false;
        }
    }

    private String renew(String staleAccessToken) {
        TokenRecord existing = store.read(namespace);
        if (existing != null
                && !existing.accessToken().equals(staleAccessToken)
                && !existing.needsRefresh(EXPIRY_SKEW_SECONDS * 1000)) {
            return existing.accessToken();
        }

        TokenRecord minted = null;
        if (existing != null && existing.refreshToken() != null) {
            minted = requestWithRefreshGrant(existing.refreshToken());
        }
        if (minted == null) {
            minted = requestWithCredentials();
        }

        TokenRecord toStore = minted;
        try {
            store.withLock(namespace, () -> {
                store.write(namespace, toStore);
                return null;
            });
        } catch (SdkTokenLockError | SdkTokenCacheError error) {
            // Absorbed: the grant is done and the token is good, so only the sharing is
            // lost. The store has already printed the condition.
            return minted.accessToken();
        }
        return minted.accessToken();
    }

    private TokenRecord requestWithCredentials() {
        // The four fields the token endpoint takes. It has no authString credential,
        // and it ignores a field it does not recognise rather than refusing it.
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("grant_type", config.auth().grantType());
        payload.put("client_id", config.auth().clientId());
        payload.put("client_secret", config.auth().clientSecret());
        payload.put("redirect_uri", config.auth().redirectUri());

        Transport.Exchange exchange = post(payload);
        if (exchange.status >= 300 && exchange.status < 400) {
            throw refused("the API answered " + exchange.status
                    + (redirectsToSignIn(exchange)
                            ? ", redirecting to its sign-in page"
                            : ", a redirect instead of a token"),
                    exchange.status);
        }
        if (exchange.status >= 200 && exchange.status < 300 && answeredWithAPage(exchange)) {
            throw refused("the API answered " + exchange.status
                    + " with an HTML page, its sign-in page, instead of a token", exchange.status);
        }
        if (exchange.status < 200 || exchange.status >= 300) {
            throw new SdkAuthError(
                    "token request failed with " + exchange.status + ": "
                            + (exchange.text == null ? "no detail" : truncate(exchange.text)),
                    new SdkError.Details()
                            .statusCode(exchange.status)
                            .errorType("authentication")
                            .rawResponse(Redaction.redactValue(exchange.decoded)));
        }
        return buildRecord(exchange.decoded, null);
    }

    private TokenRecord requestWithRefreshGrant(String refreshToken) {
        // No redirect_uri: the refresh grant does not take one.
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("grant_type", "refresh_token");
        payload.put("client_id", config.auth().clientId());
        payload.put("client_secret", config.auth().clientSecret());
        payload.put("refresh_token", refreshToken);
        try {
            Transport.Exchange exchange = post(payload);
            if (exchange.status < 200 || exchange.status >= 300) {
                return null;
            }
            return buildRecord(exchange.decoded, refreshToken);
        } catch (SdkError error) {
            // A failed refresh must fall back to a full grant, not propagate.
            return null;
        }
    }

    private Transport.Exchange post(Map<String, Object> payload) {
        Transport.Request request = new Transport.Request();
        request.method = "POST";
        request.url = tokenUrl();
        request.jsonBody = payload;
        request.headers.put("Content-Type", "application/json");
        request.headers.put("Accept", "application/json");
        request.headers.put("User-Agent", config.userAgent());
        return transport.send(request);
    }

    private TokenRecord buildRecord(Object body, String previousRefreshToken) {
        if (!(body instanceof Map<?, ?> map)) {
            throw new SdkAuthError("token response was not a JSON object",
                    new SdkError.Details().errorType("authentication"));
        }
        Object accessToken = map.get("access_token");
        if (accessToken == null || "".equals(accessToken)) {
            throw new SdkAuthError("token response carried no access_token",
                    new SdkError.Details()
                            .errorType("authentication")
                            .rawResponse(Redaction.redactValue(body)));
        }
        long ttl = DEFAULT_TOKEN_TTL_SECONDS;
        if (map.get("expires_in") instanceof Number number) {
            ttl = Math.min(Math.max(number.longValue(), 0), MAX_TOKEN_TTL_SECONDS);
        }
        Object refreshToken = map.get("refresh_token") != null
                ? map.get("refresh_token")
                : previousRefreshToken;
        try {
            // Checked as it arrived, never coerced: a numeric access_token is refused as
            // a type rather than sent as its decimal text.
            return new TokenRecord(
                    TokenCredentials.checkAccessCredential(accessToken),
                    refreshToken == null
                            ? null
                            : TokenCredentials.checkRefreshCredential(refreshToken),
                    System.currentTimeMillis() + ttl * 1000);
        } catch (SdkUsageError error) {
            // Re-reported as an authentication failure: the caller wrote nothing
            // wrong, the token endpoint answered with something that cannot be
            // sent as a credential.
            throw new SdkAuthError(
                    "the token endpoint answered with a credential this SDK cannot send - "
                            + error.getMessage(),
                    new SdkError.Details()
                            .errorType("authentication")
                            .rawResponse(Redaction.redactValue(body)));
        }
    }

    /**
     * A refused grant that came back as a redirect, or as the page it leads to. The token
     * endpoint sends a request it will not honour to its sign-in page; the message carries
     * neither the response nor the Location, whose query can echo what was sent.
     */
    private static SdkAuthError refused(String answer, int status) {
        return new SdkAuthError(
                "the token request was refused: check client_id, client_secret and redirect_uri ("
                        + answer + ")",
                new SdkError.Details()
                        .statusCode(status)
                        .errorType("authentication"));
    }

    /** Whether the Location's path, its query never read, leads to the sign-in page. */
    private static boolean redirectsToSignIn(Transport.Exchange exchange) {
        String location = null;
        if (exchange.headers != null) {
            for (Map.Entry<String, String> header : exchange.headers.entrySet()) {
                if ("location".equalsIgnoreCase(header.getKey())) {
                    location = header.getValue();
                }
            }
        }
        if (location == null) {
            return false;
        }
        int query = location.indexOf('?');
        String path = (query < 0 ? location : location.substring(0, query)).toLowerCase(Locale.ROOT);
        return path.contains("/login");
    }

    /** A redirect the HTTP client followed lands on HTML; the token endpoint only answers JSON. */
    private static boolean answeredWithAPage(Transport.Exchange exchange) {
        if (exchange.decoded instanceof Map<?, ?>) {
            return false;
        }
        String contentType = exchange.contentType == null
                ? "" : exchange.contentType.toLowerCase(Locale.ROOT);
        return contentType.startsWith("text/html") || contentType.startsWith("application/xhtml");
    }

    private static String truncate(String text) {
        return text.length() <= 500 ? text : text.substring(0, 500);
    }
}
