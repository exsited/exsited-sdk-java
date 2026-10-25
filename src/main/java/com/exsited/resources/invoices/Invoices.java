package com.exsited.resources.invoices;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.NoteFileList;
import com.exsited.resources.credit_note_applications.credit_note_applications_dto.CreditNoteApplicationsDto.CreditNoteApplicationRead;
import com.exsited.resources.invoices.invoices_dto.InvoicesDto.Invoice;
import com.exsited.resources.invoices.invoices_dto.InvoicesDto.InvoiceKpisRead;
import com.exsited.resources.invoices.invoices_dto.InvoicesDto.InvoiceLineRead;
import com.exsited.resources.invoices.invoices_dto.InvoicesDto.InvoiceLines;
import com.exsited.resources.invoices.invoices_dto.InvoicesDto.InvoiceList;
import com.exsited.resources.invoices.invoices_dto.InvoicesDto.InvoiceNote;
import com.exsited.resources.invoices.invoices_dto.InvoicesDto.InvoiceNoteFile;
import com.exsited.resources.invoices.invoices_dto.InvoicesDto.InvoicePdf;
import com.exsited.resources.invoices.invoices_dto.InvoicesDto;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto.ItemFulfillmentRead;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto.ItemFulfillmentReadList;
import com.exsited.resources.return_merchandise_authorisations.return_merchandise_authorisations_dto.ReturnMerchandiseAuthorisationsDto.InvoiceReturnMerchandiseAuthorisations;
import com.exsited.resources.return_merchandise_authorisations.return_merchandise_authorisations_dto.ReturnMerchandiseAuthorisationsDto.ReturnMerchandiseAuthorisationRead;
import java.util.Map;

/** Invoice endpoints, under {@code /api/{api_version}}. */
public final class Invoices implements BoundResource {

    private final ExsitedClient client;

    public Invoices(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List invoices: {@code GET /invoices}. */
    public ApiResponse<InvoiceList> getInvoices(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(InvoicesUrls.INVOICES,
                reopen(options).queryParams(queryParams).build(), InvoiceList.class);
    }

    public ApiResponse<InvoiceList> getInvoices(
            Map<String, Object> queryParams) {
        return getInvoices(queryParams, null);
    }

    public ApiResponse<InvoiceList> getInvoices() {
        return getInvoices(null, null);
    }

    /** Create an invoice: {@code POST /invoices}. Recorded 201. */
    public ApiResponse<Invoice> createInvoice(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(InvoicesUrls.INVOICES,
                reopen(options).jsonBody(payload).build(), Invoice.class);
    }

    public ApiResponse<Invoice> createInvoice(
            Object payload) {
        return createInvoice(payload, null);
    }

    /** Delete an invoice: {@code DELETE /invoices/{invoice_id}}. */
    public ApiResponse<Invoice> deleteInvoice(
            String invoiceId, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        return client.sendDeleteRequest(InvoicesUrls.INVOICE,
                reopen(options).pathParam("invoice_id", invoiceId).build(), Invoice.class);
    }

    public ApiResponse<Invoice> deleteInvoice(
            String invoiceId) {
        return deleteInvoice(invoiceId, null);
    }

    /** Retrieve one invoice: {@code GET /invoices/{invoice_id}}. */
    public ApiResponse<Invoice> getInvoice(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE,
                reopen(options).pathParam("invoice_id", invoiceId).queryParams(queryParams).build(), Invoice.class);
    }

    public ApiResponse<Invoice> getInvoice(
            String invoiceId, Map<String, Object> queryParams) {
        return getInvoice(invoiceId, queryParams, null);
    }

    public ApiResponse<Invoice> getInvoice(String invoiceId) {
        return getInvoice(invoiceId, null, null);
    }

    /** Amend an invoice: {@code POST /invoices/{invoice_id}/amend}. Recorded 201. */
    public ApiResponse<Invoice> amendInvoice(
            String invoiceId, Object payload, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requirePayload(payload);
        return client.sendPostRequest(InvoicesUrls.INVOICE_AMEND,
                reopen(options).pathParam("invoice_id", invoiceId).jsonBody(payload).build(), Invoice.class);
    }

    public ApiResponse<Invoice> amendInvoice(
            String invoiceId, Object payload) {
        return amendInvoice(invoiceId, payload, null);
    }

