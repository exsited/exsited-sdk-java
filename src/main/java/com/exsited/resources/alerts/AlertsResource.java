package com.exsited.resources.alerts;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.alerts.alerts_dto.AlertsDto;
import java.util.Map;

/**
 * Forwards every call to {@link Alerts}.
 *
 * @deprecated {@link Alerts} is bound to a client itself and is what
 *     {@code client.alerts()} answers; use it instead.
 */
@Deprecated
public final class AlertsResource implements BoundResource {

    private final Alerts alerts;

    public AlertsResource(ExsitedClient client) {
        this.alerts = new Alerts(client);
    }

    @Override
    public ExsitedClient getClient() {
        return alerts.getClient();
    }

    public ApiResponse<AlertsDto.Alerts> getAlerts(
            Map<String, Object> queryParams, RequestOptions options) {
        return alerts.getAlerts(queryParams, options);
    }

    public ApiResponse<AlertsDto.Alerts> getAlerts(Map<String, Object> queryParams) {
        return alerts.getAlerts(queryParams);
    }

    public ApiResponse<Dto> getAlert(
            String alertId, Map<String, Object> queryParams, RequestOptions options) {
        return alerts.getAlert(alertId, queryParams, options);
    }

    public ApiResponse<Dto> getAlert(String alertId, Map<String, Object> queryParams) {
        return alerts.getAlert(alertId, queryParams);
    }

    public ApiResponse<AlertsDto.Alerts> getAlertsAccountById(
            String accountId, Map<String, Object> queryParams, RequestOptions options) {
        return alerts.getAlertsAccountById(accountId, queryParams, options);
    }

    public ApiResponse<AlertsDto.Alerts> getAlertsAccountById(
            String accountId, Map<String, Object> queryParams) {
        return alerts.getAlertsAccountById(accountId, queryParams);
    }

    public ApiResponse<AlertsDto.Alerts> getAlertsEmployeeById(
            String employeeId, Map<String, Object> queryParams, RequestOptions options) {
        return alerts.getAlertsEmployeeById(employeeId, queryParams, options);
    }

    public ApiResponse<AlertsDto.Alerts> getAlertsEmployeeById(
            String employeeId, Map<String, Object> queryParams) {
        return alerts.getAlertsEmployeeById(employeeId, queryParams);
    }

    public ApiResponse<AlertsDto.Alerts> getAlertsLabourById(
            String labourId, Map<String, Object> queryParams, RequestOptions options) {
        return alerts.getAlertsLabourById(labourId, queryParams, options);
    }

    public ApiResponse<AlertsDto.Alerts> getAlertsLabourById(String labourId, Map<String, Object> queryParams) {
        return alerts.getAlertsLabourById(labourId, queryParams);
    }
}
