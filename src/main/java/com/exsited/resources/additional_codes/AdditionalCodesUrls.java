package com.exsited.resources.additional_codes;

/** Path templates and body keys for the additional code endpoints. */
public final class AdditionalCodesUrls {

    private AdditionalCodesUrls() {}

    public static final String SETTINGS_ADDITIONAL_CODES = "/api/{api_version}/settings/additional_codes";
    public static final String SETTINGS_ADDITIONAL_CODE = "/api/{api_version}/settings/additional_codes/{additional_code_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_ADDITIONAL_CODES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ADDITIONAL_CODE_KEY = "additional_code";
}
