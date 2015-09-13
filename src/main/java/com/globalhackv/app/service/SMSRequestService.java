package com.globalhackv.app.service;

import java.util.List;

import com.globalhackv.app.domain.Citation;
import com.globalhackv.app.domain.SMSResponse;
 
  
public interface SMSRequestService {

    List<Citation> searchForCitations(String phoneNumber, List<String> searchStrings);

}
