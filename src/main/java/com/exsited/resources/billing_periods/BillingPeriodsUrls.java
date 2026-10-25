package com.exsited.resources.billing_periods;

/** Path templates and body keys for the billing period endpoints. */
public final class BillingPeriodsUrls {

    private BillingPeriodsUrls() {}

    public static final String SETTINGS_BILLING_PERIODS = "/api/{api_version}/settings/billing_periods";
    public static final String SETTINGS_BILLING_PERIOD = "/api/{api_version}/settings/billing_periods/{billing_period_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_BILLING_PERIODS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String BILLING_PERIOD_KEY = "billing_period";
}
