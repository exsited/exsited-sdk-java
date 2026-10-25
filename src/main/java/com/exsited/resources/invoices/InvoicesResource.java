package com.exsited.resources.invoices;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.accounts.accounts_dto.AccountsDto.NoteFileList;
import com.exsited.resources.invoices.invoices_dto.InvoicesDto.Invoice;
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

/**
 * Forwards every call to {@link Invoices}.
 *
 * @deprecated {@link Invoices} is bound to a client itself and is what
 *     {@code client.invoices()} answers; use it instead.
 */
@Deprecated
public final class InvoicesResource implements BoundResource {

    private final Invoices invoices;

    public InvoicesResource(ExsitedClient client) {
        this.invoices = new Invoices(client);
    }

    @Override
    public ExsitedClient getClient() {
        return invoices.getClient();
    }

    public ApiResponse<InvoiceList> getInvoices(
            Map<String, Object> queryParams, RequestOptions options) {
        return invoices.getInvoices(queryParams, options);
    }

    public ApiResponse<InvoiceList> getInvoices(Map<String, Object> queryParams) {
        return invoices.getInvoices(queryParams);
    }

    public ApiResponse<Invoice> createInvoice(Object payload, RequestOptions options) {
        return invoices.createInvoice(payload, options);
    }

    public ApiResponse<Invoice> createInvoice(Object payload) {
        return invoices.createInvoice(payload);
    }

    public ApiResponse<Invoice> deleteInvoice(String invoiceId, RequestOptions options) {
        return invoices.deleteInvoice(invoiceId, options);
    }

    public ApiResponse<Invoice> deleteInvoice(String invoiceId) {
        return invoices.deleteInvoice(invoiceId);
    }

    public ApiResponse<Invoice> getInvoice(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return invoices.getInvoice(invoiceId, queryParams, options);
    }

    public ApiResponse<Invoice> getInvoice(String invoiceId, Map<String, Object> queryParams) {
        return invoices.getInvoice(invoiceId, queryParams);
    }

    public ApiResponse<Invoice> amendInvoice(
            String invoiceId, Object payload, RequestOptions options) {
        return invoices.amendInvoice(invoiceId, payload, options);
    }

    public ApiResponse<Invoice> amendInvoice(String invoiceId, Object payload) {
        return invoices.amendInvoice(invoiceId, payload);
    }

    public ApiResponse<Invoice> cancelInvoice(
            String invoiceId, Object payload, RequestOptions options) {
        return invoices.cancelInvoice(invoiceId, payload, options);
    }

    public ApiResponse<Invoice> cancelInvoice(String invoiceId, Object payload) {
        return invoices.cancelInvoice(invoiceId, payload);
    }

    public ApiResponse<Invoice> updateInvoiceInformation(
            String invoiceId, Object payload, RequestOptions options) {
        return invoices.updateInvoiceInformation(invoiceId, payload, options);
    }

    public ApiResponse<Invoice> updateInvoiceInformation(String invoiceId, Object payload) {
        return invoices.updateInvoiceInformation(invoiceId, payload);
    }

    public ApiResponse<Invoice> getInvoiceInformation(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return invoices.getInvoiceInformation(invoiceId, queryParams, options);
    }

    public ApiResponse<Invoice> getInvoiceInformation(
            String invoiceId, Map<String, Object> queryParams) {
        return invoices.getInvoiceInformation(invoiceId, queryParams);
    }

    public ApiResponse<ItemFulfillmentReadList> getInvoiceItemFulfillments(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return invoices.getInvoiceItemFulfillments(invoiceId, queryParams, options);
    }

    public ApiResponse<ItemFulfillmentReadList> getInvoiceItemFulfillments(
            String invoiceId, Map<String, Object> queryParams) {
        return invoices.getInvoiceItemFulfillments(invoiceId, queryParams);
    }

    public ApiResponse<InvoicesDto.InvoiceNoteList> getInvoiceNotes(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return invoices.getInvoiceNotes(invoiceId, queryParams, options);
    }

    public ApiResponse<InvoicesDto.InvoiceNoteList> getInvoiceNotes(
            String invoiceId, Map<String, Object> queryParams) {
        return invoices.getInvoiceNotes(invoiceId, queryParams);
    }

    public ApiResponse<InvoiceNote> createInvoiceNote(
            String invoiceId, String note, RequestOptions options) {
        return invoices.createInvoiceNote(invoiceId, note, options);
    }

    public ApiResponse<InvoiceNote> createInvoiceNote(String invoiceId, String note) {
        return invoices.createInvoiceNote(invoiceId, note);
    }

    public ApiResponse<InvoicePdf> getInvoicePdf(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return invoices.getInvoicePdf(invoiceId, queryParams, options);
    }

