package com.exsited.resources.tasks.tasks_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the tasks bodies. */
public final class TasksDto {

    /** The task body. */
    public static final class Task extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/tasks");

        /** never populated in 2 measured records. */
        public Object actual_hours;
        public String code;
        public String completed_at;
        public TaskCompletedBy completed_by;
        public TaskCreatedBy created_by;
        public String created_on;
        public List<Object> custom_attributes;
        /** 1 measured, null on 1. */
        public String description;
        public String display_name;
        public String due_date;
        public Long estimated_hours;
        public String href;
        public String id;
        public TaskLastUpdatedBy last_updated_by;
        public String last_updated_on;
        /** never populated in 2 measured records. */
        public Object milestone;
        public String name;
        public String object;
        /** never populated in 2 measured records. */
        public Object parent_task;
        public String priority;
        /** never populated in 2 measured records. */
        public Object resource;
        public Long sort_order;
        /** never populated in 2 measured records. */
        public Object start_after;
        public String start_date;
        /** never populated in 2 measured records. */
        public Object state;
        public String status;
        public String task_status;
        public Long version;
        /** never populated in 2 measured records. */
        public Object workflow;
    }

    /** A page of Task records. */
    public static final class TaskList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/tasks");

        public List<Task> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** What {@code get_task} answers, under {@code work_task}. */
    public static final class TaskView extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/tasks/{task_id}",
                "GET /api/{api_version}/tasks/{task_id}/checklist",
                "GET /api/{api_version}/tasks/{task_id}/checklist/{task_checklist_uuid}",
                "PATCH /api/{api_version}/tasks/{task_id}",
                "POST /api/{api_version}/tasks/{task_id}/checklist");

        @Override
        protected String envelopeKey() {
            return "work_task";
        }

        public List<TaskViewActivityLog> activity_logs;
        /** Null in every record measured, so untyped. */
        public Object actual_hours;
        public List<Object> alerts;
        public TaskViewAssignees assignees;
        public Object checklist;
        public String code;
        public String completed_at;
        public TaskViewCompletedBy completed_by;
        public TaskViewCreatedBy created_by;
        public String created_on;
        public List<Object> custom_attributes;
        public String description;
        public String display_name;
        public String due_date;
        public Long estimated_hours;
        public String href;
        public String id;
        public TaskViewLastUpdatedBy last_updated_by;
        public String last_updated_on;
        /** Null in every record measured, so untyped. */
        public Object milestone;
        public String name;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object parent_task;
        public String priority;
        /** Null in every record measured, so untyped. */
        public Object resource;
        public Long sort_order;
        /** Null in every record measured, so untyped. */
        public Object start_after;
        public String start_date;
        /** Null in every record measured, so untyped. */
        public Object state;
        public String status;
        public String task_status;
        public Long version;
        /** Null in every record measured, so untyped. */
        public Object workflow;
    }

    /** One page of what {@code get_task_notes} lists, under {@code data}. */
    public static final class TaskNoteList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/tasks/{task_code}/notes");

        public List<Object> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** A {@link TaskView}'s {@code completed_by}. */
    public static final class TaskViewCompletedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link TaskView}'s {@code assignees}. */
    public static final class TaskViewAssignees extends Dto {
        public List<Object> manually_added_account_contacts;
        public List<Object> selected_account_contacts;
        public List<TaskViewAssigneesSelectedEmployee> selected_employees;
        public List<TaskViewAssigneesSelectedLabour> selected_labours;
        public List<Object> selected_operators;
    }

    /** A {@link TaskView}'s {@code created_by}. */
    public static final class TaskViewCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link TaskView}'s {@code last_updated_by}. */
    public static final class TaskViewLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link TaskView}'s {@code activity_logs}. */
    public static final class TaskViewActivityLog extends Dto {
        public String activity;
    }

    /** A {@link Task}'s {@code completed_by}. */
    public static final class TaskCompletedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link Task}'s {@code created_by}. */
    public static final class TaskCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link Task}'s {@code last_updated_by}. */
    public static final class TaskLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** One entry of a {@link TaskViewAssignees}'s {@code selected_labours}. */
    public static final class TaskViewAssigneesSelectedLabour extends Dto {
        public Long display_order;
        public String email;
        public String labour_code;
        public String name;
        /** Null in every record measured, so untyped. */
        public Object responsibility;
        public String uuid;
    }

    /** One entry of a {@link TaskViewAssignees}'s {@code selected_employees}. */
    public static final class TaskViewAssigneesSelectedEmployee extends Dto {
        public Long display_order;
        public String email;
        public String employee_code;
        public String name;
        /** Null in every record measured, so untyped. */
        public Object responsibility;
        public String uuid;
    }

    private TasksDto() {
    }
}
