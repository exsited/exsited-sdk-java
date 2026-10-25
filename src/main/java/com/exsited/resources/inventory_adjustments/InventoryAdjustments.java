package com.exsited.resources.inventory_adjustments;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.inventory_adjustments.inventory_adjustments_dto.InventoryAdjustmentsDto.InventoryAdjustment;
import com.exsited.resources.inventory_adjustments.inventory_adjustments_dto.InventoryAdjustmentsDto.InventoryAdjustmentList;
import java.util.Map;

/** Inventory adjustment endpoints, under {@code /api/{api_version}}. */
public final class InventoryAdjustments implements BoundResource {

    private final ExsitedClient client;

    public InventoryAdjustments(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List all: {@code GET /inventory_adjustments}. */
    public ApiResponse<InventoryAdjustmentList> getInventoryAdjustments(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(InventoryAdjustmentsUrls.INVENTORY_ADJUSTMENTS,
                reopen(options).queryParams(queryParams).build(), InventoryAdjustmentList.class);
    }

    public ApiResponse<InventoryAdjustmentList> getInventoryAdjustments(
            Map<String, Object> queryParams) {
        return getInventoryAdjustments(queryParams, null);
    }

    public ApiResponse<InventoryAdjustmentList> getInventoryAdjustments() {
        return getInventoryAdjustments(null, null);
    }

    /** Get single: {@code GET /inventory_adjustments/{inventory_adjustment_id}}. */
    public ApiResponse<InventoryAdjustment> getInventoryAdjustment(
            String inventoryAdjustmentId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(inventoryAdjustmentId, "inventory_adjustment_id");
        return client.sendGetRequest(InventoryAdjustmentsUrls.INVENTORY_ADJUSTMENT,
                reopen(options).pathParam("inventory_adjustment_id", inventoryAdjustmentId).queryParams(queryParams).build(), InventoryAdjustment.class);
    }

    public ApiResponse<InventoryAdjustment> getInventoryAdjustment(
            String inventoryAdjustmentId, Map<String, Object> queryParams) {
        return getInventoryAdjustment(inventoryAdjustmentId, queryParams, null);
    }

    public ApiResponse<InventoryAdjustment> getInventoryAdjustment(String inventoryAdjustmentId) {
        return getInventoryAdjustment(inventoryAdjustmentId, null, null);
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
