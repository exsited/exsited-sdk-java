package com.exsited.resources.day_of_month;

/** Path templates and body keys for the day of month endpoints. */
public final class DayOfMonthUrls {

    private DayOfMonthUrls() {}

    public static final String SETTINGS_DAYS_OF_MONTH = "/api/{api_version}/settings/day_of_month";
    public static final String SETTINGS_DAY_OF_MONTH = "/api/{api_version}/settings/day_of_month/{day_of_month_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_DAYS_OF_MONTH_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String DAY_OF_MONTH_KEY = "day_of_month";
}
