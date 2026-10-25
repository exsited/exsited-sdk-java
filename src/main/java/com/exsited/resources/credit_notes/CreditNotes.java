package com.exsited.resources.credit_notes;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.credit_notes.credit_notes_dto.CreditNotesDto.CreditNoteApplications;
import com.exsited.resources.credit_notes.credit_notes_dto.CreditNotesDto.CreditNoteItems;
import com.exsited.resources.credit_notes.credit_notes_dto.CreditNotesDto.CreditNoteList;
import com.exsited.resources.credit_notes.credit_notes_dto.CreditNotesDto.CreditNoteNote;
import com.exsited.resources.credit_notes.credit_notes_dto.CreditNotesDto.CreditNoteNoteList;
import com.exsited.resources.credit_notes.credit_notes_dto.CreditNotesDto.CreditNoteRead;
import java.util.Map;

/** Credit note endpoints, under {@code /api/{api_version}/credit_notes}. */
public final class CreditNotes implements BoundResource {

    private final ExsitedClient client;

    public CreditNotes(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List credit notes: {@code GET /credit_notes}. */
    public ApiResponse<CreditNoteList> getCreditNotes(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(CreditNotesUrls.CREDIT_NOTES,
                reopen(options).queryParams(queryParams).build(), CreditNoteList.class);
    }

    public ApiResponse<CreditNoteList> getCreditNotes(
            Map<String, Object> queryParams) {
        return getCreditNotes(queryParams, null);
    }

    public ApiResponse<CreditNoteList> getCreditNotes() {
        return getCreditNotes(null, null);
    }

    /** Get single: {@code GET /credit_notes/{credit_note_id}}. */
    public ApiResponse<CreditNoteRead> getCreditNote(
            String creditNoteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(creditNoteId, "credit_note_id");
        return client.sendGetRequest(CreditNotesUrls.CREDIT_NOTE,
                reopen(options).pathParam("credit_note_id", creditNoteId).queryParams(queryParams).build(), CreditNoteRead.class);
    }

    public ApiResponse<CreditNoteRead> getCreditNote(
            String creditNoteId, Map<String, Object> queryParams) {
        return getCreditNote(creditNoteId, queryParams, null);
    }

    public ApiResponse<CreditNoteRead> getCreditNote(String creditNoteId) {
        return getCreditNote(creditNoteId, null, null);
    }

    /**
     * Update credit note: {@code PATCH /credit_notes/{credit_note_id}}.
     */
    public ApiResponse<CreditNoteRead> updateCreditNote(
            String creditNoteId, Object payload, RequestOptions options) {
        requireValue(creditNoteId, "credit_note_id");
        requirePayload(payload);
        return client.sendPatchRequest(CreditNotesUrls.CREDIT_NOTE,
                reopen(options).pathParam("credit_note_id", creditNoteId).jsonBody(payload).build(), CreditNoteRead.class);
    }

    public ApiResponse<CreditNoteRead> updateCreditNote(
            String creditNoteId, Object payload) {
        return updateCreditNote(creditNoteId, payload, null);
    }

    /**
     * Credit an invoice: {@code POST /credit_notes}, {@code {"credit_note": {invoice_id, amount}}};
     * {@code 201}. The amount is drawn from the invoice's active payments, oldest first. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createCreditNote(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(CreditNotesUrls.CREDIT_NOTES,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createCreditNote(Object payload) {
        return createCreditNote(payload, null);
    }

    /**
     * Delete a credit note: {@code DELETE /credit_notes/{credit_note_id}}, by {@code cn_} id or
     * code; {@code 204}. A credit note with an active refund is refused {@code 400} {@code in_use}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteCreditNote(
            String creditNoteId, RequestOptions options) {
        requireValue(creditNoteId, "credit_note_id");
        return client.sendDeleteRequest(CreditNotesUrls.CREDIT_NOTE,
                reopen(options).pathParam("credit_note_id", creditNoteId).build(), null);
    }

    public ApiResponse<Dto> deleteCreditNote(String creditNoteId) {
        return deleteCreditNote(creditNoteId, null);
    }

    /**
     * Apply a credit note to invoices: {@code POST /credit_notes/{credit_note_id}/apply}, under
     * {@code credit_note.invoices}; it records a payment against each, and answers the credit note.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> applyCreditNote(
            String creditNoteId, Object payload, RequestOptions options) {
        requireValue(creditNoteId, "credit_note_id");
        requirePayload(payload);
        return client.sendPostRequest(CreditNotesUrls.CREDIT_NOTE_APPLY,
                reopen(options).pathParam("credit_note_id", creditNoteId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> applyCreditNote(String creditNoteId, Object payload) {
        return applyCreditNote(creditNoteId, payload, null);
    }

    /**
     * A credit note's balance rows: {@code GET /credit_notes/{credit_note_id}/items}, under
     * {@code credit_note.items}; not a paged listing.
     */
    public ApiResponse<CreditNoteItems> getCreditNoteItems(
            String creditNoteId, RequestOptions options) {
        requireValue(creditNoteId, "credit_note_id");
        return client.sendGetRequest(CreditNotesUrls.CREDIT_NOTE_ITEMS,
                reopen(options).pathParam("credit_note_id", creditNoteId).build(), CreditNoteItems.class);
    }

    public ApiResponse<CreditNoteItems> getCreditNoteItems(String creditNoteId) {
        return getCreditNoteItems(creditNoteId, null);
    }

    /**
     * A credit note's applications:
     * {@code GET /credit_notes/{credit_note_id}/credit_note_applications}, under
     * {@code credit_note.applications}; not a paged listing.
     */
    public ApiResponse<CreditNoteApplications> getCreditNoteCreditNoteApplications(
            String creditNoteId, RequestOptions options) {
        requireValue(creditNoteId, "credit_note_id");
        return client.sendGetRequest(CreditNotesUrls.CREDIT_NOTE_CREDIT_NOTE_APPLICATIONS,
                reopen(options).pathParam("credit_note_id", creditNoteId).build(), CreditNoteApplications.class);
    }

    public ApiResponse<CreditNoteApplications> getCreditNoteCreditNoteApplications(String creditNoteId) {
        return getCreditNoteCreditNoteApplications(creditNoteId, null);
    }

    /**
     * List a credit note's notes: {@code GET /credit_notes/{credit_note_id}/notes}; a paged
     * listing, under {@code data}.
     */
    public ApiResponse<CreditNoteNoteList> getCreditNoteNotes(
            String creditNoteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(creditNoteId, "credit_note_id");
        return client.sendGetRequest(CreditNotesUrls.CREDIT_NOTE_NOTES,
                reopen(options).pathParam("credit_note_id", creditNoteId).queryParams(queryParams).build(), CreditNoteNoteList.class);
    }

    public ApiResponse<CreditNoteNoteList> getCreditNoteNotes(
            String creditNoteId, Map<String, Object> queryParams) {
        return getCreditNoteNotes(creditNoteId, queryParams, null);
    }

    public ApiResponse<CreditNoteNoteList> getCreditNoteNotes(String creditNoteId) {
        return getCreditNoteNotes(creditNoteId, null, null);
    }

    /**
     * Retrieve one note: {@code GET /credit_notes/{credit_note_id}/notes/{note_id}}, by its
     * {@code note_} id; under {@code note}.
     */
    public ApiResponse<CreditNoteNote> getCreditNoteNote(
            String creditNoteId, String noteId, RequestOptions options) {
        requireValue(creditNoteId, "credit_note_id");
        requireValue(noteId, "note_id");
        return client.sendGetRequest(CreditNotesUrls.CREDIT_NOTE_NOTE,
                reopen(options).pathParam("credit_note_id", creditNoteId).pathParam("note_id", noteId).build(), CreditNoteNote.class);
    }

    public ApiResponse<CreditNoteNote> getCreditNoteNote(String creditNoteId, String noteId) {
        return getCreditNoteNote(creditNoteId, noteId, null);
    }

    /**
     * Attach a note to a credit note: {@code POST /credit_notes/{credit_note_id}/notes}; a form,
     * the text as the {@code note} field, and {@code 201}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createCreditNoteNote(
            String creditNoteId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(creditNoteId, "credit_note_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("credit_note_id", creditNoteId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(CreditNotesUrls.CREDIT_NOTE_NOTES,
                request.build(), null);
    }

    public ApiResponse<Dto> createCreditNoteNote(String creditNoteId, String note, RequestOptions options) {
        return createCreditNoteNote(creditNoteId, note, null, options);
    }

    public ApiResponse<Dto> createCreditNoteNote(String creditNoteId, String note) {
        return createCreditNoteNote(creditNoteId, note, null, null);
    }

    /**
     * Delete one note: {@code DELETE /credit_notes/{credit_note_id}/notes/{note_id}}, by its
     * {@code note_} id; {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteCreditNoteNote(
            String creditNoteId, String noteId, RequestOptions options) {
        requireValue(creditNoteId, "credit_note_id");
        requireValue(noteId, "note_id");
        return client.sendDeleteRequest(CreditNotesUrls.CREDIT_NOTE_NOTE,
                reopen(options).pathParam("credit_note_id", creditNoteId).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> deleteCreditNoteNote(String creditNoteId, String noteId) {
        return deleteCreditNoteNote(creditNoteId, noteId, null);
    }

    /**
     * List a note's attachments: {@code GET /credit_notes/{credit_note_id}/notes/{note_id}/files},
     * under {@code data}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getCreditNoteNoteFiles(
            String creditNoteId, String noteId, RequestOptions options) {
        requireValue(creditNoteId, "credit_note_id");
        requireValue(noteId, "note_id");
        return client.sendGetRequest(CreditNotesUrls.CREDIT_NOTE_NOTE_FILES,
                reopen(options).pathParam("credit_note_id", creditNoteId).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> getCreditNoteNoteFiles(String creditNoteId, String noteId) {
        return getCreditNoteNoteFiles(creditNoteId, noteId, null);
    }

    /**
     * Retrieve one attachment:
     * {@code GET /credit_notes/{credit_note_id}/notes/{note_id}/files/{file_id}}; a JSON descriptor
     * under {@code file}, not the file's bytes. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getCreditNoteNoteFile(
            String creditNoteId, String noteId, String fileId, RequestOptions options) {
        requireValue(creditNoteId, "credit_note_id");
        requireValue(noteId, "note_id");
        requireValue(fileId, "file_id");
        return client.sendGetRequest(CreditNotesUrls.CREDIT_NOTE_NOTE_FILE,
                reopen(options).pathParam("credit_note_id", creditNoteId).pathParam("note_id", noteId).pathParam("file_id", fileId).build(), null);
    }

    public ApiResponse<Dto> getCreditNoteNoteFile(String creditNoteId, String noteId, String fileId) {
        return getCreditNoteNoteFile(creditNoteId, noteId, fileId, null);
    }

    private static RequestOptions.Builder reopen(RequestOptions options) {
        RequestOptions given = options == null ? RequestOptions.none() : options;
        return RequestOptions.builder()
                .pathParams(given.getPathParams())
                .queryParams(given.getQueryParams())
                .jsonBody(given.getJsonBody())
                .formData(given.getFormData())
                .files(given.getFiles())
                .headers(given.getHeaders())
                .authenticated(given.isAuthenticated())
                .raiseOnError(given.getRaiseOnError())
                .responseFormat(given.getResponseFormat())
                .dtoFactory(given.getDtoFactory());
    }

    private static void requireValue(String value, String what) {
        if (value == null || value.isBlank()) {
            throw new SdkUsageError(
                    what + " is required: the request cannot be built without it");
        }
    }

    private static void requirePayload(Object payload) {
        if (payload == null) {
            throw new SdkUsageError("payload is required: this endpoint takes a body, "
                    + "and null would send the request with no body at all");
        }
    }

    private static void requireNote(String note) {
        if (note == null) {
            throw new SdkUsageError(
                    "note is required: the note text is the body of this request, and "
                            + "omitting it would store the string \"null\" as the note");
        }
    }
}
