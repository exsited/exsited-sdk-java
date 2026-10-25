package com.exsited.resources.attribute_attachments;

/** Path templates and body keys for the attribute attachment endpoints. */
public final class AttributeAttachmentsUrls {

    private AttributeAttachmentsUrls() {}

    /** Singular on the wire; the account views link a file attribute here. */
    public static final String ATTRIBUTE_ATTACHMENT = "/api/{api_version}/attribute_attachment/{file_uuid}";
}
