package com.globalhackv.app.service;

import java.util.List;

import com.globalhackv.app.domain.SMSResponse;

 
public interface SMSRequestService {

    SMSResponse searchForCitations(String phoneNumber, List<String> searchStrings);

}
