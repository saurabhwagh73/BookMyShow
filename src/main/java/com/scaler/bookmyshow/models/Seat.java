package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Seat extends BaseModel {
    private int rowVal;
    private int colVal;
    @ManyToOne
    private SeatType seatType;
    private String number;
}
