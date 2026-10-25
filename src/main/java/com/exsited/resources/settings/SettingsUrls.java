package com.exsited.resources.settings;

/** Path templates and body keys for the settings endpoints. */
public final class SettingsUrls {

    private SettingsUrls() {}

    public static final String CUSTOM_OBJECTS = "/api/{api_version}/custom_objects";
    public static final String SETTINGS = "/api/{api_version}/settings";
    public static final String CUSTOM_OBJECT = "/api/{api_version}/custom_objects/{custom_object_id}";
    public static final String SETTINGS_CURRENCIES = "/api/{api_version}/settings/currencies";
    public static final String SETTINGS_CUSTOM_LOGIC_SCRIPTS = "/api/{api_version}/settings/custom_logic_scripts";
    public static final String SETTINGS_CUSTOM_OBJECTS = "/api/{api_version}/settings/custom_objects";
    public static final String SETTINGS_DISCOUNT_PROFILES = "/api/{api_version}/settings/discount_profiles";
    public static final String SETTINGS_ITEM_GROUPS = "/api/{api_version}/settings/item_groups";
    public static final String SETTINGS_PAYMENT_PROCESSORS = "/api/{api_version}/settings/payment_processors";
    public static final String SETTINGS_PAYMENT_TERMS = "/api/{api_version}/settings/payment_terms";
    public static final String SETTINGS_PRICING_LEVELS = "/api/{api_version}/settings/pricing_levels";
    public static final String SETTINGS_SHIPPING_PROFILES = "/api/{api_version}/settings/shipping_profiles";
    public static final String SETTINGS_TAXES = "/api/{api_version}/settings/taxes";
    public static final String SETTINGS_TIME_ZONES = "/api/{api_version}/settings/time_zones";
    public static final String SETTINGS_VARIATIONS = "/api/{api_version}/settings/variations";
    public static final String SETTINGS_WAREHOUSES = "/api/{api_version}/settings/warehouses";
    public static final String SETTINGS_CURRENCY = "/api/{api_version}/settings/currencies/{currency_id}";
    public static final String SETTINGS_CUSTOM_LOGIC_SCRIPT = "/api/{api_version}/settings/custom_logic_scripts/{custom_logic_script_id}";
    public static final String SETTINGS_CUSTOM_OBJECT = "/api/{api_version}/settings/custom_objects/{custom_object_id}";
    public static final String SETTINGS_FIELD_SETTINGS_ACCOUNT = "/api/{api_version}/settings/field_settings/account";
    public static final String SETTINGS_FIELD_SETTINGS_ITEM = "/api/{api_version}/settings/field_settings/item";
    public static final String SETTINGS_GENERAL_SETTINGS_ACCOUNT = "/api/{api_version}/settings/general_settings/account";
    public static final String SETTINGS_GENERAL_SETTINGS_ITEM = "/api/{api_version}/settings/general_settings/item";
    public static final String SETTINGS_ITEM_GROUP = "/api/{api_version}/settings/item_groups/{item_group_id}";
    public static final String SETTINGS_TIME_ZONE = "/api/{api_version}/settings/time_zones/{time_zone_id}";
    public static final String SETTINGS_VARIATION = "/api/{api_version}/settings/variations/{variation_id}";
    public static final String SETTINGS_CUSTOM_OBJECT_CUSTOMIZE = "/api/{api_version}/settings/custom_objects/{custom_object_id}/customize";
    public static final String SETTINGS_ITEM_GROUP_BILLING_PREFERENCES = "/api/{api_version}/settings/item_groups/{item_group_id}/billing_preferences";
    public static final String SETTINGS_DISCOUNT_PROFILE = "/api/{api_version}/settings/discount_profiles/{discount_profile_id}";
    public static final String SETTINGS_FIELD_SETTINGS = "/api/{api_version}/settings/field_settings";
    public static final String SETTINGS_FIELD_SETTING = "/api/{api_version}/settings/field_settings/{field_setting_id}";
    public static final String SETTINGS_GENERAL_SETTINGS_GROUP = "/api/{api_version}/settings/general_settings/{group}";
    public static final String SETTINGS_GENERAL_SETTING = "/api/{api_version}/settings/general_settings/{group}/{general_setting_id}";
    public static final String SETTINGS_GENERAL_SETTINGS_DASHBOARD = "/api/{api_version}/settings/general_settings/dashboard";
    public static final String SETTINGS_GENERAL_SETTINGS_DATE_AND_TIME = "/api/{api_version}/settings/general_settings/date_and_time";
    public static final String SETTINGS_GENERAL_SETTINGS_DISPLAY = "/api/{api_version}/settings/general_settings/display";
    public static final String SETTINGS_GENERAL_SETTINGS_GENERAL = "/api/{api_version}/settings/general_settings/general";
    public static final String SETTINGS_GENERAL_SETTINGS_LABOUR = "/api/{api_version}/settings/general_settings/labour";
    public static final String SETTINGS_GENERAL_SETTINGS_ORDER = "/api/{api_version}/settings/general_settings/order";
    public static final String SETTINGS_GENERAL_SETTINGS_PORTAL = "/api/{api_version}/settings/general_settings/portal";
    public static final String SETTINGS_GENERAL_SETTINGS_PURCHASE = "/api/{api_version}/settings/general_settings/purchase";
    public static final String SETTINGS_GENERAL_SETTINGS_SECURITY = "/api/{api_version}/settings/general_settings/security";
    public static final String SETTINGS_GENERAL_SETTINGS_TRANSACTION = "/api/{api_version}/settings/general_settings/transaction";
    public static final String SETTINGS_PAYMENT_PROCESSOR = "/api/{api_version}/settings/payment_processors/{payment_processor_id}";
    public static final String SETTINGS_PAYMENT_TERM = "/api/{api_version}/settings/payment_terms/{payment_term_id}";
    public static final String SETTINGS_PRICING_LEVEL = "/api/{api_version}/settings/pricing_levels/{pricing_level_id}";
    public static final String SETTINGS_SHIPPING_PROFILE = "/api/{api_version}/settings/shipping_profiles/{shipping_profile_id}";
    public static final String SETTINGS_TAX = "/api/{api_version}/settings/taxes/{tax_id}";
    public static final String SETTINGS_WAREHOUSE = "/api/{api_version}/settings/warehouses/{warehouse_id}";

