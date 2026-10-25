package com.exsited.resources.approval_user_groups.approval_user_groups_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the approval user group bodies. */
public final class ApprovalUserGroupsDto {

    private ApprovalUserGroupsDto() {}

    /** The settings approval user group body, under {@code approval_user_group}. */
    public static final class SettingsApprovalUserGroup extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/approval_user_groups",
                "GET /api/{api_version}/settings/approval_user_groups/{approval_user_group_id}",
                "POST /api/{api_version}/settings/approval_user_groups",
                "PATCH /api/{api_version}/settings/approval_user_groups/{approval_user_group_id}");

        @Override
        protected String envelopeKey() {
            return "approval_user_group";
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

    /** One page of {@code SettingsApprovalUserGroup} records, under {@code data}. */
    public static final class SettingsApprovalUserGroupList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/approval_user_groups");

        public List<SettingsApprovalUserGroup> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
