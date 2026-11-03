package com.exsited.resources.user_settings.user_settings_dto;

import com.exsited.Dto;
import java.util.List;
import java.util.Map;

/** Typed views over the user settings bodies. */
public final class UserSettingsDto {

    private UserSettingsDto() {}

    /** A {@link UserSettings}' {@code profile}. */
    public static final class UserSettingsProfile extends Dto {
        public String display_name;
        public String email;
        public String image_link;
        public String name;
        public Object time_zone;
    }

    /** A {@link UserSettings}' {@code dashboard}. */
    public static final class UserSettingsDashboard extends Dto {
        public Object default_dashboard;
        public Map<String, Object> enabled_dashboards;
        public Boolean use_default;
    }

    /** A {@link UserSettings}' {@code display_settings}. */
    public static final class UserSettingsDisplaySettings extends Dto {
        public List<Object> modules;
        public Boolean use_default;
    }

    /** One user's settings, under {@code user_settings}. */
    public static final class UserSettings extends Dto {
        /** The endpoints these fields belong to. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/user_settings/{user_id}");

        @Override
        protected String envelopeKey() {
            return "user_settings";
        }

        public Object created_by;
        public String created_on;
        public UserSettingsDashboard dashboard;
        public UserSettingsDisplaySettings display_settings;
        public String href;
        public String id;
        public Object last_updated_by;
        public String last_updated_on;
        public String object;
        public UserSettingsProfile profile;
    }

    /** A user's profile image, under {@code user_settings}. */
    public static final class UserSettingsProfileImage extends Dto {
        /** The endpoints these fields belong to. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/user_settings/{user_id}/profile_image");

        @Override
        protected String envelopeKey() {
            return "user_settings";
        }

        public String base64;
    }
}
