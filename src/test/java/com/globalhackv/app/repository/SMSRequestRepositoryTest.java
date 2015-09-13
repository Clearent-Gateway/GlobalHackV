package com.globalhackv.app.repository;

import com.globalhackv.app.GlobalHackVApplication;
import com.globalhackv.app.domain.Citation;
import com.globalhackv.app.domain.SMSRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GlobalHackVApplication.class)
public class SMSRequestRepositoryTest {

    public static final String FIRST_NAME = "Peter";
    public static final String LAST_NAME = "Sherman";
    public static final String ADDRESS = "42 Wallaby Way";
    public static final String DATE_OF_BIRTH = "12/12/12";
    public static final String DRIVERS_LISCENSE = "N1231231234";
    public static final int CITATION_NUMBER = 345345;

    @Autowired
    SMSRequestRepository smsRequestRepository;

    @Before
    public void setUp() throws Exception {
    	smsRequestRepository.deleteAll();
    }

    @Test
    public void testFindByPhoneNumber() throws Exception {
    	SMSRequest example = new SMSRequest();
    	String phoneNumber = "3143744742";
    	example.setPhoneNumber(phoneNumber);
        example.setFirstName(FIRST_NAME);
        example.setLastName(LAST_NAME);
        example.setDateOfbirth(new Date(DATE_OF_BIRTH));
        example.setDriversLicense(DRIVERS_LISCENSE);
        example.setAddress(ADDRESS);
        smsRequestRepository.save(example);

        List<SMSRequest> smsRequest = smsRequestRepository.findByPhoneNumber(phoneNumber);

        assertEquals(FIRST_NAME,smsRequest.get(0).getFirstName());
    }




}