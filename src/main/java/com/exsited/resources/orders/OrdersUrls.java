package com.exsited.resources.orders;

/** Path templates and body keys for the order endpoints. */
public final class OrdersUrls {

    private OrdersUrls() {}

    public static final String ORDERS = "/api/{api_version}/orders";
    public static final String ORDER = "/api/{api_version}/orders/{order_id}";
    public static final String ORDER_ACTIVATE = "/api/{api_version}/orders/{order_id}/activate";
    public static final String ORDER_ACTIVATE_DRAFT = "/api/{api_version}/orders/{order_id}/activate_draft";
    public static final String ORDER_BILLING_PREFERENCES = "/api/{api_version}/orders/{order_id}/billing_preferences";
    public static final String ORDER_CANCEL = "/api/{api_version}/orders/{order_id}/cancel";
    public static final String ORDER_CHANGE = "/api/{api_version}/orders/{order_id}/change";
    public static final String ORDER_CONVERT_TO_ORDER = "/api/{api_version}/orders/{order_id}/convert_to_order";
    public static final String ORDER_CUSTOM_OBJECTS = "/api/{api_version}/orders/{order_id}/custom_objects";
    public static final String ORDER_CUSTOM_OBJECTS_INSTANCES = "/api/{api_version}/orders/{order_id}/custom_objects_instances";
    public static final String ORDER_DISCARD_DRAFT = "/api/{api_version}/orders/{order_id}/discard_draft";
    public static final String ORDER_EXTEND_TRIAL = "/api/{api_version}/orders/{order_id}/extend_trial";
    public static final String ORDER_INFORMATION = "/api/{api_version}/orders/{order_id}/information";
    public static final String ORDER_ITEM_FULFILLMENTS = "/api/{api_version}/orders/{order_id}/item_fulfillments";
    public static final String ORDER_LINES = "/api/{api_version}/orders/{order_id}/lines";
    public static final String ORDER_NOTES = "/api/{api_version}/orders/{order_id}/notes";
    public static final String ORDER_PAYMENT_METHODS = "/api/{api_version}/orders/{order_id}/payment_methods";
    public static final String ORDER_PDF = "/api/{api_version}/orders/{order_id}/pdf";
    public static final String ORDER_REACTIVATE = "/api/{api_version}/orders/{order_id}/reactivate";
    public static final String ORDER_RELINQUISH = "/api/{api_version}/orders/{order_id}/relinquish";
    public static final String ORDER_LINE = "/api/{api_version}/orders/{order_id}/lines/{line_uuid}";
    public static final String ORDER_NOTE = "/api/{api_version}/orders/{order_id}/notes/{order_note_id}";
    public static final String ORDER_PAYMENT_METHOD = "/api/{api_version}/orders/{order_id}/payment_methods/{payment_method_ref}";
    public static final String ORDER_LINE_BILLING_PREFERENCES = "/api/{api_version}/orders/{order_id}/lines/{line_uuid}/billing_preferences";
    public static final String ORDER_NOTE_FILES = "/api/{api_version}/orders/{order_id}/notes/{order_note_id}/files";
    public static final String ORDER_NOTE_FILE = "/api/{api_version}/orders/{order_id}/notes/{order_note_id}/files/{order_file_id}";
    /**
     * Literal second segments. The server reads "notes", "preview" and "express" in the slot an
     * order id takes, so each is its own path rather than an order id.
     */
    public static final String ORDERS_NOTES = "/api/{api_version}/orders/notes";
    public static final String ORDERS_PREVIEW = "/api/{api_version}/orders/preview";
    public static final String ORDERS_EXPRESS = "/api/{api_version}/orders/express";
    /**
     * State transitions. Only a trailing "preview" is special: any other word in that slot runs the
     * real operation, so each preview is a path of its own.
     */
    public static final String ORDER_ACCEPT = "/api/{api_version}/orders/{order_id}/accept";
    public static final String ORDER_ACTIVATE_INVOICE_FROM_DORMANT = "/api/{api_version}/orders/{order_id}/activate_invoice_from_dormant/{invoice_uuid}";
    public static final String ORDER_CHANGE_PREVIEW = "/api/{api_version}/orders/{order_id}/change/preview";
    public static final String ORDER_CHANGE_STATUS = "/api/{api_version}/orders/{order_id}/change_status";
    public static final String ORDER_CONTRACT_ADJUSTMENT = "/api/{api_version}/orders/{order_id}/contract_adjustment";
    public static final String ORDER_CONTRACT_ADJUSTMENT_PREVIEW = "/api/{api_version}/orders/{order_id}/contract_adjustment/preview";
    public static final String ORDER_DOWNGRADE = "/api/{api_version}/orders/{order_id}/downgrade";
    public static final String ORDER_DOWNGRADE_PREVIEW = "/api/{api_version}/orders/{order_id}/downgrade/preview";
    public static final String ORDER_UPGRADE = "/api/{api_version}/orders/{order_id}/upgrade";
    public static final String ORDER_UPGRADE_PREVIEW = "/api/{api_version}/orders/{order_id}/upgrade/preview";
    public static final String ORDER_TERMINATE_TRIAL = "/api/{api_version}/orders/{order_id}/terminate_trial";
    /**
     * Sub-reads. custom_objects_instances is the server's alias of custom_objects, one case in its
     * dispatcher, and answers the same bodies.
     */
    public static final String ORDER_CUSTOM_OBJECT = "/api/{api_version}/orders/{order_id}/custom_objects/{custom_object_id}";
    public static final String ORDER_CUSTOM_OBJECT_INSTANCE = "/api/{api_version}/orders/{order_id}/custom_objects/{custom_object_id}/instances/{instance_id}";
    public static final String ORDER_CUSTOM_OBJECTS_INSTANCE = "/api/{api_version}/orders/{order_id}/custom_objects_instances/{custom_object_id}";
    public static final String ORDER_CUSTOM_OBJECTS_INSTANCE_INSTANCE = "/api/{api_version}/orders/{order_id}/custom_objects_instances/{custom_object_id}/instances/{instance_id}";
    public static final String ORDER_ITEM_FULFILLMENT = "/api/{api_version}/orders/{order_id}/item_fulfillments/{fulfillment_id}";
    public static final String ORDER_KPIS = "/api/{api_version}/orders/{order_id}/kpis";
    public static final String ORDER_PAYMENT = "/api/{api_version}/orders/{order_id}/payments/{payment_id}";
    public static final String ORDER_QUOTE_PARTIAL_INVOICES = "/api/{api_version}/orders/{order_id}/quote_partial_invoices";
    public static final String ORDER_SHIPPING_PDF = "/api/{api_version}/orders/{order_id}/shipping_pdf/{shipping_line_id}";

    /** The key a listing's records sit under. */
    public static final String ORDERS_KEY = "data";
    public static final String ORDER_CUSTOM_OBJECTS_INSTANCES_KEY = "data";
    public static final String ORDER_CUSTOM_OBJECTS_KEY = "data";
    public static final String ORDER_ITEM_FULFILLMENTS_KEY = "data";
    public static final String ORDER_NOTES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String FILE_KEY = "file";
    public static final String NOTE_KEY = "note";
    public static final String ORDER_KEY = "order";
    public static final String ORDER_LINE_KEY = "order_line";
}
