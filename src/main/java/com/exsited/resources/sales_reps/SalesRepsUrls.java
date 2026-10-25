package com.exsited.resources.sales_reps;

/** Path templates and body keys for the sales rep endpoints. */
public final class SalesRepsUrls {

    private SalesRepsUrls() {}

    public static final String SETTINGS_SALES_REPS = "/api/{api_version}/settings/sales_reps";
    public static final String SETTINGS_SALES_REP = "/api/{api_version}/settings/sales_reps/{sales_rep_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_SALES_REPS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String SALES_REP_KEY = "sales_rep";
}
