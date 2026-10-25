package com.exsited.resources.zones;

/** Path templates and body keys for the zone endpoints. */
public final class ZonesUrls {

    private ZonesUrls() {}

    public static final String SETTINGS_ZONES = "/api/{api_version}/settings/zones";
    public static final String SETTINGS_ZONE = "/api/{api_version}/settings/zones/{zone_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_ZONES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ZONE_KEY = "zone";
}
