package com.xyz.booktickets.bookingservice.dto;

import com.xyz.booktickets.bookingservice.model.BookingStatusEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class BookingResponse implements Serializable {

    private final long showId;

    private final long userId;

    private final BookingStatusEnum bookingStatus;

    private final String transactionId;

    private int seats;

    private Double amount;

}
