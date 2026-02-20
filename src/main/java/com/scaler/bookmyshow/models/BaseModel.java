package com.scaler.bookmyshow.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;
@MappedSuperclass
public abstract class BaseModel {
    @Id
    private int id;
    private Date createdAt;
    private Date lastUpdatedAt;
}
