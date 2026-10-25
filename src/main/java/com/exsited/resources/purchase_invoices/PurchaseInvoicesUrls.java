package com.exsited.resources.purchase_invoices;

/** Path templates and body keys for the purchase invoice endpoints. */
public final class PurchaseInvoicesUrls {

    private PurchaseInvoicesUrls() {}

    public static final String PURCHASE_INVOICES = "/api/{api_version}/purchase_invoices";
    public static final String PURCHASE_INVOICES_NOTES = "/api/{api_version}/purchase_invoices/notes";
    public static final String PURCHASE_INVOICE = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}";
    public static final String PURCHASE_INVOICE_AMEND = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/amend";
    public static final String PURCHASE_INVOICE_CANCEL = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/cancel";
    public static final String PURCHASE_INVOICE_INFORMATION = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/information";
    public static final String PURCHASE_INVOICE_LINES = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/lines";
    public static final String PURCHASE_INVOICE_NOTES = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/notes";
    public static final String PURCHASE_INVOICE_PDF = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/pdf";
    public static final String PURCHASE_INVOICE_PURCHASE_CREDIT_NOTE_APPLICATIONS = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/purchase_credit_note_applications";
    public static final String PURCHASE_INVOICE_PURCHASE_PAYMENTS = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/purchase_payments";
    public static final String PURCHASE_INVOICE_REACTIVATE = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/reactivate";
    public static final String PURCHASE_INVOICE_LINE = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/lines/{purchase_invoice_line_uuid}";
    public static final String PURCHASE_INVOICE_NOTE = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/notes/{note_id}";
    public static final String PURCHASE_INVOICE_NOTE_FILES = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/notes/{note_id}/files";
    public static final String PURCHASE_INVOICE_NOTE_FILE = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/notes/{note_id}/files/{file_id}";
    /**
     * Not scoped by the server: any application id answers under any purchase invoice that exists.
     */
    public static final String PURCHASE_INVOICE_PURCHASE_CREDIT_NOTE_APPLICATION = "/api/{api_version}/purchase_invoices/{purchase_invoice_id}/purchase_credit_note_applications/{application_id}";

    /** The key a listing's records sit under. */
    public static final String PURCHASE_INVOICES_KEY = "data";
    public static final String PURCHASE_INVOICES_NOTES_KEY = "data";
    public static final String PURCHASE_INVOICE_NOTES_KEY = "data";
    public static final String PURCHASE_INVOICE_PURCHASE_CREDIT_NOTE_APPLICATIONS_KEY = "data";
    public static final String PURCHASE_INVOICE_PURCHASE_PAYMENTS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String FILE_KEY = "file";
    public static final String NOTE_KEY = "note";
    public static final String PURCHASE_INVOICE_KEY = "purchase_invoice";
    public static final String WATCHERLIST_KEY = "watcherList";
    public static final String PURCHASE_CREDIT_NOTE_APPLICATION_KEY = "purchase_credit_note_application";
}
