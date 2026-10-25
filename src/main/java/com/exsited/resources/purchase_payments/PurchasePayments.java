package com.exsited.resources.purchase_payments;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.purchase_credit_notes.purchase_credit_notes_dto.PurchaseCreditNotesDto.PurchaseCreditNoteList;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentList;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentNoteFile;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentNoteList;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentNoteRead;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentNoteUpdated;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentNotes;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentRead;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentUpdated;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto;
import java.util.Map;

/** Purchase payment endpoints, under {@code /api/{api_version}}. */
public final class PurchasePayments implements BoundResource {

    private final ExsitedClient client;

    public PurchasePayments(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List purchase payments: {@code GET /purchase_payments}. */
    public ApiResponse<PurchasePaymentList> getPurchasePayments(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(PurchasePaymentsUrls.PURCHASE_PAYMENTS,
                reopen(options).queryParams(queryParams).build(), PurchasePaymentList.class);
    }

    public ApiResponse<PurchasePaymentList> getPurchasePayments(
            Map<String, Object> queryParams) {
        return getPurchasePayments(queryParams, null);
    }

    public ApiResponse<PurchasePaymentList> getPurchasePayments() {
        return getPurchasePayments(null, null);
    }

    /** Create purchase payment: {@code POST /purchase_payments}. */
    public ApiResponse<PurchasePaymentsDto.PurchasePayments> createPurchasePayment(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PurchasePaymentsUrls.PURCHASE_PAYMENTS,
                reopen(options).jsonBody(payload).build(), PurchasePaymentsDto.PurchasePayments.class);
    }

    public ApiResponse<PurchasePaymentsDto.PurchasePayments> createPurchasePayment(
            Object payload) {
        return createPurchasePayment(payload, null);
    }

