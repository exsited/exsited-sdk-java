package com.exsited.resources.approval_user_groups;

/** Path templates and body keys for the approval user group endpoints. */
public final class ApprovalUserGroupsUrls {

    private ApprovalUserGroupsUrls() {}

    public static final String SETTINGS_APPROVAL_USER_GROUPS = "/api/{api_version}/settings/approval_user_groups";
    public static final String SETTINGS_APPROVAL_USER_GROUP = "/api/{api_version}/settings/approval_user_groups/{approval_user_group_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_APPROVAL_USER_GROUPS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String APPROVAL_USER_GROUP_KEY = "approval_user_group";
}
