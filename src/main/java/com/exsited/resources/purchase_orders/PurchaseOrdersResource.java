package com.exsited.resources.purchase_orders;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderInformation;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderLineList;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderLineRead;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderList;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderNote;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderNoteFile;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderNotes;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderNotesView;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderPurchaseInvoiceList;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderPurchaseInvoices;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderPurchasePaymentList;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderRead;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderUpdated;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrdersNoteList;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto;
import java.util.Map;

/**
 * Forwards every call to {@link PurchaseOrders}.
 *
 * @deprecated {@link PurchaseOrders} is bound to a client itself and is what
 *     {@code client.purchaseOrders()} answers; use it instead.
 */
@Deprecated
public final class PurchaseOrdersResource implements BoundResource {

    private final PurchaseOrders purchaseOrders;

    public PurchaseOrdersResource(ExsitedClient client) {
        this.purchaseOrders = new PurchaseOrders(client);
    }

    @Override
    public ExsitedClient getClient() {
        return purchaseOrders.getClient();
    }

    public ApiResponse<PurchaseOrderList> getPurchaseOrders(
            Map<String, Object> queryParams, RequestOptions options) {
        return purchaseOrders.getPurchaseOrders(queryParams, options);
    }

    public ApiResponse<PurchaseOrderList> getPurchaseOrders(Map<String, Object> queryParams) {
        return purchaseOrders.getPurchaseOrders(queryParams);
    }

    public ApiResponse<PurchaseOrdersDto.PurchaseOrders> createPurchaseOrder(
            Object payload, RequestOptions options) {
        return purchaseOrders.createPurchaseOrder(payload, options);
    }

    public ApiResponse<PurchaseOrdersDto.PurchaseOrders> createPurchaseOrder(Object payload) {
        return purchaseOrders.createPurchaseOrder(payload);
    }

    public ApiResponse<PurchaseOrdersNoteList> getPurchaseOrdersNotes(
            Map<String, Object> queryParams, RequestOptions options) {
        return purchaseOrders.getPurchaseOrdersNotes(queryParams, options);
    }

    public ApiResponse<PurchaseOrdersNoteList> getPurchaseOrdersNotes(
            Map<String, Object> queryParams) {
        return purchaseOrders.getPurchaseOrdersNotes(queryParams);
    }

    public ApiResponse<Dto> deletePurchaseOrder(String purchaseOrderId, RequestOptions options) {
        return purchaseOrders.deletePurchaseOrder(purchaseOrderId, options);
    }

    public ApiResponse<Dto> deletePurchaseOrder(String purchaseOrderId) {
        return purchaseOrders.deletePurchaseOrder(purchaseOrderId);
    }

    public ApiResponse<PurchaseOrderUpdated> updatePurchaseOrder(
            String purchaseOrderId, Object payload, RequestOptions options) {
        return purchaseOrders.updatePurchaseOrder(purchaseOrderId, payload, options);
    }

    public ApiResponse<PurchaseOrderUpdated> updatePurchaseOrder(
            String purchaseOrderId, Object payload) {
        return purchaseOrders.updatePurchaseOrder(purchaseOrderId, payload);
    }

    public ApiResponse<PurchaseOrderRead> getPurchaseOrder(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseOrders.getPurchaseOrder(purchaseOrderId, queryParams, options);
    }

    public ApiResponse<PurchaseOrderRead> getPurchaseOrder(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return purchaseOrders.getPurchaseOrder(purchaseOrderId, queryParams);
    }

    public ApiResponse<PurchaseOrderRead> cancelPurchaseOrder(
            String purchaseOrderId, Object payload, RequestOptions options) {
        return purchaseOrders.cancelPurchaseOrder(purchaseOrderId, payload, options);
    }

    public ApiResponse<PurchaseOrderRead> cancelPurchaseOrder(String purchaseOrderId, Object payload) {
        return purchaseOrders.cancelPurchaseOrder(purchaseOrderId, payload);
    }

    public ApiResponse<PurchaseOrderRead> changePurchaseOrder(
            String purchaseOrderId, Object payload, RequestOptions options) {
        return purchaseOrders.changePurchaseOrder(purchaseOrderId, payload, options);
    }

    public ApiResponse<PurchaseOrderRead> changePurchaseOrder(String purchaseOrderId, Object payload) {
        return purchaseOrders.changePurchaseOrder(purchaseOrderId, payload);
    }

    public ApiResponse<PurchaseOrderInformation> getPurchaseOrderInformation(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseOrders.getPurchaseOrderInformation(purchaseOrderId, queryParams, options);
    }

    public ApiResponse<PurchaseOrderInformation> getPurchaseOrderInformation(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return purchaseOrders.getPurchaseOrderInformation(purchaseOrderId, queryParams);
    }

    public ApiResponse<PurchaseOrderLineList> getPurchaseOrderLines(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseOrders.getPurchaseOrderLines(purchaseOrderId, queryParams, options);
    }

    public ApiResponse<PurchaseOrderLineList> getPurchaseOrderLines(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return purchaseOrders.getPurchaseOrderLines(purchaseOrderId, queryParams);
    }

    public ApiResponse<PurchaseOrderNotes> getPurchaseOrderNotes(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseOrders.getPurchaseOrderNotes(purchaseOrderId, queryParams, options);
    }

    public ApiResponse<PurchaseOrderNotes> getPurchaseOrderNotes(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return purchaseOrders.getPurchaseOrderNotes(purchaseOrderId, queryParams);
    }

