package com.globalhackv.app.service;

import com.globalhackv.app.domain.Citation;
import com.globalhackv.app.repository.CitationRepository;
import com.globalhackv.app.repository.CitationSpec;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwillard on 9/11/2015.
 */
public class SearchService {

    @Autowired
    CitationRepository citationRepository;

    public List<Citation> findByCitation(Citation example) {
        CitationSpec citationSpec = new CitationSpec(example);
        return citationRepository.findAll(citationSpec);
    }

}
