package com.exsited.resources.custom_fields.custom_fields_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the custom field bodies. */
public final class CustomFieldsDto {

    private CustomFieldsDto() {}

    /** The settings custom field body, under {@code custom_field}. */
    public static final class SettingsCustomField extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/custom_fields",
                "GET /api/{api_version}/settings/custom_fields/{custom_field_id}");

        @Override
        protected String envelopeKey() {
            return "custom_field";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public String href;
        public String id;
        public Boolean is_mandatory;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
        public String type;
        public Long version;
    }

    /** One page of {@code SettingsCustomField} records, under {@code data}. */
    public static final class SettingsCustomFieldList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/custom_fields");

        public List<SettingsCustomField> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
