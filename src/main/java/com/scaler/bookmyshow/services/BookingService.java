package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.models.Booking;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingService {
    public Booking booking(Long userId, Long showId, List<Long> showSeatId){
        /*
         Get User using userId
         Get Show using showId
         Check for Availability showSeat status
         Select the seats
         create booking object and store all booking related information
         return boooking object
         */
        return null;
    }
}