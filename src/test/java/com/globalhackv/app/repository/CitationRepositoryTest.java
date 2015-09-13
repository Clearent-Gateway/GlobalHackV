package com.globalhackv.app.repository;

import com.globalhackv.app.GlobalHackVApplication;
import com.globalhackv.app.domain.Citation;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GlobalHackVApplication.class)
public class CitationRepositoryTest {

    public static final String FIRST_NAME = "Peter";
    public static final String LAST_NAME = "Sherman";
    public static final String ADDRESS = "42 Wallaby Way";
    public static final String DATE_OF_BIRTH = "12/12/12";
    public static final String DRIVERS_LISCENSE = "N1231231234";
    public static final int CITATION_NUMBER = 345345;

    @Autowired
    CitationRepository citationRepository;

    @Before
    public void setUp() throws Exception {
        citationRepository.deleteAll();
    }

    @Ignore
    @Test
         public void testFindBySpecFirstName() throws Exception {
        Citation example = new Citation();
        example.setFirstName(FIRST_NAME);
        example.setLastName(LAST_NAME);
        example.setDateOfBirth(DATE_OF_BIRTH);
        example.setDriversLicense(DRIVERS_LISCENSE);
        example.setAddress(ADDRESS);
        citationRepository.save(example);

        CitationSpec citationSpec = new CitationSpec(example);
        List<Citation> citations = citationRepository.findAll(citationSpec);

        assertEquals(FIRST_NAME,citations.get(0).getFirstName());
    }

    @Ignore
    @Test
    public void testFindByCitationNumber() throws Exception {
        Citation example = new Citation();
        example.setCitationNumber(CITATION_NUMBER);
        citationRepository.save(example);

        List<Citation> citations = citationRepository.findByCitationNumber(CITATION_NUMBER);

        assertEquals(CITATION_NUMBER,citations.get(0).getCitationNumber());
    }



}