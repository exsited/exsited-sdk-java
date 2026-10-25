package com.exsited.resources.account_groups;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.account_groups.account_groups_dto.AccountGroupsDto.SettingsAccountGroup;
import com.exsited.resources.account_groups.account_groups_dto.AccountGroupsDto.SettingsAccountGroupList;
import java.util.Map;

/** Account group endpoints, under {@code /api/{api_version}/settings/account_groups}. */
public final class AccountGroups implements BoundResource {

    private final ExsitedClient client;

    public AccountGroups(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the account groups: {@code GET /settings/account_groups}. */
    public ApiResponse<SettingsAccountGroupList> getSettingsAccountGroups(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(AccountGroupsUrls.SETTINGS_ACCOUNT_GROUPS,
                reopen(options).queryParams(queryParams).build(), SettingsAccountGroupList.class);
    }

    public ApiResponse<SettingsAccountGroupList> getSettingsAccountGroups(Map<String, Object> queryParams) {
        return getSettingsAccountGroups(queryParams, null);
    }

    public ApiResponse<SettingsAccountGroupList> getSettingsAccountGroups() {
        return getSettingsAccountGroups(null, null);
    }

    /** Create an account group; {@code name} is required: {@code POST /settings/account_groups}. */
    public ApiResponse<SettingsAccountGroup> createSettingsAccountGroup(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(AccountGroupsUrls.SETTINGS_ACCOUNT_GROUPS,
                reopen(options).jsonBody(payload).build(), SettingsAccountGroup.class);
    }

    public ApiResponse<SettingsAccountGroup> createSettingsAccountGroup(Object payload) {
        return createSettingsAccountGroup(payload, null);
    }

    /**
     * Read one account group by its {@code ag_} id:
     * {@code GET /settings/account_groups/{account_group_id}}.
     */
    public ApiResponse<SettingsAccountGroup> getSettingsAccountGroup(
            String accountGroupId, RequestOptions options) {
        requireValue(accountGroupId, "account_group_id");
        return client.sendGetRequest(AccountGroupsUrls.SETTINGS_ACCOUNT_GROUP,
                reopen(options).pathParam("account_group_id", accountGroupId).build(), SettingsAccountGroup.class);
    }

    public ApiResponse<SettingsAccountGroup> getSettingsAccountGroup(String accountGroupId) {
        return getSettingsAccountGroup(accountGroupId, null);
    }

    /**
     * Delete an account group no account uses:
     * {@code DELETE /settings/account_groups/{account_group_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsAccountGroup(
            String accountGroupId, RequestOptions options) {
        requireValue(accountGroupId, "account_group_id");
        return client.sendDeleteRequest(AccountGroupsUrls.SETTINGS_ACCOUNT_GROUP,
                reopen(options).pathParam("account_group_id", accountGroupId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsAccountGroup(String accountGroupId) {
        return deleteSettingsAccountGroup(accountGroupId, null);
    }

    /**
     * Update an account group's fields or its status:
     * {@code PATCH /settings/account_groups/{account_group_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsAccountGroup(
            String accountGroupId, Object payload, RequestOptions options) {
        requireValue(accountGroupId, "account_group_id");
        requirePayload(payload);
        return client.sendPatchRequest(AccountGroupsUrls.SETTINGS_ACCOUNT_GROUP,
                reopen(options).pathParam("account_group_id", accountGroupId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsAccountGroup(String accountGroupId, Object payload) {
        return updateSettingsAccountGroup(accountGroupId, payload, null);
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
