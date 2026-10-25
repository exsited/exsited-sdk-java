package com.exsited.resources.settings_workflows;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.settings_workflows.settings_workflows_dto.SettingsWorkflowsDto.SettingsWorkflow;
import com.exsited.resources.settings_workflows.settings_workflows_dto.SettingsWorkflowsDto.SettingsWorkflowList;
import java.util.Map;

/** Settings workflow endpoints, under {@code /api/{api_version}/settings/workflows}. */
public final class SettingsWorkflows implements BoundResource {

    private final ExsitedClient client;

    public SettingsWorkflows(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List workflows: {@code GET /settings/workflows}; read only here, the writes live under
     * {@code /workflows}.
     */
    public ApiResponse<SettingsWorkflowList> getSettingsWorkflows(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SettingsWorkflowsUrls.SETTINGS_WORKFLOWS,
                reopen(options).queryParams(queryParams).build(), SettingsWorkflowList.class);
    }

    public ApiResponse<SettingsWorkflowList> getSettingsWorkflows(Map<String, Object> queryParams) {
        return getSettingsWorkflows(queryParams, null);
    }

    public ApiResponse<SettingsWorkflowList> getSettingsWorkflows() {
        return getSettingsWorkflows(null, null);
    }

    /** Get one workflow: {@code GET /settings/workflows/{workflow_id}}. */
    public ApiResponse<SettingsWorkflow> getSettingsWorkflow(
            String workflowId, RequestOptions options) {
        requireValue(workflowId, "workflow_id");
        return client.sendGetRequest(SettingsWorkflowsUrls.SETTINGS_WORKFLOW,
                reopen(options).pathParam("workflow_id", workflowId).build(), SettingsWorkflow.class);
    }

    public ApiResponse<SettingsWorkflow> getSettingsWorkflow(String workflowId) {
        return getSettingsWorkflow(workflowId, null);
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
