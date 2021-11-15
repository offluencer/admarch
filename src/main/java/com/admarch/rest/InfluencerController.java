package com.admarch.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admarch/")
public class InfluencerController {

    @RequestMapping(method = RequestMethod.GET, value="/{version:[v|V][0-9]+}/test")
    public String test(){
        return "Test";
    }

    public void loginInfluencer(){}
    public void getInfluencerEarnings(){}
    public void upsertInfluencerInfo(){}
    public void getAllInfluencerRankings(){}


}
