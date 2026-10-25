package com.exsited.resources.employees.employees_dto;

import com.exsited.Dto;
import com.exsited.resources.PaginationDto;
import java.util.List;

/** Typed views over the employees bodies. */
public final class EmployeesDto {

    /** The employee body. */
    public static final class Employee extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/employees",
                "GET /api/{api_version}/employees/{employee_id}",
                "POST /api/{api_version}/employees",
                "PATCH /api/{api_version}/employees/{employee_id}");

        @Override
        protected String envelopeKey() {
            return "employee";
        }

        public List<Object> activity_logs;
        public String code;
        public List<EmployeeContact> contacts;
        public EmployeeCreatedBy created_by;
        public String created_on;
        public List<Object> custom_attributes;
        public EmployeeCustomForm custom_form;
        public String description;
        public String display_name;
        public String email_address;
        public String href;
        public String id;
        /** never populated in 4 measured records. */
        public Object image_uuid;
        public EmployeeLastUpdatedBy last_updated_by;
        public String last_updated_on;
        public String name;
        public String object;
        public String status;
        public Long version;
    }

    /** A page of Employee records. */
    public static final class EmployeeList extends Dto {
        /** The endpoints these fields were measured from. */
        public static final List<String> MEASURED_FROM = List.of(
                "GET /api/{api_version}/employees");

        public List<Employee> data;
        public String object;
        public PaginationDto pagination;
        public String url;
    }

    /** A {@link Employee}'s {@code custom_form}. */
    public static final class EmployeeCustomForm extends Dto {
        public String href;
        public String id;
        public String name;
        public String object;
    }

    /** One entry of a {@link Employee}'s {@code contacts}. */
    public static final class EmployeeContact extends Dto {
        /** Null in every record measured, so untyped. */
        public Object address;
        public EmployeeContactCreatedBy created_by;
        public String created_on;
        public EmployeeContactEmail email;
        /** Null in every record measured, so untyped. */
        public Object fax;
        public String id;
        /** Null in every record measured, so untyped. */
        public Object last_updated_by;
        /** Null in every record measured, so untyped. */
        public Object last_updated_on;
        /** Null in every record measured, so untyped. */
        public Object mobile;
        public EmployeeContactName name;
        public String object;
        /** Null in every record measured, so untyped. */
        public Object phone;
        public String status;
        public String type;
        public Long version;
    }

    /** A {@link Employee}'s {@code created_by}. */
    public static final class EmployeeCreatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link Employee}'s {@code last_updated_by}. */
    public static final class EmployeeLastUpdatedBy extends Dto {
        public String name;
        public String object;
    }

    /** A {@link EmployeeContact}'s {@code name}. */
    public static final class EmployeeContactName extends Dto {
        /** Null in every record measured, so untyped. */
        public Object designation;
        public String first;
        public String last;
        /** Null in every record measured, so untyped. */
        public Object middle;
        /** Null in every record measured, so untyped. */
        public Object salutation;
    }

    /** A {@link EmployeeContact}'s {@code email}. */
    public static final class EmployeeContactEmail extends Dto {
        public String address;
        public Boolean email_opted_out;
    }

    /** A {@link EmployeeContact}'s {@code created_by}. */
    public static final class EmployeeContactCreatedBy extends Dto {
        public String name;
        public String object;
    }

    private EmployeesDto() {
    }
}
