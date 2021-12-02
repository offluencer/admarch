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

    @SerializedName("rideDestinationLat")
    public String rideDestinationLat;
    @SerializedName("rideDestinationLon")
    public String rideDestinationLon;
    @SerializedName("rideSourceLat")
    public String rideSourceLat;
    @SerializedName("rideSourceLon")
    public String rideSourceLon;
    @SerializedName("startRideTime")
    public String startRideTime;

    public void setRideSourceLon(String rideSourceLon) {
        this.rideSourceLon = rideSourceLon;
    }

    public void setRideSourceLat(String rideSourceLat) {
        this.rideSourceLat = rideSourceLat;
    }

    public void setStartRideTime(String startRideTime) {
        this.startRideTime = startRideTime;
    }

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

    public void setRideDestinationLat(String rideDestinationLat){
        this.rideDestinationLat = rideDestinationLat;
    }

    public void setRideDestinationLon(String rideDestinationLon) {
        this.rideDestinationLon = rideDestinationLon;
    }

    public void setViewers(List<Viewer> viewers){
        this.viewers = viewers;
    }

    @Override
    public String toString() {
        return "{" + "\"regNumber\":\"" + regNumber + "\"" + ", \"rideId\":\"" + rideId + "\"" + ", \"endRideTime\":\"" + endRideTime + "\"" + ", \"rideDuration\":\"" + rideDuration + "\"" + ", \"rideFare\":\"" + rideFare + "\"" + ", \"noOfPassengers:\":\"" + noOfPassengers + "\"" + ", \"viewers\":" + viewers + ", \"isActive\":\"" + isActive + "\", \"rideDestinationLat\":\"" + rideDestinationLat + "\"" + ", \"rideDestinationLon\":\"" + rideDestinationLon + '\"' + '}';
    }
}
