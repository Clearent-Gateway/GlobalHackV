package com.globalhackv.app.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
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
		BigDecimal amountLeft = new BigDecimal(Double.parseDouble(amountToBePaid));
		if(amountLeft.compareTo(BigDecimal.ZERO) == 1){ //amount left is greater than zero
			for(Violation v : sortedViolations){
				if(amountLeft.compareTo(BigDecimal.ZERO) == 0){
					break;
				}
				else if(amountLeft.compareTo(v.getFine_Amount()) == 1){ // amount left is greater than fine amount
					amountLeft = amountLeft.subtract(v.getFine_Amount());
					v.setFine_Amount(BigDecimal.ZERO);
					v.setStatus("CLOSED");
				}
				else if(amountLeft.compareTo(v.getFine_Amount()) == -1){ // amount left is less than fine amount
					v.setFine_Amount(v.getFine_Amount().subtract(amountLeft));
					amountLeft = BigDecimal.ZERO;
					v.setStatus("CONT FOR PAYMENT"); //only partially payed
				}
				else{ //fine amount equals amount left
					amountLeft = BigDecimal.ZERO;
					v.setFine_Amount(BigDecimal.ZERO);
					v.setStatus("CLOSED");
				}
			}
		}
	}
	
	
	
	
	
	
	
	}
