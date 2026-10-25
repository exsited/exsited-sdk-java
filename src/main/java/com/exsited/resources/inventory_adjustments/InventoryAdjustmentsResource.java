package com.exsited.resources.inventory_adjustments;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.inventory_adjustments.inventory_adjustments_dto.InventoryAdjustmentsDto.InventoryAdjustment;
import com.exsited.resources.inventory_adjustments.inventory_adjustments_dto.InventoryAdjustmentsDto.InventoryAdjustmentList;
import java.util.Map;

/**
 * Forwards every call to {@link InventoryAdjustments}.
 *
 * @deprecated {@link InventoryAdjustments} is bound to a client itself and is what
 *     {@code client.inventoryAdjustments()} answers; use it instead.
 */
@Deprecated
public final class InventoryAdjustmentsResource implements BoundResource {

    private final InventoryAdjustments inventoryAdjustments;

    public InventoryAdjustmentsResource(ExsitedClient client) {
        this.inventoryAdjustments = new InventoryAdjustments(client);
    }

    @Override
    public ExsitedClient getClient() {
        return inventoryAdjustments.getClient();
    }

    public ApiResponse<InventoryAdjustmentList> getInventoryAdjustments(
            Map<String, Object> queryParams, RequestOptions options) {
        return inventoryAdjustments.getInventoryAdjustments(queryParams, options);
    }

    public ApiResponse<InventoryAdjustmentList> getInventoryAdjustments(
            Map<String, Object> queryParams) {
        return inventoryAdjustments.getInventoryAdjustments(queryParams);
    }

    public ApiResponse<InventoryAdjustment> getInventoryAdjustment(
            String inventoryAdjustmentId, Map<String, Object> queryParams, RequestOptions options) {
        return inventoryAdjustments.getInventoryAdjustment(inventoryAdjustmentId, queryParams, options);
    }

    public ApiResponse<InventoryAdjustment> getInventoryAdjustment(
            String inventoryAdjustmentId, Map<String, Object> queryParams) {
        return inventoryAdjustments.getInventoryAdjustment(inventoryAdjustmentId, queryParams);
    }
}
