package com.globalhackv.app.service;

import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.globalhackv.app.domain.PaymentRequest;
import com.globalhackv.app.domain.Transaction;
import com.globalhackv.app.domain.Violation;

//import com.globalhackv.app.domain.String;

/**
 * Created by jwillard on 9/11/2015.
 */

public class PaymentProcessor {

	public PaymentProcessor(String cardNum, String expDate, int amountToBePaid, Violation[] violations, String userID){
		pay(cardNum, expDate, amountToBePaid);
		update(violations, userID);
	}

	private void update(Violation[] violations, String userID) {
		
		
	}

	public void pay(String cardNum, String expDate, int amountToBePaid){
		PaymentRequest request = new PaymentRequest();
		request.setAmountToPay(amountToBePaid);
		request.setCardNumber(cardNum);
		request.setExpDate(expDate);
		JSONObject JSONrequest = request.getJSONString();
		
		String response = sendRequest(JSONrequest);
	}
	
	public static String sendRequest(JSONObject obj){
		String response = "";
		try {
			response = Transaction.requestTransaction(obj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
