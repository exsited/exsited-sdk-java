package com.exsited.resources.usages;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.usages.usages_dto.UsagesDto.Usage;
import com.exsited.resources.usages.usages_dto.UsagesDto.UsageList;
import com.exsited.resources.usages.usages_dto.UsagesDto.UsageUpdated;
import com.exsited.resources.usages.usages_dto.UsagesDto;
import java.util.Map;

/**
 * Forwards every call to {@link Usages}.
 *
 * @deprecated {@link Usages} is bound to a client itself and is what
 *     {@code client.usages()} answers; use it instead.
 */
@Deprecated
public final class UsagesResource implements BoundResource {

    private final Usages usages;

    public UsagesResource(ExsitedClient client) {
        this.usages = new Usages(client);
    }

    @Override
    public ExsitedClient getClient() {
        return usages.getClient();
    }

    public ApiResponse<UsageList> getUsages(
            Map<String, Object> queryParams, RequestOptions options) {
        return usages.getUsages(queryParams, options);
    }

    public ApiResponse<UsageList> getUsages(Map<String, Object> queryParams) {
        return usages.getUsages(queryParams);
    }

    public ApiResponse<UsagesDto.Usages> createUsage(Object payload, RequestOptions options) {
        return usages.createUsage(payload, options);
    }

    public ApiResponse<UsagesDto.Usages> createUsage(Object payload) {
        return usages.createUsage(payload);
    }

    public ApiResponse<Usage> getUsage(
            String usageId, Map<String, Object> queryParams, RequestOptions options) {
        return usages.getUsage(usageId, queryParams, options);
    }

    public ApiResponse<Usage> getUsage(String usageId, Map<String, Object> queryParams) {
        return usages.getUsage(usageId, queryParams);
    }

    public ApiResponse<Dto> deleteUsage(String usageId, RequestOptions options) {
        return usages.deleteUsage(usageId, options);
    }

    public ApiResponse<Dto> deleteUsage(String usageId) {
        return usages.deleteUsage(usageId);
    }

    public ApiResponse<UsageUpdated> updateUsage(
            String usageId, Object payload, RequestOptions options) {
        return usages.updateUsage(usageId, payload, options);
    }

    public ApiResponse<UsageUpdated> updateUsage(String usageId, Object payload) {
        return usages.updateUsage(usageId, payload);
    }

    public ApiResponse<UsageUpdated> replaceUsage(
            String usageId, Object payload, RequestOptions options) {
        return usages.replaceUsage(usageId, payload, options);
    }

    public ApiResponse<UsageUpdated> replaceUsage(String usageId, Object payload) {
        return usages.replaceUsage(usageId, payload);
    }
}
