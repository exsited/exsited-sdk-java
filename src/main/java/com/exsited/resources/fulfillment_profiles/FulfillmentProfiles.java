package com.exsited.resources.fulfillment_profiles;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.fulfillment_profiles.fulfillment_profiles_dto.FulfillmentProfilesDto.SettingsFulfillmentProfile;
import com.exsited.resources.fulfillment_profiles.fulfillment_profiles_dto.FulfillmentProfilesDto.SettingsFulfillmentProfileList;
import java.util.Map;

/**
 * Fulfillment profile endpoints, under {@code /api/{api_version}/settings/fulfillment_profiles}.
 */
public final class FulfillmentProfiles implements BoundResource {

    private final ExsitedClient client;

    public FulfillmentProfiles(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List fulfillment profiles: {@code GET /settings/fulfillment_profiles}. */
    public ApiResponse<SettingsFulfillmentProfileList> getSettingsFulfillmentProfiles(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(FulfillmentProfilesUrls.SETTINGS_FULFILLMENT_PROFILES,
                reopen(options).queryParams(queryParams).build(), SettingsFulfillmentProfileList.class);
    }

    public ApiResponse<SettingsFulfillmentProfileList> getSettingsFulfillmentProfiles(
            Map<String, Object> queryParams) {
        return getSettingsFulfillmentProfiles(queryParams, null);
    }

    public ApiResponse<SettingsFulfillmentProfileList> getSettingsFulfillmentProfiles() {
        return getSettingsFulfillmentProfiles(null, null);
    }

    /**
     * Create a fulfillment profile: {@code POST /settings/fulfillment_profiles}; {@code statuses}
     * needs one entry at least.
     */
    public ApiResponse<SettingsFulfillmentProfile> createSettingsFulfillmentProfile(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(FulfillmentProfilesUrls.SETTINGS_FULFILLMENT_PROFILES,
                reopen(options).jsonBody(payload).build(), SettingsFulfillmentProfile.class);
    }

    public ApiResponse<SettingsFulfillmentProfile> createSettingsFulfillmentProfile(Object payload) {
        return createSettingsFulfillmentProfile(payload, null);
    }

    /**
     * Get one fulfillment profile:
     * {@code GET /settings/fulfillment_profiles/{fulfillment_profile_id}}.
     */
    public ApiResponse<SettingsFulfillmentProfile> getSettingsFulfillmentProfile(
            String fulfillmentProfileId, RequestOptions options) {
        requireValue(fulfillmentProfileId, "fulfillment_profile_id");
        return client.sendGetRequest(FulfillmentProfilesUrls.SETTINGS_FULFILLMENT_PROFILE,
                reopen(options).pathParam("fulfillment_profile_id", fulfillmentProfileId).build(), SettingsFulfillmentProfile.class);
    }

    public ApiResponse<SettingsFulfillmentProfile> getSettingsFulfillmentProfile(
            String fulfillmentProfileId) {
        return getSettingsFulfillmentProfile(fulfillmentProfileId, null);
    }

    /**
     * Delete a fulfillment profile:
     * {@code DELETE /settings/fulfillment_profiles/{fulfillment_profile_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsFulfillmentProfile(
            String fulfillmentProfileId, RequestOptions options) {
        requireValue(fulfillmentProfileId, "fulfillment_profile_id");
        return client.sendDeleteRequest(FulfillmentProfilesUrls.SETTINGS_FULFILLMENT_PROFILE,
                reopen(options).pathParam("fulfillment_profile_id", fulfillmentProfileId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsFulfillmentProfile(String fulfillmentProfileId) {
        return deleteSettingsFulfillmentProfile(fulfillmentProfileId, null);
    }

    /**
     * Update a fulfillment profile:
     * {@code PATCH /settings/fulfillment_profiles/{fulfillment_profile_id}}.
     */
    public ApiResponse<SettingsFulfillmentProfile> updateSettingsFulfillmentProfile(
            String fulfillmentProfileId, Object payload, RequestOptions options) {
        requireValue(fulfillmentProfileId, "fulfillment_profile_id");
        requirePayload(payload);
        return client.sendPatchRequest(FulfillmentProfilesUrls.SETTINGS_FULFILLMENT_PROFILE,
                reopen(options).pathParam("fulfillment_profile_id", fulfillmentProfileId).jsonBody(payload).build(), SettingsFulfillmentProfile.class);
    }

    public ApiResponse<SettingsFulfillmentProfile> updateSettingsFulfillmentProfile(
            String fulfillmentProfileId, Object payload) {
        return updateSettingsFulfillmentProfile(fulfillmentProfileId, payload, null);
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
