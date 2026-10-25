package com.exsited.resources.payments;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.NoteFileList;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentList;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentNoteFile;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentNoteList;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentNoteRead;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentNotes;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentRead;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentsNoteList;
import java.util.Map;

/** Payment endpoints, under {@code /api/{api_version}}. */
public final class Payments implements BoundResource {

    private final ExsitedClient client;

    public Payments(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List payments: {@code GET /payments}. */
    public ApiResponse<PaymentList> getPayments(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(PaymentsUrls.PAYMENTS,
                reopen(options).queryParams(queryParams).build(), PaymentList.class);
    }

    public ApiResponse<PaymentList> getPayments(
            Map<String, Object> queryParams) {
        return getPayments(queryParams, null);
    }

    public ApiResponse<PaymentList> getPayments() {
        return getPayments(null, null);
    }

    /** List all notes across payments: {@code GET /payments/notes}. */
    public ApiResponse<PaymentsNoteList> getPaymentsNotes(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(PaymentsUrls.PAYMENTS_NOTES,
                reopen(options).queryParams(queryParams).build(), PaymentsNoteList.class);
    }

    public ApiResponse<PaymentsNoteList> getPaymentsNotes(
            Map<String, Object> queryParams) {
        return getPaymentsNotes(queryParams, null);
    }

    public ApiResponse<PaymentsNoteList> getPaymentsNotes() {
        return getPaymentsNotes(null, null);
    }

    /**
     * Delete payment: {@code DELETE /payments/{payment_id}}. Recorded 204. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deletePayment(
            String paymentId, RequestOptions options) {
        requireValue(paymentId, "payment_id");
        return client.sendDeleteRequest(PaymentsUrls.PAYMENT,
                reopen(options).pathParam("payment_id", paymentId).build(), null);
    }

    public ApiResponse<Dto> deletePayment(
            String paymentId) {
        return deletePayment(paymentId, null);
    }

    /**
     * Update payment information: {@code PATCH /payments/{payment_id}}.
     */
    public ApiResponse<PaymentRead> updatePayment(
            String paymentId, Object payload, RequestOptions options) {
        requireValue(paymentId, "payment_id");
        requirePayload(payload);
        return client.sendPatchRequest(PaymentsUrls.PAYMENT,
                reopen(options).pathParam("payment_id", paymentId).jsonBody(payload).build(), PaymentRead.class);
    }

    public ApiResponse<PaymentRead> updatePayment(
            String paymentId, Object payload) {
        return updatePayment(paymentId, payload, null);
    }

    /** Get single: {@code GET /payments/{payment_id}}. */
    public ApiResponse<PaymentRead> getPayment(
            String paymentId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(paymentId, "payment_id");
        return client.sendGetRequest(PaymentsUrls.PAYMENT,
                reopen(options).pathParam("payment_id", paymentId).queryParams(queryParams).build(), PaymentRead.class);
    }

    public ApiResponse<PaymentRead> getPayment(
            String paymentId, Map<String, Object> queryParams) {
        return getPayment(paymentId, queryParams, null);
    }

    public ApiResponse<PaymentRead> getPayment(String paymentId) {
        return getPayment(paymentId, null, null);
    }

    /** List invoice payments: {@code GET /invoices/{invoice_id}/payments}. */
    public ApiResponse<PaymentList> getInvoicePayments(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        return client.sendGetRequest(PaymentsUrls.INVOICE_PAYMENTS,
                reopen(options).pathParam("invoice_id", invoiceId).queryParams(queryParams).build(), PaymentList.class);
    }

    public ApiResponse<PaymentList> getInvoicePayments(
            String invoiceId, Map<String, Object> queryParams) {
        return getInvoicePayments(invoiceId, queryParams, null);
    }

    public ApiResponse<PaymentList> getInvoicePayments(String invoiceId) {
        return getInvoicePayments(invoiceId, null, null);
    }

    /** List order payments: {@code GET /orders/{order_id}/payments}. */
    public ApiResponse<PaymentList> getOrderPayments(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(PaymentsUrls.ORDER_PAYMENTS,
                reopen(options).pathParam("order_id", orderId).queryParams(queryParams).build(), PaymentList.class);
    }

    public ApiResponse<PaymentList> getOrderPayments(
            String orderId, Map<String, Object> queryParams) {
        return getOrderPayments(orderId, queryParams, null);
    }

    public ApiResponse<PaymentList> getOrderPayments(String orderId) {
        return getOrderPayments(orderId, null, null);
    }

    /** List notes: {@code GET /payments/{payment_id}/notes}. */
    public ApiResponse<PaymentNoteList> getPaymentNotes(
            String paymentId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(paymentId, "payment_id");
        return client.sendGetRequest(PaymentsUrls.PAYMENT_NOTES,
                reopen(options).pathParam("payment_id", paymentId).queryParams(queryParams).build(), PaymentNoteList.class);
    }

    public ApiResponse<PaymentNoteList> getPaymentNotes(
            String paymentId, Map<String, Object> queryParams) {
        return getPaymentNotes(paymentId, queryParams, null);
    }

    public ApiResponse<PaymentNoteList> getPaymentNotes(String paymentId) {
        return getPaymentNotes(paymentId, null, null);
    }

    /**
     * Create note: {@code POST /payments/{payment_id}/notes},
     * the text as the {@code note} form field; files on the options make it multipart.
     * Any {@code files} are sent beside the text as a multipart body.
     */
    public ApiResponse<PaymentNotes> createPaymentNote(
            String paymentId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(paymentId, "payment_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("payment_id", paymentId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(PaymentsUrls.PAYMENT_NOTES,
                request.build(), PaymentNotes.class);
    }

    public ApiResponse<PaymentNotes> createPaymentNote(
            String paymentId, String note, RequestOptions options) {
        return createPaymentNote(paymentId, note, null, options);
    }

    public ApiResponse<PaymentNotes> createPaymentNote(
            String paymentId, String note) {
        return createPaymentNote(paymentId, note, null);
    }

    /** Get specific note: {@code GET /payments/{payment_id}/notes/{payment_note_id}}. */
    public ApiResponse<PaymentNoteRead> getPaymentNote(
            String paymentId, String paymentNoteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(paymentId, "payment_id");
        requireValue(paymentNoteId, "payment_note_id");
        return client.sendGetRequest(PaymentsUrls.PAYMENT_NOTE,
                reopen(options).pathParam("payment_id", paymentId).pathParam("payment_note_id", paymentNoteId).queryParams(queryParams).build(), PaymentNoteRead.class);
    }

    public ApiResponse<PaymentNoteRead> getPaymentNote(
            String paymentId, String paymentNoteId, Map<String, Object> queryParams) {
        return getPaymentNote(paymentId, paymentNoteId, queryParams, null);
    }

    public ApiResponse<PaymentNoteRead> getPaymentNote(String paymentId, String paymentNoteId) {
        return getPaymentNote(paymentId, paymentNoteId, null, null);
    }

    /**
     * Delete note: {@code DELETE /payments/{payment_id}/notes/{payment_note_id}}. Recorded 400. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deletePaymentNote(
            String paymentId, String paymentNoteId, RequestOptions options) {
        requireValue(paymentId, "payment_id");
        requireValue(paymentNoteId, "payment_note_id");
        return client.sendDeleteRequest(PaymentsUrls.PAYMENT_NOTE,
                reopen(options).pathParam("payment_id", paymentId).pathParam("payment_note_id", paymentNoteId).build(), null);
    }

    public ApiResponse<Dto> deletePaymentNote(
            String paymentId, String paymentNoteId) {
        return deletePaymentNote(paymentId, paymentNoteId, null);
    }

    /**
     * Get note file:
     * {@code GET /payments/{payment_id}/notes/{payment_note_id}/files/{payment_file_id}}.
     * Recorded 404.
     */
    public ApiResponse<PaymentNoteFile> getPaymentNoteFile(
            String paymentId, String paymentNoteId, String paymentFileId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(paymentId, "payment_id");
        requireValue(paymentNoteId, "payment_note_id");
        requireValue(paymentFileId, "payment_file_id");
        return client.sendGetRequest(PaymentsUrls.PAYMENT_NOTE_FILE,
                reopen(options).pathParam("payment_id", paymentId).pathParam("payment_note_id", paymentNoteId).pathParam("payment_file_id", paymentFileId).queryParams(queryParams).build(), PaymentNoteFile.class);
    }

    public ApiResponse<PaymentNoteFile> getPaymentNoteFile(
            String paymentId, String paymentNoteId, String paymentFileId, Map<String, Object> queryParams) {
        return getPaymentNoteFile(paymentId, paymentNoteId, paymentFileId, queryParams, null);
    }

    public ApiResponse<PaymentNoteFile> getPaymentNoteFile(String paymentId, String paymentNoteId, String paymentFileId) {
        return getPaymentNoteFile(paymentId, paymentNoteId, paymentFileId, null, null);
    }

    /**
     * Record a payment: {@code POST /payments}, wrapped in {@code payment}; {@code 201}, answered
     * as {@code getPayment} reads it. A {@code payment_applied} processor with a card token charges
     * the card.
     */
    public ApiResponse<PaymentRead> createPayment(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PaymentsUrls.PAYMENTS,
                reopen(options).jsonBody(payload).build(), PaymentRead.class);
    }

