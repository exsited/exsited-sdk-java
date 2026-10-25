package com.exsited.resources.sales_reps;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.sales_reps.sales_reps_dto.SalesRepsDto.SettingsSalesRep;
import com.exsited.resources.sales_reps.sales_reps_dto.SalesRepsDto.SettingsSalesRepList;
import java.util.Map;

/** Sales rep endpoints, under {@code /api/{api_version}/settings/sales_reps}. */
public final class SalesReps implements BoundResource {

    private final ExsitedClient client;

    public SalesReps(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List sales reps: {@code GET /settings/sales_reps}. */
    public ApiResponse<SettingsSalesRepList> getSettingsSalesReps(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SalesRepsUrls.SETTINGS_SALES_REPS,
                reopen(options).queryParams(queryParams).build(), SettingsSalesRepList.class);
    }

    public ApiResponse<SettingsSalesRepList> getSettingsSalesReps(Map<String, Object> queryParams) {
        return getSettingsSalesReps(queryParams, null);
    }

    public ApiResponse<SettingsSalesRepList> getSettingsSalesReps() {
        return getSettingsSalesReps(null, null);
    }

    /**
     * Create a sales rep: {@code POST /settings/sales_reps}; answers 400 validation_error, naming
     * no field, even for a valid body. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createSettingsSalesRep(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(SalesRepsUrls.SETTINGS_SALES_REPS,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createSettingsSalesRep(Object payload) {
        return createSettingsSalesRep(payload, null);
    }

    /** Get one sales rep: {@code GET /settings/sales_reps/{sales_rep_id}}. */
    public ApiResponse<SettingsSalesRep> getSettingsSalesRep(
            String salesRepId, RequestOptions options) {
        requireValue(salesRepId, "sales_rep_id");
        return client.sendGetRequest(SalesRepsUrls.SETTINGS_SALES_REP,
                reopen(options).pathParam("sales_rep_id", salesRepId).build(), SettingsSalesRep.class);
    }

    public ApiResponse<SettingsSalesRep> getSettingsSalesRep(String salesRepId) {
        return getSettingsSalesRep(salesRepId, null);
    }

    /**
     * Delete a sales rep: {@code DELETE /settings/sales_reps/{sales_rep_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsSalesRep(
            String salesRepId, RequestOptions options) {
        requireValue(salesRepId, "sales_rep_id");
        return client.sendDeleteRequest(SalesRepsUrls.SETTINGS_SALES_REP,
                reopen(options).pathParam("sales_rep_id", salesRepId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsSalesRep(String salesRepId) {
        return deleteSettingsSalesRep(salesRepId, null);
    }

    /**
     * Update or enable/disable a sales rep: {@code PATCH /settings/sales_reps/{sales_rep_id}};
     * {@code status} takes active or inactive. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsSalesRep(
            String salesRepId, Object payload, RequestOptions options) {
        requireValue(salesRepId, "sales_rep_id");
        requirePayload(payload);
        return client.sendPatchRequest(SalesRepsUrls.SETTINGS_SALES_REP,
                reopen(options).pathParam("sales_rep_id", salesRepId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsSalesRep(String salesRepId, Object payload) {
        return updateSettingsSalesRep(salesRepId, payload, null);
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
