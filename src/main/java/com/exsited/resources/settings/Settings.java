package com.exsited.resources.settings;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.settings.settings_dto.SettingsDto.CustomObjectList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCurrencyList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCurrencyRead;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCustomLogicScript;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCustomLogicScriptSaved;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCustomLogicScripts;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCustomObjectCustomize;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCustomObjectDefinitionList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCustomObjectDefinitionRead;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCustomObjects;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsDiscountProfileList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsDiscountProfileRead;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsFieldSettingList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsFieldSettingRead;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsFieldSettingsItem;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsGeneralSettings;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsGeneralSettingsItemUpdated;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsItemGroupList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsItemGroups;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsPaymentProcessorList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsPaymentProcessorRead;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsPaymentProcessorSaved;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsPaymentTermCreated;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsPaymentTermList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsPaymentTermRead;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsPaymentTermUpdated;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsPricingLevelCreated;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsPricingLevelList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsPricingLevelRead;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsShippingProfileList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsShippingProfileRead;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsTaxList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsTaxRead;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsTimeZoneList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsTimeZoneRead;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsVariationList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsVariations;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsWarehouseList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsWarehouseRead;
import com.exsited.resources.settings.settings_dto.SettingsDto;
import java.util.Map;

/** Settings endpoints, under {@code /api/{api_version}}. */
public final class Settings implements BoundResource {

    private final ExsitedClient client;

    public Settings(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** Custom object definitions: {@code GET /custom_objects}. */
    public ApiResponse<CustomObjectList> getCustomObjects(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.CUSTOM_OBJECTS,
                reopen(options).queryParams(queryParams).build(), CustomObjectList.class);
    }

    public ApiResponse<CustomObjectList> getCustomObjects(
            Map<String, Object> queryParams) {
        return getCustomObjects(queryParams, null);
    }

    public ApiResponse<CustomObjectList> getCustomObjects() {
        return getCustomObjects(null, null);
    }

