package com.exsited.resources.settings_workflows.settings_workflows_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the workflow bodies. */
public final class SettingsWorkflowsDto {

    private SettingsWorkflowsDto() {}

    /** The settings workflow body, under {@code workflow}. */
    public static final class SettingsWorkflow extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/workflows",
                "GET /api/{api_version}/settings/workflows/{workflow_id}");

        @Override
        protected String envelopeKey() {
            return "workflow";
        }

        /** Null in every record measured, so untyped. */
        public Object code;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object description;
        public String display_name;
        public Boolean enable_stage;
        public String entity;
        public String event;
        public String href;
        public String id;
        public Boolean is_pre_built;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
    }

    /** One page of {@code SettingsWorkflow} records, under {@code data}. */
    public static final class SettingsWorkflowList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/workflows");

        public List<SettingsWorkflow> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
