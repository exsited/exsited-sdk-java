package com.exsited.resources.system_events.system_events_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the system event bodies. */
public final class SystemEventsDto {

    private SystemEventsDto() {}

    /** The settings system event body, under {@code system_event}. */
    public static final class SettingsSystemEvent extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/system_events",
                "GET /api/{api_version}/settings/system_events/{system_event_id}");

        @Override
        protected String envelopeKey() {
            return "system_event";
        }

        public Boolean base_event;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object description;
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

    /** One page of {@code SettingsSystemEvent} records, under {@code data}. */
    public static final class SettingsSystemEventList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/system_events");

        public List<SettingsSystemEvent> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
