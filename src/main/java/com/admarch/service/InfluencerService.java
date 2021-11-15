package com.admarch.service;

import com.admarch.model.Influencer;
import org.springframework.stereotype.Component;

@Component
public interface InfluencerService {
    void createInfluencer(Influencer influencer);
}
