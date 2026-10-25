package com.exsited.resources.sales_reps.sales_reps_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the sales rep bodies. */
public final class SalesRepsDto {

    private SalesRepsDto() {}

    /** The settings sales rep body, under {@code sales_rep}. */
    public static final class SettingsSalesRep extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/sales_reps",
                "GET /api/{api_version}/settings/sales_reps/{sales_rep_id}");

        @Override
        protected String envelopeKey() {
            return "sales_rep";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String email_address;
        public String first_name;
        public String href;
        public String id;
        public String last_name;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
        public Long version;
    }

    /** One page of {@code SettingsSalesRep} records, under {@code data}. */
    public static final class SettingsSalesRepList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/sales_reps");

        public List<SettingsSalesRep> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
