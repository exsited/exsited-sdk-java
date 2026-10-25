package com.exsited.resources.salutations.salutations_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the salutation bodies. */
public final class SalutationsDto {

    private SalutationsDto() {}

    /** The settings salutation body, under {@code salutation}. */
    public static final class SettingsSalutation extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/salutations",
                "GET /api/{api_version}/settings/salutations/{salutation_id}",
                "POST /api/{api_version}/settings/salutations",
                "PATCH /api/{api_version}/settings/salutations/{salutation_id}");

        @Override
        protected String envelopeKey() {
            return "salutation";
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
    }

    /** One page of {@code SettingsSalutation} records, under {@code data}. */
    public static final class SettingsSalutationList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/salutations");

        public List<SettingsSalutation> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
