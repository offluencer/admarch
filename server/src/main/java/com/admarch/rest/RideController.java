package com.admarch.rest;

import com.admarch.model.Influencer;
import com.admarch.model.RideDetails;
import com.admarch.model.Rides;
import com.admarch.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admarch/")
public class RideController {

    @Autowired
    RideService rideService;

    public void updateRideLocation(){}
    public void upsertViewerInfo(){}
    public void upsertRideInfo(){}

    @RequestMapping(method = RequestMethod.POST, value = "/{version:[v|V][0-9]+}/startRide")
    public Rides startRide(
            @PathVariable("version") String version,
            @RequestParam(value = "nonce", required = false) String nonce,
            @RequestBody Rides rides
    ){
        rideService.startRide(rides);
        return rides;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{version:[v|V][0-9]+}/endRide")
    public RideDetails endRide(
            @PathVariable("version") String version,
            @RequestParam(value = "nonce", required = false) String nonce,
            @RequestBody RideDetails rides
    ){
        rideService.endRide(rides);
        return rides;
    }

}
