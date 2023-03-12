package com.xyz.booktickets.theatreservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyz.booktickets.theatreservice.dto.Show;
import com.xyz.booktickets.theatreservice.model.Movie;
import com.xyz.booktickets.theatreservice.model.MovieFilterEnum;
import com.xyz.booktickets.theatreservice.model.TheatreShows;
import com.xyz.booktickets.theatreservice.repository.CityRepository;
import com.xyz.booktickets.theatreservice.repository.MovieRepository;
import com.xyz.booktickets.theatreservice.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Override
    public List<Movie> getMovies(MovieFilterEnum filter, String filterVal) {
        List<Movie> _movies= new ArrayList<>();
        switch (filter.name()){
            case "CITY_ID" :
                try {
                    Long _cityId = Long.parseLong(filterVal);
                    return movieRepository.findByCityIdEquals(_cityId);
                }catch (Exception ex){
                    throw new HTTPException(HttpStatus.BAD_REQUEST.value());
                }
            case "MOVIE_NAME":
                return movieRepository.findByMovieNameLikeIgnoreCase(filterVal);
            case "MOVIE_GENRE":
                return movieRepository.findByGenreLikeIgnoreCase(filterVal);
            case "MOVIE_LANGUAGE":
                return movieRepository.findByLanguageLikeIgnoreCase(filterVal);
        }
        return _movies;
    }

    @Override
    public List<com.xyz.booktickets.theatreservice.dto.TheatreShows> getShows(Long movieId, LocalDateTime date) {
        LocalDateTime _today = LocalDateTime.now();
        if(date.isBefore(_today))
        {
           throw new HTTPException(HttpStatus.FORBIDDEN.value());
        }
        List<TheatreShows> _shows = showRepository.findByMovieIdEqualsAndShowTimeIsGreaterThanAndShowTimeIsBetween(movieId,_today,date,date.plusDays(1));
        List<com.xyz.booktickets.theatreservice.dto.TheatreShows> shows = new ArrayList<>();
        _shows.forEach(theatreShow -> shows.add(objectMapper.convertValue(theatreShow, com.xyz.booktickets.theatreservice.dto.TheatreShows.class)));
        return shows;
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
