package com.exsited.resources.receive_terms.receive_terms_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the receive term bodies. */
public final class ReceiveTermsDto {

    private ReceiveTermsDto() {}

    /** The settings receive term body, under {@code receive_term}. */
    public static final class SettingsReceiveTerm extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/receive_terms",
                "GET /api/{api_version}/settings/receive_terms/{receive_term_id}",
                "POST /api/{api_version}/settings/receive_terms",
                "PATCH /api/{api_version}/settings/receive_terms/{receive_term_id}");

        @Override
        protected String envelopeKey() {
            return "receive_term";
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

    /** One page of {@code SettingsReceiveTerm} records, under {@code data}. */
    public static final class SettingsReceiveTermList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/receive_terms");

        public List<SettingsReceiveTerm> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
