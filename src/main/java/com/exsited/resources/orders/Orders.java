package com.exsited.resources.orders;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.orders.orders_dto.OrdersDto.AccountOrders;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderBillingPreferences;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderCustomObject;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderDiscardDraft;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderExtendTrial;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderInformation;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderItemFulfillment;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderKpis;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderLine;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderList;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderNoteFile;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderNoteFileList;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderNoteList;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderNoteView;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderPayment;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderPaymentMethod;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderPaymentMethods;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderPdf;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderQuotePartialInvoices;
import com.exsited.resources.orders.orders_dto.OrdersDto.OrderRead;
import java.util.Map;

/** Order endpoints, under {@code /api/{api_version}}. */
public final class Orders implements BoundResource {

    private final ExsitedClient client;

    public Orders(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List of all orders: {@code GET /orders}. */
    public ApiResponse<OrderList> getOrders(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(OrdersUrls.ORDERS,
                reopen(options).queryParams(queryParams).build(), OrderList.class);
    }

    public ApiResponse<OrderList> getOrders(
            Map<String, Object> queryParams) {
        return getOrders(queryParams, null);
    }

    public ApiResponse<OrderList> getOrders() {
        return getOrders(null, null);
    }

    /**
     * Create order: {@code POST /orders}; answered {@code 201}. A contract needs a recurring or
     * metered item on a line.
     */
    public ApiResponse<OrderRead> createOrder(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDERS,
                reopen(options).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> createOrder(
            Object payload) {
        return createOrder(payload, null);
    }

    /** Order details: {@code GET /orders/{order_id}}. */
    public ApiResponse<OrderRead> getOrder(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(OrdersUrls.ORDER,
                reopen(options).pathParam("order_id", orderId).queryParams(queryParams).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> getOrder(
            String orderId, Map<String, Object> queryParams) {
        return getOrder(orderId, queryParams, null);
    }

    public ApiResponse<OrderRead> getOrder(String orderId) {
        return getOrder(orderId, null, null);
    }

    /** Delete order: {@code DELETE /orders/{order_id}}. No typed view; read {@code getMap()}. */
    public ApiResponse<Dto> deleteOrder(
            String orderId, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendDeleteRequest(OrdersUrls.ORDER,
                reopen(options).pathParam("order_id", orderId).build(), null);
    }

    public ApiResponse<Dto> deleteOrder(
            String orderId) {
        return deleteOrder(orderId, null);
    }

    /**
     * Activate order: {@code POST /orders/{order_id}/activate}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> activateOrder(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_ACTIVATE,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> activateOrder(
            String orderId, Object payload) {
        return activateOrder(orderId, payload, null);
    }

    /**
     * Activate draft order: {@code POST /orders/{order_id}/activate_draft}.
     */
    public ApiResponse<OrderRead> createOrderActivateDraft(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_ACTIVATE_DRAFT,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> createOrderActivateDraft(
            String orderId, Object payload) {
        return createOrderActivateDraft(orderId, payload, null);
    }

    /** Billing preferences: {@code GET /orders/{order_id}/billing_preferences}. */
    public ApiResponse<OrderBillingPreferences> getOrderBillingPreferences(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(OrdersUrls.ORDER_BILLING_PREFERENCES,
                reopen(options).pathParam("order_id", orderId).queryParams(queryParams).build(), OrderBillingPreferences.class);
    }

    public ApiResponse<OrderBillingPreferences> getOrderBillingPreferences(
            String orderId, Map<String, Object> queryParams) {
        return getOrderBillingPreferences(orderId, queryParams, null);
    }

    public ApiResponse<OrderBillingPreferences> getOrderBillingPreferences(String orderId) {
        return getOrderBillingPreferences(orderId, null, null);
    }

    /**
     * Update billing preferences: {@code PATCH /orders/{order_id}/billing_preferences}.
     */
    public ApiResponse<OrderRead> updateOrderBillingPreferences(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPatchRequest(OrdersUrls.ORDER_BILLING_PREFERENCES,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> updateOrderBillingPreferences(
            String orderId, Object payload) {
        return updateOrderBillingPreferences(orderId, payload, null);
    }

    /**
     * Update billing preferences: {@code PUT /orders/{order_id}/billing_preferences}.
     */
    public ApiResponse<OrderRead> replaceOrderBillingPreferences(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPutRequest(OrdersUrls.ORDER_BILLING_PREFERENCES,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> replaceOrderBillingPreferences(
            String orderId, Object payload) {
        return replaceOrderBillingPreferences(orderId, payload, null);
    }

    /**
     * Cancel order: {@code POST /orders/{order_id}/cancel}.
     */
    public ApiResponse<OrderRead> cancelOrder(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_CANCEL,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> cancelOrder(
            String orderId, Object payload) {
        return cancelOrder(orderId, payload, null);
    }

    /**
     * Change order: {@code POST /orders/{order_id}/change}.
     */
    public ApiResponse<OrderRead> changeOrder(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_CHANGE,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> changeOrder(
            String orderId, Object payload) {
        return changeOrder(orderId, payload, null);
    }

    /**
     * Convert quote order: {@code POST /orders/{order_id}/convert_to_order}.
     */
    public ApiResponse<OrderRead> createOrderConvertToOrder(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_CONVERT_TO_ORDER,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> createOrderConvertToOrder(
            String orderId, Object payload) {
        return createOrderConvertToOrder(orderId, payload, null);
    }

    /** List custom objects: {@code GET /orders/{order_id}/custom_objects}. */
    public ApiResponse<AccountOrders> getOrderCustomObjects(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(OrdersUrls.ORDER_CUSTOM_OBJECTS,
                reopen(options).pathParam("order_id", orderId).queryParams(queryParams).build(), AccountOrders.class);
    }

    public ApiResponse<AccountOrders> getOrderCustomObjects(
            String orderId, Map<String, Object> queryParams) {
        return getOrderCustomObjects(orderId, queryParams, null);
    }

    public ApiResponse<AccountOrders> getOrderCustomObjects(String orderId) {
        return getOrderCustomObjects(orderId, null, null);
    }

    /** List custom object instances: {@code GET /orders/{order_id}/custom_objects_instances}. */
    public ApiResponse<AccountOrders> getOrderCustomObjectsInstances(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(OrdersUrls.ORDER_CUSTOM_OBJECTS_INSTANCES,
                reopen(options).pathParam("order_id", orderId).queryParams(queryParams).build(), AccountOrders.class);
    }

    public ApiResponse<AccountOrders> getOrderCustomObjectsInstances(
            String orderId, Map<String, Object> queryParams) {
        return getOrderCustomObjectsInstances(orderId, queryParams, null);
    }

    public ApiResponse<AccountOrders> getOrderCustomObjectsInstances(String orderId) {
        return getOrderCustomObjectsInstances(orderId, null, null);
    }

    /**
     * Discard draft order: {@code POST /orders/{order_id}/discard_draft}.
     */
    public ApiResponse<OrderDiscardDraft> createOrderDiscardDraft(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_DISCARD_DRAFT,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), OrderDiscardDraft.class);
    }

    public ApiResponse<OrderDiscardDraft> createOrderDiscardDraft(
            String orderId, Object payload) {
        return createOrderDiscardDraft(orderId, payload, null);
    }

    /**
     * Extend trial: {@code POST /orders/{order_id}/extend_trial}; the new window starts after the
     * current trial ends.
     */
    public ApiResponse<OrderExtendTrial> createOrderExtendTrial(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_EXTEND_TRIAL,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), OrderExtendTrial.class);
    }

    public ApiResponse<OrderExtendTrial> createOrderExtendTrial(
            String orderId, Object payload) {
        return createOrderExtendTrial(orderId, payload, null);
    }

    /** Order information: {@code GET /orders/{order_id}/information}. */
    public ApiResponse<OrderInformation> getOrderInformation(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(OrdersUrls.ORDER_INFORMATION,
                reopen(options).pathParam("order_id", orderId).queryParams(queryParams).build(), OrderInformation.class);
    }

    public ApiResponse<OrderInformation> getOrderInformation(
            String orderId, Map<String, Object> queryParams) {
        return getOrderInformation(orderId, queryParams, null);
    }

    public ApiResponse<OrderInformation> getOrderInformation(String orderId) {
        return getOrderInformation(orderId, null, null);
    }

    /**
     * Update order information: {@code PATCH /orders/{order_id}/information}.
     */
    public ApiResponse<OrderRead> updateOrderInformation(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPatchRequest(OrdersUrls.ORDER_INFORMATION,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> updateOrderInformation(
            String orderId, Object payload) {
        return updateOrderInformation(orderId, payload, null);
    }

    /** List fulfillments: {@code GET /orders/{order_id}/item_fulfillments}. */
    public ApiResponse<AccountOrders> getOrderItemFulfillments(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(OrdersUrls.ORDER_ITEM_FULFILLMENTS,
                reopen(options).pathParam("order_id", orderId).queryParams(queryParams).build(), AccountOrders.class);
    }

    public ApiResponse<AccountOrders> getOrderItemFulfillments(
            String orderId, Map<String, Object> queryParams) {
        return getOrderItemFulfillments(orderId, queryParams, null);
    }

    public ApiResponse<AccountOrders> getOrderItemFulfillments(String orderId) {
        return getOrderItemFulfillments(orderId, null, null);
    }

    /**
     * List of order lines: {@code GET /orders/{order_id}/lines}.
     */
    public ApiResponse<OrderRead> getOrderLines(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(OrdersUrls.ORDER_LINES,
                reopen(options).pathParam("order_id", orderId).queryParams(queryParams).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> getOrderLines(
            String orderId, Map<String, Object> queryParams) {
        return getOrderLines(orderId, queryParams, null);
    }

    public ApiResponse<OrderRead> getOrderLines(String orderId) {
        return getOrderLines(orderId, null, null);
    }

    /** List notes: {@code GET /orders/{order_id}/notes}. */
    public ApiResponse<OrderNoteList> getOrderNotes(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(OrdersUrls.ORDER_NOTES,
                reopen(options).pathParam("order_id", orderId).queryParams(queryParams).build(), OrderNoteList.class);
    }

    public ApiResponse<OrderNoteList> getOrderNotes(
            String orderId, Map<String, Object> queryParams) {
        return getOrderNotes(orderId, queryParams, null);
    }

    public ApiResponse<OrderNoteList> getOrderNotes(String orderId) {
        return getOrderNotes(orderId, null, null);
    }

    /**
     * Create note: {@code POST /orders/{order_id}/notes},
     * the text as the {@code note} form field; files on the options make it multipart.
     * Any {@code files} are sent beside the text as a multipart body.
     */
    public ApiResponse<OrderNoteView> createOrderNote(
            String orderId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("order_id", orderId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(OrdersUrls.ORDER_NOTES,
                request.build(), OrderNoteView.class);
    }

    public ApiResponse<OrderNoteView> createOrderNote(
            String orderId, String note, RequestOptions options) {
        return createOrderNote(orderId, note, null, options);
    }

    public ApiResponse<OrderNoteView> createOrderNote(
            String orderId, String note) {
        return createOrderNote(orderId, note, null);
    }

    /**
     * List order payment methods: {@code GET /orders/{order_id}/payment_methods}.
     */
    public ApiResponse<OrderPaymentMethods> getOrderPaymentMethods(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(OrdersUrls.ORDER_PAYMENT_METHODS,
                reopen(options).pathParam("order_id", orderId).queryParams(queryParams).build(), OrderPaymentMethods.class);
    }

    public ApiResponse<OrderPaymentMethods> getOrderPaymentMethods(
            String orderId, Map<String, Object> queryParams) {
        return getOrderPaymentMethods(orderId, queryParams, null);
    }

    public ApiResponse<OrderPaymentMethods> getOrderPaymentMethods(String orderId) {
        return getOrderPaymentMethods(orderId, null, null);
    }

    /**
     * Order PDF: {@code GET /orders/{order_id}/pdf}.
     */
    public ApiResponse<OrderPdf> getOrderPdf(
            String orderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(OrdersUrls.ORDER_PDF,
                reopen(options).pathParam("order_id", orderId).queryParams(queryParams).build(), OrderPdf.class);
    }

    public ApiResponse<OrderPdf> getOrderPdf(
            String orderId, Map<String, Object> queryParams) {
        return getOrderPdf(orderId, queryParams, null);
    }

    public ApiResponse<OrderPdf> getOrderPdf(String orderId) {
        return getOrderPdf(orderId, null, null);
    }

    /**
     * Reactivate order: {@code POST /orders/{order_id}/reactivate}.
     */
    public ApiResponse<OrderRead> reactivateOrder(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_REACTIVATE,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> reactivateOrder(
            String orderId, Object payload) {
        return reactivateOrder(orderId, payload, null);
    }

    /**
     * Pre-order relinquish: {@code POST /orders/{order_id}/relinquish}.
     */
    public ApiResponse<OrderRead> createOrderRelinquish(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_RELINQUISH,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> createOrderRelinquish(
            String orderId, Object payload) {
        return createOrderRelinquish(orderId, payload, null);
    }

    /**
     * Update line information: {@code PATCH /orders/{order_id}/lines/{line_uuid}}.
     */
    public ApiResponse<OrderRead> updateOrderLine(
            String orderId, String lineUuid, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(lineUuid, "line_uuid");
        requirePayload(payload);
        return client.sendPatchRequest(OrdersUrls.ORDER_LINE,
                reopen(options).pathParam("order_id", orderId).pathParam("line_uuid", lineUuid).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> updateOrderLine(
            String orderId, String lineUuid, Object payload) {
        return updateOrderLine(orderId, lineUuid, payload, null);
    }

    /**
     * Single line details: {@code GET /orders/{order_id}/lines/{line_uuid}}.
     */
    public ApiResponse<OrderLine> getOrderLine(
            String orderId, String lineUuid, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(lineUuid, "line_uuid");
        return client.sendGetRequest(OrdersUrls.ORDER_LINE,
                reopen(options).pathParam("order_id", orderId).pathParam("line_uuid", lineUuid).queryParams(queryParams).build(), OrderLine.class);
    }

    public ApiResponse<OrderLine> getOrderLine(
            String orderId, String lineUuid, Map<String, Object> queryParams) {
        return getOrderLine(orderId, lineUuid, queryParams, null);
    }

    public ApiResponse<OrderLine> getOrderLine(String orderId, String lineUuid) {
        return getOrderLine(orderId, lineUuid, null, null);
    }

    /**
     * Delete note: {@code DELETE /orders/{order_id}/notes/{order_note_id}}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> deleteOrderNote(
            String orderId, String orderNoteId, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(orderNoteId, "order_note_id");
        return client.sendDeleteRequest(OrdersUrls.ORDER_NOTE,
                reopen(options).pathParam("order_id", orderId).pathParam("order_note_id", orderNoteId).build(), null);
    }

    public ApiResponse<Dto> deleteOrderNote(
            String orderId, String orderNoteId) {
        return deleteOrderNote(orderId, orderNoteId, null);
    }

    /**
     * Get specific note: {@code GET /orders/{order_id}/notes/{order_note_id}}.
     */
    public ApiResponse<OrderNoteView> getOrderNote(
            String orderId, String orderNoteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(orderNoteId, "order_note_id");
        return client.sendGetRequest(OrdersUrls.ORDER_NOTE,
                reopen(options).pathParam("order_id", orderId).pathParam("order_note_id", orderNoteId).queryParams(queryParams).build(), OrderNoteView.class);
    }

    public ApiResponse<OrderNoteView> getOrderNote(
            String orderId, String orderNoteId, Map<String, Object> queryParams) {
        return getOrderNote(orderId, orderNoteId, queryParams, null);
    }

    public ApiResponse<OrderNoteView> getOrderNote(String orderId, String orderNoteId) {
        return getOrderNote(orderId, orderNoteId, null, null);
    }

    /**
     * Update note: {@code PATCH /orders/{order_id}/notes/{order_note_id}},
     * the text as the {@code note} form field; files on the options make it multipart.
     * Any {@code files} are sent beside the text as a multipart body.
     */
    public ApiResponse<OrderNoteView> updateOrderNote(
            String orderId, String orderNoteId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(orderNoteId, "order_note_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("order_id", orderId).pathParam("order_note_id", orderNoteId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPatchRequest(OrdersUrls.ORDER_NOTE,
                request.build(), OrderNoteView.class);
    }

    public ApiResponse<OrderNoteView> updateOrderNote(
            String orderId, String orderNoteId, String note, RequestOptions options) {
        return updateOrderNote(orderId, orderNoteId, note, null, options);
    }

    public ApiResponse<OrderNoteView> updateOrderNote(
            String orderId, String orderNoteId, String note) {
        return updateOrderNote(orderId, orderNoteId, note, null);
    }

    /**
     * Get single: {@code GET /orders/{order_id}/payment_methods/{payment_method_ref}}.
     */
    public ApiResponse<OrderPaymentMethod> getOrderPaymentMethod(
            String orderId, String paymentMethodRef, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(paymentMethodRef, "payment_method_ref");
        return client.sendGetRequest(OrdersUrls.ORDER_PAYMENT_METHOD,
                reopen(options).pathParam("order_id", orderId).pathParam("payment_method_ref", paymentMethodRef).queryParams(queryParams).build(), OrderPaymentMethod.class);
    }

    public ApiResponse<OrderPaymentMethod> getOrderPaymentMethod(
            String orderId, String paymentMethodRef, Map<String, Object> queryParams) {
        return getOrderPaymentMethod(orderId, paymentMethodRef, queryParams, null);
    }

    public ApiResponse<OrderPaymentMethod> getOrderPaymentMethod(String orderId, String paymentMethodRef) {
        return getOrderPaymentMethod(orderId, paymentMethodRef, null, null);
    }

    /**
     * Update line billing preferences:
     * {@code PATCH /orders/{order_id}/lines/{line_uuid}/billing_preferences}.
     */
    public ApiResponse<OrderRead> updateOrderLineBillingPreferences(
            String orderId, String lineUuid, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(lineUuid, "line_uuid");
        requirePayload(payload);
        return client.sendPatchRequest(OrdersUrls.ORDER_LINE_BILLING_PREFERENCES,
                reopen(options).pathParam("order_id", orderId).pathParam("line_uuid", lineUuid).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> updateOrderLineBillingPreferences(
            String orderId, String lineUuid, Object payload) {
        return updateOrderLineBillingPreferences(orderId, lineUuid, payload, null);
    }

    /**
     * Update line billing preferences:
     * {@code PUT /orders/{order_id}/lines/{line_uuid}/billing_preferences}.
     */
    public ApiResponse<OrderRead> replaceOrderLineBillingPreferences(
            String orderId, String lineUuid, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(lineUuid, "line_uuid");
        requirePayload(payload);
        return client.sendPutRequest(OrdersUrls.ORDER_LINE_BILLING_PREFERENCES,
                reopen(options).pathParam("order_id", orderId).pathParam("line_uuid", lineUuid).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> replaceOrderLineBillingPreferences(
            String orderId, String lineUuid, Object payload) {
        return replaceOrderLineBillingPreferences(orderId, lineUuid, payload, null);
    }

    /**
     * List note files: {@code GET /orders/{order_id}/notes/{order_note_id}/files}.
     */
    public ApiResponse<OrderNoteFileList> getOrderNoteFiles(
            String orderId, String orderNoteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(orderNoteId, "order_note_id");
        return client.sendGetRequest(OrdersUrls.ORDER_NOTE_FILES,
                reopen(options).pathParam("order_id", orderId).pathParam("order_note_id", orderNoteId).queryParams(queryParams).build(), OrderNoteFileList.class);
    }

    public ApiResponse<OrderNoteFileList> getOrderNoteFiles(
            String orderId, String orderNoteId, Map<String, Object> queryParams) {
        return getOrderNoteFiles(orderId, orderNoteId, queryParams, null);
    }

    public ApiResponse<OrderNoteFileList> getOrderNoteFiles(String orderId, String orderNoteId) {
        return getOrderNoteFiles(orderId, orderNoteId, null, null);
    }

    /**
     * Get note file:
     * {@code GET /orders/{order_id}/notes/{order_note_id}/files/{order_file_id}}.
     */
    public ApiResponse<OrderNoteFile> getOrderNoteFile(
            String orderId, String orderNoteId, String orderFileId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(orderNoteId, "order_note_id");
        requireValue(orderFileId, "order_file_id");
        return client.sendGetRequest(OrdersUrls.ORDER_NOTE_FILE,
                reopen(options).pathParam("order_id", orderId).pathParam("order_note_id", orderNoteId).pathParam("order_file_id", orderFileId).queryParams(queryParams).build(), OrderNoteFile.class);
    }

    public ApiResponse<OrderNoteFile> getOrderNoteFile(
            String orderId, String orderNoteId, String orderFileId, Map<String, Object> queryParams) {
        return getOrderNoteFile(orderId, orderNoteId, orderFileId, queryParams, null);
    }

    public ApiResponse<OrderNoteFile> getOrderNoteFile(String orderId, String orderNoteId, String orderFileId) {
        return getOrderNoteFile(orderId, orderNoteId, orderFileId, null, null);
    }

    /** List the notes of every order: {@code GET /orders/notes}; not scoped to one order. */
    public ApiResponse<OrderNoteList> getOrdersNotes(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(OrdersUrls.ORDERS_NOTES,
                reopen(options).queryParams(queryParams).build(), OrderNoteList.class);
    }

    public ApiResponse<OrderNoteList> getOrdersNotes(Map<String, Object> queryParams) {
        return getOrdersNotes(queryParams, null);
    }

    public ApiResponse<OrderNoteList> getOrdersNotes() {
        return getOrdersNotes(null, null);
    }

    /**
     * Price an order without creating it: {@code POST /orders/preview}, the {@code createOrder}
     * body; {@code 201}, with no cart data in the answer. An {@code account} object still creates
     * that account. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> previewOrder(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDERS_PREVIEW,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> previewOrder(Object payload) {
        return previewOrder(payload, null);
    }

    /**
     * Create an order, and its invoice and payment if the body nests them, in one call:
     * {@code POST /orders/express}, the {@code createExpress} body under {@code account}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createOrderExpress(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDERS_EXPRESS,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createOrderExpress(Object payload) {
        return createOrderExpress(payload, null);
    }

    /**
     * Accept an order's pending acceptance: {@code POST /orders/{order_id}/accept}; no body sent.
     * It raises an invoice and may require payment. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> acceptOrder(
            String orderId, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendPostRequest(OrdersUrls.ORDER_ACCEPT,
                reopen(options).pathParam("order_id", orderId).build(), null);
    }

    public ApiResponse<Dto> acceptOrder(String orderId) {
        return acceptOrder(orderId, null);
    }

    /**
     * Activate a dormant invoice:
     * {@code POST /orders/{order_id}/activate_invoice_from_dormant/ {invoice_uuid}}, body under
     * {@code activate}: {@code due_date}, {@code amount}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> activateOrderInvoiceFromDormant(
            String orderId, String invoiceUuid, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(invoiceUuid, "invoice_uuid");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_ACTIVATE_INVOICE_FROM_DORMANT,
                reopen(options).pathParam("order_id", orderId).pathParam("invoice_uuid", invoiceUuid).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> activateOrderInvoiceFromDormant(
            String orderId, String invoiceUuid, Object payload) {
        return activateOrderInvoiceFromDormant(orderId, invoiceUuid, payload, null);
    }

    /**
     * Preview a change: {@code POST /orders/{order_id}/change/preview}, the {@code changeOrder}
     * body; nothing is changed, and the answer is the order as it stands.
     */
    public ApiResponse<OrderRead> previewOrderChange(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_CHANGE_PREVIEW,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), OrderRead.class);
    }

    public ApiResponse<OrderRead> previewOrderChange(String orderId, Object payload) {
        return previewOrderChange(orderId, payload, null);
    }

    /**
     * Trigger a workflow state: {@code POST /orders/{order_id}/change_status}, body
     * {@code order.workflow_status}; it runs the workflow's connections. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> changeOrderStatus(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_CHANGE_STATUS,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> changeOrderStatus(String orderId, Object payload) {
        return changeOrderStatus(orderId, payload, null);
    }

    /**
     * Upgrade or downgrade by package rank: {@code POST /orders/{order_id}/contract_adjustment};
     * the body is not wrapped: {@code effective_date} and {@code lines}. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> adjustOrderContract(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_CONTRACT_ADJUSTMENT,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> adjustOrderContract(String orderId, Object payload) {
        return adjustOrderContract(orderId, payload, null);
    }

    /**
     * Preview a contract adjustment: {@code POST /orders/{order_id}/contract_adjustment/preview},
     * the {@code adjustOrderContract} body, not wrapped. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> previewOrderContractAdjustment(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_CONTRACT_ADJUSTMENT_PREVIEW,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> previewOrderContractAdjustment(String orderId, Object payload) {
        return previewOrderContractAdjustment(orderId, payload, null);
    }

    /**
     * Downgrade a contract: {@code POST /orders/{order_id}/downgrade}; the body is not wrapped:
     * {@code effective_date} and {@code lines} naming each {@code charge_item_uuid} and
     * {@code package_name}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> downgradeOrder(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_DOWNGRADE,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> downgradeOrder(String orderId, Object payload) {
        return downgradeOrder(orderId, payload, null);
    }

    /**
     * Preview a downgrade: {@code POST /orders/{order_id}/downgrade/preview}, the
     * {@code downgradeOrder} body, not wrapped. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> previewOrderDowngrade(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_DOWNGRADE_PREVIEW,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> previewOrderDowngrade(String orderId, Object payload) {
        return previewOrderDowngrade(orderId, payload, null);
    }

    /**
     * Upgrade a contract: {@code POST /orders/{order_id}/upgrade}; the body is not wrapped:
     * {@code effective_date} and {@code lines} naming each {@code charge_item_uuid} and
     * {@code package_name}. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> upgradeOrder(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_UPGRADE,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> upgradeOrder(String orderId, Object payload) {
        return upgradeOrder(orderId, payload, null);
    }

    /**
     * Preview an upgrade: {@code POST /orders/{order_id}/upgrade/preview}, the {@code upgradeOrder}
     * body, not wrapped. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> previewOrderUpgrade(
            String orderId, Object payload, RequestOptions options) {
        requireValue(orderId, "order_id");
        requirePayload(payload);
        return client.sendPostRequest(OrdersUrls.ORDER_UPGRADE_PREVIEW,
                reopen(options).pathParam("order_id", orderId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> previewOrderUpgrade(String orderId, Object payload) {
        return previewOrderUpgrade(orderId, payload, null);
    }

    /**
     * End a contract's trial now: {@code POST /orders/{order_id}/terminate_trial}; no body sent. It
     * starts the contract. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> terminateOrderTrial(
            String orderId, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendPostRequest(OrdersUrls.ORDER_TERMINATE_TRIAL,
                reopen(options).pathParam("order_id", orderId).build(), null);
    }

    public ApiResponse<Dto> terminateOrderTrial(String orderId) {
        return terminateOrderTrial(orderId, null);
    }

    /**
     * Delete a note attachment:
     * {@code DELETE /orders/{order_id}/notes/{order_note_id}/files/{order_file_id}}; {@code 204}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteOrderNoteFile(
            String orderId, String orderNoteId, String orderFileId, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(orderNoteId, "order_note_id");
        requireValue(orderFileId, "order_file_id");
        return client.sendDeleteRequest(OrdersUrls.ORDER_NOTE_FILE,
                reopen(options).pathParam("order_id", orderId).pathParam("order_note_id", orderNoteId).pathParam("order_file_id", orderFileId).build(), null);
    }

    public ApiResponse<Dto> deleteOrderNoteFile(String orderId, String orderNoteId, String orderFileId) {
        return deleteOrderNoteFile(orderId, orderNoteId, orderFileId, null);
    }

    /**
     * One custom object with the order's instances of it:
     * {@code GET /orders/{order_id}/custom_objects/{custom_object_id}}.
     */
    public ApiResponse<OrderCustomObject> getOrderCustomObject(
            String orderId, String customObjectId, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(customObjectId, "custom_object_id");
        return client.sendGetRequest(OrdersUrls.ORDER_CUSTOM_OBJECT,
                reopen(options).pathParam("order_id", orderId).pathParam("custom_object_id", customObjectId).build(), OrderCustomObject.class);
    }

    public ApiResponse<OrderCustomObject> getOrderCustomObject(String orderId, String customObjectId) {
        return getOrderCustomObject(orderId, customObjectId, null);
    }

    /**
     * One custom object instance:
     * {@code GET /orders/{order_id}/custom_objects/{custom_object_id}/instances/{instance_id}}. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getOrderCustomObjectInstance(
            String orderId, String customObjectId, String instanceId, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(customObjectId, "custom_object_id");
        requireValue(instanceId, "instance_id");
        return client.sendGetRequest(OrdersUrls.ORDER_CUSTOM_OBJECT_INSTANCE,
                reopen(options).pathParam("order_id", orderId).pathParam("custom_object_id", customObjectId).pathParam("instance_id", instanceId).build(), null);
    }

    public ApiResponse<Dto> getOrderCustomObjectInstance(
            String orderId, String customObjectId, String instanceId) {
        return getOrderCustomObjectInstance(orderId, customObjectId, instanceId, null);
    }

    /**
     * The server's alias of {@code getOrderCustomObject}:
     * {@code GET /orders/{order_id}/custom_objects_instances/{custom_object_id}}.
     */
    public ApiResponse<OrderCustomObject> getOrderCustomObjectsInstance(
            String orderId, String customObjectId, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(customObjectId, "custom_object_id");
        return client.sendGetRequest(OrdersUrls.ORDER_CUSTOM_OBJECTS_INSTANCE,
                reopen(options).pathParam("order_id", orderId).pathParam("custom_object_id", customObjectId).build(), OrderCustomObject.class);
    }

    public ApiResponse<OrderCustomObject> getOrderCustomObjectsInstance(
            String orderId, String customObjectId) {
        return getOrderCustomObjectsInstance(orderId, customObjectId, null);
    }

    /**
     * The server's alias of {@code getOrderCustomObjectInstance}:
     * {@code GET /orders/{order_id}/ custom_objects_instances/{custom_object_id}/instances/{instance_id}}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getOrderCustomObjectsInstanceInstance(
            String orderId, String customObjectId, String instanceId, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(customObjectId, "custom_object_id");
        requireValue(instanceId, "instance_id");
        return client.sendGetRequest(OrdersUrls.ORDER_CUSTOM_OBJECTS_INSTANCE_INSTANCE,
                reopen(options).pathParam("order_id", orderId).pathParam("custom_object_id", customObjectId).pathParam("instance_id", instanceId).build(), null);
    }

    public ApiResponse<Dto> getOrderCustomObjectsInstanceInstance(
            String orderId, String customObjectId, String instanceId) {
        return getOrderCustomObjectsInstanceInstance(orderId, customObjectId, instanceId, null);
    }

    /**
     * One fulfillment: {@code GET /orders/{order_id}/item_fulfillments/{fulfillment_id}}; the
     * server does not check that the fulfillment belongs to the order.
     */
    public ApiResponse<OrderItemFulfillment> getOrderItemFulfillment(
            String orderId, String fulfillmentId, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(fulfillmentId, "fulfillment_id");
        return client.sendGetRequest(OrdersUrls.ORDER_ITEM_FULFILLMENT,
                reopen(options).pathParam("order_id", orderId).pathParam("fulfillment_id", fulfillmentId).build(), OrderItemFulfillment.class);
    }

    public ApiResponse<OrderItemFulfillment> getOrderItemFulfillment(String orderId, String fulfillmentId) {
        return getOrderItemFulfillment(orderId, fulfillmentId, null);
    }

    /** An order's KPIs: {@code GET /orders/{order_id}/kpis}, under {@code order_kpis}. */
    public ApiResponse<OrderKpis> getOrderKpis(
            String orderId, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(OrdersUrls.ORDER_KPIS,
                reopen(options).pathParam("order_id", orderId).build(), OrderKpis.class);
    }

    public ApiResponse<OrderKpis> getOrderKpis(String orderId) {
        return getOrderKpis(orderId, null);
    }

    /**
     * One payment: {@code GET /orders/{order_id}/payments/{payment_id}}; the server does not check
     * that the payment belongs to the order.
     */
    public ApiResponse<OrderPayment> getOrderPayment(
            String orderId, String paymentId, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(paymentId, "payment_id");
        return client.sendGetRequest(OrdersUrls.ORDER_PAYMENT,
                reopen(options).pathParam("order_id", orderId).pathParam("payment_id", paymentId).build(), OrderPayment.class);
    }

    public ApiResponse<OrderPayment> getOrderPayment(String orderId, String paymentId) {
        return getOrderPayment(orderId, paymentId, null);
    }

    /**
     * The deposit and instalment invoices of a converted quote:
     * {@code GET /orders/{order_id}/quote_partial_invoices}; an unconverted quote answers
     * {@code 400}.
     */
    public ApiResponse<OrderQuotePartialInvoices> getOrderQuotePartialInvoices(
            String orderId, RequestOptions options) {
        requireValue(orderId, "order_id");
        return client.sendGetRequest(OrdersUrls.ORDER_QUOTE_PARTIAL_INVOICES,
                reopen(options).pathParam("order_id", orderId).build(), OrderQuotePartialInvoices.class);
    }

    public ApiResponse<OrderQuotePartialInvoices> getOrderQuotePartialInvoices(String orderId) {
        return getOrderQuotePartialInvoices(orderId, null);
    }

    /**
     * A shipping line's PDF: {@code GET /orders/{order_id}/shipping_pdf/{shipping_line_id}};
     * {@code 404} until the line has a generated shipping PDF. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> getOrderShippingPdf(
            String orderId, String shippingLineId, RequestOptions options) {
        requireValue(orderId, "order_id");
        requireValue(shippingLineId, "shipping_line_id");
        return client.sendGetRequest(OrdersUrls.ORDER_SHIPPING_PDF,
                reopen(options).pathParam("order_id", orderId).pathParam("shipping_line_id", shippingLineId).build(), null);
    }

    public ApiResponse<Dto> getOrderShippingPdf(String orderId, String shippingLineId) {
        return getOrderShippingPdf(orderId, shippingLineId, null);
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

    private static void requireNote(String note) {
        if (note == null) {
            throw new SdkUsageError(
                    "note is required: the note text is the body of this request, and "
                            + "omitting it would store the string \"null\" as the note");
        }
    }
}
