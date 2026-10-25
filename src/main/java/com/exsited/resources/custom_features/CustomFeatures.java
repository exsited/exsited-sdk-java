package com.exsited.resources.custom_features;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.custom_features.custom_features_dto.CustomFeaturesDto.CustomFeature;
import com.exsited.resources.custom_features.custom_features_dto.CustomFeaturesDto.CustomFeatureList;
import java.util.Map;

/** Custom feature endpoints, under {@code /api/{api_version}}. */
public final class CustomFeatures implements BoundResource {

    private final ExsitedClient client;

    public CustomFeatures(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** Create custom feature: {@code POST /custom_feature}. Recorded 201. */
    public ApiResponse<CustomFeature> createCustomFeature(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(CustomFeaturesUrls.CUSTOM_FEATURE,
                reopen(options).jsonBody(payload).build(), CustomFeature.class);
    }

    public ApiResponse<CustomFeature> createCustomFeature(
            Object payload) {
        return createCustomFeature(payload, null);
    }

    /**
     * Delete custom feature: {@code DELETE /component/{custom_feature_id}}. Recorded 204. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteComponentById(
            String customFeatureId, RequestOptions options) {
        requireValue(customFeatureId, "custom_feature_id");
        return client.sendDeleteRequest(CustomFeaturesUrls.COMPONENT_BY_ID,
                reopen(options).pathParam("custom_feature_id", customFeatureId).build(), null);
    }

    public ApiResponse<Dto> deleteComponentById(
            String customFeatureId) {
        return deleteComponentById(customFeatureId, null);
    }

    /** Update custom feature: {@code PATCH /custom_feature/{custom_feature_id}}. Recorded 202. */
    public ApiResponse<CustomFeature> updateCustomFeatureById(
            String customFeatureId, Object payload, RequestOptions options) {
        requireValue(customFeatureId, "custom_feature_id");
        requirePayload(payload);
        return client.sendPatchRequest(CustomFeaturesUrls.CUSTOM_FEATURE_BY_ID,
                reopen(options).pathParam("custom_feature_id", customFeatureId).jsonBody(payload).build(), CustomFeature.class);
    }

    public ApiResponse<CustomFeature> updateCustomFeatureById(
            String customFeatureId, Object payload) {
        return updateCustomFeatureById(customFeatureId, payload, null);
    }

    /**
     * Customize form layout and attributes:
     * {@code POST /custom_feature/{custom_feature_id}}. Recorded 201.
     */
    public ApiResponse<CustomFeature> createCustomFeatureById(
            String customFeatureId, Object payload, RequestOptions options) {
        requireValue(customFeatureId, "custom_feature_id");
        requirePayload(payload);
        return client.sendPostRequest(CustomFeaturesUrls.CUSTOM_FEATURE_BY_ID,
                reopen(options).pathParam("custom_feature_id", customFeatureId).jsonBody(payload).build(), CustomFeature.class);
    }

    public ApiResponse<CustomFeature> createCustomFeatureById(
            String customFeatureId, Object payload) {
        return createCustomFeatureById(customFeatureId, payload, null);
    }

    /** List custom features: {@code GET /custom_feature}, cursor paged, the active ones only. */
    public ApiResponse<CustomFeatureList> getCustomFeatures(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(CustomFeaturesUrls.CUSTOM_FEATURE,
                reopen(options).queryParams(queryParams).build(), CustomFeatureList.class);
    }

    public ApiResponse<CustomFeatureList> getCustomFeatures(Map<String, Object> queryParams) {
        return getCustomFeatures(queryParams, null);
    }

    public ApiResponse<CustomFeatureList> getCustomFeatures() {
        return getCustomFeatures(null, null);
    }

    /**
     * Retrieve custom feature: {@code GET /custom_feature/{custom_feature_id}}, the {@code cmp_}
     * id, under {@code custom_feature}; a deleted one is {@code 404}.
     */
    public ApiResponse<CustomFeature> getCustomFeatureById(
            String customFeatureId, RequestOptions options) {
        requireValue(customFeatureId, "custom_feature_id");
        return client.sendGetRequest(CustomFeaturesUrls.CUSTOM_FEATURE_BY_ID,
                reopen(options).pathParam("custom_feature_id", customFeatureId).build(), CustomFeature.class);
    }

    public ApiResponse<CustomFeature> getCustomFeatureById(String customFeatureId) {
        return getCustomFeatureById(customFeatureId, null);
    }

    /**
     * Delete custom feature: {@code DELETE /custom_feature/{custom_feature_id}}, the {@code cmp_}
     * id; answered {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteCustomFeatureById(
            String customFeatureId, RequestOptions options) {
        requireValue(customFeatureId, "custom_feature_id");
        return client.sendDeleteRequest(CustomFeaturesUrls.CUSTOM_FEATURE_BY_ID,
                reopen(options).pathParam("custom_feature_id", customFeatureId).build(), null);
    }

    public ApiResponse<Dto> deleteCustomFeatureById(String customFeatureId) {
        return deleteCustomFeatureById(customFeatureId, null);
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
