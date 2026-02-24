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
    public Booking booking(Long userId, Long showId, List<Long> showSeatId) throws ShowSeatNotFoundException,
            ShowNotFoundException, UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException("Show not found"));

        List<ShowSeat> showSeats = showSeatRepository.findAllByIdInForUpdate(showSeatId);

        for (ShowSeat seat : showSeats) {
            if (!seat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new ShowSeatNotFoundException("Seat not available");
            }
            seat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
        }

        showSeatRepository.saveAll(showSeats);

        Booking booking = new Booking();
        booking.setBookedAt(new Date());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setUser(user);
        booking.setShow(show);
        booking.setShowSeats(showSeats);
        booking.setAmount(pricingService.calculatedPrice(showSeats, show));

        return bookingRepository.save(booking);
    }
}