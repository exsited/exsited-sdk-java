package com.exsited.resources.roles.roles_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the role bodies. */
public final class RolesDto {

    private RolesDto() {}

    /** The settings role body, under {@code role}. */
    public static final class SettingsRole extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/roles",
                "GET /api/{api_version}/settings/roles/{role_id}");

        @Override
        protected String envelopeKey() {
            return "role";
        }

        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        /** Null in every record measured, so untyped. */
        public Object description;
        public String display_name;
        public String href;
        public String id;
        public Boolean is_system_role;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
    }

    /** One page of {@code SettingsRole} records, under {@code data}. */
    public static final class SettingsRoleList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/roles");

        public List<SettingsRole> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
