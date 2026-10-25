package com.exsited.resources.purchase_payments;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentList;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentNoteFile;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentNoteList;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentNoteRead;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentNotes;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentRead;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto.PurchasePaymentUpdated;
import com.exsited.resources.purchase_payments.purchase_payments_dto.PurchasePaymentsDto;
import java.util.Map;

/**
 * Forwards every call to {@link PurchasePayments}.
 *
 * @deprecated {@link PurchasePayments} is bound to a client itself and is what
 *     {@code client.purchasePayments()} answers; use it instead.
 */
@Deprecated
public final class PurchasePaymentsResource implements BoundResource {

    private final PurchasePayments purchasePayments;

    public PurchasePaymentsResource(ExsitedClient client) {
        this.purchasePayments = new PurchasePayments(client);
    }

    @Override
    public ExsitedClient getClient() {
        return purchasePayments.getClient();
    }

    public ApiResponse<PurchasePaymentList> getPurchasePayments(
            Map<String, Object> queryParams, RequestOptions options) {
        return purchasePayments.getPurchasePayments(queryParams, options);
    }

    public ApiResponse<PurchasePaymentList> getPurchasePayments(Map<String, Object> queryParams) {
        return purchasePayments.getPurchasePayments(queryParams);
    }

    public ApiResponse<PurchasePaymentsDto.PurchasePayments> createPurchasePayment(
            Object payload, RequestOptions options) {
        return purchasePayments.createPurchasePayment(payload, options);
    }

    public ApiResponse<PurchasePaymentsDto.PurchasePayments> createPurchasePayment(Object payload) {
        return purchasePayments.createPurchasePayment(payload);
    }

    public ApiResponse<Dto> deletePurchasePayment(
            String purchasePaymentId, RequestOptions options) {
        return purchasePayments.deletePurchasePayment(purchasePaymentId, options);
    }

    public ApiResponse<Dto> deletePurchasePayment(String purchasePaymentId) {
        return purchasePayments.deletePurchasePayment(purchasePaymentId);
    }

    public ApiResponse<PurchasePaymentRead> getPurchasePayment(
            String purchasePaymentId, Map<String, Object> queryParams, RequestOptions options) {
        return purchasePayments.getPurchasePayment(purchasePaymentId, queryParams, options);
    }

    public ApiResponse<PurchasePaymentRead> getPurchasePayment(
            String purchasePaymentId, Map<String, Object> queryParams) {
        return purchasePayments.getPurchasePayment(purchasePaymentId, queryParams);
    }

    public ApiResponse<PurchasePaymentUpdated> updatePurchasePayment(
            String purchasePaymentId, Object payload, RequestOptions options) {
        return purchasePayments.updatePurchasePayment(purchasePaymentId, payload, options);
    }

    public ApiResponse<PurchasePaymentUpdated> updatePurchasePayment(
            String purchasePaymentId, Object payload) {
        return purchasePayments.updatePurchasePayment(purchasePaymentId, payload);
    }

    public ApiResponse<PurchasePaymentsDto.PurchasePayments> createPurchaseInvoicePurchasePayment(
            String purchaseInvoiceId, Object payload, RequestOptions options) {
        return purchasePayments.createPurchaseInvoicePurchasePayment(purchaseInvoiceId, payload, options);
    }

    public ApiResponse<PurchasePaymentsDto.PurchasePayments> createPurchaseInvoicePurchasePayment(
            String purchaseInvoiceId, Object payload) {
        return purchasePayments.createPurchaseInvoicePurchasePayment(purchaseInvoiceId, payload);
    }

    public ApiResponse<PurchasePaymentNoteList> getPurchasePaymentNotes(
            String purchasePaymentId, Map<String, Object> queryParams, RequestOptions options) {
        return purchasePayments.getPurchasePaymentNotes(purchasePaymentId, queryParams, options);
    }

    public ApiResponse<PurchasePaymentNoteList> getPurchasePaymentNotes(
            String purchasePaymentId, Map<String, Object> queryParams) {
        return purchasePayments.getPurchasePaymentNotes(purchasePaymentId, queryParams);
    }

    public ApiResponse<PurchasePaymentNotes> createPurchasePaymentNote(
            String purchasePaymentId, String note, RequestOptions options) {
        return purchasePayments.createPurchasePaymentNote(purchasePaymentId, note, options);
    }

    public ApiResponse<PurchasePaymentNotes> createPurchasePaymentNote(
            String purchasePaymentId, String note) {
        return purchasePayments.createPurchasePaymentNote(purchasePaymentId, note);
    }

    public ApiResponse<PurchasePaymentNoteRead> getPurchasePaymentNote(
            String purchasePaymentId, String ppNoteId, Map<String, Object> queryParams, RequestOptions options) {
        return purchasePayments.getPurchasePaymentNote(purchasePaymentId, ppNoteId, queryParams, options);
    }

    public ApiResponse<PurchasePaymentNoteRead> getPurchasePaymentNote(
            String purchasePaymentId, String ppNoteId, Map<String, Object> queryParams) {
        return purchasePayments.getPurchasePaymentNote(purchasePaymentId, ppNoteId, queryParams);
    }

    public ApiResponse<Dto> deletePurchasePaymentNote(
            String purchasePaymentId, String ppNoteId, RequestOptions options) {
        return purchasePayments.deletePurchasePaymentNote(purchasePaymentId, ppNoteId, options);
    }

    public ApiResponse<Dto> deletePurchasePaymentNote(String purchasePaymentId, String ppNoteId) {
        return purchasePayments.deletePurchasePaymentNote(purchasePaymentId, ppNoteId);
    }

    public ApiResponse<PurchasePaymentNoteRead> getPurchasePaymentNoteFiles(
            String purchasePaymentId, String ppNoteId, Map<String, Object> queryParams, RequestOptions options) {
        return purchasePayments.getPurchasePaymentNoteFiles(purchasePaymentId, ppNoteId, queryParams, options);
    }

    public ApiResponse<PurchasePaymentNoteRead> getPurchasePaymentNoteFiles(
            String purchasePaymentId, String ppNoteId, Map<String, Object> queryParams) {
        return purchasePayments.getPurchasePaymentNoteFiles(purchasePaymentId, ppNoteId, queryParams);
    }

    public ApiResponse<PurchasePaymentNoteFile> getPurchasePaymentNoteFile(
            String purchasePaymentId, String ppNoteId, String fileId, Map<String, Object> queryParams, RequestOptions options) {
        return purchasePayments.getPurchasePaymentNoteFile(purchasePaymentId, ppNoteId, fileId, queryParams, options);
    }

    public ApiResponse<PurchasePaymentNoteFile> getPurchasePaymentNoteFile(
            String purchasePaymentId, String ppNoteId, String fileId, Map<String, Object> queryParams) {
        return purchasePayments.getPurchasePaymentNoteFile(purchasePaymentId, ppNoteId, fileId, queryParams);
    }
}
