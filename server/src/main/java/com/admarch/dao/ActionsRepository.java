package com.admarch.dao;

import com.admarch.model.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation= Isolation.READ_UNCOMMITTED)
public interface ActionsRepository extends CrudRepository<Actions,Integer> {
//    Rides findByregNumberAndIsActiveEquals(String regNumber, int isActive);
    @Query("SELECT new com.admarch.model.LeaderBoard(a.registerNumber, SUM(a.actionEarning)) " +
            "from Actions as a group by a.registerNumber ")
    List<LeaderBoard> countTotalEarningsByRegisterNumber();

    @Query("SELECT new com.admarch.model.Earnings(a.registerNumber, COUNT(a.actionEarning) , SUM(a.actionEarning)) " +
            "from Actions as a " +
            "where a.registerNumber = :registerNumber")
    Earnings getInfluencerEarning(
            @Param("registerNumber") String registerNumber
    );

    Actions findByregisterNumberAndDeviceIdAndCampaignId(String regNo, String deviceId, int campaignId);
}
