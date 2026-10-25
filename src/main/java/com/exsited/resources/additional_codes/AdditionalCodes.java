package com.exsited.resources.additional_codes;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.additional_codes.additional_codes_dto.AdditionalCodesDto.SettingsAdditionalCode;
import com.exsited.resources.additional_codes.additional_codes_dto.AdditionalCodesDto.SettingsAdditionalCodeList;
import java.util.Map;

/** Additional code endpoints, under {@code /api/{api_version}/settings/additional_codes}. */
public final class AdditionalCodes implements BoundResource {

    private final ExsitedClient client;

    public AdditionalCodes(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List the additional codes, the data sets of purchase order lines:
     * {@code GET /settings/additional_codes}.
     */
    public ApiResponse<SettingsAdditionalCodeList> getSettingsAdditionalCodes(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(AdditionalCodesUrls.SETTINGS_ADDITIONAL_CODES,
                reopen(options).queryParams(queryParams).build(), SettingsAdditionalCodeList.class);
    }

    public ApiResponse<SettingsAdditionalCodeList> getSettingsAdditionalCodes(
            Map<String, Object> queryParams) {
        return getSettingsAdditionalCodes(queryParams, null);
    }

    public ApiResponse<SettingsAdditionalCodeList> getSettingsAdditionalCodes() {
        return getSettingsAdditionalCodes(null, null);
    }

    /**
     * Create an additional code; {@code name} is required: {@code POST /settings/additional_codes}.
     */
    public ApiResponse<SettingsAdditionalCode> createSettingsAdditionalCode(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(AdditionalCodesUrls.SETTINGS_ADDITIONAL_CODES,
                reopen(options).jsonBody(payload).build(), SettingsAdditionalCode.class);
    }

    public ApiResponse<SettingsAdditionalCode> createSettingsAdditionalCode(Object payload) {
        return createSettingsAdditionalCode(payload, null);
    }

    /**
     * Read one additional code by its {@code ac_} id:
     * {@code GET /settings/additional_codes/{additional_code_id}}.
     */
    public ApiResponse<SettingsAdditionalCode> getSettingsAdditionalCode(
            String additionalCodeId, RequestOptions options) {
        requireValue(additionalCodeId, "additional_code_id");
        return client.sendGetRequest(AdditionalCodesUrls.SETTINGS_ADDITIONAL_CODE,
                reopen(options).pathParam("additional_code_id", additionalCodeId).build(), SettingsAdditionalCode.class);
    }

    public ApiResponse<SettingsAdditionalCode> getSettingsAdditionalCode(String additionalCodeId) {
        return getSettingsAdditionalCode(additionalCodeId, null);
    }

    /**
     * Delete an additional code: {@code DELETE /settings/additional_codes/{additional_code_id}}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsAdditionalCode(
            String additionalCodeId, RequestOptions options) {
        requireValue(additionalCodeId, "additional_code_id");
        return client.sendDeleteRequest(AdditionalCodesUrls.SETTINGS_ADDITIONAL_CODE,
                reopen(options).pathParam("additional_code_id", additionalCodeId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsAdditionalCode(String additionalCodeId) {
        return deleteSettingsAdditionalCode(additionalCodeId, null);
    }

    /**
     * Update an additional code's fields or its status:
     * {@code PATCH /settings/additional_codes/{additional_code_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> updateSettingsAdditionalCode(
            String additionalCodeId, Object payload, RequestOptions options) {
        requireValue(additionalCodeId, "additional_code_id");
        requirePayload(payload);
        return client.sendPatchRequest(AdditionalCodesUrls.SETTINGS_ADDITIONAL_CODE,
                reopen(options).pathParam("additional_code_id", additionalCodeId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateSettingsAdditionalCode(String additionalCodeId, Object payload) {
        return updateSettingsAdditionalCode(additionalCodeId, payload, null);
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
