package com.exsited.resources.teams;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.teams.teams_dto.TeamsDto.SettingsTeam;
import com.exsited.resources.teams.teams_dto.TeamsDto.SettingsTeamList;
import java.util.Map;

/** Team endpoints, under {@code /api/{api_version}/settings/teams}. */
public final class Teams implements BoundResource {

    private final ExsitedClient client;

    public Teams(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List teams: {@code GET /settings/teams}. */
    public ApiResponse<SettingsTeamList> getSettingsTeams(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(TeamsUrls.SETTINGS_TEAMS,
                reopen(options).queryParams(queryParams).build(), SettingsTeamList.class);
    }

    public ApiResponse<SettingsTeamList> getSettingsTeams(Map<String, Object> queryParams) {
        return getSettingsTeams(queryParams, null);
    }

    public ApiResponse<SettingsTeamList> getSettingsTeams() {
        return getSettingsTeams(null, null);
    }

    /** Create a team: {@code POST /settings/teams}. */
    public ApiResponse<SettingsTeam> createSettingsTeam(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(TeamsUrls.SETTINGS_TEAMS,
                reopen(options).jsonBody(payload).build(), SettingsTeam.class);
    }

    public ApiResponse<SettingsTeam> createSettingsTeam(Object payload) {
        return createSettingsTeam(payload, null);
    }

    /** Get one team: {@code GET /settings/teams/{team_id}}. */
    public ApiResponse<SettingsTeam> getSettingsTeam(
            String teamId, RequestOptions options) {
        requireValue(teamId, "team_id");
        return client.sendGetRequest(TeamsUrls.SETTINGS_TEAM,
                reopen(options).pathParam("team_id", teamId).build(), SettingsTeam.class);
    }

    public ApiResponse<SettingsTeam> getSettingsTeam(String teamId) {
        return getSettingsTeam(teamId, null);
    }

    /**
     * Delete a team: {@code DELETE /settings/teams/{team_id}}; refused while the team has members.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsTeam(
            String teamId, RequestOptions options) {
        requireValue(teamId, "team_id");
        return client.sendDeleteRequest(TeamsUrls.SETTINGS_TEAM,
                reopen(options).pathParam("team_id", teamId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsTeam(String teamId) {
        return deleteSettingsTeam(teamId, null);
    }

    /**
     * Update or enable/disable a team: {@code PATCH /settings/teams/{team_id}}; {@code status}
     * takes active or inactive.
     */
    public ApiResponse<SettingsTeam> updateSettingsTeam(
            String teamId, Object payload, RequestOptions options) {
        requireValue(teamId, "team_id");
        requirePayload(payload);
        return client.sendPatchRequest(TeamsUrls.SETTINGS_TEAM,
                reopen(options).pathParam("team_id", teamId).jsonBody(payload).build(), SettingsTeam.class);
    }

    public ApiResponse<SettingsTeam> updateSettingsTeam(String teamId, Object payload) {
        return updateSettingsTeam(teamId, payload, null);
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

    private static void requirePayload(Object payload) {
        if (payload == null) {
            throw new SdkUsageError("payload is required: this endpoint takes a body, "
                    + "and null would send the request with no body at all");
        }
    }
}
