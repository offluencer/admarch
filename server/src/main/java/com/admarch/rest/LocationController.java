package com.admarch.rest;

import com.admarch.model.Locations;
import com.admarch.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(value = "/admarch/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    Logger logger = LoggerFactory.getLogger(LocationController.class);

    @RequestMapping(method = RequestMethod.POST, value = "/{version:[v|V][0-9]+}/track")
    public boolean track(
            @PathVariable("version") String version,
            @RequestParam(value = "nonce", required = false) String nonce,
            @RequestParam(value = "regNumber") String regNum,
            @RequestBody Locations locations,
            @RequestParam(value = "rideId",required = false,defaultValue = "") String rideId
    ) throws IOException {
        boolean tracked = true;
        try{
            locationService.trackLocation(regNum,rideId,locations);
        }catch (Exception e){
            tracked = false;
            logger.error("Exception while tracking the location",e);
        }
        return tracked;
    }
}
