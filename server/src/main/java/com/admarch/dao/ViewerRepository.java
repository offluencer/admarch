package com.admarch.dao;

import com.admarch.model.Rides;
import com.admarch.model.Viewers;
import javassist.tools.web.Viewer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation= Isolation.READ_UNCOMMITTED)
public interface ViewerRepository extends CrudRepository<Viewers,String> {
//    Rides findByregNumberAndIsActiveEquals(String regNumber, int isActive);

}
