package com.exsited.resources.employees;

/** Path templates and body keys for the employee endpoints. */
public final class EmployeesUrls {

    private EmployeesUrls() {}

    public static final String EMPLOYEES = "/api/{api_version}/employees";
    public static final String EMPLOYEE = "/api/{api_version}/employees/{employee_id}";

    /** The key a listing's records sit under. */
    public static final String EMPLOYEES_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String EMPLOYEE_KEY = "employee";
}