    /** The key a listing's records sit under. */
    public static final String CUSTOM_OBJECTS_KEY = "data";
    public static final String SETTINGS_CURRENCIES_KEY = "data";
    public static final String SETTINGS_CUSTOM_LOGIC_SCRIPTS_KEY = "data";
    public static final String SETTINGS_DISCOUNT_PROFILES_KEY = "data";
    public static final String SETTINGS_ITEM_GROUPS_KEY = "data";
    public static final String SETTINGS_PAYMENT_PROCESSORS_KEY = "data";
    public static final String SETTINGS_PAYMENT_TERMS_KEY = "data";
    public static final String SETTINGS_PRICING_LEVELS_KEY = "data";
    public static final String SETTINGS_SHIPPING_PROFILES_KEY = "data";
    public static final String SETTINGS_TAXES_KEY = "data";
    public static final String SETTINGS_TIME_ZONES_KEY = "data";
    public static final String SETTINGS_VARIATIONS_KEY = "data";
    public static final String SETTINGS_WAREHOUSES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String CURRENCY_KEY = "currency";
    public static final String CUSTOM_LOGIC_SCRIPT_KEY = "custom_logic_script";
    public static final String CUSTOM_OBJECT_KEY = "custom_object";
    public static final String GENERAL_SETTINGS_KEY = "general_settings";
    public static final String ITEM_GROUP_KEY = "item_group";
    public static final String SETTINGS_KEY = "settings";
    public static final String TIME_ZONE_KEY = "time_zone";
    public static final String VARIATION_KEY = "variation";
    public static final String SETTINGS_FIELD_SETTINGS_KEY = "data";
    public static final String DISCOUNT_PROFILE_KEY = "discount_profile";
    public static final String FIELD_SETTING_KEY = "field_setting";
    public static final String GENERAL_SETTING_KEY = "general_setting";
    public static final String PAYMENT_PROCESSOR_KEY = "payment_processor";
    public static final String PAYMENT_TERM_KEY = "payment_term";
    public static final String PRICING_LEVEL_KEY = "pricing_level";
    public static final String SHIPPING_PROFILE_KEY = "shipping_profile";
    public static final String TAX_KEY = "tax";
    public static final String WAREHOUSE_KEY = "warehouse";
}
