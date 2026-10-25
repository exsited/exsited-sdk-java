package com.exsited.resources.settings;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.settings.settings_dto.SettingsDto.CustomObjectList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCurrencyList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCurrencyRead;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCustomLogicScript;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCustomLogicScripts;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCustomObjectCustomize;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsCustomObjects;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsDiscountProfileList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsFieldSettingsItem;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsGeneralSettingsItemUpdated;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsItemGroupList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsItemGroups;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsPaymentProcessorList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsPaymentTermList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsPricingLevelList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsShippingProfileList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsTaxList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsTimeZoneList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsTimeZoneRead;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsVariationList;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsVariations;
import com.exsited.resources.settings.settings_dto.SettingsDto.SettingsWarehouseList;
import com.exsited.resources.settings.settings_dto.SettingsDto;
import java.util.Map;

/**
 * Forwards every call to {@link Settings}.
 *
 * @deprecated {@link Settings} is bound to a client itself and is what
 *     {@code client.settings()} answers; use it instead.
 */
@Deprecated
public final class SettingsResource implements BoundResource {

    private final Settings settings;

    public SettingsResource(ExsitedClient client) {
        this.settings = new Settings(client);
    }

    @Override
    public ExsitedClient getClient() {
        return settings.getClient();
    }

    public ApiResponse<CustomObjectList> getCustomObjects(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getCustomObjects(queryParams, options);
    }

    public ApiResponse<CustomObjectList> getCustomObjects(Map<String, Object> queryParams) {
        return settings.getCustomObjects(queryParams);
    }

    public ApiResponse<SettingsDto.Settings> getSettings(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettings(queryParams, options);
    }

    public ApiResponse<SettingsDto.Settings> getSettings(Map<String, Object> queryParams) {
        return settings.getSettings(queryParams);
    }

    public ApiResponse<SettingsCustomObjects> getCustomObject(
            String customObjectId, Map<String, Object> queryParams, RequestOptions options) {
        return settings.getCustomObject(customObjectId, queryParams, options);
    }

    public ApiResponse<SettingsCustomObjects> getCustomObject(
            String customObjectId, Map<String, Object> queryParams) {
        return settings.getCustomObject(customObjectId, queryParams);
    }

    public ApiResponse<SettingsCurrencyList> getSettingsCurrencies(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsCurrencies(queryParams, options);
    }

    public ApiResponse<SettingsCurrencyList> getSettingsCurrencies(
            Map<String, Object> queryParams) {
        return settings.getSettingsCurrencies(queryParams);
    }

    public ApiResponse<SettingsCustomLogicScripts> getSettingsCustomLogicScripts(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsCustomLogicScripts(queryParams, options);
    }

    public ApiResponse<SettingsCustomLogicScripts> getSettingsCustomLogicScripts(
            Map<String, Object> queryParams) {
        return settings.getSettingsCustomLogicScripts(queryParams);
    }

    public ApiResponse<SettingsCustomObjects> createSettingsCustomObject(
            Object payload, RequestOptions options) {
        return settings.createSettingsCustomObject(payload, options);
    }

    public ApiResponse<SettingsCustomObjects> createSettingsCustomObject(Object payload) {
        return settings.createSettingsCustomObject(payload);
    }

    public ApiResponse<SettingsDiscountProfileList> getSettingsDiscountProfiles(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsDiscountProfiles(queryParams, options);
    }

    public ApiResponse<SettingsDiscountProfileList> getSettingsDiscountProfiles(
            Map<String, Object> queryParams) {
        return settings.getSettingsDiscountProfiles(queryParams);
    }

    public ApiResponse<SettingsItemGroupList> getSettingsItemGroups(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsItemGroups(queryParams, options);
    }

    public ApiResponse<SettingsItemGroupList> getSettingsItemGroups(
            Map<String, Object> queryParams) {
        return settings.getSettingsItemGroups(queryParams);
    }

    public ApiResponse<SettingsItemGroups> createSettingsItemGroup(
            Object payload, RequestOptions options) {
        return settings.createSettingsItemGroup(payload, options);
    }

    public ApiResponse<SettingsItemGroups> createSettingsItemGroup(Object payload) {
        return settings.createSettingsItemGroup(payload);
    }

    public ApiResponse<SettingsPaymentProcessorList> getSettingsPaymentProcessors(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsPaymentProcessors(queryParams, options);
    }

    public ApiResponse<SettingsPaymentProcessorList> getSettingsPaymentProcessors(
            Map<String, Object> queryParams) {
        return settings.getSettingsPaymentProcessors(queryParams);
    }

