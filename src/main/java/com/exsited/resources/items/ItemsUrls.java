package com.exsited.resources.items;

import java.util.List;
import java.util.Set;

/**
 * Path templates, body keys and filter names for the item endpoints. {@code {item_id}} is the
 * listing's {@code id} ({@code "ITEM-0001"}), never the {@code uuid} or the integer {@code id}
 * the sale, purchase and inventories reads carry; {@code {warehouse_name}} is a warehouse name.
 */
public final class ItemsUrls {

    private ItemsUrls() {}

    public static final String ITEMS = "/api/{api_version}/items";
    public static final String ITEM = "/api/{api_version}/items/{item_id}";
    public static final String ITEM_INFORMATION = "/api/{api_version}/items/{item_id}/information";

    public static final String ITEM_DEACTIVATE = "/api/{api_version}/items/{item_id}/deactivate";
    public static final String ITEM_REACTIVATE = "/api/{api_version}/items/{item_id}/reactivate";

    public static final String ITEM_IMAGE = "/api/{api_version}/items/{item_id}/image";

    public static final String ITEM_SALE = "/api/{api_version}/items/{item_id}/sale";
    public static final String ITEM_PURCHASE = "/api/{api_version}/items/{item_id}/purchase";
    public static final String ITEM_INVENTORIES = "/api/{api_version}/items/{item_id}/inventories";

    public static final String ITEM_INVENTORY_ADD = "/api/{api_version}/items/{item_id}/inventory/add";
    public static final String ITEM_INVENTORY_ADJUSTMENTS =
            "/api/{api_version}/items/{item_id}/inventory_adjustments";

    public static final String ITEM_WAREHOUSE_INVENTORIES =
            "/api/{api_version}/items/{item_id}/warehouses/{warehouse_name}/inventories";

    public static final String ITEM_BUNDLE_DETAILS = "/api/{api_version}/items/{item_id}/bundle_details";
    public static final String ITEM_VARIATIONS = "/api/{api_version}/items/{item_id}/variations";

    public static final String ITEM_FAMILY_DETAILS = "/api/{api_version}/items/{item_id}/family_details";

    public static final String ITEM_PACKAGE_DETAILS = "/api/{api_version}/items/{item_id}/package_details";
    public static final String ITEM_PACKAGE_DETAIL =
            "/api/{api_version}/items/{item_id}/package_details/{package_detail_id}";

    public static final String ITEM_NOTES = "/api/{api_version}/items/{item_id}/notes";
    public static final String ITEM_NOTE = "/api/{api_version}/items/{item_id}/notes/{note_id}";
    public static final String ITEM_NOTE_FILES = "/api/{api_version}/items/{item_id}/notes/{note_id}/files";
    public static final String ITEM_NOTE_FILE =
            "/api/{api_version}/items/{item_id}/notes/{note_id}/files/{file_id}";
    /** The singular and plural spellings are one handler each way: create, and add stock. */
    public static final String ITEM_INVENTORY = "/api/{api_version}/items/{item_id}/inventory";
    public static final String ITEM_INVENTORIES_ADD = "/api/{api_version}/items/{item_id}/inventories/add";
    public static final String ITEM_KPIS = "/api/{api_version}/items/{item_id}/kpis";

    /** The key a listing's records sit under. */
    public static final String ITEMS_KEY = "data";
    public static final String ITEM_INVENTORY_ADJUSTMENTS_KEY = "data";
    public static final String ITEM_NOTES_KEY = "data";
    public static final String ITEM_NOTE_FILES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ITEM_KEY = "item";
    public static final String NOTE_KEY = "note";
    public static final String INVENTORY_ADJUSTMENT_KEY = "inventory_adjustment";
    public static final String FILE_KEY = "file";
    public static final String ITEM_KPIS_KEY = "item_kpis";

    /**
     * The names that shape the listing, which pages by cursor; every other name is a
     * {@code filter_<field>}. {@code order_by} and {@code direction} are accepted and ignored,
     * and a bare {@code filter} expression is {@code 400}.
     */
    public static final List<String> ITEMS_QUERY_PARAMS = List.of("limit", "cursor", "sort");

    /**
     * Top-level item fields that {@code filter_<field>} accepts. Every one also takes
     * {@code _neq}; the text ones additionally take {@code _contains} and
     * {@code _not_contains}.
     */
    public static final Set<String> FILTERABLE_FIELDS = Set.of(
            "id", "uuid", "name", "display_name", "description", "status", "type",
            "origin", "invoice_note", "image_uri", "base_uom", "group", "manager",
            "custom_form", "created_by", "last_updated_by", "version");

    /**
     * Fields the API documentation lists as {@code filter_<field>} and the API
     * <b>rejects</b> with {@code 400 "Filter syntax deprecated bracket"}. Each lives
     * inside a nested block and is reachable through the {@code filter} expression
     * instead.
     */
    public static final Set<String> DEPRECATED_FILTERS = Set.of(
            "item_number", "upc_code",
            "sale_enabled", "sales_accounting_code_revenue", "sales_billing_mode",
            "sales_charge_type", "sales_currency", "sales_fulfillment_mode",
            "sales_payment_mode", "sales_pricing_method", "sales_tax_inclusive_based_on",
            "purchase_enabled", "purchase_currency", "purchase_order_note",
            "purchase_profile", "purchase_receive_mode",
            "inventory_enabled", "inventory_quantity_available",
            "inventory_quantity_on_hand", "inventory_qty_avl_on_sale_determination");

    /** The subset that also takes {@code _contains} and {@code _not_contains}. */
    public static final Set<String> TEXT_FILTER_FIELDS = Set.of(
            "description", "display_name", "id", "image_uri", "invoice_note", "name", "origin");

    /**
     * The fields {@code filter_<field>} reaches inside the {@code sale}, {@code purchase} and
     * {@code inventories} blocks. All answer {@code 200}; an unmatched value is {@code data: []},
     * not an error.
     */
    public static final Set<String> NESTED_BLOCK_FILTERS = Set.of(
            "inventory_enabled", "inventory_qty_avl_on_sale_determination",
            "inventory_quantity_available", "inventory_quantity_on_hand", "item_number",
            "purchase_currency", "purchase_enabled", "purchase_order_note", "purchase_profile",
            "purchase_receive_mode", "sale_enabled", "sales_accounting_code_revenue",
            "sales_billing_mode", "sales_charge_type", "sales_currency", "sales_fulfillment_mode",
            "sales_payment_mode", "sales_pricing_method", "sales_tax_inclusive_based_on",
            "upc_code");
}
