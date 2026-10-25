package com.exsited.resources.settings.settings_dto;

import com.exsited.Dto;
import com.exsited.WireName;
import com.exsited.resources.PaginationDto;
import java.util.List;
import java.util.Map;

/** Typed views over the settings bodies. */
public final class SettingsDto {

    /** The settings discount profile body. */
    public static class SettingsDiscountProfile extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/discount_profiles");

        public SettingsDiscountProfileAccountingCode accounting_code;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        /** 1 measured, null on 1. */
        public String description;
        public String discount_type;
        public String display_name;
        public String end_time;
        public Double fixed_amount;
        public String href;
        public String id;
        /** 1 measured, null on 1. */
        public String invoice_note;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public Long max_use_per_account;
        public Long max_use_per_order;
        public Long max_use_total;
        public String name;
        public String object;
        public String owner_type;
        public Double percentage_amount;
        public Boolean price_is_inclusive_of_discount;
        /** 1 measured, null on 1. */
        public Long priority;
        public Boolean redemption_required;
        public Boolean stackable;
        public String start_time;
        public String status;
        /** 1 measured, null on 1. */
        public SettingsDiscountProfileTaxCode tax_code;
        public Long version;
    }

    /** A page of SettingsDiscountProfile records. */
    public static final class SettingsDiscountProfileList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/discount_profiles");

        public List<SettingsDiscountProfile> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The settings payment processor body. */
    public static class SettingsPaymentProcessor extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/payment_processors");

        public SettingsPaymentProcessorAccountingCode accounting_code;
        public Double conversion_rate;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        public SettingsPaymentProcessorCurrency currency;
        /** never populated in 2 measured records. */
        public Object description;
        public Boolean disconnected;
        public String display_name;
        public String gateway_type;
        public String href;
        public String id;
        public String integration_state;
        public Boolean is_default;
        public Boolean is_foreign_currency;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String provider;
        public String status;
        public Long version;
    }

    /** A page of SettingsPaymentProcessor records. */
    public static final class SettingsPaymentProcessorList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/payment_processors");

        public List<SettingsPaymentProcessor> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The settings shipping profile body. */
    public static class SettingsShippingProfile extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/shipping_profiles");

        public SettingsShippingProfileAccountingCode accounting_code;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        /** 1 measured, null on 1. */
        public String description;
        public String display_name;
        public Double fixed_amount;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object integration_connection;
        /** 1 measured, null on 1. */
        public String invoice_note;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public Boolean price_tax_inclusive;
        public Double sales_tax_rate;
        public String shipping_type;
        public String status;
        public Boolean tax_exempt;
        public SettingsShippingProfileTaxRule tax_rule;
        public Long version;
    }

    /** A page of SettingsShippingProfile records. */
    public static final class SettingsShippingProfileList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/shipping_profiles");

        public List<SettingsShippingProfile> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The settings pricing level body. */
    public static class SettingsPricingLevel extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/pricing_levels");

        /** never populated in 2 measured records. */
        public Object built_in_item;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object is_default;
        public Boolean is_tax_inclusive;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public Long ordering;
        public String status;
        public SettingsPricingLevelTaxCode tax_code;
        public Boolean tax_exempt;
        public String tax_inclusive_based_on;
    }

    /** A page of SettingsPricingLevel records. */
    public static final class SettingsPricingLevelList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/pricing_levels");

        public List<SettingsPricingLevel> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The settings time zone body. */
    public static final class SettingsTimeZone extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/time_zones");

        public String code;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        /** never populated in 2 measured records. */
        public Object description;
        public Boolean dst_enabled;
        public Long hour;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public Long minute;
        public String name;
        public String object;
        public Long offset;
        public Boolean positive;
        public String status;
        public String time_zone_pattern;
    }

    /** A page of SettingsTimeZone records. */
    public static final class SettingsTimeZoneList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/time_zones");

        public List<SettingsTimeZone> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The settings warehouse body. */
    public static class SettingsWarehouse extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/warehouses");

        public String city;
        public SettingsWarehouseCountry country;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public String href;
        public String id;
        public Boolean is_default;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String post_code;
        public String state;
        public String status;
        public Long version;
    }

    /** A page of SettingsWarehouse records. */
    public static final class SettingsWarehouseList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/warehouses");

        public List<SettingsWarehouse> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The settings currency body. */
    public static final class SettingsCurrency extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/currencies");

        public Double cash_rounding_interval;
        public String code;
        public Double conversion_rate;
        public SettingsCurrencyCountry country;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        public Long decimal_places;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String rounding;
        public String status;
        public String symbol;
    }

    /** A page of SettingsCurrency records. */
    public static final class SettingsCurrencyList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/currencies");

        public List<SettingsCurrency> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The settings item group body. */
    public static final class SettingsItemGroup extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/item_groups");

        /** 1 measured, null on 1. */
        public SettingsItemGroupAccountingCode accounting_code;
        public String code;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        /** 1 measured, null on 1. */
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
        public Long version;
    }

    /** A page of SettingsItemGroup records. */
    public static final class SettingsItemGroupList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/item_groups");

        public List<SettingsItemGroup> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The custom object body. */
    public static class CustomObject extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/custom_objects");

        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        /** never populated in 2 measured records. */
        public Object custom_component;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String relation;
        public String use_in;
    }

    /** A page of CustomObject records. */
    public static final class CustomObjectList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/custom_objects",
                "GET /api/{api_version}/accounts/{account_id}/custom_objects");

        public List<CustomObject> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The settings tax body. */
    public static class SettingsTax extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/taxes");

        public String code;
        public SettingsTaxCountry country;
        public List<SettingsTaxCountryTaxByState> country_tax_by_states;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public Double rate;
        public String status;
    }

    /** A page of SettingsTax records. */
    public static final class SettingsTaxList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/taxes");

        public List<SettingsTax> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The settings variation body. */
    public static final class SettingsVariation extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/variations");

        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        /** 1 measured, null on 1. */
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
    }

    /** A page of SettingsVariation records. */
    public static final class SettingsVariationList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/variations");

        public List<SettingsVariation> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The settings payment term body. */
    public static class SettingsPaymentTerm extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/payment_terms");

        /** never populated in 2 measured records. */
        public Object description;
        public String display_name;
        public SettingsPaymentTermDuration duration;
        public String href;
        public String id;
        public String name;
        public String object;
        public String status;
    }

    /** A page of SettingsPaymentTerm records. */
    public static final class SettingsPaymentTermList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/payment_terms");

        public List<SettingsPaymentTerm> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The settings item groups body. */
    public static final class SettingsItemGroups extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/settings/item_groups",
                "GET /api/{api_version}/settings/item_groups/{item_group_id}",
                "PATCH /api/{api_version}/settings/item_groups/{item_group_id}",
                "POST /api/{api_version}/settings/item_groups/{item_group_id}/billing_preferences");

        @Override
        protected String envelopeKey() {
            return "item_group";
        }

        public SettingsItemGroupsAccountingCode accounting_code;
        public Map<String, Object> billing_preferences;
        public String code;
        public String created_by;
        public String created_on;
        public List<Object> custom_attributes;
        public String description;
        public String display_name;
        public String href;
        public String id;
        public String image_uri;
        public String last_updated_by;
        public String last_updated_on;
        public String manager;
        public String name;
        public String object;
        public String status;
        public String uuid;
        public Long version;
    }

    /** The settings custom logic script body. */
    public static class SettingsCustomLogicScript extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/custom_logic_scripts/{custom_logic_script_id}");

        @Override
        protected String envelopeKey() {
            return "custom_logic_script";
        }

        public String action_name;
        /** never populated in 1 measured record. */
        public Object created_by;
        public String created_on;
        /** never populated in 1 measured record. */
        public Object description;
        public String display_name;
        public String href;
        public String id;
        /** never populated in 1 measured record. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String object_name;
        public String status;
        public String type;
    }

    /** The settings currency read body. */
    public static final class SettingsCurrencyRead extends Dto {
        @Override
        protected String envelopeKey() {
            return "currency";
        }

        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/currencies/{currency_id}");

        public Double cash_rounding_interval;
        public String code;
        public Double conversion_rate;
        public SettingsCurrencyReadCountry country;
        /** never populated in 6 measured records. */
        public Object created_by;
        public String created_on;
        public Long decimal_places;
        public String href;
        public String id;
        /** never populated in 6 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String rounding;
        public String status;
        public String symbol;
    }

    /** The settings custom object customize body. */
    public static final class SettingsCustomObjectCustomize extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/settings/custom_objects/{custom_object_id}/customize");

        @Override
        protected String envelopeKey() {
            return "custom_object";
        }

        public SettingsCustomObjectCustomizeConfiguration configuration;
        public String created;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String relation;
        public String status;
        public String updated;
        public String use_in;
        public String uuid;
    }

    /** The settings custom objects body. */
    public static final class SettingsCustomObjects extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/custom_objects/{custom_object_id}",
                "POST /api/{api_version}/settings/custom_objects",
                "PATCH /api/{api_version}/settings/custom_objects/{custom_object_id}");

        @Override
        protected String envelopeKey() {
            return "custom_object";
        }

        public String created;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object custom_component;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String relation;
        public String status;
        public String updated;
        public String use_in;
        public String uuid;
    }

    /** The settings variations body. */
    public static final class SettingsVariations extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/settings/variations",
                "GET /api/{api_version}/settings/variations/{variation_id}",
                "PATCH /api/{api_version}/settings/variations/{variation_id}");

        @Override
        protected String envelopeKey() {
            return "variation";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public List<Object> options;
        public String status;
        public String uuid;
    }

    /** The settings time zone read body. */
    public static final class SettingsTimeZoneRead extends Dto {
        @Override
        protected String envelopeKey() {
            return "time_zone";
        }

        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/time_zones/{time_zone_id}");

        public String code;
        /** never populated in 25 measured records. */
        public Object created_by;
        public String created_on;
        /** never populated in 25 measured records. */
        public Object description;
        public Boolean dst_enabled;
        public Long hour;
        public String href;
        public String id;
        /** never populated in 25 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public Long minute;
        public String name;
        public String object;
        public Long offset;
        public Boolean positive;
        public String status;
        public String time_zone_pattern;
    }

    /** The settings custom logic scripts body. */
    public static final class SettingsCustomLogicScripts extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/custom_logic_scripts");

        public List<Object> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The settings body. */
    public static final class Settings extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings");

        @Override
        protected String envelopeKey() {
            return "settings";
        }

        public String object;
        public List<SettingsResource> resources;
    }

    /** The settings field settings item body. */
    public static final class SettingsFieldSettingsItem extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "PATCH /api/{api_version}/settings/field_settings/account",
                "PATCH /api/{api_version}/settings/field_settings/item",
                "PATCH /api/{api_version}/settings/general_settings/account");

        public List<SettingsFieldSettingsItemData> data;
        public SettingsFieldSettingsItemGeneralSettings general_settings;
        public String message;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object pagination;
        public String url;
    }

    /**
     * What {@code update_settings_general_settings_item} answers, under {@code general_settings}.
     */
    public static final class SettingsGeneralSettingsItemUpdated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "PATCH /api/{api_version}/settings/general_settings/item");

        @Override
        protected String envelopeKey() {
            return "general_settings";
        }

        public SettingsGeneralSettingsItemUpdatedDetail item;
        public SettingsGeneralSettingsItemUpdatedProduct product;
    }

    /** One entry of a {@link Settings}'s {@code resources}. */
    public static final class SettingsResource extends Dto {
        public String href;
        public String name;
    }

    /** A {@link SettingsCurrencyRead}'s {@code country}. */
    public static final class SettingsCurrencyReadCountry extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link SettingsItemGroups}'s {@code accounting_code}. */
    public static final class SettingsItemGroupsAccountingCode extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link SettingsCustomObjectCustomize}'s {@code configuration}. */
    public static final class SettingsCustomObjectCustomizeConfiguration extends Dto {
        public List<SettingsCustomObjectCustomizeConfigurationAttribute> attributes;
    }

    /** A {@link SettingsFieldSettingsItem}'s {@code general_settings}. */
    public static final class SettingsFieldSettingsItemGeneralSettings extends Dto {
        public SettingsFieldSettingsItemGeneralSettingsAccount account;
    }

    /** One entry of a {@link SettingsFieldSettingsItem}'s {@code data}. */
    public static class SettingsFieldSettingsItemData extends Dto {
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object description;
        public String display_code;
        /** Null in every record measured, so untyped. */
        public Object editable;
        public Boolean enabled;
        public String entity;
        public String field_name;
        public String field_type;
        public Boolean global_search;
        public String group_key;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public Boolean mandatory;
        /** Null in every record measured, so untyped. */
        public Object note;
        public String object;
        public Long sort_order;
    }

    /** A {@link SettingsGeneralSettingsItemUpdated}'s {@code item}. */
    public static final class SettingsGeneralSettingsItemUpdatedDetail extends Dto {
        public SettingsGeneralSettingsItemUpdatedDetailAvailableChargeTypes available_charge_types;
        public SettingsGeneralSettingsItemUpdatedDetailAvailablePricingMethods available_pricing_methods;
        public String charging_period_can_vary_from_billing_period;
        public String charging_start_date_can_vary_from_billing_start_date;
        public String enable_back_order;
        public String enable_batch_tracking;
        public String enable_bundle_item;
        public String enable_direct_cost_item;
        public String enable_family_cost_item;
        public String enable_fixed_asset_item;
        public String enable_gift_certificate_based_item;
        public String enable_inventory_costing;
        public String enable_inventory_management;
        public String enable_pricing_profile;
        public String enable_purchase;
        public String enable_sales;
        public String enable_serialization;
        public String enable_supplier_management;
        public String enable_variation;
        public String enable_variation_based_item;
        public String enable_warehouse_management;
        public String enable_workflow_approval_for_item;
        public SettingsGeneralSettingsItemUpdatedDetailInventoryAdjustmentRules inventory_adjustment_rules;
        public SettingsGeneralSettingsItemUpdatedDetailItemGrouping item_grouping;
        public SettingsGeneralSettingsItemUpdatedDetailItemManagement item_management;
        public SettingsGeneralSettingsItemUpdatedDetailPreOrder pre_order;
        public SettingsGeneralSettingsItemUpdatedDetailPricingPeriodCanVaryFromChargingPeriod pricing_period_can_vary_from_charging_period;
        public SettingsGeneralSettingsItemUpdatedDetailPurchasePriceEnteredIsInclusiveOfTax purchase_price_entered_is_inclusive_of_tax;
        public String require_name_to_be_unique;
        public SettingsGeneralSettingsItemUpdatedDetailSalePriceEnteredIsInclusiveOfTax sale_price_entered_is_inclusive_of_tax;
        public String use_on_sale_price;
        public String use_pricing_level;
        public String use_scheduling_of_future_pricing;
        public String use_shipping_profile;
        public SettingsGeneralSettingsItemUpdatedDetailUseVolumePricing use_volume_pricing;
    }

    /** A {@link SettingsGeneralSettingsItemUpdated}'s {@code product}. */
    public static final class SettingsGeneralSettingsItemUpdatedProduct extends Dto {
        public String allow_item_sell_multi_quantity;
        public String allow_plan_sell_multi_quantity;
        public String allow_product_sell_multi_quantity;
        public String allow_rate_sell_multi_quantity;
        public String item_quantity_use;
        public String plan_quantity_use;
        public String product_quantity_use;
        public String rate_quantity_use;
    }

    /** A {@link SettingsCurrency}'s {@code country}. */
    public static final class SettingsCurrencyCountry extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link SettingsDiscountProfile}'s {@code accounting_code}. */
    public static final class SettingsDiscountProfileAccountingCode extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link SettingsDiscountProfile}'s {@code tax_code}. */
    public static final class SettingsDiscountProfileTaxCode extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link SettingsItemGroup}'s {@code accounting_code}. */
    public static final class SettingsItemGroupAccountingCode extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link SettingsPaymentProcessor}'s {@code currency}. */
    public static final class SettingsPaymentProcessorCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link SettingsPaymentProcessor}'s {@code accounting_code}. */
    public static final class SettingsPaymentProcessorAccountingCode extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link SettingsPaymentTerm}'s {@code duration}. */
    public static final class SettingsPaymentTermDuration extends Dto {
        public Long count;
        public String period;
    }

    /** A {@link SettingsPricingLevel}'s {@code tax_code}. */
    public static final class SettingsPricingLevelTaxCode extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link SettingsShippingProfile}'s {@code tax_rule}. */
    public static final class SettingsShippingProfileTaxRule extends Dto {
        public String href;
        public String id;
        public String object;
    }

    /** A {@link SettingsShippingProfile}'s {@code accounting_code}. */
    public static final class SettingsShippingProfileAccountingCode extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link SettingsTax}'s {@code country}. */
    public static final class SettingsTaxCountry extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link SettingsTax}'s {@code country_tax_by_states}. */
    public static final class SettingsTaxCountryTaxByState extends Dto {
        public String created_on;
        public String id;
        public String last_updated_on;
        public String object;
        public Double rate;
        /** Null in every record measured, so untyped. */
        public Object state;
        public String status;
    }

    /** A {@link SettingsWarehouse}'s {@code country}. */
    public static final class SettingsWarehouseCountry extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link SettingsCustomObjectCustomizeConfiguration}'s {@code attributes}. */
    public static final class SettingsCustomObjectCustomizeConfigurationAttribute extends Dto {
        public String name;
        public Long order;
    }

    /** A {@link SettingsFieldSettingsItemGeneralSettings}'s {@code account}. */
    public static final class SettingsFieldSettingsItemGeneralSettingsAccount extends Dto {
        public SettingsFieldSettingsItemGeneralSettingsAccountAccountGrouping account_grouping;
        public SettingsFieldSettingsItemGeneralSettingsAccountAccountManagement account_management;
        public SettingsFieldSettingsItemGeneralSettingsAccountAdditionalContact1 additional_contact_1;
        public SettingsFieldSettingsItemGeneralSettingsAccountAdditionalContact2 additional_contact_2;
        public SettingsFieldSettingsItemGeneralSettingsAccountAdditionalContact3 additional_contact_3;
        public SettingsFieldSettingsItemGeneralSettingsAccountBillingContact billing_contact;
        public SettingsFieldSettingsItemGeneralSettingsAccountCreationOfSubsidiaryAccount creation_of_subsidiary_account;
        public String enable_workflow_approval_for_account;
        public SettingsFieldSettingsItemGeneralSettingsAccountReferralTrackingAtAccountLevel referral_tracking_at_account_level;
        public String require_email_address_for_account;
        public String require_name_to_be_unique;
        public SettingsFieldSettingsItemGeneralSettingsAccountSalesRepTrackingAtAccountLevel sales_rep_tracking_at_account_level;
        public String send_communication_as_groups;
        public SettingsFieldSettingsItemGeneralSettingsAccountShippingContact shipping_contact;
        public String unique_email_address_for_account;
        public String unique_email_address_for_contacts;
    }

    /** A {@link SettingsGeneralSettingsItemUpdatedDetail}'s {@code available_charge_types}. */
    public static final class SettingsGeneralSettingsItemUpdatedDetailAvailableChargeTypes extends Dto {
        public String metered;
        public String one_off;
        public String recurring;
    }

    /** A {@link SettingsGeneralSettingsItemUpdatedDetail}'s {@code available_pricing_methods}. */
    public static final class SettingsGeneralSettingsItemUpdatedDetailAvailablePricingMethods extends Dto {
        public String discount_based;
        public String markup_based;
        public String standard;
    }

    /** A {@link SettingsGeneralSettingsItemUpdatedDetail}'s {@code item_grouping}. */
    public static final class SettingsGeneralSettingsItemUpdatedDetailItemGrouping extends Dto {
        /** Null in every record measured, so untyped. */
        public Object default_group;
        public String enabled;
    }

    /** A {@link SettingsGeneralSettingsItemUpdatedDetail}'s {@code item_management}. */
    public static final class SettingsGeneralSettingsItemUpdatedDetailItemManagement extends Dto {
        /** Null in every record measured, so untyped. */
        public Object default_manager;
        public String enabled;
    }

    /** A {@link SettingsGeneralSettingsItemUpdatedDetail}'s {@code use_volume_pricing}. */
    public static final class SettingsGeneralSettingsItemUpdatedDetailUseVolumePricing extends Dto {
        public String all_units_fixed_pricing;
        public String all_units_per_unit_pricing;
        public String enabled;
        public String incremental_fixed_pricing;
        public String incremental_per_unit_pricing;
    }

    /**
     * A {@link SettingsGeneralSettingsItemUpdatedDetail}'s {@code
     * pricing_period_can_vary_from_charging_period}.
     */
    public static final class SettingsGeneralSettingsItemUpdatedDetailPricingPeriodCanVaryFromChargingPeriod extends Dto {
        /** Null in every record measured, so untyped. */
        public Object default_pricing_period;
        public String enabled;
    }

    /**
     * A {@link SettingsGeneralSettingsItemUpdatedDetail}'s {@code
     * sale_price_entered_is_inclusive_of_tax}.
     */
    public static final class SettingsGeneralSettingsItemUpdatedDetailSalePriceEnteredIsInclusiveOfTax extends Dto {
        public String enabled;
        /** Null in every record measured, so untyped. */
        public Object tax;
        public String tax_included_in_sales_price_is_based_on;
    }

    /**
     * A {@link SettingsGeneralSettingsItemUpdatedDetail}'s {@code
     * purchase_price_entered_is_inclusive_of_tax}.
     */
    public static final class SettingsGeneralSettingsItemUpdatedDetailPurchasePriceEnteredIsInclusiveOfTax extends Dto {
        public String enabled;
        public SettingsGeneralSettingsItemUpdatedDetailPurchasePriceEnteredIsInclusiveOfTaxDetail tax;
    }

    /** A {@link SettingsGeneralSettingsItemUpdatedDetail}'s {@code inventory_adjustment_rules}. */
    public static final class SettingsGeneralSettingsItemUpdatedDetailInventoryAdjustmentRules extends Dto {
        public String quantity_available_for_sale;
        public String quantity_available_for_sale_determination;
    }

    /** A {@link SettingsGeneralSettingsItemUpdatedDetail}'s {@code pre_order}. */
    public static final class SettingsGeneralSettingsItemUpdatedDetailPreOrder extends Dto {
        public String enabled;
        public String number_of_period;
        public String period;
    }

    /** A {@link SettingsFieldSettingsItemGeneralSettingsAccount}'s {@code account_grouping}. */
    public static final class SettingsFieldSettingsItemGeneralSettingsAccountAccountGrouping extends Dto {
        /** Null in every record measured, so untyped. */
        public Object default_group;
        public String enabled;
    }

    /**
     * A {@link SettingsFieldSettingsItemGeneralSettingsAccount}'s {@code
     * creation_of_subsidiary_account}.
     */
    public static final class SettingsFieldSettingsItemGeneralSettingsAccountCreationOfSubsidiaryAccount extends Dto {
        /** Null in every record measured, so untyped. */
        public Object default_group;
        public String enabled;
    }

    /** A {@link SettingsFieldSettingsItemGeneralSettingsAccount}'s {@code account_management}. */
    public static final class SettingsFieldSettingsItemGeneralSettingsAccountAccountManagement extends Dto {
        /** Null in every record measured, so untyped. */
        public Object default_manager;
        public String enabled;
    }

    /**
     * A {@link SettingsFieldSettingsItemGeneralSettingsAccount}'s {@code
     * sales_rep_tracking_at_account_level}.
     */
    public static final class SettingsFieldSettingsItemGeneralSettingsAccountSalesRepTrackingAtAccountLevel extends Dto {
        /** Null in every record measured, so untyped. */
        public Object default_sales_rep_tracking;
        public String enabled;
    }

    /**
     * A {@link SettingsFieldSettingsItemGeneralSettingsAccount}'s {@code
     * referral_tracking_at_account_level}.
     */
    public static final class SettingsFieldSettingsItemGeneralSettingsAccountReferralTrackingAtAccountLevel extends Dto {
        /** Null in every record measured, so untyped. */
        public Object default_referral_tracking;
        public String enabled;
    }

    /** A {@link SettingsFieldSettingsItemGeneralSettingsAccount}'s {@code billing_contact}. */
    public static final class SettingsFieldSettingsItemGeneralSettingsAccountBillingContact extends Dto {
        public String display_name;
        public String enabled;
        public String require_email_address_for_billing_contact;
        public String type;
    }

    /** A {@link SettingsFieldSettingsItemGeneralSettingsAccount}'s {@code shipping_contact}. */
    public static final class SettingsFieldSettingsItemGeneralSettingsAccountShippingContact extends Dto {
        public String display_name;
        public String enabled;
        public String require_email_address_for_shipping_contact;
        public String type;
    }

    /** A {@link SettingsFieldSettingsItemGeneralSettingsAccount}'s {@code additional_contact_1}. */
    public static final class SettingsFieldSettingsItemGeneralSettingsAccountAdditionalContact1 extends Dto {
        public String display_name;
        public String enabled;
        public String require_email_address_for_additional_contact_1;
        public String type;
    }

    /** A {@link SettingsFieldSettingsItemGeneralSettingsAccount}'s {@code additional_contact_2}. */
    public static final class SettingsFieldSettingsItemGeneralSettingsAccountAdditionalContact2 extends Dto {
        public String display_name;
        public String enabled;
        public String require_email_address_for_additional_contact_2;
        public String type;
    }

    /** A {@link SettingsFieldSettingsItemGeneralSettingsAccount}'s {@code additional_contact_3}. */
    public static final class SettingsFieldSettingsItemGeneralSettingsAccountAdditionalContact3 extends Dto {
        public String display_name;
        public String enabled;
        public String require_email_address_for_additional_contact_3;
        public String type;
    }

    /**
     * A {@link SettingsGeneralSettingsItemUpdatedDetailPurchasePriceEnteredIsInclusiveOfTax}'s
     * {@code tax}.
     */
    public static final class SettingsGeneralSettingsItemUpdatedDetailPurchasePriceEnteredIsInclusiveOfTaxDetail extends Dto {
        public String uuid;
    }

    private SettingsDto() {
    }

    /** One custom object definition, as {@code /settings/custom_objects} serves it. */
    public static class SettingsCustomObjectDefinition extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/custom_objects",
                "GET /api/{api_version}/settings/custom_objects/{custom_object_id}");

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String relation;
        public String status;
        public String use_in;
    }

    /**
     * One page of {@code SettingsCustomObjectDefinition} records, under {@code data}; paged by
     * cursor.
     */
    public static final class SettingsCustomObjectDefinitionList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/custom_objects");

        public List<SettingsCustomObjectDefinition> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** One custom object definition, under {@code custom_object}: the record the listing serves. */
    public static final class SettingsCustomObjectDefinitionRead extends SettingsCustomObjectDefinition {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/custom_objects/{custom_object_id}");

        @Override
        protected String envelopeKey() {
            return "custom_object";
        }
    }

    /** One discount profile, under {@code discount_profile}: the record the listing serves. */
    public static final class SettingsDiscountProfileRead extends SettingsDiscountProfile {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/discount_profiles/{discount_profile_id}",
                "POST /api/{api_version}/settings/discount_profiles",
                "PATCH /api/{api_version}/settings/discount_profiles/{discount_profile_id}");

        @Override
        protected String envelopeKey() {
            return "discount_profile";
        }
    }

    /** One payment processor, under {@code payment_processor}: the record the listing serves. */
    public static final class SettingsPaymentProcessorRead extends SettingsPaymentProcessor {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/payment_processors/{payment_processor_id}");

        @Override
        protected String envelopeKey() {
            return "payment_processor";
        }
    }

    /** One payment term, under {@code payment_term}: the record the listing serves. */
    public static class SettingsPaymentTermRead extends SettingsPaymentTerm {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/payment_terms/{payment_term_id}");

        @Override
        protected String envelopeKey() {
            return "payment_term";
        }
    }

    /** One pricing level, under {@code pricing_level}: the record the listing serves. */
    public static final class SettingsPricingLevelRead extends SettingsPricingLevel {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/pricing_levels/{pricing_level_id}",
                "PATCH /api/{api_version}/settings/pricing_levels/{pricing_level_id}");

        @Override
        protected String envelopeKey() {
            return "pricing_level";
        }
    }

    /** One shipping profile, under {@code shipping_profile}: the record the listing serves. */
    public static final class SettingsShippingProfileRead extends SettingsShippingProfile {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/shipping_profiles/{shipping_profile_id}",
                "POST /api/{api_version}/settings/shipping_profiles");

        @Override
        protected String envelopeKey() {
            return "shipping_profile";
        }
    }

    /** One tax, under {@code tax}: the record the listing serves. */
    public static final class SettingsTaxRead extends SettingsTax {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/taxes/{tax_id}");

        @Override
        protected String envelopeKey() {
            return "tax";
        }
    }

    /** One warehouse, under {@code warehouse}: the record the listing serves. */
    public static final class SettingsWarehouseRead extends SettingsWarehouse {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/warehouses/{warehouse_id}",
                "POST /api/{api_version}/settings/warehouses",
                "PATCH /api/{api_version}/settings/warehouses/{warehouse_id}");

        @Override
        protected String envelopeKey() {
            return "warehouse";
        }
    }

    /**
     * One page of {@code SettingsFieldSettingsItemData} records, under {@code data}; paged by
     * cursor.
     */
    public static final class SettingsFieldSettingList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/field_settings");

        public List<SettingsFieldSettingsItemData> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** One field setting, under {@code field_setting}: the record the listing serves. */
    public static final class SettingsFieldSettingRead extends SettingsFieldSettingsItemData {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/field_settings/{field_setting_id}");

        @Override
        protected String envelopeKey() {
            return "field_setting";
        }
    }

    /**
     * What the custom logic script writes answer, under {@code custom_logic_script}: the read's
     * fields and {@code code}, which the server does not store.
     */
    public static final class SettingsCustomLogicScriptSaved extends SettingsCustomLogicScript {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/settings/custom_logic_scripts",
                "PATCH /api/{api_version}/settings/custom_logic_scripts/{custom_logic_script_id}");

        @Override
        protected String envelopeKey() {
            return "custom_logic_script";
        }

        /** Null in every record measured, so untyped. */
        public Object code;
    }

    /**
     * What {@code updateSettingsPaymentTerm} answers, under {@code payment_term}: the read's fields
     * and {@code display_order}.
     */
    public static final class SettingsPaymentTermUpdated extends SettingsPaymentTermRead {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "PATCH /api/{api_version}/settings/payment_terms/{payment_term_id}");

        @Override
        protected String envelopeKey() {
            return "payment_term";
        }

        public Long display_order;
    }

    /**
     * What {@code createSettingsPaymentProcessor} answers, under {@code payment_processor}:
     * {@code gateway_name} and {@code credentials} where the read has {@code provider}.
     */
    public static final class SettingsPaymentProcessorSaved extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/settings/payment_processors",
                "PATCH /api/{api_version}/settings/payment_processors/{payment_processor_id}");

        @Override
        protected String envelopeKey() {
            return "payment_processor";
        }

        /** Null in every record measured, so untyped. */
        public Object accounting_code;
        public Double conversion_rate;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object credentials;
        public SettingsPaymentProcessorSavedCurrency currency;
        public String description;
        public Boolean disconnected;
        public String display_name;
        public String gateway_name;
        public String gateway_type;
        public String href;
        public String id;
        public String integration_state;
        public Boolean is_default;
        public Boolean is_foreign_currency;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String provider;
        public String status;
        public Long version;
    }

    /** A {@link SettingsPaymentProcessorSaved}'s {@code currency}. */
    public static final class SettingsPaymentProcessorSavedCurrency extends Dto {
        public String code;
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /**
     * What {@code createSettingsPaymentTerm} answers, under {@code payment_term}: a flat
     * {@code period} and {@code duration} where the read nests them.
     */
    public static final class SettingsPaymentTermCreated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/settings/payment_terms");

        @Override
        protected String envelopeKey() {
            return "payment_term";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object description;
        public String display_name;
        public Long duration;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object ordering;
        public String period;
        public String status;
    }

    /**
     * What {@code createSettingsPricingLevel} answers, under {@code pricing_level}:
     * {@code price_tax_inclusive} where the read has three tax fields.
     */
    public static final class SettingsPricingLevelCreated extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "POST /api/{api_version}/settings/pricing_levels");

        @Override
        protected String envelopeKey() {
            return "pricing_level";
        }

        /** Null in every record measured, so untyped. */
        public Object built_in_item;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object description;
        public String display_name;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object is_default;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public Long ordering;
        /** Null in every record measured, so untyped. */
        public Object price_tax_inclusive;
        public String status;
        /** Null in every record measured, so untyped. */
        public Object tax_exempt;
    }

    /**
     * One group of general settings, under {@code general_settings}, keyed by the group;
     * {@code item} and {@code order} also carry {@code product}.
     */
    public static final class SettingsGeneralSettings extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/general_settings/{group}");

        @Override
        protected String envelopeKey() {
            return "general_settings";
        }

        public SettingsFieldSettingsItemGeneralSettingsAccount account;
        public SettingsGeneralSettingsDashboard dashboard;
        public SettingsGeneralSettingsDateAndTime date_and_time;
        /** Keys are the instance's own setting names, so a mapping. */
        public Map<String, Object> display;
        /** Keys are the instance's own setting names, so a mapping. */
        public Map<String, Object> general;
        public SettingsGeneralSettingsItemUpdatedDetail item;
        public SettingsGeneralSettingsLabour labour;
        public SettingsGeneralSettingsOrder order;
        public SettingsGeneralSettingsPortal portal;
        public SettingsGeneralSettingsItemUpdatedProduct product;
        public SettingsGeneralSettingsPurchase purchase;
        public SettingsGeneralSettingsSecurity security;
        public SettingsGeneralSettingsTransaction transaction;
    }

    /** A {@link SettingsGeneralSettings}'s {@code dashboard}. */
    public static final class SettingsGeneralSettingsDashboard extends Dto {
        public String accounting;
        /** Null in every record measured, so untyped. */
        @WireName("defaultDashboard")
        public Object defaultDashboard;
        public String executive;
        public String finance;
        public String production;
        public String purchase;
        public String sales;
    }

    /** A {@link SettingsGeneralSettings}'s {@code date_and_time}. */
    public static final class SettingsGeneralSettingsDateAndTime extends Dto {
        public String date_format;
        /** Null in every record measured, so untyped. */
        public Object first_day_of_week;
        public String time_format;
        public String time_zone;
    }

    /** A {@link SettingsGeneralSettings}'s {@code labour}. */
    public static final class SettingsGeneralSettingsLabour extends Dto {
        public String require_email_address_for_labour;
        public String unique_email_address;
        public String unique_labour_name;
    }

    /** A {@link SettingsGeneralSettings}'s {@code order}. */
    public static final class SettingsGeneralSettingsOrder extends Dto {
        public String allow_name_to_be_changed_in_order;
        public String allow_unit_price_to_be_changed_in_order;
        public String default_fulfillment_status;
        /** Null in every record measured, so untyped. */
        public Object default_shipping_profile;
        public String displayed_price_is_inclusive_of_tax;
        public String enable_booking;
        public String enable_last_sale_price_look_up_for_an_account;
        public String enable_order_backdating;
        public String enable_quote;
        public SettingsGeneralSettingsOrderOrderManagement order_management;
        public SettingsGeneralSettingsOrderReferralTrackingAtOrderLevel referral_tracking_at_order_level;
        public SettingsGeneralSettingsOrderSalesRepTrackingAtOrderLevel sales_rep_tracking_at_order_level;
        public String use_global_warehouse;
    }

    /** A {@link SettingsGeneralSettingsOrder}'s {@code order_management}. */
    public static final class SettingsGeneralSettingsOrderOrderManagement extends Dto {
        /** Null in every record measured, so untyped. */
        public Object default_manager;
        public String enabled;
    }

    /** A {@link SettingsGeneralSettingsOrder}'s {@code referral_tracking_at_order_level}. */
    public static final class SettingsGeneralSettingsOrderReferralTrackingAtOrderLevel extends Dto {
        /** Null in every record measured, so untyped. */
        public Object default_referral_tracking;
        public String enabled;
    }

    /** A {@link SettingsGeneralSettingsOrder}'s {@code sales_rep_tracking_at_order_level}. */
    public static final class SettingsGeneralSettingsOrderSalesRepTrackingAtOrderLevel extends Dto {
        /** Null in every record measured, so untyped. */
        public Object default_sales_rep_tracking;
        public String enabled;
    }

    /** A {@link SettingsGeneralSettings}'s {@code portal}. */
    public static final class SettingsGeneralSettingsPortal extends Dto {
        /** Keys are the instance's own setting names, so a mapping. */
        public Map<String, Object> contacts;
        @WireName("enableCustomerPortal")
        public String enableCustomerPortal;
        @WireName("enableInviteToActivePortalForContact")
        public String enableInviteToActivePortalForContact;
        @WireName("enableSpecifyPortalPasswordForContact")
        public String enableSpecifyPortalPasswordForContact;
        /** Keys are the instance's own setting names, so a mapping. */
        public Map<String, Object> settings;
    }

    /** A {@link SettingsGeneralSettings}'s {@code purchase}. */
    public static final class SettingsGeneralSettingsPurchase extends Dto {
        public SettingsGeneralSettingsPurchaseAllowModificationOfFollowingPurchaseCreditNoteFields allow_modification_of_following_purchase_credit_note_fields;
        public SettingsGeneralSettingsPurchaseAllowModificationOfFollowingPurchaseInvoiceFields allow_modification_of_following_purchase_invoice_fields;
        public SettingsGeneralSettingsPurchaseAllowModificationOfFollowingPurchasePaymentFields allow_modification_of_following_purchase_payment_fields;
        public SettingsGeneralSettingsPurchaseAllowModificationOfFollowingPurchaseRefundFields allow_modification_of_following_purchase_refund_fields;
        public String enable_last_purchase_price_look_up;
        public String enable_purchase_quote;
        public String enable_workflow_approval_for_purchase_credit_note;
        public String enable_workflow_approval_for_purchase_invoice;
        public String enable_workflow_approval_for_purchase_order;
        public String enable_workflow_approval_for_purchase_payment;
        public String enable_workflow_approval_for_purchase_refund;
        public String inventory_is_adjusted_when;
        public String supplier_invoice_id_should_be_unique;
        public String use_global_warehouse;
    }

    /**
     * A {@link SettingsGeneralSettingsPurchase}'s
     * {@code allow_modification_of_following_purchase_credit_note_fields}.
     */
    public static final class SettingsGeneralSettingsPurchaseAllowModificationOfFollowingPurchaseCreditNoteFields extends Dto {
        public String refundable;
    }

    /**
     * A {@link SettingsGeneralSettingsPurchase}'s
     * {@code allow_modification_of_following_purchase_invoice_fields}.
     */
    public static final class SettingsGeneralSettingsPurchaseAllowModificationOfFollowingPurchaseInvoiceFields extends Dto {
        public String due_date;
        public String issue_date;
    }

    /**
     * A {@link SettingsGeneralSettingsPurchase}'s
     * {@code allow_modification_of_following_purchase_payment_fields}.
     */
    public static final class SettingsGeneralSettingsPurchaseAllowModificationOfFollowingPurchasePaymentFields extends Dto {
        public String payment_date;
        public String payment_processor;
        public String payment_reference;
    }

    /**
     * A {@link SettingsGeneralSettingsPurchase}'s
     * {@code allow_modification_of_following_purchase_refund_fields}.
     */
    public static final class SettingsGeneralSettingsPurchaseAllowModificationOfFollowingPurchaseRefundFields extends Dto {
        public String refund_date;
        public String refund_reference;
    }

    /** A {@link SettingsGeneralSettings}'s {@code security}. */
    public static final class SettingsGeneralSettingsSecurity extends Dto {
        public String enable_force_session_expire;
        public String enable_two_factor_authentication;
        @WireName("implementerLogin")
        public String implementerLogin;
        public String session_expire_limit_hour;
    }

    /** A {@link SettingsGeneralSettings}'s {@code transaction}. */
    public static final class SettingsGeneralSettingsTransaction extends Dto {
        /** Null in every record measured, so untyped. */
        public Object accounting_method;
        public SettingsGeneralSettingsTransactionAllowModificationOfFollowingCreditNoteFields allow_modification_of_following_credit_note_fields;
        public SettingsGeneralSettingsTransactionAllowModificationOfFollowingInvoiceFields allow_modification_of_following_invoice_fields;
        public SettingsGeneralSettingsTransactionAllowModificationOfFollowingPaymentFields allow_modification_of_following_payment_fields;
        public SettingsGeneralSettingsTransactionAllowModificationOfFollowingRefundFields allow_modification_of_following_refund_fields;
        public SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreference available_mediums_for_communication_preference;
        public String create_negative_charge_to_accommodate_adjustment_required_for_order_cancel_or_change;
        public SettingsGeneralSettingsTransactionDefaultAccountingCodes default_accounting_codes;
        /** Null in every record measured, so untyped. */
        public Object default_shipping_profile;
        public String disable_notification_for_invoice_with_charges_of_zero_amount;
        public String displayed_price_is_inclusive_of_tax;
        public String enable_creation_of_automatic_payment_surcharge;
        public String enable_discount_profile;
        public String update_current_invoice_to_include_charge_adjustments;
        public String update_invoice_to_include_current_payment_surcharge;
    }

    /**
     * A {@link SettingsGeneralSettingsTransaction}'s
     * {@code allow_modification_of_following_credit_note_fields}.
     */
    public static final class SettingsGeneralSettingsTransactionAllowModificationOfFollowingCreditNoteFields extends Dto {
        public String credit_note_date;
        public String refundable;
    }

    /**
     * A {@link SettingsGeneralSettingsTransaction}'s
     * {@code allow_modification_of_following_invoice_fields}.
     */
    public static final class SettingsGeneralSettingsTransactionAllowModificationOfFollowingInvoiceFields extends Dto {
        public String billing_period;
        public String charge_period;
        public String due_date;
        public String issue_date;
    }

    /**
     * A {@link SettingsGeneralSettingsTransaction}'s
     * {@code allow_modification_of_following_payment_fields}.
     */
    public static final class SettingsGeneralSettingsTransactionAllowModificationOfFollowingPaymentFields extends Dto {
        public String payment_date;
        public String payment_processor;
        public String payment_reference;
    }

    /**
     * A {@link SettingsGeneralSettingsTransaction}'s
     * {@code allow_modification_of_following_refund_fields}.
     */
    public static final class SettingsGeneralSettingsTransactionAllowModificationOfFollowingRefundFields extends Dto {
        public String refund_date;
        public String refund_reference;
    }

    /**
     * A {@link SettingsGeneralSettingsTransaction}'s
     * {@code available_mediums_for_communication_preference}.
     */
    public static final class SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreference extends Dto {
        public SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreferenceEmail email;
        public SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreferencePostalMail postal_mail;
        public SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreferenceTextMessage text_message;
        public SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreferenceVoiceMail voice_mail;
    }

    /**
     * A {@link SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreference}'s
     * {@code email}.
     */
    public static final class SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreferenceEmail extends Dto {
        @WireName("default")
        public String isDefault;
        public String enabled;
    }

    /**
     * A {@link SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreference}'s
     * {@code postal_mail}.
     */
    public static final class SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreferencePostalMail extends Dto {
        @WireName("default")
        public String isDefault;
        public String enabled;
    }

    /**
     * A {@link SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreference}'s
     * {@code text_message}.
     */
    public static final class SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreferenceTextMessage extends Dto {
        @WireName("default")
        public String isDefault;
        public String enabled;
    }

    /**
     * A {@link SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreference}'s
     * {@code voice_mail}.
     */
    public static final class SettingsGeneralSettingsTransactionAvailableMediumsForCommunicationPreferenceVoiceMail extends Dto {
        @WireName("default")
        public String isDefault;
        public String enabled;
    }

    /** A {@link SettingsGeneralSettingsTransaction}'s {@code default_accounting_codes}. */
    public static final class SettingsGeneralSettingsTransactionDefaultAccountingCodes extends Dto {
        public String account_payable;
        public String account_receivable;
        public String cash_and_cash_equivalent;
        public String cost_of_goods_sold;
        public String credit_note_payable;
        public String deduction;
        public String event_charge;
        public String inventory;
        public String payment_surcharge;
        public String rounding;
        public String sales_revenue;
        public String tax;
    }
}
