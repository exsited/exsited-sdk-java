package com.exsited.resources.workflows;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.workflows.workflows_dto.WorkflowsDto.WorkflowMilestoneList;
import java.util.Map;

/**
 * Forwards every call to {@link Workflows}.
 *
 * @deprecated {@link Workflows} is bound to a client itself and is what
 *     {@code client.workflows()} answers; use it instead.
 */
@Deprecated
public final class WorkflowsResource implements BoundResource {

    private final Workflows workflows;

    public WorkflowsResource(ExsitedClient client) {
        this.workflows = new Workflows(client);
    }

    @Override
    public ExsitedClient getClient() {
        return workflows.getClient();
    }

    public ApiResponse<Dto> deleteMilestone(String milestoneUuid, RequestOptions options) {
        return workflows.deleteMilestone(milestoneUuid, options);
    }

    public ApiResponse<Dto> deleteMilestone(String milestoneUuid) {
        return workflows.deleteMilestone(milestoneUuid);
    }

    public ApiResponse<Dto> updateWorkflowComponentById(
            String ccId, Object payload, RequestOptions options) {
        return workflows.updateWorkflowComponentById(ccId, payload, options);
    }

    public ApiResponse<Dto> updateWorkflowComponentById(String ccId, Object payload) {
        return workflows.updateWorkflowComponentById(ccId, payload);
    }

    public ApiResponse<WorkflowMilestoneList> getWorkflowMilestones(
            String workflowUuid, Map<String, Object> queryParams, RequestOptions options) {
        return workflows.getWorkflowMilestones(workflowUuid, queryParams, options);
    }

    public ApiResponse<WorkflowMilestoneList> getWorkflowMilestones(
            String workflowUuid, Map<String, Object> queryParams) {
        return workflows.getWorkflowMilestones(workflowUuid, queryParams);
    }

    public ApiResponse<Dto> createWorkflowComponentCcMilestone(
            String ccId, Object payload, RequestOptions options) {
        return workflows.createWorkflowComponentCcMilestone(ccId, payload, options);
    }

    public ApiResponse<Dto> createWorkflowComponentCcMilestone(String ccId, Object payload) {
        return workflows.createWorkflowComponentCcMilestone(ccId, payload);
    }

    public ApiResponse<Dto> getWorkflowComponentHistory(
            String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        return workflows.getWorkflowComponentHistory(componentObjectId, queryParams, options);
    }

    public ApiResponse<Dto> getWorkflowComponentHistory(
            String componentObjectId, Map<String, Object> queryParams) {
        return workflows.getWorkflowComponentHistory(componentObjectId, queryParams);
    }

    public ApiResponse<Dto> getWorkflowComponentCcInstanceCcMilestone(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        return workflows.getWorkflowComponentCcInstanceCcMilestone(componentUuid, componentObjectId, queryParams, options);
    }

    public ApiResponse<Dto> getWorkflowComponentCcInstanceCcMilestone(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams) {
        return workflows.getWorkflowComponentCcInstanceCcMilestone(componentUuid, componentObjectId, queryParams);
    }
}
