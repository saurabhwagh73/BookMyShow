package com.scaler.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookingRequestDto {
    private Long user_id;
    private Long show_id;
    private List<Long> showSeatId;
}
