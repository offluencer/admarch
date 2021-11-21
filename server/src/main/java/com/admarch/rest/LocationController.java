package com.admarch.rest;

import com.admarch.model.Locations;
import com.admarch.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/admarch/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @RequestMapping(method = RequestMethod.POST, value = "/{version:[v|V][0-9]+}/track")
    public void track(
            @PathVariable("version") String version,
            @RequestParam(value = "nonce", required = false) String nonce,
            @RequestParam(value = "regNumber") String regNum,
            @RequestBody Locations locations,
            @RequestParam(value = "rideId",required = false,defaultValue = "") String rideId
    ) throws IOException {
        locationService.trackLocation(regNum,rideId,locations);
    }
}
