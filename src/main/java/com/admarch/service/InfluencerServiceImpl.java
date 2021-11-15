package com.admarch.service;

import com.admarch.dao.InfluencerRepository;
import com.admarch.model.Influencer;
import org.springframework.beans.factory.annotation.Autowired;

public class InfluencerServiceImpl implements InfluencerService{

    @Autowired
    InfluencerRepository influencerRepository;

    @Override
    public void createInfluencer(Influencer influencer) {
        influencerRepository.save(influencer);
    }
}
