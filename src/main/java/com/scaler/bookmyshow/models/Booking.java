package com.scaler.bookmyshow.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
public class Booking extends BaseModel{
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    @ManyToOne
    private Show show;
    @ManyToMany
    private List<ShowSeat> showSeats;
    @ManyToOne
    private User user;
    private Date bookedAt;
    private int amount;
    @OneToMany
    private List<Payment> payments;
}
