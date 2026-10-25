package com.exsited.resources.labours;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.labours.labours_dto.LaboursDto.Labour;
import com.exsited.resources.labours.labours_dto.LaboursDto.LabourAvailabilityList;
import com.exsited.resources.labours.labours_dto.LaboursDto.LabourCalendar;
import com.exsited.resources.labours.labours_dto.LaboursDto.LabourList;
import com.exsited.resources.labours.labours_dto.LaboursDto.LabourOrderList;
import com.exsited.resources.labours.labours_dto.LaboursDto.LabourProfileList;
import com.exsited.resources.labours.labours_dto.LaboursDto.LabourProfileSessionList;
import com.exsited.resources.labours.labours_dto.LaboursDto.LabourProfileSessionView;
import com.exsited.resources.labours.labours_dto.LaboursDto.LabourProfileView;
import java.util.Map;

/** Labour endpoints, under {@code /api/{api_version}}. */
public final class Labours implements BoundResource {

    private final ExsitedClient client;

    public Labours(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** Labour availability: {@code GET /labour_availability}. */
    public ApiResponse<LabourAvailabilityList> getLabourAvailability(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(LaboursUrls.LABOUR_AVAILABILITY,
                reopen(options).queryParams(queryParams).build(), LabourAvailabilityList.class);
    }

    public ApiResponse<LabourAvailabilityList> getLabourAvailability(
            Map<String, Object> queryParams) {
        return getLabourAvailability(queryParams, null);
    }

    public ApiResponse<LabourAvailabilityList> getLabourAvailability() {
        return getLabourAvailability(null, null);
    }

    /** List labour profiles: {@code GET /labour_profiles}. */
    public ApiResponse<LabourProfileList> getLabourProfiles(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(LaboursUrls.LABOUR_PROFILES,
                reopen(options).queryParams(queryParams).build(), LabourProfileList.class);
    }

    public ApiResponse<LabourProfileList> getLabourProfiles(
            Map<String, Object> queryParams) {
        return getLabourProfiles(queryParams, null);
    }

    public ApiResponse<LabourProfileList> getLabourProfiles() {
        return getLabourProfiles(null, null);
    }

    /**
     * Create labour profile: {@code POST /labour_profiles}.
     */
    public ApiResponse<LabourProfileView> createLabourProfile(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(LaboursUrls.LABOUR_PROFILES,
                reopen(options).jsonBody(payload).build(), LabourProfileView.class);
    }

    public ApiResponse<LabourProfileView> createLabourProfile(
            Object payload) {
        return createLabourProfile(payload, null);
    }

    /** List labours: {@code GET /labours}. */
    public ApiResponse<LabourList> getLabours(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(LaboursUrls.LABOURS,
                reopen(options).queryParams(queryParams).build(), LabourList.class);
    }

    public ApiResponse<LabourList> getLabours(
            Map<String, Object> queryParams) {
        return getLabours(queryParams, null);
    }

    public ApiResponse<LabourList> getLabours() {
        return getLabours(null, null);
    }

    /** Create labour: {@code POST /labours}. */
    public ApiResponse<Labour> createLabour(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(LaboursUrls.LABOURS,
                reopen(options).jsonBody(payload).build(), Labour.class);
    }

    public ApiResponse<Labour> createLabour(
            Object payload) {
        return createLabour(payload, null);
    }

    /**
     * Get labour profile: {@code GET /labour_profiles/{labour_profile_id}}.
     */
    public ApiResponse<LabourProfileView> getLabourProfile(
            String labourProfileId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(labourProfileId, "labour_profile_id");
        return client.sendGetRequest(LaboursUrls.LABOUR_PROFILE,
                reopen(options).pathParam("labour_profile_id", labourProfileId).queryParams(queryParams).build(), LabourProfileView.class);
    }

    public ApiResponse<LabourProfileView> getLabourProfile(
            String labourProfileId, Map<String, Object> queryParams) {
        return getLabourProfile(labourProfileId, queryParams, null);
    }

    public ApiResponse<LabourProfileView> getLabourProfile(String labourProfileId) {
        return getLabourProfile(labourProfileId, null, null);
    }

    /**
     * Update labour profile: {@code PATCH /labour_profiles/{labour_profile_id}}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> updateLabourProfile(
            String labourProfileId, Object payload, RequestOptions options) {
        requireValue(labourProfileId, "labour_profile_id");
        requirePayload(payload);
        return client.sendPatchRequest(LaboursUrls.LABOUR_PROFILE,
                reopen(options).pathParam("labour_profile_id", labourProfileId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateLabourProfile(
            String labourProfileId, Object payload) {
        return updateLabourProfile(labourProfileId, payload, null);
    }

    /**
     * Delete labour profile: {@code DELETE /labour_profiles/{labour_profile_id}}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteLabourProfile(
            String labourProfileId, RequestOptions options) {
        requireValue(labourProfileId, "labour_profile_id");
        return client.sendDeleteRequest(LaboursUrls.LABOUR_PROFILE,
                reopen(options).pathParam("labour_profile_id", labourProfileId).build(), null);
    }

    public ApiResponse<Dto> deleteLabourProfile(
            String labourProfileId) {
        return deleteLabourProfile(labourProfileId, null);
    }

    /** Available labours: {@code GET /labours/available_for_booking}. */
    public ApiResponse<LabourList> getLaboursAvailableForBooking(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(LaboursUrls.LABOURS_AVAILABLE_FOR_BOOKING,
                reopen(options).queryParams(queryParams).build(), LabourList.class);
    }

    public ApiResponse<LabourList> getLaboursAvailableForBooking(
            Map<String, Object> queryParams) {
        return getLaboursAvailableForBooking(queryParams, null);
    }

    public ApiResponse<LabourList> getLaboursAvailableForBooking() {
        return getLaboursAvailableForBooking(null, null);
    }

    /**
     * Update labour: {@code PATCH /labours/{labour_uuid}}.
     */
    public ApiResponse<Labour> updateLabour(
            String labourUuid, Object payload, RequestOptions options) {
        requireValue(labourUuid, "labour_uuid");
        requirePayload(payload);
        return client.sendPatchRequest(LaboursUrls.LABOUR,
                reopen(options).pathParam("labour_uuid", labourUuid).jsonBody(payload).build(), Labour.class);
    }

    public ApiResponse<Labour> updateLabour(
            String labourUuid, Object payload) {
        return updateLabour(labourUuid, payload, null);
    }

    /**
     * Delete labour: {@code DELETE /labours/{labour_uuid}}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteLabour(
            String labourUuid, RequestOptions options) {
        requireValue(labourUuid, "labour_uuid");
        return client.sendDeleteRequest(LaboursUrls.LABOUR,
                reopen(options).pathParam("labour_uuid", labourUuid).build(), null);
    }

    public ApiResponse<Dto> deleteLabour(
            String labourUuid) {
        return deleteLabour(labourUuid, null);
    }

    /** Get labour: {@code GET /labours/{labour_uuid}}. */
    public ApiResponse<Labour> getLabour(
            String labourUuid, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(labourUuid, "labour_uuid");
        return client.sendGetRequest(LaboursUrls.LABOUR,
                reopen(options).pathParam("labour_uuid", labourUuid).queryParams(queryParams).build(), Labour.class);
    }

    public ApiResponse<Labour> getLabour(
            String labourUuid, Map<String, Object> queryParams) {
        return getLabour(labourUuid, queryParams, null);
    }

    public ApiResponse<Labour> getLabour(String labourUuid) {
        return getLabour(labourUuid, null, null);
    }

    /**
     * Cancel labour: {@code POST /labours/{labour_id}/cancel}.
     */
    public ApiResponse<Labour> cancelLabour(
            String labourId, Object payload, RequestOptions options) {
        requireValue(labourId, "labour_id");
        requirePayload(payload);
        return client.sendPostRequest(LaboursUrls.LABOUR_CANCEL,
                reopen(options).pathParam("labour_id", labourId).jsonBody(payload).build(), Labour.class);
    }

    public ApiResponse<Labour> cancelLabour(
            String labourId, Object payload) {
        return cancelLabour(labourId, payload, null);
    }

    /**
     * Reactivate labour: {@code POST /labours/{labour_id}/reactivate}.
     */
    public ApiResponse<Labour> reactivateLabour(
            String labourId, Object payload, RequestOptions options) {
        requireValue(labourId, "labour_id");
        requirePayload(payload);
        return client.sendPostRequest(LaboursUrls.LABOUR_REACTIVATE,
                reopen(options).pathParam("labour_id", labourId).jsonBody(payload).build(), Labour.class);
    }

    public ApiResponse<Labour> reactivateLabour(
            String labourId, Object payload) {
        return reactivateLabour(labourId, payload, null);
    }

    /**
     * Get labour orders: {@code GET /labours/{labour_uuid}/orders}.
     */
    public ApiResponse<LabourOrderList> getLabourOrders(
            String labourUuid, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(labourUuid, "labour_uuid");
        return client.sendGetRequest(LaboursUrls.LABOUR_ORDERS,
                reopen(options).pathParam("labour_uuid", labourUuid).queryParams(queryParams).build(), LabourOrderList.class);
    }

    public ApiResponse<LabourOrderList> getLabourOrders(
            String labourUuid, Map<String, Object> queryParams) {
        return getLabourOrders(labourUuid, queryParams, null);
    }

    public ApiResponse<LabourOrderList> getLabourOrders(String labourUuid) {
        return getLabourOrders(labourUuid, null, null);
    }

    /**
     * Edit availability slots in bulk: {@code PATCH /labour_availability}; the body is
     * {@code {"labours": [{"id", "labour_availability", "labour_custom_availability"}]}},
     * {@code 204}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> updateLabourAvailability(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPatchRequest(LaboursUrls.LABOUR_AVAILABILITY,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> updateLabourAvailability(Object payload) {
        return updateLabourAvailability(payload, null);
    }

    /**
     * List a labour profile's sessions: {@code GET /labour_profiles/{labour_profile_id}/sessions}.
     */
    public ApiResponse<LabourProfileSessionList> getLabourProfileSessions(
            String labourProfileId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(labourProfileId, "labour_profile_id");
        return client.sendGetRequest(LaboursUrls.LABOUR_PROFILE_SESSIONS,
                reopen(options).pathParam("labour_profile_id", labourProfileId).queryParams(queryParams).build(), LabourProfileSessionList.class);
    }

    public ApiResponse<LabourProfileSessionList> getLabourProfileSessions(
            String labourProfileId, Map<String, Object> queryParams) {
        return getLabourProfileSessions(labourProfileId, queryParams, null);
    }

    public ApiResponse<LabourProfileSessionList> getLabourProfileSessions(String labourProfileId) {
        return getLabourProfileSessions(labourProfileId, null, null);
    }

    /**
     * Get one labour profile session:
     * {@code GET /labour_profiles/{labour_profile_id}/sessions/{session_id}}.
     */
    public ApiResponse<LabourProfileSessionView> getLabourProfileSession(
            String labourProfileId, String sessionId, RequestOptions options) {
        requireValue(labourProfileId, "labour_profile_id");
        requireValue(sessionId, "session_id");
        return client.sendGetRequest(LaboursUrls.LABOUR_PROFILE_SESSION,
                reopen(options).pathParam("labour_profile_id", labourProfileId).pathParam("session_id", sessionId).build(), LabourProfileSessionView.class);
    }

    public ApiResponse<LabourProfileSessionView> getLabourProfileSession(
            String labourProfileId, String sessionId) {
        return getLabourProfileSession(labourProfileId, sessionId, null);
    }

    /**
     * One labour's availability calendar: {@code GET /labours/{labour_id}/availability}, under
     * {@code labour_calendar}; the id or the code.
     */
    public ApiResponse<LabourCalendar> getLabourCalendar(
            String labourId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(labourId, "labour_id");
        return client.sendGetRequest(LaboursUrls.LABOUR_CALENDAR,
                reopen(options).pathParam("labour_id", labourId).queryParams(queryParams).build(), LabourCalendar.class);
    }

    public ApiResponse<LabourCalendar> getLabourCalendar(String labourId, Map<String, Object> queryParams) {
        return getLabourCalendar(labourId, queryParams, null);
    }

    public ApiResponse<LabourCalendar> getLabourCalendar(String labourId) {
        return getLabourCalendar(labourId, null, null);
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
