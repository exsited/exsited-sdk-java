package com.exsited.resources.charging_periods;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.charging_periods.charging_periods_dto.ChargingPeriodsDto.SettingsChargingPeriod;
import com.exsited.resources.charging_periods.charging_periods_dto.ChargingPeriodsDto.SettingsChargingPeriodList;
import java.util.Map;

/** Charging period endpoints, under {@code /api/{api_version}/settings/charging_periods}. */
public final class ChargingPeriods implements BoundResource {

    private final ExsitedClient client;

    public ChargingPeriods(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the charging periods: {@code GET /settings/charging_periods}. */
    public ApiResponse<SettingsChargingPeriodList> getSettingsChargingPeriods(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ChargingPeriodsUrls.SETTINGS_CHARGING_PERIODS,
                reopen(options).queryParams(queryParams).build(), SettingsChargingPeriodList.class);
    }

    public ApiResponse<SettingsChargingPeriodList> getSettingsChargingPeriods(
            Map<String, Object> queryParams) {
        return getSettingsChargingPeriods(queryParams, null);
    }

    public ApiResponse<SettingsChargingPeriodList> getSettingsChargingPeriods() {
        return getSettingsChargingPeriods(null, null);
    }

    /**
     * Create a charging period; {@code name}, {@code display_name} and {@code time_period}, a
     * {@code tp_} id, are required: {@code POST /settings/charging_periods}.
     */
    public ApiResponse<SettingsChargingPeriod> createSettingsChargingPeriod(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ChargingPeriodsUrls.SETTINGS_CHARGING_PERIODS,
                reopen(options).jsonBody(payload).build(), SettingsChargingPeriod.class);
    }

    public ApiResponse<SettingsChargingPeriod> createSettingsChargingPeriod(Object payload) {
        return createSettingsChargingPeriod(payload, null);
    }

    /**
     * Read one charging period by its {@code cp_} id:
     * {@code GET /settings/charging_periods/{charging_period_id}}.
     */
    public ApiResponse<SettingsChargingPeriod> getSettingsChargingPeriod(
            String chargingPeriodId, RequestOptions options) {
        requireValue(chargingPeriodId, "charging_period_id");
        return client.sendGetRequest(ChargingPeriodsUrls.SETTINGS_CHARGING_PERIOD,
                reopen(options).pathParam("charging_period_id", chargingPeriodId).build(), SettingsChargingPeriod.class);
    }

    public ApiResponse<SettingsChargingPeriod> getSettingsChargingPeriod(String chargingPeriodId) {
        return getSettingsChargingPeriod(chargingPeriodId, null);
    }

    /**
     * Delete a charging period: {@code DELETE /settings/charging_periods/{charging_period_id}}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsChargingPeriod(
            String chargingPeriodId, RequestOptions options) {
        requireValue(chargingPeriodId, "charging_period_id");
        return client.sendDeleteRequest(ChargingPeriodsUrls.SETTINGS_CHARGING_PERIOD,
                reopen(options).pathParam("charging_period_id", chargingPeriodId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsChargingPeriod(String chargingPeriodId) {
        return deleteSettingsChargingPeriod(chargingPeriodId, null);
    }

    /**
     * Update a charging period's fields or its status:
     * {@code PATCH /settings/charging_periods/{charging_period_id}}.
     */
    public ApiResponse<SettingsChargingPeriod> updateSettingsChargingPeriod(
            String chargingPeriodId, Object payload, RequestOptions options) {
        requireValue(chargingPeriodId, "charging_period_id");
        requirePayload(payload);
        return client.sendPatchRequest(ChargingPeriodsUrls.SETTINGS_CHARGING_PERIOD,
                reopen(options).pathParam("charging_period_id", chargingPeriodId).jsonBody(payload).build(), SettingsChargingPeriod.class);
    }

    public ApiResponse<SettingsChargingPeriod> updateSettingsChargingPeriod(
            String chargingPeriodId, Object payload) {
        return updateSettingsChargingPeriod(chargingPeriodId, payload, null);
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
