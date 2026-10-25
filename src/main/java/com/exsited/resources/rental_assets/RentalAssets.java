package com.exsited.resources.rental_assets;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.rental_assets.rental_assets_dto.RentalAssetsDto.RentalAsset;
import com.exsited.resources.rental_assets.rental_assets_dto.RentalAssetsDto.RentalAssetAvailabilityList;
import com.exsited.resources.rental_assets.rental_assets_dto.RentalAssetsDto.RentalAssetList;
import com.exsited.resources.rental_assets.rental_assets_dto.RentalAssetsDto.RentalAssetProfileList;
import com.exsited.resources.rental_assets.rental_assets_dto.RentalAssetsDto.RentalAssetProfileView;
import java.util.Map;

/** Rental asset endpoints, under {@code /api/{api_version}}. */
public final class RentalAssets implements BoundResource {

    private final ExsitedClient client;

    public RentalAssets(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** Rental asset availability: {@code GET /rental_asset_availability}. */
    public ApiResponse<RentalAssetAvailabilityList> getRentalAssetAvailability(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(RentalAssetsUrls.RENTAL_ASSET_AVAILABILITY,
                reopen(options).queryParams(queryParams).build(), RentalAssetAvailabilityList.class);
    }

    public ApiResponse<RentalAssetAvailabilityList> getRentalAssetAvailability(
            Map<String, Object> queryParams) {
        return getRentalAssetAvailability(queryParams, null);
    }

    public ApiResponse<RentalAssetAvailabilityList> getRentalAssetAvailability() {
        return getRentalAssetAvailability(null, null);
    }

    /**
     * Update rental asset availability: {@code PATCH /rental_asset_availability}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> updateRentalAssetAvailability(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(RentalAssetsUrls.RENTAL_ASSET_AVAILABILITY,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateRentalAssetAvailability(
            Object payload) {
        return updateRentalAssetAvailability(payload, null);
    }

    /** List rental asset profiles: {@code GET /rental_asset_profiles}. */
    public ApiResponse<RentalAssetProfileList> getRentalAssetProfiles(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(RentalAssetsUrls.RENTAL_ASSET_PROFILES,
                reopen(options).queryParams(queryParams).build(), RentalAssetProfileList.class);
    }

    public ApiResponse<RentalAssetProfileList> getRentalAssetProfiles(
            Map<String, Object> queryParams) {
        return getRentalAssetProfiles(queryParams, null);
    }

    public ApiResponse<RentalAssetProfileList> getRentalAssetProfiles() {
        return getRentalAssetProfiles(null, null);
    }

    /**
     * Create rental asset profile: {@code POST /rental_asset_profiles}.
     */
    public ApiResponse<RentalAssetProfileView> createRentalAssetProfile(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(RentalAssetsUrls.RENTAL_ASSET_PROFILES,
                reopen(options).jsonBody(payload).build(), RentalAssetProfileView.class);
    }

    public ApiResponse<RentalAssetProfileView> createRentalAssetProfile(
            Object payload) {
        return createRentalAssetProfile(payload, null);
    }

    /** List rental assets: {@code GET /rental_assets}. */
    public ApiResponse<RentalAssetList> getRentalAssets(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(RentalAssetsUrls.RENTAL_ASSETS,
                reopen(options).queryParams(queryParams).build(), RentalAssetList.class);
    }

    public ApiResponse<RentalAssetList> getRentalAssets(
            Map<String, Object> queryParams) {
        return getRentalAssets(queryParams, null);
    }

    public ApiResponse<RentalAssetList> getRentalAssets() {
        return getRentalAssets(null, null);
    }

    /**
     * Create rental asset: {@code POST /rental_assets}.
     */
    public ApiResponse<RentalAsset> createRentalAsset(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(RentalAssetsUrls.RENTAL_ASSETS,
                reopen(options).jsonBody(payload).build(), RentalAsset.class);
    }

    public ApiResponse<RentalAsset> createRentalAsset(
            Object payload) {
        return createRentalAsset(payload, null);
    }

    /**
     * Get rental asset profile:
     * {@code GET /rental_asset_profiles/{rental_asset_profile_id}}.
     */
    public ApiResponse<RentalAssetProfileView> getRentalAssetProfile(
            String rentalAssetProfileId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(rentalAssetProfileId, "rental_asset_profile_id");
        return client.sendGetRequest(RentalAssetsUrls.RENTAL_ASSET_PROFILE,
                reopen(options).pathParam("rental_asset_profile_id", rentalAssetProfileId).queryParams(queryParams).build(), RentalAssetProfileView.class);
    }

    public ApiResponse<RentalAssetProfileView> getRentalAssetProfile(
            String rentalAssetProfileId, Map<String, Object> queryParams) {
        return getRentalAssetProfile(rentalAssetProfileId, queryParams, null);
    }

    public ApiResponse<RentalAssetProfileView> getRentalAssetProfile(String rentalAssetProfileId) {
        return getRentalAssetProfile(rentalAssetProfileId, null, null);
    }

    /**
     * Update rental asset profile: {@code PATCH /rental_asset_profiles/{rental_asset_profile_id}}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> updateRentalAssetProfile(
            String rentalAssetProfileId, Object payload, RequestOptions options) {
        requireValue(rentalAssetProfileId, "rental_asset_profile_id");
        requirePayload(payload);
        return client.sendPatchRequest(RentalAssetsUrls.RENTAL_ASSET_PROFILE,
                reopen(options).pathParam("rental_asset_profile_id", rentalAssetProfileId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateRentalAssetProfile(
            String rentalAssetProfileId, Object payload) {
        return updateRentalAssetProfile(rentalAssetProfileId, payload, null);
    }

    /**
     * Delete rental asset profile: {@code DELETE /rental_asset_profiles/{rental_asset_profile_id}}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteRentalAssetProfile(
            String rentalAssetProfileId, RequestOptions options) {
        requireValue(rentalAssetProfileId, "rental_asset_profile_id");
        return client.sendDeleteRequest(RentalAssetsUrls.RENTAL_ASSET_PROFILE,
                reopen(options).pathParam("rental_asset_profile_id", rentalAssetProfileId).build(), null);
    }

    public ApiResponse<Dto> deleteRentalAssetProfile(
            String rentalAssetProfileId) {
        return deleteRentalAssetProfile(rentalAssetProfileId, null);
    }

    /**
     * Update rental asset: {@code PATCH /rental_assets/{rental_asset_uuid}}.
     */
    public ApiResponse<RentalAsset> updateRentalAsset(
            String rentalAssetUuid, Object payload, RequestOptions options) {
        requireValue(rentalAssetUuid, "rental_asset_uuid");
        requirePayload(payload);
        return client.sendPatchRequest(RentalAssetsUrls.RENTAL_ASSET,
                reopen(options).pathParam("rental_asset_uuid", rentalAssetUuid).jsonBody(payload).build(), RentalAsset.class);
    }

    public ApiResponse<RentalAsset> updateRentalAsset(
            String rentalAssetUuid, Object payload) {
        return updateRentalAsset(rentalAssetUuid, payload, null);
    }

    /**
     * Delete rental asset: {@code DELETE /rental_assets/{rental_asset_uuid}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteRentalAsset(
            String rentalAssetUuid, RequestOptions options) {
        requireValue(rentalAssetUuid, "rental_asset_uuid");
        return client.sendDeleteRequest(RentalAssetsUrls.RENTAL_ASSET,
                reopen(options).pathParam("rental_asset_uuid", rentalAssetUuid).build(), null);
    }

    public ApiResponse<Dto> deleteRentalAsset(
            String rentalAssetUuid) {
        return deleteRentalAsset(rentalAssetUuid, null);
    }

    /** Get rental asset: {@code GET /rental_assets/{rental_asset_uuid}}. */
    public ApiResponse<RentalAsset> getRentalAsset(
            String rentalAssetUuid, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(rentalAssetUuid, "rental_asset_uuid");
        return client.sendGetRequest(RentalAssetsUrls.RENTAL_ASSET,
                reopen(options).pathParam("rental_asset_uuid", rentalAssetUuid).queryParams(queryParams).build(), RentalAsset.class);
    }

    public ApiResponse<RentalAsset> getRentalAsset(
            String rentalAssetUuid, Map<String, Object> queryParams) {
        return getRentalAsset(rentalAssetUuid, queryParams, null);
    }

    public ApiResponse<RentalAsset> getRentalAsset(String rentalAssetUuid) {
        return getRentalAsset(rentalAssetUuid, null, null);
    }

    /**
     * Cancel rental asset: {@code POST /rental_assets/{rental_asset_id}/cancel}.
     */
    public ApiResponse<RentalAsset> cancelRentalAsset(
            String rentalAssetId, Object payload, RequestOptions options) {
        requireValue(rentalAssetId, "rental_asset_id");
        requirePayload(payload);
        return client.sendPostRequest(RentalAssetsUrls.RENTAL_ASSET_CANCEL,
                reopen(options).pathParam("rental_asset_id", rentalAssetId).jsonBody(payload).build(), RentalAsset.class);
    }

    public ApiResponse<RentalAsset> cancelRentalAsset(
            String rentalAssetId, Object payload) {
        return cancelRentalAsset(rentalAssetId, payload, null);
    }

    /**
     * Reactivate rental asset: {@code POST /rental_assets/{rental_asset_id}/reactivate}.
     */
    public ApiResponse<RentalAsset> reactivateRentalAsset(
            String rentalAssetId, Object payload, RequestOptions options) {
        requireValue(rentalAssetId, "rental_asset_id");
        requirePayload(payload);
        return client.sendPostRequest(RentalAssetsUrls.RENTAL_ASSET_REACTIVATE,
                reopen(options).pathParam("rental_asset_id", rentalAssetId).jsonBody(payload).build(), RentalAsset.class);
    }

    public ApiResponse<RentalAsset> reactivateRentalAsset(
            String rentalAssetId, Object payload) {
        return reactivateRentalAsset(rentalAssetId, payload, null);
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
