package com.exsited.resources.note_groups;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.note_groups.note_groups_dto.NoteGroupsDto.NoteGroup;
import com.exsited.resources.note_groups.note_groups_dto.NoteGroupsDto.NoteGroupList;
import com.exsited.resources.note_groups.note_groups_dto.NoteGroupsDto.NoteGroupNotes;
import java.util.Map;

/**
 * Forwards every call to {@link NoteGroups}.
 *
 * @deprecated {@link NoteGroups} is bound to a client itself and is what
 *     {@code client.noteGroups()} answers; use it instead.
 */
@Deprecated
public final class NoteGroupsResource implements BoundResource {

    private final NoteGroups noteGroups;

    public NoteGroupsResource(ExsitedClient client) {
        this.noteGroups = new NoteGroups(client);
    }

    @Override
    public ExsitedClient getClient() {
        return noteGroups.getClient();
    }

    public ApiResponse<NoteGroupList> getNoteGroups(
            Map<String, Object> queryParams, RequestOptions options) {
        return noteGroups.getNoteGroups(queryParams, options);
    }

    public ApiResponse<NoteGroupList> getNoteGroups(Map<String, Object> queryParams) {
        return noteGroups.getNoteGroups(queryParams);
    }

    public ApiResponse<NoteGroup> createNoteGroup(Object payload, RequestOptions options) {
        return noteGroups.createNoteGroup(payload, options);
    }

    public ApiResponse<NoteGroup> createNoteGroup(Object payload) {
        return noteGroups.createNoteGroup(payload);
    }

    public ApiResponse<Dto> deleteNoteGroup(String noteGroupId, RequestOptions options) {
        return noteGroups.deleteNoteGroup(noteGroupId, options);
    }

    public ApiResponse<Dto> deleteNoteGroup(String noteGroupId) {
        return noteGroups.deleteNoteGroup(noteGroupId);
    }

    public ApiResponse<NoteGroup> getNoteGroup(
            String noteGroupId, Map<String, Object> queryParams, RequestOptions options) {
        return noteGroups.getNoteGroup(noteGroupId, queryParams, options);
    }

    public ApiResponse<NoteGroup> getNoteGroup(
            String noteGroupId, Map<String, Object> queryParams) {
        return noteGroups.getNoteGroup(noteGroupId, queryParams);
    }

    public ApiResponse<NoteGroup> updateNoteGroup(
            String noteGroupId, Object payload, RequestOptions options) {
        return noteGroups.updateNoteGroup(noteGroupId, payload, options);
    }

    public ApiResponse<NoteGroup> updateNoteGroup(String noteGroupId, Object payload) {
        return noteGroups.updateNoteGroup(noteGroupId, payload);
    }

    public ApiResponse<NoteGroupNotes> getNoteGroupNotes(
            String noteGroupId, Map<String, Object> queryParams, RequestOptions options) {
        return noteGroups.getNoteGroupNotes(noteGroupId, queryParams, options);
    }

    public ApiResponse<NoteGroupNotes> getNoteGroupNotes(
            String noteGroupId, Map<String, Object> queryParams) {
        return noteGroups.getNoteGroupNotes(noteGroupId, queryParams);
    }
}
