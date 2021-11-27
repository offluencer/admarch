package com.admarch.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
public class RideDetails implements Serializable {

    private String rideId;
    private String regNumber;

    private int rideFare;
    private int rideDuration;
    private Timestamp startRideTime;
    private Timestamp endRideTime;
    private int isActive;
    private int noOfPassengers;
    private String rideDestinationLat;
    private String rideDestinationLon;



    List<Viewer> viewers;
}
