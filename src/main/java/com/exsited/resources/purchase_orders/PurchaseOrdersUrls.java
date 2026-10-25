package com.exsited.resources.purchase_orders;

/** Path templates and body keys for the purchase order endpoints. */
public final class PurchaseOrdersUrls {

    private PurchaseOrdersUrls() {}

    public static final String PURCHASE_ORDERS = "/api/{api_version}/purchase_orders";
    public static final String PURCHASE_ORDERS_NOTES = "/api/{api_version}/purchase_orders/notes";
    public static final String PURCHASE_ORDER = "/api/{api_version}/purchase_orders/{purchase_order_id}";
    public static final String PURCHASE_ORDER_CANCEL = "/api/{api_version}/purchase_orders/{purchase_order_id}/cancel";
    public static final String PURCHASE_ORDER_CHANGE = "/api/{api_version}/purchase_orders/{purchase_order_id}/change";
    public static final String PURCHASE_ORDER_INFORMATION = "/api/{api_version}/purchase_orders/{purchase_order_id}/information";
    public static final String PURCHASE_ORDER_LINES = "/api/{api_version}/purchase_orders/{purchase_order_id}/lines";
    public static final String PURCHASE_ORDER_NOTES = "/api/{api_version}/purchase_orders/{purchase_order_id}/notes";
    public static final String PURCHASE_ORDER_PURCHASE_INVOICES = "/api/{api_version}/purchase_orders/{purchase_order_id}/purchase_invoices";
    public static final String PURCHASE_ORDER_PURCHASE_PAYMENTS = "/api/{api_version}/purchase_orders/{purchase_order_id}/purchase_payments";
    public static final String PURCHASE_ORDER_REACTIVATE = "/api/{api_version}/purchase_orders/{purchase_order_id}/reactivate";
    public static final String PURCHASE_ORDER_LINE = "/api/{api_version}/purchase_orders/{purchase_order_id}/lines/{purchase_order_line_uuid}";
    public static final String PURCHASE_ORDER_NOTE = "/api/{api_version}/purchase_orders/{purchase_order_id}/notes/{purchase_order_note_id}";
    public static final String PURCHASE_ORDER_NOTE_FILES = "/api/{api_version}/purchase_orders/{purchase_order_id}/notes/{purchase_order_note_id}/files";
    public static final String PURCHASE_ORDER_NOTE_FILE = "/api/{api_version}/purchase_orders/{purchase_order_id}/notes/{purchase_order_note_id}/files/{purchase_order_file_id}";
    public static final String PURCHASE_ORDER_KPIS = "/api/{api_version}/purchase_orders/{purchase_order_id}/kpis";

    /** The key a listing's records sit under. */
    public static final String PURCHASE_ORDERS_KEY = "data";
    public static final String PURCHASE_ORDERS_NOTES_KEY = "data";
    public static final String PURCHASE_ORDER_LINES_KEY = "data";
    public static final String PURCHASE_ORDER_PURCHASE_INVOICES_KEY = "data";
    public static final String PURCHASE_ORDER_PURCHASE_PAYMENTS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String FILE_KEY = "file";
    public static final String NOTE_KEY = "note";
    public static final String PURCHASE_INVOICE_KEY = "purchase_invoice";
    public static final String PURCHASE_ORDER_KEY = "purchase_order";
    public static final String PURCHASE_ORDER_KPIS_KEY = "purchase_order_kpis";
}
