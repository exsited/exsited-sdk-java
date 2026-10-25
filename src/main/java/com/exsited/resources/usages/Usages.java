package com.exsited.resources.usages;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.usages.usages_dto.UsagesDto.Usage;
import com.exsited.resources.usages.usages_dto.UsagesDto.UsageList;
import com.exsited.resources.usages.usages_dto.UsagesDto.UsageUpdated;
import com.exsited.resources.usages.usages_dto.UsagesDto;
import java.util.Map;

/** Usage endpoints, under {@code /api/{api_version}/usages}. */
public final class Usages implements BoundResource {

    private final ExsitedClient client;

    public Usages(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List of usages: {@code GET /usages}. */
    public ApiResponse<UsageList> getUsages(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(UsagesUrls.USAGES,
                reopen(options).queryParams(queryParams).build(), UsageList.class);
    }

    public ApiResponse<UsageList> getUsages(
            Map<String, Object> queryParams) {
        return getUsages(queryParams, null);
    }

    public ApiResponse<UsageList> getUsages() {
        return getUsages(null, null);
    }

    /** Create bulk usages: {@code POST /usages}. Recorded 201. */
    public ApiResponse<UsagesDto.Usages> createUsage(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(UsagesUrls.USAGES,
                reopen(options).jsonBody(payload).build(), UsagesDto.Usages.class);
    }

    public ApiResponse<UsagesDto.Usages> createUsage(
            Object payload) {
        return createUsage(payload, null);
    }

    /** Get multiple usages: {@code GET /usages/{usage_id}}. Recorded 404, 200. */
    public ApiResponse<Usage> getUsage(
            String usageId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(usageId, "usage_id");
        return client.sendGetRequest(UsagesUrls.USAGE,
                reopen(options).pathParam("usage_id", usageId).queryParams(queryParams).build(), Usage.class);
    }

    public ApiResponse<Usage> getUsage(
            String usageId, Map<String, Object> queryParams) {
        return getUsage(usageId, queryParams, null);
    }

    public ApiResponse<Usage> getUsage(String usageId) {
        return getUsage(usageId, null, null);
    }

    /**
     * Delete usage: {@code DELETE /usages/{usage_id}}. Recorded 404. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteUsage(
            String usageId, RequestOptions options) {
        requireValue(usageId, "usage_id");
        return client.sendDeleteRequest(UsagesUrls.USAGE,
                reopen(options).pathParam("usage_id", usageId).build(), null);
    }

    public ApiResponse<Dto> deleteUsage(
            String usageId) {
        return deleteUsage(usageId, null);
    }

    /** Partial update usage: {@code PATCH /usages/{usage_id}}. */
    public ApiResponse<UsageUpdated> updateUsage(
            String usageId, Object payload, RequestOptions options) {
        requireValue(usageId, "usage_id");
        requirePayload(payload);
        return client.sendPatchRequest(UsagesUrls.USAGE,
                reopen(options).pathParam("usage_id", usageId).jsonBody(payload).build(), UsageUpdated.class);
    }

    public ApiResponse<UsageUpdated> updateUsage(
            String usageId, Object payload) {
        return updateUsage(usageId, payload, null);
    }

    /** Full replace usage: {@code PUT /usages/{usage_id}}. */
    public ApiResponse<UsageUpdated> replaceUsage(
            String usageId, Object payload, RequestOptions options) {
        requireValue(usageId, "usage_id");
        requirePayload(payload);
        return client.sendPutRequest(UsagesUrls.USAGE,
                reopen(options).pathParam("usage_id", usageId).jsonBody(payload).build(), UsageUpdated.class);
    }

    public ApiResponse<UsageUpdated> replaceUsage(
            String usageId, Object payload) {
        return replaceUsage(usageId, payload, null);
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
