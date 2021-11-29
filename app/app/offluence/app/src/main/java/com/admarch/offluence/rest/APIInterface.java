package com.admarch.offluence.rest;

import com.admarch.offluence.model.Earnings;
import com.admarch.offluence.model.EndRideResponse;
import com.admarch.offluence.model.LeaderBoard;
import com.admarch.offluence.model.Location;
import com.admarch.offluence.model.Locations;
import com.admarch.offluence.model.LoginResponse;
import com.admarch.offluence.model.Ride;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

        @GET("v1/earning")
        Call<Earnings> getEarnings(@Query("regNumber") String regNumber);

        @GET("v1/ranking")
        Call<List<LeaderBoard>> getLeaderBoard();

        @POST("location/v1/track")
        Call<Boolean> sendLocation(@Query("regNumber") String regNumber,
                     @Query("rideId") String rideId,
                     @Body Locations location);

//        @POST("v1/register")
//        Call<LoginResponse> createUser(@Body LoginResponse login);
//        @POST("v1/register")
//        Call<LoginResponse> createUser(@Body LoginResponse login);

}

