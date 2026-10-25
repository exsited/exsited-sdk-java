package com.exsited.resources.alerts;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.alerts.alerts_dto.AlertsDto;
import java.util.Map;

/** Alert endpoints, under {@code /api/{api_version}/alerts}. */
public final class Alerts implements BoundResource {

    private final ExsitedClient client;

    public Alerts(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List all: {@code GET /alerts}. */
    public ApiResponse<AlertsDto.Alerts> getAlerts(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(AlertsUrls.ALERTS,
                reopen(options).queryParams(queryParams).build(), AlertsDto.Alerts.class);
    }

    public ApiResponse<AlertsDto.Alerts> getAlerts(
            Map<String, Object> queryParams) {
        return getAlerts(queryParams, null);
    }

    public ApiResponse<AlertsDto.Alerts> getAlerts() {
        return getAlerts(null, null);
    }

    /** Get single: {@code GET /alerts/{alert_id}}. No typed view; read {@code getMap()}. */
    public ApiResponse<Dto> getAlert(
            String alertId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(alertId, "alert_id");
        return client.sendGetRequest(AlertsUrls.ALERT,
                reopen(options).pathParam("alert_id", alertId).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getAlert(
            String alertId, Map<String, Object> queryParams) {
        return getAlert(alertId, queryParams, null);
    }

    public ApiResponse<Dto> getAlert(String alertId) {
        return getAlert(alertId, null, null);
    }

    /** Alerts by account: {@code GET /alerts/account/{account_id}}. */
    public ApiResponse<AlertsDto.Alerts> getAlertsAccountById(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(accountId, "account_id");
        return client.sendGetRequest(AlertsUrls.ALERTS_ACCOUNT_BY_ID,
                reopen(options).pathParam("account_id", accountId).queryParams(queryParams).build(), AlertsDto.Alerts.class);
    }

    public ApiResponse<AlertsDto.Alerts> getAlertsAccountById(
            String accountId, Map<String, Object> queryParams) {
        return getAlertsAccountById(accountId, queryParams, null);
    }

    public ApiResponse<AlertsDto.Alerts> getAlertsAccountById(String accountId) {
        return getAlertsAccountById(accountId, null, null);
    }

    /**
     * Alerts by employee: {@code GET /alerts/employee/{employee_id}}.
     */
    public ApiResponse<AlertsDto.Alerts> getAlertsEmployeeById(
            String employeeId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(employeeId, "employee_id");
        return client.sendGetRequest(AlertsUrls.ALERTS_EMPLOYEE_BY_ID,
                reopen(options).pathParam("employee_id", employeeId).queryParams(queryParams).build(), AlertsDto.Alerts.class);
    }

    public ApiResponse<AlertsDto.Alerts> getAlertsEmployeeById(
            String employeeId, Map<String, Object> queryParams) {
        return getAlertsEmployeeById(employeeId, queryParams, null);
    }

    public ApiResponse<AlertsDto.Alerts> getAlertsEmployeeById(String employeeId) {
        return getAlertsEmployeeById(employeeId, null, null);
    }

    /**
     * Alerts by labour: {@code GET /alerts/labour/{labour_id}}.
     */
    public ApiResponse<AlertsDto.Alerts> getAlertsLabourById(
            String labourId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(labourId, "labour_id");
        return client.sendGetRequest(AlertsUrls.ALERTS_LABOUR_BY_ID,
                reopen(options).pathParam("labour_id", labourId).queryParams(queryParams).build(), AlertsDto.Alerts.class);
    }

    public ApiResponse<AlertsDto.Alerts> getAlertsLabourById(
            String labourId, Map<String, Object> queryParams) {
        return getAlertsLabourById(labourId, queryParams, null);
    }

    public ApiResponse<AlertsDto.Alerts> getAlertsLabourById(String labourId) {
        return getAlertsLabourById(labourId, null, null);
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
