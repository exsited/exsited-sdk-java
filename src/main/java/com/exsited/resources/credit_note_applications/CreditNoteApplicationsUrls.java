package com.exsited.resources.credit_note_applications;

/** Path templates and body keys for the credit note application endpoints. */
public final class CreditNoteApplicationsUrls {

    private CreditNoteApplicationsUrls() {}

    public static final String CREDIT_NOTE_APPLICATIONS = "/api/{api_version}/credit_note_applications";
    public static final String CREDIT_NOTE_APPLICATION = "/api/{api_version}/credit_note_applications/{credit_note_application_id}";
    public static final String INVOICE_CREDIT_NOTE_APPLICATIONS = "/api/{api_version}/invoices/{invoice_id}/credit_note_applications";

    /** The key a listing's records sit under. */
    public static final String CREDIT_NOTE_APPLICATIONS_KEY = "data";
    public static final String INVOICE_CREDIT_NOTE_APPLICATIONS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String CREDIT_NOTE_APPLICATION_KEY = "credit_note_application";
}
