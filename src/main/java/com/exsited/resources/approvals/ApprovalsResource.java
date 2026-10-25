package com.exsited.resources.approvals;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.approvals.approvals_dto.ApprovalsDto.ApprovalsAccountList;
import com.exsited.resources.approvals.approvals_dto.ApprovalsDto.ApprovalsSaleorder;
import java.util.Map;

/**
 * Forwards every call to {@link Approvals}.
 *
 * @deprecated {@link Approvals} is bound to a client itself and is what
 *     {@code client.approvals()} answers; use it instead.
 */
@Deprecated
public final class ApprovalsResource implements BoundResource {

    private final Approvals approvals;

    public ApprovalsResource(ExsitedClient client) {
        this.approvals = new Approvals(client);
    }

    @Override
    public ExsitedClient getClient() {
        return approvals.getClient();
    }

    public ApiResponse<ApprovalsAccountList> getApprovalsAccount(
            Map<String, Object> queryParams, RequestOptions options) {
        return approvals.getApprovalsAccount(queryParams, options);
    }

    public ApiResponse<ApprovalsAccountList> getApprovalsAccount(Map<String, Object> queryParams) {
        return approvals.getApprovalsAccount(queryParams);
    }

    public ApiResponse<ApprovalsSaleorder> getApprovalsSaleorder(
            Map<String, Object> queryParams, RequestOptions options) {
        return approvals.getApprovalsSaleorder(queryParams, options);
    }

    public ApiResponse<ApprovalsSaleorder> getApprovalsSaleorder(Map<String, Object> queryParams) {
        return approvals.getApprovalsSaleorder(queryParams);
    }

    public ApiResponse<Dto> getApprovalsAccountByUuid(
            String accountUuid, Map<String, Object> queryParams, RequestOptions options) {
        return approvals.getApprovalsAccountByUuid(accountUuid, queryParams, options);
    }

    public ApiResponse<Dto> getApprovalsAccountByUuid(
            String accountUuid, Map<String, Object> queryParams) {
        return approvals.getApprovalsAccountByUuid(accountUuid, queryParams);
    }
}
