package com.xyz.booktickets.theatreservice.repository;

import com.xyz.booktickets.theatreservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByCityIdEquals(Long cityId);

    List<Movie> findByMovieNameLikeIgnoreCase(String movieName);

    List<Movie> findByLanguageLikeIgnoreCase(String language);

    List<Movie> findByGenreLikeIgnoreCase(String genre);
}
