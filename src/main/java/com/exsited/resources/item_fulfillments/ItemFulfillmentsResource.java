package com.exsited.resources.item_fulfillments;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto.ItemFulfillmentList;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto.ItemFulfillmentNotes;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto.ItemFulfillmentRead;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto.ItemFulfillmentUpdated;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto;
import java.util.Map;

/**
 * Forwards every call to {@link ItemFulfillments}.
 *
 * @deprecated {@link ItemFulfillments} is bound to a client itself and is what
 *     {@code client.itemFulfillments()} answers; use it instead.
 */
@Deprecated
public final class ItemFulfillmentsResource implements BoundResource {

    private final ItemFulfillments itemFulfillments;

    public ItemFulfillmentsResource(ExsitedClient client) {
        this.itemFulfillments = new ItemFulfillments(client);
    }

    @Override
    public ExsitedClient getClient() {
        return itemFulfillments.getClient();
    }

    public ApiResponse<ItemFulfillmentList> getItemFulfillments(
            Map<String, Object> queryParams, RequestOptions options) {
        return itemFulfillments.getItemFulfillments(queryParams, options);
    }

    public ApiResponse<ItemFulfillmentList> getItemFulfillments(Map<String, Object> queryParams) {
        return itemFulfillments.getItemFulfillments(queryParams);
    }

    public ApiResponse<ItemFulfillmentsDto.ItemFulfillments> createItemFulfillment(
            Object payload, RequestOptions options) {
        return itemFulfillments.createItemFulfillment(payload, options);
    }

    public ApiResponse<ItemFulfillmentsDto.ItemFulfillments> createItemFulfillment(Object payload) {
        return itemFulfillments.createItemFulfillment(payload);
    }

    public ApiResponse<ItemFulfillmentRead> getItemFulfillment(
            String fulfillmentId, Map<String, Object> queryParams, RequestOptions options) {
        return itemFulfillments.getItemFulfillment(fulfillmentId, queryParams, options);
    }

    public ApiResponse<ItemFulfillmentRead> getItemFulfillment(
            String fulfillmentId, Map<String, Object> queryParams) {
        return itemFulfillments.getItemFulfillment(fulfillmentId, queryParams);
    }

    public ApiResponse<ItemFulfillmentUpdated> updateItemFulfillment(
            String fulfillmentId, Object payload, RequestOptions options) {
        return itemFulfillments.updateItemFulfillment(fulfillmentId, payload, options);
    }

    public ApiResponse<ItemFulfillmentUpdated> updateItemFulfillment(
            String fulfillmentId, Object payload) {
        return itemFulfillments.updateItemFulfillment(fulfillmentId, payload);
    }

    public ApiResponse<ItemFulfillmentNotes> createItemFulfillmentNote(
            String fulfillmentId, String note, RequestOptions options) {
        return itemFulfillments.createItemFulfillmentNote(fulfillmentId, note, options);
    }

    public ApiResponse<ItemFulfillmentNotes> createItemFulfillmentNote(String fulfillmentId, String note) {
        return itemFulfillments.createItemFulfillmentNote(fulfillmentId, note);
    }
}
