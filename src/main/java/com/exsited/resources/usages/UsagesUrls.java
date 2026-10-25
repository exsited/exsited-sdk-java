package com.exsited.resources.usages;

/** Path templates and body keys for the usage endpoints. */
public final class UsagesUrls {

    private UsagesUrls() {}

    public static final String USAGES = "/api/{api_version}/usages";
    public static final String USAGE = "/api/{api_version}/usages/{usage_id}";

    /** The key a listing's records sit under. */
    public static final String USAGES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String USAGE_KEY = "usage";
}
