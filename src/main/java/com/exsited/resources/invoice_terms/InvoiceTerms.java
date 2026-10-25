package com.exsited.resources.invoice_terms;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.invoice_terms.invoice_terms_dto.InvoiceTermsDto.SettingsInvoiceTerm;
import com.exsited.resources.invoice_terms.invoice_terms_dto.InvoiceTermsDto.SettingsInvoiceTermList;
import com.exsited.resources.invoice_terms.invoice_terms_dto.InvoiceTermsDto.SettingsInvoiceTermWrite;
import java.util.Map;

/** Invoice term endpoints, under {@code /api/{api_version}/settings/invoice_terms}. */
public final class InvoiceTerms implements BoundResource {

    private final ExsitedClient client;

    public InvoiceTerms(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List invoice terms: {@code GET /settings/invoice_terms}. */
    public ApiResponse<SettingsInvoiceTermList> getSettingsInvoiceTerms(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(InvoiceTermsUrls.SETTINGS_INVOICE_TERMS,
                reopen(options).queryParams(queryParams).build(), SettingsInvoiceTermList.class);
    }

    public ApiResponse<SettingsInvoiceTermList> getSettingsInvoiceTerms(Map<String, Object> queryParams) {
        return getSettingsInvoiceTerms(queryParams, null);
    }

    public ApiResponse<SettingsInvoiceTermList> getSettingsInvoiceTerms() {
        return getSettingsInvoiceTerms(null, null);
    }

    /**
     * Create an invoice term: {@code POST /settings/invoice_terms}; the answer is flat, where a
     * read nests {@code duration}.
     */
    public ApiResponse<SettingsInvoiceTermWrite> createSettingsInvoiceTerm(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(InvoiceTermsUrls.SETTINGS_INVOICE_TERMS,
                reopen(options).jsonBody(payload).build(), SettingsInvoiceTermWrite.class);
    }

    public ApiResponse<SettingsInvoiceTermWrite> createSettingsInvoiceTerm(Object payload) {
        return createSettingsInvoiceTerm(payload, null);
    }

    /** Get one invoice term: {@code GET /settings/invoice_terms/{invoice_term_id}}. */
    public ApiResponse<SettingsInvoiceTerm> getSettingsInvoiceTerm(
            String invoiceTermId, RequestOptions options) {
        requireValue(invoiceTermId, "invoice_term_id");
        return client.sendGetRequest(InvoiceTermsUrls.SETTINGS_INVOICE_TERM,
                reopen(options).pathParam("invoice_term_id", invoiceTermId).build(), SettingsInvoiceTerm.class);
    }

    public ApiResponse<SettingsInvoiceTerm> getSettingsInvoiceTerm(String invoiceTermId) {
        return getSettingsInvoiceTerm(invoiceTermId, null);
    }

    /**
     * Delete an invoice term: {@code DELETE /settings/invoice_terms/{invoice_term_id}}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteSettingsInvoiceTerm(
            String invoiceTermId, RequestOptions options) {
        requireValue(invoiceTermId, "invoice_term_id");
        return client.sendDeleteRequest(InvoiceTermsUrls.SETTINGS_INVOICE_TERM,
                reopen(options).pathParam("invoice_term_id", invoiceTermId).build(), null);
    }

    public ApiResponse<Dto> deleteSettingsInvoiceTerm(String invoiceTermId) {
        return deleteSettingsInvoiceTerm(invoiceTermId, null);
    }

    /**
     * Update or enable/disable an invoice term:
     * {@code PATCH /settings/invoice_terms/{invoice_term_id}}; the answer is flat, where a read
     * nests {@code duration}.
     */
    public ApiResponse<SettingsInvoiceTermWrite> updateSettingsInvoiceTerm(
            String invoiceTermId, Object payload, RequestOptions options) {
        requireValue(invoiceTermId, "invoice_term_id");
        requirePayload(payload);
        return client.sendPatchRequest(InvoiceTermsUrls.SETTINGS_INVOICE_TERM,
                reopen(options).pathParam("invoice_term_id", invoiceTermId).jsonBody(payload).build(), SettingsInvoiceTermWrite.class);
    }

    public ApiResponse<SettingsInvoiceTermWrite> updateSettingsInvoiceTerm(
            String invoiceTermId, Object payload) {
        return updateSettingsInvoiceTerm(invoiceTermId, payload, null);
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
