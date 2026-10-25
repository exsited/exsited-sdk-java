package com.exsited.resources.custom_events;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.custom_events.custom_events_dto.CustomEventsDto.SettingsCustomEvent;
import com.exsited.resources.custom_events.custom_events_dto.CustomEventsDto.SettingsCustomEventList;
import java.util.Map;

/** Custom event endpoints, under {@code /api/{api_version}/settings/custom_events}. */
public final class CustomEvents implements BoundResource {

    private final ExsitedClient client;

    public CustomEvents(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the custom events: {@code GET /settings/custom_events}. */
    public ApiResponse<SettingsCustomEventList> getSettingsCustomEvents(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(CustomEventsUrls.SETTINGS_CUSTOM_EVENTS,
                reopen(options).queryParams(queryParams).build(), SettingsCustomEventList.class);
    }

    public ApiResponse<SettingsCustomEventList> getSettingsCustomEvents(Map<String, Object> queryParams) {
        return getSettingsCustomEvents(queryParams, null);
    }

    public ApiResponse<SettingsCustomEventList> getSettingsCustomEvents() {
        return getSettingsCustomEvents(null, null);
    }

    /**
     * Create a custom event; {@code name} and {@code base_event}, an {@code se_} id, are required,
     * and a message is added to every communication profile: {@code POST /settings/custom_events}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createSettingsCustomEvent(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(CustomEventsUrls.SETTINGS_CUSTOM_EVENTS,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createSettingsCustomEvent(Object payload) {
        return createSettingsCustomEvent(payload, null);
    }

    /**
     * Read one custom event by its {@code ce_} id:
     * {@code GET /settings/custom_events/{custom_event_id}}.
     */
    public ApiResponse<SettingsCustomEvent> getSettingsCustomEvent(
            String customEventId, RequestOptions options) {
        requireValue(customEventId, "custom_event_id");
        return client.sendGetRequest(CustomEventsUrls.SETTINGS_CUSTOM_EVENT,
                reopen(options).pathParam("custom_event_id", customEventId).build(), SettingsCustomEvent.class);
    }

    public ApiResponse<SettingsCustomEvent> getSettingsCustomEvent(String customEventId) {
        return getSettingsCustomEvent(customEventId, null);
    }

    /**
     * Delete a custom event no workflow uses:
     * {@code DELETE /settings/custom_events/{custom_event_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsCustomEvent(
            String customEventId, RequestOptions options) {
        requireValue(customEventId, "custom_event_id");
        return client.sendDeleteRequest(CustomEventsUrls.SETTINGS_CUSTOM_EVENT,
                reopen(options).pathParam("custom_event_id", customEventId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsCustomEvent(String customEventId) {
        return deleteSettingsCustomEvent(customEventId, null);
    }

    /**
     * Update a custom event's fields or its status:
     * {@code PATCH /settings/custom_events/{custom_event_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsCustomEvent(
            String customEventId, Object payload, RequestOptions options) {
        requireValue(customEventId, "custom_event_id");
        requirePayload(payload);
        return client.sendPatchRequest(CustomEventsUrls.SETTINGS_CUSTOM_EVENT,
                reopen(options).pathParam("custom_event_id", customEventId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsCustomEvent(String customEventId, Object payload) {
        return updateSettingsCustomEvent(customEventId, payload, null);
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
