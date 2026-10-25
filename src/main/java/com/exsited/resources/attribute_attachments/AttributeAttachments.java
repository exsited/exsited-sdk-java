package com.exsited.resources.attribute_attachments;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;

/** Attribute attachment endpoints, under {@code /api/{api_version}/attribute_attachment}. */
public final class AttributeAttachments implements BoundResource {

    private final ExsitedClient client;

    public AttributeAttachments(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * A custom attribute's file as base64: {@code GET /attribute_attachment/{file_uuid}}, the uuid
     * an account's file attribute links to; answered as {@code {"base64": ...}} with no envelope.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getAttributeAttachment(
            String fileUuid, RequestOptions options) {
        requireValue(fileUuid, "file_uuid");
        return client.sendGetRequest(AttributeAttachmentsUrls.ATTRIBUTE_ATTACHMENT,
                reopen(options).pathParam("file_uuid", fileUuid).build(), null);
    }

    public ApiResponse<Dto> getAttributeAttachment(String fileUuid) {
        return getAttributeAttachment(fileUuid, null);
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
