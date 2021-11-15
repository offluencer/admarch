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
    @Column(name = "regNumber")
    private String rideSource;
    @Column(name = "rideDestination")
    private String rideDestination;
    @Column(name = "rideFare")
    private int rideFare;
    @Column(name = "rideDuration")
    private int rideDuration;
    @Column(name = "startRideTime")
    private Timestamp startRideTime;
    @Column(name = "endRideTime")
    private Timestamp endRideTime;

}
