package com.exsited.resources.employees;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.employees.employees_dto.EmployeesDto.Employee;
import com.exsited.resources.employees.employees_dto.EmployeesDto.EmployeeList;
import java.util.Map;

/** Employee endpoints, under {@code /api/{api_version}/employees}. */
public final class Employees implements BoundResource {

    private final ExsitedClient client;

    public Employees(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List employees: {@code GET /employees}. */
    public ApiResponse<EmployeeList> getEmployees(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(EmployeesUrls.EMPLOYEES,
                reopen(options).queryParams(queryParams).build(), EmployeeList.class);
    }

    public ApiResponse<EmployeeList> getEmployees(
            Map<String, Object> queryParams) {
        return getEmployees(queryParams, null);
    }

    public ApiResponse<EmployeeList> getEmployees() {
        return getEmployees(null, null);
    }

    /** Create employee: {@code POST /employees}. */
    public ApiResponse<Employee> createEmployee(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(EmployeesUrls.EMPLOYEES,
                reopen(options).jsonBody(payload).build(), Employee.class);
    }

    public ApiResponse<Employee> createEmployee(
            Object payload) {
        return createEmployee(payload, null);
    }

    /**
     * Delete employee: {@code DELETE /employees/{employee_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteEmployee(
            String employeeId, RequestOptions options) {
        requireValue(employeeId, "employee_id");
        return client.sendDeleteRequest(EmployeesUrls.EMPLOYEE,
                reopen(options).pathParam("employee_id", employeeId).build(), null);
    }

    public ApiResponse<Dto> deleteEmployee(
            String employeeId) {
        return deleteEmployee(employeeId, null);
    }

    /** Get employee: {@code GET /employees/{employee_id}}. */
    public ApiResponse<Employee> getEmployee(
            String employeeId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(employeeId, "employee_id");
        return client.sendGetRequest(EmployeesUrls.EMPLOYEE,
                reopen(options).pathParam("employee_id", employeeId).queryParams(queryParams).build(), Employee.class);
    }

    public ApiResponse<Employee> getEmployee(
            String employeeId, Map<String, Object> queryParams) {
        return getEmployee(employeeId, queryParams, null);
    }

    public ApiResponse<Employee> getEmployee(String employeeId) {
        return getEmployee(employeeId, null, null);
    }

    /**
     * Update employee: {@code PATCH /employees/{employee_id}}.
     */
    public ApiResponse<Employee> updateEmployee(
            String employeeId, Object payload, RequestOptions options) {
        requireValue(employeeId, "employee_id");
        requirePayload(payload);
        return client.sendPatchRequest(EmployeesUrls.EMPLOYEE,
                reopen(options).pathParam("employee_id", employeeId).jsonBody(payload).build(), Employee.class);
    }

    public ApiResponse<Employee> updateEmployee(
            String employeeId, Object payload) {
        return updateEmployee(employeeId, payload, null);
    }

    private static RequestOptions.Builder reopen(RequestOptions options) {
        RequestOptions given = options == null ? RequestOptions.none() : options;
        return RequestOptions.builder()
                .pathParams(given.getPathParams())
                .queryParams(given.getQueryParams())
                .jsonBody(given.getJsonBody())
                .formData(given.getFormData())
                .files(given.getFiles())
                .headers(given.getHeaders())
                .authenticated(given.isAuthenticated())
                .raiseOnError(given.getRaiseOnError())
                .responseFormat(given.getResponseFormat())
                .dtoFactory(given.getDtoFactory());
    }

    private static void requireValue(String value, String what) {
        if (value == null || value.isBlank()) {
            throw new SdkUsageError(
                    what + " is required: the request cannot be built without it");
        }
    }

    private static void requirePayload(Object payload) {
        if (payload == null) {
            throw new SdkUsageError("payload is required: this endpoint takes a body, "
                    + "and null would send the request with no body at all");
        }
    }
}
