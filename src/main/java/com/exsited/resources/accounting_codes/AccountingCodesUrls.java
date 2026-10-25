package com.exsited.resources.accounting_codes;

/** Path templates and body keys for the accounting code endpoints. */
public final class AccountingCodesUrls {

    private AccountingCodesUrls() {}

    public static final String SETTINGS_ACCOUNTING_CODES = "/api/{api_version}/settings/accounting_codes";
    public static final String SETTINGS_ACCOUNTING_CODE = "/api/{api_version}/settings/accounting_codes/{accounting_code_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_ACCOUNTING_CODES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String ACCOUNTING_CODE_KEY = "accounting_code";
}
