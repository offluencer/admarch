package com.admarch.offluence.rest;

import com.admarch.offluence.model.EndRideResponse;
import com.admarch.offluence.model.LoginResponse;
import com.admarch.offluence.model.Ride;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {

        // get json list of items example
        // TODO allow data scrolling
//        @GET("/items/json")
//        void getItems(Callback<List<Item>> callback);

        // posting a new item example

        @POST("v1/register")
        Call<LoginResponse> createUser(@Body LoginResponse login);

        @POST("v1/startRide")
        Call<Ride> startRide(@Body Ride ride);
        @POST("v1/endRide")
        Call<EndRideResponse> endRide(@Body EndRideResponse endRide);

//        @POST("v1/register")
//        Call<LoginResponse> createUser(@Body LoginResponse login);
//        @POST("v1/register")
//        Call<LoginResponse> createUser(@Body LoginResponse login);

}

