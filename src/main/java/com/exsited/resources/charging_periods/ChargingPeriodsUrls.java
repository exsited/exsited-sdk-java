package com.exsited.resources.charging_periods;

/** Path templates and body keys for the charging period endpoints. */
public final class ChargingPeriodsUrls {

    private ChargingPeriodsUrls() {}

    public static final String SETTINGS_CHARGING_PERIODS = "/api/{api_version}/settings/charging_periods";
    public static final String SETTINGS_CHARGING_PERIOD = "/api/{api_version}/settings/charging_periods/{charging_period_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_CHARGING_PERIODS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String CHARGING_PERIOD_KEY = "charging_period";
}