    public ApiResponse<InvoicePdf> getInvoicePdf(String invoiceId, Map<String, Object> queryParams) {
        return invoices.getInvoicePdf(invoiceId, queryParams);
    }

    public ApiResponse<Invoice> reactivateInvoice(
            String invoiceId, Object payload, RequestOptions options) {
        return invoices.reactivateInvoice(invoiceId, payload, options);
    }

    public ApiResponse<Invoice> reactivateInvoice(String invoiceId, Object payload) {
        return invoices.reactivateInvoice(invoiceId, payload);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisations> getInvoiceReturnMerchandiseAuthorisations(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return invoices.getInvoiceReturnMerchandiseAuthorisations(invoiceId, queryParams, options);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisations> getInvoiceReturnMerchandiseAuthorisations(
            String invoiceId, Map<String, Object> queryParams) {
        return invoices.getInvoiceReturnMerchandiseAuthorisations(invoiceId, queryParams);
    }

    public ApiResponse<InvoiceList> getOrderInvoices(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        return invoices.getOrderInvoices(orderId, queryParams, options);
    }

    public ApiResponse<InvoiceList> getOrderInvoices(String orderId, Map<String, Object> queryParams) {
        return invoices.getOrderInvoices(orderId, queryParams);
    }

    public ApiResponse<Invoice> createOrderInvoice(
            String orderId, Object payload, RequestOptions options) {
        return invoices.createOrderInvoice(orderId, payload, options);
    }

    public ApiResponse<Invoice> createOrderInvoice(String orderId, Object payload) {
        return invoices.createOrderInvoice(orderId, payload);
    }

    public ApiResponse<ItemFulfillmentRead> getInvoiceItemFulfillment(
            String invoiceId, String fulfillmentId, Map<String, Object> queryParams, RequestOptions options) {
        return invoices.getInvoiceItemFulfillment(invoiceId, fulfillmentId, queryParams, options);
    }

    public ApiResponse<ItemFulfillmentRead> getInvoiceItemFulfillment(
            String invoiceId, String fulfillmentId, Map<String, Object> queryParams) {
        return invoices.getInvoiceItemFulfillment(invoiceId, fulfillmentId, queryParams);
    }

    public ApiResponse<InvoiceNote> getInvoiceNote(
            String invoiceId, String invoiceNoteId, Map<String, Object> queryParams, RequestOptions options) {
        return invoices.getInvoiceNote(invoiceId, invoiceNoteId, queryParams, options);
    }

    public ApiResponse<InvoiceNote> getInvoiceNote(
            String invoiceId, String invoiceNoteId, Map<String, Object> queryParams) {
        return invoices.getInvoiceNote(invoiceId, invoiceNoteId, queryParams);
    }

    public ApiResponse<Dto> deleteInvoiceNote(
            String invoiceId, String invoiceNoteId, RequestOptions options) {
        return invoices.deleteInvoiceNote(invoiceId, invoiceNoteId, options);
    }

    public ApiResponse<Dto> deleteInvoiceNote(String invoiceId, String invoiceNoteId) {
        return invoices.deleteInvoiceNote(invoiceId, invoiceNoteId);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationRead> getInvoiceReturnMerchandiseAuthorisation(
            String invoiceId, String rmaId, Map<String, Object> queryParams, RequestOptions options) {
        return invoices.getInvoiceReturnMerchandiseAuthorisation(invoiceId, rmaId, queryParams, options);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationRead> getInvoiceReturnMerchandiseAuthorisation(
            String invoiceId, String rmaId, Map<String, Object> queryParams) {
        return invoices.getInvoiceReturnMerchandiseAuthorisation(invoiceId, rmaId, queryParams);
    }

    public ApiResponse<NoteFileList> getInvoiceNoteFiles(
            String invoiceId, String invoiceNoteId, Map<String, Object> queryParams, RequestOptions options) {
        return invoices.getInvoiceNoteFiles(invoiceId, invoiceNoteId, queryParams, options);
    }

    public ApiResponse<NoteFileList> getInvoiceNoteFiles(
            String invoiceId, String invoiceNoteId, Map<String, Object> queryParams) {
        return invoices.getInvoiceNoteFiles(invoiceId, invoiceNoteId, queryParams);
    }

    public ApiResponse<InvoiceNoteFile> getInvoiceNoteFile(
            String invoiceId, String invoiceNoteId, String invoiceFileId, Map<String, Object> queryParams, RequestOptions options) {
        return invoices.getInvoiceNoteFile(invoiceId, invoiceNoteId, invoiceFileId, queryParams, options);
    }

    public ApiResponse<InvoiceNoteFile> getInvoiceNoteFile(
            String invoiceId, String invoiceNoteId, String invoiceFileId, Map<String, Object> queryParams) {
        return invoices.getInvoiceNoteFile(invoiceId, invoiceNoteId, invoiceFileId, queryParams);
    }
}
