package com.admarch.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Ride implements Serializable {


    @Id
    private String rideId;
    private String regNumber;
    private String rideSource;
    private String rideDestination;
    private int rideFare;
    private int rideDuration;

}
