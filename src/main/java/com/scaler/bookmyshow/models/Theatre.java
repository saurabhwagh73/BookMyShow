package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;
@Entity
public class Theatre extends BaseModel{
    private String name;
    @ManyToOne
    private Region region;
    @OneToMany
    private List<Screen> screens;
}
