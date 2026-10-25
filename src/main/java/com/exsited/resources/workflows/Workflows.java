package com.exsited.resources.workflows;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.workflows.workflows_dto.WorkflowsDto.WorkflowList;
import com.exsited.resources.workflows.workflows_dto.WorkflowsDto.WorkflowMilestoneList;
import com.exsited.resources.workflows.workflows_dto.WorkflowsDto.WorkflowMilestoneView;
import com.exsited.resources.workflows.workflows_dto.WorkflowsDto.WorkflowView;
import java.util.Map;

/** Workflow endpoints, under {@code /api/{api_version}}. */
public final class Workflows implements BoundResource {

    private final ExsitedClient client;

    public Workflows(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** Delete milestone (V4): {@code DELETE /milestones/{milestone_uuid}}. */
    public ApiResponse<Dto> deleteMilestone(
            String milestoneUuid, RequestOptions options) {
        requireValue(milestoneUuid, "milestone_uuid");
        return client.sendDeleteRequest(WorkflowsUrls.MILESTONE,
                reopen(options).pathParam("milestone_uuid", milestoneUuid).build(), null);
    }

    public ApiResponse<Dto> deleteMilestone(
            String milestoneUuid) {
        return deleteMilestone(milestoneUuid, null);
    }

    /**
     * Update component workflow: {@code PATCH /workflow/component/{cc_id}}. The API
     * answers 405 on this path, and no verified body is known.
     */
    public ApiResponse<Dto> updateWorkflowComponentById(
            String ccId, Object payload, RequestOptions options) {
        requireValue(ccId, "cc_id");
        requirePayload(payload);
        return client.sendPatchRequest(WorkflowsUrls.WORKFLOW_COMPONENT_BY_ID,
                reopen(options).pathParam("cc_id", ccId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateWorkflowComponentById(
            String ccId, Object payload) {
        return updateWorkflowComponentById(ccId, payload, null);
    }

    /** Get workflow milestones (V4): {@code GET /workflows/{workflow_uuid}/milestones}. */
    public ApiResponse<WorkflowMilestoneList> getWorkflowMilestones(
            String workflowUuid, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(workflowUuid, "workflow_uuid");
        return client.sendGetRequest(WorkflowsUrls.WORKFLOW_MILESTONES,
                reopen(options).pathParam("workflow_uuid", workflowUuid).queryParams(queryParams).build(), WorkflowMilestoneList.class);
    }

    public ApiResponse<WorkflowMilestoneList> getWorkflowMilestones(
            String workflowUuid, Map<String, Object> queryParams) {
        return getWorkflowMilestones(workflowUuid, queryParams, null);
    }

    public ApiResponse<WorkflowMilestoneList> getWorkflowMilestones(String workflowUuid) {
        return getWorkflowMilestones(workflowUuid, null, null);
    }

    /**
     * Schedule milestones: {@code POST /workflow/component/{cc_id}/milestone}. The API
     * answers 405 on this path, and no verified body is known.
     */
    public ApiResponse<Dto> createWorkflowComponentCcMilestone(
            String ccId, Object payload, RequestOptions options) {
        requireValue(ccId, "cc_id");
        requirePayload(payload);
        return client.sendPostRequest(WorkflowsUrls.WORKFLOW_COMPONENT_CC_MILESTONE,
                reopen(options).pathParam("cc_id", ccId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createWorkflowComponentCcMilestone(
            String ccId, Object payload) {
        return createWorkflowComponentCcMilestone(ccId, payload, null);
    }

    /**
     * Workflow history of one component instance, by its code:
     * {@code GET /workflow/component/{component_object_id}/history}; answered under
     * {@code histories}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getWorkflowComponentHistory(
            String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(componentObjectId, "component_object_id");
        return client.sendGetRequest(WorkflowsUrls.WORKFLOW_COMPONENT_HISTORY,
                reopen(options).pathParam("component_object_id", componentObjectId)
                        .queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getWorkflowComponentHistory(
            String componentObjectId, Map<String, Object> queryParams) {
        return getWorkflowComponentHistory(componentObjectId, queryParams, null);
    }

    public ApiResponse<Dto> getWorkflowComponentHistory(String componentObjectId) {
        return getWorkflowComponentHistory(componentObjectId, null, null);
    }

    /**
     * Milestones of one component instance, by the component's uuid and the instance's code:
     * {@code GET /workflow/component/{component_uuid}/{component_object_id}/milestone}; answered
     * under {@code milestones}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getWorkflowComponentCcInstanceCcMilestone(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams,
            RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        return client.sendGetRequest(WorkflowsUrls.WORKFLOW_COMPONENT_CC_INSTANCE_CC_MILESTONE,
                reopen(options).pathParam("component_uuid", componentUuid)
                        .pathParam("component_object_id", componentObjectId)
                        .queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getWorkflowComponentCcInstanceCcMilestone(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams) {
        return getWorkflowComponentCcInstanceCcMilestone(componentUuid, componentObjectId,
                queryParams, null);
    }

    public ApiResponse<Dto> getWorkflowComponentCcInstanceCcMilestone(
            String componentUuid, String componentObjectId) {
        return getWorkflowComponentCcInstanceCcMilestone(componentUuid, componentObjectId, null,
                null);
    }

    /** List workflows: {@code GET /workflows}, cursor paged; a deleted one is not listed. */
    public ApiResponse<WorkflowList> getWorkflows(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(WorkflowsUrls.WORKFLOWS,
                reopen(options).queryParams(queryParams).build(), WorkflowList.class);
    }

    public ApiResponse<WorkflowList> getWorkflows(Map<String, Object> queryParams) {
        return getWorkflows(queryParams, null);
    }

    public ApiResponse<WorkflowList> getWorkflows() {
        return getWorkflows(null, null);
    }

    /**
     * Retrieve one workflow: {@code GET /workflows/{workflow_id}}, the {@code wf_} id or the
     * {@code code}, under {@code workflow}.
     */
    public ApiResponse<WorkflowView> getWorkflow(
            String workflowId, RequestOptions options) {
        requireValue(workflowId, "workflow_id");
        return client.sendGetRequest(WorkflowsUrls.WORKFLOW,
                reopen(options).pathParam("workflow_id", workflowId).build(), WorkflowView.class);
    }

    public ApiResponse<WorkflowView> getWorkflow(String workflowId) {
        return getWorkflow(workflowId, null);
    }

    /**
     * Create a workflow, as a draft: {@code POST /workflows}, wrapped in {@code workflow};
     * {@code name} is required and any key but the eight the handler reads is refused.
     */
    public ApiResponse<WorkflowView> createWorkflow(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(WorkflowsUrls.WORKFLOWS,
                reopen(options).jsonBody(payload).build(), WorkflowView.class);
    }

    public ApiResponse<WorkflowView> createWorkflow(Object payload) {
        return createWorkflow(payload, null);
    }

    /**
     * Update a workflow or move its status: {@code PATCH /workflows/{workflow_id}}, wrapped in
     * {@code workflow}; {@code DRAFT} goes to {@code ACTIVE}, and {@code ACTIVE} and
     * {@code INACTIVE} to each other.
     */
    public ApiResponse<WorkflowView> updateWorkflow(
            String workflowId, Object payload, RequestOptions options) {
        requireValue(workflowId, "workflow_id");
        requirePayload(payload);
        return client.sendPatchRequest(WorkflowsUrls.WORKFLOW,
                reopen(options).pathParam("workflow_id", workflowId).jsonBody(payload).build(), WorkflowView.class);
    }

    public ApiResponse<WorkflowView> updateWorkflow(String workflowId, Object payload) {
        return updateWorkflow(workflowId, payload, null);
    }

    /**
     * Delete a workflow: {@code DELETE /workflows/{workflow_id}}; answered {@code 204}. The record
     * is kept as {@code DELETED} and reads {@code 404} after; a pre-built workflow is refused. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteWorkflow(
            String workflowId, RequestOptions options) {
        requireValue(workflowId, "workflow_id");
        return client.sendDeleteRequest(WorkflowsUrls.WORKFLOW,
                reopen(options).pathParam("workflow_id", workflowId).build(), null);
    }

    public ApiResponse<Dto> deleteWorkflow(String workflowId) {
        return deleteWorkflow(workflowId, null);
    }

    /**
     * List a workflow's stages: {@code GET /workflows/{workflow_id}/stages}. No stage has been
     * measured, so no typed view. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getWorkflowStages(
            String workflowId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(workflowId, "workflow_id");
        return client.sendGetRequest(WorkflowsUrls.WORKFLOW_STAGES,
                reopen(options).pathParam("workflow_id", workflowId).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getWorkflowStages(String workflowId, Map<String, Object> queryParams) {
        return getWorkflowStages(workflowId, queryParams, null);
    }

    public ApiResponse<Dto> getWorkflowStages(String workflowId) {
        return getWorkflowStages(workflowId, null, null);
    }

    /**
     * Retrieve one stage: {@code GET /workflows/{workflow_id}/stages/{stage_id}}, the {@code wfs_}
     * id, under {@code workflow_stage}. No stage has been measured, so no typed view. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getWorkflowStage(
            String workflowId, String stageId, RequestOptions options) {
        requireValue(workflowId, "workflow_id");
        requireValue(stageId, "stage_id");
        return client.sendGetRequest(WorkflowsUrls.WORKFLOW_STAGE,
                reopen(options).pathParam("workflow_id", workflowId).pathParam("stage_id", stageId).build(), null);
    }

    public ApiResponse<Dto> getWorkflowStage(String workflowId, String stageId) {
        return getWorkflowStage(workflowId, stageId, null);
    }

    /**
     * Retrieve one milestone definition:
     * {@code GET /workflows/{workflow_id}/milestones/{milestone_id}}, the {@code wfm_} id, under
     * {@code workflow_milestone}.
     */
    public ApiResponse<WorkflowMilestoneView> getWorkflowMilestone(
            String workflowId, String milestoneId, RequestOptions options) {
        requireValue(workflowId, "workflow_id");
        requireValue(milestoneId, "milestone_id");
        return client.sendGetRequest(WorkflowsUrls.WORKFLOW_MILESTONE,
                reopen(options).pathParam("workflow_id", workflowId).pathParam("milestone_id", milestoneId).build(), WorkflowMilestoneView.class);
    }

    public ApiResponse<WorkflowMilestoneView> getWorkflowMilestone(String workflowId, String milestoneId) {
        return getWorkflowMilestone(workflowId, milestoneId, null);
    }

    /**
     * Workflow history of one component instance:
     * {@code GET /workflows/custom_components/{component_uuid}/{object_code}/history}, the
     * component's dashed uuid and the instance's {@code code}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> getWorkflowCustomComponentHistory(
            String componentUuid, String objectCode, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(objectCode, "object_code");
        return client.sendGetRequest(WorkflowsUrls.WORKFLOW_CUSTOM_COMPONENT_HISTORY,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("object_code", objectCode).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getWorkflowCustomComponentHistory(
            String componentUuid, String objectCode, Map<String, Object> queryParams) {
        return getWorkflowCustomComponentHistory(componentUuid, objectCode, queryParams, null);
    }

    public ApiResponse<Dto> getWorkflowCustomComponentHistory(String componentUuid, String objectCode) {
        return getWorkflowCustomComponentHistory(componentUuid, objectCode, null, null);
    }

    /**
     * Milestones of one component instance:
     * {@code GET /workflows/custom_components/{component_uuid}/{object_code}/milestones}, the
     * component's dashed uuid and the instance's {@code code}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> getWorkflowCustomComponentMilestones(
            String componentUuid, String objectCode, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(objectCode, "object_code");
        return client.sendGetRequest(WorkflowsUrls.WORKFLOW_CUSTOM_COMPONENT_MILESTONES,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("object_code", objectCode).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getWorkflowCustomComponentMilestones(
            String componentUuid, String objectCode, Map<String, Object> queryParams) {
        return getWorkflowCustomComponentMilestones(componentUuid, objectCode, queryParams, null);
    }

    public ApiResponse<Dto> getWorkflowCustomComponentMilestones(String componentUuid, String objectCode) {
        return getWorkflowCustomComponentMilestones(componentUuid, objectCode, null, null);
    }

    /**
     * One milestone of one component instance:
     * {@code GET /workflows/custom_components/{component_uuid}/{object_code}/milestones/{milestone_id}},
     * the {@code wfmi_} id, under {@code workflow_milestone_instance}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> getWorkflowCustomComponentMilestone(
            String componentUuid, String objectCode, String milestoneId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(objectCode, "object_code");
        requireValue(milestoneId, "milestone_id");
        return client.sendGetRequest(WorkflowsUrls.WORKFLOW_CUSTOM_COMPONENT_MILESTONE,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("object_code", objectCode).pathParam("milestone_id", milestoneId).build(), null);
    }

    public ApiResponse<Dto> getWorkflowCustomComponentMilestone(
            String componentUuid, String objectCode, String milestoneId) {
        return getWorkflowCustomComponentMilestone(componentUuid, objectCode, milestoneId, null);
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
