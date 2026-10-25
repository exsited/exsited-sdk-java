package com.exsited.resources.system_events;

/** Path templates and body keys for the system event endpoints. */
public final class SystemEventsUrls {

    private SystemEventsUrls() {}

    public static final String SETTINGS_SYSTEM_EVENTS = "/api/{api_version}/settings/system_events";
    public static final String SETTINGS_SYSTEM_EVENT = "/api/{api_version}/settings/system_events/{system_event_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_SYSTEM_EVENTS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String SYSTEM_EVENT_KEY = "system_event";
}
