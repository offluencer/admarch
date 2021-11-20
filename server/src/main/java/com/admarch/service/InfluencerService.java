package com.admarch.service;

import com.admarch.model.Earnings;
import com.admarch.model.Influencer;
import com.admarch.model.LeaderBoard;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface InfluencerService {
    void createInfluencer(Influencer influencer);
    List<LeaderBoard> getEarningsRanking();
    Earnings getInfluencerEarning(String regNumber);

}
