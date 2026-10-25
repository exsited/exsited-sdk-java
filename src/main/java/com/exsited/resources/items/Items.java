package com.exsited.resources.items;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.http.QueryParams;
import com.exsited.resources.items.items_dto.ItemsDto.InventoryAdjustmentList;
import com.exsited.resources.items.items_dto.ItemsDto.Item;
import com.exsited.resources.items.items_dto.ItemsDto.ItemInventoriesView;
import com.exsited.resources.items.items_dto.ItemsDto.ItemList;
import com.exsited.resources.items.items_dto.ItemsDto.ItemNoteFile;
import com.exsited.resources.items.items_dto.ItemsDto.ItemPurchaseView;
import com.exsited.resources.items.items_dto.ItemsDto.ItemSaleView;
import com.exsited.resources.items.items_dto.ItemsDto.NoteFileList;
import com.exsited.resources.items.items_dto.ItemsDto.NoteList;
import com.exsited.resources.items.items_dto.ItemsDto.NoteView;
import java.util.Map;

/** Item endpoints, under {@code /api/{api_version}/items}. */
public final class Items implements BoundResource {

    private final ExsitedClient client;

    public Items(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List items; {@link #sortParams} sorts, {@link #itemFilterParam} or {@code filter} filters.
     */
    public ApiResponse<ItemList> getItems(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEMS, reopen(options).queryParams(queryParams).build(), ItemList.class);
    }

    public ApiResponse<ItemList> getItems(Map<String, Object> queryParams) {
        return getItems(queryParams, null);
    }

    public ApiResponse<ItemList> getItems() {
        return getItems(null, null);
    }

    /** One item by its {@code id} ({@code "ITEM-0001"}), not the {@code uuid} or an integer id. */
    public ApiResponse<Item> getItem(
            String itemId, RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM, reopen(options).pathParam("item_id", itemId).build(), Item.class);
    }

    public ApiResponse<Item> getItem(String itemId) {
        return getItem(itemId, null);
    }

