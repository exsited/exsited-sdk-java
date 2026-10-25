package com.exsited.resources.departments;

/** Path templates and body keys for the department endpoints. */
public final class DepartmentsUrls {

    private DepartmentsUrls() {}

    public static final String SETTINGS_DEPARTMENTS = "/api/{api_version}/settings/departments";
    public static final String SETTINGS_DEPARTMENT = "/api/{api_version}/settings/departments/{department_id}";

    /** The key a listing's records sit under. */
    public static final String SETTINGS_DEPARTMENTS_KEY = "data";

    /** The envelope one record arrives under. */
    public static final String DEPARTMENT_KEY = "department";
}
