package com.admarch.offluence.model;

import com.google.gson.annotations.SerializedName;

public class Viewer {

    @SerializedName("gender")
    public String gender;

    @SerializedName("avgAge")
    public String avgAge;

    public Viewer(String gender, String avgAge){
        this.gender = gender;
        this.avgAge = avgAge;
    }

    @Override
    public String toString() {
        return "{\"gender\":\"" + gender + "\"," + ", \"avgAge\":\"" + avgAge + "\"" + "}";
    }
}
