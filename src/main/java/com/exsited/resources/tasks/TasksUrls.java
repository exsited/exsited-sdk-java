package com.exsited.resources.tasks;

/** Path templates and body keys for the task endpoints. */
public final class TasksUrls {

    private TasksUrls() {}

    public static final String TASKS = "/api/{api_version}/tasks";
    public static final String TASK = "/api/{api_version}/tasks/{task_id}";
    public static final String TASK_CHECKLIST = "/api/{api_version}/tasks/{task_id}/checklist";
    public static final String TASK_NOTES = "/api/{api_version}/tasks/{task_code}/notes";
    public static final String TASK_CHECKLIST_BY_UUID = "/api/{api_version}/tasks/{task_id}/checklist/{task_checklist_uuid}";
    public static final String TASK_NOTE = "/api/{api_version}/tasks/{task_code}/notes/{note_id}";
    public static final String TASK_NOTE_FILES = "/api/{api_version}/tasks/{task_code}/notes/{note_id}/files";
    public static final String TASK_NOTE_FILE = "/api/{api_version}/tasks/{task_code}/notes/{note_id}/files/{file_id}";

    /** The key a listing's records sit under. */
    public static final String TASKS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String WORK_TASK_KEY = "work_task";
    public static final String TASK_NOTE_FILES_KEY = "data";
    public static final String NOTE_KEY = "note";
    public static final String FILE_KEY = "file";
}
