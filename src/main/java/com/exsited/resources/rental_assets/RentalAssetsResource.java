package com.exsited.resources.rental_assets;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.rental_assets.rental_assets_dto.RentalAssetsDto.RentalAsset;
import com.exsited.resources.rental_assets.rental_assets_dto.RentalAssetsDto.RentalAssetAvailabilityList;
import com.exsited.resources.rental_assets.rental_assets_dto.RentalAssetsDto.RentalAssetList;
import com.exsited.resources.rental_assets.rental_assets_dto.RentalAssetsDto.RentalAssetProfileList;
import com.exsited.resources.rental_assets.rental_assets_dto.RentalAssetsDto.RentalAssetProfileView;
import java.util.Map;

/**
 * Forwards every call to {@link RentalAssets}.
 *
 * @deprecated {@link RentalAssets} is bound to a client itself and is what
 *     {@code client.rentalAssets()} answers; use it instead.
 */
@Deprecated
public final class RentalAssetsResource implements BoundResource {

    private final RentalAssets rentalAssets;

    public RentalAssetsResource(ExsitedClient client) {
        this.rentalAssets = new RentalAssets(client);
    }

    @Override
    public ExsitedClient getClient() {
        return rentalAssets.getClient();
    }

    public ApiResponse<RentalAssetAvailabilityList> getRentalAssetAvailability(
            Map<String, Object> queryParams, RequestOptions options) {
        return rentalAssets.getRentalAssetAvailability(queryParams, options);
    }

    public ApiResponse<RentalAssetAvailabilityList> getRentalAssetAvailability(
            Map<String, Object> queryParams) {
        return rentalAssets.getRentalAssetAvailability(queryParams);
    }

    public ApiResponse<Dto> updateRentalAssetAvailability(Object payload, RequestOptions options) {
        return rentalAssets.updateRentalAssetAvailability(payload, options);
    }

    public ApiResponse<Dto> updateRentalAssetAvailability(Object payload) {
        return rentalAssets.updateRentalAssetAvailability(payload);
    }

    public ApiResponse<RentalAssetProfileList> getRentalAssetProfiles(
            Map<String, Object> queryParams, RequestOptions options) {
        return rentalAssets.getRentalAssetProfiles(queryParams, options);
    }

    public ApiResponse<RentalAssetProfileList> getRentalAssetProfiles(
            Map<String, Object> queryParams) {
        return rentalAssets.getRentalAssetProfiles(queryParams);
    }

    public ApiResponse<RentalAssetProfileView> createRentalAssetProfile(Object payload, RequestOptions options) {
        return rentalAssets.createRentalAssetProfile(payload, options);
    }

    public ApiResponse<RentalAssetProfileView> createRentalAssetProfile(Object payload) {
        return rentalAssets.createRentalAssetProfile(payload);
    }

    public ApiResponse<RentalAssetList> getRentalAssets(
            Map<String, Object> queryParams, RequestOptions options) {
        return rentalAssets.getRentalAssets(queryParams, options);
    }

    public ApiResponse<RentalAssetList> getRentalAssets(Map<String, Object> queryParams) {
        return rentalAssets.getRentalAssets(queryParams);
    }

    public ApiResponse<RentalAsset> createRentalAsset(Object payload, RequestOptions options) {
        return rentalAssets.createRentalAsset(payload, options);
    }

    public ApiResponse<RentalAsset> createRentalAsset(Object payload) {
        return rentalAssets.createRentalAsset(payload);
    }

    public ApiResponse<RentalAssetProfileView> getRentalAssetProfile(
            String rentalAssetProfileId, Map<String, Object> queryParams, RequestOptions options) {
        return rentalAssets.getRentalAssetProfile(rentalAssetProfileId, queryParams, options);
    }

    public ApiResponse<RentalAssetProfileView> getRentalAssetProfile(
            String rentalAssetProfileId, Map<String, Object> queryParams) {
        return rentalAssets.getRentalAssetProfile(rentalAssetProfileId, queryParams);
    }

    public ApiResponse<Dto> updateRentalAssetProfile(
            String rentalAssetProfileId, Object payload, RequestOptions options) {
        return rentalAssets.updateRentalAssetProfile(rentalAssetProfileId, payload, options);
    }

    public ApiResponse<Dto> updateRentalAssetProfile(String rentalAssetProfileId, Object payload) {
        return rentalAssets.updateRentalAssetProfile(rentalAssetProfileId, payload);
    }

    public ApiResponse<Dto> deleteRentalAssetProfile(
            String rentalAssetProfileId, RequestOptions options) {
        return rentalAssets.deleteRentalAssetProfile(rentalAssetProfileId, options);
    }

    public ApiResponse<Dto> deleteRentalAssetProfile(String rentalAssetProfileId) {
        return rentalAssets.deleteRentalAssetProfile(rentalAssetProfileId);
    }

    public ApiResponse<RentalAsset> updateRentalAsset(
            String rentalAssetUuid, Object payload, RequestOptions options) {
        return rentalAssets.updateRentalAsset(rentalAssetUuid, payload, options);
    }

    public ApiResponse<RentalAsset> updateRentalAsset(String rentalAssetUuid, Object payload) {
        return rentalAssets.updateRentalAsset(rentalAssetUuid, payload);
    }

    public ApiResponse<Dto> deleteRentalAsset(String rentalAssetUuid, RequestOptions options) {
        return rentalAssets.deleteRentalAsset(rentalAssetUuid, options);
    }

    public ApiResponse<Dto> deleteRentalAsset(String rentalAssetUuid) {
        return rentalAssets.deleteRentalAsset(rentalAssetUuid);
    }

    public ApiResponse<RentalAsset> getRentalAsset(
            String rentalAssetUuid, Map<String, Object> queryParams, RequestOptions options) {
        return rentalAssets.getRentalAsset(rentalAssetUuid, queryParams, options);
    }

    public ApiResponse<RentalAsset> getRentalAsset(
            String rentalAssetUuid, Map<String, Object> queryParams) {
        return rentalAssets.getRentalAsset(rentalAssetUuid, queryParams);
    }

    public ApiResponse<RentalAsset> cancelRentalAsset(
            String rentalAssetId, Object payload, RequestOptions options) {
        return rentalAssets.cancelRentalAsset(rentalAssetId, payload, options);
    }

    public ApiResponse<RentalAsset> cancelRentalAsset(String rentalAssetId, Object payload) {
        return rentalAssets.cancelRentalAsset(rentalAssetId, payload);
    }

    public ApiResponse<RentalAsset> reactivateRentalAsset(
            String rentalAssetId, Object payload, RequestOptions options) {
        return rentalAssets.reactivateRentalAsset(rentalAssetId, payload, options);
    }

    public ApiResponse<RentalAsset> reactivateRentalAsset(String rentalAssetId, Object payload) {
        return rentalAssets.reactivateRentalAsset(rentalAssetId, payload);
    }
}
