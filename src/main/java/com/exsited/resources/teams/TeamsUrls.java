package com.exsited.resources.teams;

/** Path templates and body keys for the team endpoints. */
public final class TeamsUrls {

    private TeamsUrls() {}

    public static final String SETTINGS_TEAMS = "/api/{api_version}/settings/teams";
    public static final String SETTINGS_TEAM = "/api/{api_version}/settings/teams/{team_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_TEAMS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String TEAM_KEY = "team";
}
