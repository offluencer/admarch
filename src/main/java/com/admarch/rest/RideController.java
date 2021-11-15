package com.admarch.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admarch/")
public class RideController {
    public void updateRideLocation(){}
    public void upsertViewerInfo(){}
    public void upsertRideInfo(){}
    public void startRide(){}

}
