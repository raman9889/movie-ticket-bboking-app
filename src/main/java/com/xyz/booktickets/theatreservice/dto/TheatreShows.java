package com.xyz.booktickets.theatreservice.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class TheatreShows {

    private final Long movieId;
    private final Long theatreId;
    private final LocalDateTime showTime;

}
