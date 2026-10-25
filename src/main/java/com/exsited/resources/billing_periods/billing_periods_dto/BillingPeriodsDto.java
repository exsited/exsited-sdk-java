package com.exsited.resources.billing_periods.billing_periods_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the billing period bodies. */
public final class BillingPeriodsDto {

    private BillingPeriodsDto() {}

    /** The settings billing period body, under {@code billing_period}. */
    public static final class SettingsBillingPeriod extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/billing_periods",
                "GET /api/{api_version}/settings/billing_periods/{billing_period_id}",
                "POST /api/{api_version}/settings/billing_periods",
                "PATCH /api/{api_version}/settings/billing_periods/{billing_period_id}");

        @Override
        protected String envelopeKey() {
            return "billing_period";
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
        public Long ordering;
        public String period;
        public String status;
        public String time_period;
    }

    /** One page of {@code SettingsBillingPeriod} records, under {@code data}. */
    public static final class SettingsBillingPeriodList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/billing_periods");

        public List<SettingsBillingPeriod> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
