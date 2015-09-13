package com.globalhackv.app.controllers;

import java.util.ArrayList;
import java.util.List; 
  
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.globalhackv.app.domain.SMSResponse;
import com.globalhackv.app.service.SMSRequestService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
 
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Message;

@Controller
public class SMSController {

	@Autowired
	SMSRequestService smsRequestService;

	@RequestMapping("/sms")
	public void search(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("woohoo !");
		
		 // Create a dict of people we know.
        HashMap<String, String> callers = new HashMap<String, String>();
        callers.put("+14158675309", "Curious George");
        callers.put("+14158675310", "Boots");
        callers.put("+14158675311", "Virgil");
 
        String fromNumber = request.getParameter("From");
        
        //request.getParameterNames()
        
        String knownCaller = callers.get(fromNumber);
        String message;
        if (knownCaller == null) {
            // Use a generic message
            message = "Monkey, thanks for the message!";
        } else {
            // Use the caller's name
            message = knownCaller + ", thanks for the message!";
        }
 
        // Create a TwiML response and add our friendly message.
        TwiMLResponse twiml = new TwiMLResponse();
        Message sms = new Message(message);
        try {
            twiml.append(sms);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
 
        response.setContentType("application/xml");
        try {
			response.getWriter().print(twiml.toXML());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //return response;
		
		// TODO Get phone number from request
		//String phoneNumber = getPhoneNumber(request);
		// TODO Get list of search strings from the request.
		//List<String> searchStrings = getSearchStrings(request);

		// TODO Call new service
		//SMSResponse smsResponse = smsRequestService.searchForCitations(phoneNumber, searchStrings);

		// TODO Does the response have to match a Twilio format ?
//		if (smsResponse.getCitationNumber() != null) {
//			return "Parking ticket number " + smsResponse.getCitationNumber();
//		} else {
//			return smsResponse.getMessage();
//		}
		
		//return "fail";

	}

	// TODO can we identify what each string represents ?
	private List<String> getSearchStrings(HttpServletRequest request) {
		List<String> searchStrings = new ArrayList<String>();
		String firstName = "Peter";
		String lastName = "Sherman";
		searchStrings.add(firstName);
		searchStrings.add(lastName);
		return searchStrings;
	}

	private String getPhoneNumber(HttpServletRequest request) {
		return "3148675309";
	}
}
