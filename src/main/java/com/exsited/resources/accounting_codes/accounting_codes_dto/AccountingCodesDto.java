package com.exsited.resources.accounting_codes.accounting_codes_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the accounting codes bodies. */
public final class AccountingCodesDto {

    /** The settings accounting code body. */
    public static final class SettingsAccountingCode extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/accounting_codes",
                "GET /api/{api_version}/settings/accounting_codes/{accounting_code_id}",
                "POST /api/{api_version}/settings/accounting_codes",
                "PATCH /api/{api_version}/settings/accounting_codes/{accounting_code_id}");

        @Override
        protected String envelopeKey() {
            return "accounting_code";
        }

        public String account_code_type;
        /** never populated in 2 measured records. */
        public Object created_by;
        public String created_on;
        public String default_match_key;
        /** never populated in 2 measured records. */
        public Object description;
        public String display_name;
        public String gl_account_name;
        public String gl_account_number;
        public String href;
        public String id;
        /** never populated in 2 measured records. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
        public Long version;
    }

    /** A page of SettingsAccountingCode records. */
    public static final class SettingsAccountingCodeList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/accounting_codes");

        public List<SettingsAccountingCode> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    private AccountingCodesDto() {
    }
}
