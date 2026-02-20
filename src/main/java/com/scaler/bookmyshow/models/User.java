package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;
@Entity
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
    @OneToMany
    private List<Booking> bookings;
}
