package com.xyz.booktickets.theatreservice.repository;

import com.xyz.booktickets.theatreservice.model.SeatsClassification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatClassificationRepository extends JpaRepository<SeatsClassification, Long> {

    Optional<SeatsClassification> findBySeatClassificationNameEqualsIgnoreCase(String seatClassificationName);
}
