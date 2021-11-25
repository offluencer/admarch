package com.admarch.offluence.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EndRideResponse {
    @SerializedName("regNumber")
    public String regNumber;

    @SerializedName("rideId")
    public String rideId;
    @SerializedName("endRideTime")
    public String endRideTime;
    @SerializedName("rideDuration")
    public String rideDuration;
    @SerializedName("rideFare")
    public String rideFare;
    @SerializedName("noOfPassengers")
    public String noOfPassengers;
    @SerializedName("viewers")
    public List<Viewer> viewers;

    @SerializedName("isActive")
    public int isActive;


    public EndRideResponse(String regNumber,
                           String rideId,
                           String endRideTime,
                           int isActive) {
        this.regNumber = regNumber;
        this.rideId = rideId;
        this.endRideTime = endRideTime;
        this.isActive = isActive;
    }

    public EndRideResponse(){}
    
    public String getRideId() {
        return rideId;
    }
    public String getRegNumber() {
        return regNumber;
    }
    public String getEndRideTime() {
        return endRideTime;
    }
    public void setRideFare(String rideFare) {
        this.rideFare = rideFare;
    }
    public void setRideDuration(String rideDuration) {
        this.rideDuration = rideDuration;
    }

    public void setNoOfPassengers(String noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }
    public int getIsActive() {
        return isActive;
    }

    public void setViewers(List<Viewer> viewers){
        this.viewers = viewers;
    }

}
