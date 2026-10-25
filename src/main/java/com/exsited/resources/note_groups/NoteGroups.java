package com.exsited.resources.note_groups;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.note_groups.note_groups_dto.NoteGroupsDto.NoteGroup;
import com.exsited.resources.note_groups.note_groups_dto.NoteGroupsDto.NoteGroupList;
import com.exsited.resources.note_groups.note_groups_dto.NoteGroupsDto.NoteGroupNotes;
import java.util.Map;

/** Note group endpoints, under {@code /api/{api_version}/note_groups}. */
public final class NoteGroups implements BoundResource {

    private final ExsitedClient client;

    public NoteGroups(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List note groups: {@code GET /note_groups}. */
    public ApiResponse<NoteGroupList> getNoteGroups(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(NoteGroupsUrls.NOTE_GROUPS,
                reopen(options).queryParams(queryParams).build(), NoteGroupList.class);
    }

    public ApiResponse<NoteGroupList> getNoteGroups(
            Map<String, Object> queryParams) {
        return getNoteGroups(queryParams, null);
    }

    public ApiResponse<NoteGroupList> getNoteGroups() {
        return getNoteGroups(null, null);
    }

    /**
     * Create note group: {@code POST /note_groups}.
     */
    public ApiResponse<NoteGroup> createNoteGroup(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(NoteGroupsUrls.NOTE_GROUPS,
                reopen(options).jsonBody(payload).build(), NoteGroup.class);
    }

    public ApiResponse<NoteGroup> createNoteGroup(
            Object payload) {
        return createNoteGroup(payload, null);
    }

    /**
     * Delete note group: {@code DELETE /note_groups/{note_group_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteNoteGroup(
            String noteGroupId, RequestOptions options) {
        requireValue(noteGroupId, "note_group_id");
        return client.sendDeleteRequest(NoteGroupsUrls.NOTE_GROUP,
                reopen(options).pathParam("note_group_id", noteGroupId).build(), null);
    }

    public ApiResponse<Dto> deleteNoteGroup(
            String noteGroupId) {
        return deleteNoteGroup(noteGroupId, null);
    }

    /** Get note group: {@code GET /note_groups/{note_group_id}}. */
    public ApiResponse<NoteGroup> getNoteGroup(
            String noteGroupId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(noteGroupId, "note_group_id");
        return client.sendGetRequest(NoteGroupsUrls.NOTE_GROUP,
                reopen(options).pathParam("note_group_id", noteGroupId).queryParams(queryParams).build(), NoteGroup.class);
    }

    public ApiResponse<NoteGroup> getNoteGroup(
            String noteGroupId, Map<String, Object> queryParams) {
        return getNoteGroup(noteGroupId, queryParams, null);
    }

    public ApiResponse<NoteGroup> getNoteGroup(String noteGroupId) {
        return getNoteGroup(noteGroupId, null, null);
    }

    /**
     * Update note group: {@code PATCH /note_groups/{note_group_id}}.
     */
    public ApiResponse<NoteGroup> updateNoteGroup(
            String noteGroupId, Object payload, RequestOptions options) {
        requireValue(noteGroupId, "note_group_id");
        requirePayload(payload);
        return client.sendPatchRequest(NoteGroupsUrls.NOTE_GROUP,
                reopen(options).pathParam("note_group_id", noteGroupId).jsonBody(payload).build(), NoteGroup.class);
    }

    public ApiResponse<NoteGroup> updateNoteGroup(
            String noteGroupId, Object payload) {
        return updateNoteGroup(noteGroupId, payload, null);
    }

    /** List group notes: {@code GET /note_groups/{note_group_id}/notes}. */
    public ApiResponse<NoteGroupNotes> getNoteGroupNotes(
            String noteGroupId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(noteGroupId, "note_group_id");
        return client.sendGetRequest(NoteGroupsUrls.NOTE_GROUP_NOTES,
                reopen(options).pathParam("note_group_id", noteGroupId).queryParams(queryParams).build(), NoteGroupNotes.class);
    }

    public ApiResponse<NoteGroupNotes> getNoteGroupNotes(
            String noteGroupId, Map<String, Object> queryParams) {
        return getNoteGroupNotes(noteGroupId, queryParams, null);
    }

    public ApiResponse<NoteGroupNotes> getNoteGroupNotes(String noteGroupId) {
        return getNoteGroupNotes(noteGroupId, null, null);
    }

    /**
     * List a group's notes as one of its users sees them:
     * {@code GET /note_groups/{note_group_id}/notes/user/{note_user}}; {@code note_user} is the
     * note user's uuid or what it was added as: an entity's uuid, or a manual user's email address.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getNoteGroupNotesByUser(
            String noteGroupId, String noteUser, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(noteGroupId, "note_group_id");
        requireValue(noteUser, "note_user");
        return client.sendGetRequest(NoteGroupsUrls.NOTE_GROUP_NOTES_BY_USER,
                reopen(options).pathParam("note_group_id", noteGroupId).pathParam("note_user", noteUser).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getNoteGroupNotesByUser(
            String noteGroupId, String noteUser, Map<String, Object> queryParams) {
        return getNoteGroupNotesByUser(noteGroupId, noteUser, queryParams, null);
    }

    public ApiResponse<Dto> getNoteGroupNotesByUser(String noteGroupId, String noteUser) {
        return getNoteGroupNotesByUser(noteGroupId, noteUser, null, null);
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
