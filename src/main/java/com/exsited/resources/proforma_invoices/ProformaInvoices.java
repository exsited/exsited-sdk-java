package com.exsited.resources.proforma_invoices;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import java.util.Map;

/**
 * Pro-forma invoice endpoints, under {@code /api/{api_version}/proforma_invoices}. The server
 * lists only the pro-forma invoices whose invoice status is active.
 */
public final class ProformaInvoices implements BoundResource {

    private final ExsitedClient client;

    public ProformaInvoices(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /**
     * List pro-forma invoices: {@code GET /proforma_invoices}; paged by cursor, under {@code data}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getProformaInvoices(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(ProformaInvoicesUrls.PROFORMA_INVOICES,
                reopen(options).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getProformaInvoices(Map<String, Object> queryParams) {
        return getProformaInvoices(queryParams, null);
    }

    public ApiResponse<Dto> getProformaInvoices() {
        return getProformaInvoices(null, null);
    }

    /**
     * Retrieve one pro-forma invoice: {@code GET /proforma_invoices/{proforma_id}}, by its code;
     * under {@code proforma_invoice}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getProformaInvoice(
            String proformaId, RequestOptions options) {
        requireValue(proformaId, "proforma_id");
        return client.sendGetRequest(ProformaInvoicesUrls.PROFORMA_INVOICE,
                reopen(options).pathParam("proforma_id", proformaId).build(), null);
    }

    public ApiResponse<Dto> getProformaInvoice(String proformaId) {
        return getProformaInvoice(proformaId, null);
    }

    /**
     * Delete a pro-forma invoice: {@code DELETE /proforma_invoices/{proforma_id}}; {@code 204}. A
     * paid one is refused {@code 400} {@code already_paid}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteProformaInvoice(
            String proformaId, RequestOptions options) {
        requireValue(proformaId, "proforma_id");
        return client.sendDeleteRequest(ProformaInvoicesUrls.PROFORMA_INVOICE,
                reopen(options).pathParam("proforma_id", proformaId).build(), null);
    }

    public ApiResponse<Dto> deleteProformaInvoice(String proformaId) {
        return deleteProformaInvoice(proformaId, null);
    }

    /**
     * Pay a pro-forma invoice: {@code POST /proforma_invoices/{proforma_id}/payments}, wrapped in
     * {@code payment}; {@code 201}, answering the new payment's code as {@code payment.payment_id}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createProformaInvoicePayment(
            String proformaId, Object payload, RequestOptions options) {
        requireValue(proformaId, "proforma_id");
        requirePayload(payload);
        return client.sendPostRequest(ProformaInvoicesUrls.PROFORMA_INVOICE_PAYMENTS,
                reopen(options).pathParam("proforma_id", proformaId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createProformaInvoicePayment(String proformaId, Object payload) {
        return createProformaInvoicePayment(proformaId, payload, null);
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
