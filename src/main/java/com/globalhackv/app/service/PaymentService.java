package com.globalhackv.app.service;

import java.io.IOException;
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
	
	public void sortViolationsByDate(List<Violation> violations){
		
		
	}
	
	public void payByOldestViolation(List<Violation> sortedViolations, String amountToBePaid){
		
	}
	
	
	
	
	
	
	
	}
