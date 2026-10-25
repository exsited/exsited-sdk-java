package com.exsited.resources.items;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
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

/**
 * Forwards every call to {@link Items}.
 *
 * @deprecated {@link Items} is bound to a client itself and is what
 *     {@code client.items()} answers; use it instead.
 */
@Deprecated
public final class ItemsResource implements BoundResource {

    private final Items items;

    public ItemsResource(ExsitedClient client) {
        this.items = new Items(client);
    }

    @Override
    public ExsitedClient getClient() {
        return items.getClient();
    }

    public ApiResponse<ItemList> getItems(Map<String, Object> queryParams, RequestOptions options) {
        return items.getItems(queryParams, options);
    }

    public ApiResponse<ItemList> getItems(Map<String, Object> queryParams) {
        return items.getItems(queryParams);
    }

    public ApiResponse<Item> getItem(String itemId, RequestOptions options) {
        return items.getItem(itemId, options);
    }

    public ApiResponse<Item> getItem(String itemId) {
        return items.getItem(itemId);
    }

    public ApiResponse<Item> getItemInformation(String itemId, RequestOptions options) {
        return items.getItemInformation(itemId, options);
    }

    public ApiResponse<Item> getItemInformation(String itemId) {
        return items.getItemInformation(itemId);
    }

    public ApiResponse<Item> createItem(Object payload, RequestOptions options) {
        return items.createItem(payload, options);
    }

    public ApiResponse<Item> createItem(Object payload) {
        return items.createItem(payload);
    }

    public ApiResponse<Item> updateItemInformation(
            String itemId, Object payload, RequestOptions options) {
        return items.updateItemInformation(itemId, payload, options);
    }

    public ApiResponse<Item> updateItemInformation(String itemId, Object payload) {
        return items.updateItemInformation(itemId, payload);
    }

    public ApiResponse<Item> deactivateItem(String itemId, RequestOptions options) {
        return items.deactivateItem(itemId, options);
    }

    public ApiResponse<Item> deactivateItem(String itemId) {
        return items.deactivateItem(itemId);
    }

    public ApiResponse<Item> reactivateItem(String itemId, RequestOptions options) {
        return items.reactivateItem(itemId, options);
    }

    public ApiResponse<Item> reactivateItem(String itemId) {
        return items.reactivateItem(itemId);
    }

    public ApiResponse<Dto> deleteItem(String itemId, RequestOptions options) {
        return items.deleteItem(itemId, options);
    }

    public ApiResponse<Dto> deleteItem(String itemId) {
        return items.deleteItem(itemId);
    }

    public ApiResponse<Item> uploadItemImage(String itemId, Object payload, RequestOptions options) {
        return items.uploadItemImage(itemId, payload, options);
    }

    public ApiResponse<Item> uploadItemImage(String itemId, Object payload) {
        return items.uploadItemImage(itemId, payload);
    }

    public ApiResponse<ItemSaleView> getItemSale(String itemId, RequestOptions options) {
        return items.getItemSale(itemId, options);
    }

    public ApiResponse<ItemSaleView> getItemSale(String itemId) {
        return items.getItemSale(itemId);
    }

    public ApiResponse<Item> createItemSale(String itemId, Object payload, RequestOptions options) {
        return items.createItemSale(itemId, payload, options);
    }

    public ApiResponse<Item> createItemSale(String itemId, Object payload) {
        return items.createItemSale(itemId, payload);
    }

    public ApiResponse<Item> updateItemSale(String itemId, Object payload, RequestOptions options) {
        return items.updateItemSale(itemId, payload, options);
    }

    public ApiResponse<Item> updateItemSale(String itemId, Object payload) {
        return items.updateItemSale(itemId, payload);
    }

    public ApiResponse<ItemPurchaseView> getItemPurchase(String itemId, RequestOptions options) {
        return items.getItemPurchase(itemId, options);
    }

    public ApiResponse<ItemPurchaseView> getItemPurchase(String itemId) {
        return items.getItemPurchase(itemId);
    }

    public ApiResponse<Item> createItemPurchase(
            String itemId, Object payload, RequestOptions options) {
        return items.createItemPurchase(itemId, payload, options);
    }

    public ApiResponse<Item> createItemPurchase(String itemId, Object payload) {
        return items.createItemPurchase(itemId, payload);
    }

    public ApiResponse<Item> updateItemPurchase(
            String itemId, Object payload, RequestOptions options) {
        return items.updateItemPurchase(itemId, payload, options);
    }

    public ApiResponse<Item> updateItemPurchase(String itemId, Object payload) {
        return items.updateItemPurchase(itemId, payload);
    }

    public ApiResponse<ItemInventoriesView> getItemInventories(
            String itemId, RequestOptions options) {
        return items.getItemInventories(itemId, options);
    }

    public ApiResponse<ItemInventoriesView> getItemInventories(String itemId) {
        return items.getItemInventories(itemId);
    }

    public ApiResponse<Item> updateItemInventories(
            String itemId, Object payload, RequestOptions options) {
        return items.updateItemInventories(itemId, payload, options);
    }

    public ApiResponse<Item> updateItemInventories(String itemId, Object payload) {
        return items.updateItemInventories(itemId, payload);
    }

    public ApiResponse<Item> addItemInventory(
            String itemId, Object payload, RequestOptions options) {
        return items.addItemInventory(itemId, payload, options);
    }

    public ApiResponse<Item> addItemInventory(String itemId, Object payload) {
        return items.addItemInventory(itemId, payload);
    }

    public ApiResponse<InventoryAdjustmentList> getItemInventoryAdjustments(
            String itemId, Map<String, Object> queryParams, RequestOptions options) {
        return items.getItemInventoryAdjustments(itemId, queryParams, options);
    }

