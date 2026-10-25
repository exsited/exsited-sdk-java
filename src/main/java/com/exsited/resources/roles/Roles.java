package com.exsited.resources.roles;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.roles.roles_dto.RolesDto.SettingsRole;
import com.exsited.resources.roles.roles_dto.RolesDto.SettingsRoleList;
import java.util.Map;

/** Role endpoints, under {@code /api/{api_version}/settings/roles}. */
public final class Roles implements BoundResource {

    private final ExsitedClient client;

    public Roles(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List roles: {@code GET /settings/roles}. */
    public ApiResponse<SettingsRoleList> getSettingsRoles(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(RolesUrls.SETTINGS_ROLES,
                reopen(options).queryParams(queryParams).build(), SettingsRoleList.class);
    }

    public ApiResponse<SettingsRoleList> getSettingsRoles(Map<String, Object> queryParams) {
        return getSettingsRoles(queryParams, null);
    }

    public ApiResponse<SettingsRoleList> getSettingsRoles() {
        return getSettingsRoles(null, null);
    }

    /**
     * Create a role: {@code POST /settings/roles}; the role can be saved and the answer still be a
     * 500, from a failing server-side lookup. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createSettingsRole(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(RolesUrls.SETTINGS_ROLES,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createSettingsRole(Object payload) {
        return createSettingsRole(payload, null);
    }

    /** Get one role: {@code GET /settings/roles/{role_id}}. */
    public ApiResponse<SettingsRole> getSettingsRole(
            String roleId, RequestOptions options) {
        requireValue(roleId, "role_id");
        return client.sendGetRequest(RolesUrls.SETTINGS_ROLE,
                reopen(options).pathParam("role_id", roleId).build(), SettingsRole.class);
    }

    public ApiResponse<SettingsRole> getSettingsRole(String roleId) {
        return getSettingsRole(roleId, null);
    }

    /**
     * Delete a role: {@code DELETE /settings/roles/{role_id}}; a system role is refused. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsRole(
            String roleId, RequestOptions options) {
        requireValue(roleId, "role_id");
        return client.sendDeleteRequest(RolesUrls.SETTINGS_ROLE,
                reopen(options).pathParam("role_id", roleId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsRole(String roleId) {
        return deleteSettingsRole(roleId, null);
    }

    /**
     * Update or enable/disable a role: {@code PATCH /settings/roles/{role_id}}; the change can be
     * saved and the answer still be a 500, from a failing server-side lookup. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsRole(
            String roleId, Object payload, RequestOptions options) {
        requireValue(roleId, "role_id");
        requirePayload(payload);
        return client.sendPatchRequest(RolesUrls.SETTINGS_ROLE,
                reopen(options).pathParam("role_id", roleId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsRole(String roleId, Object payload) {
        return updateSettingsRole(roleId, payload, null);
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