    public ApiResponse<SettingsPaymentTermList> getSettingsPaymentTerms(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsPaymentTerms(queryParams, options);
    }

    public ApiResponse<SettingsPaymentTermList> getSettingsPaymentTerms(
            Map<String, Object> queryParams) {
        return settings.getSettingsPaymentTerms(queryParams);
    }

    public ApiResponse<SettingsPricingLevelList> getSettingsPricingLevels(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsPricingLevels(queryParams, options);
    }

    public ApiResponse<SettingsPricingLevelList> getSettingsPricingLevels(
            Map<String, Object> queryParams) {
        return settings.getSettingsPricingLevels(queryParams);
    }

    public ApiResponse<SettingsShippingProfileList> getSettingsShippingProfiles(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsShippingProfiles(queryParams, options);
    }

    public ApiResponse<SettingsShippingProfileList> getSettingsShippingProfiles(
            Map<String, Object> queryParams) {
        return settings.getSettingsShippingProfiles(queryParams);
    }

    public ApiResponse<SettingsTaxList> getSettingsTaxes(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsTaxes(queryParams, options);
    }

    public ApiResponse<SettingsTaxList> getSettingsTaxes(Map<String, Object> queryParams) {
        return settings.getSettingsTaxes(queryParams);
    }

    public ApiResponse<SettingsTimeZoneList> getSettingsTimeZones(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsTimeZones(queryParams, options);
    }

    public ApiResponse<SettingsTimeZoneList> getSettingsTimeZones(Map<String, Object> queryParams) {
        return settings.getSettingsTimeZones(queryParams);
    }

    public ApiResponse<SettingsVariationList> getSettingsVariations(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsVariations(queryParams, options);
    }

    public ApiResponse<SettingsVariationList> getSettingsVariations(
            Map<String, Object> queryParams) {
        return settings.getSettingsVariations(queryParams);
    }

    public ApiResponse<SettingsVariations> createSettingsVariation(
            Object payload, RequestOptions options) {
        return settings.createSettingsVariation(payload, options);
    }

    public ApiResponse<SettingsVariations> createSettingsVariation(Object payload) {
        return settings.createSettingsVariation(payload);
    }

    public ApiResponse<SettingsWarehouseList> getSettingsWarehouses(
            Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsWarehouses(queryParams, options);
    }

    public ApiResponse<SettingsWarehouseList> getSettingsWarehouses(
            Map<String, Object> queryParams) {
        return settings.getSettingsWarehouses(queryParams);
    }

    public ApiResponse<SettingsCurrencyRead> getSettingsCurrency(
            String currencyId, Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsCurrency(currencyId, queryParams, options);
    }

    public ApiResponse<SettingsCurrencyRead> getSettingsCurrency(
            String currencyId, Map<String, Object> queryParams) {
        return settings.getSettingsCurrency(currencyId, queryParams);
    }

    public ApiResponse<SettingsCustomLogicScript> getSettingsCustomLogicScript(
            String customLogicScriptId, Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsCustomLogicScript(customLogicScriptId, queryParams, options);
    }

    public ApiResponse<SettingsCustomLogicScript> getSettingsCustomLogicScript(
            String customLogicScriptId, Map<String, Object> queryParams) {
        return settings.getSettingsCustomLogicScript(customLogicScriptId, queryParams);
    }

    public ApiResponse<Dto> deleteSettingsCustomObject(
            String customObjectId, RequestOptions options) {
        return settings.deleteSettingsCustomObject(customObjectId, options);
    }

    public ApiResponse<Dto> deleteSettingsCustomObject(String customObjectId) {
        return settings.deleteSettingsCustomObject(customObjectId);
    }

    public ApiResponse<SettingsCustomObjects> updateSettingsCustomObject(
            String customObjectId, Object payload, RequestOptions options) {
        return settings.updateSettingsCustomObject(customObjectId, payload, options);
    }

    public ApiResponse<SettingsCustomObjects> updateSettingsCustomObject(
            String customObjectId, Object payload) {
        return settings.updateSettingsCustomObject(customObjectId, payload);
    }

    public ApiResponse<SettingsFieldSettingsItem> updateSettingsFieldSettingsAccount(
            Object payload, RequestOptions options) {
        return settings.updateSettingsFieldSettingsAccount(payload, options);
    }

    public ApiResponse<SettingsFieldSettingsItem> updateSettingsFieldSettingsAccount(
            Object payload) {
        return settings.updateSettingsFieldSettingsAccount(payload);
    }

