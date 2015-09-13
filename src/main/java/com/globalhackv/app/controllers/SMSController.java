package com.globalhackv.app.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List; 
  



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.globalhackv.app.domain.Citation;
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
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        String fromNumber = request.getParameter("From");
        String body = request.getParameter("Body");
        System.out.println("body is " + body );
        System.out.println("fromNumber is " + fromNumber );
        
        String returnMessage = "";
        if("?".equalsIgnoreCase(body)) {
        	returnMessage = "Welcome to the Ticket Retrieval Hotline. Please enter your first name, last name, and date of birth (mm/dd/yyyy), each separated by a space";
        } else {
        	String[] theStrings = body.split(" ");
        	List<String> searchStrings = new ArrayList<String>();
        	searchStrings.add(theStrings[0]);
        	searchStrings.add(theStrings[1]);
        	searchStrings.add(theStrings[2]);
    		// TODO Get list of search strings from the request.
    		//List<String> searchStrings = getSearchStrings(request);

    		List<Citation> citations = smsRequestService.searchForCitations(fromNumber, searchStrings);
    		
    		if(!citations.isEmpty()) {
    			StringBuilder sb = new StringBuilder();
    			for(Citation citation: citations) {
    			    sb.append(" Ticket #: " + citation.getCitationNumber() + " Date: " + citation.getCitationDate().toString());
    			}
    			returnMessage = sb.toString();
    		} else {
    			returnMessage = "No tickets found";    			
    		}
    		
        }
        
        updateResponse(response, returnMessage);
        	
	}
	
	private void updateResponse(HttpServletResponse response, String message) {

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
