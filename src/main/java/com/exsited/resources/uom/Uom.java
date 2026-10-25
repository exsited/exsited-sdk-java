package com.exsited.resources.uom;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.uom.uom_dto.UomDto.SettingsUom;
import com.exsited.resources.uom.uom_dto.UomDto.SettingsUomList;
import java.util.Map;

/** Unit of measurement endpoints, under {@code /api/{api_version}/settings/uom}. */
public final class Uom implements BoundResource {

    private final ExsitedClient client;

    public Uom(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List units of measurement: {@code GET /settings/uom}. */
    public ApiResponse<SettingsUomList> getSettingsUoms(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(UomUrls.SETTINGS_UOMS,
                reopen(options).queryParams(queryParams).build(), SettingsUomList.class);
    }

    public ApiResponse<SettingsUomList> getSettingsUoms(Map<String, Object> queryParams) {
        return getSettingsUoms(queryParams, null);
    }

    public ApiResponse<SettingsUomList> getSettingsUoms() {
        return getSettingsUoms(null, null);
    }

    /**
     * Create a unit of measurement: {@code POST /settings/uom}; answers 400 validation_error,
     * naming no field, even for a valid body. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createSettingsUom(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(UomUrls.SETTINGS_UOMS,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createSettingsUom(Object payload) {
        return createSettingsUom(payload, null);
    }

    /** Get one unit of measurement: {@code GET /settings/uom/{uom_id}}. */
    public ApiResponse<SettingsUom> getSettingsUom(
            String uomId, RequestOptions options) {
        requireValue(uomId, "uom_id");
        return client.sendGetRequest(UomUrls.SETTINGS_UOM,
                reopen(options).pathParam("uom_id", uomId).build(), SettingsUom.class);
    }

    public ApiResponse<SettingsUom> getSettingsUom(String uomId) {
        return getSettingsUom(uomId, null);
    }

    /**
     * Delete a unit of measurement: {@code DELETE /settings/uom/{uom_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsUom(
            String uomId, RequestOptions options) {
        requireValue(uomId, "uom_id");
        return client.sendDeleteRequest(UomUrls.SETTINGS_UOM,
                reopen(options).pathParam("uom_id", uomId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsUom(String uomId) {
        return deleteSettingsUom(uomId, null);
    }

    /**
     * Update or enable/disable a unit of measurement: {@code PATCH /settings/uom/{uom_id}};
     * {@code status} takes active or inactive. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsUom(
            String uomId, Object payload, RequestOptions options) {
        requireValue(uomId, "uom_id");
        requirePayload(payload);
        return client.sendPatchRequest(UomUrls.SETTINGS_UOM,
                reopen(options).pathParam("uom_id", uomId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsUom(String uomId, Object payload) {
        return updateSettingsUom(uomId, payload, null);
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
