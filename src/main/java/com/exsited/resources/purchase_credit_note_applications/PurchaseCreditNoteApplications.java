package com.exsited.resources.purchase_credit_note_applications;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.purchase_credit_note_applications.purchase_credit_note_applications_dto.PurchaseCreditNoteApplicationsDto.PurchaseCreditNoteApplicationList;
import com.exsited.resources.purchase_credit_note_applications.purchase_credit_note_applications_dto.PurchaseCreditNoteApplicationsDto.PurchaseCreditNoteApplicationRead;
import java.util.Map;

/** Purchase credit note application endpoints, under {@code /api/{api_version}}. */
public final class PurchaseCreditNoteApplications implements BoundResource {

    private final ExsitedClient client;

    public PurchaseCreditNoteApplications(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List purchase credit note applications: {@code GET /purchase_credit_note_applications}. */
    public ApiResponse<PurchaseCreditNoteApplicationList> getPurchaseCreditNoteApplications(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(PurchaseCreditNoteApplicationsUrls.PURCHASE_CREDIT_NOTE_APPLICATIONS,
                reopen(options).queryParams(queryParams).build(), PurchaseCreditNoteApplicationList.class);
    }

    public ApiResponse<PurchaseCreditNoteApplicationList> getPurchaseCreditNoteApplications(
            Map<String, Object> queryParams) {
        return getPurchaseCreditNoteApplications(queryParams, null);
    }

    public ApiResponse<PurchaseCreditNoteApplicationList> getPurchaseCreditNoteApplications() {
        return getPurchaseCreditNoteApplications(null, null);
    }

    /**
     * Get single:
     * {@code GET /purchase_credit_note_applications/{purchase_credit_note_application_id}}.
     */
    public ApiResponse<PurchaseCreditNoteApplicationRead> getPurchaseCreditNoteApplication(
            String purchaseCreditNoteApplicationId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseCreditNoteApplicationId, "purchase_credit_note_application_id");
        return client.sendGetRequest(PurchaseCreditNoteApplicationsUrls.PURCHASE_CREDIT_NOTE_APPLICATION,
                reopen(options).pathParam("purchase_credit_note_application_id", purchaseCreditNoteApplicationId).queryParams(queryParams).build(), PurchaseCreditNoteApplicationRead.class);
    }

    public ApiResponse<PurchaseCreditNoteApplicationRead> getPurchaseCreditNoteApplication(
            String purchaseCreditNoteApplicationId, Map<String, Object> queryParams) {
        return getPurchaseCreditNoteApplication(purchaseCreditNoteApplicationId, queryParams, null);
    }

    public ApiResponse<PurchaseCreditNoteApplicationRead> getPurchaseCreditNoteApplication(String purchaseCreditNoteApplicationId) {
        return getPurchaseCreditNoteApplication(purchaseCreditNoteApplicationId, null, null);
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
