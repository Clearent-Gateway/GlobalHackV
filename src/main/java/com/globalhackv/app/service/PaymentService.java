package com.globalhackv.app.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import com.globalhackv.app.domain.PaymentRequest;
import com.globalhackv.app.domain.PaymentResponse;
import com.globalhackv.app.domain.Transaction;
import com.globalhackv.app.domain.Violation;

//import com.globalhackv.app.domain.String;

/**
 * Created by jwillard on 9/11/2015.
 */

public class PaymentService {

//	public PaymentService(String cardNum, String expDate, int amountToBePaid, Violation[] violations, String userID){
//		pay(cardNum, expDate, amountToBePaid);
//		update(violations, userID);
//	}
//
//	private void update(Violation[] violations, String userID) {
//		
//		
//	}
//
//	public void pay(String cardNum, String expDate, int amountToBePaid){
//		PaymentRequest request = new PaymentRequest();
//		request.setAmountToPay(amountToBePaid);
//		request.setCardNumber(cardNum);
//		request.setExpDate(expDate);
//		JSONObject JSONrequest = request.getJSONString();
//		
//		String response = sendRequest(JSONrequest);
//	}
//	
//	public static String sendRequest(JSONObject obj){
//		String response = "";
//		try {
//			response = Transaction.requestTransaction(obj.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return response;
//	}

	public static PaymentResponse pay(PaymentRequest request) {
		PaymentResponse response = new PaymentResponse();
		response = submitPayments(request);
		return response;
	}

	private static PaymentResponse submitPayments(PaymentRequest request) {
      final String clearentRequest = "{\"type\":\"SALE\",\"card\":\"" + request.getCardNumber()
      + "\",\"exp-date\":\"" + request.getExpDate() + "\",\"amount\":\"" + request.getAmountToPay()
      + "\"}";
		PaymentResponse response = new PaymentResponse();
		String responseString = "";
		try {
			responseString = Transaction.requestTransaction(clearentRequest);
			response.setTest(responseString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return response;
	}
	
	public Boolean checkSuccess(){
		//parse response and check success
		
		
		return true;
	}
	
	public static List<Violation> sortViolationsByDate(List<Violation> violations){
		List<Violation> sortedViolations = new ArrayList<Violation>();
		while (violations.size()>0){
			int oldestId = getOldest(violations);
				sortedViolations.add(violations.get(oldestId));
				violations.remove(oldestId);
		}
		return sortedViolations;
	}
	
	private static int getOldest (List<Violation> violations) {
		int oldestViolationIndex = 0;
		Date oldestViolationDate = new Date ();
		
		for (int i = 0; i < violations.size(); i++){
			Date violationDate = getViolationDate(violations.get(i));
			if(violationDate.before(oldestViolationDate)){
				oldestViolationIndex = i;
			}
		}
		
		return oldestViolationIndex;
	}

	private static Date getViolationDate(Violation element) {
		String violationDateStr = element.getStatus_Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date violationDate = new Date();
		try {
			violationDate = dateFormat.parse(violationDateStr);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return violationDate;
	}

	public void payByOldestViolation(List<Violation> sortedViolations, String amountToBePaid){
		
	}
	
	
	
	
	
	}