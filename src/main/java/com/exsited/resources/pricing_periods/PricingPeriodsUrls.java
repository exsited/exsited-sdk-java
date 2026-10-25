package com.exsited.resources.pricing_periods;

/** Path templates and body keys for the pricing period endpoints. */
public final class PricingPeriodsUrls {

    private PricingPeriodsUrls() {}

    public static final String SETTINGS_PRICING_PERIODS = "/api/{api_version}/settings/pricing_periods";
    public static final String SETTINGS_PRICING_PERIOD = "/api/{api_version}/settings/pricing_periods/{pricing_period_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_PRICING_PERIODS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String PRICING_PERIOD_KEY = "pricing_period";
}
