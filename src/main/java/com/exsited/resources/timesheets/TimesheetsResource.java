package com.exsited.resources.timesheets;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.timesheets.timesheets_dto.TimesheetsDto.TimesheetList;
import com.exsited.resources.timesheets.timesheets_dto.TimesheetsDto.TimesheetUpdated;
import com.exsited.resources.timesheets.timesheets_dto.TimesheetsDto;
import java.util.Map;

/**
 * Forwards every call to {@link Timesheets}.
 *
 * @deprecated {@link Timesheets} is bound to a client itself and is what
 *     {@code client.timesheets()} answers; use it instead.
 */
@Deprecated
public final class TimesheetsResource implements BoundResource {

    private final Timesheets timesheets;

    public TimesheetsResource(ExsitedClient client) {
        this.timesheets = new Timesheets(client);
    }

    @Override
    public ExsitedClient getClient() {
        return timesheets.getClient();
    }

    public ApiResponse<TimesheetList> getTimesheets(
            Map<String, Object> queryParams, RequestOptions options) {
        return timesheets.getTimesheets(queryParams, options);
    }

    public ApiResponse<TimesheetList> getTimesheets(Map<String, Object> queryParams) {
        return timesheets.getTimesheets(queryParams);
    }

    public ApiResponse<TimesheetsDto.Timesheets> createTimesheet(Object payload, RequestOptions options) {
        return timesheets.createTimesheet(payload, options);
    }

    public ApiResponse<TimesheetsDto.Timesheets> createTimesheet(Object payload) {
        return timesheets.createTimesheet(payload);
    }

    public ApiResponse<Dto> deleteTimesheet(String createdTimesheetId, RequestOptions options) {
        return timesheets.deleteTimesheet(createdTimesheetId, options);
    }

    public ApiResponse<Dto> deleteTimesheet(String createdTimesheetId) {
        return timesheets.deleteTimesheet(createdTimesheetId);
    }

    public ApiResponse<TimesheetUpdated> updateTimesheet(
            String createdTimesheetId, Object payload, RequestOptions options) {
        return timesheets.updateTimesheet(createdTimesheetId, payload, options);
    }

    public ApiResponse<TimesheetUpdated> updateTimesheet(String createdTimesheetId, Object payload) {
        return timesheets.updateTimesheet(createdTimesheetId, payload);
    }
}
