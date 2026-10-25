package com.exsited.resources.approvals;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.approvals.approvals_dto.ApprovalsDto.ApprovalsAccountList;
import com.exsited.resources.approvals.approvals_dto.ApprovalsDto.ApprovalsSaleorder;
import java.util.Map;

/** Approval endpoints, under {@code /api/{api_version}/approvals}. */
public final class Approvals implements BoundResource {

    private final ExsitedClient client;

    public Approvals(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List account approvals: {@code GET /approvals/account}. */
    public ApiResponse<ApprovalsAccountList> getApprovalsAccount(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ApprovalsUrls.APPROVALS_ACCOUNT,
                reopen(options).queryParams(queryParams).build(), ApprovalsAccountList.class);
    }

    public ApiResponse<ApprovalsAccountList> getApprovalsAccount(
            Map<String, Object> queryParams) {
        return getApprovalsAccount(queryParams, null);
    }

    public ApiResponse<ApprovalsAccountList> getApprovalsAccount() {
        return getApprovalsAccount(null, null);
    }

    /** List sale order approvals: {@code GET /approvals/saleOrder}. */
    public ApiResponse<ApprovalsSaleorder> getApprovalsSaleorder(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ApprovalsUrls.APPROVALS_SALEORDER,
                reopen(options).queryParams(queryParams).build(), ApprovalsSaleorder.class);
    }

    public ApiResponse<ApprovalsSaleorder> getApprovalsSaleorder(
            Map<String, Object> queryParams) {
        return getApprovalsSaleorder(queryParams, null);
    }

    public ApiResponse<ApprovalsSaleorder> getApprovalsSaleorder() {
        return getApprovalsSaleorder(null, null);
    }

    /**
     * Get single account approval: {@code GET /approvals/account/{account_uuid}}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> getApprovalsAccountByUuid(
            String accountUuid, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(accountUuid, "account_uuid");
        return client.sendGetRequest(ApprovalsUrls.APPROVALS_ACCOUNT_BY_UUID,
                reopen(options).pathParam("account_uuid", accountUuid).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getApprovalsAccountByUuid(
            String accountUuid, Map<String, Object> queryParams) {
        return getApprovalsAccountByUuid(accountUuid, queryParams, null);
    }

    public ApiResponse<Dto> getApprovalsAccountByUuid(String accountUuid) {
        return getApprovalsAccountByUuid(accountUuid, null, null);
    }

    /**
     * Approve a pending approval: {@code POST /approvals/approve},
     * {@code {"approval": {"entity", "resource_id", "note"}}} for any of the 16 entities; it runs
     * the held action. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> approveApproval(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ApprovalsUrls.APPROVALS_APPROVE,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> approveApproval(Object payload) {
        return approveApproval(payload, null);
    }

    /**
     * Reject a pending approval: {@code POST /approvals/reject}, the same
     * {@code {"approval": {"entity", "resource_id", "note"}}} body as {@code approveApproval}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> rejectApproval(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ApprovalsUrls.APPROVALS_REJECT,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> rejectApproval(Object payload) {
        return rejectApproval(payload, null);
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
