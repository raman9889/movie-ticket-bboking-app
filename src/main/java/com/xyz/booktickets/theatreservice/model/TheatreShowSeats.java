package com.xyz.booktickets.theatreservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "theatre_show_seats", schema = "theatre")
@Getter @Setter
public class TheatreShowSeats {
    @Id
    @Column(name = "seat_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seatId;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "seat_type_id")
    private Long seatTypeId;

    @Column(name = "show_id")
    private Long showId;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "seat_row")
    private String seatRow;

}
