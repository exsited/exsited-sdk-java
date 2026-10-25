package com.exsited.resources.alert_responsibilities;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import java.util.Map;

/**
 * Alert responsibility endpoints, under {@code /api/{api_version}/settings/alert_responsibilities}.
 */
public final class AlertResponsibilities implements BoundResource {

    private final ExsitedClient client;

    public AlertResponsibilities(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List the alert responsibilities: {@code GET /settings/alert_responsibilities}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> getSettingsAlertResponsibilities(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(AlertResponsibilitiesUrls.SETTINGS_ALERT_RESPONSIBILITIES,
                reopen(options).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getSettingsAlertResponsibilities(Map<String, Object> queryParams) {
        return getSettingsAlertResponsibilities(queryParams, null);
    }

    public ApiResponse<Dto> getSettingsAlertResponsibilities() {
        return getSettingsAlertResponsibilities(null, null);
    }

    /**
     * Create an alert responsibility; {@code name} is required:
     * {@code POST /settings/alert_responsibilities}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createSettingsAlertResponsibility(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(AlertResponsibilitiesUrls.SETTINGS_ALERT_RESPONSIBILITIES,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createSettingsAlertResponsibility(Object payload) {
        return createSettingsAlertResponsibility(payload, null);
    }

    /**
     * Read one alert responsibility by its {@code ar_} id:
     * {@code GET /settings/alert_responsibilities/{alert_responsibility_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> getSettingsAlertResponsibility(
            String alertResponsibilityId, RequestOptions options) {
        requireValue(alertResponsibilityId, "alert_responsibility_id");
        return client.sendGetRequest(AlertResponsibilitiesUrls.SETTINGS_ALERT_RESPONSIBILITY,
                reopen(options).pathParam("alert_responsibility_id", alertResponsibilityId).build(), null);
    }

    public ApiResponse<Dto> getSettingsAlertResponsibility(String alertResponsibilityId) {
        return getSettingsAlertResponsibility(alertResponsibilityId, null);
    }

    /**
     * Delete an alert responsibility:
     * {@code DELETE /settings/alert_responsibilities/{alert_responsibility_id}}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsAlertResponsibility(
            String alertResponsibilityId, RequestOptions options) {
        requireValue(alertResponsibilityId, "alert_responsibility_id");
        return client.sendDeleteRequest(AlertResponsibilitiesUrls.SETTINGS_ALERT_RESPONSIBILITY,
                reopen(options).pathParam("alert_responsibility_id", alertResponsibilityId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsAlertResponsibility(String alertResponsibilityId) {
        return deleteSettingsAlertResponsibility(alertResponsibilityId, null);
    }

    /**
     * Update an alert responsibility's fields or its status:
     * {@code PATCH /settings/alert_responsibilities/{alert_responsibility_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsAlertResponsibility(
            String alertResponsibilityId, Object payload, RequestOptions options) {
        requireValue(alertResponsibilityId, "alert_responsibility_id");
        requirePayload(payload);
        return client.sendPatchRequest(AlertResponsibilitiesUrls.SETTINGS_ALERT_RESPONSIBILITY,
                reopen(options).pathParam("alert_responsibility_id", alertResponsibilityId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsAlertResponsibility(String alertResponsibilityId, Object payload) {
        return updateSettingsAlertResponsibility(alertResponsibilityId, payload, null);
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
