package com.exsited.resources.applications;

/** Path templates and body keys for the application endpoints. */
public final class ApplicationsUrls {

    private ApplicationsUrls() {}

    public static final String SETTINGS_APPLICATIONS = "/api/{api_version}/settings/applications";
    public static final String SETTINGS_APPLICATION = "/api/{api_version}/settings/applications/{application_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_APPLICATIONS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String APPLICATION_KEY = "application";
}
