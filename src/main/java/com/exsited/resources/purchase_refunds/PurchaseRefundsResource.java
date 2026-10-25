package com.exsited.resources.purchase_refunds;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.purchase_refunds.purchase_refunds_dto.PurchaseRefundsDto.PurchaseRefund;
import com.exsited.resources.purchase_refunds.purchase_refunds_dto.PurchaseRefundsDto;
import java.util.Map;

/**
 * Forwards every call to {@link PurchaseRefunds}.
 *
 * @deprecated {@link PurchaseRefunds} is bound to a client itself and is what
 *     {@code client.purchaseRefunds()} answers; use it instead.
 */
@Deprecated
public final class PurchaseRefundsResource implements BoundResource {

    private final PurchaseRefunds purchaseRefunds;

    public PurchaseRefundsResource(ExsitedClient client) {
        this.purchaseRefunds = new PurchaseRefunds(client);
    }

    @Override
    public ExsitedClient getClient() {
        return purchaseRefunds.getClient();
    }

    public ApiResponse<PurchaseRefundsDto.PurchaseRefunds> getPurchaseRefunds(
            Map<String, Object> queryParams, RequestOptions options) {
        return purchaseRefunds.getPurchaseRefunds(queryParams, options);
    }

    public ApiResponse<PurchaseRefundsDto.PurchaseRefunds> getPurchaseRefunds(
            Map<String, Object> queryParams) {
        return purchaseRefunds.getPurchaseRefunds(queryParams);
    }

    public ApiResponse<PurchaseRefund> getPurchaseRefund(
            String purchaseRefundId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseRefunds.getPurchaseRefund(purchaseRefundId, queryParams, options);
    }

    public ApiResponse<PurchaseRefund> getPurchaseRefund(
            String purchaseRefundId, Map<String, Object> queryParams) {
        return purchaseRefunds.getPurchaseRefund(purchaseRefundId, queryParams);
    }
}
