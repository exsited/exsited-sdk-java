package com.exsited.resources.operator_groups;

/** Path templates and body keys for the operator group endpoints. */
public final class OperatorGroupsUrls {

    private OperatorGroupsUrls() {}

    public static final String SETTINGS_OPERATOR_GROUPS = "/api/{api_version}/settings/operator_groups";
    public static final String SETTINGS_OPERATOR_GROUP =
            "/api/{api_version}/settings/operator_groups/{operator_group_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_OPERATOR_GROUPS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String OPERATOR_GROUP_KEY = "operator_group";
}
