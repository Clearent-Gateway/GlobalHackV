package com.globalhackv.app.domain;

import org.json.simple.JSONObject;

public class PaymentRequest {

    private String cardNumber;
    private String expDate;
    private double amountToPay;
    private long violationNumber;

    public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public double getAmountToPay() {
		return amountToPay;
	}

	public void setAmountToPay(double amountToPay) {
		this.amountToPay = amountToPay;
	}

	public long getViolationNumber() {
		return violationNumber;
	}

	public void setViolationNumber(long violationNumber) {
		this.violationNumber = violationNumber;
	}

	public PaymentRequest() {
        
    }

	public JSONObject getJSONString() {
	  JSONObject obj = new JSONObject();
	  obj.put("cardNumber",cardNumber);
	  obj.put("expDate", expDate);
	  obj.put("amountPaying",amountToPay);
	  return obj;
	}
	
}