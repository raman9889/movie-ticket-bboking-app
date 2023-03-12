package com.xyz.booktickets.theatreservice.controller;

import com.xyz.booktickets.theatreservice.dto.TheatreShows;
import com.xyz.booktickets.theatreservice.model.Movie;
import com.xyz.booktickets.theatreservice.model.MovieFilterEnum;
import com.xyz.booktickets.theatreservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(@RequestParam("filter") @Valid MovieFilterEnum filter, @RequestParam("value") String filterVal) {
        try {
            List<Movie> _movies = movieService.getMovies(filter, filterVal);

            return new ResponseEntity<>(_movies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{movieId}/shows")
    public ResponseEntity<List<TheatreShows>> getShows(@PathVariable("movieId") Long movieId, @RequestParam("date")
                                                @DateTimeFormat(pattern="MM/dd/yyyy") @Valid Date date) {
        try {
            List<TheatreShows> _shows = movieService.getShows(movieId,
                    date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

            return new ResponseEntity<>(_shows, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) {
        try {
            Movie _movie = movieService.addMovie(movie);

            return new ResponseEntity<>(_movie, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
