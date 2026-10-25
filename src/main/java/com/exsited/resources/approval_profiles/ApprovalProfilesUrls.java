package com.exsited.resources.approval_profiles;

/** Path templates and body keys for the approval profile endpoints. */
public final class ApprovalProfilesUrls {

    private ApprovalProfilesUrls() {}

    public static final String SETTINGS_APPROVAL_PROFILES = "/api/{api_version}/settings/approval_profiles";
    public static final String SETTINGS_APPROVAL_PROFILE = "/api/{api_version}/settings/approval_profiles/{approval_profile_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_APPROVAL_PROFILES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String APPROVAL_PROFILE_KEY = "approval_profile";
}
