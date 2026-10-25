package com.exsited.resources.accounting_codes;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.accounting_codes.accounting_codes_dto.AccountingCodesDto.SettingsAccountingCode;
import com.exsited.resources.accounting_codes.accounting_codes_dto.AccountingCodesDto.SettingsAccountingCodeList;
import java.util.Map;

/** Accounting code endpoints, under {@code /api/{api_version}/settings/accounting_codes}. */
public final class AccountingCodes implements BoundResource {

    private final ExsitedClient client;

    public AccountingCodes(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List all: {@code GET /settings/accounting_codes}. */
    public ApiResponse<SettingsAccountingCodeList> getSettingsAccountingCodes(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(AccountingCodesUrls.SETTINGS_ACCOUNTING_CODES,
                reopen(options).queryParams(queryParams).build(), SettingsAccountingCodeList.class);
    }

    public ApiResponse<SettingsAccountingCodeList> getSettingsAccountingCodes(
            Map<String, Object> queryParams) {
        return getSettingsAccountingCodes(queryParams, null);
    }

    public ApiResponse<SettingsAccountingCodeList> getSettingsAccountingCodes() {
        return getSettingsAccountingCodes(null, null);
    }

    /**
     * Create accounting code: {@code POST /settings/accounting_codes}.
     */
    public ApiResponse<SettingsAccountingCode> createSettingsAccountingCode(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(AccountingCodesUrls.SETTINGS_ACCOUNTING_CODES,
                reopen(options).jsonBody(payload).build(), SettingsAccountingCode.class);
    }

    public ApiResponse<SettingsAccountingCode> createSettingsAccountingCode(
            Object payload) {
        return createSettingsAccountingCode(payload, null);
    }

    /** Get single: {@code GET /settings/accounting_codes/{accounting_code_id}}. */
    public ApiResponse<SettingsAccountingCode> getSettingsAccountingCode(
            String accountingCodeId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(accountingCodeId, "accounting_code_id");
        return client.sendGetRequest(AccountingCodesUrls.SETTINGS_ACCOUNTING_CODE,
                reopen(options).pathParam("accounting_code_id", accountingCodeId).queryParams(queryParams).build(), SettingsAccountingCode.class);
    }

    public ApiResponse<SettingsAccountingCode> getSettingsAccountingCode(
            String accountingCodeId, Map<String, Object> queryParams) {
        return getSettingsAccountingCode(accountingCodeId, queryParams, null);
    }

    public ApiResponse<SettingsAccountingCode> getSettingsAccountingCode(String accountingCodeId) {
        return getSettingsAccountingCode(accountingCodeId, null, null);
    }

    /**
     * Delete accounting code: {@code DELETE /settings/accounting_codes/{accounting_code_id}}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsAccountingCode(
            String accountingCodeId, RequestOptions options) {
        requireValue(accountingCodeId, "accounting_code_id");
        return client.sendDeleteRequest(AccountingCodesUrls.SETTINGS_ACCOUNTING_CODE,
                reopen(options).pathParam("accounting_code_id", accountingCodeId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsAccountingCode(
            String accountingCodeId) {
        return deleteSettingsAccountingCode(accountingCodeId, null);
    }

    /**
     * Update accounting code:
     * {@code PATCH /settings/accounting_codes/{accounting_code_id}}.
     */
    public ApiResponse<SettingsAccountingCode> updateSettingsAccountingCode(
            String accountingCodeId, Object payload, RequestOptions options) {
        requireValue(accountingCodeId, "accounting_code_id");
        requirePayload(payload);
        return client.sendPatchRequest(AccountingCodesUrls.SETTINGS_ACCOUNTING_CODE,
                reopen(options).pathParam("accounting_code_id", accountingCodeId).jsonBody(payload).build(), SettingsAccountingCode.class);
    }

    public ApiResponse<SettingsAccountingCode> updateSettingsAccountingCode(
            String accountingCodeId, Object payload) {
        return updateSettingsAccountingCode(accountingCodeId, payload, null);
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
