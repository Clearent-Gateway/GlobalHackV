package com.globalhackv.app.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;
import java.lang.System;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;

import com.globalhackv.app.domain.ClearentResponse;
import com.globalhackv.app.domain.PaymentRequest;
import com.globalhackv.app.domain.PaymentResponse;
import com.globalhackv.app.domain.SubmitTransaction;
import com.globalhackv.app.domain.Transaction;
import com.globalhackv.app.domain.Violation;
import com.google.gson.Gson;

//import com.globalhackv.app.domain.String;

/**
 * Created by jwillard on 9/11/2015.
 */

public class PaymentService {

	private int transactionID;

	public static PaymentResponse pay(PaymentRequest request) {
		PaymentResponse response = new PaymentResponse();
		response = submitPayments(request);
		return response;
	}

	public static PaymentResponse submitPayments(PaymentRequest request) {
		final String clearentRequest = "{\"type\":\"SALE\",\"card\":\"" + request.getCardNumber()
		+ "\",\"exp-date\":\"" + request.getExpDate() + "\",\"amount\":\"" + request.getAmountToPay()
		+ "\"}";
		PaymentResponse response = new PaymentResponse();
		String responseString = "";
		try {
			responseString = SubmitTransaction.requestTransaction(clearentRequest);
			ClearentResponse clearentResponse = createClearentResponse(responseString, request);
			if(checkSuccess(clearentResponse)){	
				List<Violation> violations = request.getViolations();
				violations = sortViolationsByDate(violations);
				//violations are now sorted by date
				for (Violation e : violations){
					System.out.println(" Violation # " + e.getViolationNumber() +" amount: " + e.getFineAmount());
				}
				violations = payByOldestViolation(violations, request.getAmountToPay()); //newly updated violations with new amount owed and status
				updateDatabase(violations);
				//	response = createPaymentResponse(clearentResponse, clearet);
			}else{
				//throw exception
			}
			response.setTest(responseString);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return response;
	}


	//	private static PaymentResponse createPaymentResponse(ClearentResponse clearentResponse) {
	//		// TODO Auto-generated method stub
	//		return null;
	//	}

	private static void updateDatabase(List<Violation> violations) {
		for (Violation e : violations){
			System.out.println(" Violation # " + e.getViolationNumber() +" amount: " + e.getFineAmount());
		}
	}

	private static ClearentResponse createClearentResponse(String responseString, PaymentRequest request) {
		Gson gson = new Gson();
		ClearentResponse clearentResponse = new ClearentResponse();	
		clearentResponse=    gson.fromJson(responseString, ClearentResponse.class);
		System.out.println("transaction id " + clearentResponse.getPayload().getTransaction().getId());

		return clearentResponse;
		//update database with translation code in clearentResponse object
		//update violations in data base
	}

	public static Boolean checkSuccess(ClearentResponse response){
		//parse response and check success

		if (response.getCode().equals("200")){ //should be code - tests for success
			return true;
		}
		else{
			//figure out other error codes or throw exception
			System.out.println("Transaction failed!");
			return false;
		}
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
		String violationDateStr = element.getStatusDate();
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

	public static List<Violation> payByOldestViolation(List<Violation> sortedViolations, String amountToBePaid){ //UPDATE DATABASE STUFF SHOULD GO HERE
		BigDecimal amountLeft = new BigDecimal(Double.parseDouble(amountToBePaid));
		if(amountLeft.compareTo(BigDecimal.ZERO) == 1){ //amount left is greater than zero
			for(Violation v : sortedViolations){
				if(amountLeft.compareTo(BigDecimal.ZERO) == 0){
					break;
				}
				else if(amountLeft.compareTo(v.getFineAmount()) == 1){ // amount left is greater than fine amount
					amountLeft = amountLeft.subtract(v.getFineAmount());
					v.setFineAmount(BigDecimal.ZERO);
					v.setStatus("CLOSED");
				}
				else if(amountLeft.compareTo(v.getFineAmount()) == -1){ // amount left is less than fine amount
					v.setFineAmount(v.getFineAmount().subtract(amountLeft));
					amountLeft = BigDecimal.ZERO;
					v.setStatus("CONT FOR PAYMENT"); //only partially payed
				}
				else{ //fine amount equals amount left
					amountLeft = BigDecimal.ZERO;
					v.setFineAmount(BigDecimal.ZERO);
					v.setStatus("CLOSED");
				}
			}
		}
		return sortedViolations;
	}





}
