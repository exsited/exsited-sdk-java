package com.exsited.resources.organisations;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import java.util.Map;

/** Child organisation endpoints, under {@code /api/{api_version}/settings/organisations}. */
public final class Organisations implements BoundResource {

    private final ExsitedClient client;

    public Organisations(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List the child organisations: {@code GET /settings/organisations}; the root is never among
     * them. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getSettingsOrganisations(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(OrganisationsUrls.SETTINGS_ORGANISATIONS,
                reopen(options).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getSettingsOrganisations(Map<String, Object> queryParams) {
        return getSettingsOrganisations(queryParams, null);
    }

    public ApiResponse<Dto> getSettingsOrganisations() {
        return getSettingsOrganisations(null, null);
    }

    /**
     * Create a child organisation: {@code POST /settings/organisations}; the envelope is
     * {@code organization}, with a z. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createSettingsOrganisation(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(OrganisationsUrls.SETTINGS_ORGANISATIONS,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createSettingsOrganisation(Object payload) {
        return createSettingsOrganisation(payload, null);
    }

    /**
     * Get one organisation: {@code GET /settings/organisations/{organisation_id}}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> getSettingsOrganisation(
            String organisationId, RequestOptions options) {
        requireValue(organisationId, "organisation_id");
        return client.sendGetRequest(OrganisationsUrls.SETTINGS_ORGANISATION,
                reopen(options).pathParam("organisation_id", organisationId).build(), null);
    }

    public ApiResponse<Dto> getSettingsOrganisation(String organisationId) {
        return getSettingsOrganisation(organisationId, null);
    }

    /**
     * Delete an organisation: {@code DELETE /settings/organisations/{organisation_id}}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsOrganisation(
            String organisationId, RequestOptions options) {
        requireValue(organisationId, "organisation_id");
        return client.sendDeleteRequest(OrganisationsUrls.SETTINGS_ORGANISATION,
                reopen(options).pathParam("organisation_id", organisationId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsOrganisation(String organisationId) {
        return deleteSettingsOrganisation(organisationId, null);
    }

    /**
     * Update an organisation: {@code PATCH /settings/organisations/{organisation_id}}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsOrganisation(
            String organisationId, Object payload, RequestOptions options) {
        requireValue(organisationId, "organisation_id");
        requirePayload(payload);
        return client.sendPatchRequest(OrganisationsUrls.SETTINGS_ORGANISATION,
                reopen(options).pathParam("organisation_id", organisationId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsOrganisation(String organisationId, Object payload) {
        return updateSettingsOrganisation(organisationId, payload, null);
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
