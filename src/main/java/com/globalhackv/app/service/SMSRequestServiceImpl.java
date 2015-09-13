package com.globalhackv.app.service;

import java.util.List;

import com.globalhackv.app.domain.Citation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalhackv.app.domain.SMSResponse;
import com.globalhackv.app.repository.SMSRequestRepository;

@Service(value="smsRequestService")
public class SMSRequestServiceImpl implements SMSRequestService {

    @Autowired
    SMSRequestRepository smsRequestRepository;

    @Autowired
    SearchService searchService;

	@Override
	public List<Citation> searchForCitations(String phoneNumber,
			List<String> searchStrings) {
		
		//TODO Query for other search strings that were previously persisted for the given phone number
		
		//TODO combine any search strings found with the current ones being provided.
		
		//TODO use the search service to query for citations.
		
		//TODO handle one citation returned.
		
		//TODO handle multiple citations returned.
		
		//TODO handle zero citations returned.

        Citation citation = new Citation();
        citation.setFirstName(searchStrings.get(0));
        citation.setLastName(searchStrings.get(1));

        List<Citation> citations = searchService.findByCitation(citation);



        return citations;
	}

    

}
