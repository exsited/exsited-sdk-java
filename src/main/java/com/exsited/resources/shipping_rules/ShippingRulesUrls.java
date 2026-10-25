package com.exsited.resources.shipping_rules;

/** Path templates and body keys for the shipping rule endpoints. */
public final class ShippingRulesUrls {

    private ShippingRulesUrls() {}

    public static final String SETTINGS_SHIPPING_RULES = "/api/{api_version}/settings/shipping_rules";
    public static final String SETTINGS_SHIPPING_RULE = "/api/{api_version}/settings/shipping_rules/{shipping_rule_id}";
    public static final String SETTINGS_SHIPPING_RULE_CONFIGURATION = "/api/{api_version}/settings/shipping_rules/{shipping_rule_id}/configuration";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_SHIPPING_RULES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String SHIPPING_RULE_KEY = "shipping_rule";

    /** The envelope a configuration is sent under. */
    public static final String CONFIGURATION_KEY = "configuration";
}
