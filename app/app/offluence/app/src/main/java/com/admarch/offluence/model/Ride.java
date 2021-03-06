package com.admarch.offluence.model;

import com.google.gson.annotations.SerializedName;

public class Ride {
    @SerializedName("regNumber")
    public String regNumber;

    @SerializedName("rideId")
    public String rideId;
    @SerializedName("startRideTime")
    public String startRideTime;
    @SerializedName("isActive")
    public int isActive;
    @SerializedName("rideSourceLat")
    private String rideSourceLat;
    @SerializedName("rideSourceLon")
    private String rideSourceLon;



    public Ride(String regNumber,
                String rideId,
                String startRideTime,
                int isActive) {
        this.regNumber = regNumber;
        this.rideId = rideId;
        this.startRideTime = startRideTime;
        this.isActive = isActive;
    }

    public String getRideId() {
        return rideId;
    }
    public String getRegNumber() {
        return regNumber;
    }
    public String getStartRideTime() {
        return startRideTime;
    }
    public int getIsActive() {
        return isActive;
    }

    public void setRideSourceLat(String rideSourceLat) {
        this.rideSourceLat = rideSourceLat;
    }

    public void setRideSourceLon(String rideSourceLon) {
        this.rideSourceLon = rideSourceLon;
    }

    @Override
    public String toString() {
        return "{\"regNumber\":\"" + regNumber  + "\", \"rideId\":\"" + rideId + "\",\" startRideTime:\"" + startRideTime  + "\", \"isActive\":\"" + isActive + "\"}";
    }
}
