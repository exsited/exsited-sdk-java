package com.exsited.resources.custom_components;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.Component;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentByUuid;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentList;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObject;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjectActivity;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjectCustomObjects;
import com.exsited.resources.custom_components.custom_components_dto.CustomComponentsDto.ComponentObjects;
import java.util.Map;

/**
 * Forwards every call to {@link CustomComponents}.
 *
 * @deprecated {@link CustomComponents} is bound to a client itself and is what
 *     {@code client.customComponents()} answers; use it instead.
 */
@Deprecated
public final class CustomComponentsResource implements BoundResource {

    private final CustomComponents customComponents;

    public CustomComponentsResource(ExsitedClient client) {
        this.customComponents = new CustomComponents(client);
    }

    @Override
    public ExsitedClient getClient() {
        return customComponents.getClient();
    }

    public ApiResponse<ComponentList> getComponent(
            Map<String, Object> queryParams, RequestOptions options) {
        return customComponents.getComponent(queryParams, options);
    }

    public ApiResponse<ComponentList> getComponent(Map<String, Object> queryParams) {
        return customComponents.getComponent(queryParams);
    }

    public ApiResponse<Component> getComponentByUuid(
            String componentUuid, Map<String, Object> queryParams, RequestOptions options) {
        return customComponents.getComponentByUuid(componentUuid, queryParams, options);
    }

    public ApiResponse<Component> getComponentByUuid(
            String componentUuid, Map<String, Object> queryParams) {
        return customComponents.getComponentByUuid(componentUuid, queryParams);
    }

    public ApiResponse<ComponentByUuid> createComponentByUuid(
            String componentUuid, Object payload, RequestOptions options) {
        return customComponents.createComponentByUuid(componentUuid, payload, options);
    }

    public ApiResponse<ComponentByUuid> createComponentByUuid(
            String componentUuid, Object payload) {
        return customComponents.createComponentByUuid(componentUuid, payload);
    }

    public ApiResponse<Dto> deleteComponentById(
            String componentUuid, String componentObjectId, RequestOptions options) {
        return customComponents.deleteComponentById(componentUuid, componentObjectId, options);
    }

    public ApiResponse<Dto> deleteComponentById(String componentUuid, String componentObjectId) {
        return customComponents.deleteComponentById(componentUuid, componentObjectId);
    }

    public ApiResponse<ComponentByUuid> updateComponentById(
            String componentUuid, String componentObjectId, Object payload, RequestOptions options) {
        return customComponents.updateComponentById(componentUuid, componentObjectId, payload, options);
    }

    public ApiResponse<ComponentByUuid> updateComponentById(
            String componentUuid, String componentObjectId, Object payload) {
        return customComponents.updateComponentById(componentUuid, componentObjectId, payload);
    }

    public ApiResponse<ComponentObjects> getComponentObjects(
            String componentUuid, Map<String, Object> queryParams, RequestOptions options) {
        return customComponents.getComponentObjects(componentUuid, queryParams, options);
    }

    public ApiResponse<ComponentObjects> getComponentObjects(
            String componentUuid, Map<String, Object> queryParams) {
        return customComponents.getComponentObjects(componentUuid, queryParams);
    }

    public ApiResponse<ComponentObject> getComponentObject(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        return customComponents.getComponentObject(componentUuid, componentObjectId, queryParams, options);
    }

    public ApiResponse<ComponentObject> getComponentObject(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams) {
        return customComponents.getComponentObject(componentUuid, componentObjectId, queryParams);
    }

    public ApiResponse<ComponentObjectActivity> getComponentObjectActivity(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        return customComponents.getComponentObjectActivity(componentUuid, componentObjectId, queryParams, options);
    }

    public ApiResponse<ComponentObjectActivity> getComponentObjectActivity(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams) {
        return customComponents.getComponentObjectActivity(componentUuid, componentObjectId, queryParams);
    }

    public ApiResponse<ComponentObjectCustomObjects> getComponentObjectCustomObjects(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        return customComponents.getComponentObjectCustomObjects(componentUuid, componentObjectId, queryParams, options);
    }

    public ApiResponse<ComponentObjectCustomObjects> getComponentObjectCustomObjects(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams) {
        return customComponents.getComponentObjectCustomObjects(componentUuid, componentObjectId, queryParams);
    }

    public ApiResponse<Dto> getComponentObjectPdf(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams, RequestOptions options) {
        return customComponents.getComponentObjectPdf(componentUuid, componentObjectId, queryParams, options);
    }

    public ApiResponse<Dto> getComponentObjectPdf(
            String componentUuid, String componentObjectId, Map<String, Object> queryParams) {
        return customComponents.getComponentObjectPdf(componentUuid, componentObjectId, queryParams);
    }
}
