package com.exsited.resources.day_of_month.day_of_month_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the day of month bodies. */
public final class DayOfMonthDto {

    private DayOfMonthDto() {}

    /** The settings day of month body, under {@code day_of_month}. */
    public static final class SettingsDayOfMonth extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/day_of_month",
                "GET /api/{api_version}/settings/day_of_month/{day_of_month_id}");

        @Override
        protected String envelopeKey() {
            return "day_of_month";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public Long day;
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

    /** One page of {@code SettingsDayOfMonth} records, under {@code data}. */
    public static final class SettingsDayOfMonthList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/day_of_month");

        public List<SettingsDayOfMonth> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
