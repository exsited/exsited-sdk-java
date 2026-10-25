package com.exsited.config;

import com.exsited.Redaction;
import com.exsited.errors.SdkConfigError;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Who you are and which instance you are talking to.
 *
 * <p>Only the credentials and where the resulting token is kept; how the SDK behaves is
 * {@link SdkConfig}. The four credentials are the four the token endpoint takes, which has no
 * {@code authString}. Immutable, and {@code toString} masks the secret, so an instance is safe
 * to log.
 */
public final class AuthConfig {

    public static final String DEFAULT_CLIENT_NAME = "default";
    public static final String DEFAULT_GRANT_TYPE = "client_credentials";

    private final String clientName;
    private final String baseUrl;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String grantType;
    private final String tokenCachePath;

    private AuthConfig(Builder builder) {
        List<String> problems = new ArrayList<>();
        requireText(problems, "clientName", builder.clientName);
        requireText(problems, "baseUrl", builder.baseUrl);
        requireText(problems, "clientId", builder.clientId);
        requireText(problems, "clientSecret", builder.clientSecret);
        requireText(problems, "redirectUri", builder.redirectUri);

        String normalisedBase = builder.baseUrl == null ? "" : normaliseBaseUrl(builder.baseUrl, problems);
        String resolvedGrant = builder.grantType == null ? DEFAULT_GRANT_TYPE : builder.grantType;
        if (resolvedGrant.isBlank()) {
            problems.add("grantType: must not be blank");
        }
        if (builder.tokenCachePath != null && builder.tokenCachePath.isBlank()) {
            // A blank string is how an unset environment variable arrives, and
            // treating it as "in memory" would silently stop sharing the token.
            problems.add("tokenCachePath: must not be blank - omit it for the platform "
                    + "default, or call inMemoryToken() to keep it in memory");
        }

        if (!problems.isEmpty()) {
            String label = builder.clientName == null || builder.clientName.isBlank()
                    ? "<unnamed>" : builder.clientName;
            throw new SdkConfigError("instance " + label + ": " + String.join("; ", problems));
        }

        this.clientName = builder.clientName;
        this.baseUrl = normalisedBase;
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
        this.redirectUri = builder.redirectUri;
        this.grantType = resolvedGrant;
        this.tokenCachePath = builder.tokenCachePath;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String clientName() { return clientName; }
    public String baseUrl() { return baseUrl; }
    public String clientId() { return clientId; }
    public String clientSecret() { return clientSecret; }
    public String redirectUri() { return redirectUri; }
    public String grantType() { return grantType; }

    /** Where the token is cached, or null to keep it in memory only. */
    public String tokenCachePath() { return tokenCachePath; }

    /** Whether the token is kept in memory rather than a file. */
    public boolean inMemoryToken() { return tokenCachePath == null; }

    @Override
    public String toString() {
        return "AuthConfig(clientName=" + clientName
                + ", baseUrl=" + baseUrl
                + ", clientId=" + clientId
                + ", clientSecret=" + Redaction.REDACTED
                + ", redirectUri=" + redirectUri
                + ", grantType=" + grantType + ")";
    }

    private static void requireText(List<String> problems, String name, String value) {
        if (value == null || value.isBlank()) {
            problems.add(name + ": must not be blank");
        }
    }

    private static String normaliseBaseUrl(String raw, List<String> problems) {
        String trimmed = raw.strip().replaceAll("/+$", "");
        if (trimmed.isEmpty()) {
            return "";
        }
        try {
            URI parsed = new URI(trimmed);
            String scheme = parsed.getScheme();
            if (scheme == null || !(scheme.equals("http") || scheme.equals("https"))) {
                problems.add("baseUrl: needs an http:// or https:// scheme");
            }
            // http:// is accepted: a local instance is the ordinary reason for it. The
            // cleartext risk against a remote host is warned about once per host by
            // ExsitedClient.warnAboutPlaintextTransport, not here, because validating a
            // config must have no side effect.
            if (parsed.getUserInfo() != null) {
                // An HTTP client turns userinfo into a Basic header that replaces
                // the bearer token.
                problems.add("baseUrl: must not carry user:password@ credentials");
            }
            if (parsed.getQuery() != null || parsed.getFragment() != null) {
                problems.add("baseUrl: must not carry a query string or fragment");
            }
            checkPort(parsed, problems);
            // A path is allowed: an instance can be mounted under a sub-path.
        } catch (URISyntaxException error) {
            problems.add("baseUrl: not a URL: " + trimmed);
        }
        return trimmed;
    }

    /**
     * The port, when there is one, must be a number in 1 to 65535.
     *
     * <p>Read out of the raw authority rather than from {@link URI#getPort()}: given
     * {@code https://host:abcd}, {@code URI} falls back to a registry-based authority and
     * {@code getPort()} answers -1, so the typo would surface only as a connect failure.
     */
    private static void checkPort(URI parsed, List<String> problems) {
        String authority = parsed.getRawAuthority();
        if (authority == null) {
            return;
        }
        String hostAndPort = authority.substring(authority.indexOf('@') + 1);
        int colon = hostAndPort.lastIndexOf(':');
        // A colon inside an IPv6 literal is part of the host, and those literals
        // are bracketed - so only a colon after the closing bracket is a port.
        if (colon < 0 || colon < hostAndPort.lastIndexOf(']')) {
            return;
        }
        String port = hostAndPort.substring(colon + 1);
        if (port.isEmpty()) {
            // An empty port means the scheme's default (RFC 3986).
            return;
        }
        for (int index = 0; index < port.length(); index++) {
            char digit = port.charAt(index);
            if (digit < '0' || digit > '9') {
                problems.add("baseUrl: port \"" + port + "\" is not a number");
                return;
            }
        }
        // Compared as text, because a run of digits long enough to be a typo is
        // also long enough to overflow the parse that would have caught it.
        String withoutLeadingZeroes = port.replaceFirst("^0+(?=.)", "");
        if (withoutLeadingZeroes.equals("0")
                || withoutLeadingZeroes.length() > 5
                || (withoutLeadingZeroes.length() == 5
                        && withoutLeadingZeroes.compareTo("65535") > 0)) {
            problems.add("baseUrl: port " + port + " is outside 1-65535");
        }
    }

    /** Builds an {@link AuthConfig}; every problem is reported at once. */
    public static final class Builder {
        private String clientName = DEFAULT_CLIENT_NAME;
        private String baseUrl;
        private String clientId;
        private String clientSecret;
        private String redirectUri;
        private String grantType;
        private String tokenCachePath = "";

        private Builder() {}

        public Builder clientName(String value) { this.clientName = value; return this; }
        public Builder baseUrl(String value) { this.baseUrl = value; return this; }
        public Builder clientId(String value) { this.clientId = value; return this; }
        public Builder clientSecret(String value) { this.clientSecret = value; return this; }
        public Builder redirectUri(String value) { this.redirectUri = value; return this; }
        public Builder grantType(String value) { this.grantType = value; return this; }

        /** Cache the token to this file. */
        public Builder tokenCachePath(String value) { this.tokenCachePath = value; return this; }

        /** Keep the token in memory for the life of the process. */
        public Builder inMemoryToken() { this.tokenCachePath = null; return this; }

        public AuthConfig build() {
            if ("".equals(tokenCachePath)) {
                tokenCachePath = com.exsited.auth.FileTokenStore.defaultCachePath();
            }
            return new AuthConfig(this);
        }
    }
}
