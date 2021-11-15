package com.admarch.dao;

import com.admarch.model.Influencer;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.*;

@Repository
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation= Isolation.READ_UNCOMMITTED)
public interface InfluencerRepository extends CrudRepository<Influencer,String> {
}
