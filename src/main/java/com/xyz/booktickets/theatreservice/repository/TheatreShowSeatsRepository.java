package com.xyz.booktickets.theatreservice.repository;


import com.xyz.booktickets.theatreservice.model.TheatreShowSeats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TheatreShowSeatsRepository extends JpaRepository<TheatreShowSeats, Long> {
    List<TheatreShowSeats> findByShowIdEqualsAndBookingIdNull(Long showId);

    List<TheatreShowSeats> findByBookingIdEquals(Long bookingId);

    List<TheatreShowSeats> findBySeatIdInAndBookingIdNull(Collection<Long> seatIds);

}
