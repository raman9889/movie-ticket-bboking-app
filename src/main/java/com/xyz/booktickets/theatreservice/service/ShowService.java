package com.xyz.booktickets.theatreservice.service;

import com.xyz.booktickets.theatreservice.model.OperationEnum;
import com.xyz.booktickets.theatreservice.model.TheatreShowSeats;

import java.util.List;

public interface ShowService {
    List<TheatreShowSeats> getTheatreShowSeats( Long showId, String seats);

    void updateSeats(Long showId, Long bookingId, String seatIds, OperationEnum operation);
}
