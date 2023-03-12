package com.xyz.booktickets.theatreservice.repository;

import com.xyz.booktickets.theatreservice.model.TheatreShows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<TheatreShows, Long> {

    List<TheatreShows> findByMovieIdEqualsAndShowTimeIsGreaterThanAndShowTimeIsBetween(Long movieId,
        LocalDateTime showTime, LocalDateTime showTimeStart, LocalDateTime showTimeEnd);

    List<TheatreShows> findByTheatreIdEqualsAndShowTimeIsGreaterThanAndShowTimeIsBetween(Long theatreId,
              LocalDateTime showTime, LocalDateTime showTimeStart, LocalDateTime showTimeEnd);

}
