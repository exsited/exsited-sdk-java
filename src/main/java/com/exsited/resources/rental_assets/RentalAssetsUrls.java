package com.exsited.resources.rental_assets;

/** Path templates and body keys for the rental asset endpoints. */
public final class RentalAssetsUrls {

    private RentalAssetsUrls() {}

    public static final String RENTAL_ASSET_AVAILABILITY = "/api/{api_version}/rental_asset_availability";
    public static final String RENTAL_ASSET_PROFILES = "/api/{api_version}/rental_asset_profiles";
    public static final String RENTAL_ASSETS = "/api/{api_version}/rental_assets";
    public static final String RENTAL_ASSET_PROFILE = "/api/{api_version}/rental_asset_profiles/{rental_asset_profile_id}";
    public static final String RENTAL_ASSET = "/api/{api_version}/rental_assets/{rental_asset_uuid}";
    public static final String RENTAL_ASSET_CANCEL = "/api/{api_version}/rental_assets/{rental_asset_id}/cancel";
    public static final String RENTAL_ASSET_REACTIVATE = "/api/{api_version}/rental_assets/{rental_asset_id}/reactivate";

    /** The key a listing's records sit under. */
    public static final String RENTAL_ASSETS_KEY = "data";
    public static final String RENTAL_ASSET_AVAILABILITY_KEY = "data";
    public static final String RENTAL_ASSET_PROFILES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String RENTAL_ASSET_KEY = "rental_asset";
    public static final String RENTAL_ASSET_PROFILE_KEY = "rental_asset_profile";
}
