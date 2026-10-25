package com.exsited.resources.action_scripts;

/** Path templates and body keys for the action script endpoints. */
public final class ActionScriptsUrls {

    private ActionScriptsUrls() {}

    public static final String ACTION_SCRIPTS = "/api/{api_version}/action_scripts";
    public static final String ACTION_SCRIPT = "/api/{api_version}/action_scripts/{action_script_id}";

    /** The key a listing's records sit under. */
    public static final String ACTION_SCRIPTS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ACTION_SCRIPT_KEY = "action_script";
}
