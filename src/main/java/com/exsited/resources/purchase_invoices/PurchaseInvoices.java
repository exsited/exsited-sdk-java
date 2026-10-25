package com.exsited.resources.purchase_invoices;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.purchase_credit_note_applications.purchase_credit_note_applications_dto.PurchaseCreditNoteApplicationsDto.PurchaseCreditNoteApplicationRead;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.AccountPurchaseInvoices;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoice;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoiceCancel;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoiceInformation;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoiceLine;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoiceLines;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoiceList;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoiceNoteFile;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoiceNoteList;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoiceNoteRead;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoiceNoteUpdated;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoiceNotes;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoicePurchasePaymentList;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoiceRead;
import com.exsited.resources.purchase_invoices.purchase_invoices_dto.PurchaseInvoicesDto.PurchaseInvoicesNoteList;
import java.util.Map;

/** Purchase invoice endpoints, under {@code /api/{api_version}}. */
public final class PurchaseInvoices implements BoundResource {

    private final ExsitedClient client;

    public PurchaseInvoices(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List purchase invoices: {@code GET /purchase_invoices}. */
    public ApiResponse<PurchaseInvoiceList> getPurchaseInvoices(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICES,
                reopen(options).queryParams(queryParams).build(), PurchaseInvoiceList.class);
    }

    public ApiResponse<PurchaseInvoiceList> getPurchaseInvoices(
            Map<String, Object> queryParams) {
        return getPurchaseInvoices(queryParams, null);
    }

    public ApiResponse<PurchaseInvoiceList> getPurchaseInvoices() {
        return getPurchaseInvoices(null, null);
    }

