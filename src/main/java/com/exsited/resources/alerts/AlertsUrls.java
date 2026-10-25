package com.exsited.resources.alerts;

/** Path templates and body keys for the alert endpoints. */
public final class AlertsUrls {

    private AlertsUrls() {}

    public static final String ALERTS = "/api/{api_version}/alerts";
    public static final String ALERT = "/api/{api_version}/alerts/{alert_id}";
    public static final String ALERTS_ACCOUNT_BY_ID = "/api/{api_version}/alerts/account/{account_id}";
    public static final String ALERTS_EMPLOYEE_BY_ID = "/api/{api_version}/alerts/employee/{employee_id}";
    public static final String ALERTS_LABOUR_BY_ID = "/api/{api_version}/alerts/labour/{labour_id}";

    /** The key a listing's records sit under. */
    public static final String ALERTS_ACCOUNT_BY_ID_KEY = "data";
    public static final String ALERTS_KEY = "data";
}
