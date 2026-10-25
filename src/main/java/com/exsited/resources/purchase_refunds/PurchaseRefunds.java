package com.exsited.resources.purchase_refunds;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.purchase_refunds.purchase_refunds_dto.PurchaseRefundsDto.PurchaseRefund;
import com.exsited.resources.purchase_refunds.purchase_refunds_dto.PurchaseRefundsDto;
import java.util.Map;

/** Purchase refund endpoints, under {@code /api/{api_version}}. */
public final class PurchaseRefunds implements BoundResource {

    private final ExsitedClient client;

    public PurchaseRefunds(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List purchase refunds: {@code GET /purchase_refunds}. */
    public ApiResponse<PurchaseRefundsDto.PurchaseRefunds> getPurchaseRefunds(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(PurchaseRefundsUrls.PURCHASE_REFUNDS,
                reopen(options).queryParams(queryParams).build(), PurchaseRefundsDto.PurchaseRefunds.class);
    }

    public ApiResponse<PurchaseRefundsDto.PurchaseRefunds> getPurchaseRefunds(
            Map<String, Object> queryParams) {
        return getPurchaseRefunds(queryParams, null);
    }

    public ApiResponse<PurchaseRefundsDto.PurchaseRefunds> getPurchaseRefunds() {
        return getPurchaseRefunds(null, null);
    }

    /** Get single: {@code GET /purchase_refunds/{purchase_refund_id}}. */
    public ApiResponse<PurchaseRefund> getPurchaseRefund(
            String purchaseRefundId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseRefundId, "purchase_refund_id");
        return client.sendGetRequest(PurchaseRefundsUrls.PURCHASE_REFUND,
                reopen(options).pathParam("purchase_refund_id", purchaseRefundId).queryParams(queryParams).build(), PurchaseRefund.class);
    }

    public ApiResponse<PurchaseRefund> getPurchaseRefund(
            String purchaseRefundId, Map<String, Object> queryParams) {
        return getPurchaseRefund(purchaseRefundId, queryParams, null);
    }

    public ApiResponse<PurchaseRefund> getPurchaseRefund(String purchaseRefundId) {
        return getPurchaseRefund(purchaseRefundId, null, null);
    }

    /**
     * Refund from a purchase credit note:
     * {@code POST /purchase_credit_notes/{purchase_credit_note_id}/purchase_refunds}; {@code 201},
     * as {@code getPurchaseRefund} reads it. {@code payment_method} is the supplier's method
     * reference.
     */
    public ApiResponse<PurchaseRefund> createPurchaseCreditNotePurchaseRefund(
            String purchaseCreditNoteId, Object payload, RequestOptions options) {
        requireValue(purchaseCreditNoteId, "purchase_credit_note_id");
        requirePayload(payload);
        return client.sendPostRequest(PurchaseRefundsUrls.PURCHASE_CREDIT_NOTE_PURCHASE_REFUNDS,
                reopen(options).pathParam("purchase_credit_note_id", purchaseCreditNoteId).jsonBody(payload).build(), PurchaseRefund.class);
    }

    public ApiResponse<PurchaseRefund> createPurchaseCreditNotePurchaseRefund(
            String purchaseCreditNoteId, Object payload) {
        return createPurchaseCreditNotePurchaseRefund(purchaseCreditNoteId, payload, null);
    }

    /**
     * Edit a refund's reference, note, date or attributes:
     * {@code PATCH /purchase_refunds/{purchase_refund_id}}, wrapped in {@code purchase_refund}; the
     * amount cannot change.
     */
    public ApiResponse<PurchaseRefund> updatePurchaseRefund(
            String purchaseRefundId, Object payload, RequestOptions options) {
        requireValue(purchaseRefundId, "purchase_refund_id");
        requirePayload(payload);
        return client.sendPatchRequest(PurchaseRefundsUrls.PURCHASE_REFUND,
                reopen(options).pathParam("purchase_refund_id", purchaseRefundId).jsonBody(payload).build(), PurchaseRefund.class);
    }

    public ApiResponse<PurchaseRefund> updatePurchaseRefund(String purchaseRefundId, Object payload) {
        return updatePurchaseRefund(purchaseRefundId, payload, null);
    }

    /**
     * Delete a refund: {@code DELETE /purchase_refunds/{purchase_refund_id}}, by its code;
     * {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deletePurchaseRefund(
            String purchaseRefundId, RequestOptions options) {
        requireValue(purchaseRefundId, "purchase_refund_id");
        return client.sendDeleteRequest(PurchaseRefundsUrls.PURCHASE_REFUND,
                reopen(options).pathParam("purchase_refund_id", purchaseRefundId).build(), null);
    }

    public ApiResponse<Dto> deletePurchaseRefund(String purchaseRefundId) {
        return deletePurchaseRefund(purchaseRefundId, null);
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
