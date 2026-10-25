package com.exsited.resources.labours;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.labours.labours_dto.LaboursDto.Labour;
import com.exsited.resources.labours.labours_dto.LaboursDto.LabourAvailabilityList;
import com.exsited.resources.labours.labours_dto.LaboursDto.LabourList;
import com.exsited.resources.labours.labours_dto.LaboursDto.LabourOrderList;
import com.exsited.resources.labours.labours_dto.LaboursDto.LabourProfileList;
import com.exsited.resources.labours.labours_dto.LaboursDto.LabourProfileView;
import java.util.Map;

/**
 * Forwards every call to {@link Labours}.
 *
 * @deprecated {@link Labours} is bound to a client itself and is what
 *     {@code client.labours()} answers; use it instead.
 */
@Deprecated
public final class LaboursResource implements BoundResource {

    private final Labours labours;

    public LaboursResource(ExsitedClient client) {
        this.labours = new Labours(client);
    }

    @Override
    public ExsitedClient getClient() {
        return labours.getClient();
    }

    public ApiResponse<LabourAvailabilityList> getLabourAvailability(
            Map<String, Object> queryParams, RequestOptions options) {
        return labours.getLabourAvailability(queryParams, options);
    }

    public ApiResponse<LabourAvailabilityList> getLabourAvailability(
            Map<String, Object> queryParams) {
        return labours.getLabourAvailability(queryParams);
    }

    public ApiResponse<LabourProfileList> getLabourProfiles(
            Map<String, Object> queryParams, RequestOptions options) {
        return labours.getLabourProfiles(queryParams, options);
    }

    public ApiResponse<LabourProfileList> getLabourProfiles(Map<String, Object> queryParams) {
        return labours.getLabourProfiles(queryParams);
    }

    public ApiResponse<LabourProfileView> createLabourProfile(Object payload, RequestOptions options) {
        return labours.createLabourProfile(payload, options);
    }

    public ApiResponse<LabourProfileView> createLabourProfile(Object payload) {
        return labours.createLabourProfile(payload);
    }

    public ApiResponse<LabourList> getLabours(
            Map<String, Object> queryParams, RequestOptions options) {
        return labours.getLabours(queryParams, options);
    }

    public ApiResponse<LabourList> getLabours(Map<String, Object> queryParams) {
        return labours.getLabours(queryParams);
    }

    public ApiResponse<Labour> createLabour(Object payload, RequestOptions options) {
        return labours.createLabour(payload, options);
    }

    public ApiResponse<Labour> createLabour(Object payload) {
        return labours.createLabour(payload);
    }

    public ApiResponse<LabourProfileView> getLabourProfile(
            String labourProfileId, Map<String, Object> queryParams, RequestOptions options) {
        return labours.getLabourProfile(labourProfileId, queryParams, options);
    }

    public ApiResponse<LabourProfileView> getLabourProfile(
            String labourProfileId, Map<String, Object> queryParams) {
        return labours.getLabourProfile(labourProfileId, queryParams);
    }

    public ApiResponse<Dto> updateLabourProfile(
            String labourProfileId, Object payload, RequestOptions options) {
        return labours.updateLabourProfile(labourProfileId, payload, options);
    }

    public ApiResponse<Dto> updateLabourProfile(String labourProfileId, Object payload) {
        return labours.updateLabourProfile(labourProfileId, payload);
    }

    public ApiResponse<Dto> deleteLabourProfile(String labourProfileId, RequestOptions options) {
        return labours.deleteLabourProfile(labourProfileId, options);
    }

    public ApiResponse<Dto> deleteLabourProfile(String labourProfileId) {
        return labours.deleteLabourProfile(labourProfileId);
    }

    public ApiResponse<LabourList> getLaboursAvailableForBooking(
            Map<String, Object> queryParams, RequestOptions options) {
        return labours.getLaboursAvailableForBooking(queryParams, options);
    }

    public ApiResponse<LabourList> getLaboursAvailableForBooking(Map<String, Object> queryParams) {
        return labours.getLaboursAvailableForBooking(queryParams);
    }

    public ApiResponse<Labour> updateLabour(
            String labourUuid, Object payload, RequestOptions options) {
        return labours.updateLabour(labourUuid, payload, options);
    }

    public ApiResponse<Labour> updateLabour(String labourUuid, Object payload) {
        return labours.updateLabour(labourUuid, payload);
    }

    public ApiResponse<Dto> deleteLabour(String labourUuid, RequestOptions options) {
        return labours.deleteLabour(labourUuid, options);
    }

    public ApiResponse<Dto> deleteLabour(String labourUuid) {
        return labours.deleteLabour(labourUuid);
    }

    public ApiResponse<Labour> getLabour(
            String labourUuid, Map<String, Object> queryParams, RequestOptions options) {
        return labours.getLabour(labourUuid, queryParams, options);
    }

    public ApiResponse<Labour> getLabour(String labourUuid, Map<String, Object> queryParams) {
        return labours.getLabour(labourUuid, queryParams);
    }

    public ApiResponse<Labour> cancelLabour(String labourId, Object payload, RequestOptions options) {
        return labours.cancelLabour(labourId, payload, options);
    }

    public ApiResponse<Labour> cancelLabour(String labourId, Object payload) {
        return labours.cancelLabour(labourId, payload);
    }

    public ApiResponse<Labour> reactivateLabour(
            String labourId, Object payload, RequestOptions options) {
        return labours.reactivateLabour(labourId, payload, options);
    }

    public ApiResponse<Labour> reactivateLabour(String labourId, Object payload) {
        return labours.reactivateLabour(labourId, payload);
    }

    public ApiResponse<LabourOrderList> getLabourOrders(
            String labourUuid, Map<String, Object> queryParams, RequestOptions options) {
        return labours.getLabourOrders(labourUuid, queryParams, options);
    }

    public ApiResponse<LabourOrderList> getLabourOrders(String labourUuid, Map<String, Object> queryParams) {
        return labours.getLabourOrders(labourUuid, queryParams);
    }
}
