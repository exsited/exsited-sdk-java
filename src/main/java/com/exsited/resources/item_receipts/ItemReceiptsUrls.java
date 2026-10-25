package com.exsited.resources.item_receipts;

/** Path templates and body keys for the item receipt endpoints. */
public final class ItemReceiptsUrls {

    private ItemReceiptsUrls() {}

    public static final String ITEM_RECEIPTS = "/api/{api_version}/item_receipts";
    public static final String ITEM_RECEIPT = "/api/{api_version}/item_receipts/{item_receipt_id}";
    public static final String PURCHASE_ORDER_ITEM_RECEIPTS = "/api/{api_version}/purchase_orders/{purchase_order_id}/item_receipts";
    public static final String ITEM_RECEIPT_NOTE = "/api/{api_version}/item_receipts/{item_receipt_id}/notes/{note_id}";

    /** The key a listing's records sit under. */
    public static final String ITEM_RECEIPTS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ITEM_RECEIPT_KEY = "item_receipt";
    public static final String PURCHASE_ORDER_KEY = "purchase_order";
    public static final String ITEM_RECEIPT_HYPHENATED_KEY = "item-receipt";
}
