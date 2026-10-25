package com.exsited.resources.alert_responsibility;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.alert_responsibility.alert_responsibility_dto.AlertResponsibilityDto.AlertResponsibilityList;
import com.exsited.resources.alert_responsibility.alert_responsibility_dto.AlertResponsibilityDto;
import java.util.Map;

/** Alert responsibility endpoints, under {@code /api/{api_version}/alert_responsibility}. */
public final class AlertResponsibility implements BoundResource {

    private final ExsitedClient client;

    public AlertResponsibility(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List alert responsibilities: {@code GET /alert_responsibility}, the singular path the
     * workflow services serve.
     */
    public ApiResponse<AlertResponsibilityList> getAlertResponsibilities(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(AlertResponsibilityUrls.ALERT_RESPONSIBILITIES,
                reopen(options).queryParams(queryParams).build(), AlertResponsibilityList.class);
    }

    public ApiResponse<AlertResponsibilityList> getAlertResponsibilities(Map<String, Object> queryParams) {
        return getAlertResponsibilities(queryParams, null);
    }

    public ApiResponse<AlertResponsibilityList> getAlertResponsibilities() {
        return getAlertResponsibilities(null, null);
    }

    /**
     * One alert responsibility: {@code GET /alert_responsibility/{alert_responsibility_id}}; the
     * {@code ar_} id or the raw uuid.
     */
    public ApiResponse<AlertResponsibilityDto.AlertResponsibility> getAlertResponsibility(
            String alertResponsibilityId, RequestOptions options) {
        requireValue(alertResponsibilityId, "alert_responsibility_id");
        return client.sendGetRequest(AlertResponsibilityUrls.ALERT_RESPONSIBILITY,
                reopen(options).pathParam("alert_responsibility_id", alertResponsibilityId).build(), AlertResponsibilityDto.AlertResponsibility.class);
    }

    public ApiResponse<AlertResponsibilityDto.AlertResponsibility> getAlertResponsibility(
            String alertResponsibilityId) {
        return getAlertResponsibility(alertResponsibilityId, null);
    }

    /**
     * Create an alert responsibility: {@code POST /alert_responsibility}, under
     * {@code alert_responsibility}; {@code name} is required and unique, beside
     * {@code display_name} and {@code description} only.
     */
    public ApiResponse<AlertResponsibilityDto.AlertResponsibility> createAlertResponsibility(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(AlertResponsibilityUrls.ALERT_RESPONSIBILITIES,
                reopen(options).jsonBody(payload).build(), AlertResponsibilityDto.AlertResponsibility.class);
    }

    public ApiResponse<AlertResponsibilityDto.AlertResponsibility> createAlertResponsibility(Object payload) {
        return createAlertResponsibility(payload, null);
    }

    /**
     * Update an alert responsibility:
     * {@code PATCH /alert_responsibility/{alert_responsibility_id}}; {@code status} may move
     * between {@code active} and {@code inactive} beside the three create keys.
     */
    public ApiResponse<AlertResponsibilityDto.AlertResponsibility> updateAlertResponsibility(
            String alertResponsibilityId, Object payload, RequestOptions options) {
        requireValue(alertResponsibilityId, "alert_responsibility_id");
        requirePayload(payload);
        return client.sendPatchRequest(AlertResponsibilityUrls.ALERT_RESPONSIBILITY,
                reopen(options).pathParam("alert_responsibility_id", alertResponsibilityId).jsonBody(payload).build(), AlertResponsibilityDto.AlertResponsibility.class);
    }

    public ApiResponse<AlertResponsibilityDto.AlertResponsibility> updateAlertResponsibility(
            String alertResponsibilityId, Object payload) {
        return updateAlertResponsibility(alertResponsibilityId, payload, null);
    }

    /**
     * Delete an alert responsibility:
     * {@code DELETE /alert_responsibility/{alert_responsibility_id}}, a soft delete; the id must be
     * the {@code ar_} one. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteAlertResponsibility(
            String alertResponsibilityId, RequestOptions options) {
        requireValue(alertResponsibilityId, "alert_responsibility_id");
        return client.sendDeleteRequest(AlertResponsibilityUrls.ALERT_RESPONSIBILITY,
                reopen(options).pathParam("alert_responsibility_id", alertResponsibilityId).build(), null);
    }

    public ApiResponse<Dto> deleteAlertResponsibility(String alertResponsibilityId) {
        return deleteAlertResponsibility(alertResponsibilityId, null);
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
