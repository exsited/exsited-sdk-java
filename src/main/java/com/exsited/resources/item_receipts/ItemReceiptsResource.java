package com.exsited.resources.item_receipts;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.item_receipts.item_receipts_dto.ItemReceiptsDto.ItemReceipt;
import com.exsited.resources.item_receipts.item_receipts_dto.ItemReceiptsDto.ItemReceiptList;
import com.exsited.resources.item_receipts.item_receipts_dto.ItemReceiptsDto.ItemReceiptUpdated;
import com.exsited.resources.item_receipts.item_receipts_dto.ItemReceiptsDto.PurchaseOrderItemReceipts;
import com.exsited.resources.item_receipts.item_receipts_dto.ItemReceiptsDto;
import java.util.Map;

/**
 * Forwards every call to {@link ItemReceipts}.
 *
 * @deprecated {@link ItemReceipts} is bound to a client itself and is what
 *     {@code client.itemReceipts()} answers; use it instead.
 */
@Deprecated
public final class ItemReceiptsResource implements BoundResource {

    private final ItemReceipts itemReceipts;

    public ItemReceiptsResource(ExsitedClient client) {
        this.itemReceipts = new ItemReceipts(client);
    }

    @Override
    public ExsitedClient getClient() {
        return itemReceipts.getClient();
    }

    public ApiResponse<ItemReceiptList> getItemReceipts(
            Map<String, Object> queryParams, RequestOptions options) {
        return itemReceipts.getItemReceipts(queryParams, options);
    }

    public ApiResponse<ItemReceiptList> getItemReceipts(Map<String, Object> queryParams) {
        return itemReceipts.getItemReceipts(queryParams);
    }

    public ApiResponse<ItemReceiptsDto.ItemReceipts> createItemReceipt(
            Object payload, RequestOptions options) {
        return itemReceipts.createItemReceipt(payload, options);
    }

    public ApiResponse<ItemReceiptsDto.ItemReceipts> createItemReceipt(Object payload) {
        return itemReceipts.createItemReceipt(payload);
    }

    public ApiResponse<ItemReceiptUpdated> updateItemReceipt(
            String itemReceiptId, Object payload, RequestOptions options) {
        return itemReceipts.updateItemReceipt(itemReceiptId, payload, options);
    }

    public ApiResponse<ItemReceiptUpdated> updateItemReceipt(String itemReceiptId, Object payload) {
        return itemReceipts.updateItemReceipt(itemReceiptId, payload);
    }

    public ApiResponse<ItemReceipt> getItemReceipt(
            String itemReceiptId, Map<String, Object> queryParams, RequestOptions options) {
        return itemReceipts.getItemReceipt(itemReceiptId, queryParams, options);
    }

    public ApiResponse<ItemReceipt> getItemReceipt(
            String itemReceiptId, Map<String, Object> queryParams) {
        return itemReceipts.getItemReceipt(itemReceiptId, queryParams);
    }

    public ApiResponse<PurchaseOrderItemReceipts> getPurchaseOrderItemReceipts(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        return itemReceipts.getPurchaseOrderItemReceipts(purchaseOrderId, queryParams, options);
    }

    public ApiResponse<PurchaseOrderItemReceipts> getPurchaseOrderItemReceipts(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return itemReceipts.getPurchaseOrderItemReceipts(purchaseOrderId, queryParams);
    }
}
