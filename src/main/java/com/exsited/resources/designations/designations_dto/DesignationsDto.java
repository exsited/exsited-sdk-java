package com.exsited.resources.designations.designations_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the designation bodies. */
public final class DesignationsDto {

    private DesignationsDto() {}

    /** The settings designation body, under {@code designation}. */
    public static final class SettingsDesignation extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/designations",
                "GET /api/{api_version}/settings/designations/{designation_id}");

        @Override
        protected String envelopeKey() {
            return "designation";
        }

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

    /** One page of {@code SettingsDesignation} records, under {@code data}. */
    public static final class SettingsDesignationList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/designations");

        public List<SettingsDesignation> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
