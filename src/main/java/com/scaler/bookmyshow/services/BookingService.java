package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.Exception.ShowNotFoundException;
import com.scaler.bookmyshow.Exception.ShowSeatNotFoundException;
import com.scaler.bookmyshow.Exception.UserNotFoundException;
import com.scaler.bookmyshow.models.*;
import com.scaler.bookmyshow.repositories.BookingRepository;
import com.scaler.bookmyshow.repositories.ShowRepository;
import com.scaler.bookmyshow.repositories.ShowSeatRepository;
import com.scaler.bookmyshow.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private PricingService pricingService;
    @Autowired
    private BookingRepository bookingRepository;
    @Transactional
    public Booking booking(Long userId, Long showId, List<Long> showSeatId) throws UserNotFoundException,
            ShowNotFoundException, ShowSeatNotFoundException {
        /*
         Get User using userId
         Get Show using showId
         Check for Availability showSeat status
         Select the seats
         create booking object and store all booking related information
         return boooking object
         */
        Optional<User> user=userRepository.getUserById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("User is not existed");
        }
        User user1=user.get();

        Optional<Show> show=showRepository.getShowById(showId);
        if(show.isEmpty()){
            throw new ShowNotFoundException("Show is not present");
        }
        Show bookedShow=show.get();

        List<ShowSeat> showSeats=showSeatRepository.getAllShowSeatById(showSeatId);
        //Check for availability status of showSeats
        for(ShowSeat showSeat:showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new ShowSeatNotFoundException("Please choose another ShowSeat");
            }
        }
        List<ShowSeat> savedSeats=new ArrayList<>();
        for(ShowSeat showSeat:showSeats){
            if(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
                savedSeats.add(showSeatRepository.save(showSeat));
            }
        }
        Booking booking=new Booking();
        booking.setBookedAt(new Date());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setUser(user1);
        booking.setShow(bookedShow);
        booking.setShowSeats(savedSeats);
        booking.setAmount(pricingService.calculatedPrice(savedSeats,bookedShow));


        return bookingRepository.save(booking);
    }
}