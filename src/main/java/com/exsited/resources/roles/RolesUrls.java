package com.exsited.resources.roles;

/** Path templates and body keys for the role endpoints. */
public final class RolesUrls {

    private RolesUrls() {}

    public static final String SETTINGS_ROLES = "/api/{api_version}/settings/roles";
    public static final String SETTINGS_ROLE = "/api/{api_version}/settings/roles/{role_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_ROLES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ROLE_KEY = "role";
}
