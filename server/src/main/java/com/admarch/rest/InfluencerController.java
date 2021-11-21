package com.admarch.rest;

import com.admarch.model.Earnings;
import com.admarch.model.Influencer;
import com.admarch.model.LeaderBoard;
import com.admarch.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Influencer registerInfluencer(
            @PathVariable("version") String version,
            @RequestParam(value = "nonce", required = false) String nonce,
            @RequestBody Influencer influencer
    ){
        influencerService.createInfluencer(influencer);
        return influencer;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{version:[v|V][0-9]+}/earning")
    public Earnings getInfluencerEarnings(
            @PathVariable("version") String version,
            @RequestParam(value = "nonce", required = false) String nonce,
            @RequestParam(value = "regNumber") String regNum
            ){
        return influencerService.getInfluencerEarning(regNum);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{version:[v|V][0-9]+}/ranking")
    public List<LeaderBoard> getAllInfluencerRankings(
            @PathVariable("version") String version,
            @RequestParam(value = "nonce", required = false) String nonce
    ){
        return influencerService.getEarningsRanking();
    }


}
