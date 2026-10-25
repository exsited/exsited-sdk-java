package com.exsited.resources.designations;

/** Path templates and body keys for the designation endpoints. */
public final class DesignationsUrls {

    private DesignationsUrls() {}

    public static final String SETTINGS_DESIGNATIONS = "/api/{api_version}/settings/designations";
    public static final String SETTINGS_DESIGNATION = "/api/{api_version}/settings/designations/{designation_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_DESIGNATIONS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String DESIGNATION_KEY = "designation";
}
