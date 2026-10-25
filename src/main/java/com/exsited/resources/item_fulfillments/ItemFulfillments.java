package com.exsited.resources.item_fulfillments;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto.ItemFulfillmentList;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto.ItemFulfillmentNoteList;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto.ItemFulfillmentNoteRead;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto.ItemFulfillmentNotes;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto.ItemFulfillmentRead;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto.ItemFulfillmentUpdated;
import com.exsited.resources.item_fulfillments.item_fulfillments_dto.ItemFulfillmentsDto;
import java.util.Map;

/** Item fulfillment endpoints, under {@code /api/{api_version}}. */
public final class ItemFulfillments implements BoundResource {

    private final ExsitedClient client;

    public ItemFulfillments(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List item fulfilments: {@code GET /item_fulfillments}. */
    public ApiResponse<ItemFulfillmentList> getItemFulfillments(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ItemFulfillmentsUrls.ITEM_FULFILLMENTS,
                reopen(options).queryParams(queryParams).build(), ItemFulfillmentList.class);
    }

    public ApiResponse<ItemFulfillmentList> getItemFulfillments(
            Map<String, Object> queryParams) {
        return getItemFulfillments(queryParams, null);
    }

    public ApiResponse<ItemFulfillmentList> getItemFulfillments() {
        return getItemFulfillments(null, null);
    }

