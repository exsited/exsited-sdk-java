package com.exsited.resources.events;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.events.events_dto.EventsDto.Event;
import com.exsited.resources.events.events_dto.EventsDto.EventList;
import java.util.Map;

/**
 * Forwards every call to {@link Events}.
 *
 * @deprecated {@link Events} is bound to a client itself and is what
 *     {@code client.events()} answers; use it instead.
 */
@Deprecated
public final class EventsResource implements BoundResource {

    private final Events events;

    public EventsResource(ExsitedClient client) {
        this.events = new Events(client);
    }

    @Override
    public ExsitedClient getClient() {
        return events.getClient();
    }

    public ApiResponse<EventList> getEvents(
            Map<String, Object> queryParams, RequestOptions options) {
        return events.getEvents(queryParams, options);
    }

    public ApiResponse<EventList> getEvents(Map<String, Object> queryParams) {
        return events.getEvents(queryParams);
    }

    public ApiResponse<Event> getEvent(
            String eventUuid, Map<String, Object> queryParams, RequestOptions options) {
        return events.getEvent(eventUuid, queryParams, options);
    }

    public ApiResponse<Event> getEvent(String eventUuid, Map<String, Object> queryParams) {
        return events.getEvent(eventUuid, queryParams);
    }

    public ApiResponse<Dto> createEventExecute(
            String eventUuid, Object payload, RequestOptions options) {
        return events.createEventExecute(eventUuid, payload, options);
    }

    public ApiResponse<Dto> createEventExecute(String eventUuid, Object payload) {
        return events.createEventExecute(eventUuid, payload);
    }

    public ApiResponse<Event> createEventRemove(
            String eventUuid, Object payload, RequestOptions options) {
        return events.createEventRemove(eventUuid, payload, options);
    }

    public ApiResponse<Event> createEventRemove(String eventUuid, Object payload) {
        return events.createEventRemove(eventUuid, payload);
    }
}
