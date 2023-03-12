package com.xyz.booktickets.theatreservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "theatre_shows", schema = "theatre")
@Getter @Setter
public class TheatreShows {
    @Id
    @Column(name = "show_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long showId;

    @Column(name = "show_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime showTime;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "theatre_id")
    private Long theatreId;

}
