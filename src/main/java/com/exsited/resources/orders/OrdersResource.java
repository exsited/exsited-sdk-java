package com.exsited.resources.orders;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.orders.orders_dto.OrdersDto.AccountOrders;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderBillingPreferences;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderDiscardDraft;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderInformation;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderLine;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderList;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderNoteFile;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderNoteFileList;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderNoteList;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderNoteView;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderPaymentMethod;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderPaymentMethods;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderPdf;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderRead;
import java.util.Map;

/**
 * Forwards every call to {@link Orders}.
 *
 * @deprecated {@link Orders} is bound to a client itself and is what
 *     {@code client.orders()} answers; use it instead.
 */
@Deprecated
public final class OrdersResource implements BoundResource {

    private final Orders orders;

    public OrdersResource(ExsitedClient client) {
        this.orders = new Orders(client);
    }

    @Override
    public ExsitedClient getClient() {
        return orders.getClient();
    }

    public ApiResponse<OrderList> getOrders(
            Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrders(queryParams, options);
    }

    public ApiResponse<OrderList> getOrders(Map<String, Object> queryParams) {
        return orders.getOrders(queryParams);
    }

    public ApiResponse<OrderRead> getOrder(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrder(orderId, queryParams, options);
    }

    public ApiResponse<OrderRead> getOrder(String orderId, Map<String, Object> queryParams) {
        return orders.getOrder(orderId, queryParams);
    }

    public ApiResponse<Dto> deleteOrder(String orderId, RequestOptions options) {
        return orders.deleteOrder(orderId, options);
    }

    public ApiResponse<Dto> deleteOrder(String orderId) {
        return orders.deleteOrder(orderId);
    }

    public ApiResponse<Dto> activateOrder(String orderId, Object payload, RequestOptions options) {
        return orders.activateOrder(orderId, payload, options);
    }

    public ApiResponse<Dto> activateOrder(String orderId, Object payload) {
        return orders.activateOrder(orderId, payload);
    }

    public ApiResponse<OrderRead> createOrderActivateDraft(
            String orderId, Object payload, RequestOptions options) {
        return orders.createOrderActivateDraft(orderId, payload, options);
    }

    public ApiResponse<OrderRead> createOrderActivateDraft(String orderId, Object payload) {
        return orders.createOrderActivateDraft(orderId, payload);
    }

    public ApiResponse<OrderBillingPreferences> getOrderBillingPreferences(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderBillingPreferences(orderId, queryParams, options);
    }

    public ApiResponse<OrderBillingPreferences> getOrderBillingPreferences(
            String orderId, Map<String, Object> queryParams) {
        return orders.getOrderBillingPreferences(orderId, queryParams);
    }

    public ApiResponse<OrderRead> updateOrderBillingPreferences(
            String orderId, Object payload, RequestOptions options) {
        return orders.updateOrderBillingPreferences(orderId, payload, options);
    }

    public ApiResponse<OrderRead> updateOrderBillingPreferences(String orderId, Object payload) {
        return orders.updateOrderBillingPreferences(orderId, payload);
    }

    public ApiResponse<OrderRead> replaceOrderBillingPreferences(
            String orderId, Object payload, RequestOptions options) {
        return orders.replaceOrderBillingPreferences(orderId, payload, options);
    }

    public ApiResponse<OrderRead> replaceOrderBillingPreferences(String orderId, Object payload) {
        return orders.replaceOrderBillingPreferences(orderId, payload);
    }

    public ApiResponse<OrderRead> cancelOrder(String orderId, Object payload, RequestOptions options) {
        return orders.cancelOrder(orderId, payload, options);
    }

    public ApiResponse<OrderRead> cancelOrder(String orderId, Object payload) {
        return orders.cancelOrder(orderId, payload);
    }

    public ApiResponse<OrderRead> changeOrder(String orderId, Object payload, RequestOptions options) {
        return orders.changeOrder(orderId, payload, options);
    }

    public ApiResponse<OrderRead> changeOrder(String orderId, Object payload) {
        return orders.changeOrder(orderId, payload);
    }

    public ApiResponse<OrderRead> createOrderConvertToOrder(
            String orderId, Object payload, RequestOptions options) {
        return orders.createOrderConvertToOrder(orderId, payload, options);
    }

