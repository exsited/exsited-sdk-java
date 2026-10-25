package com.exsited.resources.custom_attributes;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.custom_attributes.custom_attributes_dto.CustomAttributesDto.CustomAttribute;
import com.exsited.resources.custom_attributes.custom_attributes_dto.CustomAttributesDto.CustomAttributeList;
import com.exsited.resources.custom_attributes.custom_attributes_dto.CustomAttributesDto;
import java.util.Map;

/** Custom attribute endpoints, under {@code /api/{api_version}/custom_attributes}. */
public final class CustomAttributes implements BoundResource {

    private final ExsitedClient client;

    public CustomAttributes(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List all custom attributes: {@code GET /custom_attributes}. */
    public ApiResponse<CustomAttributeList> getCustomAttributes(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(CustomAttributesUrls.CUSTOM_ATTRIBUTES,
                reopen(options).queryParams(queryParams).build(), CustomAttributeList.class);
    }

    public ApiResponse<CustomAttributeList> getCustomAttributes(
            Map<String, Object> queryParams) {
        return getCustomAttributes(queryParams, null);
    }

    public ApiResponse<CustomAttributeList> getCustomAttributes() {
        return getCustomAttributes(null, null);
    }

    /** Create custom attribute: {@code POST /custom_attributes}. Recorded 201. */
    public ApiResponse<CustomAttributesDto.CustomAttributes> createCustomAttribute(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(CustomAttributesUrls.CUSTOM_ATTRIBUTES,
                reopen(options).jsonBody(payload).build(), CustomAttributesDto.CustomAttributes.class);
    }

    public ApiResponse<CustomAttributesDto.CustomAttributes> createCustomAttribute(
            Object payload) {
        return createCustomAttribute(payload, null);
    }

    /**
     * Delete custom attribute: {@code DELETE /custom_attributes/{custom_attribute_id}}. Recorded
     * 204. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteCustomAttribute(
            String customAttributeId, RequestOptions options) {
        requireValue(customAttributeId, "custom_attribute_id");
        return client.sendDeleteRequest(CustomAttributesUrls.CUSTOM_ATTRIBUTE,
                reopen(options).pathParam("custom_attribute_id", customAttributeId).build(), null);
    }

    public ApiResponse<Dto> deleteCustomAttribute(
            String customAttributeId) {
        return deleteCustomAttribute(customAttributeId, null);
    }

    /** Get custom attribute details: {@code GET /custom_attributes/{custom_attribute_id}}. */
    public ApiResponse<CustomAttribute> getCustomAttribute(
            String customAttributeId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(customAttributeId, "custom_attribute_id");
        return client.sendGetRequest(CustomAttributesUrls.CUSTOM_ATTRIBUTE,
                reopen(options).pathParam("custom_attribute_id", customAttributeId).queryParams(queryParams).build(), CustomAttribute.class);
    }

    public ApiResponse<CustomAttribute> getCustomAttribute(
            String customAttributeId, Map<String, Object> queryParams) {
        return getCustomAttribute(customAttributeId, queryParams, null);
    }

    public ApiResponse<CustomAttribute> getCustomAttribute(String customAttributeId) {
        return getCustomAttribute(customAttributeId, null, null);
    }

    /**
     * Update custom attribute: {@code PATCH /custom_attributes/{custom_attribute_id}}.
     */
    public ApiResponse<CustomAttribute> updateCustomAttribute(
            String customAttributeId, Object payload, RequestOptions options) {
        requireValue(customAttributeId, "custom_attribute_id");
        requirePayload(payload);
        return client.sendPatchRequest(CustomAttributesUrls.CUSTOM_ATTRIBUTE,
                reopen(options).pathParam("custom_attribute_id", customAttributeId).jsonBody(payload).build(), CustomAttribute.class);
    }

    public ApiResponse<CustomAttribute> updateCustomAttribute(
            String customAttributeId, Object payload) {
        return updateCustomAttribute(customAttributeId, payload, null);
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