    public ApiResponse<SettingsFieldSettingsItem> updateSettingsFieldSettingsItem(
            Object payload, RequestOptions options) {
        return settings.updateSettingsFieldSettingsItem(payload, options);
    }

    public ApiResponse<SettingsFieldSettingsItem> updateSettingsFieldSettingsItem(Object payload) {
        return settings.updateSettingsFieldSettingsItem(payload);
    }

    public ApiResponse<SettingsFieldSettingsItem> updateSettingsGeneralSettingsAccount(
            Object payload, RequestOptions options) {
        return settings.updateSettingsGeneralSettingsAccount(payload, options);
    }

    public ApiResponse<SettingsFieldSettingsItem> updateSettingsGeneralSettingsAccount(
            Object payload) {
        return settings.updateSettingsGeneralSettingsAccount(payload);
    }

    public ApiResponse<SettingsGeneralSettingsItemUpdated> updateSettingsGeneralSettingsItem(
            Object payload, RequestOptions options) {
        return settings.updateSettingsGeneralSettingsItem(payload, options);
    }

    public ApiResponse<SettingsGeneralSettingsItemUpdated> updateSettingsGeneralSettingsItem(Object payload) {
        return settings.updateSettingsGeneralSettingsItem(payload);
    }

    public ApiResponse<Dto> deleteSettingsItemGroup(String itemGroupId, RequestOptions options) {
        return settings.deleteSettingsItemGroup(itemGroupId, options);
    }

    public ApiResponse<Dto> deleteSettingsItemGroup(String itemGroupId) {
        return settings.deleteSettingsItemGroup(itemGroupId);
    }

    public ApiResponse<SettingsItemGroups> getSettingsItemGroup(
            String itemGroupId, Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsItemGroup(itemGroupId, queryParams, options);
    }

    public ApiResponse<SettingsItemGroups> getSettingsItemGroup(
            String itemGroupId, Map<String, Object> queryParams) {
        return settings.getSettingsItemGroup(itemGroupId, queryParams);
    }

    public ApiResponse<SettingsItemGroups> updateSettingsItemGroup(
            String itemGroupId, Object payload, RequestOptions options) {
        return settings.updateSettingsItemGroup(itemGroupId, payload, options);
    }

    public ApiResponse<SettingsItemGroups> updateSettingsItemGroup(
            String itemGroupId, Object payload) {
        return settings.updateSettingsItemGroup(itemGroupId, payload);
    }

    public ApiResponse<SettingsTimeZoneRead> getSettingsTimeZone(
            String timeZoneId, Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsTimeZone(timeZoneId, queryParams, options);
    }

    public ApiResponse<SettingsTimeZoneRead> getSettingsTimeZone(
            String timeZoneId, Map<String, Object> queryParams) {
        return settings.getSettingsTimeZone(timeZoneId, queryParams);
    }

    public ApiResponse<SettingsVariations> getSettingsVariation(
            String variationId, Map<String, Object> queryParams, RequestOptions options) {
        return settings.getSettingsVariation(variationId, queryParams, options);
    }

    public ApiResponse<SettingsVariations> getSettingsVariation(
            String variationId, Map<String, Object> queryParams) {
        return settings.getSettingsVariation(variationId, queryParams);
    }

    public ApiResponse<SettingsVariations> updateSettingsVariation(
            String variationId, Object payload, RequestOptions options) {
        return settings.updateSettingsVariation(variationId, payload, options);
    }

    public ApiResponse<SettingsVariations> updateSettingsVariation(
            String variationId, Object payload) {
        return settings.updateSettingsVariation(variationId, payload);
    }

    public ApiResponse<SettingsCustomObjectCustomize> createSettingsCustomObjectCustomize(
            String customObjectId, Object payload, RequestOptions options) {
        return settings.createSettingsCustomObjectCustomize(customObjectId, payload, options);
    }

    public ApiResponse<SettingsCustomObjectCustomize> createSettingsCustomObjectCustomize(
            String customObjectId, Object payload) {
        return settings.createSettingsCustomObjectCustomize(customObjectId, payload);
    }

    public ApiResponse<SettingsItemGroups> createSettingsItemGroupBillingPreference(
            String itemGroupId, Object payload, RequestOptions options) {
        return settings.createSettingsItemGroupBillingPreference(itemGroupId, payload, options);
    }

    public ApiResponse<SettingsItemGroups> createSettingsItemGroupBillingPreference(
            String itemGroupId, Object payload) {
        return settings.createSettingsItemGroupBillingPreference(itemGroupId, payload);
    }
}
