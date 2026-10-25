package com.exsited.resources.timesheets;

/** Path templates and body keys for the timesheet endpoints. */
public final class TimesheetsUrls {

    private TimesheetsUrls() {}

    public static final String TIMESHEETS = "/api/{api_version}/timesheets";
    public static final String TIMESHEET = "/api/{api_version}/timesheets/{timesheet_id}";

    /** The key a listing's records sit under. */
    public static final String TIMESHEETS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String TIMESHEET_KEY = "timesheet";
}
