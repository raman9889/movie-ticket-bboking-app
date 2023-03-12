package com.xyz.booktickets.theatreservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Show {
    @NotNull
    private final Long movieId;
    @NotNull
    private final Long theatreId;
    @NotNull
    private final LocalDateTime dateTime;
    @NotNull
    private final List<Seat> seatList;
}
