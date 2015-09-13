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
		
        Citation citation = new Citation();
        if(searchStrings.size() == 1) {
        	citation.setDriversLicense(searchStrings.get(0));
        } else {
            citation.setFirstName(searchStrings.get(0));
            citation.setLastName(searchStrings.get(1));
            //citation.setCitationDate(searchStrings.get(2));
        }

        List<Citation> citations = searchService.findByCitation(citation);



        return citations;
	}

    

}
