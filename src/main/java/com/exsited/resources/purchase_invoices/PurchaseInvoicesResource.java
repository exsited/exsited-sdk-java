package com.exsited.resources.purchase_invoices;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
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

/**
 * Forwards every call to {@link PurchaseInvoices}.
 *
 * @deprecated {@link PurchaseInvoices} is bound to a client itself and is what
 *     {@code client.purchaseInvoices()} answers; use it instead.
 */
@Deprecated
public final class PurchaseInvoicesResource implements BoundResource {

    private final PurchaseInvoices purchaseInvoices;

    public PurchaseInvoicesResource(ExsitedClient client) {
        this.purchaseInvoices = new PurchaseInvoices(client);
    }

    @Override
    public ExsitedClient getClient() {
        return purchaseInvoices.getClient();
    }

    public ApiResponse<PurchaseInvoiceList> getPurchaseInvoices(
            Map<String, Object> queryParams, RequestOptions options) {
        return purchaseInvoices.getPurchaseInvoices(queryParams, options);
    }

    public ApiResponse<PurchaseInvoiceList> getPurchaseInvoices(Map<String, Object> queryParams) {
        return purchaseInvoices.getPurchaseInvoices(queryParams);
    }

    public ApiResponse<PurchaseInvoiceRead> createPurchaseInvoice(Object payload, RequestOptions options) {
        return purchaseInvoices.createPurchaseInvoice(payload, options);
    }

    public ApiResponse<PurchaseInvoiceRead> createPurchaseInvoice(Object payload) {
        return purchaseInvoices.createPurchaseInvoice(payload);
    }

    public ApiResponse<PurchaseInvoicesNoteList> getPurchaseInvoicesNotes(
            Map<String, Object> queryParams, RequestOptions options) {
        return purchaseInvoices.getPurchaseInvoicesNotes(queryParams, options);
    }

    public ApiResponse<PurchaseInvoicesNoteList> getPurchaseInvoicesNotes(
            Map<String, Object> queryParams) {
        return purchaseInvoices.getPurchaseInvoicesNotes(queryParams);
    }

    public ApiResponse<Dto> deletePurchaseInvoice(
            String purchaseInvoiceId, RequestOptions options) {
        return purchaseInvoices.deletePurchaseInvoice(purchaseInvoiceId, options);
    }

    public ApiResponse<Dto> deletePurchaseInvoice(String purchaseInvoiceId) {
        return purchaseInvoices.deletePurchaseInvoice(purchaseInvoiceId);
    }

    public ApiResponse<PurchaseInvoiceRead> getPurchaseInvoice(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseInvoices.getPurchaseInvoice(purchaseInvoiceId, queryParams, options);
    }

    public ApiResponse<PurchaseInvoiceRead> getPurchaseInvoice(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return purchaseInvoices.getPurchaseInvoice(purchaseInvoiceId, queryParams);
    }

    public ApiResponse<PurchaseInvoiceRead> amendPurchaseInvoice(
            String purchaseInvoiceId, Object payload, RequestOptions options) {
        return purchaseInvoices.amendPurchaseInvoice(purchaseInvoiceId, payload, options);
    }

    public ApiResponse<PurchaseInvoiceRead> amendPurchaseInvoice(String purchaseInvoiceId, Object payload) {
        return purchaseInvoices.amendPurchaseInvoice(purchaseInvoiceId, payload);
    }

    public ApiResponse<PurchaseInvoiceCancel> cancelPurchaseInvoice(
            String purchaseInvoiceId, Object payload, RequestOptions options) {
        return purchaseInvoices.cancelPurchaseInvoice(purchaseInvoiceId, payload, options);
    }

    public ApiResponse<PurchaseInvoiceCancel> cancelPurchaseInvoice(
            String purchaseInvoiceId, Object payload) {
        return purchaseInvoices.cancelPurchaseInvoice(purchaseInvoiceId, payload);
    }

