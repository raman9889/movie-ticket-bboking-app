package com.xyz.booktickets.theatreservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyz.booktickets.theatreservice.dto.*;
import com.xyz.booktickets.theatreservice.model.SeatsClassification;
import com.xyz.booktickets.theatreservice.model.Theatre;
import com.xyz.booktickets.theatreservice.model.TheatreShowSeats;
import com.xyz.booktickets.theatreservice.repository.SeatClassificationRepository;
import com.xyz.booktickets.theatreservice.repository.ShowRepository;
import com.xyz.booktickets.theatreservice.repository.TheatreRepository;
import com.xyz.booktickets.theatreservice.repository.TheatreShowSeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.ws.http.HTTPException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TheatreServiceImpl implements TheatreService{

    @Autowired
    ShowRepository showRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    SeatClassificationRepository seatClassificationRepository;

    @Autowired
    TheatreShowSeatsRepository theatreShowSeatsRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<TheatreShows> getTheatreShows(Long theatreId, LocalDateTime date) {
        LocalDateTime _today = LocalDateTime.now();
        if(date.isBefore(_today))
        {
            throw new HTTPException(HttpStatus.FORBIDDEN.value());
        }
        List<com.xyz.booktickets.theatreservice.model.TheatreShows> _shows = showRepository
                .findByTheatreIdEqualsAndShowTimeIsGreaterThanAndShowTimeIsBetween(theatreId,_today,date,date.plusDays(1));
        List<com.xyz.booktickets.theatreservice.dto.TheatreShows> shows = new ArrayList<>();
        _shows.forEach(theatreShow -> shows.add(objectMapper.convertValue(theatreShow, com.xyz.booktickets.theatreservice.dto.TheatreShows.class)));
        return shows;
    }

    @Override
    @Transactional
    public TheatreOnboardingResponse onboardTheatre(TheatreOnboarding theatreOnboarding)  {
        Theatre _theatre = objectMapper.convertValue(theatreOnboarding, Theatre.class);
        TheatreOnboardingResponse _theatreResp = objectMapper.convertValue(theatreRepository.save(_theatre)
                ,TheatreOnboardingResponse.class);
        if(null != theatreOnboarding.getShows() && !theatreOnboarding.getShows().isEmpty()){
            theatreOnboarding.getShows().forEach(show -> addShow(_theatreResp.getTheatreId(), show));
        }
        return _theatreResp;
    }

    @Override
    @Transactional
    public TheatreOnboardingResponse addShow(Long theatreId, Show show) {
        com.xyz.booktickets.theatreservice.model.TheatreShows theatreShow = getShow(theatreId,show);
        Long showId = showRepository.saveAndFlush(theatreShow).getShowId();

        show.getSeatList().forEach(seat -> addSeat(showId,seat));
        return new TheatreOnboardingResponse(theatreId,"");
    }

    private void addSeat(Long showId, Seat seat) {

        TheatreShowSeats showSeats = new TheatreShowSeats();
        showSeats.setShowId(showId);
        showSeats.setSeatRow(seat.getSeatRow());
        showSeats.setSeatNumber(seat.getSeatNumber());
        Optional<SeatsClassification> _seatType = seatClassificationRepository.findBySeatClassificationNameEqualsIgnoreCase(seat.getSeatCategory());
        Long _seatTypeID = 0L;
        if(!_seatType.isPresent()){
            SeatsClassification seatsClassification = new SeatsClassification();
            seatsClassification.setSeatClassificationName(seat.getSeatCategory());
            seatsClassification.setPrice(seat.getPrice());
            _seatTypeID = seatClassificationRepository.saveAndFlush(seatsClassification).getSeatTypeId();
        }else{
            _seatTypeID =  _seatType.get().getSeatTypeId();
        }
        showSeats.setSeatTypeId(_seatTypeID);
        theatreShowSeatsRepository.saveAndFlush(showSeats);
    }

    private com.xyz.booktickets.theatreservice.model.TheatreShows getShow(Long theatreId, Show show) {
        com.xyz.booktickets.theatreservice.model.TheatreShows theatreShows = new com.xyz.booktickets.theatreservice.model.TheatreShows();
        theatreShows.setTheatreId(theatreId);
        theatreShows.setMovieId(show.getMovieId());
        theatreShows.setShowTime(show.getDateTime());
        return theatreShows;
    }
}
