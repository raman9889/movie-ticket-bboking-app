package com.xyz.booktickets.bookingservice.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class Booking implements Serializable {

    @NotNull
    @NotEmpty
    private final String seatIds;

    @NotNull
    private final long showId;

    @NotNull
    private final long userId;

}
