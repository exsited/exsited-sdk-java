package com.exsited.resources.return_merchandise_authorisations;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.return_merchandise_authorisations.return_merchandise_authorisations_dto.ReturnMerchandiseAuthorisationsDto.InvoiceReturnMerchandiseAuthorisationReceive;
import com.exsited.resources.return_merchandise_authorisations.return_merchandise_authorisations_dto.ReturnMerchandiseAuthorisationsDto.InvoiceReturnMerchandiseAuthorisationReceiveList;
import com.exsited.resources.return_merchandise_authorisations.return_merchandise_authorisations_dto.ReturnMerchandiseAuthorisationsDto.ReturnMerchandiseAuthorisationList;
import com.exsited.resources.return_merchandise_authorisations.return_merchandise_authorisations_dto.ReturnMerchandiseAuthorisationsDto.ReturnMerchandiseAuthorisationRead;
import com.exsited.resources.return_merchandise_authorisations.return_merchandise_authorisations_dto.ReturnMerchandiseAuthorisationsDto;
import java.util.Map;

/**
 * Forwards every call to {@link ReturnMerchandiseAuthorisations}.
 *
 * @deprecated {@link ReturnMerchandiseAuthorisations} is bound to a client itself and is what
 *     {@code client.returnMerchandiseAuthorisations()} answers; use it instead.
 */
@Deprecated
public final class ReturnMerchandiseAuthorisationsResource implements BoundResource {

    private final ReturnMerchandiseAuthorisations returnMerchandiseAuthorisations;

    public ReturnMerchandiseAuthorisationsResource(ExsitedClient client) {
        this.returnMerchandiseAuthorisations = new ReturnMerchandiseAuthorisations(client);
    }

    @Override
    public ExsitedClient getClient() {
        return returnMerchandiseAuthorisations.getClient();
    }

    public ApiResponse<ReturnMerchandiseAuthorisationList> getReturnMerchandiseAuthorisations(
            Map<String, Object> queryParams, RequestOptions options) {
        return returnMerchandiseAuthorisations.getReturnMerchandiseAuthorisations(queryParams, options);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationList> getReturnMerchandiseAuthorisations(
            Map<String, Object> queryParams) {
        return returnMerchandiseAuthorisations.getReturnMerchandiseAuthorisations(queryParams);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationsDto.ReturnMerchandiseAuthorisations> createReturnMerchandiseAuthorisation(
            Object payload, RequestOptions options) {
        return returnMerchandiseAuthorisations.createReturnMerchandiseAuthorisation(payload, options);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationsDto.ReturnMerchandiseAuthorisations> createReturnMerchandiseAuthorisation(
            Object payload) {
        return returnMerchandiseAuthorisations.createReturnMerchandiseAuthorisation(payload);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationRead> getReturnMerchandiseAuthorisation(
            String rmaId, Map<String, Object> queryParams, RequestOptions options) {
        return returnMerchandiseAuthorisations.getReturnMerchandiseAuthorisation(rmaId, queryParams, options);
    }

    public ApiResponse<ReturnMerchandiseAuthorisationRead> getReturnMerchandiseAuthorisation(
            String rmaId, Map<String, Object> queryParams) {
        return returnMerchandiseAuthorisations.getReturnMerchandiseAuthorisation(rmaId, queryParams);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceiveList> getInvoiceReturnMerchandiseAuthorisationReceive(
            String invoiceId, String rmaId, Map<String, Object> queryParams, RequestOptions options) {
        return returnMerchandiseAuthorisations.getInvoiceReturnMerchandiseAuthorisationReceive(invoiceId, rmaId, queryParams, options);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceiveList> getInvoiceReturnMerchandiseAuthorisationReceive(
            String invoiceId, String rmaId, Map<String, Object> queryParams) {
        return returnMerchandiseAuthorisations.getInvoiceReturnMerchandiseAuthorisationReceive(invoiceId, rmaId, queryParams);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceive> getInvoiceReturnMerchandiseAuthorisationReceiveByUuid(
            String invoiceId, String rmaId, String receiveUuid, Map<String, Object> queryParams, RequestOptions options) {
        return returnMerchandiseAuthorisations.getInvoiceReturnMerchandiseAuthorisationReceiveByUuid(invoiceId, rmaId, receiveUuid, queryParams, options);
    }

    public ApiResponse<InvoiceReturnMerchandiseAuthorisationReceive> getInvoiceReturnMerchandiseAuthorisationReceiveByUuid(
            String invoiceId, String rmaId, String receiveUuid, Map<String, Object> queryParams) {
        return returnMerchandiseAuthorisations.getInvoiceReturnMerchandiseAuthorisationReceiveByUuid(invoiceId, rmaId, receiveUuid, queryParams);
    }
}
