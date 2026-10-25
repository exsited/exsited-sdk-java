package com.exsited.resources.applications;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.applications.applications_dto.ApplicationsDto.SettingsApplication;
import com.exsited.resources.applications.applications_dto.ApplicationsDto.SettingsApplicationList;
import java.util.Map;

/** Application endpoints, under {@code /api/{api_version}/settings/applications}. */
public final class Applications implements BoundResource {

    private final ExsitedClient client;

    public Applications(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the OAuth applications: {@code GET /settings/applications}. */
    public ApiResponse<SettingsApplicationList> getSettingsApplications(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ApplicationsUrls.SETTINGS_APPLICATIONS,
                reopen(options).queryParams(queryParams).build(), SettingsApplicationList.class);
    }

    public ApiResponse<SettingsApplicationList> getSettingsApplications(Map<String, Object> queryParams) {
        return getSettingsApplications(queryParams, null);
    }

    public ApiResponse<SettingsApplicationList> getSettingsApplications() {
        return getSettingsApplications(null, null);
    }

    /**
     * Create an OAuth application; {@code name} and {@code redirect_url} are required, and the
     * client id and secret are generated server side and never answered:
     * {@code POST /settings/applications}.
     */
    public ApiResponse<SettingsApplication> createSettingsApplication(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ApplicationsUrls.SETTINGS_APPLICATIONS,
                reopen(options).jsonBody(payload).build(), SettingsApplication.class);
    }

    public ApiResponse<SettingsApplication> createSettingsApplication(Object payload) {
        return createSettingsApplication(payload, null);
    }

    /**
     * Read one OAuth application by its {@code app_} id:
     * {@code GET /settings/applications/{application_id}}.
     */
    public ApiResponse<SettingsApplication> getSettingsApplication(
            String applicationId, RequestOptions options) {
        requireValue(applicationId, "application_id");
        return client.sendGetRequest(ApplicationsUrls.SETTINGS_APPLICATION,
                reopen(options).pathParam("application_id", applicationId).build(), SettingsApplication.class);
    }

    public ApiResponse<SettingsApplication> getSettingsApplication(String applicationId) {
        return getSettingsApplication(applicationId, null);
    }

    /**
     * Delete an OAuth application: {@code DELETE /settings/applications/{application_id}}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsApplication(
            String applicationId, RequestOptions options) {
        requireValue(applicationId, "application_id");
        return client.sendDeleteRequest(ApplicationsUrls.SETTINGS_APPLICATION,
                reopen(options).pathParam("application_id", applicationId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsApplication(String applicationId) {
        return deleteSettingsApplication(applicationId, null);
    }

    /**
     * Update an OAuth application's fields or its status:
     * {@code PATCH /settings/applications/{application_id}}.
     */
    public ApiResponse<SettingsApplication> updateSettingsApplication(
            String applicationId, Object payload, RequestOptions options) {
        requireValue(applicationId, "application_id");
        requirePayload(payload);
        return client.sendPatchRequest(ApplicationsUrls.SETTINGS_APPLICATION,
                reopen(options).pathParam("application_id", applicationId).jsonBody(payload).build(), SettingsApplication.class);
    }

    public ApiResponse<SettingsApplication> updateSettingsApplication(String applicationId, Object payload) {
        return updateSettingsApplication(applicationId, payload, null);
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
