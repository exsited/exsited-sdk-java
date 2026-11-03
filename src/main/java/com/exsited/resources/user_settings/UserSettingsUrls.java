package com.exsited.resources.user_settings;

/**
 * Path templates and body keys for the user settings endpoints. {@code user_id} is the
 * {@code usr_} id, which carries the hex of the user's {@code op_} operator id.
 */
public final class UserSettingsUrls {

    private UserSettingsUrls() {}

    public static final String USER_SETTINGS = "/api/{api_version}/user_settings/{user_id}";
    public static final String USER_SETTINGS_PROFILE_IMAGE =
            "/api/{api_version}/user_settings/{user_id}/profile_image";

    /** The envelope one record arrives under. */
    public static final String USER_SETTINGS_KEY = "user_settings";
}
