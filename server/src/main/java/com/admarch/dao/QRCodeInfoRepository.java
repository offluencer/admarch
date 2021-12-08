package com.admarch.dao;

import com.admarch.model.LeaderBoard;
import com.admarch.model.QRCodeInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation= Isolation.READ_UNCOMMITTED)
public interface QRCodeInfoRepository extends CrudRepository<QRCodeInfo,Integer> {
    QRCodeInfo findById(String id);

    @Query(value = "select C.tinyUrl from Campaign C,QRCodeInfo Q where Q.campaignId=C.campaignId and Q.id=?1",nativeQuery = true)
    String getCampaignUrl(String qrCodeId);

    @Query(value = "select I.regNumber from Influencer I,QRCodeInfo Q where Q.infuencerRegNo=I.regNumber and Q.id=?1",nativeQuery = true)
    String getInfluencerRegNo(String qrCodeId);
}

