package com.exsited.resources.custom_attributes;

/** Path templates and body keys for the custom attribute endpoints. */
public final class CustomAttributesUrls {

    private CustomAttributesUrls() {}

    public static final String CUSTOM_ATTRIBUTES = "/api/{api_version}/custom_attributes";
    public static final String CUSTOM_ATTRIBUTE = "/api/{api_version}/custom_attributes/{custom_attribute_id}";

    /** The key a listing's records sit under. */
    public static final String CUSTOM_ATTRIBUTES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String CUSTOM_ATTRIBUTE_KEY = "custom_attribute";
}
