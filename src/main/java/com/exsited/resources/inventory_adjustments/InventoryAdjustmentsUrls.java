package com.exsited.resources.inventory_adjustments;

/** Path templates and body keys for the inventory adjustment endpoints. */
public final class InventoryAdjustmentsUrls {

    private InventoryAdjustmentsUrls() {}

    public static final String INVENTORY_ADJUSTMENTS = "/api/{api_version}/inventory_adjustments";
    public static final String INVENTORY_ADJUSTMENT = "/api/{api_version}/inventory_adjustments/{inventory_adjustment_id}";

    /** The key a listing's records sit under. */
    public static final String INVENTORY_ADJUSTMENTS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String INVENTORY_ADJUSTMENT_KEY = "inventory_adjustment";
}
