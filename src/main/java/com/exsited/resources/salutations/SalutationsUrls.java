package com.exsited.resources.salutations;

/** Path templates and body keys for the salutation endpoints. */
public final class SalutationsUrls {

    private SalutationsUrls() {}

    public static final String SETTINGS_SALUTATIONS = "/api/{api_version}/settings/salutations";
    public static final String SETTINGS_SALUTATION = "/api/{api_version}/settings/salutations/{salutation_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_SALUTATIONS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String SALUTATION_KEY = "salutation";
}
