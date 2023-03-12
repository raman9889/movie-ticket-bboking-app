package com.xyz.booktickets.bookingservice.scheduler;

import com.xyz.booktickets.bookingservice.feign.TheatreFeignClient;
import com.xyz.booktickets.bookingservice.model.Booking;
import com.xyz.booktickets.bookingservice.model.BookingStatusEnum;
import com.xyz.booktickets.bookingservice.repository.BookingRepository;
import com.xyz.booktickets.theatreservice.dto.StringResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.ws.http.HTTPException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class TicketReleaseBot {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    TheatreFeignClient theatreFeignClient;

    @Scheduled(fixedDelay = 5000*60)
    public void releaseTickets(){
        log.info("Ticket release bot activated");
        List<Booking> idleBookingIds = bookingRepository.findByBookingStatusEqualsAndOrderTimeIsLessThan(
                BookingStatusEnum.PENDING_CONFIRMATION,LocalDateTime.now().minusMinutes(8));
        idleBookingIds.stream().map(Booking::getBookingId).forEach(bookingId -> {
            log.info("Going to release booking for bookingId :{}",bookingId);
            theatreFeignClient.releaseShowTickets(bookingId);
        });
        log.info("Ticket release bot activated");
    }
}
