package com.exsited.resources.refunds;

/** Path templates and body keys for the refund endpoints. */
public final class RefundsUrls {

    private RefundsUrls() {}

    public static final String REFUNDS = "/api/{api_version}/refunds";
    public static final String REFUND = "/api/{api_version}/refunds/{refund_id}";
    public static final String CREDIT_NOTE_REFUNDS = "/api/{api_version}/credit_notes/{credit_note_id}/refunds";
    /**
     * A refund's notes. {@code {refund_id}} is the refund's code, as {@code getRefunds} hands it
     * out.
     */
    public static final String REFUND_NOTES = "/api/{api_version}/refunds/{refund_id}/notes";
    public static final String REFUND_NOTE = "/api/{api_version}/refunds/{refund_id}/notes/{note_id}";
    public static final String REFUND_NOTE_FILES = "/api/{api_version}/refunds/{refund_id}/notes/{note_id}/files";
    public static final String REFUND_NOTE_FILE = "/api/{api_version}/refunds/{refund_id}/notes/{note_id}/files/{file_id}";

    /** The key a listing's records sit under. */
    public static final String REFUNDS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String REFUND_KEY = "refund";
    public static final String REFUND_NOTES_KEY = "data";
    public static final String REFUND_NOTE_FILES_KEY = "data";
    public static final String NOTE_KEY = "note";
    public static final String FILE_KEY = "file";
}
