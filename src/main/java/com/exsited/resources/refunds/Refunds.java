package com.exsited.resources.refunds;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.NoteFileList;
import com.exsited.resources.refunds.refunds_dto.RefundsDto.Refund;
import com.exsited.resources.refunds.refunds_dto.RefundsDto.RefundList;
import com.exsited.resources.refunds.refunds_dto.RefundsDto.RefundNote;
import com.exsited.resources.refunds.refunds_dto.RefundsDto.RefundNoteFile;
import com.exsited.resources.refunds.refunds_dto.RefundsDto.RefundNoteList;
import com.exsited.resources.refunds.refunds_dto.RefundsDto.RefundUpdated;
import java.util.Map;

/** Refund endpoints, under {@code /api/{api_version}}. */
public final class Refunds implements BoundResource {

    private final ExsitedClient client;

    public Refunds(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List: {@code GET /refunds}. */
    public ApiResponse<RefundList> getRefunds(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(RefundsUrls.REFUNDS,
                reopen(options).queryParams(queryParams).build(), RefundList.class);
    }

    public ApiResponse<RefundList> getRefunds(
            Map<String, Object> queryParams) {
        return getRefunds(queryParams, null);
    }

    public ApiResponse<RefundList> getRefunds() {
        return getRefunds(null, null);
    }

    /**
     * Delete refund: {@code DELETE /refunds/{refund_id}}. Recorded 204. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteRefund(
            String refundId, RequestOptions options) {
        requireValue(refundId, "refund_id");
        return client.sendDeleteRequest(RefundsUrls.REFUND,
                reopen(options).pathParam("refund_id", refundId).build(), null);
    }

    public ApiResponse<Dto> deleteRefund(
            String refundId) {
        return deleteRefund(refundId, null);
    }

    /** Get single: {@code GET /refunds/{refund_id}}. */
    public ApiResponse<Refund> getRefund(
            String refundId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(refundId, "refund_id");
        return client.sendGetRequest(RefundsUrls.REFUND,
                reopen(options).pathParam("refund_id", refundId).queryParams(queryParams).build(), Refund.class);
    }

    public ApiResponse<Refund> getRefund(
            String refundId, Map<String, Object> queryParams) {
        return getRefund(refundId, queryParams, null);
    }

    public ApiResponse<Refund> getRefund(String refundId) {
        return getRefund(refundId, null, null);
    }

    /** Update refund: {@code PATCH /refunds/{refund_id}}. Recorded 202. */
    public ApiResponse<RefundUpdated> updateRefund(
            String refundId, Object payload, RequestOptions options) {
        requireValue(refundId, "refund_id");
        requirePayload(payload);
        return client.sendPatchRequest(RefundsUrls.REFUND,
                reopen(options).pathParam("refund_id", refundId).jsonBody(payload).build(), RefundUpdated.class);
    }

    public ApiResponse<RefundUpdated> updateRefund(
            String refundId, Object payload) {
        return updateRefund(refundId, payload, null);
    }

    /** Create refund: {@code POST /credit_notes/{credit_note_id}/refunds}. Recorded 201. */
    public ApiResponse<RefundUpdated> createCreditNoteRefund(
            String creditNoteId, Object payload, RequestOptions options) {
        requireValue(creditNoteId, "credit_note_id");
        requirePayload(payload);
        return client.sendPostRequest(RefundsUrls.CREDIT_NOTE_REFUNDS,
                reopen(options).pathParam("credit_note_id", creditNoteId).jsonBody(payload).build(), RefundUpdated.class);
    }

    public ApiResponse<RefundUpdated> createCreditNoteRefund(
            String creditNoteId, Object payload) {
        return createCreditNoteRefund(creditNoteId, payload, null);
    }

    /**
     * List a refund's notes: {@code GET /refunds/{refund_id}/notes}; a paged listing, under
     * {@code data}.
     */
    public ApiResponse<RefundNoteList> getRefundNotes(
            String refundId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(refundId, "refund_id");
        return client.sendGetRequest(RefundsUrls.REFUND_NOTES,
                reopen(options).pathParam("refund_id", refundId).queryParams(queryParams).build(), RefundNoteList.class);
    }

    public ApiResponse<RefundNoteList> getRefundNotes(String refundId, Map<String, Object> queryParams) {
        return getRefundNotes(refundId, queryParams, null);
    }

    public ApiResponse<RefundNoteList> getRefundNotes(String refundId) {
        return getRefundNotes(refundId, null, null);
    }

    /**
     * Retrieve one note: {@code GET /refunds/{refund_id}/notes/{note_id}}, by its {@code note_} id;
     * under {@code note}.
     */
    public ApiResponse<RefundNote> getRefundNote(
            String refundId, String noteId, RequestOptions options) {
        requireValue(refundId, "refund_id");
        requireValue(noteId, "note_id");
        return client.sendGetRequest(RefundsUrls.REFUND_NOTE,
                reopen(options).pathParam("refund_id", refundId).pathParam("note_id", noteId).build(), RefundNote.class);
    }

    public ApiResponse<RefundNote> getRefundNote(String refundId, String noteId) {
        return getRefundNote(refundId, noteId, null);
    }

    /**
     * Attach a note to a refund: {@code POST /refunds/{refund_id}/notes}; a form, the text as the
     * {@code note} field, and {@code 201} under {@code note}.
     */
    public ApiResponse<RefundNote> createRefundNote(
            String refundId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(refundId, "refund_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("refund_id", refundId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(RefundsUrls.REFUND_NOTES,
                request.build(), RefundNote.class);
    }

    public ApiResponse<RefundNote> createRefundNote(String refundId, String note, RequestOptions options) {
        return createRefundNote(refundId, note, null, options);
    }

    public ApiResponse<RefundNote> createRefundNote(String refundId, String note) {
        return createRefundNote(refundId, note, null, null);
    }

    /**
     * Delete one note: {@code DELETE /refunds/{refund_id}/notes/{note_id}}; {@code 204}. The server
     * checks {@code CREATE_REFUND_NOTE} here, not a delete permission. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteRefundNote(
            String refundId, String noteId, RequestOptions options) {
        requireValue(refundId, "refund_id");
        requireValue(noteId, "note_id");
        return client.sendDeleteRequest(RefundsUrls.REFUND_NOTE,
                reopen(options).pathParam("refund_id", refundId).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> deleteRefundNote(String refundId, String noteId) {
        return deleteRefundNote(refundId, noteId, null);
    }

    /**
     * List a note's attachments: {@code GET /refunds/{refund_id}/notes/{note_id}/files}, under
     * {@code data}.
     */
    public ApiResponse<NoteFileList> getRefundNoteFiles(
            String refundId, String noteId, RequestOptions options) {
        requireValue(refundId, "refund_id");
        requireValue(noteId, "note_id");
        return client.sendGetRequest(RefundsUrls.REFUND_NOTE_FILES,
                reopen(options).pathParam("refund_id", refundId).pathParam("note_id", noteId).build(), NoteFileList.class);
    }

    public ApiResponse<NoteFileList> getRefundNoteFiles(String refundId, String noteId) {
        return getRefundNoteFiles(refundId, noteId, null);
    }

    /**
     * Retrieve one attachment: {@code GET /refunds/{refund_id}/notes/{note_id}/files/{file_id}}, by
     * the attachment's {@code uuid}; a JSON descriptor under {@code file}, not the file's bytes.
     */
    public ApiResponse<RefundNoteFile> getRefundNoteFile(
            String refundId, String noteId, String fileId, RequestOptions options) {
        requireValue(refundId, "refund_id");
        requireValue(noteId, "note_id");
        requireValue(fileId, "file_id");
        return client.sendGetRequest(RefundsUrls.REFUND_NOTE_FILE,
                reopen(options).pathParam("refund_id", refundId).pathParam("note_id", noteId).pathParam("file_id", fileId).build(), RefundNoteFile.class);
    }

    public ApiResponse<RefundNoteFile> getRefundNoteFile(String refundId, String noteId, String fileId) {
        return getRefundNoteFile(refundId, noteId, fileId, null);
    }

    /**
     * Delete one attachment: {@code DELETE /refunds/{refund_id}/notes/{note_id}/files/{file_id}},
     * by the attachment's {@code uuid}; {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteRefundNoteFile(
            String refundId, String noteId, String fileId, RequestOptions options) {
        requireValue(refundId, "refund_id");
        requireValue(noteId, "note_id");
        requireValue(fileId, "file_id");
        return client.sendDeleteRequest(RefundsUrls.REFUND_NOTE_FILE,
                reopen(options).pathParam("refund_id", refundId).pathParam("note_id", noteId).pathParam("file_id", fileId).build(), null);
    }

    public ApiResponse<Dto> deleteRefundNoteFile(String refundId, String noteId, String fileId) {
        return deleteRefundNoteFile(refundId, noteId, fileId, null);
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
