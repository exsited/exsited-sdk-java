package com.exsited.resources.credit_note_applications;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.credit_note_applications.credit_note_applications_dto.CreditNoteApplicationsDto.CreditNoteApplicationList;
import com.exsited.resources.credit_note_applications.credit_note_applications_dto.CreditNoteApplicationsDto.CreditNoteApplicationRead;
import com.exsited.resources.credit_note_applications.credit_note_applications_dto.CreditNoteApplicationsDto.InvoiceCreditNoteApplications;
import java.util.Map;

/** Credit note application endpoints, under {@code /api/{api_version}}. */
public final class CreditNoteApplications implements BoundResource {

    private final ExsitedClient client;

    public CreditNoteApplications(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List credit note applications: {@code GET /credit_note_applications}. */
    public ApiResponse<CreditNoteApplicationList> getCreditNoteApplications(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(CreditNoteApplicationsUrls.CREDIT_NOTE_APPLICATIONS,
                reopen(options).queryParams(queryParams).build(), CreditNoteApplicationList.class);
    }

    public ApiResponse<CreditNoteApplicationList> getCreditNoteApplications(
            Map<String, Object> queryParams) {
        return getCreditNoteApplications(queryParams, null);
    }

    public ApiResponse<CreditNoteApplicationList> getCreditNoteApplications() {
        return getCreditNoteApplications(null, null);
    }

    /** Get single: {@code GET /credit_note_applications/{credit_note_application_id}}. */
    public ApiResponse<CreditNoteApplicationRead> getCreditNoteApplication(
            String creditNoteApplicationId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(creditNoteApplicationId, "credit_note_application_id");
        return client.sendGetRequest(CreditNoteApplicationsUrls.CREDIT_NOTE_APPLICATION,
                reopen(options).pathParam("credit_note_application_id", creditNoteApplicationId).queryParams(queryParams).build(), CreditNoteApplicationRead.class);
    }

    public ApiResponse<CreditNoteApplicationRead> getCreditNoteApplication(
            String creditNoteApplicationId, Map<String, Object> queryParams) {
        return getCreditNoteApplication(creditNoteApplicationId, queryParams, null);
    }

    public ApiResponse<CreditNoteApplicationRead> getCreditNoteApplication(String creditNoteApplicationId) {
        return getCreditNoteApplication(creditNoteApplicationId, null, null);
    }

    /**
     * List invoice credit note applications:
     * {@code GET /invoices/{invoice_id}/credit_note_applications}.
     */
    public ApiResponse<InvoiceCreditNoteApplications> getInvoiceCreditNoteApplications(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(invoiceId, "invoice_id");
        return client.sendGetRequest(CreditNoteApplicationsUrls.INVOICE_CREDIT_NOTE_APPLICATIONS,
                reopen(options).pathParam("invoice_id", invoiceId).queryParams(queryParams).build(), InvoiceCreditNoteApplications.class);
    }

    public ApiResponse<InvoiceCreditNoteApplications> getInvoiceCreditNoteApplications(
            String invoiceId, Map<String, Object> queryParams) {
        return getInvoiceCreditNoteApplications(invoiceId, queryParams, null);
    }

    public ApiResponse<InvoiceCreditNoteApplications> getInvoiceCreditNoteApplications(String invoiceId) {
        return getInvoiceCreditNoteApplications(invoiceId, null, null);
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
