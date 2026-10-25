package com.exsited.resources.bookings;

import com.exsited.Dto;
import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.errors.SdkUsageError;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingBillingPreferencesView;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingById;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingCustomObject;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingCustomObjectList;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingInvoiceList;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingItemFulfillment;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingKpis;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingLine;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingList;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingNoteList;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingPaymentList;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingPaymentMethod;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingPaymentMethods;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingPaymentView;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingPdf;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingQuotePartialInvoices;
import java.util.Map;

/** Booking endpoints, under {@code /api/{api_version}/booking}. */
public final class Bookings implements BoundResource {

    private final ExsitedClient client;

    public Bookings(ExsitedClient client) {
        this.client = BoundResource.requireClient(client);
    }

    @Override
    public ExsitedClient getClient() {
        return client;
    }

    /** List all bookings: {@code GET /booking}. */
    public ApiResponse<BookingList> getBooking(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(BookingsUrls.BOOKING,
                reopen(options).queryParams(queryParams).build(), BookingList.class);
    }

    public ApiResponse<BookingList> getBooking(
            Map<String, Object> queryParams) {
        return getBooking(queryParams, null);
    }

    public ApiResponse<BookingList> getBooking() {
        return getBooking(null, null);
    }

    /** Get single booking: {@code GET /booking/{booking_id}}. */
    public ApiResponse<BookingById> getBookingById(
            String bookingId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_BY_ID,
                reopen(options).pathParam("booking_id", bookingId).queryParams(queryParams).build(), BookingById.class);
    }

    public ApiResponse<BookingById> getBookingById(
            String bookingId, Map<String, Object> queryParams) {
        return getBookingById(bookingId, queryParams, null);
    }

    public ApiResponse<BookingById> getBookingById(String bookingId) {
        return getBookingById(bookingId, null, null);
    }

    /**
     * Create a booking: {@code POST /booking}, the {@code createOrder} body under {@code order};
     * {@code 201}, answered under {@code booking}. Each line needs a labour and its booking times.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> createBooking(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(BookingsUrls.BOOKING,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> createBooking(Object payload) {
        return createBooking(payload, null);
    }

    /**
     * Price a booking without creating it: {@code POST /booking/preview}, the {@code createBooking}
     * body; {@code 201}, with no cart data in the answer, under {@code booking}. No typed view;
     * read {@code getMap()}.
     */
    public ApiResponse<Dto> previewBooking(
            Object payload, RequestOptions options) {
        requirePayload(payload);
        return client.sendPostRequest(BookingsUrls.BOOKING_PREVIEW,
                reopen(options).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> previewBooking(Object payload) {
        return previewBooking(payload, null);
    }

    /**
     * Delete a booking: {@code DELETE /booking/{booking_id}}; {@code 204}. The server deletes any
     * order by this path, whatever its type. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> deleteBooking(
            String bookingId, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendDeleteRequest(BookingsUrls.BOOKING_BY_ID,
                reopen(options).pathParam("booking_id", bookingId).build(), null);
    }

    public ApiResponse<Dto> deleteBooking(String bookingId) {
        return deleteBooking(bookingId, null);
    }

    /**
     * List the notes of every order: {@code GET /booking/notes}, the same list as
     * {@code orders.get_orders_notes}; {@code /booking/{booking_id}/notes} is refused.
     */
    public ApiResponse<BookingNoteList> getBookingNotes(
            Map<String, Object> queryParams, RequestOptions options) {
        return client.sendGetRequest(BookingsUrls.BOOKING_NOTES,
                reopen(options).queryParams(queryParams).build(), BookingNoteList.class);
    }

    public ApiResponse<BookingNoteList> getBookingNotes(Map<String, Object> queryParams) {
        return getBookingNotes(queryParams, null);
    }

    public ApiResponse<BookingNoteList> getBookingNotes() {
        return getBookingNotes(null, null);
    }

    /** A booking's billing preferences: {@code GET /booking/{booking_id}/billing_preferences}. */
    public ApiResponse<BookingBillingPreferencesView> getBookingBillingPreferences(
            String bookingId, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_BILLING_PREFERENCES,
                reopen(options).pathParam("booking_id", bookingId).build(), BookingBillingPreferencesView.class);
    }

    public ApiResponse<BookingBillingPreferencesView> getBookingBillingPreferences(String bookingId) {
        return getBookingBillingPreferences(bookingId, null);
    }

    /**
     * Update a booking's billing preferences:
     * {@code PATCH /booking/{booking_id}/billing_preferences}, body
     * {@code order.billing_preferences}; answered with the whole booking under {@code order}.
     */
    public ApiResponse<BookingById> updateBookingBillingPreferences(
            String bookingId, Object payload, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requirePayload(payload);
        return client.sendPatchRequest(BookingsUrls.BOOKING_BILLING_PREFERENCES,
                reopen(options).pathParam("booking_id", bookingId).jsonBody(payload).build(), BookingById.class);
    }

    public ApiResponse<BookingById> updateBookingBillingPreferences(String bookingId, Object payload) {
        return updateBookingBillingPreferences(bookingId, payload, null);
    }

    /**
     * Change a booking: {@code POST /booking/{booking_id}/change}, body under {@code booking}:
     * {@code effective_date} and {@code lines}. Only a booking-type order is accepted. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> changeBooking(
            String bookingId, Object payload, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requirePayload(payload);
        return client.sendPostRequest(BookingsUrls.BOOKING_CHANGE,
                reopen(options).pathParam("booking_id", bookingId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> changeBooking(String bookingId, Object payload) {
        return changeBooking(bookingId, payload, null);
    }

    /**
     * Preview a booking change: {@code POST /booking/{booking_id}/change/preview}, the
     * {@code changeBooking} body; nothing is changed. No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> previewBookingChange(
            String bookingId, Object payload, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requirePayload(payload);
        return client.sendPostRequest(BookingsUrls.BOOKING_CHANGE_PREVIEW,
                reopen(options).pathParam("booking_id", bookingId).jsonBody(payload).build(), null);
    }

    public ApiResponse<Dto> previewBookingChange(String bookingId, Object payload) {
        return previewBookingChange(bookingId, payload, null);
    }

    /** A booking's header: {@code GET /booking/{booking_id}/information}. */
    public ApiResponse<BookingById> getBookingInformation(
            String bookingId, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_INFORMATION,
                reopen(options).pathParam("booking_id", bookingId).build(), BookingById.class);
    }

    public ApiResponse<BookingById> getBookingInformation(String bookingId) {
        return getBookingInformation(bookingId, null);
    }

    /**
     * Update a booking's header: {@code PATCH /booking/{booking_id}/information}, body under
     * {@code order}; answered with the whole booking under {@code order}.
     */
    public ApiResponse<BookingById> updateBookingInformation(
            String bookingId, Object payload, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requirePayload(payload);
        return client.sendPatchRequest(BookingsUrls.BOOKING_INFORMATION,
                reopen(options).pathParam("booking_id", bookingId).jsonBody(payload).build(), BookingById.class);
    }

    public ApiResponse<BookingById> updateBookingInformation(String bookingId, Object payload) {
        return updateBookingInformation(bookingId, payload, null);
    }

    /** A booking with its lines: {@code GET /booking/{booking_id}/lines}, under {@code order}. */
    public ApiResponse<BookingById> getBookingLines(
            String bookingId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_LINES,
                reopen(options).pathParam("booking_id", bookingId).queryParams(queryParams).build(), BookingById.class);
    }

    public ApiResponse<BookingById> getBookingLines(String bookingId, Map<String, Object> queryParams) {
        return getBookingLines(bookingId, queryParams, null);
    }

    public ApiResponse<BookingById> getBookingLines(String bookingId) {
        return getBookingLines(bookingId, null, null);
    }

    /**
     * One booking line: {@code GET /booking/{booking_id}/lines/{line_uuid}}, under
     * {@code order_line}.
     */
    public ApiResponse<BookingLine> getBookingLine(
            String bookingId, String lineUuid, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requireValue(lineUuid, "line_uuid");
        return client.sendGetRequest(BookingsUrls.BOOKING_LINE,
                reopen(options).pathParam("booking_id", bookingId).pathParam("line_uuid", lineUuid).build(), BookingLine.class);
    }

    public ApiResponse<BookingLine> getBookingLine(String bookingId, String lineUuid) {
        return getBookingLine(bookingId, lineUuid, null);
    }

    /**
     * Update a booking line: {@code PATCH /booking/{booking_id}/lines/{line_uuid}}, body
     * {@code order.line}; answered with the whole booking under {@code order}.
     */
    public ApiResponse<BookingById> updateBookingLine(
            String bookingId, String lineUuid, Object payload, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requireValue(lineUuid, "line_uuid");
        requirePayload(payload);
        return client.sendPatchRequest(BookingsUrls.BOOKING_LINE,
                reopen(options).pathParam("booking_id", bookingId).pathParam("line_uuid", lineUuid).jsonBody(payload).build(), BookingById.class);
    }

    public ApiResponse<BookingById> updateBookingLine(String bookingId, String lineUuid, Object payload) {
        return updateBookingLine(bookingId, lineUuid, payload, null);
    }

    /**
     * Update a line's billing preferences:
     * {@code PATCH /booking/{booking_id}/lines/{line_uuid}/billing_preferences}, body
     * {@code order.line.item_properties}, not {@code billing_preferences}.
     */
    public ApiResponse<BookingById> updateBookingLineBillingPreferences(
            String bookingId, String lineUuid, Object payload, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requireValue(lineUuid, "line_uuid");
        requirePayload(payload);
        return client.sendPatchRequest(BookingsUrls.BOOKING_LINE_BILLING_PREFERENCES,
                reopen(options).pathParam("booking_id", bookingId).pathParam("line_uuid", lineUuid).jsonBody(payload).build(), BookingById.class);
    }

    public ApiResponse<BookingById> updateBookingLineBillingPreferences(
            String bookingId, String lineUuid, Object payload) {
        return updateBookingLineBillingPreferences(bookingId, lineUuid, payload, null);
    }

    /**
     * List the custom objects orders use, with the booking's instances:
     * {@code GET /booking/{booking_id}/custom_objects}.
     */
    public ApiResponse<BookingCustomObjectList> getBookingCustomObjects(
            String bookingId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_CUSTOM_OBJECTS,
                reopen(options).pathParam("booking_id", bookingId).queryParams(queryParams).build(), BookingCustomObjectList.class);
    }

    public ApiResponse<BookingCustomObjectList> getBookingCustomObjects(
            String bookingId, Map<String, Object> queryParams) {
        return getBookingCustomObjects(bookingId, queryParams, null);
    }

    public ApiResponse<BookingCustomObjectList> getBookingCustomObjects(String bookingId) {
        return getBookingCustomObjects(bookingId, null, null);
    }

    /**
     * One custom object with the booking's instances of it:
     * {@code GET /booking/{booking_id}/custom_objects/{custom_object_id}}.
     */
    public ApiResponse<BookingCustomObject> getBookingCustomObject(
            String bookingId, String customObjectId, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requireValue(customObjectId, "custom_object_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_CUSTOM_OBJECT,
                reopen(options).pathParam("booking_id", bookingId).pathParam("custom_object_id", customObjectId).build(), BookingCustomObject.class);
    }

    public ApiResponse<BookingCustomObject> getBookingCustomObject(String bookingId, String customObjectId) {
        return getBookingCustomObject(bookingId, customObjectId, null);
    }

    /**
     * One custom object instance:
     * {@code GET /booking/{booking_id}/custom_objects/{custom_object_id}/instances/{instance_id}}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getBookingCustomObjectInstance(
            String bookingId, String customObjectId, String instanceId, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requireValue(customObjectId, "custom_object_id");
        requireValue(instanceId, "instance_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_CUSTOM_OBJECT_INSTANCE,
                reopen(options).pathParam("booking_id", bookingId).pathParam("custom_object_id", customObjectId).pathParam("instance_id", instanceId).build(), null);
    }

    public ApiResponse<Dto> getBookingCustomObjectInstance(
            String bookingId, String customObjectId, String instanceId) {
        return getBookingCustomObjectInstance(bookingId, customObjectId, instanceId, null);
    }

    /**
     * The server's alias of {@code getBookingCustomObjects}:
     * {@code GET /booking/{booking_id}/custom_objects_instances}.
     */
    public ApiResponse<BookingCustomObjectList> getBookingCustomObjectsInstances(
            String bookingId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_CUSTOM_OBJECTS_INSTANCES,
                reopen(options).pathParam("booking_id", bookingId).queryParams(queryParams).build(), BookingCustomObjectList.class);
    }

    public ApiResponse<BookingCustomObjectList> getBookingCustomObjectsInstances(
            String bookingId, Map<String, Object> queryParams) {
        return getBookingCustomObjectsInstances(bookingId, queryParams, null);
    }

    public ApiResponse<BookingCustomObjectList> getBookingCustomObjectsInstances(String bookingId) {
        return getBookingCustomObjectsInstances(bookingId, null, null);
    }

    /**
     * The server's alias of {@code getBookingCustomObject}:
     * {@code GET /booking/{booking_id}/custom_objects_instances/{custom_object_id}}.
     */
    public ApiResponse<BookingCustomObject> getBookingCustomObjectsInstance(
            String bookingId, String customObjectId, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requireValue(customObjectId, "custom_object_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_CUSTOM_OBJECTS_INSTANCE,
                reopen(options).pathParam("booking_id", bookingId).pathParam("custom_object_id", customObjectId).build(), BookingCustomObject.class);
    }

    public ApiResponse<BookingCustomObject> getBookingCustomObjectsInstance(
            String bookingId, String customObjectId) {
        return getBookingCustomObjectsInstance(bookingId, customObjectId, null);
    }

    /**
     * The server's alias of {@code getBookingCustomObjectInstance}:
     * {@code GET /booking/ {booking_id}/custom_objects_instances/{custom_object_id}/instances/{instance_id}}.
     * No typed view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getBookingCustomObjectsInstanceInstance(
            String bookingId, String customObjectId, String instanceId, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requireValue(customObjectId, "custom_object_id");
        requireValue(instanceId, "instance_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_CUSTOM_OBJECTS_INSTANCE_INSTANCE,
                reopen(options).pathParam("booking_id", bookingId).pathParam("custom_object_id", customObjectId).pathParam("instance_id", instanceId).build(), null);
    }

    public ApiResponse<Dto> getBookingCustomObjectsInstanceInstance(
            String bookingId, String customObjectId, String instanceId) {
        return getBookingCustomObjectsInstanceInstance(bookingId, customObjectId, instanceId, null);
    }

    /** List a booking's invoices: {@code GET /booking/{booking_id}/invoices}. */
    public ApiResponse<BookingInvoiceList> getBookingInvoices(
            String bookingId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_INVOICES,
                reopen(options).pathParam("booking_id", bookingId).queryParams(queryParams).build(), BookingInvoiceList.class);
    }

    public ApiResponse<BookingInvoiceList> getBookingInvoices(
            String bookingId, Map<String, Object> queryParams) {
        return getBookingInvoices(bookingId, queryParams, null);
    }

    public ApiResponse<BookingInvoiceList> getBookingInvoices(String bookingId) {
        return getBookingInvoices(bookingId, null, null);
    }

    /**
     * List a booking's fulfillments: {@code GET /booking/{booking_id}/item_fulfillments}. No typed
     * view; read {@code getMap()}.
     */
    public ApiResponse<Dto> getBookingItemFulfillments(
            String bookingId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_ITEM_FULFILLMENTS,
                reopen(options).pathParam("booking_id", bookingId).queryParams(queryParams).build(), null);
    }

    public ApiResponse<Dto> getBookingItemFulfillments(String bookingId, Map<String, Object> queryParams) {
        return getBookingItemFulfillments(bookingId, queryParams, null);
    }

    public ApiResponse<Dto> getBookingItemFulfillments(String bookingId) {
        return getBookingItemFulfillments(bookingId, null, null);
    }

    /**
     * One fulfillment: {@code GET /booking/{booking_id}/item_fulfillments/{fulfillment_id}}; the
     * server does not check that the fulfillment belongs to the booking.
     */
    public ApiResponse<BookingItemFulfillment> getBookingItemFulfillment(
            String bookingId, String fulfillmentId, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requireValue(fulfillmentId, "fulfillment_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_ITEM_FULFILLMENT,
                reopen(options).pathParam("booking_id", bookingId).pathParam("fulfillment_id", fulfillmentId).build(), BookingItemFulfillment.class);
    }

    public ApiResponse<BookingItemFulfillment> getBookingItemFulfillment(
            String bookingId, String fulfillmentId) {
        return getBookingItemFulfillment(bookingId, fulfillmentId, null);
    }

    /** A booking's KPIs: {@code GET /booking/{booking_id}/kpis}, under {@code order_kpis}. */
    public ApiResponse<BookingKpis> getBookingKpis(
            String bookingId, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_KPIS,
                reopen(options).pathParam("booking_id", bookingId).build(), BookingKpis.class);
    }

    public ApiResponse<BookingKpis> getBookingKpis(String bookingId) {
        return getBookingKpis(bookingId, null);
    }

    /**
     * The payment methods bound to a booking: {@code GET /booking/{booking_id}/payment_methods};
     * {@code 404} when none is.
     */
    public ApiResponse<BookingPaymentMethods> getBookingPaymentMethods(
            String bookingId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_PAYMENT_METHODS,
                reopen(options).pathParam("booking_id", bookingId).queryParams(queryParams).build(), BookingPaymentMethods.class);
    }

    public ApiResponse<BookingPaymentMethods> getBookingPaymentMethods(
            String bookingId, Map<String, Object> queryParams) {
        return getBookingPaymentMethods(bookingId, queryParams, null);
    }

    public ApiResponse<BookingPaymentMethods> getBookingPaymentMethods(String bookingId) {
        return getBookingPaymentMethods(bookingId, null, null);
    }

    /**
     * One bound payment method, by its reference:
     * {@code GET /booking/{booking_id}/payment_methods/{payment_method_ref}}.
     */
    public ApiResponse<BookingPaymentMethod> getBookingPaymentMethod(
            String bookingId, String paymentMethodRef, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requireValue(paymentMethodRef, "payment_method_ref");
        return client.sendGetRequest(BookingsUrls.BOOKING_PAYMENT_METHOD,
                reopen(options).pathParam("booking_id", bookingId).pathParam("payment_method_ref", paymentMethodRef).build(), BookingPaymentMethod.class);
    }

    public ApiResponse<BookingPaymentMethod> getBookingPaymentMethod(
            String bookingId, String paymentMethodRef) {
        return getBookingPaymentMethod(bookingId, paymentMethodRef, null);
    }

    /** List a booking's payments: {@code GET /booking/{booking_id}/payments}. */
    public ApiResponse<BookingPaymentList> getBookingPayments(
            String bookingId, Map<String, Object> queryParams, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_PAYMENTS,
                reopen(options).pathParam("booking_id", bookingId).queryParams(queryParams).build(), BookingPaymentList.class);
    }

    public ApiResponse<BookingPaymentList> getBookingPayments(
            String bookingId, Map<String, Object> queryParams) {
        return getBookingPayments(bookingId, queryParams, null);
    }

    public ApiResponse<BookingPaymentList> getBookingPayments(String bookingId) {
        return getBookingPayments(bookingId, null, null);
    }

    /**
     * One payment: {@code GET /booking/{booking_id}/payments/{payment_id}}; the server does not
     * check that the payment belongs to the booking.
     */
    public ApiResponse<BookingPaymentView> getBookingPayment(
            String bookingId, String paymentId, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requireValue(paymentId, "payment_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_PAYMENT,
                reopen(options).pathParam("booking_id", bookingId).pathParam("payment_id", paymentId).build(), BookingPaymentView.class);
    }

    public ApiResponse<BookingPaymentView> getBookingPayment(String bookingId, String paymentId) {
        return getBookingPayment(bookingId, paymentId, null);
    }

    /** A booking's PDF links: {@code GET /booking/{booking_id}/pdf}. */
    public ApiResponse<BookingPdf> getBookingPdf(
            String bookingId, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_PDF,
                reopen(options).pathParam("booking_id", bookingId).build(), BookingPdf.class);
    }

    public ApiResponse<BookingPdf> getBookingPdf(String bookingId) {
        return getBookingPdf(bookingId, null);
    }

    /**
     * The deposit and instalment invoices of a converted quote:
     * {@code GET /booking/{booking_id}/quote_partial_invoices}.
     */
    public ApiResponse<BookingQuotePartialInvoices> getBookingQuotePartialInvoices(
            String bookingId, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_QUOTE_PARTIAL_INVOICES,
                reopen(options).pathParam("booking_id", bookingId).build(), BookingQuotePartialInvoices.class);
    }

    public ApiResponse<BookingQuotePartialInvoices> getBookingQuotePartialInvoices(String bookingId) {
        return getBookingQuotePartialInvoices(bookingId, null);
    }

    /**
     * A shipping line's PDF: {@code GET /booking/{booking_id}/shipping_pdf/{shipping_line_id}};
     * {@code 404} until the line has a generated shipping PDF. No typed view; read
     * {@code getMap()}.
     */
    public ApiResponse<Dto> getBookingShippingPdf(
            String bookingId, String shippingLineId, RequestOptions options) {
        requireValue(bookingId, "booking_id");
        requireValue(shippingLineId, "shipping_line_id");
        return client.sendGetRequest(BookingsUrls.BOOKING_SHIPPING_PDF,
                reopen(options).pathParam("booking_id", bookingId).pathParam("shipping_line_id", shippingLineId).build(), null);
    }

    public ApiResponse<Dto> getBookingShippingPdf(String bookingId, String shippingLineId) {
        return getBookingShippingPdf(bookingId, shippingLineId, null);
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
