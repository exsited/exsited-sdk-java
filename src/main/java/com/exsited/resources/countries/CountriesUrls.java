package com.exsited.resources.countries;

/** Path templates and body keys for the country endpoints. */
public final class CountriesUrls {

    private CountriesUrls() {}

    public static final String SETTINGS_COUNTRIES = "/api/{api_version}/settings/countries";
    public static final String SETTINGS_COUNTRY = "/api/{api_version}/settings/countries/{country_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_COUNTRIES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String COUNTRY_KEY = "country";
}