    /** Create: {@code POST /purchase_invoices}. */
    public ApiResponse<PurchaseInvoiceRead> createPurchaseInvoice(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PurchaseInvoicesUrls.PURCHASE_INVOICES,
                reopen(options).jsonBody(payload).build(), PurchaseInvoiceRead.class);
    }

    public ApiResponse<PurchaseInvoiceRead> createPurchaseInvoice(
            Object payload) {
        return createPurchaseInvoice(payload, null);
    }

    /** List all notes: {@code GET /purchase_invoices/notes}. */
    public ApiResponse<PurchaseInvoicesNoteList> getPurchaseInvoicesNotes(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICES_NOTES,
                reopen(options).queryParams(queryParams).build(), PurchaseInvoicesNoteList.class);
    }

    public ApiResponse<PurchaseInvoicesNoteList> getPurchaseInvoicesNotes(
            Map<String, Object> queryParams) {
        return getPurchaseInvoicesNotes(queryParams, null);
    }

    public ApiResponse<PurchaseInvoicesNoteList> getPurchaseInvoicesNotes() {
        return getPurchaseInvoicesNotes(null, null);
    }

    /**
     * Delete purchase invoice: {@code DELETE /purchase_invoices/{purchase_invoice_id}}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deletePurchaseInvoice(
            String purchaseInvoiceId, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        return client.sendDeleteRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).build(), null);
    }

    public ApiResponse<Dto> deletePurchaseInvoice(
            String purchaseInvoiceId) {
        return deletePurchaseInvoice(purchaseInvoiceId, null);
    }

    /** Get single: {@code GET /purchase_invoices/{purchase_invoice_id}}. */
    public ApiResponse<PurchaseInvoiceRead> getPurchaseInvoice(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).queryParams(queryParams).build(), PurchaseInvoiceRead.class);
    }

    public ApiResponse<PurchaseInvoiceRead> getPurchaseInvoice(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return getPurchaseInvoice(purchaseInvoiceId, queryParams, null);
    }

    public ApiResponse<PurchaseInvoiceRead> getPurchaseInvoice(String purchaseInvoiceId) {
        return getPurchaseInvoice(purchaseInvoiceId, null, null);
    }

    /**
     * Amend purchase invoice: {@code POST /purchase_invoices/{purchase_invoice_id}/amend}.
     */
    public ApiResponse<PurchaseInvoiceRead> amendPurchaseInvoice(
            String purchaseInvoiceId, Object payload, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        requirePayload(payload);
        return client.sendPostRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_AMEND,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).jsonBody(payload).build(), PurchaseInvoiceRead.class);
    }

    public ApiResponse<PurchaseInvoiceRead> amendPurchaseInvoice(
            String purchaseInvoiceId, Object payload) {
        return amendPurchaseInvoice(purchaseInvoiceId, payload, null);
    }

    /**
     * Cancel purchase invoice:
     * {@code POST /purchase_invoices/{purchase_invoice_id}/cancel}. Recorded 201.
     */
    public ApiResponse<PurchaseInvoiceCancel> cancelPurchaseInvoice(
            String purchaseInvoiceId, Object payload, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        requirePayload(payload);
        return client.sendPostRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_CANCEL,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).jsonBody(payload).build(), PurchaseInvoiceCancel.class);
    }

    public ApiResponse<PurchaseInvoiceCancel> cancelPurchaseInvoice(
            String purchaseInvoiceId, Object payload) {
        return cancelPurchaseInvoice(purchaseInvoiceId, payload, null);
    }

    /**
     * Purchase invoice information:
     * {@code GET /purchase_invoices/{purchase_invoice_id}/information}.
     */
    public ApiResponse<PurchaseInvoice> getPurchaseInvoiceInformation(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_INFORMATION,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).queryParams(queryParams).build(), PurchaseInvoice.class);
    }

    public ApiResponse<PurchaseInvoice> getPurchaseInvoiceInformation(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return getPurchaseInvoiceInformation(purchaseInvoiceId, queryParams, null);
    }

    public ApiResponse<PurchaseInvoice> getPurchaseInvoiceInformation(String purchaseInvoiceId) {
        return getPurchaseInvoiceInformation(purchaseInvoiceId, null, null);
    }

    /**
     * Update purchase invoice information:
     * {@code PATCH /purchase_invoices/{purchase_invoice_id}/information}.
     */
    public ApiResponse<PurchaseInvoiceInformation> updatePurchaseInvoiceInformation(
            String purchaseInvoiceId, Object payload, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        requirePayload(payload);
        return client.sendPatchRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_INFORMATION,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).jsonBody(payload).build(), PurchaseInvoiceInformation.class);
    }

    public ApiResponse<PurchaseInvoiceInformation> updatePurchaseInvoiceInformation(
            String purchaseInvoiceId, Object payload) {
        return updatePurchaseInvoiceInformation(purchaseInvoiceId, payload, null);
    }

    /** List purchase invoice lines: {@code GET /purchase_invoices/{purchase_invoice_id}/lines}. */
    public ApiResponse<PurchaseInvoiceLines> getPurchaseInvoiceLines(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_LINES,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).queryParams(queryParams).build(), PurchaseInvoiceLines.class);
    }

    public ApiResponse<PurchaseInvoiceLines> getPurchaseInvoiceLines(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return getPurchaseInvoiceLines(purchaseInvoiceId, queryParams, null);
    }

    public ApiResponse<PurchaseInvoiceLines> getPurchaseInvoiceLines(String purchaseInvoiceId) {
        return getPurchaseInvoiceLines(purchaseInvoiceId, null, null);
    }

    /** List purchase invoice notes: {@code GET /purchase_invoices/{purchase_invoice_id}/notes}. */
    public ApiResponse<PurchaseInvoiceNoteList> getPurchaseInvoiceNotes(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_NOTES,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).queryParams(queryParams).build(), PurchaseInvoiceNoteList.class);
    }

    public ApiResponse<PurchaseInvoiceNoteList> getPurchaseInvoiceNotes(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return getPurchaseInvoiceNotes(purchaseInvoiceId, queryParams, null);
    }

    public ApiResponse<PurchaseInvoiceNoteList> getPurchaseInvoiceNotes(String purchaseInvoiceId) {
        return getPurchaseInvoiceNotes(purchaseInvoiceId, null, null);
    }

    /**
     * Create note: {@code POST /purchase_invoices/{purchase_invoice_id}/notes},
     * the text as the {@code note} form field; files on the options make it multipart.
     * Any {@code files} are sent beside the text as a multipart body.
     */
    public ApiResponse<PurchaseInvoiceNotes> createPurchaseInvoiceNote(
            String purchaseInvoiceId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_NOTES,
                request.build(), PurchaseInvoiceNotes.class);
    }

    public ApiResponse<PurchaseInvoiceNotes> createPurchaseInvoiceNote(
            String purchaseInvoiceId, String note, RequestOptions options) {
        return createPurchaseInvoiceNote(purchaseInvoiceId, note, null, options);
    }

    public ApiResponse<PurchaseInvoiceNotes> createPurchaseInvoiceNote(
            String purchaseInvoiceId, String note) {
        return createPurchaseInvoiceNote(purchaseInvoiceId, note, null);
    }

    /**
     * Purchase invoice PDF: {@code GET /purchase_invoices/{purchase_invoice_id}/pdf}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getPurchaseInvoicePdf(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_PDF,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getPurchaseInvoicePdf(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return getPurchaseInvoicePdf(purchaseInvoiceId, queryParams, null);
    }

    public ApiResponse<Dto> getPurchaseInvoicePdf(String purchaseInvoiceId) {
        return getPurchaseInvoicePdf(purchaseInvoiceId, null, null);
    }

    /**
     * List purchase invoice purchase credit note applications:
     * {@code GET /purchase_invoices/{purchase_invoice_id}/purchase_credit_note_applications}.
     */
    public ApiResponse<AccountPurchaseInvoices> getPurchaseInvoicePurchaseCreditNoteApplications(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_PURCHASE_CREDIT_NOTE_APPLICATIONS,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).queryParams(queryParams).build(), AccountPurchaseInvoices.class);
    }

    public ApiResponse<AccountPurchaseInvoices> getPurchaseInvoicePurchaseCreditNoteApplications(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return getPurchaseInvoicePurchaseCreditNoteApplications(purchaseInvoiceId, queryParams, null);
    }

    public ApiResponse<AccountPurchaseInvoices> getPurchaseInvoicePurchaseCreditNoteApplications(String purchaseInvoiceId) {
        return getPurchaseInvoicePurchaseCreditNoteApplications(purchaseInvoiceId, null, null);
    }

    /**
     * List purchase invoice purchase payments:
     * {@code GET /purchase_invoices/{purchase_invoice_id}/purchase_payments}.
     */
    public ApiResponse<PurchaseInvoicePurchasePaymentList> getPurchaseInvoicePurchasePayments(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_PURCHASE_PAYMENTS,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).queryParams(queryParams).build(), PurchaseInvoicePurchasePaymentList.class);
    }

    public ApiResponse<PurchaseInvoicePurchasePaymentList> getPurchaseInvoicePurchasePayments(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return getPurchaseInvoicePurchasePayments(purchaseInvoiceId, queryParams, null);
    }

    public ApiResponse<PurchaseInvoicePurchasePaymentList> getPurchaseInvoicePurchasePayments(String purchaseInvoiceId) {
        return getPurchaseInvoicePurchasePayments(purchaseInvoiceId, null, null);
    }

    /**
     * Reactivate purchase invoice:
     * {@code POST /purchase_invoices/{purchase_invoice_id}/reactivate}.
     */
    public ApiResponse<PurchaseInvoiceRead> reactivatePurchaseInvoice(
            String purchaseInvoiceId, Object payload, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        requirePayload(payload);
        return client.sendPostRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_REACTIVATE,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).jsonBody(payload).build(), PurchaseInvoiceRead.class);
    }

    public ApiResponse<PurchaseInvoiceRead> reactivatePurchaseInvoice(
            String purchaseInvoiceId, Object payload) {
        return reactivatePurchaseInvoice(purchaseInvoiceId, payload, null);
    }

    /**
     * Get single:
     * {@code GET /purchase_invoices/{purchase_invoice_id}/lines/{purchase_invoice_line_uuid}}.
     */
    public ApiResponse<PurchaseInvoiceLine> getPurchaseInvoiceLine(
            String purchaseInvoiceId, String purchaseInvoiceLineUuid, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        requireValue(purchaseInvoiceLineUuid, "purchase_invoice_line_uuid");
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_LINE,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).pathParam("purchase_invoice_line_uuid", purchaseInvoiceLineUuid).queryParams(queryParams).build(), PurchaseInvoiceLine.class);
    }

    public ApiResponse<PurchaseInvoiceLine> getPurchaseInvoiceLine(
            String purchaseInvoiceId, String purchaseInvoiceLineUuid, Map<String, Object> queryParams) {
        return getPurchaseInvoiceLine(purchaseInvoiceId, purchaseInvoiceLineUuid, queryParams, null);
    }

    public ApiResponse<PurchaseInvoiceLine> getPurchaseInvoiceLine(String purchaseInvoiceId, String purchaseInvoiceLineUuid) {
        return getPurchaseInvoiceLine(purchaseInvoiceId, purchaseInvoiceLineUuid, null, null);
    }

    /**
     * Update note: {@code PATCH /purchase_invoices/{purchase_invoice_id}/notes/{note_id}},
     * the text as the {@code note} form field; files on the options make it multipart.
     * Any {@code files} are sent beside the text as a multipart body.
     */
    public ApiResponse<PurchaseInvoiceNoteUpdated> updatePurchaseInvoiceNote(
            String purchaseInvoiceId, String noteId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        requireValue(noteId, "note_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).pathParam("note_id", noteId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPatchRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_NOTE,
                request.build(), PurchaseInvoiceNoteUpdated.class);
    }

    public ApiResponse<PurchaseInvoiceNoteUpdated> updatePurchaseInvoiceNote(
            String purchaseInvoiceId, String noteId, String note, RequestOptions options) {
        return updatePurchaseInvoiceNote(purchaseInvoiceId, noteId, note, null, options);
    }

    public ApiResponse<PurchaseInvoiceNoteUpdated> updatePurchaseInvoiceNote(
            String purchaseInvoiceId, String noteId, String note) {
        return updatePurchaseInvoiceNote(purchaseInvoiceId, noteId, note, null);
    }

    /** Get single: {@code GET /purchase_invoices/{purchase_invoice_id}/notes/{note_id}}. */
    public ApiResponse<PurchaseInvoiceNoteRead> getPurchaseInvoiceNote(
            String purchaseInvoiceId, String noteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        requireValue(noteId, "note_id");
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_NOTE,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).pathParam("note_id", noteId).queryParams(queryParams).build(), PurchaseInvoiceNoteRead.class);
    }

    public ApiResponse<PurchaseInvoiceNoteRead> getPurchaseInvoiceNote(
            String purchaseInvoiceId, String noteId, Map<String, Object> queryParams) {
        return getPurchaseInvoiceNote(purchaseInvoiceId, noteId, queryParams, null);
    }

    public ApiResponse<PurchaseInvoiceNoteRead> getPurchaseInvoiceNote(String purchaseInvoiceId, String noteId) {
        return getPurchaseInvoiceNote(purchaseInvoiceId, noteId, null, null);
    }

    /**
     * Delete purchase invoice note:
     * {@code DELETE /purchase_invoices/{purchase_invoice_id}/notes/{note_id}}. Recorded 204. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deletePurchaseInvoiceNote(
            String purchaseInvoiceId, String noteId, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        requireValue(noteId, "note_id");
        return client.sendDeleteRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_NOTE,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> deletePurchaseInvoiceNote(
            String purchaseInvoiceId, String noteId) {
        return deletePurchaseInvoiceNote(purchaseInvoiceId, noteId, null);
    }

    /**
     * List purchase invoice note files:
     * {@code GET /purchase_invoices/{purchase_invoice_id}/notes/{note_id}/files}.
     */
    public ApiResponse<PurchaseInvoiceNoteRead> getPurchaseInvoiceNoteFiles(
            String purchaseInvoiceId, String noteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        requireValue(noteId, "note_id");
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_NOTE_FILES,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).pathParam("note_id", noteId).queryParams(queryParams).build(), PurchaseInvoiceNoteRead.class);
    }

    public ApiResponse<PurchaseInvoiceNoteRead> getPurchaseInvoiceNoteFiles(
            String purchaseInvoiceId, String noteId, Map<String, Object> queryParams) {
        return getPurchaseInvoiceNoteFiles(purchaseInvoiceId, noteId, queryParams, null);
    }

    public ApiResponse<PurchaseInvoiceNoteRead> getPurchaseInvoiceNoteFiles(String purchaseInvoiceId, String noteId) {
        return getPurchaseInvoiceNoteFiles(purchaseInvoiceId, noteId, null, null);
    }

    /**
     * Get single:
     * {@code GET /purchase_invoices/{purchase_invoice_id}/notes/{note_id}/files/{file_id}}.
     */
    public ApiResponse<PurchaseInvoiceNoteFile> getPurchaseInvoiceNoteFile(
            String purchaseInvoiceId, String noteId, String fileId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        requireValue(noteId, "note_id");
        requireValue(fileId, "file_id");
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_NOTE_FILE,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).pathParam("note_id", noteId).pathParam("file_id", fileId).queryParams(queryParams).build(), PurchaseInvoiceNoteFile.class);
    }

    public ApiResponse<PurchaseInvoiceNoteFile> getPurchaseInvoiceNoteFile(
            String purchaseInvoiceId, String noteId, String fileId, Map<String, Object> queryParams) {
        return getPurchaseInvoiceNoteFile(purchaseInvoiceId, noteId, fileId, queryParams, null);
    }

    public ApiResponse<PurchaseInvoiceNoteFile> getPurchaseInvoiceNoteFile(String purchaseInvoiceId, String noteId, String fileId) {
        return getPurchaseInvoiceNoteFile(purchaseInvoiceId, noteId, fileId, null, null);
    }

    /**
     * One purchase credit note application through a purchase invoice:
     * {@code GET /purchase_invoices/{purchase_invoice_id}/purchase_credit_note_applications/{application_id}};
     * not checked against the invoice, so any application answers under any invoice.
     */
    public ApiResponse<PurchaseCreditNoteApplicationRead> getPurchaseInvoicePurchaseCreditNoteApplication(
            String purchaseInvoiceId, String applicationId, RequestOptions options) {
        requireValue(purchaseInvoiceId, "purchase_invoice_id");
        requireValue(applicationId, "application_id");
        return client.sendGetRequest(PurchaseInvoicesUrls.PURCHASE_INVOICE_PURCHASE_CREDIT_NOTE_APPLICATION,
                reopen(options).pathParam("purchase_invoice_id", purchaseInvoiceId).pathParam("application_id", applicationId).build(), PurchaseCreditNoteApplicationRead.class);
    }

    public ApiResponse<PurchaseCreditNoteApplicationRead> getPurchaseInvoicePurchaseCreditNoteApplication(
            String purchaseInvoiceId, String applicationId) {
        return getPurchaseInvoicePurchaseCreditNoteApplication(purchaseInvoiceId, applicationId, null);
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
