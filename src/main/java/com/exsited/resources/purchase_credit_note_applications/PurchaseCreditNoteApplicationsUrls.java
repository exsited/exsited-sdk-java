package com.exsited.resources.purchase_credit_note_applications;

/** Path templates and body keys for the purchase credit note application endpoints. */
public final class PurchaseCreditNoteApplicationsUrls {

    private PurchaseCreditNoteApplicationsUrls() {}

    public static final String PURCHASE_CREDIT_NOTE_APPLICATIONS = "/api/{api_version}/purchase_credit_note_applications";
    public static final String PURCHASE_CREDIT_NOTE_APPLICATION = "/api/{api_version}/purchase_credit_note_applications/{purchase_credit_note_application_id}";

    /** The key a listing's records sit under. */
    public static final String PURCHASE_CREDIT_NOTE_APPLICATIONS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String PURCHASE_CREDIT_NOTE_APPLICATION_KEY = "purchase_credit_note_application";
}
