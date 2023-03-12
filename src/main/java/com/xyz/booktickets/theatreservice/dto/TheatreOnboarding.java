package com.xyz.booktickets.theatreservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TheatreOnboarding {
    @NotNull
    private final String theatreName;
    @NotNull
    private final Long cityId;
    @NotNull
    private final String theatreAddress;
    private final List<Show> shows;
}
