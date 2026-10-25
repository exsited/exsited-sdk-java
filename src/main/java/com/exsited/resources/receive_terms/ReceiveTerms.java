package com.exsited.resources.receive_terms;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.receive_terms.receive_terms_dto.ReceiveTermsDto.SettingsReceiveTerm;
import com.exsited.resources.receive_terms.receive_terms_dto.ReceiveTermsDto.SettingsReceiveTermList;
import java.util.Map;

/** Receive term endpoints, under {@code /api/{api_version}/settings/receive_terms}. */
public final class ReceiveTerms implements BoundResource {

    private final ExsitedClient client;

    public ReceiveTerms(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List receive terms: {@code GET /settings/receive_terms}. */
    public ApiResponse<SettingsReceiveTermList> getSettingsReceiveTerms(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ReceiveTermsUrls.SETTINGS_RECEIVE_TERMS,
                reopen(options).queryParams(queryParams).build(), SettingsReceiveTermList.class);
    }

    public ApiResponse<SettingsReceiveTermList> getSettingsReceiveTerms(Map<String, Object> queryParams) {
        return getSettingsReceiveTerms(queryParams, null);
    }

    public ApiResponse<SettingsReceiveTermList> getSettingsReceiveTerms() {
        return getSettingsReceiveTerms(null, null);
    }

    /**
     * Create a receive term: {@code POST /settings/receive_terms}; {@code period} is sent upper
     * case and answered lower case.
     */
    public ApiResponse<SettingsReceiveTerm> createSettingsReceiveTerm(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(ReceiveTermsUrls.SETTINGS_RECEIVE_TERMS,
                reopen(options).jsonBody(payload).build(), SettingsReceiveTerm.class);
    }

    public ApiResponse<SettingsReceiveTerm> createSettingsReceiveTerm(Object payload) {
        return createSettingsReceiveTerm(payload, null);
    }

    /** Get one receive term: {@code GET /settings/receive_terms/{receive_term_id}}. */
    public ApiResponse<SettingsReceiveTerm> getSettingsReceiveTerm(
            String receiveTermId, RequestOptions options) {
        requireValue(receiveTermId, "receive_term_id");
        return client.sendGetRequest(ReceiveTermsUrls.SETTINGS_RECEIVE_TERM,
                reopen(options).pathParam("receive_term_id", receiveTermId).build(), SettingsReceiveTerm.class);
    }

    public ApiResponse<SettingsReceiveTerm> getSettingsReceiveTerm(String receiveTermId) {
        return getSettingsReceiveTerm(receiveTermId, null);
    }

    /**
     * Delete a receive term: {@code DELETE /settings/receive_terms/{receive_term_id}}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsReceiveTerm(
            String receiveTermId, RequestOptions options) {
        requireValue(receiveTermId, "receive_term_id");
        return client.sendDeleteRequest(ReceiveTermsUrls.SETTINGS_RECEIVE_TERM,
                reopen(options).pathParam("receive_term_id", receiveTermId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsReceiveTerm(String receiveTermId) {
        return deleteSettingsReceiveTerm(receiveTermId, null);
    }

    /**
     * Update or enable/disable a receive term:
     * {@code PATCH /settings/receive_terms/{receive_term_id}}; {@code status} takes active or
     * inactive.
     */
    public ApiResponse<SettingsReceiveTerm> updateSettingsReceiveTerm(
            String receiveTermId, Object payload, RequestOptions options) {
        requireValue(receiveTermId, "receive_term_id");
        requirePayload(payload);
        return client.sendPatchRequest(ReceiveTermsUrls.SETTINGS_RECEIVE_TERM,
                reopen(options).pathParam("receive_term_id", receiveTermId).jsonBody(payload).build(), SettingsReceiveTerm.class);
    }

    public ApiResponse<SettingsReceiveTerm> updateSettingsReceiveTerm(String receiveTermId, Object payload) {
        return updateSettingsReceiveTerm(receiveTermId, payload, null);
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
