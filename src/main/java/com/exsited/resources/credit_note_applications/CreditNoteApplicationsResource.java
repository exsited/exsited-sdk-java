package com.exsited.resources.credit_note_applications;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.credit_note_applications.credit_note_applications_dto.CreditNoteApplicationsDto.CreditNoteApplicationList;
import com.exsited.resources.credit_note_applications.credit_note_applications_dto.CreditNoteApplicationsDto.CreditNoteApplicationRead;
import com.exsited.resources.credit_note_applications.credit_note_applications_dto.CreditNoteApplicationsDto.InvoiceCreditNoteApplications;
import java.util.Map;

/**
 * Forwards every call to {@link CreditNoteApplications}.
 *
 * @deprecated {@link CreditNoteApplications} is bound to a client itself and is what
 *     {@code client.creditNoteApplications()} answers; use it instead.
 */
@Deprecated
public final class CreditNoteApplicationsResource implements BoundResource {

    private final CreditNoteApplications creditNoteApplications;

    public CreditNoteApplicationsResource(ExsitedClient client) {
        this.creditNoteApplications = new CreditNoteApplications(client);
    }

    @Override
    public ExsitedClient getClient() {
        return creditNoteApplications.getClient();
    }

    public ApiResponse<CreditNoteApplicationList> getCreditNoteApplications(
            Map<String, Object> queryParams, RequestOptions options) {
        return creditNoteApplications.getCreditNoteApplications(queryParams, options);
    }

    public ApiResponse<CreditNoteApplicationList> getCreditNoteApplications(
            Map<String, Object> queryParams) {
        return creditNoteApplications.getCreditNoteApplications(queryParams);
    }

    public ApiResponse<CreditNoteApplicationRead> getCreditNoteApplication(
            String creditNoteApplicationId, Map<String, Object> queryParams, RequestOptions options) {
        return creditNoteApplications.getCreditNoteApplication(creditNoteApplicationId, queryParams, options);
    }

    public ApiResponse<CreditNoteApplicationRead> getCreditNoteApplication(
            String creditNoteApplicationId, Map<String, Object> queryParams) {
        return creditNoteApplications.getCreditNoteApplication(creditNoteApplicationId, queryParams);
    }

    public ApiResponse<InvoiceCreditNoteApplications> getInvoiceCreditNoteApplications(
            String invoiceId, Map<String, Object> queryParams, RequestOptions options) {
        return creditNoteApplications.getInvoiceCreditNoteApplications(invoiceId, queryParams, options);
    }

    public ApiResponse<InvoiceCreditNoteApplications> getInvoiceCreditNoteApplications(
            String invoiceId, Map<String, Object> queryParams) {
        return creditNoteApplications.getInvoiceCreditNoteApplications(invoiceId, queryParams);
    }
}
