package com.exsited.resources.system_events;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.system_events.system_events_dto.SystemEventsDto.SettingsSystemEvent;
import com.exsited.resources.system_events.system_events_dto.SystemEventsDto.SettingsSystemEventList;
import java.util.Map;

/** System event endpoints, under {@code /api/{api_version}/settings/system_events}. */
public final class SystemEvents implements BoundResource {

    private final ExsitedClient client;

    public SystemEvents(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List the base system events: {@code GET /settings/system_events}; reference data, read only.
     */
    public ApiResponse<SettingsSystemEventList> getSettingsSystemEvents(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(SystemEventsUrls.SETTINGS_SYSTEM_EVENTS,
                reopen(options).queryParams(queryParams).build(), SettingsSystemEventList.class);
    }

    public ApiResponse<SettingsSystemEventList> getSettingsSystemEvents(Map<String, Object> queryParams) {
        return getSettingsSystemEvents(queryParams, null);
    }

    public ApiResponse<SettingsSystemEventList> getSettingsSystemEvents() {
        return getSettingsSystemEvents(null, null);
    }

    /** Get one system event: {@code GET /settings/system_events/{system_event_id}}. */
    public ApiResponse<SettingsSystemEvent> getSettingsSystemEvent(
            String systemEventId, RequestOptions options) {
        requireValue(systemEventId, "system_event_id");
        return client.sendGetRequest(SystemEventsUrls.SETTINGS_SYSTEM_EVENT,
                reopen(options).pathParam("system_event_id", systemEventId).build(), SettingsSystemEvent.class);
    }

    public ApiResponse<SettingsSystemEvent> getSettingsSystemEvent(String systemEventId) {
        return getSettingsSystemEvent(systemEventId, null);
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
