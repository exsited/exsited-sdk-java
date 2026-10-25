package com.exsited.resources.fulfillment_profiles;

/** Path templates and body keys for the fulfillment profile endpoints. */
public final class FulfillmentProfilesUrls {

    private FulfillmentProfilesUrls() {}

    public static final String SETTINGS_FULFILLMENT_PROFILES = "/api/{api_version}/settings/fulfillment_profiles";
    public static final String SETTINGS_FULFILLMENT_PROFILE = "/api/{api_version}/settings/fulfillment_profiles/{fulfillment_profile_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_FULFILLMENT_PROFILES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String FULFILLMENT_PROFILE_KEY = "fulfillment_profile";
}
