package com.exsited.resources.item_fulfillments;

/** Path templates and body keys for the item fulfillment endpoints. */
public final class ItemFulfillmentsUrls {

    private ItemFulfillmentsUrls() {}

    public static final String ITEM_FULFILLMENTS = "/api/{api_version}/item_fulfillments";
    public static final String ITEM_FULFILLMENT = "/api/{api_version}/item_fulfillments/{fulfillment_id}";
    public static final String ITEM_FULFILLMENT_NOTES = "/api/{api_version}/item_fulfillments/{fulfillment_id}/notes";
    public static final String ITEM_FULFILLMENT_NOTE = "/api/{api_version}/item_fulfillments/{fulfillment_id}/notes/{note_id}";

    /** The key a listing's records sit under. */
    public static final String ITEM_FULFILLMENTS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String INVOICE_KEY = "invoice";
    public static final String ITEM_FULFILLMENT_KEY = "item_fulfillment";
    public static final String NOTE_KEY = "note";
    public static final String ITEM_FULFILLMENT_NOTES_KEY = "data";
}
