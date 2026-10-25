package com.exsited.resources.milestones;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.milestones.milestones_dto.MilestonesDto;
import java.util.Map;

/**
 * Forwards every call to {@link Milestones}.
 *
 * @deprecated {@link Milestones} is bound to a client itself and is what
 *     {@code client.milestones()} answers; use it instead.
 */
@Deprecated
public final class MilestonesResource implements BoundResource {

    private final Milestones milestones;

    public MilestonesResource(ExsitedClient client) {
        this.milestones = new Milestones(client);
    }

    @Override
    public ExsitedClient getClient() {
        return milestones.getClient();
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestones(
            Map<String, Object> queryParams, RequestOptions options) {
        return milestones.getMilestones(queryParams, options);
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestones(Map<String, Object> queryParams) {
        return milestones.getMilestones(queryParams);
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestonesAccountById(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return milestones.getMilestonesAccountById(accountId, queryParams, options);
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestonesAccountById(
            String accountId, Map<String, Object> queryParams) {
        return milestones.getMilestonesAccountById(accountId, queryParams);
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestonesLabourById(
            String labourId, Map<String, Object> queryParams, RequestOptions options) {
        return milestones.getMilestonesLabourById(labourId, queryParams, options);
    }

    public ApiResponse<MilestonesDto.Milestones> getMilestonesLabourById(
            String labourId, Map<String, Object> queryParams) {
        return milestones.getMilestonesLabourById(labourId, queryParams);
    }
}
