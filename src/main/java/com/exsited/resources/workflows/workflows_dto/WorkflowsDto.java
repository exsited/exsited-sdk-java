package com.exsited.resources.workflows.workflows_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the workflows bodies. */
public final class WorkflowsDto {

    /** One page of what {@code get_workflow_milestones} lists, under {@code data}. */
    public static final class WorkflowMilestoneList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/workflows/{workflow_uuid}/milestones");

        public List<WorkflowMilestone> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** One entry of a {@link WorkflowMilestoneList}'s {@code data}. */
    public static class WorkflowMilestone extends Dto {
        public WorkflowMilestoneActivityDefinition activity_definition;
        /** Null in every record measured, so untyped. */
        public Object auto_create;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object description;
        public String display_name;
        /** Null in every record measured, so untyped. */
        public Object estimated_hours;
        public String href;
        /** Null in every record measured, so untyped. */
        public Object icon;
        public String id;
        public String last_updated_on;
        public String name;
        public String object;
        public String priority;
        /** Null in every record measured, so untyped. */
        public Object sort_order;
        public String status;
        public WorkflowMilestoneDetail workflow;
        public WorkflowMilestoneWorkflowVersion workflow_version;
    }

    /** A {@link WorkflowMilestone}'s {@code workflow}. */
    public static final class WorkflowMilestoneDetail extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** A {@link WorkflowMilestone}'s {@code activity_definition}. */
    public static final class WorkflowMilestoneActivityDefinition extends Dto {
        public String id;
        public String name;
        public String object;
    }

    /** A {@link WorkflowMilestone}'s {@code workflow_version}. */
    public static final class WorkflowMilestoneWorkflowVersion extends Dto {
        public String id;
        public String name;
        public String object;
    }

    private WorkflowsDto() {
    }

    /** One page of what {@code getWorkflows} lists, under {@code data}. */
    public static final class WorkflowList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/workflows");

        public List<Workflow> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** One entry of a {@link WorkflowList}'s {@code data}. */
    public static class Workflow extends Dto {
        public String object;
        public String id;
        public String status;
        /** Null in every record measured, so untyped. */
        public Object code;
        public String name;
        public String display_name;
        /** Null in every record measured, so untyped. */
        public Object description;
        public String entity;
        public Long entity_id;
        public String event;
        public Boolean is_pre_built;
        public Boolean enable_stage;
        public WorkflowCurrentVersion current_version;
        /** Null in every record measured, so untyped. */
        public Object custom_form;
        public String created_on;
        public String last_updated_on;
        public WorkflowCreatedBy created_by;
        public WorkflowLastUpdatedBy last_updated_by;
        public String href;
    }

    /**
     * One workflow, under {@code workflow}: what {@code getWorkflow} answers, and the create and
     * the update, which the handler answers through the same read.
     */
    public static final class WorkflowView extends Workflow {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/workflows/{workflow_id}");

        @Override
        protected String envelopeKey() {
            return "workflow";
        }
    }

    /** A {@link Workflow}'s {@code current_version}. */
    public static final class WorkflowCurrentVersion extends Dto {
        public String object;
        public String id;
        public String name;
    }

    /** A {@link Workflow}'s {@code created_by}. */
    public static final class WorkflowCreatedBy extends Dto {
        public String object;
        public String name;
    }

    /** A {@link Workflow}'s {@code last_updated_by}. */
    public static final class WorkflowLastUpdatedBy extends Dto {
        public String object;
        public String name;
    }

    /**
     * One milestone definition, under {@code workflow_milestone}: what {@code getWorkflowMilestone}
     * answers, the same fields as a listed one.
     */
    public static final class WorkflowMilestoneView extends WorkflowMilestone {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/workflows/{workflow_id}/milestones/{milestone_id}");

        @Override
        protected String envelopeKey() {
            return "workflow_milestone";
        }
    }
}
