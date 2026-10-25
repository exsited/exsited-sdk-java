package com.exsited.resources.billing_periods;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.billing_periods.billing_periods_dto.BillingPeriodsDto.SettingsBillingPeriod;
import com.exsited.resources.billing_periods.billing_periods_dto.BillingPeriodsDto.SettingsBillingPeriodList;
import java.util.Map;

/** Billing period endpoints, under {@code /api/{api_version}/settings/billing_periods}. */
public final class BillingPeriods implements BoundResource {

    private final ExsitedClient client;

    public BillingPeriods(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the billing periods: {@code GET /settings/billing_periods}. */
    public ApiResponse<SettingsBillingPeriodList> getSettingsBillingPeriods(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(BillingPeriodsUrls.SETTINGS_BILLING_PERIODS,
                reopen(options).queryParams(queryParams).build(), SettingsBillingPeriodList.class);
    }

    public ApiResponse<SettingsBillingPeriodList> getSettingsBillingPeriods(Map<String, Object> queryParams) {
        return getSettingsBillingPeriods(queryParams, null);
    }

    public ApiResponse<SettingsBillingPeriodList> getSettingsBillingPeriods() {
        return getSettingsBillingPeriods(null, null);
    }

    /**
     * Create a billing period; {@code name} and {@code time_period} are required, and
     * {@code time_period} is the time period's hyphenated uuid, not its {@code tp_} id:
     * {@code POST /settings/billing_periods}.
     */
    public ApiResponse<SettingsBillingPeriod> createSettingsBillingPeriod(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(BillingPeriodsUrls.SETTINGS_BILLING_PERIODS,
                reopen(options).jsonBody(payload).build(), SettingsBillingPeriod.class);
    }

    public ApiResponse<SettingsBillingPeriod> createSettingsBillingPeriod(Object payload) {
        return createSettingsBillingPeriod(payload, null);
    }

    /**
     * Read one billing period by its {@code bp_} id:
     * {@code GET /settings/billing_periods/{billing_period_id}}.
     */
    public ApiResponse<SettingsBillingPeriod> getSettingsBillingPeriod(
            String billingPeriodId, RequestOptions options) {
        requireValue(billingPeriodId, "billing_period_id");
        return client.sendGetRequest(BillingPeriodsUrls.SETTINGS_BILLING_PERIOD,
                reopen(options).pathParam("billing_period_id", billingPeriodId).build(), SettingsBillingPeriod.class);
    }

    public ApiResponse<SettingsBillingPeriod> getSettingsBillingPeriod(String billingPeriodId) {
        return getSettingsBillingPeriod(billingPeriodId, null);
    }

    /**
     * Delete a billing period: {@code DELETE /settings/billing_periods/{billing_period_id}}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsBillingPeriod(
            String billingPeriodId, RequestOptions options) {
        requireValue(billingPeriodId, "billing_period_id");
        return client.sendDeleteRequest(BillingPeriodsUrls.SETTINGS_BILLING_PERIOD,
                reopen(options).pathParam("billing_period_id", billingPeriodId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsBillingPeriod(String billingPeriodId) {
        return deleteSettingsBillingPeriod(billingPeriodId, null);
    }

    /**
     * Update a billing period; the answer carries {@code time_period} in place of {@code period}
     * and {@code duration}: {@code PATCH /settings/billing_periods/{billing_period_id}}.
     */
    public ApiResponse<SettingsBillingPeriod> updateSettingsBillingPeriod(
            String billingPeriodId, Object payload, RequestOptions options) {
        requireValue(billingPeriodId, "billing_period_id");
        requirePayload(payload);
        return client.sendPatchRequest(BillingPeriodsUrls.SETTINGS_BILLING_PERIOD,
                reopen(options).pathParam("billing_period_id", billingPeriodId).jsonBody(payload).build(), SettingsBillingPeriod.class);
    }

    public ApiResponse<SettingsBillingPeriod> updateSettingsBillingPeriod(
            String billingPeriodId, Object payload) {
        return updateSettingsBillingPeriod(billingPeriodId, payload, null);
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
