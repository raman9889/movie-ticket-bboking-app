package com.xyz.booktickets.theatreservice.service;

import com.xyz.booktickets.theatreservice.dto.TheatreShows;
import com.xyz.booktickets.theatreservice.model.Movie;
import com.xyz.booktickets.theatreservice.model.MovieFilterEnum;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface MovieService {

    List<Movie> getMovies(MovieFilterEnum filter, String filterVal);

    List<TheatreShows> getShows(Long movieId, LocalDateTime date);

    Movie addMovie(Movie movie);
}
