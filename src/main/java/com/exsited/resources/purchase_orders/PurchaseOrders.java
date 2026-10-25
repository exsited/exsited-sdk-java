package com.exsited.resources.purchase_orders;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderInformation;
import com.exsited.resources.purchase_orders.purchase_orders_dto.PurchaseOrdersDto.PurchaseOrderKpis;
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

/** Purchase order endpoints, under {@code /api/{api_version}}. */
public final class PurchaseOrders implements BoundResource {

    private final ExsitedClient client;

    public PurchaseOrders(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List purchase orders: {@code GET /purchase_orders}. */
    public ApiResponse<PurchaseOrderList> getPurchaseOrders(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(PurchaseOrdersUrls.PURCHASE_ORDERS,
                reopen(options).queryParams(queryParams).build(), PurchaseOrderList.class);
    }

    public ApiResponse<PurchaseOrderList> getPurchaseOrders(
            Map<String, Object> queryParams) {
        return getPurchaseOrders(queryParams, null);
    }

    public ApiResponse<PurchaseOrderList> getPurchaseOrders() {
        return getPurchaseOrders(null, null);
    }

    /** With status DRAFT: {@code POST /purchase_orders}. Recorded 201. */
    public ApiResponse<PurchaseOrdersDto.PurchaseOrders> createPurchaseOrder(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(PurchaseOrdersUrls.PURCHASE_ORDERS,
                reopen(options).jsonBody(payload).build(), PurchaseOrdersDto.PurchaseOrders.class);
    }

    public ApiResponse<PurchaseOrdersDto.PurchaseOrders> createPurchaseOrder(
            Object payload) {
        return createPurchaseOrder(payload, null);
    }

    /** List all notes across POs: {@code GET /purchase_orders/notes}. */
    public ApiResponse<PurchaseOrdersNoteList> getPurchaseOrdersNotes(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(PurchaseOrdersUrls.PURCHASE_ORDERS_NOTES,
                reopen(options).queryParams(queryParams).build(), PurchaseOrdersNoteList.class);
    }

    public ApiResponse<PurchaseOrdersNoteList> getPurchaseOrdersNotes(
            Map<String, Object> queryParams) {
        return getPurchaseOrdersNotes(queryParams, null);
    }

    public ApiResponse<PurchaseOrdersNoteList> getPurchaseOrdersNotes() {
        return getPurchaseOrdersNotes(null, null);
    }

    /**
     * Delete purchase order: {@code DELETE /purchase_orders/{purchase_order_id}}. Recorded 204. No
     * typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deletePurchaseOrder(
            String purchaseOrderId, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        return client.sendDeleteRequest(PurchaseOrdersUrls.PURCHASE_ORDER,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).build(), null);
    }

    public ApiResponse<Dto> deletePurchaseOrder(
            String purchaseOrderId) {
        return deletePurchaseOrder(purchaseOrderId, null);
    }

    /** Update purchase order: {@code PATCH /purchase_orders/{purchase_order_id}}. Recorded 202. */
    public ApiResponse<PurchaseOrderUpdated> updatePurchaseOrder(
            String purchaseOrderId, Object payload, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        requirePayload(payload);
        return client.sendPatchRequest(PurchaseOrdersUrls.PURCHASE_ORDER,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).jsonBody(payload).build(), PurchaseOrderUpdated.class);
    }

    public ApiResponse<PurchaseOrderUpdated> updatePurchaseOrder(
            String purchaseOrderId, Object payload) {
        return updatePurchaseOrder(purchaseOrderId, payload, null);
    }

