package com.exsited.resources.shipping_packages;

/** Path templates and body keys for the shipping package endpoints. */
public final class ShippingPackagesUrls {

    private ShippingPackagesUrls() {}

    public static final String SETTINGS_SHIPPING_PACKAGES = "/api/{api_version}/settings/shipping_packages";
    public static final String SETTINGS_SHIPPING_PACKAGE = "/api/{api_version}/settings/shipping_packages/{shipping_package_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_SHIPPING_PACKAGES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String SHIPPING_PACKAGE_KEY = "shipping_package";
}
