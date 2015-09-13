package com.globalhackv.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.globalhackv.app.domain.SMSRequest;

@Repository("smsRequestRepository")
public interface SMSRequestRepository extends JpaRepository<SMSRequest,Long>, JpaSpecificationExecutor {
 
    List<SMSRequest> findByPhoneNumber(String phoneNumber);
 
}
