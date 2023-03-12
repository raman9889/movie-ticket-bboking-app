package com.xyz.booktickets.theatreservice.service;

import com.xyz.booktickets.theatreservice.dto.Show;
import com.xyz.booktickets.theatreservice.dto.TheatreOnboarding;
import com.xyz.booktickets.theatreservice.dto.TheatreOnboardingResponse;
import com.xyz.booktickets.theatreservice.dto.TheatreShows;
import com.xyz.booktickets.theatreservice.model.TheatreShowSeats;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TheatreService {
    List<TheatreShows> getTheatreShows(Long theatreId, LocalDateTime date);

    TheatreOnboardingResponse onboardTheatre(TheatreOnboarding theatreOnboarding);

    TheatreOnboardingResponse addShow(Long theatreId, Show show);

}
