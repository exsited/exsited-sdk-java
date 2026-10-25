package com.exsited.resources.custom_attributes;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.custom_attributes.custom_attributes_dto.CustomAttributesDto.CustomAttribute;
import com.exsited.resources.custom_attributes.custom_attributes_dto.CustomAttributesDto.CustomAttributeList;
import com.exsited.resources.custom_attributes.custom_attributes_dto.CustomAttributesDto;
import java.util.Map;

/**
 * Forwards every call to {@link CustomAttributes}.
 *
 * @deprecated {@link CustomAttributes} is bound to a client itself and is what
 *     {@code client.customAttributes()} answers; use it instead.
 */
@Deprecated
public final class CustomAttributesResource implements BoundResource {

    private final CustomAttributes customAttributes;

    public CustomAttributesResource(ExsitedClient client) {
        this.customAttributes = new CustomAttributes(client);
    }

    @Override
    public ExsitedClient getClient() {
        return customAttributes.getClient();
    }

    public ApiResponse<CustomAttributeList> getCustomAttributes(
            Map<String, Object> queryParams, RequestOptions options) {
        return customAttributes.getCustomAttributes(queryParams, options);
    }

    public ApiResponse<CustomAttributeList> getCustomAttributes(Map<String, Object> queryParams) {
        return customAttributes.getCustomAttributes(queryParams);
    }

    public ApiResponse<CustomAttributesDto.CustomAttributes> createCustomAttribute(
            Object payload, RequestOptions options) {
        return customAttributes.createCustomAttribute(payload, options);
    }

    public ApiResponse<CustomAttributesDto.CustomAttributes> createCustomAttribute(Object payload) {
        return customAttributes.createCustomAttribute(payload);
    }

    public ApiResponse<Dto> deleteCustomAttribute(
            String customAttributeId, RequestOptions options) {
        return customAttributes.deleteCustomAttribute(customAttributeId, options);
    }

    public ApiResponse<Dto> deleteCustomAttribute(String customAttributeId) {
        return customAttributes.deleteCustomAttribute(customAttributeId);
    }

    public ApiResponse<CustomAttribute> getCustomAttribute(
            String customAttributeId, Map<String, Object> queryParams, RequestOptions options) {
        return customAttributes.getCustomAttribute(customAttributeId, queryParams, options);
    }

    public ApiResponse<CustomAttribute> getCustomAttribute(
            String customAttributeId, Map<String, Object> queryParams) {
        return customAttributes.getCustomAttribute(customAttributeId, queryParams);
    }

    public ApiResponse<CustomAttribute> updateCustomAttribute(
            String customAttributeId, Object payload, RequestOptions options) {
        return customAttributes.updateCustomAttribute(customAttributeId, payload, options);
    }

    public ApiResponse<CustomAttribute> updateCustomAttribute(String customAttributeId, Object payload) {
        return customAttributes.updateCustomAttribute(customAttributeId, payload);
    }
}
