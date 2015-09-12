package com.globalhackv.app.controllers;

import java.util.ArrayList;
import java.util.List; 
  
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.globalhackv.app.domain.SMSResponse;
import com.globalhackv.app.service.SMSRequestService;

@Controller
public class SMSController {

	@Autowired
	SMSRequestService smsRequestService;

	@RequestMapping("/sms")
	public String search(HttpServletRequest request) {

		// TODO Get phone number from request
		String phoneNumber = getPhoneNumber(request);
		// TODO Get list of search strings from the request.
		List<String> searchStrings = getSearchStrings(request);

		// TODO Call new service
		SMSResponse smsResponse = smsRequestService.searchForCitations(phoneNumber, searchStrings);

		// TODO Does the response have to match a Twilio format ?
		if (smsResponse.getCitationNumber() != null) {
			return "Parking ticket number " + smsResponse.getCitationNumber();
		} else {
			return smsResponse.getMessage();
		}

	}

	// TODO can we identify what each string represents ?
	private List<String> getSearchStrings(HttpServletRequest request) {
		List<String> searchStrings = new ArrayList<String>();
		String firstName = "Woody";
		String lastName = "Harrelson";
		searchStrings.add(firstName);
		searchStrings.add(lastName);
		return searchStrings;
	}

	private String getPhoneNumber(HttpServletRequest request) {
		return "3148675309";
	}
}
