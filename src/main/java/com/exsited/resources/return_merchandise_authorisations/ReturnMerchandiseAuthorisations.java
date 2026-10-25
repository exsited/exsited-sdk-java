package com.exsited.resources.return_merchandise_authorisations;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.return_merchandise_authorisations.return_merchandise_authorisations_dto.ReturnMerchandiseAuthorisationsDto.InvoiceReturnMerchandiseAuthorisationReceive;
import com.exsited.resources.return_merchandise_authorisations.return_merchandise_authorisations_dto.ReturnMerchandiseAuthorisationsDto.InvoiceReturnMerchandiseAuthorisationReceiveList;
import com.exsited.resources.return_merchandise_authorisations.return_merchandise_authorisations_dto.ReturnMerchandiseAuthorisationsDto.ReturnMerchandiseAuthorisationList;
import com.exsited.resources.return_merchandise_authorisations.return_merchandise_authorisations_dto.ReturnMerchandiseAuthorisationsDto.ReturnMerchandiseAuthorisationRead;
import com.exsited.resources.return_merchandise_authorisations.return_merchandise_authorisations_dto.ReturnMerchandiseAuthorisationsDto;
import java.util.Map;

/** Return merchandise authorisation endpoints, under {@code /api/{api_version}}. */
public final class ReturnMerchandiseAuthorisations implements BoundResource {

    private final ExsitedClient client;

