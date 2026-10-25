package com.exsited.resources.note_attachments;

/** Path templates and body keys for the note attachment endpoints. */
public final class NoteAttachmentsUrls {

    private NoteAttachmentsUrls() {}

    /** Singular on the wire, and not scoped to the note's owner. */
    public static final String NOTE_ATTACHMENT = "/api/{api_version}/note_attachment/{attachment_uuid}";

    /** The envelope one record arrives under. */
    public static final String NOTE_ATTACHMENT_KEY = "note_attachment";
}
