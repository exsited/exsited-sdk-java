package com.exsited.resources.payments;

import java.util.List;

/** Path templates and body keys for the payment endpoints. */
public final class PaymentsUrls {

    private PaymentsUrls() {}

    public static final String PAYMENTS = "/api/{api_version}/payments";
    public static final String PAYMENTS_NOTES = "/api/{api_version}/payments/notes";
    public static final String PAYMENT = "/api/{api_version}/payments/{payment_id}";
    public static final String INVOICE_PAYMENTS = "/api/{api_version}/invoices/{invoice_id}/payments";
    public static final String ORDER_PAYMENTS = "/api/{api_version}/orders/{order_id}/payments";
    public static final String PAYMENT_NOTES = "/api/{api_version}/payments/{payment_id}/notes";
    public static final String PAYMENT_NOTE = "/api/{api_version}/payments/{payment_id}/notes/{payment_note_id}";
    public static final String PAYMENT_NOTE_FILE = "/api/{api_version}/payments/{payment_id}/notes/{payment_note_id}/files/{payment_file_id}";
    public static final String PAYMENT_NOTE_FILES = "/api/{api_version}/payments/{payment_id}/notes/{payment_note_id}/files";
    /**
     * One payment by an invoice. The server does not scope it: any payment id answers under any
     * invoice that exists.
     */
    public static final String INVOICE_PAYMENT = "/api/{api_version}/invoices/{invoice_id}/payments/{payment_id}";

    /** The key a listing's records sit under. */
    public static final String INVOICE_PAYMENTS_KEY = "data";
    public static final String ORDER_PAYMENTS_KEY = "data";
    public static final String PAYMENTS_KEY = "data";
    public static final String PAYMENTS_NOTES_KEY = "data";
    public static final String PAYMENT_NOTES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String FILE_KEY = "file";
    public static final String NOTE_KEY = "note";
    public static final String PAYMENT_KEY = "payment";
    public static final String PAYMENT_NOTE_FILES_KEY = "data";

    /** The keys {@code createPayment} takes under {@code payment}; any other is a {@code 400}. */
    public static final List<String> PAYMENT_CREATE_KEYS = List.of(
            "id", "date", "origin", "payment_note", "note", "invoices", "payment_applied",
            "credit_applied", "gift_certificates", "custom_attributes");
}
