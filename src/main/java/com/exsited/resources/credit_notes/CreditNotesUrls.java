package com.exsited.resources.credit_notes;

import java.util.List;

/** Path templates and body keys for the credit note endpoints. */
public final class CreditNotesUrls {

    private CreditNotesUrls() {}

    public static final String CREDIT_NOTES = "/api/{api_version}/credit_notes";
    public static final String CREDIT_NOTE = "/api/{api_version}/credit_notes/{credit_note_id}";
    public static final String CREDIT_NOTE_APPLY = "/api/{api_version}/credit_notes/{credit_note_id}/apply";
    public static final String CREDIT_NOTE_ITEMS = "/api/{api_version}/credit_notes/{credit_note_id}/items";
    public static final String CREDIT_NOTE_CREDIT_NOTE_APPLICATIONS = "/api/{api_version}/credit_notes/{credit_note_id}/credit_note_applications";
    public static final String CREDIT_NOTE_NOTES = "/api/{api_version}/credit_notes/{credit_note_id}/notes";
    public static final String CREDIT_NOTE_NOTE = "/api/{api_version}/credit_notes/{credit_note_id}/notes/{note_id}";
    public static final String CREDIT_NOTE_NOTE_FILES = "/api/{api_version}/credit_notes/{credit_note_id}/notes/{note_id}/files";
    public static final String CREDIT_NOTE_NOTE_FILE = "/api/{api_version}/credit_notes/{credit_note_id}/notes/{note_id}/files/{file_id}";

    /** The key a listing's records sit under. */
    public static final String CREDIT_NOTES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String CREDIT_NOTE_KEY = "credit_note";
    public static final String CREDIT_NOTE_NOTES_KEY = "data";
    public static final String CREDIT_NOTE_NOTE_FILES_KEY = "data";
    public static final String NOTE_KEY = "note";
    public static final String FILE_KEY = "file";

    /**
     * The two keys {@code createCreditNote} takes under {@code credit_note}; any other is a
     * {@code 400}.
     */
    public static final List<String> CREDIT_NOTE_CREATE_KEYS = List.of("invoice_id", "amount");

    /**
     * The one key {@code applyCreditNote} takes under {@code credit_note}; each entry of it takes
     * {@code invoice_id} and {@code amount} and nothing else.
     */
    public static final List<String> CREDIT_NOTE_APPLY_KEYS = List.of("invoices");
}
