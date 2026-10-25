package com.exsited.resources.approval_user_groups;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.approval_user_groups.approval_user_groups_dto.ApprovalUserGroupsDto.SettingsApprovalUserGroup;
import com.exsited.resources.approval_user_groups.approval_user_groups_dto.ApprovalUserGroupsDto.SettingsApprovalUserGroupList;
import java.util.Map;

/**
 * Approval user group endpoints, under {@code /api/{api_version}/settings/approval_user_groups}.
 */
public final class ApprovalUserGroups implements BoundResource {

    private final ExsitedClient client;

    public ApprovalUserGroups(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the approval user groups: {@code GET /settings/approval_user_groups}. */
    public ApiResponse<SettingsApprovalUserGroupList> getSettingsApprovalUserGroups(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ApprovalUserGroupsUrls.SETTINGS_APPROVAL_USER_GROUPS,
                reopen(options).queryParams(queryParams).build(), SettingsApprovalUserGroupList.class);
    }

    public ApiResponse<SettingsApprovalUserGroupList> getSettingsApprovalUserGroups(
            Map<String, Object> queryParams) {
        return getSettingsApprovalUserGroups(queryParams, null);
    }

    public ApiResponse<SettingsApprovalUserGroupList> getSettingsApprovalUserGroups() {
        return getSettingsApprovalUserGroups(null, null);
    }

    /**
     * Create an approval user group; {@code name} is required:
     * {@code POST /settings/approval_user_groups}.
     */
    public ApiResponse<SettingsApprovalUserGroup> createSettingsApprovalUserGroup(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ApprovalUserGroupsUrls.SETTINGS_APPROVAL_USER_GROUPS,
                reopen(options).jsonBody(payload).build(), SettingsApprovalUserGroup.class);
    }

    public ApiResponse<SettingsApprovalUserGroup> createSettingsApprovalUserGroup(Object payload) {
        return createSettingsApprovalUserGroup(payload, null);
    }

    /**
     * Read one approval user group by its {@code aug_} id:
     * {@code GET /settings/approval_user_groups/{approval_user_group_id}}.
     */
    public ApiResponse<SettingsApprovalUserGroup> getSettingsApprovalUserGroup(
            String approvalUserGroupId, RequestOptions options) {
        requireValue(approvalUserGroupId, "approval_user_group_id");
        return client.sendGetRequest(ApprovalUserGroupsUrls.SETTINGS_APPROVAL_USER_GROUP,
                reopen(options).pathParam("approval_user_group_id", approvalUserGroupId).build(), SettingsApprovalUserGroup.class);
    }

    public ApiResponse<SettingsApprovalUserGroup> getSettingsApprovalUserGroup(String approvalUserGroupId) {
        return getSettingsApprovalUserGroup(approvalUserGroupId, null);
    }

    /**
     * Delete an approval user group:
     * {@code DELETE /settings/approval_user_groups/{approval_user_group_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsApprovalUserGroup(
            String approvalUserGroupId, RequestOptions options) {
        requireValue(approvalUserGroupId, "approval_user_group_id");
        return client.sendDeleteRequest(ApprovalUserGroupsUrls.SETTINGS_APPROVAL_USER_GROUP,
                reopen(options).pathParam("approval_user_group_id", approvalUserGroupId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsApprovalUserGroup(String approvalUserGroupId) {
        return deleteSettingsApprovalUserGroup(approvalUserGroupId, null);
    }

    /**
     * Update an approval user group's name, display name or description:
     * {@code PATCH /settings/approval_user_groups/{approval_user_group_id}}.
     */
    public ApiResponse<SettingsApprovalUserGroup> updateSettingsApprovalUserGroup(
            String approvalUserGroupId, Object payload, RequestOptions options) {
        requireValue(approvalUserGroupId, "approval_user_group_id");
        requirePayload(payload);
        return client.sendPatchRequest(ApprovalUserGroupsUrls.SETTINGS_APPROVAL_USER_GROUP,
                reopen(options).pathParam("approval_user_group_id", approvalUserGroupId).jsonBody(payload).build(), SettingsApprovalUserGroup.class);
    }

    public ApiResponse<SettingsApprovalUserGroup> updateSettingsApprovalUserGroup(
            String approvalUserGroupId, Object payload) {
        return updateSettingsApprovalUserGroup(approvalUserGroupId, payload, null);
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