    public ApiResponse<PurchaseInvoice> getPurchaseInvoiceInformation(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseInvoices.getPurchaseInvoiceInformation(purchaseInvoiceId, queryParams, options);
    }

    public ApiResponse<PurchaseInvoice> getPurchaseInvoiceInformation(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return purchaseInvoices.getPurchaseInvoiceInformation(purchaseInvoiceId, queryParams);
    }

    public ApiResponse<PurchaseInvoiceInformation> updatePurchaseInvoiceInformation(
            String purchaseInvoiceId, Object payload, RequestOptions options) {
        return purchaseInvoices.updatePurchaseInvoiceInformation(purchaseInvoiceId, payload, options);
    }

    public ApiResponse<PurchaseInvoiceInformation> updatePurchaseInvoiceInformation(
            String purchaseInvoiceId, Object payload) {
        return purchaseInvoices.updatePurchaseInvoiceInformation(purchaseInvoiceId, payload);
    }

    public ApiResponse<PurchaseInvoiceLines> getPurchaseInvoiceLines(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseInvoices.getPurchaseInvoiceLines(purchaseInvoiceId, queryParams, options);
    }

    public ApiResponse<PurchaseInvoiceLines> getPurchaseInvoiceLines(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return purchaseInvoices.getPurchaseInvoiceLines(purchaseInvoiceId, queryParams);
    }

    public ApiResponse<PurchaseInvoiceNoteList> getPurchaseInvoiceNotes(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseInvoices.getPurchaseInvoiceNotes(purchaseInvoiceId, queryParams, options);
    }

    public ApiResponse<PurchaseInvoiceNoteList> getPurchaseInvoiceNotes(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return purchaseInvoices.getPurchaseInvoiceNotes(purchaseInvoiceId, queryParams);
    }

    public ApiResponse<PurchaseInvoiceNotes> createPurchaseInvoiceNote(
            String purchaseInvoiceId, String note, RequestOptions options) {
        return purchaseInvoices.createPurchaseInvoiceNote(purchaseInvoiceId, note, options);
    }

    public ApiResponse<PurchaseInvoiceNotes> createPurchaseInvoiceNote(
            String purchaseInvoiceId, String note) {
        return purchaseInvoices.createPurchaseInvoiceNote(purchaseInvoiceId, note);
    }

    public ApiResponse<Dto> getPurchaseInvoicePdf(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseInvoices.getPurchaseInvoicePdf(purchaseInvoiceId, queryParams, options);
    }

    public ApiResponse<Dto> getPurchaseInvoicePdf(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return purchaseInvoices.getPurchaseInvoicePdf(purchaseInvoiceId, queryParams);
    }

    public ApiResponse<AccountPurchaseInvoices> getPurchaseInvoicePurchaseCreditNoteApplications(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseInvoices.getPurchaseInvoicePurchaseCreditNoteApplications(purchaseInvoiceId, queryParams, options);
    }

    public ApiResponse<AccountPurchaseInvoices> getPurchaseInvoicePurchaseCreditNoteApplications(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return purchaseInvoices.getPurchaseInvoicePurchaseCreditNoteApplications(purchaseInvoiceId, queryParams);
    }

    public ApiResponse<PurchaseInvoicePurchasePaymentList> getPurchaseInvoicePurchasePayments(
            String purchaseInvoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseInvoices.getPurchaseInvoicePurchasePayments(purchaseInvoiceId, queryParams, options);
    }

    public ApiResponse<PurchaseInvoicePurchasePaymentList> getPurchaseInvoicePurchasePayments(
            String purchaseInvoiceId, Map<String, Object> queryParams) {
        return purchaseInvoices.getPurchaseInvoicePurchasePayments(purchaseInvoiceId, queryParams);
    }

    public ApiResponse<PurchaseInvoiceRead> reactivatePurchaseInvoice(
            String purchaseInvoiceId, Object payload, RequestOptions options) {
        return purchaseInvoices.reactivatePurchaseInvoice(purchaseInvoiceId, payload, options);
    }

