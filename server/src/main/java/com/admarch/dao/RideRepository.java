package com.admarch.dao;

import com.admarch.model.Influencer;
import com.admarch.model.Rides;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation= Isolation.READ_UNCOMMITTED)
public interface RideRepository extends CrudRepository<Rides,String> {
    Rides findByregNumberAndIsActiveEquals(String regNumber, int isActive);
}
