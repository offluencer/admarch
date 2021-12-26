package com.admarch.service;

import com.admarch.dao.ActionsRepository;
import com.admarch.dao.InfluencerRepository;
import com.admarch.model.Earnings;
import com.admarch.model.Influencer;
import com.admarch.model.LeaderBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InfluencerServiceImpl implements InfluencerService{

    @Autowired
    InfluencerRepository influencerRepository;

    @Autowired
    ActionsRepository actionsRepository;

    @Override
    public void createInfluencer(Influencer influencer) {
        influencerRepository.save(influencer);
    }



    @Override
    public List<LeaderBoard> getEarningsRanking() {
        return actionsRepository.countTotalEarningsByRegisterNumber();
    }

    @Override
    public Earnings getInfluencerEarning(String regNumber) {
        try{
            Earnings earnings  = actionsRepository.getInfluencerEarning(regNumber);
            return earnings;
        }catch (Exception ex){
            return new Earnings();
        }
    }

    @Override
    public Influencer getInfluencerByregNo(String regNo) {
        return influencerRepository.findOne(regNo);
    }

}
