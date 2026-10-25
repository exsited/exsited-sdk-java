package com.exsited.resources.custom_features;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.custom_features.custom_features_dto.CustomFeaturesDto.CustomFeature;

/**
 * Forwards every call to {@link CustomFeatures}.
 *
 * @deprecated {@link CustomFeatures} is bound to a client itself and is what
 *     {@code client.customFeatures()} answers; use it instead.
 */
@Deprecated
public final class CustomFeaturesResource implements BoundResource {

    private final CustomFeatures customFeatures;

    public CustomFeaturesResource(ExsitedClient client) {
        this.customFeatures = new CustomFeatures(client);
    }

    @Override
    public ExsitedClient getClient() {
        return customFeatures.getClient();
    }

    public ApiResponse<CustomFeature> createCustomFeature(Object payload, RequestOptions options) {
        return customFeatures.createCustomFeature(payload, options);
    }

    public ApiResponse<CustomFeature> createCustomFeature(Object payload) {
        return customFeatures.createCustomFeature(payload);
    }

    public ApiResponse<Dto> deleteComponentById(String customFeatureId, RequestOptions options) {
        return customFeatures.deleteComponentById(customFeatureId, options);
    }

    public ApiResponse<Dto> deleteComponentById(String customFeatureId) {
        return customFeatures.deleteComponentById(customFeatureId);
    }

    public ApiResponse<CustomFeature> updateCustomFeatureById(
            String customFeatureId, Object payload, RequestOptions options) {
        return customFeatures.updateCustomFeatureById(customFeatureId, payload, options);
    }

    public ApiResponse<CustomFeature> updateCustomFeatureById(
            String customFeatureId, Object payload) {
        return customFeatures.updateCustomFeatureById(customFeatureId, payload);
    }

    public ApiResponse<CustomFeature> createCustomFeatureById(
            String customFeatureId, Object payload, RequestOptions options) {
        return customFeatures.createCustomFeatureById(customFeatureId, payload, options);
    }

    public ApiResponse<CustomFeature> createCustomFeatureById(
            String customFeatureId, Object payload) {
        return customFeatures.createCustomFeatureById(customFeatureId, payload);
    }
}
