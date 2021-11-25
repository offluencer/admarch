package com.admarch.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Table(name = "Rides", catalog = "admarch")
public class Rides implements Serializable {


    @Id
    @Column(name = "rideId")
    private String rideId;
    @Column(name = "regNumber")
    private String regNumber;
    @Column(name = "rideSource")
    private String rideSource;
    @Column(name = "rideDestination")
    private String rideDestination;
    @Column(name = "rideFare")
    private int rideFare;
    @Column(name = "rideDuration")
    private int rideDuration;
    @Column(name = "rideStartTime")
    private Timestamp startRideTime;
    @Column(name = "rideEndTime")
    private Timestamp endRideTime;
    @Column(name = "isActive")
    private int isActive;

}
