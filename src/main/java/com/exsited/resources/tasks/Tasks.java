package com.exsited.resources.tasks;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.tasks.tasks_dto.TasksDto.TaskList;
import com.exsited.resources.tasks.tasks_dto.TasksDto.TaskNoteList;
import com.exsited.resources.tasks.tasks_dto.TasksDto.TaskView;
import java.util.Map;

/** Task endpoints, under {@code /api/{api_version}}. */
public final class Tasks implements BoundResource {

    private final ExsitedClient client;

    public Tasks(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List all tasks: {@code GET /tasks}. */
    public ApiResponse<TaskList> getTasks(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(TasksUrls.TASKS,
                reopen(options).queryParams(queryParams).build(), TaskList.class);
    }

    public ApiResponse<TaskList> getTasks(
            Map<String, Object> queryParams) {
        return getTasks(queryParams, null);
    }

    public ApiResponse<TaskList> getTasks() {
        return getTasks(null, null);
    }

    /** Delete task: {@code DELETE /tasks/{task_id}}. No typed view; read {@code getMap()}. */
    public ApiResponse<Dto> deleteTask(
            String taskId, RequestOptions options) {
        requireValue(taskId, "task_id");
        return client.sendDeleteRequest(TasksUrls.TASK,
                reopen(options).pathParam("task_id", taskId).build(), null);
    }

    public ApiResponse<Dto> deleteTask(
            String taskId) {
        return deleteTask(taskId, null);
    }

    /**
     * Get single task: {@code GET /tasks/{task_id}}.
     */
    public ApiResponse<TaskView> getTask(
            String taskId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(taskId, "task_id");
        return client.sendGetRequest(TasksUrls.TASK,
                reopen(options).pathParam("task_id", taskId).queryParams(queryParams).build(), TaskView.class);
    }

    public ApiResponse<TaskView> getTask(
            String taskId, Map<String, Object> queryParams) {
        return getTask(taskId, queryParams, null);
    }

    public ApiResponse<TaskView> getTask(String taskId) {
        return getTask(taskId, null, null);
    }

    /**
     * Update task: {@code PATCH /tasks/{task_id}}.
     */
    public ApiResponse<TaskView> updateTask(
            String taskId, Object payload, RequestOptions options) {
        requireValue(taskId, "task_id");
        requirePayload(payload);
        return client.sendPatchRequest(TasksUrls.TASK,
                reopen(options).pathParam("task_id", taskId).jsonBody(payload).build(), TaskView.class);
    }

    public ApiResponse<TaskView> updateTask(
            String taskId, Object payload) {
        return updateTask(taskId, payload, null);
    }

    /**
     * List checklist items: {@code GET /tasks/{task_id}/checklist}.
     */
    public ApiResponse<TaskView> getTaskChecklist(
            String taskId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(taskId, "task_id");
        return client.sendGetRequest(TasksUrls.TASK_CHECKLIST,
                reopen(options).pathParam("task_id", taskId).queryParams(queryParams).build(), TaskView.class);
    }

    public ApiResponse<TaskView> getTaskChecklist(
            String taskId, Map<String, Object> queryParams) {
        return getTaskChecklist(taskId, queryParams, null);
    }

    public ApiResponse<TaskView> getTaskChecklist(String taskId) {
        return getTaskChecklist(taskId, null, null);
    }

    /**
     * Add checklist item: {@code POST /tasks/{task_id}/checklist}.
     */
    public ApiResponse<TaskView> createTaskChecklist(
            String taskId, Object payload, RequestOptions options) {
        requireValue(taskId, "task_id");
        requirePayload(payload);
        return client.sendPostRequest(TasksUrls.TASK_CHECKLIST,
                reopen(options).pathParam("task_id", taskId).jsonBody(payload).build(), TaskView.class);
    }

    public ApiResponse<TaskView> createTaskChecklist(
            String taskId, Object payload) {
        return createTaskChecklist(taskId, payload, null);
    }

    /**
     * List task notes: {@code GET /tasks/{task_code}/notes}.
     */
    public ApiResponse<TaskNoteList> getTaskNotes(
            String taskId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(taskId, "task_code");
        return client.sendGetRequest(TasksUrls.TASK_NOTES,
                reopen(options).pathParam("task_code", taskId).queryParams(queryParams).build(), TaskNoteList.class);
    }

    public ApiResponse<TaskNoteList> getTaskNotes(
            String taskId, Map<String, Object> queryParams) {
        return getTaskNotes(taskId, queryParams, null);
    }

    public ApiResponse<TaskNoteList> getTaskNotes(String taskId) {
        return getTaskNotes(taskId, null, null);
    }

    /**
     * Add note: {@code POST /tasks/{task_code}/notes}, the text as the {@code note} form field; files
     * on the options make it multipart. No typed view; read {@code getMap()}. Any {@code files} are
     * sent beside the text as a multipart body.
     */
    public ApiResponse<Dto> createTaskNote(
            String taskId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(taskId, "task_code");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("task_code", taskId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(TasksUrls.TASK_NOTES,
                request.build(), null);
    }

    public ApiResponse<Dto> createTaskNote(
            String taskId, String note, RequestOptions options) {
        return createTaskNote(taskId, note, null, options);
    }

    public ApiResponse<Dto> createTaskNote(
            String taskId, String note) {
        return createTaskNote(taskId, note, null);
    }

    /**
     * Get single checklist item:
     * {@code GET /tasks/{task_id}/checklist/{task_checklist_uuid}}.
     */
    public ApiResponse<TaskView> getTaskChecklistByUuid(
            String taskId, String taskChecklistUuid, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(taskId, "task_id");
        requireValue(taskChecklistUuid, "task_checklist_uuid");
        return client.sendGetRequest(TasksUrls.TASK_CHECKLIST_BY_UUID,
                reopen(options).pathParam("task_id", taskId).pathParam("task_checklist_uuid", taskChecklistUuid).queryParams(queryParams).build(), TaskView.class);
    }

    public ApiResponse<TaskView> getTaskChecklistByUuid(
            String taskId, String taskChecklistUuid, Map<String, Object> queryParams) {
        return getTaskChecklistByUuid(taskId, taskChecklistUuid, queryParams, null);
    }

    public ApiResponse<TaskView> getTaskChecklistByUuid(String taskId, String taskChecklistUuid) {
        return getTaskChecklistByUuid(taskId, taskChecklistUuid, null, null);
    }

    /**
     * Delete note [V4 N/A - 405]: {@code DELETE /tasks/{task_code}/notes/{note_id}}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteTaskNote(
            String taskId, String taskNoteId, RequestOptions options) {
        requireValue(taskId, "task_code");
        requireValue(taskNoteId, "note_id");
        return client.sendDeleteRequest(TasksUrls.TASK_NOTE,
                reopen(options).pathParam("task_code", taskId).pathParam("note_id", taskNoteId).build(), null);
    }

    public ApiResponse<Dto> deleteTaskNote(
            String taskId, String taskNoteId) {
        return deleteTaskNote(taskId, taskNoteId, null);
    }

    /**
     * Create task: {@code POST /tasks}, {@code {"work_task": {...}}}, {@code name} required and
     * unknown keys refused; {@code 201} with the task as {@code getTask} reads it.
     */
    public ApiResponse<TaskView> createTask(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(TasksUrls.TASKS,
                reopen(options).jsonBody(payload).build(), TaskView.class);
    }

    public ApiResponse<TaskView> createTask(Object payload) {
        return createTask(payload, null);
    }

    /**
     * Delete one checklist item: {@code DELETE /tasks/{task_id}/checklist/{task_checklist_uuid}};
     * {@code 409} once the item is {@code COMPLETED}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteTaskChecklistByUuid(
            String taskId, String taskChecklistUuid, RequestOptions options) {
        requireValue(taskId, "task_id");
        requireValue(taskChecklistUuid, "task_checklist_uuid");
        return client.sendDeleteRequest(TasksUrls.TASK_CHECKLIST_BY_UUID,
                reopen(options).pathParam("task_id", taskId).pathParam("task_checklist_uuid", taskChecklistUuid).build(), null);
    }

    public ApiResponse<Dto> deleteTaskChecklistByUuid(String taskId, String taskChecklistUuid) {
        return deleteTaskChecklistByUuid(taskId, taskChecklistUuid, null);
    }

    /**
     * Edit one checklist item: {@code PATCH /tasks/{task_id}/checklist/{task_checklist_uuid}},
     * {@code {"checklist": {...}}}; the task's id or code, and the whole task back.
     */
    public ApiResponse<TaskView> updateTaskChecklistByUuid(
            String taskId, String taskChecklistUuid, Object payload, RequestOptions options) {
        requireValue(taskId, "task_id");
        requireValue(taskChecklistUuid, "task_checklist_uuid");
        requirePayload(payload);
        return client.sendPatchRequest(TasksUrls.TASK_CHECKLIST_BY_UUID,
                reopen(options).pathParam("task_id", taskId).pathParam("task_checklist_uuid", taskChecklistUuid).jsonBody(payload).build(), TaskView.class);
    }

    public ApiResponse<TaskView> updateTaskChecklistByUuid(
            String taskId, String taskChecklistUuid, Object payload) {
        return updateTaskChecklistByUuid(taskId, taskChecklistUuid, payload, null);
    }

    /**
     * Delete one attachment: {@code DELETE /tasks/{task_code}/notes/{note_id}/files/{file_id}}, by
     * the task's code and the file's {@code uuid}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteTaskNoteFile(
            String taskCode, String noteId, String fileId, RequestOptions options) {
        requireValue(taskCode, "task_code");
        requireValue(noteId, "note_id");
        requireValue(fileId, "file_id");
        return client.sendDeleteRequest(TasksUrls.TASK_NOTE_FILE,
                reopen(options).pathParam("task_code", taskCode).pathParam("note_id", noteId).pathParam("file_id", fileId).build(), null);
    }

    public ApiResponse<Dto> deleteTaskNoteFile(String taskCode, String noteId, String fileId) {
        return deleteTaskNoteFile(taskCode, noteId, fileId, null);
    }

    /**
     * List a note's attachments: {@code GET /tasks/{task_code}/notes/{note_id}/files}, by the
     * task's code. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getTaskNoteFiles(
            String taskCode, String noteId, RequestOptions options) {
        requireValue(taskCode, "task_code");
        requireValue(noteId, "note_id");
        return client.sendGetRequest(TasksUrls.TASK_NOTE_FILES,
                reopen(options).pathParam("task_code", taskCode).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> getTaskNoteFiles(String taskCode, String noteId) {
        return getTaskNoteFiles(taskCode, noteId, null);
    }

    /**
     * Get one attachment: {@code GET /tasks/{task_code}/notes/{note_id}/files/{file_id}}, the
     * {@code uuid} the files listing gives; a JSON descriptor under {@code file}, not the bytes. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getTaskNoteFile(
            String taskCode, String noteId, String fileId, RequestOptions options) {
        requireValue(taskCode, "task_code");
        requireValue(noteId, "note_id");
        requireValue(fileId, "file_id");
        return client.sendGetRequest(TasksUrls.TASK_NOTE_FILE,
                reopen(options).pathParam("task_code", taskCode).pathParam("note_id", noteId).pathParam("file_id", fileId).build(), null);
    }

    public ApiResponse<Dto> getTaskNoteFile(String taskCode, String noteId, String fileId) {
        return getTaskNoteFile(taskCode, noteId, fileId, null);
    }

    /**
     * Get one note: {@code GET /tasks/{task_code}/notes/{note_id}}, by the task's code; a
     * {@code wt_} id answers {@code 404}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getTaskNote(
            String taskCode, String noteId, RequestOptions options) {
        requireValue(taskCode, "task_code");
        requireValue(noteId, "note_id");
        return client.sendGetRequest(TasksUrls.TASK_NOTE,
                reopen(options).pathParam("task_code", taskCode).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> getTaskNote(String taskCode, String noteId) {
        return getTaskNote(taskCode, noteId, null);
    }

    /**
     * Update a note's text: {@code PATCH /tasks/{task_code}/notes/{note_id}}; a form, as the create
     * is, by the task's code. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> updateTaskNote(
            String taskCode, String noteId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(taskCode, "task_code");
        requireValue(noteId, "note_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("task_code", taskCode).pathParam("note_id", noteId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPatchRequest(TasksUrls.TASK_NOTE,
                request.build(), null);
    }

    public ApiResponse<Dto> updateTaskNote(
            String taskCode, String noteId, String note, RequestOptions options) {
        return updateTaskNote(taskCode, noteId, note, null, options);
    }

    public ApiResponse<Dto> updateTaskNote(String taskCode, String noteId, String note) {
        return updateTaskNote(taskCode, noteId, note, null, null);
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

    private static void requireNote(String note) {
        if (note == null) {
            throw new SdkUsageError(
                    "note is required: the note text is the body of this request, and "
                            + "omitting it would store the string \"null\" as the note");
        }
    }
}
