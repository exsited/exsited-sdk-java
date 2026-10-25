package com.exsited.resources.time_periods.time_periods_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the time period bodies. */
public final class TimePeriodsDto {

    private TimePeriodsDto() {}

    /** The settings time period body, under {@code time_period}. */
    public static final class SettingsTimePeriod extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/time_periods",
                "GET /api/{api_version}/settings/time_periods/{time_period_id}",
                "POST /api/{api_version}/settings/time_periods",
                "PATCH /api/{api_version}/settings/time_periods/{time_period_id}");

        @Override
        protected String envelopeKey() {
            return "time_period";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public Long duration;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String period;
        public String status;
    }

    /** One page of {@code SettingsTimePeriod} records, under {@code data}. */
    public static final class SettingsTimePeriodList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/time_periods");

        public List<SettingsTimePeriod> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
