package com.exsited.resources.email_templates;

/** Path templates and body keys for the email template endpoints. */
public final class EmailTemplatesUrls {

    private EmailTemplatesUrls() {}

    public static final String SETTINGS_EMAIL_TEMPLATES = "/api/{api_version}/settings/email_templates";
    public static final String SETTINGS_EMAIL_TEMPLATE = "/api/{api_version}/settings/email_templates/{email_template_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_EMAIL_TEMPLATES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String EMAIL_TEMPLATE_KEY = "email_template";
}
