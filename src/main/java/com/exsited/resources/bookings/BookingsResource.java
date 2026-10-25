package com.exsited.resources.bookings;

import com.exsited.client.ApiResponse;
import com.exsited.client.BoundResource;
import com.exsited.client.ExsitedClient;
import com.exsited.client.RequestOptions;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingById;
import com.exsited.resources.bookings.bookings_dto.BookingsDto.BookingList;
import java.util.Map;

/**
 * Forwards every call to {@link Bookings}.
 *
 * @deprecated {@link Bookings} is bound to a client itself and is what
 *     {@code client.bookings()} answers; use it instead.
 */
@Deprecated
public final class BookingsResource implements BoundResource {

    private final Bookings bookings;

    public BookingsResource(ExsitedClient client) {
        this.bookings = new Bookings(client);
    }

    @Override
    public ExsitedClient getClient() {
        return bookings.getClient();
    }

    public ApiResponse<BookingList> getBooking(
            Map<String, Object> queryParams, RequestOptions options) {
        return bookings.getBooking(queryParams, options);
    }

    public ApiResponse<BookingList> getBooking(Map<String, Object> queryParams) {
        return bookings.getBooking(queryParams);
    }

    public ApiResponse<BookingById> getBookingById(
            String bookingId, Map<String, Object> queryParams, RequestOptions options) {
        return bookings.getBookingById(bookingId, queryParams, options);
    }

    public ApiResponse<BookingById> getBookingById(
            String bookingId, Map<String, Object> queryParams) {
        return bookings.getBookingById(bookingId, queryParams);
    }
}
