package com.exsited.resources.settings_workflows;

/** Path templates and body keys for the workflow endpoints. */
public final class SettingsWorkflowsUrls {

    private SettingsWorkflowsUrls() {}

    public static final String SETTINGS_WORKFLOWS = "/api/{api_version}/settings/workflows";
    public static final String SETTINGS_WORKFLOW = "/api/{api_version}/settings/workflows/{workflow_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_WORKFLOWS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String WORKFLOW_KEY = "workflow";
}
