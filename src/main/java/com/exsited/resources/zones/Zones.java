package com.exsited.resources.zones;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.zones.zones_dto.ZonesDto.SettingsZone;
import com.exsited.resources.zones.zones_dto.ZonesDto.SettingsZoneList;
import java.util.Map;

/** Zone endpoints, under {@code /api/{api_version}/settings/zones}. */
public final class Zones implements BoundResource {

    private final ExsitedClient client;

    public Zones(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List zones: {@code GET /settings/zones}. */
    public ApiResponse<SettingsZoneList> getSettingsZones(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ZonesUrls.SETTINGS_ZONES,
                reopen(options).queryParams(queryParams).build(), SettingsZoneList.class);
    }

    public ApiResponse<SettingsZoneList> getSettingsZones(Map<String, Object> queryParams) {
        return getSettingsZones(queryParams, null);
    }

    public ApiResponse<SettingsZoneList> getSettingsZones() {
        return getSettingsZones(null, null);
    }

    /**
     * Create a zone: {@code POST /settings/zones}; {@code country_list} takes country ids, one at
     * least.
     */
    public ApiResponse<SettingsZone> createSettingsZone(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ZonesUrls.SETTINGS_ZONES,
                reopen(options).jsonBody(payload).build(), SettingsZone.class);
    }

    public ApiResponse<SettingsZone> createSettingsZone(Object payload) {
        return createSettingsZone(payload, null);
    }

    /** Get one zone: {@code GET /settings/zones/{zone_id}}. */
    public ApiResponse<SettingsZone> getSettingsZone(
            String zoneId, RequestOptions options) {
        requireValue(zoneId, "zone_id");
        return client.sendGetRequest(ZonesUrls.SETTINGS_ZONE,
                reopen(options).pathParam("zone_id", zoneId).build(), SettingsZone.class);
    }

    public ApiResponse<SettingsZone> getSettingsZone(String zoneId) {
        return getSettingsZone(zoneId, null);
    }

    /**
     * Delete a zone: {@code DELETE /settings/zones/{zone_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsZone(
            String zoneId, RequestOptions options) {
        requireValue(zoneId, "zone_id");
        return client.sendDeleteRequest(ZonesUrls.SETTINGS_ZONE,
                reopen(options).pathParam("zone_id", zoneId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsZone(String zoneId) {
        return deleteSettingsZone(zoneId, null);
    }

    /**
     * Update a zone: {@code PATCH /settings/zones/{zone_id}}; measured answering 200 and changing
     * nothing.
     */
    public ApiResponse<SettingsZone> updateSettingsZone(
            String zoneId, Object payload, RequestOptions options) {
        requireValue(zoneId, "zone_id");
        requirePayload(payload);
        return client.sendPatchRequest(ZonesUrls.SETTINGS_ZONE,
                reopen(options).pathParam("zone_id", zoneId).jsonBody(payload).build(), SettingsZone.class);
    }

    public ApiResponse<SettingsZone> updateSettingsZone(String zoneId, Object payload) {
        return updateSettingsZone(zoneId, payload, null);
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
