package com.exsited.resources.accounting_codes;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.accounting_codes.accounting_codes_dto.AccountingCodesDto.SettingsAccountingCode;
import com.exsited.resources.accounting_codes.accounting_codes_dto.AccountingCodesDto.SettingsAccountingCodeList;
import java.util.Map;

/**
 * Forwards every call to {@link AccountingCodes}.
 *
 * @deprecated {@link AccountingCodes} is bound to a client itself and is what
 *     {@code client.accountingCodes()} answers; use it instead.
 */
@Deprecated
public final class AccountingCodesResource implements BoundResource {

    private final AccountingCodes accountingCodes;

    public AccountingCodesResource(ExsitedClient client) {
        this.accountingCodes = new AccountingCodes(client);
    }

    @Override
    public ExsitedClient getClient() {
        return accountingCodes.getClient();
    }

    public ApiResponse<SettingsAccountingCodeList> getSettingsAccountingCodes(
            Map<String, Object> queryParams, RequestOptions options) {
        return accountingCodes.getSettingsAccountingCodes(queryParams, options);
    }

    public ApiResponse<SettingsAccountingCodeList> getSettingsAccountingCodes(
            Map<String, Object> queryParams) {
        return accountingCodes.getSettingsAccountingCodes(queryParams);
    }

    public ApiResponse<SettingsAccountingCode> createSettingsAccountingCode(Object payload, RequestOptions options) {
        return accountingCodes.createSettingsAccountingCode(payload, options);
    }

    public ApiResponse<SettingsAccountingCode> createSettingsAccountingCode(Object payload) {
        return accountingCodes.createSettingsAccountingCode(payload);
    }

    public ApiResponse<SettingsAccountingCode> getSettingsAccountingCode(
            String accountingCodeId, Map<String, Object> queryParams, RequestOptions options) {
        return accountingCodes.getSettingsAccountingCode(accountingCodeId, queryParams, options);
    }

    public ApiResponse<SettingsAccountingCode> getSettingsAccountingCode(
            String accountingCodeId, Map<String, Object> queryParams) {
        return accountingCodes.getSettingsAccountingCode(accountingCodeId, queryParams);
    }

    public ApiResponse<Dto> deleteSettingsAccountingCode(
            String accountingCodeId, RequestOptions options) {
        return accountingCodes.deleteSettingsAccountingCode(accountingCodeId, options);
    }

    public ApiResponse<Dto> deleteSettingsAccountingCode(String accountingCodeId) {
        return accountingCodes.deleteSettingsAccountingCode(accountingCodeId);
    }

    public ApiResponse<SettingsAccountingCode> updateSettingsAccountingCode(
            String accountingCodeId, Object payload, RequestOptions options) {
        return accountingCodes.updateSettingsAccountingCode(accountingCodeId, payload, options);
    }

    public ApiResponse<SettingsAccountingCode> updateSettingsAccountingCode(String accountingCodeId, Object payload) {
        return accountingCodes.updateSettingsAccountingCode(accountingCodeId, payload);
    }
}
