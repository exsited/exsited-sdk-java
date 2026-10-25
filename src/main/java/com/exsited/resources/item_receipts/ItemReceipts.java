package com.exsited.resources.item_receipts;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.item_receipts.item_receipts_dto.ItemReceiptsDto.ItemReceipt;
import com.exsited.resources.item_receipts.item_receipts_dto.ItemReceiptsDto.ItemReceiptList;
import com.exsited.resources.item_receipts.item_receipts_dto.ItemReceiptsDto.ItemReceiptUpdated;
import com.exsited.resources.item_receipts.item_receipts_dto.ItemReceiptsDto.PurchaseOrderItemReceipts;
import com.exsited.resources.item_receipts.item_receipts_dto.ItemReceiptsDto;
import java.util.Map;

/** Item receipt endpoints, under {@code /api/{api_version}}. */
public final class ItemReceipts implements BoundResource {

    private final ExsitedClient client;

    public ItemReceipts(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List item receipts: {@code GET /item_receipts}. */
    public ApiResponse<ItemReceiptList> getItemReceipts(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ItemReceiptsUrls.ITEM_RECEIPTS,
                reopen(options).queryParams(queryParams).build(), ItemReceiptList.class);
    }

    public ApiResponse<ItemReceiptList> getItemReceipts(
            Map<String, Object> queryParams) {
        return getItemReceipts(queryParams, null);
    }

    public ApiResponse<ItemReceiptList> getItemReceipts() {
        return getItemReceipts(null, null);
    }

    /** Create item receipt: {@code POST /item_receipts}. Recorded 201. */
    public ApiResponse<ItemReceiptsDto.ItemReceipts> createItemReceipt(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ItemReceiptsUrls.ITEM_RECEIPTS,
                reopen(options).jsonBody(payload).build(), ItemReceiptsDto.ItemReceipts.class);
    }

    public ApiResponse<ItemReceiptsDto.ItemReceipts> createItemReceipt(
            Object payload) {
        return createItemReceipt(payload, null);
    }

    /** Update item receipt: {@code PATCH /item_receipts/{item_receipt_id}}. Recorded 202. */
    public ApiResponse<ItemReceiptUpdated> updateItemReceipt(
            String itemReceiptId, Object payload, RequestOptions options) {
        requireValue(itemReceiptId, "item_receipt_id");
        requirePayload(payload);
        return client.sendPatchRequest(ItemReceiptsUrls.ITEM_RECEIPT,
                reopen(options).pathParam("item_receipt_id", itemReceiptId).jsonBody(payload).build(), ItemReceiptUpdated.class);
    }

    public ApiResponse<ItemReceiptUpdated> updateItemReceipt(
            String itemReceiptId, Object payload) {
        return updateItemReceipt(itemReceiptId, payload, null);
    }

    /** Get single: {@code GET /item_receipts/{item_receipt_id}}. */
    public ApiResponse<ItemReceipt> getItemReceipt(
            String itemReceiptId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(itemReceiptId, "item_receipt_id");
        return client.sendGetRequest(ItemReceiptsUrls.ITEM_RECEIPT,
                reopen(options).pathParam("item_receipt_id", itemReceiptId).queryParams(queryParams).build(), ItemReceipt.class);
    }

    public ApiResponse<ItemReceipt> getItemReceipt(
            String itemReceiptId, Map<String, Object> queryParams) {
        return getItemReceipt(itemReceiptId, queryParams, null);
    }

    public ApiResponse<ItemReceipt> getItemReceipt(String itemReceiptId) {
        return getItemReceipt(itemReceiptId, null, null);
    }

    /**
     * List purchase order item receipts:
     * {@code GET /purchase_orders/{purchase_order_id}/item_receipts}.
     */
    public ApiResponse<PurchaseOrderItemReceipts> getPurchaseOrderItemReceipts(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        return client.sendGetRequest(ItemReceiptsUrls.PURCHASE_ORDER_ITEM_RECEIPTS,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).queryParams(queryParams).build(), PurchaseOrderItemReceipts.class);
    }

    public ApiResponse<PurchaseOrderItemReceipts> getPurchaseOrderItemReceipts(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return getPurchaseOrderItemReceipts(purchaseOrderId, queryParams, null);
    }

    public ApiResponse<PurchaseOrderItemReceipts> getPurchaseOrderItemReceipts(String purchaseOrderId) {
        return getPurchaseOrderItemReceipts(purchaseOrderId, null, null);
    }

    /**
     * Delete item receipt: {@code DELETE /item_receipts/{item_receipt_id}}, by its {@code ir_} id;
     * refused {@code 400} {@code stock_committed} once the receipt is completed. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteItemReceipt(
            String itemReceiptId, RequestOptions options) {
        requireValue(itemReceiptId, "item_receipt_id");
        return client.sendDeleteRequest(ItemReceiptsUrls.ITEM_RECEIPT,
                reopen(options).pathParam("item_receipt_id", itemReceiptId).build(), null);
    }

    public ApiResponse<Dto> deleteItemReceipt(String itemReceiptId) {
        return deleteItemReceipt(itemReceiptId, null);
    }

    /**
     * Delete item receipt note: {@code DELETE /item_receipts/{item_receipt_id}/notes/{note_id}},
     * {@code ir_} and {@code note_} ids; no route lists or creates a receipt's notes. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteItemReceiptNote(
            String itemReceiptId, String noteId, RequestOptions options) {
        requireValue(itemReceiptId, "item_receipt_id");
        requireValue(noteId, "note_id");
        return client.sendDeleteRequest(ItemReceiptsUrls.ITEM_RECEIPT_NOTE,
                reopen(options).pathParam("item_receipt_id", itemReceiptId).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> deleteItemReceiptNote(String itemReceiptId, String noteId) {
        return deleteItemReceiptNote(itemReceiptId, noteId, null);
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
