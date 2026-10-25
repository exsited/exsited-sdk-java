package com.exsited.resources.shipping_rules;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.shipping_rules.shipping_rules_dto.ShippingRulesDto.SettingsShippingRule;
import com.exsited.resources.shipping_rules.shipping_rules_dto.ShippingRulesDto.SettingsShippingRuleList;
import java.util.Map;

/** Shipping rule endpoints, under {@code /api/{api_version}/settings/shipping_rules}. */
public final class ShippingRules implements BoundResource {

    private final ExsitedClient client;

    public ShippingRules(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List shipping rules: {@code GET /settings/shipping_rules}. */
    public ApiResponse<SettingsShippingRuleList> getSettingsShippingRules(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ShippingRulesUrls.SETTINGS_SHIPPING_RULES,
                reopen(options).queryParams(queryParams).build(), SettingsShippingRuleList.class);
    }

    public ApiResponse<SettingsShippingRuleList> getSettingsShippingRules(Map<String, Object> queryParams) {
        return getSettingsShippingRules(queryParams, null);
    }

    public ApiResponse<SettingsShippingRuleList> getSettingsShippingRules() {
        return getSettingsShippingRules(null, null);
    }

    /** Create a shipping rule: {@code POST /settings/shipping_rules}. */
    public ApiResponse<SettingsShippingRule> createSettingsShippingRule(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ShippingRulesUrls.SETTINGS_SHIPPING_RULES,
                reopen(options).jsonBody(payload).build(), SettingsShippingRule.class);
    }

    public ApiResponse<SettingsShippingRule> createSettingsShippingRule(Object payload) {
        return createSettingsShippingRule(payload, null);
    }

    /** Get one shipping rule: {@code GET /settings/shipping_rules/{shipping_rule_id}}. */
    public ApiResponse<SettingsShippingRule> getSettingsShippingRule(
            String shippingRuleId, RequestOptions options) {
        requireValue(shippingRuleId, "shipping_rule_id");
        return client.sendGetRequest(ShippingRulesUrls.SETTINGS_SHIPPING_RULE,
                reopen(options).pathParam("shipping_rule_id", shippingRuleId).build(), SettingsShippingRule.class);
    }

    public ApiResponse<SettingsShippingRule> getSettingsShippingRule(String shippingRuleId) {
        return getSettingsShippingRule(shippingRuleId, null);
    }

    /**
     * Delete a shipping rule: {@code DELETE /settings/shipping_rules/{shipping_rule_id}}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsShippingRule(
            String shippingRuleId, RequestOptions options) {
        requireValue(shippingRuleId, "shipping_rule_id");
        return client.sendDeleteRequest(ShippingRulesUrls.SETTINGS_SHIPPING_RULE,
                reopen(options).pathParam("shipping_rule_id", shippingRuleId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsShippingRule(String shippingRuleId) {
        return deleteSettingsShippingRule(shippingRuleId, null);
    }

    /** Update a shipping rule: {@code PATCH /settings/shipping_rules/{shipping_rule_id}}. */
    public ApiResponse<SettingsShippingRule> updateSettingsShippingRule(
            String shippingRuleId, Object payload, RequestOptions options) {
        requireValue(shippingRuleId, "shipping_rule_id");
        requirePayload(payload);
        return client.sendPatchRequest(ShippingRulesUrls.SETTINGS_SHIPPING_RULE,
                reopen(options).pathParam("shipping_rule_id", shippingRuleId).jsonBody(payload).build(), SettingsShippingRule.class);
    }

    public ApiResponse<SettingsShippingRule> updateSettingsShippingRule(
            String shippingRuleId, Object payload) {
        return updateSettingsShippingRule(shippingRuleId, payload, null);
    }

    /**
     * Set a shipping rule's zones, rate and tiers:
     * {@code POST /settings/shipping_rules/{shipping_rule_id}/configuration}; each
     * {@code zones[].zone} is the zone's dashed uuid, not its prefixed id, and the body sits under
     * {@code configuration}.
     */
    public ApiResponse<SettingsShippingRule> createSettingsShippingRuleConfiguration(
            String shippingRuleId, Object payload, RequestOptions options) {
        requireValue(shippingRuleId, "shipping_rule_id");
        requirePayload(payload);
        return client.sendPostRequest(ShippingRulesUrls.SETTINGS_SHIPPING_RULE_CONFIGURATION,
                reopen(options).pathParam("shipping_rule_id", shippingRuleId).jsonBody(payload).build(), SettingsShippingRule.class);
    }

    public ApiResponse<SettingsShippingRule> createSettingsShippingRuleConfiguration(
            String shippingRuleId, Object payload) {
        return createSettingsShippingRuleConfiguration(shippingRuleId, payload, null);
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
