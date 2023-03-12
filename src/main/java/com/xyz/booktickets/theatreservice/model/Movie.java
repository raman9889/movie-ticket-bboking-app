package com.xyz.booktickets.theatreservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "movie", schema = "theatre")
@Getter @Setter
public class Movie {
    @Id
    @Column(name = "movie_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieId;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "language")
    private String language;

    @Column(name = "genre")
    private String genre;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "city_id")
    private Long cityId;

}
