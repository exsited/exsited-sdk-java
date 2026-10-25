package com.exsited.resources.fulfillment_terms;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.fulfillment_terms.fulfillment_terms_dto.FulfillmentTermsDto.SettingsFulfillmentTerm;
import com.exsited.resources.fulfillment_terms.fulfillment_terms_dto.FulfillmentTermsDto.SettingsFulfillmentTermList;
import java.util.Map;

/** Fulfillment term endpoints, under {@code /api/{api_version}/settings/fulfillment_terms}. */
public final class FulfillmentTerms implements BoundResource {

    private final ExsitedClient client;

    public FulfillmentTerms(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List fulfillment terms: {@code GET /settings/fulfillment_terms}. */
    public ApiResponse<SettingsFulfillmentTermList> getSettingsFulfillmentTerms(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(FulfillmentTermsUrls.SETTINGS_FULFILLMENT_TERMS,
                reopen(options).queryParams(queryParams).build(), SettingsFulfillmentTermList.class);
    }

    public ApiResponse<SettingsFulfillmentTermList> getSettingsFulfillmentTerms(
            Map<String, Object> queryParams) {
        return getSettingsFulfillmentTerms(queryParams, null);
    }

    public ApiResponse<SettingsFulfillmentTermList> getSettingsFulfillmentTerms() {
        return getSettingsFulfillmentTerms(null, null);
    }

    /**
     * Create a fulfillment term: {@code POST /settings/fulfillment_terms}; {@code period} is sent
     * upper case and answered lower case.
     */
    public ApiResponse<SettingsFulfillmentTerm> createSettingsFulfillmentTerm(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(FulfillmentTermsUrls.SETTINGS_FULFILLMENT_TERMS,
                reopen(options).jsonBody(payload).build(), SettingsFulfillmentTerm.class);
    }

    public ApiResponse<SettingsFulfillmentTerm> createSettingsFulfillmentTerm(Object payload) {
        return createSettingsFulfillmentTerm(payload, null);
    }

    /** Get one fulfillment term: {@code GET /settings/fulfillment_terms/{fulfillment_term_id}}. */
    public ApiResponse<SettingsFulfillmentTerm> getSettingsFulfillmentTerm(
            String fulfillmentTermId, RequestOptions options) {
        requireValue(fulfillmentTermId, "fulfillment_term_id");
        return client.sendGetRequest(FulfillmentTermsUrls.SETTINGS_FULFILLMENT_TERM,
                reopen(options).pathParam("fulfillment_term_id", fulfillmentTermId).build(), SettingsFulfillmentTerm.class);
    }

    public ApiResponse<SettingsFulfillmentTerm> getSettingsFulfillmentTerm(String fulfillmentTermId) {
        return getSettingsFulfillmentTerm(fulfillmentTermId, null);
    }

    /**
     * Delete a fulfillment term: {@code DELETE /settings/fulfillment_terms/{fulfillment_term_id}}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsFulfillmentTerm(
            String fulfillmentTermId, RequestOptions options) {
        requireValue(fulfillmentTermId, "fulfillment_term_id");
        return client.sendDeleteRequest(FulfillmentTermsUrls.SETTINGS_FULFILLMENT_TERM,
                reopen(options).pathParam("fulfillment_term_id", fulfillmentTermId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsFulfillmentTerm(String fulfillmentTermId) {
        return deleteSettingsFulfillmentTerm(fulfillmentTermId, null);
    }

    /**
     * Update or enable/disable a fulfillment term:
     * {@code PATCH /settings/fulfillment_terms/{fulfillment_term_id}}; {@code status} takes active
     * or inactive.
     */
    public ApiResponse<SettingsFulfillmentTerm> updateSettingsFulfillmentTerm(
            String fulfillmentTermId, Object payload, RequestOptions options) {
        requireValue(fulfillmentTermId, "fulfillment_term_id");
        requirePayload(payload);
        return client.sendPatchRequest(FulfillmentTermsUrls.SETTINGS_FULFILLMENT_TERM,
                reopen(options).pathParam("fulfillment_term_id", fulfillmentTermId).jsonBody(payload).build(), SettingsFulfillmentTerm.class);
    }

    public ApiResponse<SettingsFulfillmentTerm> updateSettingsFulfillmentTerm(
            String fulfillmentTermId, Object payload) {
        return updateSettingsFulfillmentTerm(fulfillmentTermId, payload, null);
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