    public ApiResponse<OrderRead> createOrderConvertToOrder(String orderId, Object payload) {
        return orders.createOrderConvertToOrder(orderId, payload);
    }

    public ApiResponse<AccountOrders> getOrderCustomObjects(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderCustomObjects(orderId, queryParams, options);
    }

    public ApiResponse<AccountOrders> getOrderCustomObjects(
            String orderId, Map<String, Object> queryParams) {
        return orders.getOrderCustomObjects(orderId, queryParams);
    }

    public ApiResponse<AccountOrders> getOrderCustomObjectsInstances(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderCustomObjectsInstances(orderId, queryParams, options);
    }

    public ApiResponse<AccountOrders> getOrderCustomObjectsInstances(
            String orderId, Map<String, Object> queryParams) {
        return orders.getOrderCustomObjectsInstances(orderId, queryParams);
    }

    public ApiResponse<OrderDiscardDraft> createOrderDiscardDraft(
            String orderId, Object payload, RequestOptions options) {
        return orders.createOrderDiscardDraft(orderId, payload, options);
    }

    public ApiResponse<OrderDiscardDraft> createOrderDiscardDraft(String orderId, Object payload) {
        return orders.createOrderDiscardDraft(orderId, payload);
    }

    public ApiResponse<OrderInformation> getOrderInformation(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderInformation(orderId, queryParams, options);
    }

    public ApiResponse<OrderInformation> getOrderInformation(
            String orderId, Map<String, Object> queryParams) {
        return orders.getOrderInformation(orderId, queryParams);
    }

    public ApiResponse<OrderRead> updateOrderInformation(
            String orderId, Object payload, RequestOptions options) {
        return orders.updateOrderInformation(orderId, payload, options);
    }

    public ApiResponse<OrderRead> updateOrderInformation(String orderId, Object payload) {
        return orders.updateOrderInformation(orderId, payload);
    }

    public ApiResponse<AccountOrders> getOrderItemFulfillments(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderItemFulfillments(orderId, queryParams, options);
    }

    public ApiResponse<AccountOrders> getOrderItemFulfillments(
            String orderId, Map<String, Object> queryParams) {
        return orders.getOrderItemFulfillments(orderId, queryParams);
    }

    public ApiResponse<OrderRead> getOrderLines(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderLines(orderId, queryParams, options);
    }

    public ApiResponse<OrderRead> getOrderLines(String orderId, Map<String, Object> queryParams) {
        return orders.getOrderLines(orderId, queryParams);
    }

    public ApiResponse<OrderNoteList> getOrderNotes(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderNotes(orderId, queryParams, options);
    }

    public ApiResponse<OrderNoteList> getOrderNotes(
            String orderId, Map<String, Object> queryParams) {
        return orders.getOrderNotes(orderId, queryParams);
    }

    public ApiResponse<OrderNoteView> createOrderNote(
            String orderId, String note, RequestOptions options) {
        return orders.createOrderNote(orderId, note, options);
    }

    public ApiResponse<OrderNoteView> createOrderNote(String orderId, String note) {
        return orders.createOrderNote(orderId, note);
    }

    public ApiResponse<OrderPaymentMethods> getOrderPaymentMethods(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderPaymentMethods(orderId, queryParams, options);
    }

    public ApiResponse<OrderPaymentMethods> getOrderPaymentMethods(
            String orderId, Map<String, Object> queryParams) {
        return orders.getOrderPaymentMethods(orderId, queryParams);
    }

    public ApiResponse<OrderPdf> getOrderPdf(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderPdf(orderId, queryParams, options);
    }

    public ApiResponse<OrderPdf> getOrderPdf(String orderId, Map<String, Object> queryParams) {
        return orders.getOrderPdf(orderId, queryParams);
    }

    public ApiResponse<OrderRead> reactivateOrder(
            String orderId, Object payload, RequestOptions options) {
        return orders.reactivateOrder(orderId, payload, options);
    }

    public ApiResponse<OrderRead> reactivateOrder(String orderId, Object payload) {
        return orders.reactivateOrder(orderId, payload);
    }

    public ApiResponse<OrderRead> createOrderRelinquish(
            String orderId, Object payload, RequestOptions options) {
        return orders.createOrderRelinquish(orderId, payload, options);
    }

    public ApiResponse<OrderRead> createOrderRelinquish(String orderId, Object payload) {
        return orders.createOrderRelinquish(orderId, payload);
    }