    public ApiResponse<PurchaseInvoiceRead> reactivatePurchaseInvoice(String purchaseInvoiceId, Object payload) {
        return purchaseInvoices.reactivatePurchaseInvoice(purchaseInvoiceId, payload);
    }

    public ApiResponse<PurchaseInvoiceLine> getPurchaseInvoiceLine(
            String purchaseInvoiceId, String purchaseInvoiceLineUuid, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseInvoices.getPurchaseInvoiceLine(purchaseInvoiceId, purchaseInvoiceLineUuid, queryParams, options);
    }

    public ApiResponse<PurchaseInvoiceLine> getPurchaseInvoiceLine(
            String purchaseInvoiceId, String purchaseInvoiceLineUuid, Map<String, Object> queryParams) {
        return purchaseInvoices.getPurchaseInvoiceLine(purchaseInvoiceId, purchaseInvoiceLineUuid, queryParams);
    }

    public ApiResponse<PurchaseInvoiceNoteUpdated> updatePurchaseInvoiceNote(
            String purchaseInvoiceId, String noteId, String note, RequestOptions options) {
        return purchaseInvoices.updatePurchaseInvoiceNote(purchaseInvoiceId, noteId, note, options);
    }

    public ApiResponse<PurchaseInvoiceNoteUpdated> updatePurchaseInvoiceNote(
            String purchaseInvoiceId, String noteId, String note) {
        return purchaseInvoices.updatePurchaseInvoiceNote(purchaseInvoiceId, noteId, note);
    }

    public ApiResponse<PurchaseInvoiceNoteRead> getPurchaseInvoiceNote(
            String purchaseInvoiceId, String noteId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseInvoices.getPurchaseInvoiceNote(purchaseInvoiceId, noteId, queryParams, options);
    }

    public ApiResponse<PurchaseInvoiceNoteRead> getPurchaseInvoiceNote(
            String purchaseInvoiceId, String noteId, Map<String, Object> queryParams) {
        return purchaseInvoices.getPurchaseInvoiceNote(purchaseInvoiceId, noteId, queryParams);
    }

    public ApiResponse<Dto> deletePurchaseInvoiceNote(
            String purchaseInvoiceId, String noteId, RequestOptions options) {
        return purchaseInvoices.deletePurchaseInvoiceNote(purchaseInvoiceId, noteId, options);
    }

    public ApiResponse<Dto> deletePurchaseInvoiceNote(String purchaseInvoiceId, String noteId) {
        return purchaseInvoices.deletePurchaseInvoiceNote(purchaseInvoiceId, noteId);
    }

    public ApiResponse<PurchaseInvoiceNoteRead> getPurchaseInvoiceNoteFiles(
            String purchaseInvoiceId, String noteId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseInvoices.getPurchaseInvoiceNoteFiles(purchaseInvoiceId, noteId, queryParams, options);
    }

    public ApiResponse<PurchaseInvoiceNoteRead> getPurchaseInvoiceNoteFiles(
            String purchaseInvoiceId, String noteId, Map<String, Object> queryParams) {
        return purchaseInvoices.getPurchaseInvoiceNoteFiles(purchaseInvoiceId, noteId, queryParams);
    }

    public ApiResponse<PurchaseInvoiceNoteFile> getPurchaseInvoiceNoteFile(
            String purchaseInvoiceId, String noteId, String fileId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseInvoices.getPurchaseInvoiceNoteFile(purchaseInvoiceId, noteId, fileId, queryParams, options);
    }

    public ApiResponse<PurchaseInvoiceNoteFile> getPurchaseInvoiceNoteFile(
            String purchaseInvoiceId, String noteId, String fileId, Map<String, Object> queryParams) {
        return purchaseInvoices.getPurchaseInvoiceNoteFile(purchaseInvoiceId, noteId, fileId, queryParams);
    }
}