    /** Create fulfillment: {@code POST /item_fulfillments}. Recorded 201. */
    public ApiResponse<ItemFulfillmentsDto.ItemFulfillments> createItemFulfillment(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ItemFulfillmentsUrls.ITEM_FULFILLMENTS,
                reopen(options).jsonBody(payload).build(), ItemFulfillmentsDto.ItemFulfillments.class);
    }

    public ApiResponse<ItemFulfillmentsDto.ItemFulfillments> createItemFulfillment(
            Object payload) {
        return createItemFulfillment(payload, null);
    }

    /** Fulfillment details: {@code GET /item_fulfillments/{fulfillment_id}}. */
    public ApiResponse<ItemFulfillmentRead> getItemFulfillment(
            String fulfillmentId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(fulfillmentId, "fulfillment_id");
        return client.sendGetRequest(ItemFulfillmentsUrls.ITEM_FULFILLMENT,
                reopen(options).pathParam("fulfillment_id", fulfillmentId).queryParams(queryParams).build(), ItemFulfillmentRead.class);
    }

    public ApiResponse<ItemFulfillmentRead> getItemFulfillment(
            String fulfillmentId, Map<String, Object> queryParams) {
        return getItemFulfillment(fulfillmentId, queryParams, null);
    }

    public ApiResponse<ItemFulfillmentRead> getItemFulfillment(String fulfillmentId) {
        return getItemFulfillment(fulfillmentId, null, null);
    }

    /** Update fulfillment: {@code PATCH /item_fulfillments/{fulfillment_id}}. */
    public ApiResponse<ItemFulfillmentUpdated> updateItemFulfillment(
            String fulfillmentId, Object payload, RequestOptions options) {
        requireValue(fulfillmentId, "fulfillment_id");
        requirePayload(payload);
        return client.sendPatchRequest(ItemFulfillmentsUrls.ITEM_FULFILLMENT,
                reopen(options).pathParam("fulfillment_id", fulfillmentId).jsonBody(payload).build(), ItemFulfillmentUpdated.class);
    }

    public ApiResponse<ItemFulfillmentUpdated> updateItemFulfillment(
            String fulfillmentId, Object payload) {
        return updateItemFulfillment(fulfillmentId, payload, null);
    }

    /**
     * Create note: {@code POST /item_fulfillments/{fulfillment_id}/notes},
     * the text as the {@code note} form field; files on the options make it multipart.
     * Any {@code files} are sent beside the text as a multipart body.
     */
    public ApiResponse<ItemFulfillmentNotes> createItemFulfillmentNote(
            String fulfillmentId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(fulfillmentId, "fulfillment_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("fulfillment_id", fulfillmentId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(ItemFulfillmentsUrls.ITEM_FULFILLMENT_NOTES,
                request.build(), ItemFulfillmentNotes.class);
    }

    public ApiResponse<ItemFulfillmentNotes> createItemFulfillmentNote(
            String fulfillmentId, String note, RequestOptions options) {
        return createItemFulfillmentNote(fulfillmentId, note, null, options);
    }

    public ApiResponse<ItemFulfillmentNotes> createItemFulfillmentNote(
            String fulfillmentId, String note) {
        return createItemFulfillmentNote(fulfillmentId, note, null);
    }

    /**
     * Delete fulfillment: {@code DELETE /item_fulfillments/{fulfillment_id}}, by its {@code ifm_}
     * id; refused {@code 400} {@code stock_committed} once any of its stock has been sent. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteItemFulfillment(
            String fulfillmentId, RequestOptions options) {
        requireValue(fulfillmentId, "fulfillment_id");
        return client.sendDeleteRequest(ItemFulfillmentsUrls.ITEM_FULFILLMENT,
                reopen(options).pathParam("fulfillment_id", fulfillmentId).build(), null);
    }

    public ApiResponse<Dto> deleteItemFulfillment(String fulfillmentId) {
        return deleteItemFulfillment(fulfillmentId, null);
    }

    /**
     * Delete fulfillment note: {@code DELETE /item_fulfillments/{fulfillment_id}/notes/{note_id}},
     * {@code ifm_} and {@code note_} ids; it looks on the fulfillment, where the create never puts
     * one. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteItemFulfillmentNote(
            String fulfillmentId, String noteId, RequestOptions options) {
        requireValue(fulfillmentId, "fulfillment_id");
        requireValue(noteId, "note_id");
        return client.sendDeleteRequest(ItemFulfillmentsUrls.ITEM_FULFILLMENT_NOTE,
                reopen(options).pathParam("fulfillment_id", fulfillmentId).pathParam("note_id", noteId).build(), null);
    }

    public ApiResponse<Dto> deleteItemFulfillmentNote(String fulfillmentId, String noteId) {
        return deleteItemFulfillmentNote(fulfillmentId, noteId, null);
    }

    /**
     * List fulfillment notes: {@code GET /item_fulfillments/{fulfillment_id}/notes}; the notes of
     * the fulfillment's sale invoice, which is where {@code createItemFulfillmentNote} puts them.
     */
    public ApiResponse<ItemFulfillmentNoteList> getItemFulfillmentNotes(
            String fulfillmentId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(fulfillmentId, "fulfillment_id");
        return client.sendGetRequest(ItemFulfillmentsUrls.ITEM_FULFILLMENT_NOTES,
                reopen(options).pathParam("fulfillment_id", fulfillmentId).queryParams(queryParams).build(), ItemFulfillmentNoteList.class);
    }

    public ApiResponse<ItemFulfillmentNoteList> getItemFulfillmentNotes(
            String fulfillmentId, Map<String, Object> queryParams) {
        return getItemFulfillmentNotes(fulfillmentId, queryParams, null);
    }

    public ApiResponse<ItemFulfillmentNoteList> getItemFulfillmentNotes(String fulfillmentId) {
        return getItemFulfillmentNotes(fulfillmentId, null, null);
    }

    /**
     * Get one fulfillment note: {@code GET /item_fulfillments/{fulfillment_id}/notes/{note_id}},
     * answered under {@code item_fulfillment.note}.
     */
    public ApiResponse<ItemFulfillmentNoteRead> getItemFulfillmentNote(
            String fulfillmentId, String noteId, RequestOptions options) {
        requireValue(fulfillmentId, "fulfillment_id");
        requireValue(noteId, "note_id");
        return client.sendGetRequest(ItemFulfillmentsUrls.ITEM_FULFILLMENT_NOTE,
                reopen(options).pathParam("fulfillment_id", fulfillmentId).pathParam("note_id", noteId).build(), ItemFulfillmentNoteRead.class);
    }

    public ApiResponse<ItemFulfillmentNoteRead> getItemFulfillmentNote(String fulfillmentId, String noteId) {
        return getItemFulfillmentNote(fulfillmentId, noteId, null);
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