    public ApiResponse<InventoryAdjustmentList> getItemInventoryAdjustments(
            String itemId, Map<String, Object> queryParams) {
        return items.getItemInventoryAdjustments(itemId, queryParams);
    }

    public ApiResponse<ItemInventoriesView> getItemWarehouseInventories(
            String itemId, String warehouseName, RequestOptions options) {
        return items.getItemWarehouseInventories(itemId, warehouseName, options);
    }

    public ApiResponse<ItemInventoriesView> getItemWarehouseInventories(String itemId, String warehouseName) {
        return items.getItemWarehouseInventories(itemId, warehouseName);
    }

    public ApiResponse<Item> updateItemBundleDetails(
            String itemId, Object payload, RequestOptions options) {
        return items.updateItemBundleDetails(itemId, payload, options);
    }

    public ApiResponse<Item> updateItemBundleDetails(String itemId, Object payload) {
        return items.updateItemBundleDetails(itemId, payload);
    }

    public ApiResponse<Item> updateItemVariations(
            String itemId, Object payload, RequestOptions options) {
        return items.updateItemVariations(itemId, payload, options);
    }

    public ApiResponse<Item> updateItemVariations(String itemId, Object payload) {
        return items.updateItemVariations(itemId, payload);
    }

    public ApiResponse<Item> getItemFamilyDetails(String itemId, RequestOptions options) {
        return items.getItemFamilyDetails(itemId, options);
    }

    public ApiResponse<Item> getItemFamilyDetails(String itemId) {
        return items.getItemFamilyDetails(itemId);
    }

    public ApiResponse<Item> createItemFamilyDetails(
            String itemId, Object payload, RequestOptions options) {
        return items.createItemFamilyDetails(itemId, payload, options);
    }

    public ApiResponse<Item> createItemFamilyDetails(String itemId, Object payload) {
        return items.createItemFamilyDetails(itemId, payload);
    }

    public ApiResponse<Item> getItemPackageDetails(String itemId, RequestOptions options) {
        return items.getItemPackageDetails(itemId, options);
    }

    public ApiResponse<Item> getItemPackageDetails(String itemId) {
        return items.getItemPackageDetails(itemId);
    }

    public ApiResponse<Item> getItemPackageDetail(
            String itemId, String packageDetailId, RequestOptions options) {
        return items.getItemPackageDetail(itemId, packageDetailId, options);
    }

    public ApiResponse<Item> getItemPackageDetail(String itemId, String packageDetailId) {
        return items.getItemPackageDetail(itemId, packageDetailId);
    }

    public ApiResponse<Item> createItemPackageDetails(
            String itemId, Object payload, RequestOptions options) {
        return items.createItemPackageDetails(itemId, payload, options);
    }

    public ApiResponse<Item> createItemPackageDetails(String itemId, Object payload) {
        return items.createItemPackageDetails(itemId, payload);
    }

    public ApiResponse<Dto> deleteItemPackageDetail(
            String itemId, String packageDetailId, RequestOptions options) {
        return items.deleteItemPackageDetail(itemId, packageDetailId, options);
    }

    public ApiResponse<Dto> deleteItemPackageDetail(String itemId, String packageDetailId) {
        return items.deleteItemPackageDetail(itemId, packageDetailId);
    }

    public ApiResponse<NoteList> getItemNotes(
            String itemId, Map<String, Object> queryParams, RequestOptions options) {
        return items.getItemNotes(itemId, queryParams, options);
    }

    public ApiResponse<NoteList> getItemNotes(String itemId, Map<String, Object> queryParams) {
        return items.getItemNotes(itemId, queryParams);
    }

    public ApiResponse<NoteView> getItemNote(String itemId, String noteId, RequestOptions options) {
        return items.getItemNote(itemId, noteId, options);
    }

    public ApiResponse<NoteView> getItemNote(String itemId, String noteId) {
        return items.getItemNote(itemId, noteId);
    }

    public ApiResponse<NoteView> createItemNote(String itemId, String note, RequestOptions options) {
        return items.createItemNote(itemId, note, options);
    }

    public ApiResponse<NoteView> createItemNote(String itemId, String note) {
        return items.createItemNote(itemId, note);
    }

    public ApiResponse<NoteView> updateItemNote(
            String itemId, String noteId, String note, RequestOptions options) {
        return items.updateItemNote(itemId, noteId, note, options);
    }

    public ApiResponse<NoteView> updateItemNote(String itemId, String noteId, String note) {
        return items.updateItemNote(itemId, noteId, note);
    }

    public ApiResponse<Dto> deleteItemNote(String itemId, String noteId, RequestOptions options) {
        return items.deleteItemNote(itemId, noteId, options);
    }

    public ApiResponse<Dto> deleteItemNote(String itemId, String noteId) {
        return items.deleteItemNote(itemId, noteId);
    }

    public ApiResponse<NoteFileList> getItemNoteFiles(
            String itemId, String noteId, RequestOptions options) {
        return items.getItemNoteFiles(itemId, noteId, options);
    }

    public ApiResponse<NoteFileList> getItemNoteFiles(String itemId, String noteId) {
        return items.getItemNoteFiles(itemId, noteId);
    }

    public ApiResponse<ItemNoteFile> getItemNoteFile(
            String itemId, String noteId, String fileId, RequestOptions options) {
        return items.getItemNoteFile(itemId, noteId, fileId, options);
    }

    public ApiResponse<ItemNoteFile> getItemNoteFile(String itemId, String noteId, String fileId) {
        return items.getItemNoteFile(itemId, noteId, fileId);
    }
}
