package com.exsited.resources.custom_features;

/** Path templates and body keys for the custom feature endpoints. */
public final class CustomFeaturesUrls {

    private CustomFeaturesUrls() {}

    public static final String CUSTOM_FEATURE = "/api/{api_version}/custom_feature";
    public static final String COMPONENT_BY_ID = "/api/{api_version}/component/{custom_feature_id}";
    public static final String CUSTOM_FEATURE_BY_ID = "/api/{api_version}/custom_feature/{custom_feature_id}";

    /** The envelope one record arrives under. */
    public static final String COMPONENT_KEY = "component";
    public static final String CUSTOM_FEATURE_LIST_KEY = "data";
    public static final String CUSTOM_FEATURE_KEY = "custom_feature";
}
