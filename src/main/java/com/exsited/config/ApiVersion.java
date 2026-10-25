package com.exsited.config;

/**
 * The API version this SDK speaks.
 *
 * <p>One constant: a version this SDK had no resources for would answer 404 on every path.
 * An enum, so a future version is an addition rather than a redesign.
 */
public enum ApiVersion {
    /** v4. Authenticates at {@code /api/v4/oauth2/token}. */
    V4("v4", "v4");

    private final String pathSegment;
    private final String tokenPathSegment;

    ApiVersion(String pathSegment, String tokenPathSegment) {
        this.pathSegment = pathSegment;
        this.tokenPathSegment = tokenPathSegment;
    }

    /** What {@code {api_version}} renders as. */
    public String pathSegment() {
        return pathSegment;
    }

    /** The token endpoint's path. */
    public String tokenPath() {
        return "/api/" + tokenPathSegment + "/oauth2/token";
    }
}
