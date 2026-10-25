package com.exsited.resources.refunds;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.refunds.refunds_dto.RefundsDto.Refund;
import com.exsited.resources.refunds.refunds_dto.RefundsDto.RefundList;
import com.exsited.resources.refunds.refunds_dto.RefundsDto.RefundUpdated;
import java.util.Map;

/**
 * Forwards every call to {@link Refunds}.
 *
 * @deprecated {@link Refunds} is bound to a client itself and is what
 *     {@code client.refunds()} answers; use it instead.
 */
@Deprecated
public final class RefundsResource implements BoundResource {

    private final Refunds refunds;

    public RefundsResource(ExsitedClient client) {
        this.refunds = new Refunds(client);
    }

    @Override
    public ExsitedClient getClient() {
        return refunds.getClient();
    }

    public ApiResponse<RefundList> getRefunds(
            Map<String, Object> queryParams, RequestOptions options) {
        return refunds.getRefunds(queryParams, options);
    }

    public ApiResponse<RefundList> getRefunds(Map<String, Object> queryParams) {
        return refunds.getRefunds(queryParams);
    }

    public ApiResponse<Dto> deleteRefund(String refundId, RequestOptions options) {
        return refunds.deleteRefund(refundId, options);
    }

    public ApiResponse<Dto> deleteRefund(String refundId) {
        return refunds.deleteRefund(refundId);
    }

    public ApiResponse<Refund> getRefund(
            String refundId, Map<String, Object> queryParams, RequestOptions options) {
        return refunds.getRefund(refundId, queryParams, options);
    }

    public ApiResponse<Refund> getRefund(String refundId, Map<String, Object> queryParams) {
        return refunds.getRefund(refundId, queryParams);
    }

    public ApiResponse<RefundUpdated> updateRefund(
            String refundId, Object payload, RequestOptions options) {
        return refunds.updateRefund(refundId, payload, options);
    }

    public ApiResponse<RefundUpdated> updateRefund(String refundId, Object payload) {
        return refunds.updateRefund(refundId, payload);
    }

    public ApiResponse<RefundUpdated> createCreditNoteRefund(
            String creditNoteId, Object payload, RequestOptions options) {
        return refunds.createCreditNoteRefund(creditNoteId, payload, options);
    }

    public ApiResponse<RefundUpdated> createCreditNoteRefund(String creditNoteId, Object payload) {
        return refunds.createCreditNoteRefund(creditNoteId, payload);
    }
}
