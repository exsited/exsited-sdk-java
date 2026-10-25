package com.exsited.resources.communication_profiles;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.communication_profiles.communication_profiles_dto.CommunicationProfilesDto.SettingsCommunicationProfile;
import com.exsited.resources.communication_profiles.communication_profiles_dto.CommunicationProfilesDto.SettingsCommunicationProfileList;
import java.util.Map;

/**
 * Communication profile endpoints, under
 * {@code /api/{api_version}/settings/communication_profiles}.
 */
public final class CommunicationProfiles implements BoundResource {

    private final ExsitedClient client;

    public CommunicationProfiles(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the communication profiles: {@code GET /settings/communication_profiles}. */
    public ApiResponse<SettingsCommunicationProfileList> getSettingsCommunicationProfiles(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(CommunicationProfilesUrls.SETTINGS_COMMUNICATION_PROFILES,
                reopen(options).queryParams(queryParams).build(), SettingsCommunicationProfileList.class);
    }

    public ApiResponse<SettingsCommunicationProfileList> getSettingsCommunicationProfiles(
            Map<String, Object> queryParams) {
        return getSettingsCommunicationProfiles(queryParams, null);
    }

    public ApiResponse<SettingsCommunicationProfileList> getSettingsCommunicationProfiles() {
        return getSettingsCommunicationProfiles(null, null);
    }

    /**
     * Create a communication profile; {@code name} is required, and its messages are copied from
     * {@code copy_from} or else from the default profile:
     * {@code POST /settings/communication_profiles}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createSettingsCommunicationProfile(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(CommunicationProfilesUrls.SETTINGS_COMMUNICATION_PROFILES,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createSettingsCommunicationProfile(Object payload) {
        return createSettingsCommunicationProfile(payload, null);
    }

    /**
     * Read one communication profile by its {@code cmp_} id:
     * {@code GET /settings/communication_profiles/{communication_profile_id}}.
     */
    public ApiResponse<SettingsCommunicationProfile> getSettingsCommunicationProfile(
            String communicationProfileId, RequestOptions options) {
        requireValue(communicationProfileId, "communication_profile_id");
        return client.sendGetRequest(CommunicationProfilesUrls.SETTINGS_COMMUNICATION_PROFILE,
                reopen(options).pathParam("communication_profile_id", communicationProfileId).build(), SettingsCommunicationProfile.class);
    }

    public ApiResponse<SettingsCommunicationProfile> getSettingsCommunicationProfile(
            String communicationProfileId) {
        return getSettingsCommunicationProfile(communicationProfileId, null);
    }

    /**
     * Delete a communication profile that is not the default:
     * {@code DELETE /settings/communication_profiles/{communication_profile_id}}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsCommunicationProfile(
            String communicationProfileId, RequestOptions options) {
        requireValue(communicationProfileId, "communication_profile_id");
        return client.sendDeleteRequest(CommunicationProfilesUrls.SETTINGS_COMMUNICATION_PROFILE,
                reopen(options).pathParam("communication_profile_id", communicationProfileId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsCommunicationProfile(String communicationProfileId) {
        return deleteSettingsCommunicationProfile(communicationProfileId, null);
    }

    /**
     * Update a communication profile's fields and channel settings:
     * {@code PATCH /settings/communication_profiles/{communication_profile_id}}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsCommunicationProfile(
            String communicationProfileId, Object payload, RequestOptions options) {
        requireValue(communicationProfileId, "communication_profile_id");
        requirePayload(payload);
        return client.sendPatchRequest(CommunicationProfilesUrls.SETTINGS_COMMUNICATION_PROFILE,
                reopen(options).pathParam("communication_profile_id", communicationProfileId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsCommunicationProfile(
            String communicationProfileId, Object payload) {
        return updateSettingsCommunicationProfile(communicationProfileId, payload, null);
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
