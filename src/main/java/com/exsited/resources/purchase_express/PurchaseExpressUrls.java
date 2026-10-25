package com.exsited.resources.purchase_express;

/** Path templates and body keys for the purchase express endpoints. */
public final class PurchaseExpressUrls {

    private PurchaseExpressUrls() {}

    public static final String PURCHASE_EXPRESS = "/api/{api_version}/purchase_express";

    /** The envelope the created purchase order arrives under. */
    public static final String PURCHASE_EXPRESS_KEY = "purchase_express";
}
