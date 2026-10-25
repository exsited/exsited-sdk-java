package com.exsited.resources.tasks;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.tasks.tasks_dto.TasksDto.TaskList;
import com.exsited.resources.tasks.tasks_dto.TasksDto.TaskNoteList;
import com.exsited.resources.tasks.tasks_dto.TasksDto.TaskView;
import java.util.Map;

/**
 * Forwards every call to {@link Tasks}.
 *
 * @deprecated {@link Tasks} is bound to a client itself and is what
 *     {@code client.tasks()} answers; use it instead.
 */
@Deprecated
public final class TasksResource implements BoundResource {

    private final Tasks tasks;

    public TasksResource(ExsitedClient client) {
        this.tasks = new Tasks(client);
    }

    @Override
    public ExsitedClient getClient() {
        return tasks.getClient();
    }

    public ApiResponse<TaskList> getTasks(Map<String, Object> queryParams, RequestOptions options) {
        return tasks.getTasks(queryParams, options);
    }

    public ApiResponse<TaskList> getTasks(Map<String, Object> queryParams) {
        return tasks.getTasks(queryParams);
    }

    public ApiResponse<Dto> deleteTask(String taskId, RequestOptions options) {
        return tasks.deleteTask(taskId, options);
    }

    public ApiResponse<Dto> deleteTask(String taskId) {
        return tasks.deleteTask(taskId);
    }

    public ApiResponse<TaskView> getTask(
            String taskId, Map<String, Object> queryParams, RequestOptions options) {
        return tasks.getTask(taskId, queryParams, options);
    }

    public ApiResponse<TaskView> getTask(String taskId, Map<String, Object> queryParams) {
        return tasks.getTask(taskId, queryParams);
    }

    public ApiResponse<TaskView> updateTask(String taskId, Object payload, RequestOptions options) {
        return tasks.updateTask(taskId, payload, options);
    }

    public ApiResponse<TaskView> updateTask(String taskId, Object payload) {
        return tasks.updateTask(taskId, payload);
    }

    public ApiResponse<TaskView> getTaskChecklist(
            String taskId, Map<String, Object> queryParams, RequestOptions options) {
        return tasks.getTaskChecklist(taskId, queryParams, options);
    }

    public ApiResponse<TaskView> getTaskChecklist(String taskId, Map<String, Object> queryParams) {
        return tasks.getTaskChecklist(taskId, queryParams);
    }

    public ApiResponse<TaskView> createTaskChecklist(
            String taskId, Object payload, RequestOptions options) {
        return tasks.createTaskChecklist(taskId, payload, options);
    }

    public ApiResponse<TaskView> createTaskChecklist(String taskId, Object payload) {
        return tasks.createTaskChecklist(taskId, payload);
    }

    public ApiResponse<TaskNoteList> getTaskNotes(
            String taskId, Map<String, Object> queryParams, RequestOptions options) {
        return tasks.getTaskNotes(taskId, queryParams, options);
    }

    public ApiResponse<TaskNoteList> getTaskNotes(String taskId, Map<String, Object> queryParams) {
        return tasks.getTaskNotes(taskId, queryParams);
    }

    public ApiResponse<Dto> createTaskNote(String taskId, String note, RequestOptions options) {
        return tasks.createTaskNote(taskId, note, options);
    }

    public ApiResponse<Dto> createTaskNote(String taskId, String note) {
        return tasks.createTaskNote(taskId, note);
    }

    public ApiResponse<TaskView> getTaskChecklistByUuid(
            String taskId, String taskChecklistUuid, Map<String, Object> queryParams, RequestOptions options) {
        return tasks.getTaskChecklistByUuid(taskId, taskChecklistUuid, queryParams, options);
    }

    public ApiResponse<TaskView> getTaskChecklistByUuid(
            String taskId, String taskChecklistUuid, Map<String, Object> queryParams) {
        return tasks.getTaskChecklistByUuid(taskId, taskChecklistUuid, queryParams);
    }

    public ApiResponse<Dto> deleteTaskNote(
            String taskId, String taskNoteId, RequestOptions options) {
        return tasks.deleteTaskNote(taskId, taskNoteId, options);
    }

    public ApiResponse<Dto> deleteTaskNote(String taskId, String taskNoteId) {
        return tasks.deleteTaskNote(taskId, taskNoteId);
    }
}
