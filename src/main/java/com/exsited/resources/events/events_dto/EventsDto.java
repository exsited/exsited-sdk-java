package com.exsited.resources.events.events_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the events bodies. */
public final class EventsDto {

    /** The event body. */
    public static final class Event extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/events",
                "GET /api/{api_version}/events/{event_uuid}",
                "POST /api/{api_version}/events/{event_uuid}/remove");

        @Override
        protected String envelopeKey() {
            return "event";
        }

        public String created_on;
        public String event_name;
        public String executed_on;
        public String href;
        public String id;
        public String object;
        public String resource_type;
        public String resource_uuid;
        public String scheduled_to_execute_on;
        public String status;
    }

    /** A page of Event records. */
    public static final class EventList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/events");

        public List<Event> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    private EventsDto() {
    }
}
