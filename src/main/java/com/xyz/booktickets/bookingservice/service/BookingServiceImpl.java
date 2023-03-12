package com.xyz.booktickets.bookingservice.service;

import com.xyz.booktickets.bookingservice.dto.Booking;
import com.xyz.booktickets.bookingservice.dto.BookingResponse;
import com.xyz.booktickets.bookingservice.feign.TheatreFeignClient;
import com.xyz.booktickets.bookingservice.model.BookingStatusEnum;
import com.xyz.booktickets.bookingservice.repository.BookingRepository;
import com.xyz.booktickets.theatreservice.dto.StringResponse;
import com.xyz.booktickets.theatreservice.model.TheatreShowSeats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TheatreFeignClient theatreFeignClient;

    @Override
    public BookingResponse createBooking(Booking booking) {
        com.xyz.booktickets.bookingservice.model.Booking _booking =new com.xyz.booktickets.bookingservice.model.Booking();
        ResponseEntity<List<TheatreShowSeats>> response = theatreFeignClient.getTheatreShowSeats(booking.getShowId(),
                booking.getSeatIds());
        List<Long> suppliedSeatIds;
        if (null != booking.getSeatIds() && booking.getSeatIds().contains(",")) {
            suppliedSeatIds = Arrays.stream(booking.getSeatIds().split(",")).map(Long::parseLong).collect(toList());
        } else {
            assert booking.getSeatIds() != null;
            suppliedSeatIds = new ArrayList<>(Collections.singletonList(Long.parseLong(booking.getSeatIds())));
        }
        if(response.getStatusCode() == HttpStatus.OK){
            List<TheatreShowSeats> showSeats = response.getBody();
            assert showSeats != null;
            if(showSeats.size() < suppliedSeatIds.size()){
                log.error("All seats not available for booking");
                throw new HTTPException(HttpStatus.CONFLICT.value());
            }
            _booking.setUserId(booking.getUserId());
            _booking.setSeats(showSeats.size());
//            _booking.setAmount(booking.get));
            _booking.setBookingStatus(BookingStatusEnum.PENDING_CONFIRMATION);
            _booking.setOrderTime(LocalDateTime.now());

            validateBooking(booking.getShowId(),
                    showSeats.stream().map(TheatreShowSeats::getSeatId).map(Object::toString).collect(Collectors.joining(",")),
                            bookingRepository.saveAndFlush(_booking));

        }else{
            throw new HTTPException(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return null;
    }

    private void validateBooking(Long showId, String showSeats, com.xyz.booktickets.bookingservice.model.Booking booking
                                 ) {
        ResponseEntity<StringResponse> response = theatreFeignClient.bookShowTickets(showId, booking.getBookingId(),
                showSeats);
        if(response.getStatusCode() == HttpStatus.OK){
            booking.setBookingStatus(BookingStatusEnum.PROCESSED);
            bookingRepository.saveAndFlush(booking);

        }else{
            booking.setBookingStatus(BookingStatusEnum.FAILED);
            bookingRepository.saveAndFlush(booking);
            throw new HTTPException(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public List<com.xyz.booktickets.bookingservice.model.Booking> getBookingsForUser(Long userId) {
        return bookingRepository.findByUserIdEquals(userId);
    }
}
