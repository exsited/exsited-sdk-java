package com.exsited.resources.fulfillment_terms.fulfillment_terms_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the fulfillment term bodies. */
public final class FulfillmentTermsDto {

    private FulfillmentTermsDto() {}

    /** The settings fulfillment term body, under {@code fulfillment_term}. */
    public static final class SettingsFulfillmentTerm extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/fulfillment_terms",
                "GET /api/{api_version}/settings/fulfillment_terms/{fulfillment_term_id}",
                "POST /api/{api_version}/settings/fulfillment_terms",
                "PATCH /api/{api_version}/settings/fulfillment_terms/{fulfillment_term_id}");

        @Override
        protected String envelopeKey() {
            return "fulfillment_term";
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

    /** One page of {@code SettingsFulfillmentTerm} records, under {@code data}. */
    public static final class SettingsFulfillmentTermList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/fulfillment_terms");

        public List<SettingsFulfillmentTerm> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
