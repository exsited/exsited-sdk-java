package com.exsited.resources.note_groups.note_groups_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the note groups bodies. */
public final class NoteGroupsDto {

    /** The note group body. */
    public static final class NoteGroup extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/note_groups",
                "GET /api/{api_version}/note_groups/{note_group_id}",
                "POST /api/{api_version}/note_groups",
                "PATCH /api/{api_version}/note_groups/{note_group_id}");

        @Override
        protected String envelopeKey() {
            return "note_group";
        }

        public List<Object> activity_logs;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        /** 1 measured, null on 1. */
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
        public Long version;
    }

    /** A page of NoteGroup records. */
    public static final class NoteGroupList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/note_groups");

        public List<NoteGroup> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** The note group notes body. */
    public static final class NoteGroupNotes extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/note_groups/{note_group_id}/notes");

        public List<Object> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    private NoteGroupsDto() {
    }
}
