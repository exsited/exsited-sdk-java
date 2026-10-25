package com.exsited.resources.payments;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentList;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentNoteFile;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentNoteList;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentNoteRead;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentNotes;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentRead;
import com.exsited.resources.payments.payments_dto.PaymentsDto.PaymentsNoteList;
import java.util.Map;

/**
 * Forwards every call to {@link Payments}.
 *
 * @deprecated {@link Payments} is bound to a client itself and is what
 *     {@code client.payments()} answers; use it instead.
 */
@Deprecated
public final class PaymentsResource implements BoundResource {

    private final Payments payments;

    public PaymentsResource(ExsitedClient client) {
        this.payments = new Payments(client);
    }

    @Override
    public ExsitedClient getClient() {
        return payments.getClient();
    }

    public ApiResponse<PaymentList> getPayments(
            Map<String, Object> queryParams, RequestOptions options) {
        return payments.getPayments(queryParams, options);
    }

    public ApiResponse<PaymentList> getPayments(Map<String, Object> queryParams) {
        return payments.getPayments(queryParams);
    }

    public ApiResponse<PaymentsNoteList> getPaymentsNotes(
            Map<String, Object> queryParams, RequestOptions options) {
        return payments.getPaymentsNotes(queryParams, options);
    }

    public ApiResponse<PaymentsNoteList> getPaymentsNotes(Map<String, Object> queryParams) {
        return payments.getPaymentsNotes(queryParams);
    }

    public ApiResponse<Dto> deletePayment(String paymentId, RequestOptions options) {
        return payments.deletePayment(paymentId, options);
    }

    public ApiResponse<Dto> deletePayment(String paymentId) {
        return payments.deletePayment(paymentId);
    }

    public ApiResponse<PaymentRead> updatePayment(
            String paymentId, Object payload, RequestOptions options) {
        return payments.updatePayment(paymentId, payload, options);
    }

    public ApiResponse<PaymentRead> updatePayment(String paymentId, Object payload) {
        return payments.updatePayment(paymentId, payload);
    }

    public ApiResponse<PaymentRead> getPayment(
            String paymentId, Map<String, Object> queryParams, RequestOptions options) {
        return payments.getPayment(paymentId, queryParams, options);
    }

    public ApiResponse<PaymentRead> getPayment(String paymentId, Map<String, Object> queryParams) {
        return payments.getPayment(paymentId, queryParams);
    }

    public ApiResponse<PaymentList> getInvoicePayments(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return payments.getInvoicePayments(invoiceId, queryParams, options);
    }

    public ApiResponse<PaymentList> getInvoicePayments(
            String invoiceId, Map<String, Object> queryParams) {
        return payments.getInvoicePayments(invoiceId, queryParams);
    }

    public ApiResponse<PaymentList> getOrderPayments(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        return payments.getOrderPayments(orderId, queryParams, options);
    }

    public ApiResponse<PaymentList> getOrderPayments(
            String orderId, Map<String, Object> queryParams) {
        return payments.getOrderPayments(orderId, queryParams);
    }

    public ApiResponse<PaymentNoteList> getPaymentNotes(
            String paymentId, Map<String, Object> queryParams, RequestOptions options) {
        return payments.getPaymentNotes(paymentId, queryParams, options);
    }

    public ApiResponse<PaymentNoteList> getPaymentNotes(
            String paymentId, Map<String, Object> queryParams) {
        return payments.getPaymentNotes(paymentId, queryParams);
    }

    public ApiResponse<PaymentNotes> createPaymentNote(
            String paymentId, String note, RequestOptions options) {
        return payments.createPaymentNote(paymentId, note, options);
    }

    public ApiResponse<PaymentNotes> createPaymentNote(String paymentId, String note) {
        return payments.createPaymentNote(paymentId, note);
    }

    public ApiResponse<PaymentNoteRead> getPaymentNote(
            String paymentId, String paymentNoteId, Map<String, Object> queryParams, RequestOptions options) {
        return payments.getPaymentNote(paymentId, paymentNoteId, queryParams, options);
    }

    public ApiResponse<PaymentNoteRead> getPaymentNote(
            String paymentId, String paymentNoteId, Map<String, Object> queryParams) {
        return payments.getPaymentNote(paymentId, paymentNoteId, queryParams);
    }

    public ApiResponse<Dto> deletePaymentNote(
            String paymentId, String paymentNoteId, RequestOptions options) {
        return payments.deletePaymentNote(paymentId, paymentNoteId, options);
    }

    public ApiResponse<Dto> deletePaymentNote(String paymentId, String paymentNoteId) {
        return payments.deletePaymentNote(paymentId, paymentNoteId);
    }

    public ApiResponse<PaymentNoteFile> getPaymentNoteFile(
            String paymentId, String paymentNoteId, String paymentFileId, Map<String, Object> queryParams, RequestOptions options) {
        return payments.getPaymentNoteFile(paymentId, paymentNoteId, paymentFileId, queryParams, options);
    }

    public ApiResponse<PaymentNoteFile> getPaymentNoteFile(
            String paymentId, String paymentNoteId, String paymentFileId, Map<String, Object> queryParams) {
        return payments.getPaymentNoteFile(paymentId, paymentNoteId, paymentFileId, queryParams);
    }
}
