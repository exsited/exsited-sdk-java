package com.exsited.resources.shipping_packages;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.shipping_packages.shipping_packages_dto.ShippingPackagesDto.SettingsShippingPackage;
import com.exsited.resources.shipping_packages.shipping_packages_dto.ShippingPackagesDto.SettingsShippingPackageList;
import java.util.Map;

/** Shipping package endpoints, under {@code /api/{api_version}/settings/shipping_packages}. */
public final class ShippingPackages implements BoundResource {

    private final ExsitedClient client;

    public ShippingPackages(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List shipping packages: {@code GET /settings/shipping_packages}. */
    public ApiResponse<SettingsShippingPackageList> getSettingsShippingPackages(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ShippingPackagesUrls.SETTINGS_SHIPPING_PACKAGES,
                reopen(options).queryParams(queryParams).build(), SettingsShippingPackageList.class);
    }

    public ApiResponse<SettingsShippingPackageList> getSettingsShippingPackages(
            Map<String, Object> queryParams) {
        return getSettingsShippingPackages(queryParams, null);
    }

    public ApiResponse<SettingsShippingPackageList> getSettingsShippingPackages() {
        return getSettingsShippingPackages(null, null);
    }

    /**
     * Create a shipping package: {@code POST /settings/shipping_packages}; a {@code *_uom} is the
     * unit's bare uuid, not its prefixed id.
     */
    public ApiResponse<SettingsShippingPackage> createSettingsShippingPackage(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ShippingPackagesUrls.SETTINGS_SHIPPING_PACKAGES,
                reopen(options).jsonBody(payload).build(), SettingsShippingPackage.class);
    }

    public ApiResponse<SettingsShippingPackage> createSettingsShippingPackage(Object payload) {
        return createSettingsShippingPackage(payload, null);
    }

    /** Get one shipping package: {@code GET /settings/shipping_packages/{shipping_package_id}}. */
    public ApiResponse<SettingsShippingPackage> getSettingsShippingPackage(
            String shippingPackageId, RequestOptions options) {
        requireValue(shippingPackageId, "shipping_package_id");
        return client.sendGetRequest(ShippingPackagesUrls.SETTINGS_SHIPPING_PACKAGE,
                reopen(options).pathParam("shipping_package_id", shippingPackageId).build(), SettingsShippingPackage.class);
    }

    public ApiResponse<SettingsShippingPackage> getSettingsShippingPackage(String shippingPackageId) {
        return getSettingsShippingPackage(shippingPackageId, null);
    }

    /**
     * Delete a shipping package: {@code DELETE /settings/shipping_packages/{shipping_package_id}}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsShippingPackage(
            String shippingPackageId, RequestOptions options) {
        requireValue(shippingPackageId, "shipping_package_id");
        return client.sendDeleteRequest(ShippingPackagesUrls.SETTINGS_SHIPPING_PACKAGE,
                reopen(options).pathParam("shipping_package_id", shippingPackageId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsShippingPackage(String shippingPackageId) {
        return deleteSettingsShippingPackage(shippingPackageId, null);
    }

    /**
     * Update a shipping package: {@code PATCH /settings/shipping_packages/{shipping_package_id}}.
     */
    public ApiResponse<SettingsShippingPackage> updateSettingsShippingPackage(
            String shippingPackageId, Object payload, RequestOptions options) {
        requireValue(shippingPackageId, "shipping_package_id");
        requirePayload(payload);
        return client.sendPatchRequest(ShippingPackagesUrls.SETTINGS_SHIPPING_PACKAGE,
                reopen(options).pathParam("shipping_package_id", shippingPackageId).jsonBody(payload).build(), SettingsShippingPackage.class);
    }

    public ApiResponse<SettingsShippingPackage> updateSettingsShippingPackage(
            String shippingPackageId, Object payload) {
        return updateSettingsShippingPackage(shippingPackageId, payload, null);
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
