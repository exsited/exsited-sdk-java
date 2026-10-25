package com.exsited.resources.custom_components;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.DtoFactory;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.Component;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentByUuid;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentList;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObject;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjectActivity;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjectActivityEntry;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjectCustomObjects;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjectNote;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjectNoteFile;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjectNoteFiles;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjectNotes;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjectRecord;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjects;
import java.util.LinkedHashMap;
import java.util.Map;

/** Custom component endpoints, under {@code /api/{api_version}}. */
public final class CustomComponents implements BoundResource {

    private final ExsitedClient client;

    public CustomComponents(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List all component definitions: {@code GET /component}. */
    public ApiResponse<ComponentList> getComponent(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT,
                reopen(options).queryParams(queryParams).build(), ComponentList.class);
    }

    public ApiResponse<ComponentList> getComponent(
            Map<String, Object> queryParams) {
        return getComponent(queryParams, null);
    }

    public ApiResponse<ComponentList> getComponent() {
        return getComponent(null, null);
    }

    /** Single component definition: {@code GET /component/{component_uuid}}. */
    public ApiResponse<Component> getComponentByUuid(
            String componentUuid, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_BY_UUID,
                reopen(options).pathParam("component_uuid", componentUuid).queryParams(queryParams).build(), Component.class);
    }

    public ApiResponse<Component> getComponentByUuid(
            String componentUuid, Map<String, Object> queryParams) {
        return getComponentByUuid(componentUuid, queryParams, null);
    }

    public ApiResponse<Component> getComponentByUuid(String componentUuid) {
        return getComponentByUuid(componentUuid, null, null);
    }

    /** Create new instance: {@code POST /component/{component_uuid}}; answered 201. */
    public ApiResponse<ComponentByUuid> createComponentByUuid(
            String componentUuid, Object payload, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requirePayload(payload);
        DtoFactory view = instanceView(payload);
        return client.sendPostRequest(CustomComponentsUrls.COMPONENT_BY_UUID,
                reopen(options).pathParam("component_uuid", componentUuid).jsonBody(payload).dtoFactory(view).build(),
                view == null ? ComponentByUuid.class : null);
    }

    public ApiResponse<ComponentByUuid> createComponentByUuid(
            String componentUuid, Object payload) {
        return createComponentByUuid(componentUuid, payload, null);
    }

    /**
     * Delete instance: {@code DELETE /component/{component_uuid}/{component_object_id}}; answered
     * 204. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteComponentById(
            String componentUuid, String componentObjectId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        return client.sendDeleteRequest(CustomComponentsUrls.COMPONENT_BY_ID,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).build(), null);
    }

    public ApiResponse<Dto> deleteComponentById(
            String componentUuid, String componentObjectId) {
        return deleteComponentById(componentUuid, componentObjectId, null);
    }

    /**
     * Update instance: {@code PATCH /component/{component_uuid}/{component_object_id}};
     * answered 202.
     */
    public ApiResponse<ComponentByUuid> updateComponentById(
            String componentUuid, String componentObjectId, Object payload, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requirePayload(payload);
        DtoFactory view = instanceView(payload);
        return client.sendPatchRequest(CustomComponentsUrls.COMPONENT_BY_ID,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).jsonBody(payload).dtoFactory(view).build(),
                view == null ? ComponentByUuid.class : null);
    }

    public ApiResponse<ComponentByUuid> updateComponentById(
            String componentUuid, String componentObjectId, Object payload) {
        return updateComponentById(componentUuid, componentObjectId, payload, null);
    }

    /** List all instances: {@code GET /components/{component_uuid}/objects}. */
    public ApiResponse<ComponentObjects> getComponentObjects(
            String componentUuid, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECTS,
                reopen(options).pathParam("component_uuid", componentUuid).queryParams(queryParams).build(), ComponentObjects.class);
    }

    public ApiResponse<ComponentObjects> getComponentObjects(
            String componentUuid, Map<String, Object> queryParams) {
        return getComponentObjects(componentUuid, queryParams, null);
    }

    public ApiResponse<ComponentObjects> getComponentObjects(String componentUuid) {
        return getComponentObjects(componentUuid, null, null);
    }

    /** Single instance: {@code GET /components/{component_uuid}/objects/{component_object_id}}. */
    public ApiResponse<ComponentObject> getComponentObject(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).queryParams(queryParams).build(), ComponentObject.class);
    }

    public ApiResponse<ComponentObject> getComponentObject(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams) {
        return getComponentObject(componentUuid, componentObjectId, queryParams, null);
    }

    public ApiResponse<ComponentObject> getComponentObject(String componentUuid, String componentObjectId) {
        return getComponentObject(componentUuid, componentObjectId, null, null);
    }

    /**
     * Activity log:
     * {@code GET /components/{component_uuid}/objects/{component_object_id}/activity}.
     */
    public ApiResponse<ComponentObjectActivity> getComponentObjectActivity(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_ACTIVITY,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).queryParams(queryParams).build(), ComponentObjectActivity.class);
    }

    public ApiResponse<ComponentObjectActivity> getComponentObjectActivity(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams) {
        return getComponentObjectActivity(componentUuid, componentObjectId, queryParams, null);
    }

    public ApiResponse<ComponentObjectActivity> getComponentObjectActivity(String componentUuid, String componentObjectId) {
        return getComponentObjectActivity(componentUuid, componentObjectId, null, null);
    }

    /**
     * List linked custom objects:
     * {@code GET /components/{component_uuid}/objects/{component_object_id}/custom_objects}.
     */
    public ApiResponse<ComponentObjectCustomObjects> getComponentObjectCustomObjects(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_CUSTOM_OBJECTS,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).queryParams(queryParams).build(), ComponentObjectCustomObjects.class);
    }

    public ApiResponse<ComponentObjectCustomObjects> getComponentObjectCustomObjects(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams) {
        return getComponentObjectCustomObjects(componentUuid, componentObjectId, queryParams, null);
    }

    public ApiResponse<ComponentObjectCustomObjects> getComponentObjectCustomObjects(String componentUuid, String componentObjectId) {
        return getComponentObjectCustomObjects(componentUuid, componentObjectId, null, null);
    }

    /**
     * PDF descriptor: {@code GET /components/{component_uuid}/objects/{component_object_id}/pdf}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getComponentObjectPdf(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_PDF,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getComponentObjectPdf(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams) {
        return getComponentObjectPdf(componentUuid, componentObjectId, queryParams, null);
    }

    public ApiResponse<Dto> getComponentObjectPdf(String componentUuid, String componentObjectId) {
        return getComponentObjectPdf(componentUuid, componentObjectId, null, null);
    }

    /** The instance view under the one key the payload names, which the answer is keyed by too. */
    private static DtoFactory instanceView(Object payload) {
        Object body = payload instanceof Dto written ? written.toMap() : payload;
        if (body instanceof Map<?, ?> fields && fields.size() == 1
                && fields.keySet().iterator().next() instanceof String name) {
            return decoded -> Dto.fromMap(ComponentByUuid.class, unwrapped(decoded, name));
        }
        return null;
    }

    private static Object unwrapped(Object decoded, String name) {
        if (!(decoded instanceof Map<?, ?> body) || !(body.get(name) instanceof Map<?, ?> record)) {
            return decoded;
        }
        Map<String, Object> merged = new LinkedHashMap<>();
        for (Map.Entry<?, ?> entry : record.entrySet()) {
            merged.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        for (Map.Entry<?, ?> entry : body.entrySet()) {
            if (!name.equals(entry.getKey())) {
                merged.put(String.valueOf(entry.getKey()), entry.getValue());
            }
        }
        return merged;
    }

    /**
     * Create a component definition: {@code POST /component}, under {@code component}; {@code name}
     * is required, and {@code use_in} asks for {@code relation} beside it.
     */
    public ApiResponse<Component> createComponent(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(CustomComponentsUrls.COMPONENT,
                reopen(options).jsonBody(payload).build(), Component.class);
    }

    public ApiResponse<Component> createComponent(Object payload) {
        return createComponent(payload, null);
    }

    /**
     * Update a component definition: {@code PATCH /component/{component_uuid}}, the {@code cmp_}
     * id, under {@code component}.
     */
    public ApiResponse<Component> updateComponent(
            String componentUuid, Object payload, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requirePayload(payload);
        return client.sendPatchRequest(CustomComponentsUrls.COMPONENT_BY_UUID,
                reopen(options).pathParam("component_uuid", componentUuid).jsonBody(payload).build(), Component.class);
    }

    public ApiResponse<Component> updateComponent(String componentUuid, Object payload) {
        return updateComponent(componentUuid, payload, null);
    }

    /**
     * Delete a component definition: {@code DELETE /component/{component_uuid}}, the {@code cmp_}
     * id; answered 204. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteComponent(
            String componentUuid, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        return client.sendDeleteRequest(CustomComponentsUrls.COMPONENT_BY_UUID,
                reopen(options).pathParam("component_uuid", componentUuid).build(), null);
    }

    public ApiResponse<Dto> deleteComponent(String componentUuid) {
        return deleteComponent(componentUuid, null);
    }

    /**
     * Create an instance on v4: {@code POST /components/{component_uuid}/objects}, under
     * {@code custom_component_object}; every key is optional, and {@code parents} names the owner.
     */
    public ApiResponse<ComponentObjectRecord> createComponentObject(
            String componentUuid, Object payload, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requirePayload(payload);
        return client.sendPostRequest(CustomComponentsUrls.COMPONENT_OBJECTS,
                reopen(options).pathParam("component_uuid", componentUuid).jsonBody(payload).build(), ComponentObjectRecord.class);
    }

    public ApiResponse<ComponentObjectRecord> createComponentObject(String componentUuid, Object payload) {
        return createComponentObject(componentUuid, payload, null);
    }

    /**
     * Update an instance on v4:
     * {@code PATCH /components/{component_uuid}/objects/{component_object_id}}; {@code version_id}
     * must match when sent, and {@code code}, {@code parents} and {@code custom_form} are refused.
     */
    public ApiResponse<ComponentObjectRecord> updateComponentObject(
            String componentUuid, String componentObjectId, Object payload, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requirePayload(payload);
        return client.sendPatchRequest(CustomComponentsUrls.COMPONENT_OBJECT,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).jsonBody(payload).build(), ComponentObjectRecord.class);
    }

    public ApiResponse<ComponentObjectRecord> updateComponentObject(
            String componentUuid, String componentObjectId, Object payload) {
        return updateComponentObject(componentUuid, componentObjectId, payload, null);
    }

    /**
     * Delete an instance on v4:
     * {@code DELETE /components/{component_uuid}/objects/{component_object_id}}; answered
     * {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteComponentObject(
            String componentUuid, String componentObjectId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        return client.sendDeleteRequest(CustomComponentsUrls.COMPONENT_OBJECT,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).build(), null);
    }

    public ApiResponse<Dto> deleteComponentObject(String componentUuid, String componentObjectId) {
        return deleteComponentObject(componentUuid, componentObjectId, null);
    }

    /**
     * Log an activity:
     * {@code POST /components/{component_uuid}/objects/{component_object_id}/activity}, under
     * {@code custom_component_object_activity} with {@code type}, {@code message} and
     * {@code user_name}.
     */
    public ApiResponse<ComponentObjectActivityEntry> createComponentObjectActivity(
            String componentUuid, String componentObjectId, Object payload, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requirePayload(payload);
        return client.sendPostRequest(CustomComponentsUrls.COMPONENT_OBJECT_ACTIVITY,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).jsonBody(payload).build(), ComponentObjectActivityEntry.class);
    }

    public ApiResponse<ComponentObjectActivityEntry> createComponentObjectActivity(
            String componentUuid, String componentObjectId, Object payload) {
        return createComponentObjectActivity(componentUuid, componentObjectId, payload, null);
    }

    /**
     * One activity:
     * {@code GET /components/{component_uuid}/objects/{component_object_id}/activity/{activity_id}}.
     */
    public ApiResponse<ComponentObjectActivityEntry> getComponentObjectActivityById(
            String componentUuid, String componentObjectId, String activityId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(activityId, "activity_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_ACTIVITY_ENTRY,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("activity_id", activityId).build(), ComponentObjectActivityEntry.class);
    }

    public ApiResponse<ComponentObjectActivityEntry> getComponentObjectActivityById(
            String componentUuid, String componentObjectId, String activityId) {
        return getComponentObjectActivityById(componentUuid, componentObjectId, activityId, null);
    }

    /**
     * List workflow milestones:
     * {@code GET /components/{component_uuid}/objects/{component_object_id}/milestones}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getComponentObjectMilestones(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_MILESTONES,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getComponentObjectMilestones(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams) {
        return getComponentObjectMilestones(componentUuid, componentObjectId, queryParams, null);
    }

    public ApiResponse<Dto> getComponentObjectMilestones(String componentUuid, String componentObjectId) {
        return getComponentObjectMilestones(componentUuid, componentObjectId, null, null);
    }

    /**
     * One workflow milestone:
     * {@code GET /components/{component_uuid}/objects/{component_object_id}/milestones/{milestone_id}},
     * the {@code wfm_} id. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getComponentObjectMilestone(
            String componentUuid, String componentObjectId, String milestoneId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(milestoneId, "milestone_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_MILESTONE,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("milestone_id", milestoneId).build(), null);
    }

    public ApiResponse<Dto> getComponentObjectMilestone(
            String componentUuid, String componentObjectId, String milestoneId) {
        return getComponentObjectMilestone(componentUuid, componentObjectId, milestoneId, null);
    }

    /**
     * List workflow history:
     * {@code GET /components/{component_uuid}/objects/{component_object_id}/history}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getComponentObjectHistory(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_HISTORY,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getComponentObjectHistory(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams) {
        return getComponentObjectHistory(componentUuid, componentObjectId, queryParams, null);
    }

    public ApiResponse<Dto> getComponentObjectHistory(String componentUuid, String componentObjectId) {
        return getComponentObjectHistory(componentUuid, componentObjectId, null, null);
    }

    /**
     * One workflow history entry:
     * {@code GET /components/{component_uuid}/objects/{component_object_id}/history/{history_id}},
     * the {@code wfh_} id. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getComponentObjectHistoryById(
            String componentUuid, String componentObjectId, String historyId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(historyId, "history_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_HISTORY_ENTRY,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("history_id", historyId).build(), null);
    }

    public ApiResponse<Dto> getComponentObjectHistoryById(
            String componentUuid, String componentObjectId, String historyId) {
        return getComponentObjectHistoryById(componentUuid, componentObjectId, historyId, null);
    }

    /** List notes: {@code GET /components/{component_uuid}/objects/{component_object_id}/notes}. */
    public ApiResponse<ComponentObjectNotes> getComponentObjectNotes(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_NOTES,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).queryParams(queryParams).build(), ComponentObjectNotes.class);
    }

    public ApiResponse<ComponentObjectNotes> getComponentObjectNotes(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams) {
        return getComponentObjectNotes(componentUuid, componentObjectId, queryParams, null);
    }

    public ApiResponse<ComponentObjectNotes> getComponentObjectNotes(
            String componentUuid, String componentObjectId) {
        return getComponentObjectNotes(componentUuid, componentObjectId, null, null);
    }

    /**
     * One note:
     * {@code GET /components/{component_uuid}/objects/{component_object_id}/notes/{note_id}}.
     */
    public ApiResponse<ComponentObjectNote> getComponentObjectNote(
            String componentUuid, String componentObjectId, String noteId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(noteId, "note_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_NOTE,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("note_id", noteId).build(), ComponentObjectNote.class);
    }

    public ApiResponse<ComponentObjectNote> getComponentObjectNote(
            String componentUuid, String componentObjectId, String noteId) {
        return getComponentObjectNote(componentUuid, componentObjectId, noteId, null);
    }

    /**
     * List a note's files:
     * {@code GET /components/{component_uuid}/objects/{component_object_id}/notes/{note_id}/files}.
     */
    public ApiResponse<ComponentObjectNoteFiles> getComponentObjectNoteFiles(
            String componentUuid, String componentObjectId, String noteId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(noteId, "note_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_NOTE_FILES,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("note_id", noteId).build(), ComponentObjectNoteFiles.class);
    }

    public ApiResponse<ComponentObjectNoteFiles> getComponentObjectNoteFiles(
            String componentUuid, String componentObjectId, String noteId) {
        return getComponentObjectNoteFiles(componentUuid, componentObjectId, noteId, null);
    }

    /**
     * One file of a note: {@code GET /components/{component_uuid}/objects/{component_object_id}}
     * {@code /notes/{note_id}/files/{file_id}}, the attachment's {@code uuid}.
     */
    public ApiResponse<ComponentObjectNoteFile> getComponentObjectNoteFile(
            String componentUuid, String componentObjectId, String noteId, String fileId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(noteId, "note_id");
        requireValue(fileId, "file_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_NOTE_FILE,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("note_id", noteId).pathParam("file_id", fileId).build(), ComponentObjectNoteFile.class);
    }

    public ApiResponse<ComponentObjectNoteFile> getComponentObjectNoteFile(
            String componentUuid, String componentObjectId, String noteId, String fileId) {
        return getComponentObjectNoteFile(componentUuid, componentObjectId, noteId, fileId, null);
    }

    /**
     * Attach a note: {@code POST /components/{component_uuid}/objects/{component_object_id}/notes};
     * a form, the text as the {@code note} field with any {@code files} beside it.
     */
    public ApiResponse<ComponentObjectNote> createComponentObjectNote(
            String componentUuid, String componentObjectId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(CustomComponentsUrls.COMPONENT_OBJECT_NOTES,
                request.build(), ComponentObjectNote.class);
    }

    public ApiResponse<ComponentObjectNote> createComponentObjectNote(
            String componentUuid, String componentObjectId, String note, RequestOptions options) {
        return createComponentObjectNote(componentUuid, componentObjectId, note, null, options);
    }

    public ApiResponse<ComponentObjectNote> createComponentObjectNote(
            String componentUuid, String componentObjectId, String note) {
        return createComponentObjectNote(componentUuid, componentObjectId, note, null, null);
    }

    /**
     * Update a note:
     * {@code PATCH /components/{component_uuid}/objects/{component_object_id}/notes/{note_id}}; a
     * form, as the create is.
     */
    public ApiResponse<ComponentObjectNote> updateComponentObjectNote(
            String componentUuid, String componentObjectId, String noteId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(noteId, "note_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("note_id", noteId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPatchRequest(CustomComponentsUrls.COMPONENT_OBJECT_NOTE,
                request.build(), ComponentObjectNote.class);
    }

    public ApiResponse<ComponentObjectNote> updateComponentObjectNote(
            String componentUuid, String componentObjectId, String noteId, String note, RequestOptions options) {
        return updateComponentObjectNote(componentUuid, componentObjectId, noteId, note, null, options);
    }

    public ApiResponse<ComponentObjectNote> updateComponentObjectNote(
            String componentUuid, String componentObjectId, String noteId, String note) {
        return updateComponentObjectNote(componentUuid, componentObjectId, noteId, note, null, null);
    }

    /**
     * Delete a note:
     * {@code DELETE /components/{component_uuid}/objects/{component_object_id}/notes/{note_id}};
     * answered {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteComponentObjectNote(
            String componentUuid, String componentObjectId, String noteId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(noteId, "note_id");
        return client.sendDeleteRequest(CustomComponentsUrls.COMPONENT_OBJECT_NOTE,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> deleteComponentObjectNote(
            String componentUuid, String componentObjectId, String noteId) {
        return deleteComponentObjectNote(componentUuid, componentObjectId, noteId, null);
    }

    /**
     * Delete a note's file:
     * {@code DELETE /components/{component_uuid}/objects/{component_object_id}}
     * {@code /notes/{note_id}/files/{file_id}}, the attachment's {@code uuid}; answered
     * {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteComponentObjectNoteFile(
            String componentUuid, String componentObjectId, String noteId, String fileId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(noteId, "note_id");
        requireValue(fileId, "file_id");
        return client.sendDeleteRequest(CustomComponentsUrls.COMPONENT_OBJECT_NOTE_FILE,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("note_id", noteId).pathParam("file_id", fileId).build(), null);
    }

    public ApiResponse<Dto> deleteComponentObjectNoteFile(
            String componentUuid, String componentObjectId, String noteId, String fileId) {
        return deleteComponentObjectNoteFile(componentUuid, componentObjectId, noteId, fileId, null);
    }

    /**
     * One custom object schema with its instances: {@code GET /components/{component_uuid}/objects}
     * {@code /{component_object_id}/custom_objects/{custom_object_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> getComponentObjectCustomObject(
            String componentUuid, String componentObjectId, String customObjectId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(customObjectId, "custom_object_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_CUSTOM_OBJECT,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("custom_object_id", customObjectId).build(), null);
    }

    public ApiResponse<Dto> getComponentObjectCustomObject(
            String componentUuid, String componentObjectId, String customObjectId) {
        return getComponentObjectCustomObject(componentUuid, componentObjectId, customObjectId, null);
    }

    /**
     * Create a custom object instance: {@code POST /components/{component_uuid}/objects}
     * {@code /{component_object_id}/custom_objects/{custom_object_id}}, under
     * {@code custom_object_instances} with an {@code attributes} list; the path has no
     * {@code instances} segment. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createComponentObjectCustomObjectInstance(
            String componentUuid, String componentObjectId, String customObjectId, Object payload, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(customObjectId, "custom_object_id");
        requirePayload(payload);
        return client.sendPostRequest(CustomComponentsUrls.COMPONENT_OBJECT_CUSTOM_OBJECT,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("custom_object_id", customObjectId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createComponentObjectCustomObjectInstance(
            String componentUuid, String componentObjectId, String customObjectId, Object payload) {
        return createComponentObjectCustomObjectInstance(componentUuid, componentObjectId, customObjectId, payload, null);
    }

    /**
     * One custom object instance: {@code GET /components/{component_uuid}/objects}
     * {@code /{component_object_id}/custom_objects/{custom_object_id}/instances/{instance_id}}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getComponentObjectCustomObjectInstance(
            String componentUuid, String componentObjectId, String customObjectId, String instanceId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(customObjectId, "custom_object_id");
        requireValue(instanceId, "instance_id");
        return client.sendGetRequest(CustomComponentsUrls.COMPONENT_OBJECT_CUSTOM_OBJECT_INSTANCE,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("custom_object_id", customObjectId).pathParam("instance_id", instanceId).build(), null);
    }

    public ApiResponse<Dto> getComponentObjectCustomObjectInstance(
            String componentUuid, String componentObjectId, String customObjectId, String instanceId) {
        return getComponentObjectCustomObjectInstance(componentUuid, componentObjectId, customObjectId, instanceId, null);
    }

    /**
     * Update a custom object instance: {@code PATCH /components/{component_uuid}/objects}
     * {@code /{component_object_id}/custom_objects/{custom_object_id}/instances/{instance_id}},
     * under {@code custom_object_instance} with an {@code attributes} list. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> updateComponentObjectCustomObjectInstance(
            String componentUuid, String componentObjectId, String customObjectId, String instanceId, Object payload, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(customObjectId, "custom_object_id");
        requireValue(instanceId, "instance_id");
        requirePayload(payload);
        return client.sendPatchRequest(CustomComponentsUrls.COMPONENT_OBJECT_CUSTOM_OBJECT_INSTANCE,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("custom_object_id", customObjectId).pathParam("instance_id", instanceId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateComponentObjectCustomObjectInstance(
            String componentUuid, String componentObjectId, String customObjectId, String instanceId, Object payload) {
        return updateComponentObjectCustomObjectInstance(componentUuid, componentObjectId, customObjectId, instanceId, payload, null);
    }

    /**
     * Delete a custom object instance: {@code DELETE /components/{component_uuid}/objects}
     * {@code /{component_object_id}/custom_objects/{custom_object_id}/instances/{instance_id}}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteComponentObjectCustomObjectInstance(
            String componentUuid, String componentObjectId, String customObjectId, String instanceId, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requireValue(customObjectId, "custom_object_id");
        requireValue(instanceId, "instance_id");
        return client.sendDeleteRequest(CustomComponentsUrls.COMPONENT_OBJECT_CUSTOM_OBJECT_INSTANCE,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).pathParam("custom_object_id", customObjectId).pathParam("instance_id", instanceId).build(), null);
    }

    public ApiResponse<Dto> deleteComponentObjectCustomObjectInstance(
            String componentUuid, String componentObjectId, String customObjectId, String instanceId) {
        return deleteComponentObjectCustomObjectInstance(componentUuid, componentObjectId, customObjectId, instanceId, null);
    }

    /**
     * Link accounts or orders:
     * {@code POST /components/{component_uuid}/objects/{component_object_id}/link_domains}, under
     * {@code custom_component_object} with {@code domain_type} and {@code domains[].domain_id}.
     */
    public ApiResponse<ComponentObjectRecord> linkComponentObjectDomains(
            String componentUuid, String componentObjectId, Object payload, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requirePayload(payload);
        return client.sendPostRequest(CustomComponentsUrls.COMPONENT_OBJECT_LINK_DOMAINS,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).jsonBody(payload).build(), ComponentObjectRecord.class);
    }

    public ApiResponse<ComponentObjectRecord> linkComponentObjectDomains(
            String componentUuid, String componentObjectId, Object payload) {
        return linkComponentObjectDomains(componentUuid, componentObjectId, payload, null);
    }

    /**
     * Replace the linked accounts or orders:
     * {@code PATCH /components/{component_uuid}/objects/{component_object_id}/link_domains}; a
     * domain left out of {@code domains} is unlinked.
     */
    public ApiResponse<ComponentObjectRecord> updateComponentObjectDomains(
            String componentUuid, String componentObjectId, Object payload, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requirePayload(payload);
        return client.sendPatchRequest(CustomComponentsUrls.COMPONENT_OBJECT_LINK_DOMAINS,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).jsonBody(payload).build(), ComponentObjectRecord.class);
    }

    public ApiResponse<ComponentObjectRecord> updateComponentObjectDomains(
            String componentUuid, String componentObjectId, Object payload) {
        return updateComponentObjectDomains(componentUuid, componentObjectId, payload, null);
    }

    /**
     * Unlink accounts or orders:
     * {@code POST /components/{component_uuid}/objects/{component_object_id}/unlink_domains}; a
     * domain that is not linked is refused.
     */
    public ApiResponse<ComponentObjectRecord> unlinkComponentObjectDomains(
            String componentUuid, String componentObjectId, Object payload, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requirePayload(payload);
        return client.sendPostRequest(CustomComponentsUrls.COMPONENT_OBJECT_UNLINK_DOMAINS,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).jsonBody(payload).build(), ComponentObjectRecord.class);
    }

    public ApiResponse<ComponentObjectRecord> unlinkComponentObjectDomains(
            String componentUuid, String componentObjectId, Object payload) {
        return unlinkComponentObjectDomains(componentUuid, componentObjectId, payload, null);
    }

    /**
     * Move an instance through its workflow:
     * {@code POST /components/{component_uuid}/objects/{component_object_id}/change_status}, under
     * {@code custom_component_object} with {@code workflow_status}; runs the transition at once.
     */
    public ApiResponse<ComponentObjectRecord> changeComponentObjectStatus(
            String componentUuid, String componentObjectId, Object payload, RequestOptions options) {
        requireValue(componentUuid, "component_uuid");
        requireValue(componentObjectId, "component_object_id");
        requirePayload(payload);
        return client.sendPostRequest(CustomComponentsUrls.COMPONENT_OBJECT_CHANGE_STATUS,
                reopen(options).pathParam("component_uuid", componentUuid).pathParam("component_object_id", componentObjectId).jsonBody(payload).build(), ComponentObjectRecord.class);
    }

    public ApiResponse<ComponentObjectRecord> changeComponentObjectStatus(
            String componentUuid, String componentObjectId, Object payload) {
        return changeComponentObjectStatus(componentUuid, componentObjectId, payload, null);
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
