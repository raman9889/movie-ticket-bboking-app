package com.xyz.booktickets.bookingservice.feign;

import com.xyz.booktickets.theatreservice.dto.StringResponse;
import com.xyz.booktickets.theatreservice.model.TheatreShowSeats;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "TheatreClient", url = "${client.theatre.service.baseurl}")
public interface TheatreFeignClient {
    @PutMapping("/{showId}/book-tickets")
    ResponseEntity<StringResponse> bookShowTickets(@PathVariable("showId") Long showId,
                                                   @RequestParam(name = "bookingId") Long bookingId,
                                                   @RequestParam(name = "seatIds") String seatIds);
    @PutMapping("/release-tickets")
    ResponseEntity<StringResponse> releaseShowTickets( @RequestParam(name = "bookingId") Long bookingId);
    @GetMapping("/{showId}")
    ResponseEntity<List<TheatreShowSeats>> getTheatreShowSeats(@PathVariable("showId") Long showId,
                                                                      @RequestParam(name = "seats", required = false)
                                                                              String seats);
}
