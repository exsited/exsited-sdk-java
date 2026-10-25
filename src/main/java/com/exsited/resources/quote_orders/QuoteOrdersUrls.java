package com.exsited.resources.quote_orders;

/** Path templates and body keys for the quote order endpoints. */
public final class QuoteOrdersUrls {

    private QuoteOrdersUrls() {}

    /**
     * The server serves this one read under quote_orders; a quote itself is read, written and
     * converted under /orders. A quote_order_id that is not a quote answers 404.
     */
    public static final String QUOTE_ORDER_INVOICES = "/api/{api_version}/quote_orders/{quote_order_id}/invoices";

    /** The key a listing's records sit under. */
    public static final String QUOTE_ORDER_INVOICES_KEY = "data";
}
