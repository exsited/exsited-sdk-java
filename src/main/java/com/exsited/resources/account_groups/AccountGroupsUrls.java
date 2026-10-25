package com.exsited.resources.account_groups;

/** Path templates and body keys for the account group endpoints. */
public final class AccountGroupsUrls {

    private AccountGroupsUrls() {}

    public static final String SETTINGS_ACCOUNT_GROUPS = "/api/{api_version}/settings/account_groups";
    public static final String SETTINGS_ACCOUNT_GROUP = "/api/{api_version}/settings/account_groups/{account_group_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_ACCOUNT_GROUPS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ACCOUNT_GROUP_KEY = "account_group";
}
