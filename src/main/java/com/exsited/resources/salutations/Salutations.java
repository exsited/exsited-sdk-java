package com.exsited.resources.salutations;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.salutations.salutations_dto.SalutationsDto.SettingsSalutation;
import com.exsited.resources.salutations.salutations_dto.SalutationsDto.SettingsSalutationList;
import java.util.Map;

/** Salutation endpoints, under {@code /api/{api_version}/settings/salutations}. */
public final class Salutations implements BoundResource {

    private final ExsitedClient client;

    public Salutations(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List salutations: {@code GET /settings/salutations}. */
    public ApiResponse<SettingsSalutationList> getSettingsSalutations(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SalutationsUrls.SETTINGS_SALUTATIONS,
                reopen(options).queryParams(queryParams).build(), SettingsSalutationList.class);
    }

    public ApiResponse<SettingsSalutationList> getSettingsSalutations(Map<String, Object> queryParams) {
        return getSettingsSalutations(queryParams, null);
    }

    public ApiResponse<SettingsSalutationList> getSettingsSalutations() {
        return getSettingsSalutations(null, null);
    }

    /** Create a salutation: {@code POST /settings/salutations}. */
    public ApiResponse<SettingsSalutation> createSettingsSalutation(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SalutationsUrls.SETTINGS_SALUTATIONS,
                reopen(options).jsonBody(payload).build(), SettingsSalutation.class);
    }

    public ApiResponse<SettingsSalutation> createSettingsSalutation(Object payload) {
        return createSettingsSalutation(payload, null);
    }

    /** Get one salutation: {@code GET /settings/salutations/{salutation_id}}. */
    public ApiResponse<SettingsSalutation> getSettingsSalutation(
            String salutationId, RequestOptions options) {
        requireValue(salutationId, "salutation_id");
        return client.sendGetRequest(SalutationsUrls.SETTINGS_SALUTATION,
                reopen(options).pathParam("salutation_id", salutationId).build(), SettingsSalutation.class);
    }

    public ApiResponse<SettingsSalutation> getSettingsSalutation(String salutationId) {
        return getSettingsSalutation(salutationId, null);
    }

    /**
     * Delete a salutation: {@code DELETE /settings/salutations/{salutation_id}}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsSalutation(
            String salutationId, RequestOptions options) {
        requireValue(salutationId, "salutation_id");
        return client.sendDeleteRequest(SalutationsUrls.SETTINGS_SALUTATION,
                reopen(options).pathParam("salutation_id", salutationId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsSalutation(String salutationId) {
        return deleteSettingsSalutation(salutationId, null);
    }

    /** Update a salutation: {@code PATCH /settings/salutations/{salutation_id}}. */
    public ApiResponse<SettingsSalutation> updateSettingsSalutation(
            String salutationId, Object payload, RequestOptions options) {
        requireValue(salutationId, "salutation_id");
        requirePayload(payload);
        return client.sendPatchRequest(SalutationsUrls.SETTINGS_SALUTATION,
                reopen(options).pathParam("salutation_id", salutationId).jsonBody(payload).build(), SettingsSalutation.class);
    }

    public ApiResponse<SettingsSalutation> updateSettingsSalutation(String salutationId, Object payload) {
        return updateSettingsSalutation(salutationId, payload, null);
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
