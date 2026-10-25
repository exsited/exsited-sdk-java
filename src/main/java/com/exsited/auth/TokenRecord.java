package com.exsited.auth;

/**
 * A minted token and when it stops being usable.
 *
 * <p>Every route into a record (a caller, a decoded grant, a cache entry) meets here, so this
 * is where both credentials are checked; see {@link TokenCredentials}.
 */
public final class TokenRecord {
    private final String accessToken;
    private final String refreshToken;
    /** Absolute epoch milliseconds. */
    private final long expiresAt;

    /**
     * @throws com.exsited.errors.SdkUsageError either credential is not text, or is not its
     *     own grammar
     */
    public TokenRecord(String accessToken, String refreshToken, long expiresAt) {
        this.accessToken = TokenCredentials.checkAccessCredential(accessToken);
        this.refreshToken = refreshToken == null
                ? null
                : TokenCredentials.checkRefreshCredential(refreshToken);
        this.expiresAt = expiresAt;
    }

    public String accessToken() { return accessToken; }
    public String refreshToken() { return refreshToken; }
    public long expiresAt() { return expiresAt; }

    /** Whether this needs replacing: before expiry, so a token never expires mid-flight. */
    public boolean needsRefresh(long skewMillis) {
        return System.currentTimeMillis() >= expiresAt - skewMillis;
    }
}
