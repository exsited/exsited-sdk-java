package com.exsited.resources.purchase_credit_notes;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.purchase_credit_notes.purchase_credit_notes_dto.PurchaseCreditNotesDto.PurchaseCreditNoteCreated;
import com.exsited.resources.purchase_credit_notes.purchase_credit_notes_dto.PurchaseCreditNotesDto.PurchaseCreditNoteList;
import com.exsited.resources.purchase_credit_notes.purchase_credit_notes_dto.PurchaseCreditNotesDto.PurchaseCreditNoteRead;
import java.util.Map;

/** Purchase credit note endpoints, under {@code /api/{api_version}/purchase_credit_notes}. */
public final class PurchaseCreditNotes implements BoundResource {

    private final ExsitedClient client;

    public PurchaseCreditNotes(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List purchase credit notes: {@code GET /purchase_credit_notes}. */
    public ApiResponse<PurchaseCreditNoteList> getPurchaseCreditNotes(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(PurchaseCreditNotesUrls.PURCHASE_CREDIT_NOTES,
                reopen(options).queryParams(queryParams).build(), PurchaseCreditNoteList.class);
    }

    public ApiResponse<PurchaseCreditNoteList> getPurchaseCreditNotes(
            Map<String, Object> queryParams) {
        return getPurchaseCreditNotes(queryParams, null);
    }

    public ApiResponse<PurchaseCreditNoteList> getPurchaseCreditNotes() {
        return getPurchaseCreditNotes(null, null);
    }

    /** Get single: {@code GET /purchase_credit_notes/{purchase_credit_note_id}}. */
    public ApiResponse<PurchaseCreditNoteRead> getPurchaseCreditNote(
            String purchaseCreditNoteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseCreditNoteId, "purchase_credit_note_id");
        return client.sendGetRequest(PurchaseCreditNotesUrls.PURCHASE_CREDIT_NOTE,
                reopen(options).pathParam("purchase_credit_note_id", purchaseCreditNoteId).queryParams(queryParams).build(), PurchaseCreditNoteRead.class);
    }

    public ApiResponse<PurchaseCreditNoteRead> getPurchaseCreditNote(
            String purchaseCreditNoteId, Map<String, Object> queryParams) {
        return getPurchaseCreditNote(purchaseCreditNoteId, queryParams, null);
    }

    public ApiResponse<PurchaseCreditNoteRead> getPurchaseCreditNote(String purchaseCreditNoteId) {
        return getPurchaseCreditNote(purchaseCreditNoteId, null, null);
    }

    /**
     * Update: {@code PATCH /purchase_credit_notes/{purchase_credit_note_id}}.
     */
    public ApiResponse<PurchaseCreditNoteRead> updatePurchaseCreditNote(
            String purchaseCreditNoteId, Object payload, RequestOptions options) {
        requireValue(purchaseCreditNoteId, "purchase_credit_note_id");
        requirePayload(payload);
        return client.sendPatchRequest(PurchaseCreditNotesUrls.PURCHASE_CREDIT_NOTE,
                reopen(options).pathParam("purchase_credit_note_id", purchaseCreditNoteId).jsonBody(payload).build(), PurchaseCreditNoteRead.class);
    }

    public ApiResponse<PurchaseCreditNoteRead> updatePurchaseCreditNote(
            String purchaseCreditNoteId, Object payload) {
        return updatePurchaseCreditNote(purchaseCreditNoteId, payload, null);
    }

    /**
     * Credit a purchase invoice: {@code POST /purchase_credit_notes}, as {@code
     * {"purchase_credit_note": {purchase_invoice_id, amount}}}; {@code 201}, drawn from its
     * payments.
     */
    public ApiResponse<PurchaseCreditNoteCreated> createPurchaseCreditNote(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PurchaseCreditNotesUrls.PURCHASE_CREDIT_NOTES,
                reopen(options).jsonBody(payload).build(), PurchaseCreditNoteCreated.class);
    }

    public ApiResponse<PurchaseCreditNoteCreated> createPurchaseCreditNote(Object payload) {
        return createPurchaseCreditNote(payload, null);
    }

    /**
     * Delete a purchase credit note:
     * {@code DELETE /purchase_credit_notes/{purchase_credit_note_id}}, by its {@code pcn_} id only;
     * {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deletePurchaseCreditNote(
            String purchaseCreditNoteId, RequestOptions options) {
        requireValue(purchaseCreditNoteId, "purchase_credit_note_id");
        return client.sendDeleteRequest(PurchaseCreditNotesUrls.PURCHASE_CREDIT_NOTE,
                reopen(options).pathParam("purchase_credit_note_id", purchaseCreditNoteId).build(), null);
    }

    public ApiResponse<Dto> deletePurchaseCreditNote(String purchaseCreditNoteId) {
        return deletePurchaseCreditNote(purchaseCreditNoteId, null);
    }

    /**
     * Apply credit to purchase invoices:
     * {@code POST /purchase_credit_notes/{purchase_credit_note_id}/apply}, under
     * {@code purchase_credit_note.purchase_invoices}; it records a purchase payment.
     */
    public ApiResponse<PurchaseCreditNoteCreated> applyPurchaseCreditNote(
            String purchaseCreditNoteId, Object payload, RequestOptions options) {
        requireValue(purchaseCreditNoteId, "purchase_credit_note_id");
        requirePayload(payload);
        return client.sendPostRequest(PurchaseCreditNotesUrls.PURCHASE_CREDIT_NOTE_APPLY,
                reopen(options).pathParam("purchase_credit_note_id", purchaseCreditNoteId).jsonBody(payload).build(), PurchaseCreditNoteCreated.class);
    }

    public ApiResponse<PurchaseCreditNoteCreated> applyPurchaseCreditNote(
            String purchaseCreditNoteId, Object payload) {
        return applyPurchaseCreditNote(purchaseCreditNoteId, payload, null);
    }

    /**
     * Attach a note: {@code POST /purchase_credit_notes/{purchase_credit_note_id}/notes}; a form,
     * the text as the {@code note} field, and {@code 201}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createPurchaseCreditNoteNote(
            String purchaseCreditNoteId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(purchaseCreditNoteId, "purchase_credit_note_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("purchase_credit_note_id", purchaseCreditNoteId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(PurchaseCreditNotesUrls.PURCHASE_CREDIT_NOTE_NOTES,
                request.build(), null);
    }

    public ApiResponse<Dto> createPurchaseCreditNoteNote(
            String purchaseCreditNoteId, String note, RequestOptions options) {
        return createPurchaseCreditNoteNote(purchaseCreditNoteId, note, null, options);
    }

    public ApiResponse<Dto> createPurchaseCreditNoteNote(String purchaseCreditNoteId, String note) {
        return createPurchaseCreditNoteNote(purchaseCreditNoteId, note, null, null);
    }

    /**
     * Delete one note:
     * {@code DELETE /purchase_credit_notes/{purchase_credit_note_id}/notes/{note_id}}, by the
     * {@code pcn_} and {@code note_} ids; {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deletePurchaseCreditNoteNote(
            String purchaseCreditNoteId, String noteId, RequestOptions options) {
        requireValue(purchaseCreditNoteId, "purchase_credit_note_id");
        requireValue(noteId, "note_id");
        return client.sendDeleteRequest(PurchaseCreditNotesUrls.PURCHASE_CREDIT_NOTE_NOTE,
                reopen(options).pathParam("purchase_credit_note_id", purchaseCreditNoteId).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> deletePurchaseCreditNoteNote(String purchaseCreditNoteId, String noteId) {
        return deletePurchaseCreditNoteNote(purchaseCreditNoteId, noteId, null);
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
