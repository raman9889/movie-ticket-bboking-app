package com.xyz.booktickets.bookingservice.controller;

import com.xyz.booktickets.bookingservice.dto.Booking;
import com.xyz.booktickets.bookingservice.dto.BookingResponse;
import com.xyz.booktickets.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<com.xyz.booktickets.bookingservice.model.Booking>> getBookings(@PathVariable("userId")
                                                                                                  @Valid Long userId) {
        try {
            List<com.xyz.booktickets.bookingservice.model.Booking> _booking = bookingService.getBookingsForUser(userId);

            return new ResponseEntity<>(_booking, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody Booking booking) {
        try {
            BookingResponse _booking = bookingService.createBooking(booking);

            return new ResponseEntity<>(_booking, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
