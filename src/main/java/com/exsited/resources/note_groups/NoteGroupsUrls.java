package com.exsited.resources.note_groups;

/** Path templates and body keys for the note group endpoints. */
public final class NoteGroupsUrls {

    private NoteGroupsUrls() {}

    public static final String NOTE_GROUPS = "/api/{api_version}/note_groups";
    public static final String NOTE_GROUP = "/api/{api_version}/note_groups/{note_group_id}";
    public static final String NOTE_GROUP_NOTES = "/api/{api_version}/note_groups/{note_group_id}/notes";
    public static final String NOTE_GROUP_NOTES_BY_USER = "/api/{api_version}/note_groups/{note_group_id}/notes/user/{note_user}";

    /** The key a listing's records sit under. */
    public static final String NOTE_GROUPS_KEY = "data";
    public static final String NOTE_GROUP_NOTES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String NOTE_GROUP_KEY = "note_group";
    public static final String NOTE_GROUP_NOTES_BY_USER_KEY = "data";
}
