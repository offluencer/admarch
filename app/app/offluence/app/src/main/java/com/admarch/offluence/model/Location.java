package com.admarch.offluence.model;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("timestamp")
    private long timestamp;

    public Location(double latitude, double longitude, long timestamp){
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }
}
