package com.exsited.resources.designations;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.designations.designations_dto.DesignationsDto.SettingsDesignation;
import com.exsited.resources.designations.designations_dto.DesignationsDto.SettingsDesignationList;
import java.util.Map;

/** Designation endpoints, under {@code /api/{api_version}/settings/designations}. */
public final class Designations implements BoundResource {

    private final ExsitedClient client;

    public Designations(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the designations: {@code GET /settings/designations}. */
    public ApiResponse<SettingsDesignationList> getSettingsDesignations(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(DesignationsUrls.SETTINGS_DESIGNATIONS,
                reopen(options).queryParams(queryParams).build(), SettingsDesignationList.class);
    }

    public ApiResponse<SettingsDesignationList> getSettingsDesignations(Map<String, Object> queryParams) {
        return getSettingsDesignations(queryParams, null);
    }

    public ApiResponse<SettingsDesignationList> getSettingsDesignations() {
        return getSettingsDesignations(null, null);
    }

    /**
     * Create a designation; {@code name} is required: {@code POST /settings/designations}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createSettingsDesignation(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(DesignationsUrls.SETTINGS_DESIGNATIONS,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createSettingsDesignation(Object payload) {
        return createSettingsDesignation(payload, null);
    }

    /**
     * Read one designation by its {@code des_} id:
     * {@code GET /settings/designations/{designation_id}}.
     */
    public ApiResponse<SettingsDesignation> getSettingsDesignation(
            String designationId, RequestOptions options) {
        requireValue(designationId, "designation_id");
        return client.sendGetRequest(DesignationsUrls.SETTINGS_DESIGNATION,
                reopen(options).pathParam("designation_id", designationId).build(), SettingsDesignation.class);
    }

    public ApiResponse<SettingsDesignation> getSettingsDesignation(String designationId) {
        return getSettingsDesignation(designationId, null);
    }

    /**
     * Delete a designation: {@code DELETE /settings/designations/{designation_id}}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsDesignation(
            String designationId, RequestOptions options) {
        requireValue(designationId, "designation_id");
        return client.sendDeleteRequest(DesignationsUrls.SETTINGS_DESIGNATION,
                reopen(options).pathParam("designation_id", designationId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsDesignation(String designationId) {
        return deleteSettingsDesignation(designationId, null);
    }

    /**
     * Update a designation's name, display name or description:
     * {@code PATCH /settings/designations/{designation_id}}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsDesignation(
            String designationId, Object payload, RequestOptions options) {
        requireValue(designationId, "designation_id");
        requirePayload(payload);
        return client.sendPatchRequest(DesignationsUrls.SETTINGS_DESIGNATION,
                reopen(options).pathParam("designation_id", designationId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsDesignation(String designationId, Object payload) {
        return updateSettingsDesignation(designationId, payload, null);
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
