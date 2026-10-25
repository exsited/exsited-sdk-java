package com.exsited.resources.timesheets;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.timesheets.timesheets_dto.TimesheetsDto.TimesheetList;
import com.exsited.resources.timesheets.timesheets_dto.TimesheetsDto.TimesheetUpdated;
import com.exsited.resources.timesheets.timesheets_dto.TimesheetsDto;
import java.util.Map;

/** Timesheet endpoints, under {@code /api/{api_version}/timesheets}. */
public final class Timesheets implements BoundResource {

    private final ExsitedClient client;

    public Timesheets(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List all timesheets: {@code GET /timesheets}. */
    public ApiResponse<TimesheetList> getTimesheets(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(TimesheetsUrls.TIMESHEETS,
                reopen(options).queryParams(queryParams).build(), TimesheetList.class);
    }

    public ApiResponse<TimesheetList> getTimesheets(
            Map<String, Object> queryParams) {
        return getTimesheets(queryParams, null);
    }

    public ApiResponse<TimesheetList> getTimesheets() {
        return getTimesheets(null, null);
    }

    /**
     * Create timesheet: {@code POST /timesheets}.
     */
    public ApiResponse<TimesheetsDto.Timesheets> createTimesheet(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(TimesheetsUrls.TIMESHEETS,
                reopen(options).jsonBody(payload).build(), TimesheetsDto.Timesheets.class);
    }

    public ApiResponse<TimesheetsDto.Timesheets> createTimesheet(
            Object payload) {
        return createTimesheet(payload, null);
    }

    /**
     * Delete timesheet: {@code DELETE /timesheets/{timesheet_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteTimesheet(
            String createdTimesheetId, RequestOptions options) {
        requireValue(createdTimesheetId, "timesheet_id");
        return client.sendDeleteRequest(TimesheetsUrls.TIMESHEET,
                reopen(options).pathParam("timesheet_id", createdTimesheetId).build(), null);
    }

    public ApiResponse<Dto> deleteTimesheet(
            String createdTimesheetId) {
        return deleteTimesheet(createdTimesheetId, null);
    }

    /**
     * Update timesheet: {@code PATCH /timesheets/{timesheet_id}}.
     */
    public ApiResponse<TimesheetUpdated> updateTimesheet(
            String createdTimesheetId, Object payload, RequestOptions options) {
        requireValue(createdTimesheetId, "timesheet_id");
        requirePayload(payload);
        return client.sendPatchRequest(TimesheetsUrls.TIMESHEET,
                reopen(options).pathParam("timesheet_id", createdTimesheetId).jsonBody(payload).build(), TimesheetUpdated.class);
    }

    public ApiResponse<TimesheetUpdated> updateTimesheet(
            String createdTimesheetId, Object payload) {
        return updateTimesheet(createdTimesheetId, payload, null);
    }

    /**
     * Get one timesheet: {@code GET /timesheets/{timesheet_id}}, by the {@code ts_} id the listing
     * and the create answer.
     */
    public ApiResponse<TimesheetsDto.Timesheets> getTimesheet(
            String timesheetId, RequestOptions options) {
        requireValue(timesheetId, "timesheet_id");
        return client.sendGetRequest(TimesheetsUrls.TIMESHEET,
                reopen(options).pathParam("timesheet_id", timesheetId).build(), TimesheetsDto.Timesheets.class);
    }

    public ApiResponse<TimesheetsDto.Timesheets> getTimesheet(String timesheetId) {
        return getTimesheet(timesheetId, null);
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
