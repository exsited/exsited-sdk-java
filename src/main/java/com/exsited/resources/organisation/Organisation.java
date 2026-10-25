package com.exsited.resources.organisation;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.organisation.organisation_dto.OrganisationDto.SettingsOrganisation;

/** Root organisation endpoints, under {@code /api/{api_version}/settings/organisation}. */
public final class Organisation implements BoundResource {

    private final ExsitedClient client;

    public Organisation(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * Get the root organisation: {@code GET /settings/organisation}; read only, a PATCH here
     * answers 405.
     */
    public ApiResponse<SettingsOrganisation> getSettingsOrganisation(
            RequestOptions options) {
        return client.sendGetRequest(OrganisationUrls.SETTINGS_ORGANISATION,
                reopen(options).build(), SettingsOrganisation.class);
    }

    public ApiResponse<SettingsOrganisation> getSettingsOrganisation() {
        return getSettingsOrganisation(null);
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
}
