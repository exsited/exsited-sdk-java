package com.exsited.resources.custom_forms.custom_forms_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the custom form bodies. */
public final class CustomFormsDto {

    private CustomFormsDto() {}

    /** The settings custom form body, under {@code custom_form}. */
    public static final class SettingsCustomForm extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/custom_forms",
                "GET /api/{api_version}/settings/custom_forms/{custom_form_id}",
                "POST /api/{api_version}/settings/custom_forms",
                "PATCH /api/{api_version}/settings/custom_forms/{custom_form_id}");

        @Override
        protected String envelopeKey() {
            return "custom_form";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public Boolean for_default_template;
        public String href;
        public String id;
        public Boolean is_system_template;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
    }

    /** One page of {@code SettingsCustomForm} records, under {@code data}. */
    public static final class SettingsCustomFormList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/custom_forms");

        public List<SettingsCustomForm> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