    public ReturnMerchandiseAuthorisations(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List of RMAs: {@code GET /return_merchandise_authorisations}. */
    public ApiResponse<ReturnMerchandiseAuthorisationList> getReturnMerchandiseAuthorisations(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATIONS,
                reopen(options).queryParams(queryParams).build(), ReturnMerchandiseAuthorisationList.class);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationList> getReturnMerchandiseAuthorisations(
            Map<String, Object> queryParams) {
        return getReturnMerchandiseAuthorisations(queryParams, null);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationList> getReturnMerchandiseAuthorisations() {
        return getReturnMerchandiseAuthorisations(null, null);
    }

    /** Create RMA: {@code POST /return_merchandise_authorisations}. Recorded 201. */
    public ApiResponse<ReturnMerchandiseAuthorisationsDto.ReturnMerchandiseAuthorisations> createReturnMerchandiseAuthorisation(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATIONS,
                reopen(options).jsonBody(payload).build(), ReturnMerchandiseAuthorisationsDto.ReturnMerchandiseAuthorisations.class);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationsDto.ReturnMerchandiseAuthorisations> createReturnMerchandiseAuthorisation(
            Object payload) {
        return createReturnMerchandiseAuthorisation(payload, null);
    }

    /** RMA details: {@code GET /return_merchandise_authorisations/{rma_id}}. */
    public ApiResponse<ReturnMerchandiseAuthorisationRead> getReturnMerchandiseAuthorisation(
            String rmaId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(rmaId, "rma_id");
        return client.sendGetRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION,
                reopen(options).pathParam("rma_id", rmaId).queryParams(queryParams).build(), ReturnMerchandiseAuthorisationRead.class);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationRead> getReturnMerchandiseAuthorisation(
            String rmaId, Map<String, Object> queryParams) {
        return getReturnMerchandiseAuthorisation(rmaId, queryParams, null);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationRead> getReturnMerchandiseAuthorisation(String rmaId) {
        return getReturnMerchandiseAuthorisation(rmaId, null, null);
    }

    /**
     * Receive all:
     * {@code GET /invoices/{invoice_id}/return_merchandise_authorisations/{rma_code}/receive}.
     */
    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceiveList> getInvoiceReturnMerchandiseAuthorisationReceive(
            String invoiceId, String rmaId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(rmaId, "rma_code");
        return client.sendGetRequest(ReturnMerchandiseAuthorisationsUrls.INVOICE_RETURN_MERCHANDISE_AUTHORISATION_RECEIVE,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("rma_code", rmaId).queryParams(queryParams).build(), InvoiceReturnMerchandiseAuthorisationReceiveList.class);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceiveList> getInvoiceReturnMerchandiseAuthorisationReceive(
            String invoiceId, String rmaId, Map<String, Object> queryParams) {
        return getInvoiceReturnMerchandiseAuthorisationReceive(invoiceId, rmaId, queryParams, null);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceiveList> getInvoiceReturnMerchandiseAuthorisationReceive(String invoiceId, String rmaId) {
        return getInvoiceReturnMerchandiseAuthorisationReceive(invoiceId, rmaId, null, null);
    }

    /**
     * Receive single:
     * {@code GET /invoices/{invoice_id}/return_merchandise_authorisations/{rma_code}/receive/{receive_uuid}}.
     */
    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceive> getInvoiceReturnMerchandiseAuthorisationReceiveByUuid(
            String invoiceId, String rmaId, String receiveUuid, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(rmaId, "rma_code");
        requireValue(receiveUuid, "receive_uuid");
        return client.sendGetRequest(ReturnMerchandiseAuthorisationsUrls.INVOICE_RETURN_MERCHANDISE_AUTHORISATION_RECEIVE_BY_UUID,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("rma_code", rmaId).pathParam("receive_uuid", receiveUuid).queryParams(queryParams).build(), InvoiceReturnMerchandiseAuthorisationReceive.class);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceive> getInvoiceReturnMerchandiseAuthorisationReceiveByUuid(
            String invoiceId, String rmaId, String receiveUuid, Map<String, Object> queryParams) {
        return getInvoiceReturnMerchandiseAuthorisationReceiveByUuid(invoiceId, rmaId, receiveUuid, queryParams, null);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceive> getInvoiceReturnMerchandiseAuthorisationReceiveByUuid(String invoiceId, String rmaId, String receiveUuid) {
        return getInvoiceReturnMerchandiseAuthorisationReceiveByUuid(invoiceId, rmaId, receiveUuid, null, null);
    }

    /**
     * Add RMA note: {@code POST /return_merchandise_authorisations/{rma_id}/notes}, the
     * {@code rma_} id and a JSON {@code {"note": {"note": "..."}}}, not a form; no attachment. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createReturnMerchandiseAuthorisationNote(
            String rmaId, Object payload, RequestOptions options) {
        requireValue(rmaId, "rma_id");
        requirePayload(payload);
        return client.sendPostRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION_NOTES,
                reopen(options).pathParam("rma_id", rmaId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createReturnMerchandiseAuthorisationNote(String rmaId, Object payload) {
        return createReturnMerchandiseAuthorisationNote(rmaId, payload, null);
    }

    /**
     * Receive returned stock: {@code POST /return_merchandise_authorisations/{rma_id}/receive},
     * {@code {"return_merchandise_authorisation": {"receive_returns": [{"uuid",
     * "rma_receive_quantity"}]}}}.
     */
    public ApiResponse<ReturnMerchandiseAuthorisationRead> receiveReturnMerchandiseAuthorisation(
            String rmaId, Object payload, RequestOptions options) {
        requireValue(rmaId, "rma_id");
        requirePayload(payload);
        return client.sendPostRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION_RECEIVE,
                reopen(options).pathParam("rma_id", rmaId).jsonBody(payload).build(), ReturnMerchandiseAuthorisationRead.class);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationRead> receiveReturnMerchandiseAuthorisation(
            String rmaId, Object payload) {
        return receiveReturnMerchandiseAuthorisation(rmaId, payload, null);
    }

    /**
     * Delete RMA: {@code DELETE /return_merchandise_authorisations/{rma_id}}, the {@code rma_} id;
     * only a draft is deleted, anything else is refused {@code 400} {@code stock_committed}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteReturnMerchandiseAuthorisation(
            String rmaId, RequestOptions options) {
        requireValue(rmaId, "rma_id");
        return client.sendDeleteRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION,
                reopen(options).pathParam("rma_id", rmaId).build(), null);
    }

    public ApiResponse<Dto> deleteReturnMerchandiseAuthorisation(String rmaId) {
        return deleteReturnMerchandiseAuthorisation(rmaId, null);
    }

    /**
     * Update RMA: {@code PATCH /return_merchandise_authorisations/{rma_id}}, the {@code rma_} id
     * and {@code {"return_merchandise_authorisation": {...}}} of {@code rma_date}, {@code note} and
     * {@code returns}.
     */
    public ApiResponse<ReturnMerchandiseAuthorisationRead> updateReturnMerchandiseAuthorisation(
            String rmaId, Object payload, RequestOptions options) {
        requireValue(rmaId, "rma_id");
        requirePayload(payload);
        return client.sendPatchRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION,
                reopen(options).pathParam("rma_id", rmaId).jsonBody(payload).build(), ReturnMerchandiseAuthorisationRead.class);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationRead> updateReturnMerchandiseAuthorisation(
            String rmaId, Object payload) {
        return updateReturnMerchandiseAuthorisation(rmaId, payload, null);
    }

    /**
     * Approve RMA: {@code POST /return_merchandise_authorisations/{rma_id}/approve}, no body;
     * {@code 422} when the RMA holds no pending approval.
     */
    public ApiResponse<ReturnMerchandiseAuthorisationRead> approveReturnMerchandiseAuthorisation(
            String rmaId, RequestOptions options) {
        requireValue(rmaId, "rma_id");
        return client.sendPostRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION_APPROVE,
                reopen(options).pathParam("rma_id", rmaId).build(), ReturnMerchandiseAuthorisationRead.class);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationRead> approveReturnMerchandiseAuthorisation(
            String rmaId) {
        return approveReturnMerchandiseAuthorisation(rmaId, null);
    }

    /**
     * Delete RMA note: {@code DELETE /return_merchandise_authorisations/{rma_id}/notes/{note_id}},
     * the {@code rma_} id; a code answers {@code 404}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteReturnMerchandiseAuthorisationNote(
            String rmaId, String noteId, RequestOptions options) {
        requireValue(rmaId, "rma_id");
        requireValue(noteId, "note_id");
        return client.sendDeleteRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION_NOTE,
                reopen(options).pathParam("rma_id", rmaId).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> deleteReturnMerchandiseAuthorisationNote(String rmaId, String noteId) {
        return deleteReturnMerchandiseAuthorisationNote(rmaId, noteId, null);
    }

    /**
     * Delete an RMA note attachment:
     * {@code DELETE /return_merchandise_authorisations/{rma_id}/notes/{note_id}/files/{file_id}}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteReturnMerchandiseAuthorisationNoteFile(
            String rmaId, String noteId, String fileId, RequestOptions options) {
        requireValue(rmaId, "rma_id");
        requireValue(noteId, "note_id");
        requireValue(fileId, "file_id");
        return client.sendDeleteRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION_NOTE_FILE,
                reopen(options).pathParam("rma_id", rmaId).pathParam("note_id", noteId).pathParam("file_id", fileId).build(), null);
    }

    public ApiResponse<Dto> deleteReturnMerchandiseAuthorisationNoteFile(
            String rmaId, String noteId, String fileId) {
        return deleteReturnMerchandiseAuthorisationNoteFile(rmaId, noteId, fileId, null);
    }

    /**
     * Reject RMA: {@code POST /return_merchandise_authorisations/{rma_id}/reject}, no body;
     * {@code 422} when the RMA holds no pending approval.
     */
    public ApiResponse<ReturnMerchandiseAuthorisationRead> rejectReturnMerchandiseAuthorisation(
            String rmaId, RequestOptions options) {
        requireValue(rmaId, "rma_id");
        return client.sendPostRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION_REJECT,
                reopen(options).pathParam("rma_id", rmaId).build(), ReturnMerchandiseAuthorisationRead.class);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationRead> rejectReturnMerchandiseAuthorisation(
            String rmaId) {
        return rejectReturnMerchandiseAuthorisation(rmaId, null);
    }

    /**
     * List RMA notes: {@code GET /return_merchandise_authorisations/{rma_code}/notes}, by the RMA's
     * code; an {@code rma_} id answers {@code 404}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getReturnMerchandiseAuthorisationNotes(
            String rmaCode, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(rmaCode, "rma_code");
        return client.sendGetRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION_NOTES_BY_CODE,
                reopen(options).pathParam("rma_code", rmaCode).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getReturnMerchandiseAuthorisationNotes(
            String rmaCode, Map<String, Object> queryParams) {
        return getReturnMerchandiseAuthorisationNotes(rmaCode, queryParams, null);
    }

    public ApiResponse<Dto> getReturnMerchandiseAuthorisationNotes(String rmaCode) {
        return getReturnMerchandiseAuthorisationNotes(rmaCode, null, null);
    }

    /**
     * Get one RMA note: {@code GET /return_merchandise_authorisations/{rma_code}/notes/{note_id}},
     * by the RMA's code. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getReturnMerchandiseAuthorisationNote(
            String rmaCode, String noteId, RequestOptions options) {
        requireValue(rmaCode, "rma_code");
        requireValue(noteId, "note_id");
        return client.sendGetRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION_NOTE_BY_CODE,
                reopen(options).pathParam("rma_code", rmaCode).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> getReturnMerchandiseAuthorisationNote(String rmaCode, String noteId) {
        return getReturnMerchandiseAuthorisationNote(rmaCode, noteId, null);
    }

    /**
     * List an RMA note's attachments:
     * {@code GET /return_merchandise_authorisations/{rma_code}/notes/{note_id}/files}, by the code.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getReturnMerchandiseAuthorisationNoteFiles(
            String rmaCode, String noteId, RequestOptions options) {
        requireValue(rmaCode, "rma_code");
        requireValue(noteId, "note_id");
        return client.sendGetRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION_NOTE_FILES_BY_CODE,
                reopen(options).pathParam("rma_code", rmaCode).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> getReturnMerchandiseAuthorisationNoteFiles(String rmaCode, String noteId) {
        return getReturnMerchandiseAuthorisationNoteFiles(rmaCode, noteId, null);
    }

    /**
     * Get one RMA note attachment:
     * {@code GET /return_merchandise_authorisations/{rma_code}/notes/{note_id}/files/{file_id}}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getReturnMerchandiseAuthorisationNoteFile(
            String rmaCode, String noteId, String fileId, RequestOptions options) {
        requireValue(rmaCode, "rma_code");
        requireValue(noteId, "note_id");
        requireValue(fileId, "file_id");
        return client.sendGetRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION_NOTE_FILE_BY_CODE,
                reopen(options).pathParam("rma_code", rmaCode).pathParam("note_id", noteId).pathParam("file_id", fileId).build(), null);
    }

    public ApiResponse<Dto> getReturnMerchandiseAuthorisationNoteFile(
            String rmaCode, String noteId, String fileId) {
        return getReturnMerchandiseAuthorisationNoteFile(rmaCode, noteId, fileId, null);
    }

    /**
     * List an RMA's receipts: {@code GET /return_merchandise_authorisations/{rma_code}/receive}, by
     * the RMA's code; an {@code rma_} id answers {@code 404}.
     */
    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceiveList> getReturnMerchandiseAuthorisationReceive(
            String rmaCode, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(rmaCode, "rma_code");
        return client.sendGetRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION_RECEIVE_BY_CODE,
                reopen(options).pathParam("rma_code", rmaCode).queryParams(queryParams).build(), InvoiceReturnMerchandiseAuthorisationReceiveList.class);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceiveList> getReturnMerchandiseAuthorisationReceive(
            String rmaCode, Map<String, Object> queryParams) {
        return getReturnMerchandiseAuthorisationReceive(rmaCode, queryParams, null);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceiveList> getReturnMerchandiseAuthorisationReceive(
            String rmaCode) {
        return getReturnMerchandiseAuthorisationReceive(rmaCode, null, null);
    }

    /**
     * Get one RMA receipt:
     * {@code GET /return_merchandise_authorisations/{rma_code}/receive/{receive_uuid}}, the
     * {@code rrma_} id or the receipt's code, under {@code receive_rma}.
     */
    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceive> getReturnMerchandiseAuthorisationReceiveByUuid(
            String rmaCode, String receiveUuid, RequestOptions options) {
        requireValue(rmaCode, "rma_code");
        requireValue(receiveUuid, "receive_uuid");
        return client.sendGetRequest(ReturnMerchandiseAuthorisationsUrls.RETURN_MERCHANDISE_AUTHORISATION_RECEIVE_BY_UUID,
                reopen(options).pathParam("rma_code", rmaCode).pathParam("receive_uuid", receiveUuid).build(), InvoiceReturnMerchandiseAuthorisationReceive.class);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceive> getReturnMerchandiseAuthorisationReceiveByUuid(
            String rmaCode, String receiveUuid) {
        return getReturnMerchandiseAuthorisationReceiveByUuid(rmaCode, receiveUuid, null);
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
}
