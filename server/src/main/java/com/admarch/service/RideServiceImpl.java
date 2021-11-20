package com.admarch.service;

import com.admarch.dao.InfluencerRepository;
import com.admarch.dao.RideRepository;
import com.admarch.dao.ViewerRepository;
import com.admarch.model.Influencer;
import com.admarch.model.RideDetails;
import com.admarch.model.Rides;
import com.admarch.model.Viewers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RideServiceImpl implements RideService{

    @Autowired
    RideRepository rideRepository;

    @Autowired
    ViewerRepository viewerRepository;

    @Override
    public void startRide(Rides rides) {
        rides.setIsActive(1);
        rideRepository.save(rides);
    }

    @Override
    public void endRide(RideDetails rideDetails) {
//        Rides rides = rideRepository.findByregNumberAndIsActiveEquals(rideDetails.getRides().getRegNumber(),
//                1);
        Rides endRide = rideDetails.getRides();
        endRide.setIsActive(0);
//        endRide.setRideId(rides.getRideId());
        rideRepository.save(endRide);
        List<Viewers> viewers = rideDetails.getViewers();
        viewerRepository.save(viewers);
    }


}
