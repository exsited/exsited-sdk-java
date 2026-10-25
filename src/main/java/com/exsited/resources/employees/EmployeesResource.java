package com.exsited.resources.employees;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.employees.employees_dto.EmployeesDto.Employee;
import com.exsited.resources.employees.employees_dto.EmployeesDto.EmployeeList;
import java.util.Map;

/**
 * Forwards every call to {@link Employees}.
 *
 * @deprecated {@link Employees} is bound to a client itself and is what
 *     {@code client.employees()} answers; use it instead.
 */
@Deprecated
public final class EmployeesResource implements BoundResource {

    private final Employees employees;

    public EmployeesResource(ExsitedClient client) {
        this.employees = new Employees(client);
    }

    @Override
    public ExsitedClient getClient() {
        return employees.getClient();
    }

    public ApiResponse<EmployeeList> getEmployees(
            Map<String, Object> queryParams, RequestOptions options) {
        return employees.getEmployees(queryParams, options);
    }

    public ApiResponse<EmployeeList> getEmployees(Map<String, Object> queryParams) {
        return employees.getEmployees(queryParams);
    }

    public ApiResponse<Employee> createEmployee(Object payload, RequestOptions options) {
        return employees.createEmployee(payload, options);
    }

    public ApiResponse<Employee> createEmployee(Object payload) {
        return employees.createEmployee(payload);
    }

    public ApiResponse<Dto> deleteEmployee(String employeeId, RequestOptions options) {
        return employees.deleteEmployee(employeeId, options);
    }

    public ApiResponse<Dto> deleteEmployee(String employeeId) {
        return employees.deleteEmployee(employeeId);
    }

    public ApiResponse<Employee> getEmployee(
            String employeeId, Map<String, Object> queryParams, RequestOptions options) {
        return employees.getEmployee(employeeId, queryParams, options);
    }

    public ApiResponse<Employee> getEmployee(String employeeId, Map<String, Object> queryParams) {
        return employees.getEmployee(employeeId, queryParams);
    }

    public ApiResponse<Employee> updateEmployee(
            String employeeId, Object payload, RequestOptions options) {
        return employees.updateEmployee(employeeId, payload, options);
    }

    public ApiResponse<Employee> updateEmployee(String employeeId, Object payload) {
        return employees.updateEmployee(employeeId, payload);
    }
}
