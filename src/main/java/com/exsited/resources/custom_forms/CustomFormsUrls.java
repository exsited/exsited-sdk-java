package com.exsited.resources.custom_forms;

/** Path templates and body keys for the custom form endpoints. */
public final class CustomFormsUrls {

    private CustomFormsUrls() {}

    public static final String SETTINGS_CUSTOM_FORMS = "/api/{api_version}/settings/custom_forms";
    public static final String SETTINGS_CUSTOM_FORM = "/api/{api_version}/settings/custom_forms/{custom_form_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_CUSTOM_FORMS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String CUSTOM_FORM_KEY = "custom_form";
}
