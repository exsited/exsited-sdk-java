package com.exsited.resources.account_groups.account_groups_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the account group bodies. */
public final class AccountGroupsDto {

    private AccountGroupsDto() {}

    /** The settings account group body, under {@code account_group}. */
    public static final class SettingsAccountGroup extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/account_groups",
                "GET /api/{api_version}/settings/account_groups/{account_group_id}",
                "POST /api/{api_version}/settings/account_groups");

        @Override
        protected String envelopeKey() {
            return "account_group";
        }

        /** Null in every record measured, so untyped. */
        public Object accounting_code;
        /** Present but empty on every record measured, so the element type is unknown. */
        public List<Object> custom_attributes;
        /** Null in every record measured, so untyped. */
        public Object description;
        public String display_name;
        public String href;
        public String id;
        public List<String> invoice_account_media;
        /** Null in every record measured, so untyped. */
        public Object managed_by;
        public String name;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object pricing_level;
        public SettingsAccountGroupStateRule state_rule;
        public String status;
    }

    /** One page of {@code SettingsAccountGroup} records, under {@code data}. */
    public static final class SettingsAccountGroupList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/account_groups");

        public List<SettingsAccountGroup> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** A {@link SettingsAccountGroup}'s {@code state_rule}. */
    public static final class SettingsAccountGroupStateRule extends Dto {
        public String href;
        public String id;
        public String object;
    }
}
