package com.exsited.resources.purchase_credit_note_applications;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.purchase_credit_note_applications.purchase_credit_note_applications_dto.PurchaseCreditNoteApplicationsDto.PurchaseCreditNoteApplicationList;
import com.exsited.resources.purchase_credit_note_applications.purchase_credit_note_applications_dto.PurchaseCreditNoteApplicationsDto.PurchaseCreditNoteApplicationRead;
import java.util.Map;

/**
 * Forwards every call to {@link PurchaseCreditNoteApplications}.
 *
 * @deprecated {@link PurchaseCreditNoteApplications} is bound to a client itself and is what
 *     {@code client.purchaseCreditNoteApplications()} answers; use it instead.
 */
@Deprecated
public final class PurchaseCreditNoteApplicationsResource implements BoundResource {

    private final PurchaseCreditNoteApplications purchaseCreditNoteApplications;

    public PurchaseCreditNoteApplicationsResource(ExsitedClient client) {
        this.purchaseCreditNoteApplications = new PurchaseCreditNoteApplications(client);
    }

    @Override
    public ExsitedClient getClient() {
        return purchaseCreditNoteApplications.getClient();
    }

    public ApiResponse<PurchaseCreditNoteApplicationList> getPurchaseCreditNoteApplications(
            Map<String, Object> queryParams, RequestOptions options) {
        return purchaseCreditNoteApplications.getPurchaseCreditNoteApplications(queryParams, options);
    }

    public ApiResponse<PurchaseCreditNoteApplicationList> getPurchaseCreditNoteApplications(
            Map<String, Object> queryParams) {
        return purchaseCreditNoteApplications.getPurchaseCreditNoteApplications(queryParams);
    }

    public ApiResponse<PurchaseCreditNoteApplicationRead> getPurchaseCreditNoteApplication(
            String purchaseCreditNoteApplicationId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseCreditNoteApplications.getPurchaseCreditNoteApplication(purchaseCreditNoteApplicationId, queryParams, options);
    }

    public ApiResponse<PurchaseCreditNoteApplicationRead> getPurchaseCreditNoteApplication(
            String purchaseCreditNoteApplicationId, Map<String, Object> queryParams) {
        return purchaseCreditNoteApplications.getPurchaseCreditNoteApplication(purchaseCreditNoteApplicationId, queryParams);
    }
}
