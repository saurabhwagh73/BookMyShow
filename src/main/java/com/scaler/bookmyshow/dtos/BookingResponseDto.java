package com.scaler.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDto {
    private int amount;
    private Long bookingId;
    private ResponseStatus responseStatus;
}
