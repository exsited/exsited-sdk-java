package com.exsited.resources.user_settings;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.user_settings.user_settings_dto.UserSettingsDto;
import com.exsited.resources.user_settings.user_settings_dto.UserSettingsDto.UserSettingsProfileImage;

/** User settings endpoints, under {@code /api/{api_version}/user_settings}. */
public final class UserSettings implements BoundResource {

    private final ExsitedClient client;

    public UserSettings(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * Read one user's settings: {@code GET /user_settings/{user_id}}, under
     * {@code user_settings}; {@code user_id} is the {@code usr_} id, which carries the same hex as
     * the user's {@code op_} id.
     */
    public ApiResponse<UserSettingsDto.UserSettings> getUserSettings(
            String userId, RequestOptions options) {
        requireValue(userId, "user_id");
        return client.sendGetRequest(UserSettingsUrls.USER_SETTINGS,
                reopen(options).pathParam("user_id", userId).build(), UserSettingsDto.UserSettings.class);
    }

    public ApiResponse<UserSettingsDto.UserSettings> getUserSettings(String userId) {
        return getUserSettings(userId, null);
    }

    /**
     * Read a user's profile image: {@code GET /user_settings/{user_id}/profile_image};
     * {@code base64} is the image without a data URI prefix, and empty when the read has none to
     * serve.
     */
    public ApiResponse<UserSettingsProfileImage> getUserSettingsProfileImage(
            String userId, RequestOptions options) {
        requireValue(userId, "user_id");
        return client.sendGetRequest(UserSettingsUrls.USER_SETTINGS_PROFILE_IMAGE,
                reopen(options).pathParam("user_id", userId).build(), UserSettingsProfileImage.class);
    }

    public ApiResponse<UserSettingsProfileImage> getUserSettingsProfileImage(String userId) {
        return getUserSettingsProfileImage(userId, null);
    }

    private static RequestOptions.Builder reopen(RequestOptions options) {
        RequestOptions given = options == null ? RequestOptions.none() : options;
        return RequestOptions.builder()
                .pathParams(given.getPathParams())
                .queryParams(given.getQueryParams())
                .jsonBody(given.getJsonBody())
                .formData(given.getFormData())
                .files(given.getFiles())
                .headers(given.getHeaders())
                .authenticated(given.isAuthenticated())
                .raiseOnError(given.getRaiseOnError())
                .responseFormat(given.getResponseFormat())
                .dtoFactory(given.getDtoFactory());
    }

    private static void requireValue(String value, String what) {
        if (value == null || value.isBlank()) {
            throw new SdkUsageError(
                    what + " is required: the request cannot be built without it");
        }
    }
}
