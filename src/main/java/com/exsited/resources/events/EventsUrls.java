package com.exsited.resources.events;

/** Path templates and body keys for the event endpoints. */
public final class EventsUrls {

    private EventsUrls() {}

    public static final String EVENTS = "/api/{api_version}/events";
    public static final String EVENT = "/api/{api_version}/events/{event_uuid}";
    public static final String EVENT_EXECUTE = "/api/{api_version}/events/{event_uuid}/execute";
    public static final String EVENT_REMOVE = "/api/{api_version}/events/{event_uuid}/remove";

    /** The key a listing's records sit under. */
    public static final String EVENTS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String EVENT_KEY = "event";
}
