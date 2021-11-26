package com.admarch.dao;

import com.admarch.model.QRCodeInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation= Isolation.READ_UNCOMMITTED)
public interface QRCodeInfoRepository extends CrudRepository<QRCodeInfo,Integer> {
    QRCodeInfo findById(String id);
}

