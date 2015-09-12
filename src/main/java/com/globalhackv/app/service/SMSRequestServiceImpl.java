package com.globalhackv.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.globalhackv.app.domain.SMSResponse;
import com.globalhackv.app.repository.SMSRequestRepository;

@Service(value="smsRequestService")
public class SMSRequestServiceImpl implements SMSRequestService {

    @Autowired
    SMSRequestRepository smsRequestRepository;

	@Override
	public SMSResponse searchForCitations(String phoneNumber,
			List<String> searchStrings) {
		
		//TODO Query for other search strings that were previously persisted for the given phone number
		
		//TODO combine any search strings found with the current ones being provided.
		
		//TODO use the search service to query for citations.
		
		//TODO handle one citation returned.
		
		//TODO handle multiple citations returned.
		
		//TODO handle zero citations returned.
		
		SMSResponse smsResponse = new SMSResponse();
		smsResponse.setCitationNumber("citationNumber");
		smsResponse.setMessage("Found your parking ticket.");
		return smsResponse;
	}

    

}
