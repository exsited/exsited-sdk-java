package com.exsited.resources.note_attachments.note_attachments_dto;

import com.exsited.Dto;
import java.util.List;

/** Typed views over the note attachment bodies. */
public final class NoteAttachmentsDto {

    private NoteAttachmentsDto() {}

    /**
     * The note attachment body, under {@code note_attachment}; {@code base64} is the file itself.
     */
    public static final class NoteAttachment extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/note_attachment/{attachment_uuid}");

        @Override
        protected String envelopeKey() {
            return "note_attachment";
        }

        public String base64;
        public String id;
        public String name;
        public String object;
        public Long size;
    }
}
