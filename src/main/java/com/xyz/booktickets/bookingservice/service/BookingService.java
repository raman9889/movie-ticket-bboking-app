package com.xyz.booktickets.bookingservice.service;

import com.xyz.booktickets.bookingservice.dto.BookingResponse;
import com.xyz.booktickets.bookingservice.dto.Booking;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(Booking booking);

    List<com.xyz.booktickets.bookingservice.model.Booking> getBookingsForUser(Long userId);
}
