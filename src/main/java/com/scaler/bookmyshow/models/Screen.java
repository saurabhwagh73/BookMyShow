package com.scaler.bookmyshow.models;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Screen extends BaseModel{
    private String name;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
    @OneToMany
    private List<Seat> seats;
}
