package com.exsited.resources.approval_profiles;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.approval_profiles.approval_profiles_dto.ApprovalProfilesDto.SettingsApprovalProfile;
import com.exsited.resources.approval_profiles.approval_profiles_dto.ApprovalProfilesDto.SettingsApprovalProfileList;
import java.util.Map;

/** Approval profile endpoints, under {@code /api/{api_version}/settings/approval_profiles}. */
public final class ApprovalProfiles implements BoundResource {

    private final ExsitedClient client;

    public ApprovalProfiles(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the approval profiles: {@code GET /settings/approval_profiles}. */
    public ApiResponse<SettingsApprovalProfileList> getSettingsApprovalProfiles(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ApprovalProfilesUrls.SETTINGS_APPROVAL_PROFILES,
                reopen(options).queryParams(queryParams).build(), SettingsApprovalProfileList.class);
    }

    public ApiResponse<SettingsApprovalProfileList> getSettingsApprovalProfiles(
            Map<String, Object> queryParams) {
        return getSettingsApprovalProfiles(queryParams, null);
    }

    public ApiResponse<SettingsApprovalProfileList> getSettingsApprovalProfiles() {
        return getSettingsApprovalProfiles(null, null);
    }

    /**
     * Create an approval profile; {@code name}, {@code entity}, {@code event},
     * {@code approver_type} and {@code approver} are required:
     * {@code POST /settings/approval_profiles}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createSettingsApprovalProfile(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ApprovalProfilesUrls.SETTINGS_APPROVAL_PROFILES,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createSettingsApprovalProfile(Object payload) {
        return createSettingsApprovalProfile(payload, null);
    }

    /**
     * Read one approval profile by its {@code ap_} id:
     * {@code GET /settings/approval_profiles/{approval_profile_id}}.
     */
    public ApiResponse<SettingsApprovalProfile> getSettingsApprovalProfile(
            String approvalProfileId, RequestOptions options) {
        requireValue(approvalProfileId, "approval_profile_id");
        return client.sendGetRequest(ApprovalProfilesUrls.SETTINGS_APPROVAL_PROFILE,
                reopen(options).pathParam("approval_profile_id", approvalProfileId).build(), SettingsApprovalProfile.class);
    }

    public ApiResponse<SettingsApprovalProfile> getSettingsApprovalProfile(String approvalProfileId) {
        return getSettingsApprovalProfile(approvalProfileId, null);
    }

    /**
     * Delete an approval profile that is not in use:
     * {@code DELETE /settings/approval_profiles/{approval_profile_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsApprovalProfile(
            String approvalProfileId, RequestOptions options) {
        requireValue(approvalProfileId, "approval_profile_id");
        return client.sendDeleteRequest(ApprovalProfilesUrls.SETTINGS_APPROVAL_PROFILE,
                reopen(options).pathParam("approval_profile_id", approvalProfileId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsApprovalProfile(String approvalProfileId) {
        return deleteSettingsApprovalProfile(approvalProfileId, null);
    }

    /**
     * Update an approval profile's fields or its status:
     * {@code PATCH /settings/approval_profiles/{approval_profile_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsApprovalProfile(
            String approvalProfileId, Object payload, RequestOptions options) {
        requireValue(approvalProfileId, "approval_profile_id");
        requirePayload(payload);
        return client.sendPatchRequest(ApprovalProfilesUrls.SETTINGS_APPROVAL_PROFILE,
                reopen(options).pathParam("approval_profile_id", approvalProfileId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsApprovalProfile(String approvalProfileId, Object payload) {
        return updateSettingsApprovalProfile(approvalProfileId, payload, null);
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
