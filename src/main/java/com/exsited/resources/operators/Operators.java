package com.exsited.resources.operators;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.operators.operators_dto.OperatorsDto.SettingsOperator;
import com.exsited.resources.operators.operators_dto.OperatorsDto.SettingsOperatorList;
import java.util.Map;

/** Operator endpoints, under {@code /api/{api_version}/settings/operators}. */
public final class Operators implements BoundResource {

    private final ExsitedClient client;

    public Operators(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List the operators: {@code GET /settings/operators}; paged by cursor. */
    public ApiResponse<SettingsOperatorList> getSettingsOperators(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(OperatorsUrls.SETTINGS_OPERATORS,
                reopen(options).queryParams(queryParams).build(), SettingsOperatorList.class);
    }

    public ApiResponse<SettingsOperatorList> getSettingsOperators(Map<String, Object> queryParams) {
        return getSettingsOperators(queryParams, null);
    }

    public ApiResponse<SettingsOperatorList> getSettingsOperators() {
        return getSettingsOperators(null, null);
    }

    /**
     * Create an operator: {@code POST /settings/operators}, answered {@code 201}. {@code role} and
     * {@code operator_group} take dashed uuids.
     */
    public ApiResponse<SettingsOperator> createSettingsOperator(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(OperatorsUrls.SETTINGS_OPERATORS,
                reopen(options).jsonBody(payload).build(), SettingsOperator.class);
    }

    public ApiResponse<SettingsOperator> createSettingsOperator(Object payload) {
        return createSettingsOperator(payload, null);
    }

    /** Read one operator: {@code GET /settings/operators/{operator_id}}. */
    public ApiResponse<SettingsOperator> getSettingsOperator(
            String operatorId, RequestOptions options) {
        requireValue(operatorId, "operator_id");
        return client.sendGetRequest(OperatorsUrls.SETTINGS_OPERATOR,
                reopen(options).pathParam("operator_id", operatorId).build(), SettingsOperator.class);
    }

    public ApiResponse<SettingsOperator> getSettingsOperator(String operatorId) {
        return getSettingsOperator(operatorId, null);
    }

    /**
     * Update an operator: {@code PATCH /settings/operators/{operator_id}}; {@code description} is
     * refused as not understood.
     */
    public ApiResponse<SettingsOperator> updateSettingsOperator(
            String operatorId, Object payload, RequestOptions options) {
        requireValue(operatorId, "operator_id");
        requirePayload(payload);
        return client.sendPatchRequest(OperatorsUrls.SETTINGS_OPERATOR,
                reopen(options).pathParam("operator_id", operatorId).jsonBody(payload).build(),
                SettingsOperator.class);
    }

    public ApiResponse<SettingsOperator> updateSettingsOperator(String operatorId, Object payload) {
        return updateSettingsOperator(operatorId, payload, null);
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
