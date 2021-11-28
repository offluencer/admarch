package com.admarch.offluence.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Locations {

    @SerializedName("locations")
    private List<Location> locations;

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