    /** Cancel an invoice: {@code POST /invoices/{invoice_id}/cancel}. Recorded 202. */
    public ApiResponse<Invoice> cancelInvoice(
            String invoiceId, Object payload, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requirePayload(payload);
        return client.sendPostRequest(InvoicesUrls.INVOICE_CANCEL,
                reopen(options).pathParam("invoice_id", invoiceId).jsonBody(payload).build(), Invoice.class);
    }

    public ApiResponse<Invoice> cancelInvoice(
            String invoiceId, Object payload) {
        return cancelInvoice(invoiceId, payload, null);
    }

    /** Update core information: {@code PATCH /invoices/{invoice_id}/information}. Recorded 202. */
    public ApiResponse<Invoice> updateInvoiceInformation(
            String invoiceId, Object payload, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requirePayload(payload);
        return client.sendPatchRequest(InvoicesUrls.INVOICE_INFORMATION,
                reopen(options).pathParam("invoice_id", invoiceId).jsonBody(payload).build(), Invoice.class);
    }

    public ApiResponse<Invoice> updateInvoiceInformation(
            String invoiceId, Object payload) {
        return updateInvoiceInformation(invoiceId, payload, null);
    }

    /** Retrieve an invoice's core information: {@code GET /invoices/{invoice_id}/information}. */
    public ApiResponse<Invoice> getInvoiceInformation(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_INFORMATION,
                reopen(options).pathParam("invoice_id", invoiceId).queryParams(queryParams).build(), Invoice.class);
    }

    public ApiResponse<Invoice> getInvoiceInformation(
            String invoiceId, Map<String, Object> queryParams) {
        return getInvoiceInformation(invoiceId, queryParams, null);
    }

    public ApiResponse<Invoice> getInvoiceInformation(String invoiceId) {
        return getInvoiceInformation(invoiceId, null, null);
    }

    /** List an invoice's fulfilments: {@code GET /invoices/{invoice_id}/item_fulfillments}. */
    public ApiResponse<ItemFulfillmentReadList> getInvoiceItemFulfillments(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_ITEM_FULFILLMENTS,
                reopen(options).pathParam("invoice_id", invoiceId).queryParams(queryParams).build(), ItemFulfillmentReadList.class);
    }

    public ApiResponse<ItemFulfillmentReadList> getInvoiceItemFulfillments(
            String invoiceId, Map<String, Object> queryParams) {
        return getInvoiceItemFulfillments(invoiceId, queryParams, null);
    }

    public ApiResponse<ItemFulfillmentReadList> getInvoiceItemFulfillments(String invoiceId) {
        return getInvoiceItemFulfillments(invoiceId, null, null);
    }

    /** List an invoice's notes, a list envelope: {@code GET /invoices/{invoice_id}/notes}. */
    public ApiResponse<InvoicesDto.InvoiceNoteList> getInvoiceNotes(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_NOTES,
                reopen(options).pathParam("invoice_id", invoiceId).queryParams(queryParams).build(),
                InvoicesDto.InvoiceNoteList.class);
    }

    public ApiResponse<InvoicesDto.InvoiceNoteList> getInvoiceNotes(
            String invoiceId, Map<String, Object> queryParams) {
        return getInvoiceNotes(invoiceId, queryParams, null);
    }

    public ApiResponse<InvoicesDto.InvoiceNoteList> getInvoiceNotes(String invoiceId) {
        return getInvoiceNotes(invoiceId, null, null);
    }

    /**
     * Attach a note to an invoice: {@code POST /invoices/{invoice_id}/notes},
     * the text as the {@code note} form field; files on the options make it multipart.
     * Any {@code files} are sent beside the text as a multipart body.
     */
    public ApiResponse<InvoiceNote> createInvoiceNote(
            String invoiceId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("invoice_id", invoiceId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(InvoicesUrls.INVOICE_NOTES,
                request.build(), InvoiceNote.class);
    }

    public ApiResponse<InvoiceNote> createInvoiceNote(
            String invoiceId, String note, RequestOptions options) {
        return createInvoiceNote(invoiceId, note, null, options);
    }

    public ApiResponse<InvoiceNote> createInvoiceNote(
            String invoiceId, String note) {
        return createInvoiceNote(invoiceId, note, null);
    }

    /** Ask for the invoice's PDF: {@code GET /invoices/{invoice_id}/pdf}. */
    public ApiResponse<InvoicePdf> getInvoicePdf(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_PDF,
                reopen(options).pathParam("invoice_id", invoiceId).queryParams(queryParams).build(), InvoicePdf.class);
    }

    public ApiResponse<InvoicePdf> getInvoicePdf(
            String invoiceId, Map<String, Object> queryParams) {
        return getInvoicePdf(invoiceId, queryParams, null);
    }

    public ApiResponse<InvoicePdf> getInvoicePdf(String invoiceId) {
        return getInvoicePdf(invoiceId, null, null);
    }

    /** Reactivate an invoice: {@code POST /invoices/{invoice_id}/reactivate}. Recorded 202. */
    public ApiResponse<Invoice> reactivateInvoice(
            String invoiceId, Object payload, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requirePayload(payload);
        return client.sendPostRequest(InvoicesUrls.INVOICE_REACTIVATE,
                reopen(options).pathParam("invoice_id", invoiceId).jsonBody(payload).build(), Invoice.class);
    }

    public ApiResponse<Invoice> reactivateInvoice(
            String invoiceId, Object payload) {
        return reactivateInvoice(invoiceId, payload, null);
    }

    /** {@code GET /invoices/{invoice_id}/return_merchandise_authorisations}. */
    public ApiResponse<InvoiceReturnMerchandiseAuthorisations> getInvoiceReturnMerchandiseAuthorisations(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_RETURN_MERCHANDISE_AUTHORISATIONS,
                reopen(options).pathParam("invoice_id", invoiceId).queryParams(queryParams).build(), InvoiceReturnMerchandiseAuthorisations.class);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisations> getInvoiceReturnMerchandiseAuthorisations(
            String invoiceId, Map<String, Object> queryParams) {
        return getInvoiceReturnMerchandiseAuthorisations(invoiceId, queryParams, null);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisations> getInvoiceReturnMerchandiseAuthorisations(String invoiceId) {
        return getInvoiceReturnMerchandiseAuthorisations(invoiceId, null, null);
    }

    /** List one order's invoices: {@code GET /orders/{order_id}/invoices}. */
    public ApiResponse<InvoiceList> getOrderInvoices(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(InvoicesUrls.ORDER_INVOICES,
                reopen(options).pathParam("order_id", orderId).queryParams(queryParams).build(), InvoiceList.class);
    }

    public ApiResponse<InvoiceList> getOrderInvoices(
            String orderId, Map<String, Object> queryParams) {
        return getOrderInvoices(orderId, queryParams, null);
    }

    public ApiResponse<InvoiceList> getOrderInvoices(String orderId) {
        return getOrderInvoices(orderId, null, null);
    }

    /** Raise an invoice on an order: {@code POST /orders/{order_id}/invoices}. Recorded 201. */
    public ApiResponse<Invoice> createOrderInvoice(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(InvoicesUrls.ORDER_INVOICES,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), Invoice.class);
    }

    public ApiResponse<Invoice> createOrderInvoice(
            String orderId, Object payload) {
        return createOrderInvoice(orderId, payload, null);
    }

    /** One fulfilment: {@code GET /invoices/{invoice_id}/item_fulfillments/{fulfillment_id}}. */
    public ApiResponse<ItemFulfillmentRead> getInvoiceItemFulfillment(
            String invoiceId, String fulfillmentId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(fulfillmentId, "fulfillment_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_ITEM_FULFILLMENT,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("fulfillment_id", fulfillmentId).queryParams(queryParams).build(), ItemFulfillmentRead.class);
    }

    public ApiResponse<ItemFulfillmentRead> getInvoiceItemFulfillment(
            String invoiceId, String fulfillmentId, Map<String, Object> queryParams) {
        return getInvoiceItemFulfillment(invoiceId, fulfillmentId, queryParams, null);
    }

    public ApiResponse<ItemFulfillmentRead> getInvoiceItemFulfillment(String invoiceId, String fulfillmentId) {
        return getInvoiceItemFulfillment(invoiceId, fulfillmentId, null, null);
    }

    /** Retrieve one note: {@code GET /invoices/{invoice_id}/notes/{invoice_note_id}}. */
    public ApiResponse<InvoiceNote> getInvoiceNote(
            String invoiceId, String invoiceNoteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(invoiceNoteId, "invoice_note_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_NOTE,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("invoice_note_id", invoiceNoteId).queryParams(queryParams).build(), InvoiceNote.class);
    }

    public ApiResponse<InvoiceNote> getInvoiceNote(
            String invoiceId, String invoiceNoteId, Map<String, Object> queryParams) {
        return getInvoiceNote(invoiceId, invoiceNoteId, queryParams, null);
    }

    public ApiResponse<InvoiceNote> getInvoiceNote(String invoiceId, String invoiceNoteId) {
        return getInvoiceNote(invoiceId, invoiceNoteId, null, null);
    }

    /**
     * Delete a note: {@code DELETE /invoices/{invoice_id}/notes/{invoice_note_id}}. Recorded 400.
     */
    public ApiResponse<Dto> deleteInvoiceNote(
            String invoiceId, String invoiceNoteId, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(invoiceNoteId, "invoice_note_id");
        return client.sendDeleteRequest(InvoicesUrls.INVOICE_NOTE,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("invoice_note_id", invoiceNoteId).build(), null);
    }

    public ApiResponse<Dto> deleteInvoiceNote(
            String invoiceId, String invoiceNoteId) {
        return deleteInvoiceNote(invoiceId, invoiceNoteId, null);
    }

    /** {@code GET /invoices/{invoice_id}/return_merchandise_authorisations/{rma_id}}. */
    public ApiResponse<ReturnMerchandiseAuthorisationRead> getInvoiceReturnMerchandiseAuthorisation(
            String invoiceId, String rmaId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(rmaId, "rma_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_RETURN_MERCHANDISE_AUTHORISATION,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("rma_id", rmaId).queryParams(queryParams).build(), ReturnMerchandiseAuthorisationRead.class);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationRead> getInvoiceReturnMerchandiseAuthorisation(
            String invoiceId, String rmaId, Map<String, Object> queryParams) {
        return getInvoiceReturnMerchandiseAuthorisation(invoiceId, rmaId, queryParams, null);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationRead> getInvoiceReturnMerchandiseAuthorisation(String invoiceId, String rmaId) {
        return getInvoiceReturnMerchandiseAuthorisation(invoiceId, rmaId, null, null);
    }

    /** A note's attachments: {@code GET /invoices/{invoice_id}/notes/{invoice_note_id}/files}. */
    public ApiResponse<NoteFileList> getInvoiceNoteFiles(
            String invoiceId, String invoiceNoteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(invoiceNoteId, "invoice_note_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_NOTE_FILES,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("invoice_note_id", invoiceNoteId).queryParams(queryParams).build(), NoteFileList.class);
    }

    public ApiResponse<NoteFileList> getInvoiceNoteFiles(
            String invoiceId, String invoiceNoteId, Map<String, Object> queryParams) {
        return getInvoiceNoteFiles(invoiceId, invoiceNoteId, queryParams, null);
    }

    public ApiResponse<NoteFileList> getInvoiceNoteFiles(String invoiceId, String invoiceNoteId) {
        return getInvoiceNoteFiles(invoiceId, invoiceNoteId, null, null);
    }

    /**
     * One attachment:
     * {@code GET /invoices/{invoice_id}/notes/{invoice_note_id}/files/{invoice_file_id}}.
     */
    public ApiResponse<InvoiceNoteFile> getInvoiceNoteFile(
            String invoiceId, String invoiceNoteId, String invoiceFileId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(invoiceNoteId, "invoice_note_id");
        requireValue(invoiceFileId, "invoice_file_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_NOTE_FILE,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("invoice_note_id", invoiceNoteId).pathParam("invoice_file_id", invoiceFileId).queryParams(queryParams).build(), InvoiceNoteFile.class);
    }

    public ApiResponse<InvoiceNoteFile> getInvoiceNoteFile(
            String invoiceId, String invoiceNoteId, String invoiceFileId, Map<String, Object> queryParams) {
        return getInvoiceNoteFile(invoiceId, invoiceNoteId, invoiceFileId, queryParams, null);
    }

    public ApiResponse<InvoiceNoteFile> getInvoiceNoteFile(String invoiceId, String invoiceNoteId, String invoiceFileId) {
        return getInvoiceNoteFile(invoiceId, invoiceNoteId, invoiceFileId, null, null);
    }

    /**
     * An invoice's KPI block: {@code GET /invoices/{invoice_id}/kpis}, under {@code invoice_kpis};
     * the same block {@code getInvoice} carries as {@code kpis}.
     */
    public ApiResponse<InvoiceKpisRead> getInvoiceKpis(
            String invoiceId, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_KPIS,
                reopen(options).pathParam("invoice_id", invoiceId).build(), InvoiceKpisRead.class);
    }

    public ApiResponse<InvoiceKpisRead> getInvoiceKpis(String invoiceId) {
        return getInvoiceKpis(invoiceId, null);
    }

    /**
     * An invoice's lines: {@code GET /invoices/{invoice_id}/lines}, under {@code invoice.lines};
     * not a paged listing.
     */
    public ApiResponse<InvoiceLines> getInvoiceLines(
            String invoiceId, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_LINES,
                reopen(options).pathParam("invoice_id", invoiceId).build(), InvoiceLines.class);
    }

    public ApiResponse<InvoiceLines> getInvoiceLines(String invoiceId) {
        return getInvoiceLines(invoiceId, null);
    }

    /**
     * One invoice line: {@code GET /invoices/{invoice_id}/lines/{line_id}}, under
     * {@code invoice.line}. The server ignores {@code line_id} and answers the invoice's first
     * line.
     */
    public ApiResponse<InvoiceLineRead> getInvoiceLine(
            String invoiceId, String lineId, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(lineId, "line_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_LINE,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("line_id", lineId).build(), InvoiceLineRead.class);
    }

    public ApiResponse<InvoiceLineRead> getInvoiceLine(String invoiceId, String lineId) {
        return getInvoiceLine(invoiceId, lineId, null);
    }

    /**
     * One credit note application through an invoice:
     * {@code GET /invoices/{invoice_id}/credit_note_applications/{credit_note_application_id}}; not
     * checked against the invoice, so any application answers under any invoice.
     */
    public ApiResponse<CreditNoteApplicationRead> getInvoiceCreditNoteApplication(
            String invoiceId, String creditNoteApplicationId, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(creditNoteApplicationId, "credit_note_application_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_CREDIT_NOTE_APPLICATION,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("credit_note_application_id", creditNoteApplicationId).build(), CreditNoteApplicationRead.class);
    }

    public ApiResponse<CreditNoteApplicationRead> getInvoiceCreditNoteApplication(
            String invoiceId, String creditNoteApplicationId) {
        return getInvoiceCreditNoteApplication(invoiceId, creditNoteApplicationId, null);
    }

    /**
     * The custom objects defined for invoices: {@code GET /invoices/{invoice_id}/custom_objects}; a
     * paged listing, under {@code data}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getInvoiceCustomObjects(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_CUSTOM_OBJECTS,
                reopen(options).pathParam("invoice_id", invoiceId).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getInvoiceCustomObjects(String invoiceId, Map<String, Object> queryParams) {
        return getInvoiceCustomObjects(invoiceId, queryParams, null);
    }

    public ApiResponse<Dto> getInvoiceCustomObjects(String invoiceId) {
        return getInvoiceCustomObjects(invoiceId, null, null);
    }

    /**
     * One custom object with this invoice's instances of it:
     * {@code GET /invoices/{invoice_id}/custom_objects/{custom_object_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> getInvoiceCustomObject(
            String invoiceId, String customObjectId, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(customObjectId, "custom_object_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_CUSTOM_OBJECT,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("custom_object_id", customObjectId).build(), null);
    }

    public ApiResponse<Dto> getInvoiceCustomObject(String invoiceId, String customObjectId) {
        return getInvoiceCustomObject(invoiceId, customObjectId, null);
    }

    /**
     * One instance of a custom object on an invoice:
     * {@code GET /invoices/{invoice_id}/custom_objects/{custom_object_id}/instances/{instance_id}}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getInvoiceCustomObjectInstance(
            String invoiceId, String customObjectId, String instanceId, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(customObjectId, "custom_object_id");
        requireValue(instanceId, "instance_id");
        return client.sendGetRequest(InvoicesUrls.INVOICE_CUSTOM_OBJECT_INSTANCE,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("custom_object_id", customObjectId).pathParam("instance_id", instanceId).build(), null);
    }

    public ApiResponse<Dto> getInvoiceCustomObjectInstance(
            String invoiceId, String customObjectId, String instanceId) {
        return getInvoiceCustomObjectInstance(invoiceId, customObjectId, instanceId, null);
    }

    /**
     * Delete one attachment: {@code DELETE /invoices/{invoice_id}/notes/{note_id}/files/{file_id}},
     * by the attachment's {@code uuid}; {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteInvoiceNoteFile(
            String invoiceId, String noteId, String fileId, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        requireValue(noteId, "invoice_note_id");
        requireValue(fileId, "invoice_file_id");
        return client.sendDeleteRequest(InvoicesUrls.INVOICE_NOTE_FILE,
                reopen(options).pathParam("invoice_id", invoiceId).pathParam("invoice_note_id", noteId).pathParam("invoice_file_id", fileId).build(), null);
    }

    public ApiResponse<Dto> deleteInvoiceNoteFile(String invoiceId, String noteId, String fileId) {
        return deleteInvoiceNoteFile(invoiceId, noteId, fileId, null);
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
