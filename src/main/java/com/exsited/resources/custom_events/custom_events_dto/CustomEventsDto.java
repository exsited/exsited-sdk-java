package com.exsited.resources.custom_events.custom_events_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the custom event bodies. */
public final class CustomEventsDto {

    private CustomEventsDto() {}

    /** The settings custom event body, under {@code custom_event}. */
    public static final class SettingsCustomEvent extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/custom_events",
                "GET /api/{api_version}/settings/custom_events/{custom_event_id}");

        @Override
        protected String envelopeKey() {
            return "custom_event";
        }

        public Boolean base_event;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
    }

    /** One page of {@code SettingsCustomEvent} records, under {@code data}. */
    public static final class SettingsCustomEventList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/custom_events");

        public List<SettingsCustomEvent> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
