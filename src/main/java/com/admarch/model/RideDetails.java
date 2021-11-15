package com.admarch.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class RideDetails implements Serializable {


    @Id
    private String rideId;
    private String passengerId;
    private String ageRange;
    private String gender;
    private int avgAge;
}
