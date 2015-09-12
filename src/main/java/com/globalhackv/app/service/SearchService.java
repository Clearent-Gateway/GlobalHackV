package com.globalhackv.app.service;

import com.globalhackv.app.domain.Citation;
import com.globalhackv.app.domain.Violation;
import com.globalhackv.app.repository.CitationRepository;
import com.globalhackv.app.repository.CitationSpec;
import com.globalhackv.app.repository.ViolationRepository;
import com.globalhackv.app.repository.ViolationSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    CitationRepository citationRepository;

    @Autowired
    ViolationRepository violationRepository;


    public List<Citation> findByCitation(Citation example) {
        CitationSpec citationSpec = new CitationSpec(example);
        return citationRepository.findAll(citationSpec);
    }

    public List<Violation> findViolation(long citationNumber) {
        ViolationSpec violationSpec = new ViolationSpec(citationNumber);
        return violationRepository.findAll(violationSpec);
    }

}
