package com.exsited.resources.gift_certificates;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.gift_certificates.gift_certificates_dto.GiftCertificatesDto.GiftCertificate;
import com.exsited.resources.gift_certificates.gift_certificates_dto.GiftCertificatesDto.GiftCertificateAllocations;
import com.exsited.resources.gift_certificates.gift_certificates_dto.GiftCertificatesDto.GiftCertificateList;
import com.exsited.resources.gift_certificates.gift_certificates_dto.GiftCertificatesDto.GiftCertificateTransactionList;
import java.util.Map;

/**
 * Forwards every call to {@link GiftCertificates}.
 *
 * @deprecated {@link GiftCertificates} is bound to a client itself and is what
 *     {@code client.giftCertificates()} answers; use it instead.
 */
@Deprecated
public final class GiftCertificatesResource implements BoundResource {

    private final GiftCertificates giftCertificates;

    public GiftCertificatesResource(ExsitedClient client) {
        this.giftCertificates = new GiftCertificates(client);
    }

    @Override
    public ExsitedClient getClient() {
        return giftCertificates.getClient();
    }

    public ApiResponse<GiftCertificateList> getGiftCertificates(
            Map<String, Object> queryParams, RequestOptions options) {
        return giftCertificates.getGiftCertificates(queryParams, options);
    }

    public ApiResponse<GiftCertificateList> getGiftCertificates(Map<String, Object> queryParams) {
        return giftCertificates.getGiftCertificates(queryParams);
    }

    public ApiResponse<GiftCertificate> createGiftCertificate(Object payload, RequestOptions options) {
        return giftCertificates.createGiftCertificate(payload, options);
    }

    public ApiResponse<GiftCertificate> createGiftCertificate(Object payload) {
        return giftCertificates.createGiftCertificate(payload);
    }

    public ApiResponse<Dto> deleteGiftCertificate(String gcId, RequestOptions options) {
        return giftCertificates.deleteGiftCertificate(gcId, options);
    }

    public ApiResponse<Dto> deleteGiftCertificate(String gcId) {
        return giftCertificates.deleteGiftCertificate(gcId);
    }

    public ApiResponse<GiftCertificate> getGiftCertificate(
            String gcId, Map<String, Object> queryParams, RequestOptions options) {
        return giftCertificates.getGiftCertificate(gcId, queryParams, options);
    }

    public ApiResponse<GiftCertificate> getGiftCertificate(
            String gcId, Map<String, Object> queryParams) {
        return giftCertificates.getGiftCertificate(gcId, queryParams);
    }

    public ApiResponse<GiftCertificate> updateGiftCertificate(
            String gcId, Object payload, RequestOptions options) {
        return giftCertificates.updateGiftCertificate(gcId, payload, options);
    }

    public ApiResponse<GiftCertificate> updateGiftCertificate(String gcId, Object payload) {
        return giftCertificates.updateGiftCertificate(gcId, payload);
    }

    public ApiResponse<GiftCertificateAllocations> getGiftCertificateAllocations(
            String gcId, Map<String, Object> queryParams, RequestOptions options) {
        return giftCertificates.getGiftCertificateAllocations(gcId, queryParams, options);
    }

    public ApiResponse<GiftCertificateAllocations> getGiftCertificateAllocations(
            String gcId, Map<String, Object> queryParams) {
        return giftCertificates.getGiftCertificateAllocations(gcId, queryParams);
    }

    public ApiResponse<GiftCertificateTransactionList> getGiftCertificateTransactions(
            String gcId, Map<String, Object> queryParams, RequestOptions options) {
        return giftCertificates.getGiftCertificateTransactions(gcId, queryParams, options);
    }

    public ApiResponse<GiftCertificateTransactionList> getGiftCertificateTransactions(
            String gcId, Map<String, Object> queryParams) {
        return giftCertificates.getGiftCertificateTransactions(gcId, queryParams);
    }
}
