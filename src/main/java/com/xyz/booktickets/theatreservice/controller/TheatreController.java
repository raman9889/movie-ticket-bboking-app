package com.xyz.booktickets.theatreservice.controller;

import com.xyz.booktickets.theatreservice.dto.Show;
import com.xyz.booktickets.theatreservice.dto.TheatreOnboarding;
import com.xyz.booktickets.theatreservice.dto.TheatreOnboardingResponse;
import com.xyz.booktickets.theatreservice.model.Theatre;
import com.xyz.booktickets.theatreservice.dto.TheatreShows;
import com.xyz.booktickets.theatreservice.model.TheatreShowSeats;
import com.xyz.booktickets.theatreservice.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @GetMapping("/{theatreId}/shows")
    public ResponseEntity<List<TheatreShows>> getTheatreShows(@PathVariable("theatreId") Long theatreId,
                                                   @RequestParam("date") @DateTimeFormat(pattern="MM/dd/yyyy") @Valid
                                                           Date date) {
        try {
            List<TheatreShows> _theatre = theatreService.getTheatreShows(theatreId,
                    date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

            return new ResponseEntity<>(_theatre, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<TheatreOnboardingResponse> onboardTheatre(@Valid @RequestBody TheatreOnboarding theatreOnboarding) {
        try {
            TheatreOnboardingResponse _theatre = theatreService.onboardTheatre(theatreOnboarding);

            return new ResponseEntity<>(_theatre, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{theatreId}/shows")
    public ResponseEntity<TheatreOnboardingResponse> addShow(@PathVariable("theatreId") Long theatreId,
                                                             @Valid @RequestBody Show show) {
        try {
            TheatreOnboardingResponse _theatre = theatreService.addShow(theatreId,show);

            return new ResponseEntity<>(_theatre, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
