package com.xyz.booktickets.bookingservice.repository;

import com.xyz.booktickets.bookingservice.model.Booking;
import com.xyz.booktickets.bookingservice.model.BookingStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserIdEquals(Long userId);

    @Query(value = "SELECT booking_id from bookings.ticket_booking WHERE booking_status in ('PENDING_CONFIRMATION') and order_time > current_timestamp_with_time_zone - interval '10 minutes' limit 1000",
            nativeQuery = true )
    List<Long> findIdleBookings();

    List<Booking> findByBookingStatusEqualsAndOrderTimeIsLessThan(BookingStatusEnum bookingStatus, LocalDateTime orderTime);
}
