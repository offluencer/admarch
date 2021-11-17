package com.admarch.offluence.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("regNumber")
    public String regNumber;

    public LoginResponse(String regNumber) {
        this.regNumber = regNumber;
    }
    public String getRegNumber() {
        return regNumber;
    }
}
