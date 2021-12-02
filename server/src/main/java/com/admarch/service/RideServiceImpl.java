package com.admarch.service;

import com.admarch.dao.RideRepository;
import com.admarch.dao.ViewerRepository;
import com.admarch.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
    public void saveRide(RideDetails rideDetails) {
//        Rides rides = rideRepository.findByregNumberAndIsActiveEquals(rideDetails.getRides().getRegNumber(),
//                1);
        Rides existingRide = new Rides();
        existingRide.setRideId(rideDetails.getRideId());
        existingRide.setRegNumber(rideDetails.getRegNumber());
        existingRide.setEndRideTime(rideDetails.getEndRideTime());
        existingRide.setRideFare(rideDetails.getRideFare());
        existingRide.setIsActive(0);
        existingRide.setRideDestinationLat(rideDetails.getRideDestinationLat());
        existingRide.setRideDestinationLon(rideDetails.getRideDestinationLon());
        existingRide.setRideSourceLat(rideDetails.getRideSourceLat());
        existingRide.setRideSourceLon(rideDetails.getRideSourceLon());
        existingRide.setStartRideTime(rideDetails.getStartRideTime());
//        endRide.setRideId(rides.getRideId());
        rideRepository.save(existingRide);
        List<Viewer> viewerList = rideDetails.getViewers();
        List<Viewers> viewers = new ArrayList<Viewers>();
        for(Viewer v : viewerList){
            Viewers vs = new Viewers();
            vs.setRideId(existingRide.getRideId());
            vs.setAvgAge(Integer.parseInt(v.getAvgAge()));
            vs.setGender(v.getGender());
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            vs.setViewerId(uuidAsString);
            viewers.add(vs);
        }
        viewerRepository.save(viewers);
    }

    // TODO: refactor this - db call is done for each ride
    @Override
    public void saveRides(List<RideDetails> rideDetails) {
        for(RideDetails ride: rideDetails)
            saveRide(ride);
    }
}
