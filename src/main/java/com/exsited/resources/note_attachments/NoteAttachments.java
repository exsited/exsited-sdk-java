package com.exsited.resources.note_attachments;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.note_attachments.note_attachments_dto.NoteAttachmentsDto.NoteAttachment;

/** Note attachment endpoints, under {@code /api/{api_version}/note_attachment}. */
public final class NoteAttachments implements BoundResource {

    private final ExsitedClient client;

    public NoteAttachments(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * Any note's attachment as base64: {@code GET /note_attachment/{attachment_uuid}}, the
     * {@code uuid} a note's {@code attachments} entry carries, whatever the note is attached to.
     */
    public ApiResponse<NoteAttachment> getNoteAttachment(
            String attachmentUuid, RequestOptions options) {
        requireValue(attachmentUuid, "attachment_uuid");
        return client.sendGetRequest(NoteAttachmentsUrls.NOTE_ATTACHMENT,
                reopen(options).pathParam("attachment_uuid", attachmentUuid).build(), NoteAttachment.class);
    }

    public ApiResponse<NoteAttachment> getNoteAttachment(String attachmentUuid) {
        return getNoteAttachment(attachmentUuid, null);
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
}