    public ApiResponse<OrderRead> updateOrderLine(
            String orderId, String lineUuid, Object payload, RequestOptions options) {
        return orders.updateOrderLine(orderId, lineUuid, payload, options);
    }

    public ApiResponse<OrderRead> updateOrderLine(String orderId, String lineUuid, Object payload) {
        return orders.updateOrderLine(orderId, lineUuid, payload);
    }

    public ApiResponse<OrderLine> getOrderLine(
            String orderId, String lineUuid, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderLine(orderId, lineUuid, queryParams, options);
    }

    public ApiResponse<OrderLine> getOrderLine(
            String orderId, String lineUuid, Map<String, Object> queryParams) {
        return orders.getOrderLine(orderId, lineUuid, queryParams);
    }

    public ApiResponse<Dto> deleteOrderNote(
            String orderId, String orderNoteId, RequestOptions options) {
        return orders.deleteOrderNote(orderId, orderNoteId, options);
    }

    public ApiResponse<Dto> deleteOrderNote(String orderId, String orderNoteId) {
        return orders.deleteOrderNote(orderId, orderNoteId);
    }

    public ApiResponse<OrderNoteView> getOrderNote(
            String orderId, String orderNoteId, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderNote(orderId, orderNoteId, queryParams, options);
    }

    public ApiResponse<OrderNoteView> getOrderNote(
            String orderId, String orderNoteId, Map<String, Object> queryParams) {
        return orders.getOrderNote(orderId, orderNoteId, queryParams);
    }

    public ApiResponse<OrderNoteView> updateOrderNote(
            String orderId, String orderNoteId, String note, RequestOptions options) {
        return orders.updateOrderNote(orderId, orderNoteId, note, options);
    }

    public ApiResponse<OrderNoteView> updateOrderNote(String orderId, String orderNoteId, String note) {
        return orders.updateOrderNote(orderId, orderNoteId, note);
    }

    public ApiResponse<OrderPaymentMethod> getOrderPaymentMethod(
            String orderId, String paymentMethodRef, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderPaymentMethod(orderId, paymentMethodRef, queryParams, options);
    }

    public ApiResponse<OrderPaymentMethod> getOrderPaymentMethod(
            String orderId, String paymentMethodRef, Map<String, Object> queryParams) {
        return orders.getOrderPaymentMethod(orderId, paymentMethodRef, queryParams);
    }

    public ApiResponse<OrderRead> updateOrderLineBillingPreferences(
            String orderId, String lineUuid, Object payload, RequestOptions options) {
        return orders.updateOrderLineBillingPreferences(orderId, lineUuid, payload, options);
    }

    public ApiResponse<OrderRead> updateOrderLineBillingPreferences(
            String orderId, String lineUuid, Object payload) {
        return orders.updateOrderLineBillingPreferences(orderId, lineUuid, payload);
    }

    public ApiResponse<OrderRead> replaceOrderLineBillingPreferences(
            String orderId, String lineUuid, Object payload, RequestOptions options) {
        return orders.replaceOrderLineBillingPreferences(orderId, lineUuid, payload, options);
    }

    public ApiResponse<OrderRead> replaceOrderLineBillingPreferences(
            String orderId, String lineUuid, Object payload) {
        return orders.replaceOrderLineBillingPreferences(orderId, lineUuid, payload);
    }

    public ApiResponse<OrderNoteFileList> getOrderNoteFiles(
            String orderId, String orderNoteId, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderNoteFiles(orderId, orderNoteId, queryParams, options);
    }

    public ApiResponse<OrderNoteFileList> getOrderNoteFiles(
            String orderId, String orderNoteId, Map<String, Object> queryParams) {
        return orders.getOrderNoteFiles(orderId, orderNoteId, queryParams);
    }

    public ApiResponse<OrderNoteFile> getOrderNoteFile(
            String orderId, String orderNoteId, String orderFileId, Map<String, Object> queryParams, RequestOptions options) {
        return orders.getOrderNoteFile(orderId, orderNoteId, orderFileId, queryParams, options);
    }

    public ApiResponse<OrderNoteFile> getOrderNoteFile(
            String orderId, String orderNoteId, String orderFileId, Map<String, Object> queryParams) {
        return orders.getOrderNoteFile(orderId, orderNoteId, orderFileId, queryParams);
    }
}
