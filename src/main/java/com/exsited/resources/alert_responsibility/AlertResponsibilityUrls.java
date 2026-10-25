package com.exsited.resources.alert_responsibility;

/** Path templates and body keys for the alert responsibility endpoints. */
public final class AlertResponsibilityUrls {

    private AlertResponsibilityUrls() {}

    /**
     * Singular on the wire, and served by the workflow services over the same records as
     * {@code /settings/alert_responsibilities}; the answers' own {@code href} values say
     * {@code /alert_responsibilities}, which v4 does not serve at the top level.
     */
    public static final String ALERT_RESPONSIBILITIES = "/api/{api_version}/alert_responsibility";
    public static final String ALERT_RESPONSIBILITY = "/api/{api_version}/alert_responsibility/{alert_responsibility_id}";

    /** The key a listing's records sit under. */
    public static final String ALERT_RESPONSIBILITIES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ALERT_RESPONSIBILITY_KEY = "alert_responsibility";
}
