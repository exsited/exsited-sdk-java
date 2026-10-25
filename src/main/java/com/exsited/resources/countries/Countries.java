package com.exsited.resources.countries;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.countries.countries_dto.CountriesDto.SettingsCountry;
import com.exsited.resources.countries.countries_dto.CountriesDto.SettingsCountryList;
import java.util.Map;

/** Country endpoints, under {@code /api/{api_version}/settings/countries}. */
public final class Countries implements BoundResource {

    private final ExsitedClient client;

    public Countries(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the countries: {@code GET /settings/countries}. */
    public ApiResponse<SettingsCountryList> getSettingsCountries(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(CountriesUrls.SETTINGS_COUNTRIES,
                reopen(options).queryParams(queryParams).build(), SettingsCountryList.class);
    }

    public ApiResponse<SettingsCountryList> getSettingsCountries(Map<String, Object> queryParams) {
        return getSettingsCountries(queryParams, null);
    }

    public ApiResponse<SettingsCountryList> getSettingsCountries() {
        return getSettingsCountries(null, null);
    }

    /**
     * Read one country by its {@code country_} id: {@code GET /settings/countries/{country_id}}.
     */
    public ApiResponse<SettingsCountry> getSettingsCountry(
            String countryId, RequestOptions options) {
        requireValue(countryId, "country_id");
        return client.sendGetRequest(CountriesUrls.SETTINGS_COUNTRY,
                reopen(options).pathParam("country_id", countryId).build(), SettingsCountry.class);
    }

    public ApiResponse<SettingsCountry> getSettingsCountry(String countryId) {
        return getSettingsCountry(countryId, null);
    }

    /**
     * Update a country's status, description or {@code default_*} fields; the answer is the country
     * as it was before the write: {@code PATCH /settings/countries/{country_id}}.
     */
    public ApiResponse<SettingsCountry> updateSettingsCountry(
            String countryId, Object payload, RequestOptions options) {
        requireValue(countryId, "country_id");
        requirePayload(payload);
        return client.sendPatchRequest(CountriesUrls.SETTINGS_COUNTRY,
                reopen(options).pathParam("country_id", countryId).jsonBody(payload).build(), SettingsCountry.class);
    }

    public ApiResponse<SettingsCountry> updateSettingsCountry(String countryId, Object payload) {
        return updateSettingsCountry(countryId, payload, null);
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