    /** Retrieve an item's core information. Pairs with {@link #updateItemInformation}. */
    public ApiResponse<Item> getItemInformation(
            String itemId, RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM_INFORMATION,
                reopen(options).pathParam("item_id", itemId).build(), Item.class);
    }

    public ApiResponse<Item> getItemInformation(String itemId) {
        return getItemInformation(itemId, null);
    }

    /** Create an item from {@code type} and {@code name}; an unknown {@code group} is a 400. */
    public ApiResponse<Item> createItem(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ItemsUrls.ITEMS, reopen(options).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> createItem(Object payload) {
        return createItem(payload, null);
    }

    /** Update an item's core information. Partial: send only what changes. */
    public ApiResponse<Item> updateItemInformation(
            String itemId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(ItemsUrls.ITEM_INFORMATION,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> updateItemInformation(
            String itemId, Object payload) {
        return updateItemInformation(itemId, payload, null);
    }

    /** Deactivate an item. Takes no body. The one recorded call answered 500. */
    public ApiResponse<Item> deactivateItem(
            String itemId, RequestOptions options) {
        return client.sendPostRequest(ItemsUrls.ITEM_DEACTIVATE,
                reopen(options).pathParam("item_id", itemId).build(), Item.class);
    }

    public ApiResponse<Item> deactivateItem(String itemId) {
        return deactivateItem(itemId, null);
    }

    /** Reactivate a deactivated item. Takes no body. The one recorded call answered 500. */
    public ApiResponse<Item> reactivateItem(
            String itemId, RequestOptions options) {
        return client.sendPostRequest(ItemsUrls.ITEM_REACTIVATE,
                reopen(options).pathParam("item_id", itemId).build(), Item.class);
    }

    public ApiResponse<Item> reactivateItem(String itemId) {
        return reactivateItem(itemId, null);
    }

    /** Delete an item; a 409 means an order references it, and is not retryable. */
    public ApiResponse<Dto> deleteItem(
            String itemId, RequestOptions options) {
        return client.sendDeleteRequest(ItemsUrls.ITEM, reopen(options).pathParam("item_id", itemId).build(), null);
    }

    public ApiResponse<Dto> deleteItem(String itemId) {
        return deleteItem(itemId, null);
    }

    /** Set the image, {@code image_base64} at the top level with no envelope. Recorded 500. */
    public ApiResponse<Item> uploadItemImage(
            String itemId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ItemsUrls.ITEM_IMAGE,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> uploadItemImage(
            String itemId, Object payload) {
        return uploadItemImage(itemId, payload, null);
    }

    /** An item's sale configuration: a top-level stub with an integer {@code id}. */
    public ApiResponse<ItemSaleView> getItemSale(
            String itemId, RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM_SALE,
                reopen(options).pathParam("item_id", itemId).build(), ItemSaleView.class);
    }

    public ApiResponse<ItemSaleView> getItemSale(String itemId) {
        return getItemSale(itemId, null);
    }

    /** Create an item's sale configuration; 409 {@code already_exists} when it has one. */
    public ApiResponse<Item> createItemSale(
            String itemId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ItemsUrls.ITEM_SALE,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> createItemSale(
            String itemId, Object payload) {
        return createItemSale(itemId, payload, null);
    }

    /** Update an item's sale configuration. {@code {"item": {"sale": {...}}}}, partial. */
    public ApiResponse<Item> updateItemSale(
            String itemId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(ItemsUrls.ITEM_SALE,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> updateItemSale(
            String itemId, Object payload) {
        return updateItemSale(itemId, payload, null);
    }

    /** Retrieve an item's purchase configuration. A <b>stub</b>, as the sale read is. */
    public ApiResponse<ItemPurchaseView> getItemPurchase(
            String itemId, RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM_PURCHASE,
                reopen(options).pathParam("item_id", itemId).build(), ItemPurchaseView.class);
    }

    public ApiResponse<ItemPurchaseView> getItemPurchase(
            String itemId) {
        return getItemPurchase(itemId, null);
    }

    /** Create an item's purchase configuration. Answers 409 already_exists if one exists. */
    public ApiResponse<Item> createItemPurchase(
            String itemId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ItemsUrls.ITEM_PURCHASE,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> createItemPurchase(
            String itemId, Object payload) {
        return createItemPurchase(itemId, payload, null);
    }

    /** Update an item's purchase configuration. */
    public ApiResponse<Item> updateItemPurchase(
            String itemId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(ItemsUrls.ITEM_PURCHASE,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> updateItemPurchase(
            String itemId, Object payload) {
        return updateItemPurchase(itemId, payload, null);
    }

    /** Inventory configuration and stock: a stub, integer quantities beside string twins. */
    public ApiResponse<ItemInventoriesView> getItemInventories(
            String itemId, RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM_INVENTORIES,
                reopen(options).pathParam("item_id", itemId).build(), ItemInventoriesView.class);
    }

    public ApiResponse<ItemInventoriesView> getItemInventories(
            String itemId) {
        return getItemInventories(itemId, null);
    }

    /**
     * Update inventory configuration; the write's {@code warehouse_enabled} is the read's
     * {@code enable_warehouse_management}, and disabling it with stock is a 400.
     */
    public ApiResponse<Item> updateItemInventories(
            String itemId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(ItemsUrls.ITEM_INVENTORIES,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> updateItemInventories(
            String itemId, Object payload) {
        return updateItemInventories(itemId, payload, null);
    }

    /** Add stock; the warehouse is {@code name} here, {@code warehouse} on reads. Recorded 400. */
    public ApiResponse<Item> addItemInventory(
            String itemId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ItemsUrls.ITEM_INVENTORY_ADD,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> addItemInventory(
            String itemId, Object payload) {
        return addItemInventory(itemId, payload, null);
    }

    /** Inventory movements; every quantity is a string, unlike {@link #getItemInventories}. */
    public ApiResponse<InventoryAdjustmentList> getItemInventoryAdjustments(
            String itemId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM_INVENTORY_ADJUSTMENTS,
                reopen(options).pathParam("item_id", itemId).queryParams(queryParams).build(),
                InventoryAdjustmentList.class);
    }

    public ApiResponse<InventoryAdjustmentList> getItemInventoryAdjustments(
            String itemId, Map<String, Object> queryParams) {
        return getItemInventoryAdjustments(itemId, queryParams, null);
    }

    public ApiResponse<InventoryAdjustmentList> getItemInventoryAdjustments(String itemId) {
        return getItemInventoryAdjustments(itemId, null, null);
    }

    /** One warehouse's stock for an item, by the warehouse's name, not an id. */
    public ApiResponse<ItemInventoriesView> getItemWarehouseInventories(
            String itemId, String warehouseName,
            RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM_WAREHOUSE_INVENTORIES,
                reopen(options).pathParam("item_id", itemId)
                        .pathParam("warehouse_name", warehouseName).build(), ItemInventoriesView.class);
    }

    public ApiResponse<ItemInventoriesView> getItemWarehouseInventories(
            String itemId, String warehouseName) {
        return getItemWarehouseInventories(itemId, warehouseName, null);
    }

    /** Set a bundle's components: a bare {@code bundle_details} list. */
    public ApiResponse<Item> updateItemBundleDetails(
            String itemId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(ItemsUrls.ITEM_BUNDLE_DETAILS,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> updateItemBundleDetails(
            String itemId, Object payload) {
        return updateItemBundleDetails(itemId, payload, null);
    }

    /**
     * Configure variations: {@code configure_variations} on a parent, {@code parent_item_id} and
     * {@code use_variations} on a child. Both recorded calls answered 400.
     */
    public ApiResponse<Item> updateItemVariations(
            String itemId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(ItemsUrls.ITEM_VARIATIONS,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> updateItemVariations(
            String itemId, Object payload) {
        return updateItemVariations(itemId, payload, null);
    }

    /** List the members of an item family. Each carries item_id and item_name only. */
    public ApiResponse<Item> getItemFamilyDetails(
            String itemId, RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM_FAMILY_DETAILS,
                reopen(options).pathParam("item_id", itemId).build(), Item.class);
    }

    public ApiResponse<Item> getItemFamilyDetails(String itemId) {
        return getItemFamilyDetails(itemId, null);
    }

    /** Add family members under {@code family_details.add}. Recorded 405. */
    public ApiResponse<Item> createItemFamilyDetails(
            String itemId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ItemsUrls.ITEM_FAMILY_DETAILS,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> createItemFamilyDetails(
            String itemId, Object payload) {
        return createItemFamilyDetails(itemId, payload, null);
    }

    /** List an item's packages. */
    public ApiResponse<Item> getItemPackageDetails(
            String itemId, RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM_PACKAGE_DETAILS,
                reopen(options).pathParam("item_id", itemId).build(), Item.class);
    }

    public ApiResponse<Item> getItemPackageDetails(String itemId) {
        return getItemPackageDetails(itemId, null);
    }

    /** One package by its {@code uuid}, as {@code package_details[].uuid} carries it. */
    public ApiResponse<Item> getItemPackageDetail(
            String itemId, String packageDetailId,
            RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM_PACKAGE_DETAIL,
                reopen(options).pathParam("item_id", itemId)
                        .pathParam("package_detail_id", packageDetailId).build(), Item.class);
    }

    public ApiResponse<Item> getItemPackageDetail(
            String itemId, String packageDetailId) {
        return getItemPackageDetail(itemId, packageDetailId, null);
    }

    /** Add a package to an item. The recorded call answered 405 method_not_allowed. */
    public ApiResponse<Item> createItemPackageDetails(
            String itemId, Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ItemsUrls.ITEM_PACKAGE_DETAILS,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> createItemPackageDetails(
            String itemId, Object payload) {
        return createItemPackageDetails(itemId, payload, null);
    }

    /** Delete one package. */
    public ApiResponse<Dto> deleteItemPackageDetail(
            String itemId, String packageDetailId,
            RequestOptions options) {
        return client.sendDeleteRequest(ItemsUrls.ITEM_PACKAGE_DETAIL,
                reopen(options).pathParam("item_id", itemId)
                        .pathParam("package_detail_id", packageDetailId).build(), null);
    }

    public ApiResponse<Dto> deleteItemPackageDetail(
            String itemId, String packageDetailId) {
        return deleteItemPackageDetail(itemId, packageDetailId, null);
    }

    /** List an item's notes. The list envelope, as on an account. */
    public ApiResponse<NoteList> getItemNotes(
            String itemId, Map<String, Object> queryParams,
            RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM_NOTES,
                reopen(options).pathParam("item_id", itemId).queryParams(queryParams).build(),
                NoteList.class);
    }

    public ApiResponse<NoteList> getItemNotes(
            String itemId, Map<String, Object> queryParams) {
        return getItemNotes(itemId, queryParams, null);
    }

    public ApiResponse<NoteList> getItemNotes(String itemId) {
        return getItemNotes(itemId, null, null);
    }

    /** Retrieve one note. Answers {@code {"note": {...}}}. */
    public ApiResponse<NoteView> getItemNote(
            String itemId, String noteId, RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM_NOTE,
                reopen(options).pathParam("item_id", itemId).pathParam("note_id", noteId).build(),
                NoteView.class);
    }

    public ApiResponse<NoteView> getItemNote(
            String itemId, String noteId) {
        return getItemNote(itemId, noteId, null);
    }

    /**
     * Attach a note, form-encoded as the {@code note} field, as on an account.
     * Any {@code files} are sent beside the text as a multipart body.
     */
    public ApiResponse<NoteView> createItemNote(
            String itemId, String note, Map<String, Object> files, RequestOptions options) {
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("item_id", itemId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(ItemsUrls.ITEM_NOTES,
                request.build(), NoteView.class);
    }

    public ApiResponse<NoteView> createItemNote(
            String itemId, String note, RequestOptions options) {
        return createItemNote(itemId, note, null, options);
    }

    public ApiResponse<NoteView> createItemNote(
            String itemId, String note) {
        return createItemNote(itemId, note, null);
    }

    /**
     * Update a note's text, form-encoded; a {@code noteId} that is not a UUID is a 400.
     * Any {@code files} are sent beside the text as a multipart body.
     */
    public ApiResponse<NoteView> updateItemNote(
            String itemId, String noteId, String note, Map<String, Object> files, RequestOptions options) {
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("item_id", itemId).pathParam("note_id", noteId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPatchRequest(ItemsUrls.ITEM_NOTE,
                request.build(), NoteView.class);
    }

    public ApiResponse<NoteView> updateItemNote(
            String itemId, String noteId, String note, RequestOptions options) {
        return updateItemNote(itemId, noteId, note, null, options);
    }

    public ApiResponse<NoteView> updateItemNote(
            String itemId, String noteId, String note) {
        return updateItemNote(itemId, noteId, note, null);
    }

    /** Delete one note. Answers {@code 400 "Invalid note_id"} when the id is not one. */
    public ApiResponse<Dto> deleteItemNote(
            String itemId, String noteId, RequestOptions options) {
        return client.sendDeleteRequest(ItemsUrls.ITEM_NOTE,
                reopen(options).pathParam("item_id", itemId).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> deleteItemNote(
            String itemId, String noteId) {
        return deleteItemNote(itemId, noteId, null);
    }

    /** A note's attachments; unverified, the recorded call answered 404. */
    public ApiResponse<NoteFileList> getItemNoteFiles(
            String itemId, String noteId, RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM_NOTE_FILES,
                reopen(options).pathParam("item_id", itemId).pathParam("note_id", noteId).build(),
                NoteFileList.class);
    }

    public ApiResponse<NoteFileList> getItemNoteFiles(
            String itemId, String noteId) {
        return getItemNoteFiles(itemId, noteId, null);
    }

    /** One attachment, perhaps bytes in {@code getMap()}; unverified, recorded 400. */
    public ApiResponse<ItemNoteFile> getItemNoteFile(
            String itemId, String noteId, String fileId,
            RequestOptions options) {
        return client.sendGetRequest(ItemsUrls.ITEM_NOTE_FILE,
                reopen(options).pathParam("item_id", itemId).pathParam("note_id", noteId)
                        .pathParam("file_id", fileId).build(), ItemNoteFile.class);
    }

    public ApiResponse<ItemNoteFile> getItemNoteFile(
            String itemId, String noteId, String fileId) {
        return getItemNoteFile(itemId, noteId, fileId, null);
    }

    /** A {@code filter_*} name, refusing one in {@link ItemsUrls#DEPRECATED_FILTERS}. */
    public static String itemFilterParam(String field, String operator) {
        if (ItemsUrls.DEPRECATED_FILTERS.contains(field)) {
            throw new SdkUsageError("filter field '" + field
                    + "': the API answers 400 'Filter syntax deprecated bracket' for "
                    + "this one, because it addresses a field inside a nested block "
                    + "rather than a top-level item field. Use the 'filter' expression "
                    + "parameter instead, e.g. "
                    + "{\"filter\": \"items/sale(pricing/latest_used_pricing_version) "
                    + "eq '1'\"} - see getItems.");
        }
        return QueryParams.filterParam(field, operator);
    }

    /** Equality, the common case. */
    public static String itemFilterParam(String field) {
        return itemFilterParam(field, "eq");
    }

    /** The {@code order_by} and {@code direction} pair items sort by; {@code sort} is ignored. */
    public static Map<String, Object> sortParams(String field, boolean descending) {
        return QueryParams.sortParams(field, descending);
    }

    /**
     * Create an item's inventory section: {@code POST /items/{item_id}/inventories}, once, on a
     * {@code STANDARD} item with sale or purchase on; {@code 201} with the whole item.
     */
    public ApiResponse<Item> createItemInventories(
            String itemId, Object payload, RequestOptions options) {
        requireValue(itemId, "item_id");
        requirePayload(payload);
        return client.sendPostRequest(ItemsUrls.ITEM_INVENTORIES,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> createItemInventories(String itemId, Object payload) {
        return createItemInventories(itemId, payload, null);
    }

    /**
     * Add stock to a warehouse: {@code POST /items/{item_id}/inventories/add}, {@code inventories}
     * plural; the same handler as {@code addItemInventory}.
     */
    public ApiResponse<Item> addItemInventories(
            String itemId, Object payload, RequestOptions options) {
        requireValue(itemId, "item_id");
        requirePayload(payload);
        return client.sendPostRequest(ItemsUrls.ITEM_INVENTORIES_ADD,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> addItemInventories(String itemId, Object payload) {
        return addItemInventories(itemId, payload, null);
    }

    /**
     * Create an item's inventory section: {@code POST /items/{item_id}/inventory},
     * {@code inventory} singular; the same handler as {@code createItemInventories}.
     */
    public ApiResponse<Item> createItemInventory(
            String itemId, Object payload, RequestOptions options) {
        requireValue(itemId, "item_id");
        requirePayload(payload);
        return client.sendPostRequest(ItemsUrls.ITEM_INVENTORY,
                reopen(options).pathParam("item_id", itemId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> createItemInventory(String itemId, Object payload) {
        return createItemInventory(itemId, payload, null);
    }

    /**
     * Delete one attachment: {@code DELETE /items/{item_id}/notes/{note_id}/files/{file_id}}, the
     * {@code uuid} the note's files listing gives; any failure is answered {@code 404}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteItemNoteFile(
            String itemId, String noteId, String fileId, RequestOptions options) {
        requireValue(itemId, "item_id");
        requireValue(noteId, "note_id");
        requireValue(fileId, "file_id");
        return client.sendDeleteRequest(ItemsUrls.ITEM_NOTE_FILE,
                reopen(options).pathParam("item_id", itemId).pathParam("note_id", noteId).pathParam("file_id", fileId).build(), null);
    }

    public ApiResponse<Dto> deleteItemNoteFile(String itemId, String noteId, String fileId) {
        return deleteItemNoteFile(itemId, noteId, fileId, null);
    }

    /**
     * Update one package: {@code PATCH /items/{item_id}/package_details/{package_detail_id}}, by
     * its {@code uuid}; the body is {@code {"item": {"package_details": {...}}}}.
     */
    public ApiResponse<Item> updateItemPackageDetail(
            String itemId, String packageDetailId, Object payload, RequestOptions options) {
        requireValue(itemId, "item_id");
        requireValue(packageDetailId, "package_detail_id");
        requirePayload(payload);
        return client.sendPatchRequest(ItemsUrls.ITEM_PACKAGE_DETAIL,
                reopen(options).pathParam("item_id", itemId).pathParam("package_detail_id", packageDetailId).jsonBody(payload).build(), Item.class);
    }

    public ApiResponse<Item> updateItemPackageDetail(String itemId, String packageDetailId, Object payload) {
        return updateItemPackageDetail(itemId, packageDetailId, payload, null);
    }

    /**
     * Retrieve an item's KPI summary: {@code GET /items/{item_id}/kpis}, under {@code item_kpis};
     * empty for every item measured, so no typed view. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getItemKpis(
            String itemId, RequestOptions options) {
        requireValue(itemId, "item_id");
        return client.sendGetRequest(ItemsUrls.ITEM_KPIS,
                reopen(options).pathParam("item_id", itemId).build(), null);
    }

    public ApiResponse<Dto> getItemKpis(String itemId) {
        return getItemKpis(itemId, null);
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

    private static void requirePayload(Object payload) {
        if (payload == null) {
            throw new SdkUsageError("payload is required: this endpoint takes a body, "
                    + "and null would send the request with no body at all");
        }
    }

    private static void requireNote(String note) {
        if (note == null || note.isEmpty()) {
            throw new SdkUsageError(
                    "note is required: the note text is the body of this request");
        }
    }

    private static void requireValue(String value, String what) {
        if (value == null || value.isBlank()) {
            throw new SdkUsageError(
                    what + " is required: the request cannot be built without it");
        }
    }
}
