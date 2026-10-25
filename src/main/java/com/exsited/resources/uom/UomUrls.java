package com.exsited.resources.uom;

/** Path templates and body keys for the unit of measurement endpoints. */
public final class UomUrls {

    private UomUrls() {}

    public static final String SETTINGS_UOMS = "/api/{api_version}/settings/uom";
    public static final String SETTINGS_UOM = "/api/{api_version}/settings/uom/{uom_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_UOMS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String UOM_KEY = "uom";
}
