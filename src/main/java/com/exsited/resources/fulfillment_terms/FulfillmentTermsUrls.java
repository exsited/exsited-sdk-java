package com.exsited.resources.fulfillment_terms;

/** Path templates and body keys for the fulfillment term endpoints. */
public final class FulfillmentTermsUrls {

    private FulfillmentTermsUrls() {}

    public static final String SETTINGS_FULFILLMENT_TERMS = "/api/{api_version}/settings/fulfillment_terms";
    public static final String SETTINGS_FULFILLMENT_TERM = "/api/{api_version}/settings/fulfillment_terms/{fulfillment_term_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_FULFILLMENT_TERMS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String FULFILLMENT_TERM_KEY = "fulfillment_term";
}
