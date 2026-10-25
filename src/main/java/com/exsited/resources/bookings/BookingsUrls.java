package com.exsited.resources.bookings;

/** Path templates and body keys for the booking endpoints. */
public final class BookingsUrls {

    private BookingsUrls() {}

    public static final String BOOKING = "/api/{api_version}/booking";
    public static final String BOOKING_BY_ID = "/api/{api_version}/booking/{booking_id}";
    /** Literal second segments, read by the server in the slot a booking id takes. */
    public static final String BOOKING_NOTES = "/api/{api_version}/booking/notes";
    public static final String BOOKING_PREVIEW = "/api/{api_version}/booking/preview";
    /**
     * Every other POST /booking/{booking_id}/{word} falls through to create, so change is the only
     * transition the path holds.
     */
    public static final String BOOKING_CHANGE = "/api/{api_version}/booking/{booking_id}/change";
    public static final String BOOKING_CHANGE_PREVIEW = "/api/{api_version}/booking/{booking_id}/change/preview";
    public static final String BOOKING_BILLING_PREFERENCES = "/api/{api_version}/booking/{booking_id}/billing_preferences";
    public static final String BOOKING_INFORMATION = "/api/{api_version}/booking/{booking_id}/information";
    public static final String BOOKING_LINES = "/api/{api_version}/booking/{booking_id}/lines";
    public static final String BOOKING_LINE = "/api/{api_version}/booking/{booking_id}/lines/{line_uuid}";
    public static final String BOOKING_LINE_BILLING_PREFERENCES = "/api/{api_version}/booking/{booking_id}/lines/{line_uuid}/billing_preferences";
    /**
     * custom_objects_instances is the server's alias of custom_objects and answers the same bodies.
     */
    public static final String BOOKING_CUSTOM_OBJECTS = "/api/{api_version}/booking/{booking_id}/custom_objects";
    public static final String BOOKING_CUSTOM_OBJECT = "/api/{api_version}/booking/{booking_id}/custom_objects/{custom_object_id}";
    public static final String BOOKING_CUSTOM_OBJECT_INSTANCE = "/api/{api_version}/booking/{booking_id}/custom_objects/{custom_object_id}/instances/{instance_id}";
    public static final String BOOKING_CUSTOM_OBJECTS_INSTANCES = "/api/{api_version}/booking/{booking_id}/custom_objects_instances";
    public static final String BOOKING_CUSTOM_OBJECTS_INSTANCE = "/api/{api_version}/booking/{booking_id}/custom_objects_instances/{custom_object_id}";
    public static final String BOOKING_CUSTOM_OBJECTS_INSTANCE_INSTANCE = "/api/{api_version}/booking/{booking_id}/custom_objects_instances/{custom_object_id}/instances/{instance_id}";
    public static final String BOOKING_INVOICES = "/api/{api_version}/booking/{booking_id}/invoices";
    public static final String BOOKING_ITEM_FULFILLMENTS = "/api/{api_version}/booking/{booking_id}/item_fulfillments";
    public static final String BOOKING_ITEM_FULFILLMENT = "/api/{api_version}/booking/{booking_id}/item_fulfillments/{fulfillment_id}";
    public static final String BOOKING_KPIS = "/api/{api_version}/booking/{booking_id}/kpis";
    public static final String BOOKING_PAYMENT_METHODS = "/api/{api_version}/booking/{booking_id}/payment_methods";
    public static final String BOOKING_PAYMENT_METHOD = "/api/{api_version}/booking/{booking_id}/payment_methods/{payment_method_ref}";
    public static final String BOOKING_PAYMENTS = "/api/{api_version}/booking/{booking_id}/payments";
    public static final String BOOKING_PAYMENT = "/api/{api_version}/booking/{booking_id}/payments/{payment_id}";
    public static final String BOOKING_PDF = "/api/{api_version}/booking/{booking_id}/pdf";
    public static final String BOOKING_QUOTE_PARTIAL_INVOICES = "/api/{api_version}/booking/{booking_id}/quote_partial_invoices";
    public static final String BOOKING_SHIPPING_PDF = "/api/{api_version}/booking/{booking_id}/shipping_pdf/{shipping_line_id}";

    /** The key a listing's records sit under. */
    public static final String BOOKING_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ORDER_KEY = "order";
}