    /** Get single: {@code GET /purchase_orders/{purchase_order_id}}. */
    public ApiResponse<PurchaseOrderRead> getPurchaseOrder(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        return client.sendGetRequest(PurchaseOrdersUrls.PURCHASE_ORDER,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).queryParams(queryParams).build(), PurchaseOrderRead.class);
    }

    public ApiResponse<PurchaseOrderRead> getPurchaseOrder(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return getPurchaseOrder(purchaseOrderId, queryParams, null);
    }

    public ApiResponse<PurchaseOrderRead> getPurchaseOrder(String purchaseOrderId) {
        return getPurchaseOrder(purchaseOrderId, null, null);
    }

    /**
     * Cancel a purchase order: {@code POST /purchase_orders/{purchase_order_id}/cancel}.
     * Recorded 204.
     */
    public ApiResponse<PurchaseOrderRead> cancelPurchaseOrder(
            String purchaseOrderId, Object payload, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        requirePayload(payload);
        return client.sendPostRequest(PurchaseOrdersUrls.PURCHASE_ORDER_CANCEL,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).jsonBody(payload).build(), PurchaseOrderRead.class);
    }

    public ApiResponse<PurchaseOrderRead> cancelPurchaseOrder(
            String purchaseOrderId, Object payload) {
        return cancelPurchaseOrder(purchaseOrderId, payload, null);
    }

    /**
     * Change purchase order: {@code POST /purchase_orders/{purchase_order_id}/change}.
     */
    public ApiResponse<PurchaseOrderRead> changePurchaseOrder(
            String purchaseOrderId, Object payload, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        requirePayload(payload);
        return client.sendPostRequest(PurchaseOrdersUrls.PURCHASE_ORDER_CHANGE,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).jsonBody(payload).build(), PurchaseOrderRead.class);
    }

    public ApiResponse<PurchaseOrderRead> changePurchaseOrder(
            String purchaseOrderId, Object payload) {
        return changePurchaseOrder(purchaseOrderId, payload, null);
    }

    /**
     * The information block of one purchase order:
     * {@code GET /purchase_orders/{purchase_order_id}/information}.
     */
    public ApiResponse<PurchaseOrderInformation> getPurchaseOrderInformation(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        return client.sendGetRequest(PurchaseOrdersUrls.PURCHASE_ORDER_INFORMATION,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).queryParams(queryParams).build(), PurchaseOrderInformation.class);
    }

    public ApiResponse<PurchaseOrderInformation> getPurchaseOrderInformation(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return getPurchaseOrderInformation(purchaseOrderId, queryParams, null);
    }

    public ApiResponse<PurchaseOrderInformation> getPurchaseOrderInformation(String purchaseOrderId) {
        return getPurchaseOrderInformation(purchaseOrderId, null, null);
    }

    /** The lines of one purchase order: {@code GET /purchase_orders/{purchase_order_id}/lines}. */
    public ApiResponse<PurchaseOrderLineList> getPurchaseOrderLines(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        return client.sendGetRequest(PurchaseOrdersUrls.PURCHASE_ORDER_LINES,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).queryParams(queryParams).build(), PurchaseOrderLineList.class);
    }

    public ApiResponse<PurchaseOrderLineList> getPurchaseOrderLines(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return getPurchaseOrderLines(purchaseOrderId, queryParams, null);
    }

    public ApiResponse<PurchaseOrderLineList> getPurchaseOrderLines(String purchaseOrderId) {
        return getPurchaseOrderLines(purchaseOrderId, null, null);
    }

    /** List notes: {@code GET /purchase_orders/{purchase_order_id}/notes}. */
    public ApiResponse<PurchaseOrderNotes> getPurchaseOrderNotes(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        return client.sendGetRequest(PurchaseOrdersUrls.PURCHASE_ORDER_NOTES,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).queryParams(queryParams).build(), PurchaseOrderNotes.class);
    }

    public ApiResponse<PurchaseOrderNotes> getPurchaseOrderNotes(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return getPurchaseOrderNotes(purchaseOrderId, queryParams, null);
    }

    public ApiResponse<PurchaseOrderNotes> getPurchaseOrderNotes(String purchaseOrderId) {
        return getPurchaseOrderNotes(purchaseOrderId, null, null);
    }

    /**
     * Create note: {@code POST /purchase_orders/{purchase_order_id}/notes},
     * the text as the {@code note} form field; files on the options make it multipart.
     * Any {@code files} are sent beside the text as a multipart body.
     */
    public ApiResponse<PurchaseOrderNotesView> createPurchaseOrderNote(
            String purchaseOrderId, String note, Map<String, Object> files, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        requireNote(note);
        RequestOptions.Builder request = reopen(options).pathParam("purchase_order_id", purchaseOrderId).formData(Map.of("note", note));
        if (files != null) {
            request.files(files);
        }
        return client.sendPostRequest(PurchaseOrdersUrls.PURCHASE_ORDER_NOTES,
                request.build(), PurchaseOrderNotesView.class);
    }

    public ApiResponse<PurchaseOrderNotesView> createPurchaseOrderNote(
            String purchaseOrderId, String note, RequestOptions options) {
        return createPurchaseOrderNote(purchaseOrderId, note, null, options);
    }

    public ApiResponse<PurchaseOrderNotesView> createPurchaseOrderNote(
            String purchaseOrderId, String note) {
        return createPurchaseOrderNote(purchaseOrderId, note, null);
    }

    /**
     * Create PI from PO:
     * {@code POST /purchase_orders/{purchase_order_id}/purchase_invoices}. Recorded 400.
     */
    public ApiResponse<PurchaseOrderPurchaseInvoices> createPurchaseOrderPurchaseInvoice(
            String purchaseOrderId, Object payload, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        requirePayload(payload);
        return client.sendPostRequest(PurchaseOrdersUrls.PURCHASE_ORDER_PURCHASE_INVOICES,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).jsonBody(payload).build(), PurchaseOrderPurchaseInvoices.class);
    }

    public ApiResponse<PurchaseOrderPurchaseInvoices> createPurchaseOrderPurchaseInvoice(
            String purchaseOrderId, Object payload) {
        return createPurchaseOrderPurchaseInvoice(purchaseOrderId, payload, null);
    }

    /** List linked invoices: {@code GET /purchase_orders/{purchase_order_id}/purchase_invoices}. */
    public ApiResponse<PurchaseOrderPurchaseInvoiceList> getPurchaseOrderPurchaseInvoices(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        return client.sendGetRequest(PurchaseOrdersUrls.PURCHASE_ORDER_PURCHASE_INVOICES,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).queryParams(queryParams).build(), PurchaseOrderPurchaseInvoiceList.class);
    }

    public ApiResponse<PurchaseOrderPurchaseInvoiceList> getPurchaseOrderPurchaseInvoices(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return getPurchaseOrderPurchaseInvoices(purchaseOrderId, queryParams, null);
    }

    public ApiResponse<PurchaseOrderPurchaseInvoiceList> getPurchaseOrderPurchaseInvoices(String purchaseOrderId) {
        return getPurchaseOrderPurchaseInvoices(purchaseOrderId, null, null);
    }

    /** List linked payments: {@code GET /purchase_orders/{purchase_order_id}/purchase_payments}. */
    public ApiResponse<PurchaseOrderPurchasePaymentList> getPurchaseOrderPurchasePayments(
            String purchaseOrderId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        return client.sendGetRequest(PurchaseOrdersUrls.PURCHASE_ORDER_PURCHASE_PAYMENTS,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).queryParams(queryParams).build(), PurchaseOrderPurchasePaymentList.class);
    }

    public ApiResponse<PurchaseOrderPurchasePaymentList> getPurchaseOrderPurchasePayments(
            String purchaseOrderId, Map<String, Object> queryParams) {
        return getPurchaseOrderPurchasePayments(purchaseOrderId, queryParams, null);
    }

    public ApiResponse<PurchaseOrderPurchasePaymentList> getPurchaseOrderPurchasePayments(String purchaseOrderId) {
        return getPurchaseOrderPurchasePayments(purchaseOrderId, null, null);
    }

    /**
     * Reactivate a purchase order:
     * {@code POST /purchase_orders/{purchase_order_id}/reactivate}. Recorded 204.
     */
    public ApiResponse<PurchaseOrderRead> reactivatePurchaseOrder(
            String purchaseOrderId, Object payload, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        requirePayload(payload);
        return client.sendPostRequest(PurchaseOrdersUrls.PURCHASE_ORDER_REACTIVATE,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).jsonBody(payload).build(), PurchaseOrderRead.class);
    }

    public ApiResponse<PurchaseOrderRead> reactivatePurchaseOrder(
            String purchaseOrderId, Object payload) {
        return reactivatePurchaseOrder(purchaseOrderId, payload, null);
    }

    /**
     * One purchase order line:
     * {@code GET /purchase_orders/{purchase_order_id}/lines/{purchase_order_line_uuid}}.
     */
    public ApiResponse<PurchaseOrderLineRead> getPurchaseOrderLine(
            String purchaseOrderId, String purchaseOrderLineUuid, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        requireValue(purchaseOrderLineUuid, "purchase_order_line_uuid");
        return client.sendGetRequest(PurchaseOrdersUrls.PURCHASE_ORDER_LINE,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).pathParam("purchase_order_line_uuid", purchaseOrderLineUuid).queryParams(queryParams).build(), PurchaseOrderLineRead.class);
    }

    public ApiResponse<PurchaseOrderLineRead> getPurchaseOrderLine(
            String purchaseOrderId, String purchaseOrderLineUuid, Map<String, Object> queryParams) {
        return getPurchaseOrderLine(purchaseOrderId, purchaseOrderLineUuid, queryParams, null);
    }

    public ApiResponse<PurchaseOrderLineRead> getPurchaseOrderLine(String purchaseOrderId, String purchaseOrderLineUuid) {
        return getPurchaseOrderLine(purchaseOrderId, purchaseOrderLineUuid, null, null);
    }

    /**
     * Delete note:
     * {@code DELETE /purchase_orders/{purchase_order_id}/notes/{purchase_order_note_id}}. Recorded
     * 400. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deletePurchaseOrderNote(
            String purchaseOrderId, String purchaseOrderNoteId, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        requireValue(purchaseOrderNoteId, "purchase_order_note_id");
        return client.sendDeleteRequest(PurchaseOrdersUrls.PURCHASE_ORDER_NOTE,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).pathParam("purchase_order_note_id", purchaseOrderNoteId).build(), null);
    }

    public ApiResponse<Dto> deletePurchaseOrderNote(
            String purchaseOrderId, String purchaseOrderNoteId) {
        return deletePurchaseOrderNote(purchaseOrderId, purchaseOrderNoteId, null);
    }

    /**
     * Get specific note:
     * {@code GET /purchase_orders/{purchase_order_id}/notes/{purchase_order_note_id}}.
     */
    public ApiResponse<PurchaseOrderNote> getPurchaseOrderNote(
            String purchaseOrderId, String purchaseOrderNoteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        requireValue(purchaseOrderNoteId, "purchase_order_note_id");
        return client.sendGetRequest(PurchaseOrdersUrls.PURCHASE_ORDER_NOTE,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).pathParam("purchase_order_note_id", purchaseOrderNoteId).queryParams(queryParams).build(), PurchaseOrderNote.class);
    }

    public ApiResponse<PurchaseOrderNote> getPurchaseOrderNote(
            String purchaseOrderId, String purchaseOrderNoteId, Map<String, Object> queryParams) {
        return getPurchaseOrderNote(purchaseOrderId, purchaseOrderNoteId, queryParams, null);
    }

    public ApiResponse<PurchaseOrderNote> getPurchaseOrderNote(String purchaseOrderId, String purchaseOrderNoteId) {
        return getPurchaseOrderNote(purchaseOrderId, purchaseOrderNoteId, null, null);
    }

    /**
     * List note files:
     * {@code GET /purchase_orders/{purchase_order_id}/notes/{purchase_order_note_id}/files}.
     */
    public ApiResponse<PurchaseOrderNote> getPurchaseOrderNoteFiles(
            String purchaseOrderId, String purchaseOrderNoteId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        requireValue(purchaseOrderNoteId, "purchase_order_note_id");
        return client.sendGetRequest(PurchaseOrdersUrls.PURCHASE_ORDER_NOTE_FILES,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).pathParam("purchase_order_note_id", purchaseOrderNoteId).queryParams(queryParams).build(), PurchaseOrderNote.class);
    }

    public ApiResponse<PurchaseOrderNote> getPurchaseOrderNoteFiles(
            String purchaseOrderId, String purchaseOrderNoteId, Map<String, Object> queryParams) {
        return getPurchaseOrderNoteFiles(purchaseOrderId, purchaseOrderNoteId, queryParams, null);
    }

    public ApiResponse<PurchaseOrderNote> getPurchaseOrderNoteFiles(String purchaseOrderId, String purchaseOrderNoteId) {
        return getPurchaseOrderNoteFiles(purchaseOrderId, purchaseOrderNoteId, null, null);
    }

    /**
     * Get note file:
     * {@code GET /purchase_orders/{purchase_order_id}/notes/{purchase_order_note_id}/files/{purchase_order_file_id}}.
     * Recorded 404.
     */
    public ApiResponse<PurchaseOrderNoteFile> getPurchaseOrderNoteFile(
            String purchaseOrderId, String purchaseOrderNoteId, String purchaseOrderFileId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        requireValue(purchaseOrderNoteId, "purchase_order_note_id");
        requireValue(purchaseOrderFileId, "purchase_order_file_id");
        return client.sendGetRequest(PurchaseOrdersUrls.PURCHASE_ORDER_NOTE_FILE,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).pathParam("purchase_order_note_id", purchaseOrderNoteId).pathParam("purchase_order_file_id", purchaseOrderFileId).queryParams(queryParams).build(), PurchaseOrderNoteFile.class);
    }

    public ApiResponse<PurchaseOrderNoteFile> getPurchaseOrderNoteFile(
            String purchaseOrderId, String purchaseOrderNoteId, String purchaseOrderFileId, Map<String, Object> queryParams) {
        return getPurchaseOrderNoteFile(purchaseOrderId, purchaseOrderNoteId, purchaseOrderFileId, queryParams, null);
    }

    public ApiResponse<PurchaseOrderNoteFile> getPurchaseOrderNoteFile(String purchaseOrderId, String purchaseOrderNoteId, String purchaseOrderFileId) {
        return getPurchaseOrderNoteFile(purchaseOrderId, purchaseOrderNoteId, purchaseOrderFileId, null, null);
    }

    /**
     * A purchase order's KPI block: {@code GET /purchase_orders/{purchase_order_id}/kpis}, under
     * {@code purchase_order_kpis}; the same block {@code getPurchaseOrder} carries as {@code kpis}.
     */
    public ApiResponse<PurchaseOrderKpis> getPurchaseOrderKpis(
            String purchaseOrderId, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        return client.sendGetRequest(PurchaseOrdersUrls.PURCHASE_ORDER_KPIS,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).build(), PurchaseOrderKpis.class);
    }

    public ApiResponse<PurchaseOrderKpis> getPurchaseOrderKpis(String purchaseOrderId) {
        return getPurchaseOrderKpis(purchaseOrderId, null);
    }

    /**
     * Delete one attachment:
     * {@code DELETE /purchase_orders/{purchase_order_id}/notes/{purchase_order_note_id}/files/{purchase_order_file_id}},
     * by the purchase order's code and the attachment's {@code uuid}; {@code 204}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> deletePurchaseOrderNoteFile(
            String purchaseOrderId, String purchaseOrderNoteId, String purchaseOrderFileId, RequestOptions options) {
        requireValue(purchaseOrderId, "purchase_order_id");
        requireValue(purchaseOrderNoteId, "purchase_order_note_id");
        requireValue(purchaseOrderFileId, "purchase_order_file_id");
        return client.sendDeleteRequest(PurchaseOrdersUrls.PURCHASE_ORDER_NOTE_FILE,
                reopen(options).pathParam("purchase_order_id", purchaseOrderId).pathParam("purchase_order_note_id", purchaseOrderNoteId).pathParam("purchase_order_file_id", purchaseOrderFileId).build(), null);
    }

    public ApiResponse<Dto> deletePurchaseOrderNoteFile(
            String purchaseOrderId, String purchaseOrderNoteId, String purchaseOrderFileId) {
        return deletePurchaseOrderNoteFile(purchaseOrderId, purchaseOrderNoteId, purchaseOrderFileId, null);
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
