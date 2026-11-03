package com.exsited.resources.operator_groups;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.operator_groups.operator_groups_dto.OperatorGroupsDto.SettingsOperatorGroup;
import com.exsited.resources.operator_groups.operator_groups_dto.OperatorGroupsDto.SettingsOperatorGroupList;
import java.util.Map;

/** Operator group endpoints, under {@code /api/{api_version}/settings/operator_groups}. */
public final class OperatorGroups implements BoundResource {

    private final ExsitedClient client;

    public OperatorGroups(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the operator groups: {@code GET /settings/operator_groups}; paged by cursor. */
    public ApiResponse<SettingsOperatorGroupList> getSettingsOperatorGroups(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(OperatorGroupsUrls.SETTINGS_OPERATOR_GROUPS,
                reopen(options).queryParams(queryParams).build(), SettingsOperatorGroupList.class);
    }

    public ApiResponse<SettingsOperatorGroupList> getSettingsOperatorGroups(Map<String, Object> queryParams) {
        return getSettingsOperatorGroups(queryParams, null);
    }

    public ApiResponse<SettingsOperatorGroupList> getSettingsOperatorGroups() {
        return getSettingsOperatorGroups(null, null);
    }

    /**
     * Create an operator group: {@code POST /settings/operator_groups}, answered {@code 201}.
     * {@code role} is required and takes the role's dashed uuid.
     */
    public ApiResponse<SettingsOperatorGroup> createSettingsOperatorGroup(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(OperatorGroupsUrls.SETTINGS_OPERATOR_GROUPS,
                reopen(options).jsonBody(payload).build(), SettingsOperatorGroup.class);
    }

    public ApiResponse<SettingsOperatorGroup> createSettingsOperatorGroup(Object payload) {
        return createSettingsOperatorGroup(payload, null);
    }

    /** Read one operator group: {@code GET /settings/operator_groups/{operator_group_id}}. */
    public ApiResponse<SettingsOperatorGroup> getSettingsOperatorGroup(
            String operatorGroupId, RequestOptions options) {
        requireValue(operatorGroupId, "operator_group_id");
        return client.sendGetRequest(OperatorGroupsUrls.SETTINGS_OPERATOR_GROUP,
                reopen(options).pathParam("operator_group_id", operatorGroupId).build(),
                SettingsOperatorGroup.class);
    }

    public ApiResponse<SettingsOperatorGroup> getSettingsOperatorGroup(String operatorGroupId) {
        return getSettingsOperatorGroup(operatorGroupId, null);
    }

    /**
     * Delete an operator group: {@code DELETE /settings/operator_groups/{operator_group_id}},
     * answered {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsOperatorGroup(
            String operatorGroupId, RequestOptions options) {
        requireValue(operatorGroupId, "operator_group_id");
        return client.sendDeleteRequest(OperatorGroupsUrls.SETTINGS_OPERATOR_GROUP,
                reopen(options).pathParam("operator_group_id", operatorGroupId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsOperatorGroup(String operatorGroupId) {
        return deleteSettingsOperatorGroup(operatorGroupId, null);
    }

    /**
     * Update an operator group: {@code PATCH /settings/operator_groups/{operator_group_id}}; the
     * answer names {@code role} as a reference carrying the dashed uuid.
     */
    public ApiResponse<SettingsOperatorGroup> updateSettingsOperatorGroup(
            String operatorGroupId, Object payload, RequestOptions options) {
        requireValue(operatorGroupId, "operator_group_id");
        requirePayload(payload);
        return client.sendPatchRequest(OperatorGroupsUrls.SETTINGS_OPERATOR_GROUP,
                reopen(options).pathParam("operator_group_id", operatorGroupId).jsonBody(payload).build(),
                SettingsOperatorGroup.class);
    }

    public ApiResponse<SettingsOperatorGroup> updateSettingsOperatorGroup(
            String operatorGroupId, Object payload) {
        return updateSettingsOperatorGroup(operatorGroupId, payload, null);
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
