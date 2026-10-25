package com.exsited.resources.action_scripts;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.action_scripts.action_scripts_dto.ActionScriptsDto.ActionScript;
import com.exsited.resources.action_scripts.action_scripts_dto.ActionScriptsDto.ActionScriptList;
import java.util.Map;

/** Action script endpoints, under {@code /api/{api_version}/action_scripts}. */
public final class ActionScripts implements BoundResource {

    private final ExsitedClient client;

    public ActionScripts(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List action scripts: {@code GET /action_scripts}; deleted scripts are left out. */
    public ApiResponse<ActionScriptList> getActionScripts(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ActionScriptsUrls.ACTION_SCRIPTS,
                reopen(options).queryParams(queryParams).build(), ActionScriptList.class);
    }

    public ApiResponse<ActionScriptList> getActionScripts(Map<String, Object> queryParams) {
        return getActionScripts(queryParams, null);
    }

    public ApiResponse<ActionScriptList> getActionScripts() {
        return getActionScripts(null, null);
    }

    /**
     * One action script: {@code GET /action_scripts/{action_script_id}}; the id is the
     * {@code ascr_} one.
     */
    public ApiResponse<ActionScript> getActionScript(
            String actionScriptId, RequestOptions options) {
        requireValue(actionScriptId, "action_script_id");
        return client.sendGetRequest(ActionScriptsUrls.ACTION_SCRIPT,
                reopen(options).pathParam("action_script_id", actionScriptId).build(), ActionScript.class);
    }

    public ApiResponse<ActionScript> getActionScript(String actionScriptId) {
        return getActionScript(actionScriptId, null);
    }

    /**
     * Store an action script: {@code POST /action_scripts}, under {@code action_script};
     * {@code name} is required and unique, and a {@code script_file} is a {@code .js} file sent as
     * base64 {@code content}.
     */
    public ApiResponse<ActionScript> createActionScript(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ActionScriptsUrls.ACTION_SCRIPTS,
                reopen(options).jsonBody(payload).build(), ActionScript.class);
    }

    public ApiResponse<ActionScript> createActionScript(Object payload) {
        return createActionScript(payload, null);
    }

    /**
     * Update an action script: {@code PATCH /action_scripts/{action_script_id}}, under
     * {@code action_script}; an unknown key is refused.
     */
    public ApiResponse<ActionScript> updateActionScript(
            String actionScriptId, Object payload, RequestOptions options) {
        requireValue(actionScriptId, "action_script_id");
        requirePayload(payload);
        return client.sendPatchRequest(ActionScriptsUrls.ACTION_SCRIPT,
                reopen(options).pathParam("action_script_id", actionScriptId).jsonBody(payload).build(), ActionScript.class);
    }

    public ApiResponse<ActionScript> updateActionScript(String actionScriptId, Object payload) {
        return updateActionScript(actionScriptId, payload, null);
    }

    /**
     * Delete an action script: {@code DELETE /action_scripts/{action_script_id}}; answered
     * {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteActionScript(
            String actionScriptId, RequestOptions options) {
        requireValue(actionScriptId, "action_script_id");
        return client.sendDeleteRequest(ActionScriptsUrls.ACTION_SCRIPT,
                reopen(options).pathParam("action_script_id", actionScriptId).build(), null);
    }

    public ApiResponse<Dto> deleteActionScript(String actionScriptId) {
        return deleteActionScript(actionScriptId, null);
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
