package com.exsited.resources.pricing_periods;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.pricing_periods.pricing_periods_dto.PricingPeriodsDto.SettingsPricingPeriod;
import com.exsited.resources.pricing_periods.pricing_periods_dto.PricingPeriodsDto.SettingsPricingPeriodList;
import java.util.Map;

/** Pricing period endpoints, under {@code /api/{api_version}/settings/pricing_periods}. */
public final class PricingPeriods implements BoundResource {

    private final ExsitedClient client;

    public PricingPeriods(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List pricing periods: {@code GET /settings/pricing_periods}; {@code time_period} reads null
     * here, whatever the record holds.
     */
    public ApiResponse<SettingsPricingPeriodList> getSettingsPricingPeriods(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(PricingPeriodsUrls.SETTINGS_PRICING_PERIODS,
                reopen(options).queryParams(queryParams).build(), SettingsPricingPeriodList.class);
    }

    public ApiResponse<SettingsPricingPeriodList> getSettingsPricingPeriods(Map<String, Object> queryParams) {
        return getSettingsPricingPeriods(queryParams, null);
    }

    public ApiResponse<SettingsPricingPeriodList> getSettingsPricingPeriods() {
        return getSettingsPricingPeriods(null, null);
    }

    /** Create a pricing period: {@code POST /settings/pricing_periods}. */
    public ApiResponse<SettingsPricingPeriod> createSettingsPricingPeriod(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PricingPeriodsUrls.SETTINGS_PRICING_PERIODS,
                reopen(options).jsonBody(payload).build(), SettingsPricingPeriod.class);
    }

    public ApiResponse<SettingsPricingPeriod> createSettingsPricingPeriod(Object payload) {
        return createSettingsPricingPeriod(payload, null);
    }

    /**
     * Get one pricing period: {@code GET /settings/pricing_periods/{pricing_period_id}};
     * {@code time_period} reads null here, whatever the record holds.
     */
    public ApiResponse<SettingsPricingPeriod> getSettingsPricingPeriod(
            String pricingPeriodId, RequestOptions options) {
        requireValue(pricingPeriodId, "pricing_period_id");
        return client.sendGetRequest(PricingPeriodsUrls.SETTINGS_PRICING_PERIOD,
                reopen(options).pathParam("pricing_period_id", pricingPeriodId).build(), SettingsPricingPeriod.class);
    }

    public ApiResponse<SettingsPricingPeriod> getSettingsPricingPeriod(String pricingPeriodId) {
        return getSettingsPricingPeriod(pricingPeriodId, null);
    }

    /**
     * Delete a pricing period: {@code DELETE /settings/pricing_periods/{pricing_period_id}}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsPricingPeriod(
            String pricingPeriodId, RequestOptions options) {
        requireValue(pricingPeriodId, "pricing_period_id");
        return client.sendDeleteRequest(PricingPeriodsUrls.SETTINGS_PRICING_PERIOD,
                reopen(options).pathParam("pricing_period_id", pricingPeriodId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsPricingPeriod(String pricingPeriodId) {
        return deleteSettingsPricingPeriod(pricingPeriodId, null);
    }

    /**
     * Update or enable/disable a pricing period:
     * {@code PATCH /settings/pricing_periods/{pricing_period_id}}; {@code status} takes active or
     * inactive.
     */
    public ApiResponse<SettingsPricingPeriod> updateSettingsPricingPeriod(
            String pricingPeriodId, Object payload, RequestOptions options) {
        requireValue(pricingPeriodId, "pricing_period_id");
        requirePayload(payload);
        return client.sendPatchRequest(PricingPeriodsUrls.SETTINGS_PRICING_PERIOD,
                reopen(options).pathParam("pricing_period_id", pricingPeriodId).jsonBody(payload).build(), SettingsPricingPeriod.class);
    }

    public ApiResponse<SettingsPricingPeriod> updateSettingsPricingPeriod(
            String pricingPeriodId, Object payload) {
        return updateSettingsPricingPeriod(pricingPeriodId, payload, null);
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
