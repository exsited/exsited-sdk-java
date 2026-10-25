package com.exsited.resources.labours;

/** Path templates and body keys for the labour endpoints. */
public final class LaboursUrls {

    private LaboursUrls() {}

    public static final String LABOUR_AVAILABILITY = "/api/{api_version}/labour_availability";
    public static final String LABOUR_PROFILES = "/api/{api_version}/labour_profiles";
    public static final String LABOURS = "/api/{api_version}/labours";
    public static final String LABOUR_PROFILE = "/api/{api_version}/labour_profiles/{labour_profile_id}";
    public static final String LABOURS_AVAILABLE_FOR_BOOKING = "/api/{api_version}/labours/available_for_booking";
    public static final String LABOUR = "/api/{api_version}/labours/{labour_uuid}";
    public static final String LABOUR_CANCEL = "/api/{api_version}/labours/{labour_id}/cancel";
    public static final String LABOUR_REACTIVATE = "/api/{api_version}/labours/{labour_id}/reactivate";
    public static final String LABOUR_ORDERS = "/api/{api_version}/labours/{labour_uuid}/orders";
    public static final String LABOUR_PROFILE_SESSIONS = "/api/{api_version}/labour_profiles/{labour_profile_id}/sessions";
    public static final String LABOUR_PROFILE_SESSION = "/api/{api_version}/labour_profiles/{labour_profile_id}/sessions/{session_id}";
    public static final String LABOUR_CALENDAR = "/api/{api_version}/labours/{labour_id}/availability";

    /** The key a listing's records sit under. */
    public static final String LABOURS_AVAILABLE_FOR_BOOKING_KEY = "data";
    public static final String LABOURS_KEY = "data";
    public static final String LABOUR_AVAILABILITY_KEY = "data";
    public static final String LABOUR_PROFILES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String LABOUR_KEY = "labour";
    public static final String LABOUR_PROFILE_KEY = "labour_profile";
    public static final String LABOUR_PROFILE_SESSIONS_KEY = "data";
    public static final String LABOUR_CALENDAR_KEY = "labour_calendar";
    public static final String LABOUR_PROFILE_SESSION_KEY = "labour_profile_session";
}
