package com.exsited.resources.departments;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.departments.departments_dto.DepartmentsDto.SettingsDepartment;
import com.exsited.resources.departments.departments_dto.DepartmentsDto.SettingsDepartmentList;
import java.util.Map;

/** Department endpoints, under {@code /api/{api_version}/settings/departments}. */
public final class Departments implements BoundResource {

    private final ExsitedClient client;

    public Departments(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List the departments; some carry no {@code id} and cannot be read singly:
     * {@code GET /settings/departments}.
     */
    public ApiResponse<SettingsDepartmentList> getSettingsDepartments(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(DepartmentsUrls.SETTINGS_DEPARTMENTS,
                reopen(options).queryParams(queryParams).build(), SettingsDepartmentList.class);
    }

    public ApiResponse<SettingsDepartmentList> getSettingsDepartments(Map<String, Object> queryParams) {
        return getSettingsDepartments(queryParams, null);
    }

    public ApiResponse<SettingsDepartmentList> getSettingsDepartments() {
        return getSettingsDepartments(null, null);
    }

    /**
     * Read one department by its {@code dept_} id:
     * {@code GET /settings/departments/{department_id}}.
     */
    public ApiResponse<SettingsDepartment> getSettingsDepartment(
            String departmentId, RequestOptions options) {
        requireValue(departmentId, "department_id");
        return client.sendGetRequest(DepartmentsUrls.SETTINGS_DEPARTMENT,
                reopen(options).pathParam("department_id", departmentId).build(), SettingsDepartment.class);
    }

    public ApiResponse<SettingsDepartment> getSettingsDepartment(String departmentId) {
        return getSettingsDepartment(departmentId, null);
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
}
