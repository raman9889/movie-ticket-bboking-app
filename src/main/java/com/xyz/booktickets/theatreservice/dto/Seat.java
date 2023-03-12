package com.xyz.booktickets.theatreservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Seat {
    @NotNull
    private final int seatNumber;
    @NotNull
    private final String seatCategory;
    @NotNull
    private final double price;
    @NotNull
    private final String seatRow;
}
