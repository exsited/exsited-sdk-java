package com.exsited.resources.purchase_credit_notes;

import java.util.List;

/** Path templates and body keys for the purchase credit note endpoints. */
public final class PurchaseCreditNotesUrls {

    private PurchaseCreditNotesUrls() {}

    public static final String PURCHASE_CREDIT_NOTES = "/api/{api_version}/purchase_credit_notes";
    public static final String PURCHASE_CREDIT_NOTE = "/api/{api_version}/purchase_credit_notes/{purchase_credit_note_id}";
    /**
     * These three take the {@code pcn_} id only; a code is refused {@code 400} where the reads
     * accept one.
     */
    public static final String PURCHASE_CREDIT_NOTE_APPLY = "/api/{api_version}/purchase_credit_notes/{purchase_credit_note_id}/apply";
    public static final String PURCHASE_CREDIT_NOTE_NOTES = "/api/{api_version}/purchase_credit_notes/{purchase_credit_note_id}/notes";
    public static final String PURCHASE_CREDIT_NOTE_NOTE = "/api/{api_version}/purchase_credit_notes/{purchase_credit_note_id}/notes/{note_id}";

    /** The key a listing's records sit under. */
    public static final String PURCHASE_CREDIT_NOTES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String PURCHASE_CREDIT_NOTE_KEY = "purchase_credit_note";
    public static final String NOTE_KEY = "note";

    /** The two keys {@code createPurchaseCreditNote} takes under {@code purchase_credit_note}. */
    public static final List<String> PURCHASE_CREDIT_NOTE_CREATE_KEYS = List.of(
            "purchase_invoice_id", "amount");

    /**
     * The one key {@code applyPurchaseCreditNote} takes under {@code purchase_credit_note}; each
     * entry of it takes {@code purchase_invoice_id} and {@code amount} and nothing else.
     */
    public static final List<String> PURCHASE_CREDIT_NOTE_APPLY_KEYS = List.of("purchase_invoices");
}
