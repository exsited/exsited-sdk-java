package com.exsited.resources.document_templates;

/** Path templates and body keys for the document template endpoints. */
public final class DocumentTemplatesUrls {

    private DocumentTemplatesUrls() {}

    public static final String SETTINGS_DOCUMENT_TEMPLATES = "/api/{api_version}/settings/document_templates";
    public static final String SETTINGS_DOCUMENT_TEMPLATE = "/api/{api_version}/settings/document_templates/{document_template_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_DOCUMENT_TEMPLATES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String DOCUMENT_TEMPLATE_KEY = "document_template";
}
