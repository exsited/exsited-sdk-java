package com.exsited.resources.purchase_payments;

/** Path templates and body keys for the purchase payment endpoints. */
public final class PurchasePaymentsUrls {

    private PurchasePaymentsUrls() {}

    public static final String PURCHASE_PAYMENTS = "/api/{api_version}/purchase_payments";
    public static final String PURCHASE_PAYMENT = "/api/{api_version}/purchase_payments/{purchase_payment_id}";
    public static final String PURCHASE_INVOICE_PURCHASE_PAYMENTS = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/purchase_payments";
    public static final String PURCHASE_PAYMENT_NOTES = "/api/{api_version}/purchase_payments/{purchase_payment_id}/notes";
    public static final String PURCHASE_PAYMENT_NOTE = "/api/{api_version}/purchase_payments/{purchase_payment_id}/notes/{pp_note_id}";
    public static final String PURCHASE_PAYMENT_NOTE_FILES = "/api/{api_version}/purchase_payments/{purchase_payment_id}/notes/{pp_note_id}/files";
    public static final String PURCHASE_PAYMENT_NOTE_FILE = "/api/{api_version}/purchase_payments/{purchase_payment_id}/notes/{pp_note_id}/files/{file_id}";
    public static final String PURCHASE_PAYMENT_PURCHASE_CREDIT_NOTES = "/api/{api_version}/purchase_payments/{purchase_payment_id}/purchase_credit_notes";

    /** The key a listing's records sit under. */
    public static final String PURCHASE_INVOICE_PURCHASE_PAYMENTS_KEY = "data";
    public static final String PURCHASE_PAYMENTS_KEY = "data";
    public static final String PURCHASE_PAYMENT_NOTES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String FILES_KEY = "files";
    public static final String FILE_KEY = "file";
    public static final String PURCHASE_PAYMENT_KEY = "purchase_payment";
    public static final String PURCHASE_PAYMENT_PURCHASE_CREDIT_NOTES_KEY = "data";
    public static final String NOTE_KEY = "note";
}