    public ApiResponse<PaymentRead> createPayment(Object payload) {
        return createPayment(payload, null);
    }

    /**
     * Retrieve one payment through an invoice:
     * {@code GET /invoices/{invoice_id}/payments/{payment_id}}; the payment is not checked against
     * the invoice, only the invoice's existence is.
     */
    public ApiResponse<PaymentRead> getInvoicePayment(
            String invoiceId, String paymentId, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(paymentId, "payment_id");
        return client.sendGetRequest(PaymentsUrls.INVOICE_PAYMENT,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("payment_id", paymentId).build(), PaymentRead.class);
    }

    public ApiResponse<PaymentRead> getInvoicePayment(String invoiceId, String paymentId) {
        return getInvoicePayment(invoiceId, paymentId, null);
    }

    /**
     * List a note's attachments: {@code GET /payments/{payment_id}/notes/{payment_note_id}/files},
     * under {@code data}.
     */
    public ApiResponse<NoteFileList> getPaymentNoteFiles(
            String paymentId, String paymentNoteId, RequestOptions options) {
        requireValue(paymentId, "payment_id");
        requireValue(paymentNoteId, "payment_note_id");
        return client.sendGetRequest(PaymentsUrls.PAYMENT_NOTE_FILES,
                reopen(options).pathParam("payment_id", paymentId).pathParam("payment_note_id", paymentNoteId).build(), NoteFileList.class);
    }

    public ApiResponse<NoteFileList> getPaymentNoteFiles(String paymentId, String paymentNoteId) {
        return getPaymentNoteFiles(paymentId, paymentNoteId, null);
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
