package com.exsited.resources.pricing_periods.pricing_periods_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the pricing period bodies. */
public final class PricingPeriodsDto {

    private PricingPeriodsDto() {}

    /** The settings pricing period body, under {@code pricing_period}. */
    public static final class SettingsPricingPeriod extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/pricing_periods",
                "GET /api/{api_version}/settings/pricing_periods/{pricing_period_id}",
                "POST /api/{api_version}/settings/pricing_periods",
                "PATCH /api/{api_version}/settings/pricing_periods/{pricing_period_id}");

        @Override
        protected String envelopeKey() {
            return "pricing_period";
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
        public SettingsPricingPeriodTimePeriod time_period;
    }

    /** One page of {@code SettingsPricingPeriod} records, under {@code data}. */
    public static final class SettingsPricingPeriodList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/pricing_periods");

        public List<SettingsPricingPeriod> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** A {@link SettingsPricingPeriod}'s {@code time_period}. */
    public static final class SettingsPricingPeriodTimePeriod extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }
}
