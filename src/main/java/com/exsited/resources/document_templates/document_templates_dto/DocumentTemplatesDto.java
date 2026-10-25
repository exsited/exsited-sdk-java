package com.exsited.resources.document_templates.document_templates_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the document template bodies. */
public final class DocumentTemplatesDto {

    private DocumentTemplatesDto() {}

    /** The settings document template body, under {@code document_template}. */
    public static final class SettingsDocumentTemplate extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/document_templates",
                "GET /api/{api_version}/settings/document_templates/{document_template_id}",
                "POST /api/{api_version}/settings/document_templates",
                "PATCH /api/{api_version}/settings/document_templates/{document_template_id}");

        @Override
        protected String envelopeKey() {
            return "document_template";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public String href;
        public String id;
        public Boolean is_custom_template;
        public Boolean is_default;
        public Boolean is_edited;
        public Boolean is_system_template;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
        public String type;
        public Long version;
    }

    /** One page of {@code SettingsDocumentTemplate} records, under {@code data}. */
    public static final class SettingsDocumentTemplateList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/document_templates");

        public List<SettingsDocumentTemplate> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
