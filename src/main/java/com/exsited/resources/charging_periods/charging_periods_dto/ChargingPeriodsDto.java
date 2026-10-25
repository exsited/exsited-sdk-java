package com.exsited.resources.charging_periods.charging_periods_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the charging period bodies. */
public final class ChargingPeriodsDto {

    private ChargingPeriodsDto() {}

    /** The settings charging period body, under {@code charging_period}. */
    public static final class SettingsChargingPeriod extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/charging_periods",
                "GET /api/{api_version}/settings/charging_periods/{charging_period_id}",
                "POST /api/{api_version}/settings/charging_periods",
                "PATCH /api/{api_version}/settings/charging_periods/{charging_period_id}");

        @Override
        protected String envelopeKey() {
            return "charging_period";
        }

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
        public SettingsChargingPeriodTimePeriod time_period;
    }

    /** One page of {@code SettingsChargingPeriod} records, under {@code data}. */
    public static final class SettingsChargingPeriodList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/charging_periods");

        public List<SettingsChargingPeriod> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** A {@link SettingsChargingPeriod}'s {@code time_period}. */
    public static final class SettingsChargingPeriodTimePeriod extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }
}
