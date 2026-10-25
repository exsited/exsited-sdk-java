package com.exsited.resources.time_periods;

/** Path templates and body keys for the time period endpoints. */
public final class TimePeriodsUrls {

    private TimePeriodsUrls() {}

    public static final String SETTINGS_TIME_PERIODS = "/api/{api_version}/settings/time_periods";
    public static final String SETTINGS_TIME_PERIOD = "/api/{api_version}/settings/time_periods/{time_period_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_TIME_PERIODS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String TIME_PERIOD_KEY = "time_period";
}
