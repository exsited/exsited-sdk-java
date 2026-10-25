package com.exsited.resources.organisation;

/** Path templates and body keys for the organisation endpoints. */
public final class OrganisationUrls {

    private OrganisationUrls() {}

    public static final String SETTINGS_ORGANISATION = "/api/{api_version}/settings/organisation";

    /** The envelope one record arrives under. */
    public static final String ORGANIZATION_KEY = "organization";
}
