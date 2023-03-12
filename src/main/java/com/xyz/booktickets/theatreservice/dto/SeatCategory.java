package com.xyz.booktickets.theatreservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class SeatCategory {
    private final String seatCategory;
    private final double categoryPrice;
}
