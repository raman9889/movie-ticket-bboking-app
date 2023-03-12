package com.xyz.booktickets.theatreservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Movie {
    private final Long movie_id;
    @NotNull
    private final String movieName;
    @NotNull
    private final String language;
    @NotNull
    private final String genre;
    @NotNull
    private final String description;

}
