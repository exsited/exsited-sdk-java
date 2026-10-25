package com.exsited.resources.milestones;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.milestones.milestones_dto.MilestonesDto;
import java.util.Map;

/** Milestone endpoints, under {@code /api/{api_version}}. */
public final class Milestones implements BoundResource {

    private final ExsitedClient client;

    public Milestones(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List all milestones: {@code GET /milestones}. */
    public ApiResponse<MilestonesDto.Milestones> getMilestones(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(MilestonesUrls.MILESTONES,
                reopen(options).queryParams(queryParams).build(), MilestonesDto.Milestones.class);
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestones(
            Map<String, Object> queryParams) {
        return getMilestones(queryParams, null);
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestones() {
        return getMilestones(null, null);
    }

    /** Get milestones by account: {@code GET /milestones/account/{account_id}}. */
    public ApiResponse<MilestonesDto.Milestones> getMilestonesAccountById(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(accountId, "account_id");
        return client.sendGetRequest(MilestonesUrls.MILESTONES_ACCOUNT_BY_ID,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(), MilestonesDto.Milestones.class);
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestonesAccountById(
            String accountId, Map<String, Object> queryParams) {
        return getMilestonesAccountById(accountId, queryParams, null);
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestonesAccountById(String accountId) {
        return getMilestonesAccountById(accountId, null, null);
    }

    /**
     * Get milestones by labour: {@code GET /milestones/labour/{labour_id}}.
     */
    public ApiResponse<MilestonesDto.Milestones> getMilestonesLabourById(
            String labourId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(labourId, "labour_id");
        return client.sendGetRequest(MilestonesUrls.MILESTONES_LABOUR_BY_ID,
                reopen(options).pathParam("labour_id", labourId).queryParams(queryParams).build(), MilestonesDto.Milestones.class);
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestonesLabourById(
            String labourId, Map<String, Object> queryParams) {
        return getMilestonesLabourById(labourId, queryParams, null);
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestonesLabourById(String labourId) {
        return getMilestonesLabourById(labourId, null, null);
    }

    /**
     * Get milestones by employee: {@code GET /milestones/employee/{employee_code}}; the employee's
     * {@code code}, since an {@code emp_} id answers {@code 404}.
     */
    public ApiResponse<MilestonesDto.Milestones> getMilestonesEmployeeByCode(
            String employeeCode, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(employeeCode, "employee_code");
        return client.sendGetRequest(MilestonesUrls.MILESTONES_EMPLOYEE_BY_CODE,
                reopen(options).pathParam("employee_code", employeeCode).queryParams(queryParams).build(), MilestonesDto.Milestones.class);
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestonesEmployeeByCode(
            String employeeCode, Map<String, Object> queryParams) {
        return getMilestonesEmployeeByCode(employeeCode, queryParams, null);
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestonesEmployeeByCode(String employeeCode) {
        return getMilestonesEmployeeByCode(employeeCode, null, null);
    }

    /**
     * Get one milestone: {@code GET /milestones/{milestone_uuid}}, a {@code wfm_} id or its raw
     * uuid; a workflow's milestone definition is a different record and answers {@code 404}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getMilestone(
            String milestoneUuid, RequestOptions options) {
        requireValue(milestoneUuid, "milestone_uuid");
        return client.sendGetRequest(MilestonesUrls.MILESTONE,
                reopen(options).pathParam("milestone_uuid", milestoneUuid).build(), null);
    }

    public ApiResponse<Dto> getMilestone(String milestoneUuid) {
        return getMilestone(milestoneUuid, null);
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
}
