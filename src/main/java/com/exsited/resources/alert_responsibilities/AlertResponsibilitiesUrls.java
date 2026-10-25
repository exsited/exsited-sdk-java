package com.exsited.resources.alert_responsibilities;

/** Path templates and body keys for the alert responsibility endpoints. */
public final class AlertResponsibilitiesUrls {

    private AlertResponsibilitiesUrls() {}

    public static final String SETTINGS_ALERT_RESPONSIBILITIES = "/api/{api_version}/settings/alert_responsibilities";
    public static final String SETTINGS_ALERT_RESPONSIBILITY = "/api/{api_version}/settings/alert_responsibilities/{alert_responsibility_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_ALERT_RESPONSIBILITIES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ALERT_RESPONSIBILITY_KEY = "alert_responsibility";
}
