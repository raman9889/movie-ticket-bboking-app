package com.xyz.booktickets.theatreservice.repository;

import com.xyz.booktickets.theatreservice.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCityNameEqualsIgnoreCase(String cityName);
}
