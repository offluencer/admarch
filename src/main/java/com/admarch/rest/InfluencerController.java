package com.admarch.rest;

import com.admarch.model.Influencer;
import com.admarch.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admarch/")
public class InfluencerController {

    @Autowired
    InfluencerService influencerService;

    @RequestMapping(method = RequestMethod.GET, value="/{version:[v|V][0-9]+}/test")
    public String test(){
        return "Test";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{version:[v|V][0-9]+}/register")
    public void registerInfluencer(
            @PathVariable("version") String version,
            @RequestParam(value = "nonce", required = false) String nonce,
            @RequestBody Influencer influencer
    ){
        influencerService.createInfluencer(influencer);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{version:[v|V][0-9]+}/earning")
    public void getInfluencerEarnings(
            @PathVariable("version") String version,
            @RequestParam(value = "nonce", required = false) String nonce
            ){
        
    }
    public void upsertInfluencerInfo(){}
    public void getAllInfluencerRankings(){}


}
