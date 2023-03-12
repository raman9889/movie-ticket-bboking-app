package com.xyz.booktickets.theatreservice.service;

import com.xyz.booktickets.theatreservice.model.OperationEnum;
import com.xyz.booktickets.theatreservice.model.TheatreShowSeats;
import com.xyz.booktickets.theatreservice.repository.TheatreShowSeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.ws.http.HTTPException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService{
    @Autowired
    private TheatreShowSeatsRepository theatreShowSeatsRepository;

    @Override
    public List<TheatreShowSeats> getTheatreShowSeats(Long showId, String seats) {
        if(null!=seats){
            List<Long> seatIds = Arrays.stream(seats.split(",")).map(Long::parseLong)
                    .collect(Collectors.toList());
            return theatreShowSeatsRepository.findBySeatIdInAndBookingIdNull(seatIds);
        }else{
            return theatreShowSeatsRepository.findByShowIdEqualsAndBookingIdNull(showId);
        }
    }

    @Override
    @Transactional
    public void updateSeats(Long showId, Long bookingId, String seatIds, OperationEnum operation) {
        List<TheatreShowSeats> showSeats = new ArrayList<>();
        boolean clearBookingId = false;
        switch (operation.name()) {
            case "BOOKING":
                List<Long> _seatIds = Arrays.stream(seatIds.split(",")).map(Long::parseLong)
                        .collect(Collectors.toList());

                showSeats = theatreShowSeatsRepository.findBySeatIdInAndBookingIdNull(_seatIds);
                break;
            case "CLEAR_BOOKING":
                showSeats = theatreShowSeatsRepository.findByBookingIdEquals(bookingId);
                clearBookingId = true;
                break;
        }

        if(!showSeats.isEmpty()) {
            boolean finalClearBookingId = clearBookingId;
            showSeats.forEach(theatreShowSeat -> updateBookingOnSeats(theatreShowSeat, finalClearBookingId, bookingId));
        }
    }

    private void updateBookingOnSeats(TheatreShowSeats theatreShowSeat, boolean clearBooking, Long bookingId) {

        if (clearBooking) {
            theatreShowSeat.setBookingId(null);
        } else {
            if(null != theatreShowSeat.getBookingId()){
                throw new HTTPException(HttpStatus.LOCKED.value());
            }
            theatreShowSeat.setBookingId(bookingId);
        }
        theatreShowSeatsRepository.saveAndFlush(theatreShowSeat);
    }
}
