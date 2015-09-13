package com.globalhackv.app.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.globalhackv.app.domain.Citation;
import com.globalhackv.app.domain.SMSResponse;
import com.globalhackv.app.domain.Violation;
import com.globalhackv.app.repository.ViolationRepository;
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

	@Autowired
	ViolationRepository violationRepository;

	@RequestMapping("/sms")
	public void search(HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

		String fromNumber = request.getParameter("From");
		String body = request.getParameter("Body");
		System.out.println("body is " + body);
		System.out.println("fromNumber is " + fromNumber);

		String returnMessage = "";
		if ("?".equalsIgnoreCase(body)) {
			returnMessage = "Welcome to the Ticket Retrieval Hotline. Please enter either your driver's license number or your first name, last name, and date of birth (mm/dd/yyyy); each separated by a space";
		} else {
			String[] theStrings = body.split(" ");
			List<String> searchStrings = new ArrayList<String>();
			searchStrings.add(theStrings[0]);
			searchStrings.add(theStrings[1]);
			searchStrings.add(theStrings[2]);
			// TODO Get list of search strings from the request.
			// List<String> searchStrings = getSearchStrings(request);

			List<Citation> citations = smsRequestService.searchForCitations(
					fromNumber, searchStrings);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
			if (!citations.isEmpty()) {
				StringBuilder sb = new StringBuilder();
				try {

					for (Citation citation : citations) {
						Date date = sdf.parse(citation.getCitationDate());
						String stringDate = newFormat.format(date);
						Date date2 = sdf.parse(citation.getCourtDate());
						String stringCourtDate = newFormat.format(date2);
						String violation = "";
						String warrant = "no";
						List<Violation> violations = violationRepository.findByCitationNumber(citation.getCitationNumber());
						if(!violations.isEmpty()) {
							violation = violations.get(0).getViolationDescription();
							if(violations.get(0).isWarrantStatus()) {
								warrant = "yes";
							}
						}
						
						sb.append(" Ticket #: " + citation.getCitationNumber()
								+ " Ticket Date: " + stringDate + " Court Address: "
								+ citation.getCourtAddress() + " Court Date:"
								+ stringCourtDate + " Violation: " + violation + " Warrant? " + warrant);
					}
					returnMessage = sb.toString();
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				returnMessage = "No tickets found";
			}

		}
		
        System.out.println("sms response is " + returnMessage);
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
			e.printStackTrace();
		}
	}
}
