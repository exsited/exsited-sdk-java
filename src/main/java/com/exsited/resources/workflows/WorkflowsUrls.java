package com.exsited.resources.workflows;

/** Path templates for the workflow endpoints. */
public final class WorkflowsUrls {

    private WorkflowsUrls() {}

    public static final String MILESTONE = "/api/{api_version}/milestones/{milestone_uuid}";
    public static final String WORKFLOW_COMPONENT_BY_ID = "/api/{api_version}/workflow/component/{cc_id}";
    public static final String WORKFLOW_MILESTONES = "/api/{api_version}/workflows/{workflow_uuid}/milestones";
    public static final String WORKFLOW_COMPONENT_CC_MILESTONE = "/api/{api_version}/workflow/component/{cc_id}/milestone";
    public static final String WORKFLOW_COMPONENT_HISTORY = "/api/{api_version}/workflow/component/{component_object_id}/history";
    public static final String WORKFLOW_COMPONENT_CC_INSTANCE_CC_MILESTONE = "/api/{api_version}/workflow/component/{component_uuid}/{component_object_id}/milestone";
    public static final String WORKFLOWS = "/api/{api_version}/workflows";
    public static final String WORKFLOW = "/api/{api_version}/workflows/{workflow_id}";
    public static final String WORKFLOW_MILESTONE = "/api/{api_version}/workflows/{workflow_id}/milestones/{milestone_id}";
    public static final String WORKFLOW_STAGES = "/api/{api_version}/workflows/{workflow_id}/stages";
    public static final String WORKFLOW_STAGE = "/api/{api_version}/workflows/{workflow_id}/stages/{stage_id}";
    /**
     * One component instance's workflow on v4. {@code {component_uuid}} is the component's dashed
     * uuid, the hex of its {@code cmp_} id with the dashes put back; the {@code cmp_} id itself is
     * a 404. {@code {object_code}} is the instance's {@code code}, not its {@code cco_} id.
     */
    public static final String WORKFLOW_CUSTOM_COMPONENT_HISTORY = "/api/{api_version}/workflows/custom_components/{component_uuid}/{object_code}/history";
    public static final String WORKFLOW_CUSTOM_COMPONENT_MILESTONES = "/api/{api_version}/workflows/custom_components/{component_uuid}/{object_code}/milestones";
    public static final String WORKFLOW_CUSTOM_COMPONENT_MILESTONE = "/api/{api_version}/workflows/custom_components/{component_uuid}/{object_code}/milestones/{milestone_id}";
    public static final String WORKFLOWS_KEY = "data";
    public static final String WORKFLOW_MILESTONES_KEY = "data";
    public static final String WORKFLOW_STAGES_KEY = "data";
    public static final String WORKFLOW_KEY = "workflow";
    public static final String WORKFLOW_MILESTONE_KEY = "workflow_milestone";
    public static final String WORKFLOW_STAGE_KEY = "workflow_stage";
    public static final String WORKFLOW_MILESTONE_INSTANCE_KEY = "workflow_milestone_instance";
}
