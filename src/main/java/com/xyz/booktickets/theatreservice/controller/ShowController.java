package com.xyz.booktickets.theatreservice.controller;

import com.xyz.booktickets.theatreservice.dto.StringResponse;
import com.xyz.booktickets.theatreservice.model.OperationEnum;
import com.xyz.booktickets.theatreservice.model.TheatreShowSeats;
import com.xyz.booktickets.theatreservice.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    ShowService showService;

    @PutMapping("/{showId}/book-tickets")
    public ResponseEntity<StringResponse> bookShowTickets(@PathVariable("showId") Long showId,
                                                          @RequestParam(name = "bookingId") Long bookingId,
                                                          @RequestParam(name = "seatIds") String seatIds) {
        try {
            showService.updateSeats(showId,bookingId, seatIds ,OperationEnum.BOOKING);
            return new ResponseEntity<>(new StringResponse("success"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/release-tickets")
    public ResponseEntity<StringResponse> releaseShowTickets(@RequestParam(name = "bookingId") Long bookingId) {
        try {
            showService.updateSeats(null,bookingId, null, OperationEnum.CLEAR_BOOKING);

            return new ResponseEntity<>(new StringResponse("success"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{showId}")
    public ResponseEntity<List<TheatreShowSeats>> getTheatreShowSeats(@PathVariable("showId") Long showId,
                                                                      @RequestParam(name = "seats", required = false)
                                                                      String seats) {
        try {
            List<TheatreShowSeats> _theatre = showService.getTheatreShowSeats(showId,seats);

            return new ResponseEntity<>(_theatre, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
