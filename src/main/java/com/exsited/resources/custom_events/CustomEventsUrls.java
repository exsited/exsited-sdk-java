package com.exsited.resources.custom_events;

/** Path templates and body keys for the custom event endpoints. */
public final class CustomEventsUrls {

    private CustomEventsUrls() {}

    public static final String SETTINGS_CUSTOM_EVENTS = "/api/{api_version}/settings/custom_events";
    public static final String SETTINGS_CUSTOM_EVENT = "/api/{api_version}/settings/custom_events/{custom_event_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_CUSTOM_EVENTS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String CUSTOM_EVENT_KEY = "custom_event";
}
