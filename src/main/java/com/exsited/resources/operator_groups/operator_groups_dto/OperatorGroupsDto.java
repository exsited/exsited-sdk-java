package com.exsited.resources.operator_groups.operator_groups_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the operator group bodies. */
public final class OperatorGroupsDto {

    private OperatorGroupsDto() {}

    /**
     * A {@link SettingsOperatorGroup}'s {@code role}. A read names it as a {@code role} with its
     * {@code rol_} id and {@code href}; the update answer names it as a {@code reference} with the
     * dashed uuid and no {@code href}.
     */
    public static final class SettingsOperatorGroupRole extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /**
     * One of a {@link SettingsOperatorGroup}'s {@code departments}, a {@code reference}; {@code id}
     * is null on the departments the instance ships with.
     */
    public static final class SettingsOperatorGroupDepartment extends Dto {
        public String id;
        public String name;
        public String object;
    }

    /**
     * The operator group body, under {@code operator_group}. The create answer carries only
     * {@code object}, {@code id}, {@code name}, {@code display_name}, {@code description} and
     * {@code href}.
     */
    public static final class SettingsOperatorGroup extends Dto {
        /** The endpoints these fields belong to. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/operator_groups",
                "GET /api/{api_version}/settings/operator_groups/{operator_group_id}",
                "POST /api/{api_version}/settings/operator_groups",
                "PATCH /api/{api_version}/settings/operator_groups/{operator_group_id}");

        @Override
        protected String envelopeKey() {
            return "operator_group";
        }

        public Object created_by;
        public String created_on;
        public List<SettingsOperatorGroupDepartment> departments;
        public String description;
        public String display_name;
        public String href;
        public String id;
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public SettingsOperatorGroupRole role;
        public String status;
    }

    /** One page of {@code SettingsOperatorGroup} records, under {@code data}. */
    public static final class SettingsOperatorGroupList extends Dto {
        /** The endpoints these fields belong to. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/operator_groups");

        public List<SettingsOperatorGroup> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }
}
