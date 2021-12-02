package com.admarch.service;

import com.admarch.model.Influencer;
import com.admarch.model.RideDetails;
import com.admarch.model.Rides;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RideService {
    void startRide(Rides rides);
    void saveRide(RideDetails rideDetails);
    void saveRides(List<RideDetails> rideDetails);
}
