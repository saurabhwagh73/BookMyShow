package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dtos.ResponseStatus;
import com.scaler.bookmyshow.dtos.bookingRequestDto;
import com.scaler.bookmyshow.dtos.bookingResponseDto;
import com.scaler.bookmyshow.models.Booking;
import com.scaler.bookmyshow.services.bookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class bookingController {
    @Autowired
    private bookingService bookingservice;

    public bookingResponseDto bookMovie(bookingRequestDto requestDto){
        bookingResponseDto responseDto=new bookingResponseDto();
        Booking booking;
        try{
            booking=bookingservice.booking(requestDto.getUser_id(),
                    requestDto.getShow_id(),
                    requestDto.getShowSeatId());
            responseDto.setAmount(booking.getAmount());
            responseDto.setBookingId(booking.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