    public ApiResponse<PurchaseOrderNotesView> createPurchaseOrderNote(
            String purchaseOrderId, String note, RequestOptions options) {
        return purchaseOrders.createPurchaseOrderNote(purchaseOrderId, note, options);
    }

    public ApiResponse<PurchaseOrderNotesView> createPurchaseOrderNote(String purchaseOrderId, String note) {
        return purchaseOrders.createPurchaseOrderNote(purchaseOrderId, note);
    }

    public ApiResponse<PurchaseOrderPurchaseInvoices> createPurchaseOrderPurchaseInvoice(
            String purchaseOrderId, Object payload, RequestOptions options) {
        return purchaseOrders.createPurchaseOrderPurchaseInvoice(purchaseOrderId, payload, options);
    }

    public ApiResponse<PurchaseOrderPurchaseInvoices> createPurchaseOrderPurchaseInvoice(
            String purchaseOrderId, Object payload) {
        return purchaseOrders.createPurchaseOrderPurchaseInvoice(purchaseOrderId, payload);
    }

    public ApiResponse<PurchaseOrderPurchaseInvoiceList> getPurchaseOrderPurchaseInvoices(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseOrders.getPurchaseOrderPurchaseInvoices(purchaseOrderId, queryParams, options);
    }

    public ApiResponse<PurchaseOrderPurchaseInvoiceList> getPurchaseOrderPurchaseInvoices(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return purchaseOrders.getPurchaseOrderPurchaseInvoices(purchaseOrderId, queryParams);
    }

    public ApiResponse<PurchaseOrderPurchasePaymentList> getPurchaseOrderPurchasePayments(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseOrders.getPurchaseOrderPurchasePayments(purchaseOrderId, queryParams, options);
    }

    public ApiResponse<PurchaseOrderPurchasePaymentList> getPurchaseOrderPurchasePayments(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return purchaseOrders.getPurchaseOrderPurchasePayments(purchaseOrderId, queryParams);
    }

    public ApiResponse<PurchaseOrderRead> reactivatePurchaseOrder(
            String purchaseOrderId, Object payload, RequestOptions options) {
        return purchaseOrders.reactivatePurchaseOrder(purchaseOrderId, payload, options);
    }

    public ApiResponse<PurchaseOrderRead> reactivatePurchaseOrder(String purchaseOrderId, Object payload) {
        return purchaseOrders.reactivatePurchaseOrder(purchaseOrderId, payload);
    }

    public ApiResponse<PurchaseOrderLineRead> getPurchaseOrderLine(
            String purchaseOrderId, String purchaseOrderLineUuid, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseOrders.getPurchaseOrderLine(purchaseOrderId, purchaseOrderLineUuid, queryParams, options);
    }

    public ApiResponse<PurchaseOrderLineRead> getPurchaseOrderLine(
            String purchaseOrderId, String purchaseOrderLineUuid, Map<String, Object> queryParams) {
        return purchaseOrders.getPurchaseOrderLine(purchaseOrderId, purchaseOrderLineUuid, queryParams);
    }

    public ApiResponse<Dto> deletePurchaseOrderNote(
            String purchaseOrderId, String purchaseOrderNoteId, RequestOptions options) {
        return purchaseOrders.deletePurchaseOrderNote(purchaseOrderId, purchaseOrderNoteId, options);
    }

    public ApiResponse<Dto> deletePurchaseOrderNote(
            String purchaseOrderId, String purchaseOrderNoteId) {
        return purchaseOrders.deletePurchaseOrderNote(purchaseOrderId, purchaseOrderNoteId);
    }

    public ApiResponse<PurchaseOrderNote> getPurchaseOrderNote(
            String purchaseOrderId, String purchaseOrderNoteId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseOrders.getPurchaseOrderNote(purchaseOrderId, purchaseOrderNoteId, queryParams, options);
    }

    public ApiResponse<PurchaseOrderNote> getPurchaseOrderNote(
            String purchaseOrderId, String purchaseOrderNoteId, Map<String, Object> queryParams) {
        return purchaseOrders.getPurchaseOrderNote(purchaseOrderId, purchaseOrderNoteId, queryParams);
    }

    public ApiResponse<PurchaseOrderNote> getPurchaseOrderNoteFiles(
            String purchaseOrderId, String purchaseOrderNoteId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseOrders.getPurchaseOrderNoteFiles(purchaseOrderId, purchaseOrderNoteId, queryParams, options);
    }

    public ApiResponse<PurchaseOrderNote> getPurchaseOrderNoteFiles(
            String purchaseOrderId, String purchaseOrderNoteId, Map<String, Object> queryParams) {
        return purchaseOrders.getPurchaseOrderNoteFiles(purchaseOrderId, purchaseOrderNoteId, queryParams);
    }

    public ApiResponse<PurchaseOrderNoteFile> getPurchaseOrderNoteFile(
            String purchaseOrderId, String purchaseOrderNoteId, String purchaseOrderFileId, Map<String, Object> queryParams, RequestOptions options) {
        return purchaseOrders.getPurchaseOrderNoteFile(purchaseOrderId, purchaseOrderNoteId, purchaseOrderFileId, queryParams, options);
    }

    public ApiResponse<PurchaseOrderNoteFile> getPurchaseOrderNoteFile(
            String purchaseOrderId, String purchaseOrderNoteId, String purchaseOrderFileId, Map<String, Object> queryParams) {
        return purchaseOrders.getPurchaseOrderNoteFile(purchaseOrderId, purchaseOrderNoteId, purchaseOrderFileId, queryParams);
    }
}