    /** All settings: {@code GET /settings}. */
    public ApiResponse<SettingsDto.Settings> getSettings(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS,
                reopen(options).queryParams(queryParams).build(), SettingsDto.Settings.class);
    }

    public ApiResponse<SettingsDto.Settings> getSettings(
            Map<String, Object> queryParams) {
        return getSettings(queryParams, null);
    }

    public ApiResponse<SettingsDto.Settings> getSettings() {
        return getSettings(null, null);
    }

    /** Single custom object definition: {@code GET /custom_objects/{custom_object_id}}. */
    public ApiResponse<SettingsCustomObjects> getCustomObject(
            String customObjectId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(customObjectId, "custom_object_id");
        return client.sendGetRequest(SettingsUrls.CUSTOM_OBJECT,
                reopen(options).pathParam("custom_object_id", customObjectId).queryParams(queryParams).build(), SettingsCustomObjects.class);
    }

    public ApiResponse<SettingsCustomObjects> getCustomObject(
            String customObjectId, Map<String, Object> queryParams) {
        return getCustomObject(customObjectId, queryParams, null);
    }

    public ApiResponse<SettingsCustomObjects> getCustomObject(String customObjectId) {
        return getCustomObject(customObjectId, null, null);
    }

    /** List currencies: {@code GET /settings/currencies}. */
    public ApiResponse<SettingsCurrencyList> getSettingsCurrencies(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_CURRENCIES,
                reopen(options).queryParams(queryParams).build(), SettingsCurrencyList.class);
    }

    public ApiResponse<SettingsCurrencyList> getSettingsCurrencies(
            Map<String, Object> queryParams) {
        return getSettingsCurrencies(queryParams, null);
    }

    public ApiResponse<SettingsCurrencyList> getSettingsCurrencies() {
        return getSettingsCurrencies(null, null);
    }

    /** List custom logic scripts: {@code GET /settings/custom_logic_scripts}. */
    public ApiResponse<SettingsCustomLogicScripts> getSettingsCustomLogicScripts(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_CUSTOM_LOGIC_SCRIPTS,
                reopen(options).queryParams(queryParams).build(), SettingsCustomLogicScripts.class);
    }

    public ApiResponse<SettingsCustomLogicScripts> getSettingsCustomLogicScripts(
            Map<String, Object> queryParams) {
        return getSettingsCustomLogicScripts(queryParams, null);
    }

    public ApiResponse<SettingsCustomLogicScripts> getSettingsCustomLogicScripts() {
        return getSettingsCustomLogicScripts(null, null);
    }

    /** Create custom object: {@code POST /settings/custom_objects}. Recorded 201. */
    public ApiResponse<SettingsCustomObjects> createSettingsCustomObject(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_CUSTOM_OBJECTS,
                reopen(options).jsonBody(payload).build(), SettingsCustomObjects.class);
    }

    public ApiResponse<SettingsCustomObjects> createSettingsCustomObject(
            Object payload) {
        return createSettingsCustomObject(payload, null);
    }

    /** List discount profiles: {@code GET /settings/discount_profiles}. */
    public ApiResponse<SettingsDiscountProfileList> getSettingsDiscountProfiles(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_DISCOUNT_PROFILES,
                reopen(options).queryParams(queryParams).build(), SettingsDiscountProfileList.class);
    }

    public ApiResponse<SettingsDiscountProfileList> getSettingsDiscountProfiles(
            Map<String, Object> queryParams) {
        return getSettingsDiscountProfiles(queryParams, null);
    }

    public ApiResponse<SettingsDiscountProfileList> getSettingsDiscountProfiles() {
        return getSettingsDiscountProfiles(null, null);
    }

    /** List item groups: {@code GET /settings/item_groups}. */
    public ApiResponse<SettingsItemGroupList> getSettingsItemGroups(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_ITEM_GROUPS,
                reopen(options).queryParams(queryParams).build(), SettingsItemGroupList.class);
    }

    public ApiResponse<SettingsItemGroupList> getSettingsItemGroups(
            Map<String, Object> queryParams) {
        return getSettingsItemGroups(queryParams, null);
    }

    public ApiResponse<SettingsItemGroupList> getSettingsItemGroups() {
        return getSettingsItemGroups(null, null);
    }

    /** Create item group: {@code POST /settings/item_groups}. Recorded 201. */
    public ApiResponse<SettingsItemGroups> createSettingsItemGroup(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_ITEM_GROUPS,
                reopen(options).jsonBody(payload).build(), SettingsItemGroups.class);
    }

    public ApiResponse<SettingsItemGroups> createSettingsItemGroup(
            Object payload) {
        return createSettingsItemGroup(payload, null);
    }

    /** List payment processors: {@code GET /settings/payment_processors}. */
    public ApiResponse<SettingsPaymentProcessorList> getSettingsPaymentProcessors(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_PAYMENT_PROCESSORS,
                reopen(options).queryParams(queryParams).build(), SettingsPaymentProcessorList.class);
    }

    public ApiResponse<SettingsPaymentProcessorList> getSettingsPaymentProcessors(
            Map<String, Object> queryParams) {
        return getSettingsPaymentProcessors(queryParams, null);
    }

    public ApiResponse<SettingsPaymentProcessorList> getSettingsPaymentProcessors() {
        return getSettingsPaymentProcessors(null, null);
    }

    /** List payment terms: {@code GET /settings/payment_terms}. */
    public ApiResponse<SettingsPaymentTermList> getSettingsPaymentTerms(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_PAYMENT_TERMS,
                reopen(options).queryParams(queryParams).build(), SettingsPaymentTermList.class);
    }

    public ApiResponse<SettingsPaymentTermList> getSettingsPaymentTerms(
            Map<String, Object> queryParams) {
        return getSettingsPaymentTerms(queryParams, null);
    }

    public ApiResponse<SettingsPaymentTermList> getSettingsPaymentTerms() {
        return getSettingsPaymentTerms(null, null);
    }

    /** List pricing levels: {@code GET /settings/pricing_levels}. */
    public ApiResponse<SettingsPricingLevelList> getSettingsPricingLevels(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_PRICING_LEVELS,
                reopen(options).queryParams(queryParams).build(), SettingsPricingLevelList.class);
    }

    public ApiResponse<SettingsPricingLevelList> getSettingsPricingLevels(
            Map<String, Object> queryParams) {
        return getSettingsPricingLevels(queryParams, null);
    }

    public ApiResponse<SettingsPricingLevelList> getSettingsPricingLevels() {
        return getSettingsPricingLevels(null, null);
    }

    /** List shipping profiles: {@code GET /settings/shipping_profiles}. */
    public ApiResponse<SettingsShippingProfileList> getSettingsShippingProfiles(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_SHIPPING_PROFILES,
                reopen(options).queryParams(queryParams).build(), SettingsShippingProfileList.class);
    }

    public ApiResponse<SettingsShippingProfileList> getSettingsShippingProfiles(
            Map<String, Object> queryParams) {
        return getSettingsShippingProfiles(queryParams, null);
    }

    public ApiResponse<SettingsShippingProfileList> getSettingsShippingProfiles() {
        return getSettingsShippingProfiles(null, null);
    }

    /** List taxes: {@code GET /settings/taxes}. */
    public ApiResponse<SettingsTaxList> getSettingsTaxes(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_TAXES,
                reopen(options).queryParams(queryParams).build(), SettingsTaxList.class);
    }

    public ApiResponse<SettingsTaxList> getSettingsTaxes(
            Map<String, Object> queryParams) {
        return getSettingsTaxes(queryParams, null);
    }

    public ApiResponse<SettingsTaxList> getSettingsTaxes() {
        return getSettingsTaxes(null, null);
    }

    /** List time zones: {@code GET /settings/time_zones}. */
    public ApiResponse<SettingsTimeZoneList> getSettingsTimeZones(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_TIME_ZONES,
                reopen(options).queryParams(queryParams).build(), SettingsTimeZoneList.class);
    }

    public ApiResponse<SettingsTimeZoneList> getSettingsTimeZones(
            Map<String, Object> queryParams) {
        return getSettingsTimeZones(queryParams, null);
    }

    public ApiResponse<SettingsTimeZoneList> getSettingsTimeZones() {
        return getSettingsTimeZones(null, null);
    }

    /** List variations: {@code GET /settings/variations}. */
    public ApiResponse<SettingsVariationList> getSettingsVariations(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_VARIATIONS,
                reopen(options).queryParams(queryParams).build(), SettingsVariationList.class);
    }

    public ApiResponse<SettingsVariationList> getSettingsVariations(
            Map<String, Object> queryParams) {
        return getSettingsVariations(queryParams, null);
    }

    public ApiResponse<SettingsVariationList> getSettingsVariations() {
        return getSettingsVariations(null, null);
    }

    /** Create variation: {@code POST /settings/variations}. Recorded 201. */
    public ApiResponse<SettingsVariations> createSettingsVariation(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_VARIATIONS,
                reopen(options).jsonBody(payload).build(), SettingsVariations.class);
    }

    public ApiResponse<SettingsVariations> createSettingsVariation(
            Object payload) {
        return createSettingsVariation(payload, null);
    }

    /** List warehouses: {@code GET /settings/warehouses}. */
    public ApiResponse<SettingsWarehouseList> getSettingsWarehouses(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_WAREHOUSES,
                reopen(options).queryParams(queryParams).build(), SettingsWarehouseList.class);
    }

    public ApiResponse<SettingsWarehouseList> getSettingsWarehouses(
            Map<String, Object> queryParams) {
        return getSettingsWarehouses(queryParams, null);
    }

    public ApiResponse<SettingsWarehouseList> getSettingsWarehouses() {
        return getSettingsWarehouses(null, null);
    }

    /** Get single: {@code GET /settings/currencies/{currency_id}}. */
    public ApiResponse<SettingsCurrencyRead> getSettingsCurrency(
            String currencyId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(currencyId, "currency_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_CURRENCY,
                reopen(options).pathParam("currency_id", currencyId).queryParams(queryParams).build(), SettingsCurrencyRead.class);
    }

    public ApiResponse<SettingsCurrencyRead> getSettingsCurrency(
            String currencyId, Map<String, Object> queryParams) {
        return getSettingsCurrency(currencyId, queryParams, null);
    }

    public ApiResponse<SettingsCurrencyRead> getSettingsCurrency(String currencyId) {
        return getSettingsCurrency(currencyId, null, null);
    }

    /** Get single: {@code GET /settings/custom_logic_scripts/{custom_logic_script_id}}. */
    public ApiResponse<SettingsCustomLogicScript> getSettingsCustomLogicScript(
            String customLogicScriptId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(customLogicScriptId, "custom_logic_script_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_CUSTOM_LOGIC_SCRIPT,
                reopen(options).pathParam("custom_logic_script_id", customLogicScriptId).queryParams(queryParams).build(), SettingsCustomLogicScript.class);
    }

    public ApiResponse<SettingsCustomLogicScript> getSettingsCustomLogicScript(
            String customLogicScriptId, Map<String, Object> queryParams) {
        return getSettingsCustomLogicScript(customLogicScriptId, queryParams, null);
    }

    public ApiResponse<SettingsCustomLogicScript> getSettingsCustomLogicScript(String customLogicScriptId) {
        return getSettingsCustomLogicScript(customLogicScriptId, null, null);
    }

    /**
     * Delete custom object: {@code DELETE /settings/custom_objects/{custom_object_id}}. Recorded
     * 204. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsCustomObject(
            String customObjectId, RequestOptions options) {
        requireValue(customObjectId, "custom_object_id");
        return client.sendDeleteRequest(SettingsUrls.SETTINGS_CUSTOM_OBJECT,
                reopen(options).pathParam("custom_object_id", customObjectId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsCustomObject(
            String customObjectId) {
        return deleteSettingsCustomObject(customObjectId, null);
    }

    /**
     * Update custom object: {@code PATCH /settings/custom_objects/{custom_object_id}}.
     * Recorded 202.
     */
    public ApiResponse<SettingsCustomObjects> updateSettingsCustomObject(
            String customObjectId, Object payload, RequestOptions options) {
        requireValue(customObjectId, "custom_object_id");
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_CUSTOM_OBJECT,
                reopen(options).pathParam("custom_object_id", customObjectId).jsonBody(payload).build(), SettingsCustomObjects.class);
    }

    public ApiResponse<SettingsCustomObjects> updateSettingsCustomObject(
            String customObjectId, Object payload) {
        return updateSettingsCustomObject(customObjectId, payload, null);
    }

    /** Update account field settings: {@code PATCH /settings/field_settings/account}. */
    public ApiResponse<SettingsFieldSettingsItem> updateSettingsFieldSettingsAccount(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_FIELD_SETTINGS_ACCOUNT,
                reopen(options).jsonBody(payload).build(), SettingsFieldSettingsItem.class);
    }

    public ApiResponse<SettingsFieldSettingsItem> updateSettingsFieldSettingsAccount(
            Object payload) {
        return updateSettingsFieldSettingsAccount(payload, null);
    }

    /** Update item field settings: {@code PATCH /settings/field_settings/item}. */
    public ApiResponse<SettingsFieldSettingsItem> updateSettingsFieldSettingsItem(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_FIELD_SETTINGS_ITEM,
                reopen(options).jsonBody(payload).build(), SettingsFieldSettingsItem.class);
    }

    public ApiResponse<SettingsFieldSettingsItem> updateSettingsFieldSettingsItem(
            Object payload) {
        return updateSettingsFieldSettingsItem(payload, null);
    }

    /** Update account general settings: {@code PATCH /settings/general_settings/account}. */
    public ApiResponse<SettingsFieldSettingsItem> updateSettingsGeneralSettingsAccount(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_GENERAL_SETTINGS_ACCOUNT,
                reopen(options).jsonBody(payload).build(), SettingsFieldSettingsItem.class);
    }

    public ApiResponse<SettingsFieldSettingsItem> updateSettingsGeneralSettingsAccount(
            Object payload) {
        return updateSettingsGeneralSettingsAccount(payload, null);
    }

    /**
     * Update item general settings: {@code PATCH /settings/general_settings/item}.
     */
    public ApiResponse<SettingsGeneralSettingsItemUpdated> updateSettingsGeneralSettingsItem(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_GENERAL_SETTINGS_ITEM,
                reopen(options).jsonBody(payload).build(), SettingsGeneralSettingsItemUpdated.class);
    }

    public ApiResponse<SettingsGeneralSettingsItemUpdated> updateSettingsGeneralSettingsItem(
            Object payload) {
        return updateSettingsGeneralSettingsItem(payload, null);
    }

    /**
     * Delete item group: {@code DELETE /settings/item_groups/{item_group_id}}. Recorded 204. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsItemGroup(
            String itemGroupId, RequestOptions options) {
        requireValue(itemGroupId, "item_group_id");
        return client.sendDeleteRequest(SettingsUrls.SETTINGS_ITEM_GROUP,
                reopen(options).pathParam("item_group_id", itemGroupId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsItemGroup(
            String itemGroupId) {
        return deleteSettingsItemGroup(itemGroupId, null);
    }

    /** Get single: {@code GET /settings/item_groups/{item_group_id}}. */
    public ApiResponse<SettingsItemGroups> getSettingsItemGroup(
            String itemGroupId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(itemGroupId, "item_group_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_ITEM_GROUP,
                reopen(options).pathParam("item_group_id", itemGroupId).queryParams(queryParams).build(), SettingsItemGroups.class);
    }

    public ApiResponse<SettingsItemGroups> getSettingsItemGroup(
            String itemGroupId, Map<String, Object> queryParams) {
        return getSettingsItemGroup(itemGroupId, queryParams, null);
    }

    public ApiResponse<SettingsItemGroups> getSettingsItemGroup(String itemGroupId) {
        return getSettingsItemGroup(itemGroupId, null, null);
    }

    /** Update item group: {@code PATCH /settings/item_groups/{item_group_id}}. Recorded 202. */
    public ApiResponse<SettingsItemGroups> updateSettingsItemGroup(
            String itemGroupId, Object payload, RequestOptions options) {
        requireValue(itemGroupId, "item_group_id");
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_ITEM_GROUP,
                reopen(options).pathParam("item_group_id", itemGroupId).jsonBody(payload).build(), SettingsItemGroups.class);
    }

    public ApiResponse<SettingsItemGroups> updateSettingsItemGroup(
            String itemGroupId, Object payload) {
        return updateSettingsItemGroup(itemGroupId, payload, null);
    }

    /** Get single: {@code GET /settings/time_zones/{time_zone_id}}. */
    public ApiResponse<SettingsTimeZoneRead> getSettingsTimeZone(
            String timeZoneId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(timeZoneId, "time_zone_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_TIME_ZONE,
                reopen(options).pathParam("time_zone_id", timeZoneId).queryParams(queryParams).build(), SettingsTimeZoneRead.class);
    }

    public ApiResponse<SettingsTimeZoneRead> getSettingsTimeZone(
            String timeZoneId, Map<String, Object> queryParams) {
        return getSettingsTimeZone(timeZoneId, queryParams, null);
    }

    public ApiResponse<SettingsTimeZoneRead> getSettingsTimeZone(String timeZoneId) {
        return getSettingsTimeZone(timeZoneId, null, null);
    }

    /** Get single: {@code GET /settings/variations/{variation_id}}. */
    public ApiResponse<SettingsVariations> getSettingsVariation(
            String variationId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(variationId, "variation_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_VARIATION,
                reopen(options).pathParam("variation_id", variationId).queryParams(queryParams).build(), SettingsVariations.class);
    }

    public ApiResponse<SettingsVariations> getSettingsVariation(
            String variationId, Map<String, Object> queryParams) {
        return getSettingsVariation(variationId, queryParams, null);
    }

    public ApiResponse<SettingsVariations> getSettingsVariation(String variationId) {
        return getSettingsVariation(variationId, null, null);
    }

    /** Update variation: {@code PATCH /settings/variations/{variation_id}}. Recorded 202. */
    public ApiResponse<SettingsVariations> updateSettingsVariation(
            String variationId, Object payload, RequestOptions options) {
        requireValue(variationId, "variation_id");
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_VARIATION,
                reopen(options).pathParam("variation_id", variationId).jsonBody(payload).build(), SettingsVariations.class);
    }

    public ApiResponse<SettingsVariations> updateSettingsVariation(
            String variationId, Object payload) {
        return updateSettingsVariation(variationId, payload, null);
    }

    /**
     * Customize attributes:
     * {@code POST /settings/custom_objects/{custom_object_id}/customize}. Recorded 201.
     */
    public ApiResponse<SettingsCustomObjectCustomize> createSettingsCustomObjectCustomize(
            String customObjectId, Object payload, RequestOptions options) {
        requireValue(customObjectId, "custom_object_id");
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_CUSTOM_OBJECT_CUSTOMIZE,
                reopen(options).pathParam("custom_object_id", customObjectId).jsonBody(payload).build(), SettingsCustomObjectCustomize.class);
    }

    public ApiResponse<SettingsCustomObjectCustomize> createSettingsCustomObjectCustomize(
            String customObjectId, Object payload) {
        return createSettingsCustomObjectCustomize(customObjectId, payload, null);
    }

    /**
     * Update billing preferences:
     * {@code POST /settings/item_groups/{item_group_id}/billing_preferences}. Recorded 201.
     */
    public ApiResponse<SettingsItemGroups> createSettingsItemGroupBillingPreference(
            String itemGroupId, Object payload, RequestOptions options) {
        requireValue(itemGroupId, "item_group_id");
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_ITEM_GROUP_BILLING_PREFERENCES,
                reopen(options).pathParam("item_group_id", itemGroupId).jsonBody(payload).build(), SettingsItemGroups.class);
    }

    public ApiResponse<SettingsItemGroups> createSettingsItemGroupBillingPreference(
            String itemGroupId, Object payload) {
        return createSettingsItemGroupBillingPreference(itemGroupId, payload, null);
    }

    /**
     * List custom object definitions: {@code GET /settings/custom_objects}; enabled definitions
     * only.
     */
    public ApiResponse<SettingsCustomObjectDefinitionList> getSettingsCustomObjects(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_CUSTOM_OBJECTS,
                reopen(options).queryParams(queryParams).build(), SettingsCustomObjectDefinitionList.class);
    }

    public ApiResponse<SettingsCustomObjectDefinitionList> getSettingsCustomObjects(
            Map<String, Object> queryParams) {
        return getSettingsCustomObjects(queryParams, null);
    }

    public ApiResponse<SettingsCustomObjectDefinitionList> getSettingsCustomObjects() {
        return getSettingsCustomObjects(null, null);
    }

    /**
     * Get one custom object definition: {@code GET /settings/custom_objects/{custom_object_id}},
     * under {@code custom_object}.
     */
    public ApiResponse<SettingsCustomObjectDefinitionRead> getSettingsCustomObject(
            String customObjectId, RequestOptions options) {
        requireValue(customObjectId, "custom_object_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_CUSTOM_OBJECT,
                reopen(options).pathParam("custom_object_id", customObjectId).build(), SettingsCustomObjectDefinitionRead.class);
    }

    public ApiResponse<SettingsCustomObjectDefinitionRead> getSettingsCustomObject(String customObjectId) {
        return getSettingsCustomObject(customObjectId, null);
    }

    /**
     * Create a custom object schema: {@code POST /custom_objects}; answered {@code 201}, under
     * {@code custom_object}. {@code name} is required and must be unused.
     */
    public ApiResponse<SettingsCustomObjects> createCustomObject(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.CUSTOM_OBJECTS,
                reopen(options).jsonBody(payload).build(), SettingsCustomObjects.class);
    }

    public ApiResponse<SettingsCustomObjects> createCustomObject(Object payload) {
        return createCustomObject(payload, null);
    }

    /**
     * Update a custom object schema: {@code PATCH /custom_objects/{custom_object_id}}, under
     * {@code custom_object}. {@code use_in}, {@code relation} and {@code custom_component} are
     * refused.
     */
    public ApiResponse<SettingsCustomObjects> updateCustomObject(
            String customObjectId, Object payload, RequestOptions options) {
        requireValue(customObjectId, "custom_object_id");
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.CUSTOM_OBJECT,
                reopen(options).pathParam("custom_object_id", customObjectId).jsonBody(payload).build(), SettingsCustomObjects.class);
    }

    public ApiResponse<SettingsCustomObjects> updateCustomObject(String customObjectId, Object payload) {
        return updateCustomObject(customObjectId, payload, null);
    }

    /**
     * Delete a custom object schema: {@code DELETE /custom_objects/{custom_object_id}}; answered
     * {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteCustomObject(
            String customObjectId, RequestOptions options) {
        requireValue(customObjectId, "custom_object_id");
        return client.sendDeleteRequest(SettingsUrls.CUSTOM_OBJECT,
                reopen(options).pathParam("custom_object_id", customObjectId).build(), null);
    }

    public ApiResponse<Dto> deleteCustomObject(String customObjectId) {
        return deleteCustomObject(customObjectId, null);
    }

    /**
     * Create a currency: {@code POST /settings/currencies}; answered {@code 201}, under
     * {@code currency}. {@code code} and {@code country} are required, and v4 has no route that
     * deletes a currency. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createSettingsCurrency(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_CURRENCIES,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createSettingsCurrency(Object payload) {
        return createSettingsCurrency(payload, null);
    }

    /**
     * Update a currency: {@code PATCH /settings/currencies/{currency_id}}, under {@code currency}.
     * Only {@code rounding}, {@code cash_rounding_interval}, {@code conversion_rate} and
     * {@code status} are accepted. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsCurrency(
            String currencyId, Object payload, RequestOptions options) {
        requireValue(currencyId, "currency_id");
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_CURRENCY,
                reopen(options).pathParam("currency_id", currencyId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsCurrency(String currencyId, Object payload) {
        return updateSettingsCurrency(currencyId, payload, null);
    }

    /**
     * Create a custom logic script: {@code POST /settings/custom_logic_scripts}; answered
     * {@code 201}. {@code name}, {@code type} and {@code code} are required, and {@code code} is
     * validated but not stored.
     */
    public ApiResponse<SettingsCustomLogicScriptSaved> createSettingsCustomLogicScript(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_CUSTOM_LOGIC_SCRIPTS,
                reopen(options).jsonBody(payload).build(), SettingsCustomLogicScriptSaved.class);
    }

    public ApiResponse<SettingsCustomLogicScriptSaved> createSettingsCustomLogicScript(Object payload) {
        return createSettingsCustomLogicScript(payload, null);
    }

    /**
     * Update a custom logic script:
     * {@code PATCH /settings/custom_logic_scripts/{custom_logic_script_id}}; {@code code} is
     * refused.
     */
    public ApiResponse<SettingsCustomLogicScriptSaved> updateSettingsCustomLogicScript(
            String customLogicScriptId, Object payload, RequestOptions options) {
        requireValue(customLogicScriptId, "custom_logic_script_id");
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_CUSTOM_LOGIC_SCRIPT,
                reopen(options).pathParam("custom_logic_script_id", customLogicScriptId).jsonBody(payload).build(), SettingsCustomLogicScriptSaved.class);
    }

    public ApiResponse<SettingsCustomLogicScriptSaved> updateSettingsCustomLogicScript(
            String customLogicScriptId, Object payload) {
        return updateSettingsCustomLogicScript(customLogicScriptId, payload, null);
    }

    /**
     * Soft-delete a custom logic script:
     * {@code DELETE /settings/custom_logic_scripts/{custom_logic_script_id}}; {@code 204}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsCustomLogicScript(
            String customLogicScriptId, RequestOptions options) {
        requireValue(customLogicScriptId, "custom_logic_script_id");
        return client.sendDeleteRequest(SettingsUrls.SETTINGS_CUSTOM_LOGIC_SCRIPT,
                reopen(options).pathParam("custom_logic_script_id", customLogicScriptId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsCustomLogicScript(String customLogicScriptId) {
        return deleteSettingsCustomLogicScript(customLogicScriptId, null);
    }

    /**
     * Create a discount profile: {@code POST /settings/discount_profiles}; {@code 201}. Times go as
     * {@code YYYY-MM-DD HH:mm} and the accounting code must be a discount one, or it is a
     * {@code 500}.
     */
    public ApiResponse<SettingsDiscountProfileRead> createSettingsDiscountProfile(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_DISCOUNT_PROFILES,
                reopen(options).jsonBody(payload).build(), SettingsDiscountProfileRead.class);
    }

    public ApiResponse<SettingsDiscountProfileRead> createSettingsDiscountProfile(Object payload) {
        return createSettingsDiscountProfile(payload, null);
    }

    /**
     * Get one discount profile: {@code GET /settings/discount_profiles/{discount_profile_id}},
     * under {@code discount_profile}.
     */
    public ApiResponse<SettingsDiscountProfileRead> getSettingsDiscountProfile(
            String discountProfileId, RequestOptions options) {
        requireValue(discountProfileId, "discount_profile_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_DISCOUNT_PROFILE,
                reopen(options).pathParam("discount_profile_id", discountProfileId).build(), SettingsDiscountProfileRead.class);
    }

    public ApiResponse<SettingsDiscountProfileRead> getSettingsDiscountProfile(String discountProfileId) {
        return getSettingsDiscountProfile(discountProfileId, null);
    }

    /**
     * Update a discount profile: {@code PATCH /settings/discount_profiles/{discount_profile_id}},
     * under {@code discount_profile}.
     */
    public ApiResponse<SettingsDiscountProfileRead> updateSettingsDiscountProfile(
            String discountProfileId, Object payload, RequestOptions options) {
        requireValue(discountProfileId, "discount_profile_id");
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_DISCOUNT_PROFILE,
                reopen(options).pathParam("discount_profile_id", discountProfileId).jsonBody(payload).build(), SettingsDiscountProfileRead.class);
    }

    public ApiResponse<SettingsDiscountProfileRead> updateSettingsDiscountProfile(
            String discountProfileId, Object payload) {
        return updateSettingsDiscountProfile(discountProfileId, payload, null);
    }

    /**
     * Delete a discount profile: {@code DELETE /settings/discount_profiles/{discount_profile_id}};
     * answered {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsDiscountProfile(
            String discountProfileId, RequestOptions options) {
        requireValue(discountProfileId, "discount_profile_id");
        return client.sendDeleteRequest(SettingsUrls.SETTINGS_DISCOUNT_PROFILE,
                reopen(options).pathParam("discount_profile_id", discountProfileId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsDiscountProfile(String discountProfileId) {
        return deleteSettingsDiscountProfile(discountProfileId, null);
    }

    /**
     * List field settings: {@code GET /settings/field_settings}; paged by cursor, and
     * {@code include_total=true} is refused.
     */
    public ApiResponse<SettingsFieldSettingList> getSettingsFieldSettings(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsUrls.SETTINGS_FIELD_SETTINGS,
                reopen(options).queryParams(queryParams).build(), SettingsFieldSettingList.class);
    }

    public ApiResponse<SettingsFieldSettingList> getSettingsFieldSettings(Map<String, Object> queryParams) {
        return getSettingsFieldSettings(queryParams, null);
    }

    public ApiResponse<SettingsFieldSettingList> getSettingsFieldSettings() {
        return getSettingsFieldSettings(null, null);
    }

    /**
     * Get one field setting: {@code GET /settings/field_settings/{field_setting_id}}, under
     * {@code field_setting}.
     */
    public ApiResponse<SettingsFieldSettingRead> getSettingsFieldSetting(
            String fieldSettingId, RequestOptions options) {
        requireValue(fieldSettingId, "field_setting_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_FIELD_SETTING,
                reopen(options).pathParam("field_setting_id", fieldSettingId).build(), SettingsFieldSettingRead.class);
    }

    public ApiResponse<SettingsFieldSettingRead> getSettingsFieldSetting(String fieldSettingId) {
        return getSettingsFieldSetting(fieldSettingId, null);
    }

    /**
     * Read one group of general settings: {@code GET /settings/general_settings/{group}}, under
     * {@code general_settings}; {@code group} is one of the twelve the server names, any case.
     */
    public ApiResponse<SettingsGeneralSettings> getSettingsGeneralSettings(
            String group, RequestOptions options) {
        requireValue(group, "group");
        return client.sendGetRequest(SettingsUrls.SETTINGS_GENERAL_SETTINGS_GROUP,
                reopen(options).pathParam("group", group).build(), SettingsGeneralSettings.class);
    }

    public ApiResponse<SettingsGeneralSettings> getSettingsGeneralSettings(String group) {
        return getSettingsGeneralSettings(group, null);
    }

    /**
     * One general setting row: {@code GET /settings/general_settings/{group}/{general_setting_id}};
     * no v4 answer carries a {@code gs_} id. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getSettingsGeneralSetting(
            String group, String generalSettingId, RequestOptions options) {
        requireValue(group, "group");
        requireValue(generalSettingId, "general_setting_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_GENERAL_SETTING,
                reopen(options).pathParam("group", group).pathParam("general_setting_id", generalSettingId).build(), null);
    }

    public ApiResponse<Dto> getSettingsGeneralSetting(String group, String generalSettingId) {
        return getSettingsGeneralSetting(group, generalSettingId, null);
    }

    /**
     * Update the dashboard group: {@code PATCH /settings/general_settings/dashboard}. Flags are
     * stored as {@code "1"}/{@code "0"} and read back raw, so a {@code "true"} cannot be restored.
     */
    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsDashboard(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_GENERAL_SETTINGS_DASHBOARD,
                reopen(options).jsonBody(payload).build(), SettingsGeneralSettings.class);
    }

    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsDashboard(Object payload) {
        return updateSettingsGeneralSettingsDashboard(payload, null);
    }

    /**
     * Update the date and time group: {@code PATCH /settings/general_settings/date_and_time}; the
     * save also pushes the organisation to the catalogue processor.
     */
    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsDateAndTime(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_GENERAL_SETTINGS_DATE_AND_TIME,
                reopen(options).jsonBody(payload).build(), SettingsGeneralSettings.class);
    }

    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsDateAndTime(Object payload) {
        return updateSettingsGeneralSettingsDateAndTime(payload, null);
    }

    /**
     * Update the display group: {@code PATCH /settings/general_settings/display}. Column lists sent
     * as the strings the read serves are ignored: the save reads arrays.
     */
    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsDisplay(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_GENERAL_SETTINGS_DISPLAY,
                reopen(options).jsonBody(payload).build(), SettingsGeneralSettings.class);
    }

    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsDisplay(Object payload) {
        return updateSettingsGeneralSettingsDisplay(payload, null);
    }

    /**
     * Update the general group: {@code PATCH /settings/general_settings/general}. It also resets
     * {@code accountingPeriod}, {@code fiscalYear} and six more settings no route can write back.
     */
    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsGeneral(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_GENERAL_SETTINGS_GENERAL,
                reopen(options).jsonBody(payload).build(), SettingsGeneralSettings.class);
    }

    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsGeneral(Object payload) {
        return updateSettingsGeneralSettingsGeneral(payload, null);
    }

    /**
     * Update the labour general settings: {@code PATCH /settings/general_settings/labour}; answers
     * the group as {@code GET} reads it, under {@code general_settings}.
     */
    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsLabour(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_GENERAL_SETTINGS_LABOUR,
                reopen(options).jsonBody(payload).build(), SettingsGeneralSettings.class);
    }

    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsLabour(Object payload) {
        return updateSettingsGeneralSettingsLabour(payload, null);
    }

    /**
     * Update the order group: {@code PATCH /settings/general_settings/order}, which reads
     * {@code order} and {@code product} from {@code general_settings}.
     */
    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsOrder(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_GENERAL_SETTINGS_ORDER,
                reopen(options).jsonBody(payload).build(), SettingsGeneralSettings.class);
    }

    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsOrder(Object payload) {
        return updateSettingsGeneralSettingsOrder(payload, null);
    }

    /**
     * Update the portal group: {@code PATCH /settings/general_settings/portal}. With
     * {@code enableCustomerPortal} off, any write also turns every portal login off.
     */
    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsPortal(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_GENERAL_SETTINGS_PORTAL,
                reopen(options).jsonBody(payload).build(), SettingsGeneralSettings.class);
    }

    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsPortal(Object payload) {
        return updateSettingsGeneralSettingsPortal(payload, null);
    }

    /**
     * Update the purchase general settings: {@code PATCH /settings/general_settings/purchase};
     * answers the group as {@code GET} reads it, under {@code general_settings}.
     */
    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsPurchase(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_GENERAL_SETTINGS_PURCHASE,
                reopen(options).jsonBody(payload).build(), SettingsGeneralSettings.class);
    }

    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsPurchase(Object payload) {
        return updateSettingsGeneralSettingsPurchase(payload, null);
    }

    /**
     * Update the security group: {@code PATCH /settings/general_settings/security}. Send flags as
     * {@code "1"}/{@code "0"}: the {@code "true"} the read serves is stored as false.
     */
    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsSecurity(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_GENERAL_SETTINGS_SECURITY,
                reopen(options).jsonBody(payload).build(), SettingsGeneralSettings.class);
    }

    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsSecurity(Object payload) {
        return updateSettingsGeneralSettingsSecurity(payload, null);
    }

    /**
     * Update the transaction general settings:
     * {@code PATCH /settings/general_settings/transaction}; answers the group as {@code GET} reads
     * it, under {@code general_settings}.
     */
    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsTransaction(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_GENERAL_SETTINGS_TRANSACTION,
                reopen(options).jsonBody(payload).build(), SettingsGeneralSettings.class);
    }

    public ApiResponse<SettingsGeneralSettings> updateSettingsGeneralSettingsTransaction(Object payload) {
        return updateSettingsGeneralSettingsTransaction(payload, null);
    }

    /**
     * Create an offline payment processor: {@code POST /settings/payment_processors}; {@code 201}.
     * Refused unless {@code credentials.maximumRetryOnFailure} is {@code "0"} or {@code "1"}.
     */
    public ApiResponse<SettingsPaymentProcessorSaved> createSettingsPaymentProcessor(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_PAYMENT_PROCESSORS,
                reopen(options).jsonBody(payload).build(), SettingsPaymentProcessorSaved.class);
    }

    public ApiResponse<SettingsPaymentProcessorSaved> createSettingsPaymentProcessor(Object payload) {
        return createSettingsPaymentProcessor(payload, null);
    }

    /**
     * Get one payment processor: {@code GET /settings/payment_processors/{payment_processor_id}},
     * under {@code payment_processor}.
     */
    public ApiResponse<SettingsPaymentProcessorRead> getSettingsPaymentProcessor(
            String paymentProcessorId, RequestOptions options) {
        requireValue(paymentProcessorId, "payment_processor_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_PAYMENT_PROCESSOR,
                reopen(options).pathParam("payment_processor_id", paymentProcessorId).build(), SettingsPaymentProcessorRead.class);
    }

    public ApiResponse<SettingsPaymentProcessorRead> getSettingsPaymentProcessor(String paymentProcessorId) {
        return getSettingsPaymentProcessor(paymentProcessorId, null);
    }

    /**
     * Update a payment processor:
     * {@code PATCH /settings/payment_processors/{payment_processor_id}}; refused, like the create,
     * without {@code credentials.maximumRetryOnFailure}.
     */
    public ApiResponse<SettingsPaymentProcessorSaved> updateSettingsPaymentProcessor(
            String paymentProcessorId, Object payload, RequestOptions options) {
        requireValue(paymentProcessorId, "payment_processor_id");
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_PAYMENT_PROCESSOR,
                reopen(options).pathParam("payment_processor_id", paymentProcessorId).jsonBody(payload).build(), SettingsPaymentProcessorSaved.class);
    }

    public ApiResponse<SettingsPaymentProcessorSaved> updateSettingsPaymentProcessor(
            String paymentProcessorId, Object payload) {
        return updateSettingsPaymentProcessor(paymentProcessorId, payload, null);
    }

    /**
     * Delete a payment processor:
     * {@code DELETE /settings/payment_processors/{payment_processor_id}}; answered {@code 204}. The
     * default processor is refused. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsPaymentProcessor(
            String paymentProcessorId, RequestOptions options) {
        requireValue(paymentProcessorId, "payment_processor_id");
        return client.sendDeleteRequest(SettingsUrls.SETTINGS_PAYMENT_PROCESSOR,
                reopen(options).pathParam("payment_processor_id", paymentProcessorId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsPaymentProcessor(String paymentProcessorId) {
        return deleteSettingsPaymentProcessor(paymentProcessorId, null);
    }

    /**
     * Create a payment term: {@code POST /settings/payment_terms}; answered {@code 201} with a flat
     * {@code period} and {@code duration} where the read nests them. A sent {@code status} is not
     * applied.
     */
    public ApiResponse<SettingsPaymentTermCreated> createSettingsPaymentTerm(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_PAYMENT_TERMS,
                reopen(options).jsonBody(payload).build(), SettingsPaymentTermCreated.class);
    }

    public ApiResponse<SettingsPaymentTermCreated> createSettingsPaymentTerm(Object payload) {
        return createSettingsPaymentTerm(payload, null);
    }

    /**
     * Get one payment term: {@code GET /settings/payment_terms/{payment_term_id}}, under
     * {@code payment_term}.
     */
    public ApiResponse<SettingsPaymentTermRead> getSettingsPaymentTerm(
            String paymentTermId, RequestOptions options) {
        requireValue(paymentTermId, "payment_term_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_PAYMENT_TERM,
                reopen(options).pathParam("payment_term_id", paymentTermId).build(), SettingsPaymentTermRead.class);
    }

    public ApiResponse<SettingsPaymentTermRead> getSettingsPaymentTerm(String paymentTermId) {
        return getSettingsPaymentTerm(paymentTermId, null);
    }

    /**
     * Update a payment term: {@code PATCH /settings/payment_terms/{payment_term_id}}, under
     * {@code payment_term}; the answer adds {@code display_order} to the read's fields.
     */
    public ApiResponse<SettingsPaymentTermUpdated> updateSettingsPaymentTerm(
            String paymentTermId, Object payload, RequestOptions options) {
        requireValue(paymentTermId, "payment_term_id");
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_PAYMENT_TERM,
                reopen(options).pathParam("payment_term_id", paymentTermId).jsonBody(payload).build(), SettingsPaymentTermUpdated.class);
    }

    public ApiResponse<SettingsPaymentTermUpdated> updateSettingsPaymentTerm(
            String paymentTermId, Object payload) {
        return updateSettingsPaymentTerm(paymentTermId, payload, null);
    }

    /**
     * Delete a payment term: {@code DELETE /settings/payment_terms/{payment_term_id}}; answered
     * {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsPaymentTerm(
            String paymentTermId, RequestOptions options) {
        requireValue(paymentTermId, "payment_term_id");
        return client.sendDeleteRequest(SettingsUrls.SETTINGS_PAYMENT_TERM,
                reopen(options).pathParam("payment_term_id", paymentTermId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsPaymentTerm(String paymentTermId) {
        return deleteSettingsPaymentTerm(paymentTermId, null);
    }

    /**
     * Create a pricing level: {@code POST /settings/pricing_levels}; answered {@code 201}, under
     * {@code pricing_level}. {@code name} is required, and a sent {@code status} is not applied.
     */
    public ApiResponse<SettingsPricingLevelCreated> createSettingsPricingLevel(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_PRICING_LEVELS,
                reopen(options).jsonBody(payload).build(), SettingsPricingLevelCreated.class);
    }

    public ApiResponse<SettingsPricingLevelCreated> createSettingsPricingLevel(Object payload) {
        return createSettingsPricingLevel(payload, null);
    }

    /**
     * Get one pricing level: {@code GET /settings/pricing_levels/{pricing_level_id}}, under
     * {@code pricing_level}.
     */
    public ApiResponse<SettingsPricingLevelRead> getSettingsPricingLevel(
            String pricingLevelId, RequestOptions options) {
        requireValue(pricingLevelId, "pricing_level_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_PRICING_LEVEL,
                reopen(options).pathParam("pricing_level_id", pricingLevelId).build(), SettingsPricingLevelRead.class);
    }

    public ApiResponse<SettingsPricingLevelRead> getSettingsPricingLevel(String pricingLevelId) {
        return getSettingsPricingLevel(pricingLevelId, null);
    }

    /**
     * Update a pricing level: {@code PATCH /settings/pricing_levels/{pricing_level_id}}, under
     * {@code pricing_level}.
     */
    public ApiResponse<SettingsPricingLevelRead> updateSettingsPricingLevel(
            String pricingLevelId, Object payload, RequestOptions options) {
        requireValue(pricingLevelId, "pricing_level_id");
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_PRICING_LEVEL,
                reopen(options).pathParam("pricing_level_id", pricingLevelId).jsonBody(payload).build(), SettingsPricingLevelRead.class);
    }

    public ApiResponse<SettingsPricingLevelRead> updateSettingsPricingLevel(
            String pricingLevelId, Object payload) {
        return updateSettingsPricingLevel(pricingLevelId, payload, null);
    }

    /**
     * Delete a pricing level: {@code DELETE /settings/pricing_levels/{pricing_level_id}}; answered
     * {@code 204}. The default level is refused. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsPricingLevel(
            String pricingLevelId, RequestOptions options) {
        requireValue(pricingLevelId, "pricing_level_id");
        return client.sendDeleteRequest(SettingsUrls.SETTINGS_PRICING_LEVEL,
                reopen(options).pathParam("pricing_level_id", pricingLevelId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsPricingLevel(String pricingLevelId) {
        return deleteSettingsPricingLevel(pricingLevelId, null);
    }

    /**
     * Create a shipping profile: {@code POST /settings/shipping_profiles}; {@code 201}.
     * {@code name} and {@code shipping_type} are required, and {@code FIXED} needs
     * {@code fixed_amount}.
     */
    public ApiResponse<SettingsShippingProfileRead> createSettingsShippingProfile(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_SHIPPING_PROFILES,
                reopen(options).jsonBody(payload).build(), SettingsShippingProfileRead.class);
    }

    public ApiResponse<SettingsShippingProfileRead> createSettingsShippingProfile(Object payload) {
        return createSettingsShippingProfile(payload, null);
    }

    /**
     * Get one shipping profile: {@code GET /settings/shipping_profiles/{shipping_profile_id}},
     * under {@code shipping_profile}.
     */
    public ApiResponse<SettingsShippingProfileRead> getSettingsShippingProfile(
            String shippingProfileId, RequestOptions options) {
        requireValue(shippingProfileId, "shipping_profile_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_SHIPPING_PROFILE,
                reopen(options).pathParam("shipping_profile_id", shippingProfileId).build(), SettingsShippingProfileRead.class);
    }

    public ApiResponse<SettingsShippingProfileRead> getSettingsShippingProfile(String shippingProfileId) {
        return getSettingsShippingProfile(shippingProfileId, null);
    }

    /**
     * Update a shipping profile: {@code PATCH /settings/shipping_profiles/{shipping_profile_id}}.
     * Answers {@code 500}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsShippingProfile(
            String shippingProfileId, Object payload, RequestOptions options) {
        requireValue(shippingProfileId, "shipping_profile_id");
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_SHIPPING_PROFILE,
                reopen(options).pathParam("shipping_profile_id", shippingProfileId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsShippingProfile(String shippingProfileId, Object payload) {
        return updateSettingsShippingProfile(shippingProfileId, payload, null);
    }

    /**
     * Delete a shipping profile: {@code DELETE /settings/shipping_profiles/{shipping_profile_id}};
     * answered {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsShippingProfile(
            String shippingProfileId, RequestOptions options) {
        requireValue(shippingProfileId, "shipping_profile_id");
        return client.sendDeleteRequest(SettingsUrls.SETTINGS_SHIPPING_PROFILE,
                reopen(options).pathParam("shipping_profile_id", shippingProfileId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsShippingProfile(String shippingProfileId) {
        return deleteSettingsShippingProfile(shippingProfileId, null);
    }

    /**
     * Create a warehouse: {@code POST /settings/warehouses}; answered {@code 201}. {@code name} and
     * {@code country} are required, and the answer's {@code country} reads null although it was
     * stored.
     */
    public ApiResponse<SettingsWarehouseRead> createSettingsWarehouse(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_WAREHOUSES,
                reopen(options).jsonBody(payload).build(), SettingsWarehouseRead.class);
    }

    public ApiResponse<SettingsWarehouseRead> createSettingsWarehouse(Object payload) {
        return createSettingsWarehouse(payload, null);
    }

    /**
     * Get one warehouse: {@code GET /settings/warehouses/{warehouse_id}}, under {@code warehouse}.
     */
    public ApiResponse<SettingsWarehouseRead> getSettingsWarehouse(
            String warehouseId, RequestOptions options) {
        requireValue(warehouseId, "warehouse_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_WAREHOUSE,
                reopen(options).pathParam("warehouse_id", warehouseId).build(), SettingsWarehouseRead.class);
    }

    public ApiResponse<SettingsWarehouseRead> getSettingsWarehouse(String warehouseId) {
        return getSettingsWarehouse(warehouseId, null);
    }

    /**
     * Update a warehouse: {@code PATCH /settings/warehouses/{warehouse_id}}, under
     * {@code warehouse}; the answer's {@code country} reads null although the record has one.
     */
    public ApiResponse<SettingsWarehouseRead> updateSettingsWarehouse(
            String warehouseId, Object payload, RequestOptions options) {
        requireValue(warehouseId, "warehouse_id");
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_WAREHOUSE,
                reopen(options).pathParam("warehouse_id", warehouseId).jsonBody(payload).build(), SettingsWarehouseRead.class);
    }

    public ApiResponse<SettingsWarehouseRead> updateSettingsWarehouse(String warehouseId, Object payload) {
        return updateSettingsWarehouse(warehouseId, payload, null);
    }

    /**
     * Delete a warehouse: {@code DELETE /settings/warehouses/{warehouse_id}}; answered {@code 204}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsWarehouse(
            String warehouseId, RequestOptions options) {
        requireValue(warehouseId, "warehouse_id");
        return client.sendDeleteRequest(SettingsUrls.SETTINGS_WAREHOUSE,
                reopen(options).pathParam("warehouse_id", warehouseId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsWarehouse(String warehouseId) {
        return deleteSettingsWarehouse(warehouseId, null);
    }

    /**
     * Create a country tax: {@code POST /settings/taxes}; answered {@code 201}, under {@code tax}.
     * {@code code} and {@code country} are required, and v4 has no route that deletes a tax. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createSettingsTax(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SettingsUrls.SETTINGS_TAXES,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createSettingsTax(Object payload) {
        return createSettingsTax(payload, null);
    }

    /** Get one tax: {@code GET /settings/taxes/{tax_id}}, under {@code tax}. */
    public ApiResponse<SettingsTaxRead> getSettingsTax(
            String taxId, RequestOptions options) {
        requireValue(taxId, "tax_id");
        return client.sendGetRequest(SettingsUrls.SETTINGS_TAX,
                reopen(options).pathParam("tax_id", taxId).build(), SettingsTaxRead.class);
    }

    public ApiResponse<SettingsTaxRead> getSettingsTax(String taxId) {
        return getSettingsTax(taxId, null);
    }

    /**
     * Update, enable or disable a tax: {@code PATCH /settings/taxes/{tax_id}}, under {@code tax}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsTax(
            String taxId, Object payload, RequestOptions options) {
        requireValue(taxId, "tax_id");
        requirePayload(payload);
        return client.sendPatchRequest(SettingsUrls.SETTINGS_TAX,
                reopen(options).pathParam("tax_id", taxId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsTax(String taxId, Object payload) {
        return updateSettingsTax(taxId, payload, null);
    }

    /**
     * Delete a variation: {@code DELETE /settings/variations/{variation_id}}; answered {@code 204}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsVariation(
            String variationId, RequestOptions options) {
        requireValue(variationId, "variation_id");
        return client.sendDeleteRequest(SettingsUrls.SETTINGS_VARIATION,
                reopen(options).pathParam("variation_id", variationId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsVariation(String variationId) {
        return deleteSettingsVariation(variationId, null);
    }

    private static RequestOptions.Builder reopen(RequestOptions options) {
        RequestOptions given = options == null ? RequestOptions.none() : options;
        return RequestOptions.builder()
                .pathParams(given.getPathParams())
                .queryParams(given.getQueryParams())
                .jsonBody(given.getJsonBody())
                .formData(given.getFormData())
                .files(given.getFiles())
                .headers(given.getHeaders())
                .authenticated(given.isAuthenticated())
                .raiseOnError(given.getRaiseOnError())
                .responseFormat(given.getResponseFormat())
                .dtoFactory(given.getDtoFactory());
    }

    private static void requireValue(String value, String what) {
        if (value == null || value.isBlank()) {
            throw new SdkUsageError(
                    what + " is required: the request cannot be built without it");
        }
    }

    private static void requirePayload(Object payload) {
        if (payload == null) {
            throw new SdkUsageError("payload is required: this endpoint takes a body, "
                    + "and null would send the request with no body at all");
        }
    }
}
