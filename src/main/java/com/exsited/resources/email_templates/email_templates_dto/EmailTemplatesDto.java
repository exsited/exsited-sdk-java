package com.exsited.resources.email_templates.email_templates_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the email template bodies. */
public final class EmailTemplatesDto {

    private EmailTemplatesDto() {}

    /** The settings email template body, under {@code email_template}. */
    public static final class SettingsEmailTemplate extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/email_templates",
                "GET /api/{api_version}/settings/email_templates/{email_template_id}",
                "POST /api/{api_version}/settings/email_templates",
                "PATCH /api/{api_version}/settings/email_templates/{email_template_id}");

        @Override
        protected String envelopeKey() {
            return "email_template";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        /** Null in every record measured, so untyped. */
        public Object entity;
        public String href;
        public String id;
        public Boolean is_edited;
        public Boolean is_system_template;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object reply_address;
        public Boolean rich_editor;
        /** Null in every record measured, so untyped. */
        public Object sender_address;
        /** Null in every record measured, so untyped. */
        public Object sender_name;
        public String status;
        public String subject;
        public Boolean track_email_open;
        public Boolean track_link_click;
    }

    /** One page of {@code SettingsEmailTemplate} records, under {@code data}. */
    public static final class SettingsEmailTemplateList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/email_templates");

        public List<SettingsEmailTemplate> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
