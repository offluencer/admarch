package com.admarch.offluence.model;

import com.google.gson.annotations.SerializedName;

public class EndRideResponse {
    @SerializedName("regNumber")
    public String regNumber;

    @SerializedName("rideId")
    public String rideId;
    @SerializedName("startRideTime")
    public String startRideTime;
    @SerializedName("isActive")
    public int isActive;


    public EndRideResponse(String regNumber,
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

}
