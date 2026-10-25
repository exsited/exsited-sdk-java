package com.exsited.resources.communication_profiles;

/** Path templates and body keys for the communication profile endpoints. */
public final class CommunicationProfilesUrls {

    private CommunicationProfilesUrls() {}

    public static final String SETTINGS_COMMUNICATION_PROFILES = "/api/{api_version}/settings/communication_profiles";
    public static final String SETTINGS_COMMUNICATION_PROFILE = "/api/{api_version}/settings/communication_profiles/{communication_profile_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_COMMUNICATION_PROFILES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String COMMUNICATION_PROFILE_KEY = "communication_profile";
}
