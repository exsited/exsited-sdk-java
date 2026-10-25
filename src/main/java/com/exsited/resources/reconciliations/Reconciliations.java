package com.exsited.resources.reconciliations;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import java.util.Map;

/** Reconciliation endpoints, under {@code /api/{api_version}/reconciliations}. */
public final class Reconciliations implements BoundResource {

    private final ExsitedClient client;

    public Reconciliations(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List the payments in a reconciliation:
     * {@code GET /reconciliations/{reconciliation_id}/sale_payments}; the uuid or the numeric id.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getReconciliationSalePayments(
            String reconciliationId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(reconciliationId, "reconciliation_id");
        return client.sendGetRequest(ReconciliationsUrls.RECONCILIATION_SALE_PAYMENTS,
                reopen(options).pathParam("reconciliation_id", reconciliationId).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getReconciliationSalePayments(
            String reconciliationId, Map<String, Object> queryParams) {
        return getReconciliationSalePayments(reconciliationId, queryParams, null);
    }

    public ApiResponse<Dto> getReconciliationSalePayments(String reconciliationId) {
        return getReconciliationSalePayments(reconciliationId, null, null);
    }

    /**
     * List the refunds in a reconciliation:
     * {@code GET /reconciliations/{reconciliation_id}/sale_refunds}; the uuid or the numeric id. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getReconciliationSaleRefunds(
            String reconciliationId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(reconciliationId, "reconciliation_id");
        return client.sendGetRequest(ReconciliationsUrls.RECONCILIATION_SALE_REFUNDS,
                reopen(options).pathParam("reconciliation_id", reconciliationId).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getReconciliationSaleRefunds(
            String reconciliationId, Map<String, Object> queryParams) {
        return getReconciliationSaleRefunds(reconciliationId, queryParams, null);
    }

    public ApiResponse<Dto> getReconciliationSaleRefunds(String reconciliationId) {
        return getReconciliationSaleRefunds(reconciliationId, null, null);
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
}