    /**
     * Delete purchase payment: {@code DELETE /purchase_payments/{purchase_payment_id}}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deletePurchasePayment(
            String purchasePaymentId, RequestOptions options) {
        requireValue(purchasePaymentId, "purchase_payment_id");
        return client.sendDeleteRequest(PurchasePaymentsUrls.PURCHASE_PAYMENT,
                reopen(options).pathParam("purchase_payment_id", purchasePaymentId).build(), null);
    }

    public ApiResponse<Dto> deletePurchasePayment(
            String purchasePaymentId) {
        return deletePurchasePayment(purchasePaymentId, null);
    }

    /** Get single: {@code GET /purchase_payments/{purchase_payment_id}}. */
    public ApiResponse<PurchasePaymentRead> getPurchasePayment(
            String purchasePaymentId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchasePaymentId, "purchase_payment_id");
        return client.sendGetRequest(PurchasePaymentsUrls.PURCHASE_PAYMENT,
                reopen(options).pathParam("purchase_payment_id", purchasePaymentId).queryParams(queryParams).build(), PurchasePaymentRead.class);
    }

    public ApiResponse<PurchasePaymentRead> getPurchasePayment(
            String purchasePaymentId, Map<String, Object> queryParams) {
        return getPurchasePayment(purchasePaymentId, queryParams, null);
    }

    public ApiResponse<PurchasePaymentRead> getPurchasePayment(String purchasePaymentId) {
        return getPurchasePayment(purchasePaymentId, null, null);
    }

    /** Update purchase payment: {@code PATCH /purchase_payments/{purchase_payment_id}}. */
    public ApiResponse<PurchasePaymentUpdated> updatePurchasePayment(
            String purchasePaymentId, Object payload, RequestOptions options) {
        requireValue(purchasePaymentId, "purchase_payment_id");
        requirePayload(payload);
        return client.sendPatchRequest(PurchasePaymentsUrls.PURCHASE_PAYMENT,
                reopen(options).pathParam("purchase_payment_id", purchasePaymentId).jsonBody(payload).build(), PurchasePaymentUpdated.class);
    }

    public ApiResponse<PurchasePaymentUpdated> updatePurchasePayment(
            String purchasePaymentId, Object payload) {
        return updatePurchasePayment(purchasePaymentId, payload, null);
    }

    /**
     * PI-scoped payment:
     * {@code POST /purchase_invoices/{purchase_invoice_id}/purchase_payments}.
     */
    public ApiResponse<PurchasePaymentsDto.PurchasePayments> createPurchaseInvoicePurchasePayment(
            String purchaseInvoiceId, Object payload, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        requirePayload(payload);
        return client.sendPostRequest(PurchasePaymentsUrls.PURCHASE_INVOICE_PURCHASE_PAYMENTS,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).jsonBody(payload).build(), PurchasePaymentsDto.PurchasePayments.class);
    }

    public ApiResponse<PurchasePaymentsDto.PurchasePayments> createPurchaseInvoicePurchasePayment(
            String purchaseInvoiceId, Object payload) {
        return createPurchaseInvoicePurchasePayment(purchaseInvoiceId, payload, null);
    }

    /** List purchase payment notes: {@code GET /purchase_payments/{purchase_payment_id}/notes}. */
    public ApiResponse<PurchasePaymentNoteList> getPurchasePaymentNotes(
            String purchasePaymentId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchasePaymentId, "purchase_payment_id");
        return client.sendGetRequest(PurchasePaymentsUrls.PURCHASE_PAYMENT_NOTES,
                reopen(options).pathParam("purchase_payment_id", purchasePaymentId).queryParams(queryParams).build(), PurchasePaymentNoteList.class);
    }

    public ApiResponse<PurchasePaymentNoteList> getPurchasePaymentNotes(
            String purchasePaymentId, Map<String, Object> queryParams) {
        return getPurchasePaymentNotes(purchasePaymentId, queryParams, null);
    }

    public ApiResponse<PurchasePaymentNoteList> getPurchasePaymentNotes(String purchasePaymentId) {
        return getPurchasePaymentNotes(purchasePaymentId, null, null);
    }

    /**
     * Create note: {@code POST /purchase_payments/{purchase_payment_id}/notes},
     * the text as the {@code note} form field; files on the options make it multipart.
     * Any {@code files} are sent beside the text as a multipart body.
     */
    public ApiResponse<PurchasePaymentNotes> createPurchasePaymentNote(
            String purchasePaymentId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(purchasePaymentId, "purchase_payment_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("purchase_payment_id", purchasePaymentId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(PurchasePaymentsUrls.PURCHASE_PAYMENT_NOTES,
                request.build(), PurchasePaymentNotes.class);
    }

    public ApiResponse<PurchasePaymentNotes> createPurchasePaymentNote(
            String purchasePaymentId, String note, RequestOptions options) {
        return createPurchasePaymentNote(purchasePaymentId, note, null, options);
    }

    public ApiResponse<PurchasePaymentNotes> createPurchasePaymentNote(
            String purchasePaymentId, String note) {
        return createPurchasePaymentNote(purchasePaymentId, note, null);
    }

    /** Get single: {@code GET /purchase_payments/{purchase_payment_id}/notes/{pp_note_id}}. */
    public ApiResponse<PurchasePaymentNoteRead> getPurchasePaymentNote(
            String purchasePaymentId, String ppNoteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchasePaymentId, "purchase_payment_id");
        requireValue(ppNoteId, "pp_note_id");
        return client.sendGetRequest(PurchasePaymentsUrls.PURCHASE_PAYMENT_NOTE,
                reopen(options).pathParam("purchase_payment_id", purchasePaymentId).pathParam("pp_note_id", ppNoteId).queryParams(queryParams).build(), PurchasePaymentNoteRead.class);
    }

    public ApiResponse<PurchasePaymentNoteRead> getPurchasePaymentNote(
            String purchasePaymentId, String ppNoteId, Map<String, Object> queryParams) {
        return getPurchasePaymentNote(purchasePaymentId, ppNoteId, queryParams, null);
    }

    public ApiResponse<PurchasePaymentNoteRead> getPurchasePaymentNote(String purchasePaymentId, String ppNoteId) {
        return getPurchasePaymentNote(purchasePaymentId, ppNoteId, null, null);
    }

    /**
     * Delete purchase payment note:
     * {@code DELETE /purchase_payments/{purchase_payment_id}/notes/{pp_note_id}}. Recorded 204. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deletePurchasePaymentNote(
            String purchasePaymentId, String ppNoteId, RequestOptions options) {
        requireValue(purchasePaymentId, "purchase_payment_id");
        requireValue(ppNoteId, "pp_note_id");
        return client.sendDeleteRequest(PurchasePaymentsUrls.PURCHASE_PAYMENT_NOTE,
                reopen(options).pathParam("purchase_payment_id", purchasePaymentId).pathParam("pp_note_id", ppNoteId).build(), null);
    }

    public ApiResponse<Dto> deletePurchasePaymentNote(
            String purchasePaymentId, String ppNoteId) {
        return deletePurchasePaymentNote(purchasePaymentId, ppNoteId, null);
    }

    /**
     * List purchase payment note files:
     * {@code GET /purchase_payments/{purchase_payment_id}/notes/{pp_note_id}/files}.
     */
    public ApiResponse<PurchasePaymentNoteRead> getPurchasePaymentNoteFiles(
            String purchasePaymentId, String ppNoteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchasePaymentId, "purchase_payment_id");
        requireValue(ppNoteId, "pp_note_id");
        return client.sendGetRequest(PurchasePaymentsUrls.PURCHASE_PAYMENT_NOTE_FILES,
                reopen(options).pathParam("purchase_payment_id", purchasePaymentId).pathParam("pp_note_id", ppNoteId).queryParams(queryParams).build(), PurchasePaymentNoteRead.class);
    }

    public ApiResponse<PurchasePaymentNoteRead> getPurchasePaymentNoteFiles(
            String purchasePaymentId, String ppNoteId, Map<String, Object> queryParams) {
        return getPurchasePaymentNoteFiles(purchasePaymentId, ppNoteId, queryParams, null);
    }

    public ApiResponse<PurchasePaymentNoteRead> getPurchasePaymentNoteFiles(String purchasePaymentId, String ppNoteId) {
        return getPurchasePaymentNoteFiles(purchasePaymentId, ppNoteId, null, null);
    }

    /**
     * Get single:
     * {@code GET /purchase_payments/{purchase_payment_id}/notes/{pp_note_id}/files/{file_id}}.
     */
    public ApiResponse<PurchasePaymentNoteFile> getPurchasePaymentNoteFile(
            String purchasePaymentId, String ppNoteId, String fileId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchasePaymentId, "purchase_payment_id");
        requireValue(ppNoteId, "pp_note_id");
        requireValue(fileId, "file_id");
        return client.sendGetRequest(PurchasePaymentsUrls.PURCHASE_PAYMENT_NOTE_FILE,
                reopen(options).pathParam("purchase_payment_id", purchasePaymentId).pathParam("pp_note_id", ppNoteId).pathParam("file_id", fileId).queryParams(queryParams).build(), PurchasePaymentNoteFile.class);
    }

    public ApiResponse<PurchasePaymentNoteFile> getPurchasePaymentNoteFile(
            String purchasePaymentId, String ppNoteId, String fileId, Map<String, Object> queryParams) {
        return getPurchasePaymentNoteFile(purchasePaymentId, ppNoteId, fileId, queryParams, null);
    }

    public ApiResponse<PurchasePaymentNoteFile> getPurchasePaymentNoteFile(String purchasePaymentId, String ppNoteId, String fileId) {
        return getPurchasePaymentNoteFile(purchasePaymentId, ppNoteId, fileId, null, null);
    }

    /**
     * The purchase credit notes a payment raised:
     * {@code GET /purchase_payments/{purchase_payment_id}/purchase_credit_notes}; a paged listing.
     */
    public ApiResponse<PurchaseCreditNoteList> getPurchasePaymentPurchaseCreditNotes(
            String purchasePaymentId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchasePaymentId, "purchase_payment_id");
        return client.sendGetRequest(PurchasePaymentsUrls.PURCHASE_PAYMENT_PURCHASE_CREDIT_NOTES,
                reopen(options).pathParam("purchase_payment_id", purchasePaymentId).queryParams(queryParams).build(), PurchaseCreditNoteList.class);
    }

    public ApiResponse<PurchaseCreditNoteList> getPurchasePaymentPurchaseCreditNotes(
            String purchasePaymentId, Map<String, Object> queryParams) {
        return getPurchasePaymentPurchaseCreditNotes(purchasePaymentId, queryParams, null);
    }

    public ApiResponse<PurchaseCreditNoteList> getPurchasePaymentPurchaseCreditNotes(
            String purchasePaymentId) {
        return getPurchasePaymentPurchaseCreditNotes(purchasePaymentId, null, null);
    }

    /**
     * Update a note: {@code PATCH /purchase_payments/{purchase_payment_id}/notes/{pp_note_id}}; a
     * form, the text as the {@code note} field, answered under {@code note}.
     */
    public ApiResponse<PurchasePaymentNoteUpdated> updatePurchasePaymentNote(
            String purchasePaymentId, String ppNoteId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(purchasePaymentId, "purchase_payment_id");
        requireValue(ppNoteId, "pp_note_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("purchase_payment_id", purchasePaymentId).pathParam("pp_note_id", ppNoteId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPatchRequest(PurchasePaymentsUrls.PURCHASE_PAYMENT_NOTE,
                request.build(), PurchasePaymentNoteUpdated.class);
    }

    public ApiResponse<PurchasePaymentNoteUpdated> updatePurchasePaymentNote(
            String purchasePaymentId, String ppNoteId, String note, RequestOptions options) {
        return updatePurchasePaymentNote(purchasePaymentId, ppNoteId, note, null, options);
    }

    public ApiResponse<PurchasePaymentNoteUpdated> updatePurchasePaymentNote(
            String purchasePaymentId, String ppNoteId, String note) {
        return updatePurchasePaymentNote(purchasePaymentId, ppNoteId, note, null, null);
    }

    /**
     * Delete one attachment:
     * {@code DELETE /purchase_payments/{purchase_payment_id}/notes/{pp_note_id}/files/{file_id}},
     * by the attachment's {@code uuid}; {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deletePurchasePaymentNoteFile(
            String purchasePaymentId, String ppNoteId, String fileId, RequestOptions options) {
        requireValue(purchasePaymentId, "purchase_payment_id");
        requireValue(ppNoteId, "pp_note_id");
        requireValue(fileId, "file_id");
        return client.sendDeleteRequest(PurchasePaymentsUrls.PURCHASE_PAYMENT_NOTE_FILE,
                reopen(options).pathParam("purchase_payment_id", purchasePaymentId).pathParam("pp_note_id", ppNoteId).pathParam("file_id", fileId).build(), null);
    }

    public ApiResponse<Dto> deletePurchasePaymentNoteFile(
            String purchasePaymentId, String ppNoteId, String fileId) {
        return deletePurchasePaymentNoteFile(purchasePaymentId, ppNoteId, fileId, null);
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
