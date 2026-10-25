package com.exsited.resources.gift_certificates;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.gift_certificates.gift_certificates_dto.GiftCertificatesDto.GiftCertificate;
import com.exsited.resources.gift_certificates.gift_certificates_dto.GiftCertificatesDto.GiftCertificateAllocationRead;
import com.exsited.resources.gift_certificates.gift_certificates_dto.GiftCertificatesDto.GiftCertificateAllocations;
import com.exsited.resources.gift_certificates.gift_certificates_dto.GiftCertificatesDto.GiftCertificateList;
import com.exsited.resources.gift_certificates.gift_certificates_dto.GiftCertificatesDto.GiftCertificateTransactionList;
import com.exsited.resources.gift_certificates.gift_certificates_dto.GiftCertificatesDto.GiftCertificateTransactionRead;
import java.util.Map;

/** Gift certificate endpoints, under {@code /api/{api_version}/gift_certificates}. */
public final class GiftCertificates implements BoundResource {

    private final ExsitedClient client;

    public GiftCertificates(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List all: {@code GET /gift_certificates}. */
    public ApiResponse<GiftCertificateList> getGiftCertificates(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(GiftCertificatesUrls.GIFT_CERTIFICATES,
                reopen(options).queryParams(queryParams).build(), GiftCertificateList.class);
    }

    public ApiResponse<GiftCertificateList> getGiftCertificates(
            Map<String, Object> queryParams) {
        return getGiftCertificates(queryParams, null);
    }

    public ApiResponse<GiftCertificateList> getGiftCertificates() {
        return getGiftCertificates(null, null);
    }

    /** Create: {@code POST /gift_certificates}. */
    public ApiResponse<GiftCertificate> createGiftCertificate(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(GiftCertificatesUrls.GIFT_CERTIFICATES,
                reopen(options).jsonBody(payload).build(), GiftCertificate.class);
    }

    public ApiResponse<GiftCertificate> createGiftCertificate(
            Object payload) {
        return createGiftCertificate(payload, null);
    }

    /** Delete: {@code DELETE /gift_certificates/{gc_id}}. No typed view; read {@code getMap()}. */
    public ApiResponse<Dto> deleteGiftCertificate(
            String gcId, RequestOptions options) {
        requireValue(gcId, "gc_id");
        return client.sendDeleteRequest(GiftCertificatesUrls.GIFT_CERTIFICATE,
                reopen(options).pathParam("gc_id", gcId).build(), null);
    }

    public ApiResponse<Dto> deleteGiftCertificate(
            String gcId) {
        return deleteGiftCertificate(gcId, null);
    }

    /** Single detail: {@code GET /gift_certificates/{gc_id}}. */
    public ApiResponse<GiftCertificate> getGiftCertificate(
            String gcId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(gcId, "gc_id");
        return client.sendGetRequest(GiftCertificatesUrls.GIFT_CERTIFICATE,
                reopen(options).pathParam("gc_id", gcId).queryParams(queryParams).build(), GiftCertificate.class);
    }

    public ApiResponse<GiftCertificate> getGiftCertificate(
            String gcId, Map<String, Object> queryParams) {
        return getGiftCertificate(gcId, queryParams, null);
    }

    public ApiResponse<GiftCertificate> getGiftCertificate(String gcId) {
        return getGiftCertificate(gcId, null, null);
    }

    /**
     * Update: {@code PATCH /gift_certificates/{gc_id}}.
     */
    public ApiResponse<GiftCertificate> updateGiftCertificate(
            String gcId, Object payload, RequestOptions options) {
        requireValue(gcId, "gc_id");
        requirePayload(payload);
        return client.sendPatchRequest(GiftCertificatesUrls.GIFT_CERTIFICATE,
                reopen(options).pathParam("gc_id", gcId).jsonBody(payload).build(), GiftCertificate.class);
    }

    public ApiResponse<GiftCertificate> updateGiftCertificate(
            String gcId, Object payload) {
        return updateGiftCertificate(gcId, payload, null);
    }

    /** List gift certificate allocations: {@code GET /gift_certificates/{gc_id}/allocations}. */
    public ApiResponse<GiftCertificateAllocations> getGiftCertificateAllocations(
            String gcId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(gcId, "gc_id");
        return client.sendGetRequest(GiftCertificatesUrls.GIFT_CERTIFICATE_ALLOCATIONS,
                reopen(options).pathParam("gc_id", gcId).queryParams(queryParams).build(), GiftCertificateAllocations.class);
    }

    public ApiResponse<GiftCertificateAllocations> getGiftCertificateAllocations(
            String gcId, Map<String, Object> queryParams) {
        return getGiftCertificateAllocations(gcId, queryParams, null);
    }

    public ApiResponse<GiftCertificateAllocations> getGiftCertificateAllocations(String gcId) {
        return getGiftCertificateAllocations(gcId, null, null);
    }

    /** List gift certificate transactions: {@code GET /gift_certificates/{gc_id}/transactions}. */
    public ApiResponse<GiftCertificateTransactionList> getGiftCertificateTransactions(
            String gcId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(gcId, "gc_id");
        return client.sendGetRequest(GiftCertificatesUrls.GIFT_CERTIFICATE_TRANSACTIONS,
                reopen(options).pathParam("gc_id", gcId).queryParams(queryParams).build(), GiftCertificateTransactionList.class);
    }

    public ApiResponse<GiftCertificateTransactionList> getGiftCertificateTransactions(
            String gcId, Map<String, Object> queryParams) {
        return getGiftCertificateTransactions(gcId, queryParams, null);
    }

    public ApiResponse<GiftCertificateTransactionList> getGiftCertificateTransactions(String gcId) {
        return getGiftCertificateTransactions(gcId, null, null);
    }

    /**
     * Set a new balance: {@code POST /gift_certificates/{gc_id}/amend}, as {@code
     * {"gift_certificate": {"new_balance": ...}}}; a balance below the used amount is a
     * {@code 400}.
     */
    public ApiResponse<GiftCertificate> amendGiftCertificate(
            String gcId, Object payload, RequestOptions options) {
        requireValue(gcId, "gc_id");
        requirePayload(payload);
        return client.sendPostRequest(GiftCertificatesUrls.GIFT_CERTIFICATE_AMEND,
                reopen(options).pathParam("gc_id", gcId).jsonBody(payload).build(), GiftCertificate.class);
    }

    public ApiResponse<GiftCertificate> amendGiftCertificate(String gcId, Object payload) {
        return amendGiftCertificate(gcId, payload, null);
    }

    /**
     * Allocate to an account: {@code POST /gift_certificates/{gc_id}/allocations}, as {@code
     * {"gift_certificate": {"account": {"code": ...}}}}; {@code 200}, not {@code 201}.
     */
    public ApiResponse<GiftCertificate> allocateGiftCertificate(
            String gcId, Object payload, RequestOptions options) {
        requireValue(gcId, "gc_id");
        requirePayload(payload);
        return client.sendPostRequest(GiftCertificatesUrls.GIFT_CERTIFICATE_ALLOCATIONS,
                reopen(options).pathParam("gc_id", gcId).jsonBody(payload).build(), GiftCertificate.class);
    }

    public ApiResponse<GiftCertificate> allocateGiftCertificate(String gcId, Object payload) {
        return allocateGiftCertificate(gcId, payload, null);
    }

    /**
     * Take an allocation back from an account:
     * {@code POST /gift_certificates/{gc_id}/deallocations}, with the same body
     * {@code allocateGiftCertificate} takes.
     */
    public ApiResponse<GiftCertificate> deallocateGiftCertificate(
            String gcId, Object payload, RequestOptions options) {
        requireValue(gcId, "gc_id");
        requirePayload(payload);
        return client.sendPostRequest(GiftCertificatesUrls.GIFT_CERTIFICATE_DEALLOCATIONS,
                reopen(options).pathParam("gc_id", gcId).jsonBody(payload).build(), GiftCertificate.class);
    }

    public ApiResponse<GiftCertificate> deallocateGiftCertificate(String gcId, Object payload) {
        return deallocateGiftCertificate(gcId, payload, null);
    }

    /**
     * Retrieve one allocation: {@code GET /gift_certificates/{gc_id}/allocations/{allocation_id}},
     * under {@code gift_certificate_allocation}.
     */
    public ApiResponse<GiftCertificateAllocationRead> getGiftCertificateAllocation(
            String gcId, String allocationId, RequestOptions options) {
        requireValue(gcId, "gc_id");
        requireValue(allocationId, "allocation_id");
        return client.sendGetRequest(GiftCertificatesUrls.GIFT_CERTIFICATE_ALLOCATION,
                reopen(options).pathParam("gc_id", gcId).pathParam("allocation_id", allocationId).build(), GiftCertificateAllocationRead.class);
    }

    public ApiResponse<GiftCertificateAllocationRead> getGiftCertificateAllocation(
            String gcId, String allocationId) {
        return getGiftCertificateAllocation(gcId, allocationId, null);
    }

    /**
     * Retrieve one transaction:
     * {@code GET /gift_certificates/{gc_id}/transactions/{transaction_id}}, under
     * {@code gift_certificate_transaction}.
     */
    public ApiResponse<GiftCertificateTransactionRead> getGiftCertificateTransaction(
            String gcId, String transactionId, RequestOptions options) {
        requireValue(gcId, "gc_id");
        requireValue(transactionId, "transaction_id");
        return client.sendGetRequest(GiftCertificatesUrls.GIFT_CERTIFICATE_TRANSACTION,
                reopen(options).pathParam("gc_id", gcId).pathParam("transaction_id", transactionId).build(), GiftCertificateTransactionRead.class);
    }

    public ApiResponse<GiftCertificateTransactionRead> getGiftCertificateTransaction(
            String gcId, String transactionId) {
        return getGiftCertificateTransaction(gcId, transactionId, null);
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
