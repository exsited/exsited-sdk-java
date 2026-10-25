package com.exsited.resources.time_periods;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.time_periods.time_periods_dto.TimePeriodsDto.SettingsTimePeriod;
import com.exsited.resources.time_periods.time_periods_dto.TimePeriodsDto.SettingsTimePeriodList;
import java.util.Map;

/** Time period endpoints, under {@code /api/{api_version}/settings/time_periods}. */
public final class TimePeriods implements BoundResource {

    private final ExsitedClient client;

    public TimePeriods(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List time periods: {@code GET /settings/time_periods}. */
    public ApiResponse<SettingsTimePeriodList> getSettingsTimePeriods(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(TimePeriodsUrls.SETTINGS_TIME_PERIODS,
                reopen(options).queryParams(queryParams).build(), SettingsTimePeriodList.class);
    }

    public ApiResponse<SettingsTimePeriodList> getSettingsTimePeriods(Map<String, Object> queryParams) {
        return getSettingsTimePeriods(queryParams, null);
    }

    public ApiResponse<SettingsTimePeriodList> getSettingsTimePeriods() {
        return getSettingsTimePeriods(null, null);
    }

    /**
     * Create a time period: {@code POST /settings/time_periods}; {@code period} is sent upper case
     * and answered lower case.
     */
    public ApiResponse<SettingsTimePeriod> createSettingsTimePeriod(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(TimePeriodsUrls.SETTINGS_TIME_PERIODS,
                reopen(options).jsonBody(payload).build(), SettingsTimePeriod.class);
    }

    public ApiResponse<SettingsTimePeriod> createSettingsTimePeriod(Object payload) {
        return createSettingsTimePeriod(payload, null);
    }

    /** Get one time period: {@code GET /settings/time_periods/{time_period_id}}. */
    public ApiResponse<SettingsTimePeriod> getSettingsTimePeriod(
            String timePeriodId, RequestOptions options) {
        requireValue(timePeriodId, "time_period_id");
        return client.sendGetRequest(TimePeriodsUrls.SETTINGS_TIME_PERIOD,
                reopen(options).pathParam("time_period_id", timePeriodId).build(), SettingsTimePeriod.class);
    }

    public ApiResponse<SettingsTimePeriod> getSettingsTimePeriod(String timePeriodId) {
        return getSettingsTimePeriod(timePeriodId, null);
    }

    /**
     * Delete a time period: {@code DELETE /settings/time_periods/{time_period_id}}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsTimePeriod(
            String timePeriodId, RequestOptions options) {
        requireValue(timePeriodId, "time_period_id");
        return client.sendDeleteRequest(TimePeriodsUrls.SETTINGS_TIME_PERIOD,
                reopen(options).pathParam("time_period_id", timePeriodId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsTimePeriod(String timePeriodId) {
        return deleteSettingsTimePeriod(timePeriodId, null);
    }

    /**
     * Update or enable/disable a time period:
     * {@code PATCH /settings/time_periods/{time_period_id}}; {@code status} takes active or
     * inactive.
     */
    public ApiResponse<SettingsTimePeriod> updateSettingsTimePeriod(
            String timePeriodId, Object payload, RequestOptions options) {
        requireValue(timePeriodId, "time_period_id");
        requirePayload(payload);
        return client.sendPatchRequest(TimePeriodsUrls.SETTINGS_TIME_PERIOD,
                reopen(options).pathParam("time_period_id", timePeriodId).jsonBody(payload).build(), SettingsTimePeriod.class);
    }

    public ApiResponse<SettingsTimePeriod> updateSettingsTimePeriod(String timePeriodId, Object payload) {
        return updateSettingsTimePeriod(timePeriodId, payload, null);
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
