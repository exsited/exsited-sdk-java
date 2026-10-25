package com.exsited.resources.day_of_month;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.day_of_month.day_of_month_dto.DayOfMonthDto.SettingsDayOfMonth;
import com.exsited.resources.day_of_month.day_of_month_dto.DayOfMonthDto.SettingsDayOfMonthList;
import java.util.Map;

/** Day of month endpoints, under {@code /api/{api_version}/settings/day_of_month}. */
public final class DayOfMonth implements BoundResource {

    private final ExsitedClient client;

    public DayOfMonth(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the days of the month a date can be aligned to: {@code GET /settings/day_of_month}. */
    public ApiResponse<SettingsDayOfMonthList> getSettingsDaysOfMonth(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(DayOfMonthUrls.SETTINGS_DAYS_OF_MONTH,
                reopen(options).queryParams(queryParams).build(), SettingsDayOfMonthList.class);
    }

    public ApiResponse<SettingsDayOfMonthList> getSettingsDaysOfMonth(Map<String, Object> queryParams) {
        return getSettingsDaysOfMonth(queryParams, null);
    }

    public ApiResponse<SettingsDayOfMonthList> getSettingsDaysOfMonth() {
        return getSettingsDaysOfMonth(null, null);
    }

    /**
     * Read one day of the month by its {@code dom_} id:
     * {@code GET /settings/day_of_month/{day_of_month_id}}.
     */
    public ApiResponse<SettingsDayOfMonth> getSettingsDayOfMonth(
            String dayOfMonthId, RequestOptions options) {
        requireValue(dayOfMonthId, "day_of_month_id");
        return client.sendGetRequest(DayOfMonthUrls.SETTINGS_DAY_OF_MONTH,
                reopen(options).pathParam("day_of_month_id", dayOfMonthId).build(), SettingsDayOfMonth.class);
    }

    public ApiResponse<SettingsDayOfMonth> getSettingsDayOfMonth(String dayOfMonthId) {
        return getSettingsDayOfMonth(dayOfMonthId, null);
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
}
