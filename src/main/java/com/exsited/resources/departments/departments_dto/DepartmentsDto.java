package com.exsited.resources.departments.departments_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the department bodies. */
public final class DepartmentsDto {

    private DepartmentsDto() {}

    /** The settings department body, under {@code department}. */
    public static final class SettingsDepartment extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/departments",
                "GET /api/{api_version}/settings/departments/{department_id}");

        @Override
        protected String envelopeKey() {
            return "department";
        }

        public String code;
        /** Null in every record measured, so untyped. */
        public Object created_by;
        public String created_on;
        public String description;
        public String display_name;
        public SettingsDepartmentDivision division;
        public String href;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
        public Long version;
    }

    /** One page of {@code SettingsDepartment} records, under {@code data}. */
    public static final class SettingsDepartmentList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/settings/departments");

        public List<SettingsDepartment> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** A {@link SettingsDepartment}'s {@code division}. */
    public static final class SettingsDepartmentDivision extends Dto {
        public String id;
        public String name;
        public String object;
    }
}
