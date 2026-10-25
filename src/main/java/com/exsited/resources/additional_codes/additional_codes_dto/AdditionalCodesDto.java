package com.exsited.resources.additional_codes.additional_codes_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the additional code bodies. */
public final class AdditionalCodesDto {

    private AdditionalCodesDto() {}

    /** The settings additional code body, under {@code additional_code}. */
    public static final class SettingsAdditionalCode extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/additional_codes",
                "GET /api/{api_version}/settings/additional_codes/{additional_code_id}",
                "POST /api/{api_version}/settings/additional_codes");

        @Override
        protected String envelopeKey() {
            return "additional_code";
        }

        public String description;
        public String display_name;
        public String href;
        public String id;
        public String name;
        public String object;
        public String status;
    }

    /** One page of {@code SettingsAdditionalCode} records, under {@code data}. */
    public static final class SettingsAdditionalCodeList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/additional_codes");

        public List<SettingsAdditionalCode> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
