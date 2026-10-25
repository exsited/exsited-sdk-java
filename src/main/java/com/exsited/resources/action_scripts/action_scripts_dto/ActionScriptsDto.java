package com.exsited.resources.action_scripts.action_scripts_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the action script bodies. */
public final class ActionScriptsDto {

    private ActionScriptsDto() {}

    /**
     * The action script body, under {@code action_script}; {@code activity_logs} comes on the
     * create answer only.
     */
    public static final class ActionScript extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/action_scripts",
                "GET /api/{api_version}/action_scripts/{action_script_id}",
                "POST /api/{api_version}/action_scripts",
                "PATCH /api/{api_version}/action_scripts/{action_script_id}");

        @Override
        protected String envelopeKey() {
            return "action_script";
        }

        /** Null in every record measured, so untyped. */
        public Object action_name;
        public List<Object> activity_logs;
        public ActionScriptCreatedBy created_by;
        public String created_on;
        public String description;
        public String display_name;
        public String href;
        public String id;
        public ActionScriptLastUpdatedBy last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        public String name;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object object_name;
        public String status;
    }

    /** One page of {@code ActionScript} records, under {@code data}. */
    public static final class ActionScriptList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/action_scripts");

        public List<ActionScript> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** An {@link ActionScript}'s {@code created_by}. */
    public static final class ActionScriptCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** An {@link ActionScript}'s {@code last_updated_by}, set once the script has been updated. */
    public static final class ActionScriptLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }
}
