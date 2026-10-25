package com.exsited.resources.events;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.events.events_dto.EventsDto.Event;
import com.exsited.resources.events.events_dto.EventsDto.EventList;
import java.util.Map;

/** Event endpoints, under {@code /api/{api_version}/events}. */
public final class Events implements BoundResource {

    private final ExsitedClient client;

    public Events(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List all events: {@code GET /events}. */
    public ApiResponse<EventList> getEvents(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(EventsUrls.EVENTS,
                reopen(options).queryParams(queryParams).build(), EventList.class);
    }

    public ApiResponse<EventList> getEvents(
            Map<String, Object> queryParams) {
        return getEvents(queryParams, null);
    }

    public ApiResponse<EventList> getEvents() {
        return getEvents(null, null);
    }

    /** Get single event: {@code GET /events/{event_uuid}}. */
    public ApiResponse<Event> getEvent(
            String eventUuid, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(eventUuid, "event_uuid");
        return client.sendGetRequest(EventsUrls.EVENT,
                reopen(options).pathParam("event_uuid", eventUuid).queryParams(queryParams).build(), Event.class);
    }

    public ApiResponse<Event> getEvent(
            String eventUuid, Map<String, Object> queryParams) {
        return getEvent(eventUuid, queryParams, null);
    }

    public ApiResponse<Event> getEvent(String eventUuid) {
        return getEvent(eventUuid, null, null);
    }

    /**
     * Execute event: {@code POST /events/{event_uuid}/execute}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> createEventExecute(
            String eventUuid, Object payload, RequestOptions options) {
        requireValue(eventUuid, "event_uuid");
        requirePayload(payload);
        return client.sendPostRequest(EventsUrls.EVENT_EXECUTE,
                reopen(options).pathParam("event_uuid", eventUuid).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createEventExecute(
            String eventUuid, Object payload) {
        return createEventExecute(eventUuid, payload, null);
    }

    /**
     * Remove event: {@code POST /events/{event_uuid}/remove}.
     */
    public ApiResponse<Event> createEventRemove(
            String eventUuid, Object payload, RequestOptions options) {
        requireValue(eventUuid, "event_uuid");
        requirePayload(payload);
        return client.sendPostRequest(EventsUrls.EVENT_REMOVE,
                reopen(options).pathParam("event_uuid", eventUuid).jsonBody(payload).build(), Event.class);
    }

    public ApiResponse<Event> createEventRemove(
            String eventUuid, Object payload) {
        return createEventRemove(eventUuid, payload, null);
    }

    private static RequestOptions.Builder reopen(RequestOptions options) {
        RequestOptions given = options == null ? RequestOptions.none() : options;
        return RequestOptions.builder()
                .pathParams(given.getPathParams())
                .queryParams(given.getQueryParams())
                .jsonBody(given.getJsonBody())
                .formData(given.getFormData())
                .files(given.getFiles())
                .headers(given.getHeaders())
                .authenticated(given.isAuthenticated())
                .raiseOnError(given.getRaiseOnError())
                .responseFormat(given.getResponseFormat())
                .dtoFactory(given.getDtoFactory());
    }

    private static void requireValue(String value, String what) {
        if (value == null || value.isBlank()) {
            throw new SdkUsageError(
                    what + " is required: the request cannot be built without it");
        }
    }

    private static void requirePayload(Object payload) {
        if (payload == null) {
            throw new SdkUsageError("payload is required: this endpoint takes a body, "
                    + "and null would send the request with no body at all");
        }
    }
}
