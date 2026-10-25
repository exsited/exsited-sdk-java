package com.exsited.resources.communications;

import java.util.List;

/** Path templates and body keys for the communication endpoints. */
public final class CommunicationsUrls {

    private CommunicationsUrls() {}

    public static final String COMMUNICATIONS_SEND_EMAIL = "/api/{api_version}/communications/send_email";

    /** The one key the body names, which picks the document the email carries. */
    public static final List<String> SEND_EMAIL_DOCUMENT_KEYS = List.of(
            "invoice", "purchase_invoice", "account_statement", "payment");

    /** The envelope one record arrives under. */
    public static final String EMAIL_KEY = "email";
}
