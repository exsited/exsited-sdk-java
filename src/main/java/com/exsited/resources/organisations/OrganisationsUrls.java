package com.exsited.resources.organisations;

/** Path templates and body keys for the organisation endpoints. */
public final class OrganisationsUrls {

    private OrganisationsUrls() {}

    public static final String SETTINGS_ORGANISATIONS = "/api/{api_version}/settings/organisations";
    public static final String SETTINGS_ORGANISATION = "/api/{api_version}/settings/organisations/{organisation_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_ORGANISATIONS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ORGANIZATION_KEY = "organization";
}
