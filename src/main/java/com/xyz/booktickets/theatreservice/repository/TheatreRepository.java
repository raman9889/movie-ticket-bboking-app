package com.xyz.booktickets.theatreservice.repository;

import com.xyz.booktickets.theatreservice.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    List<Theatre> findByCityIdEquals(String cityId);
}
