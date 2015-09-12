package com.globalhackv.app.domain;

import java.util.List;

import org.json.simple.JSONObject;

public class PaymentRequest {

    private String cardNumber;
    private String expDate;
    private double amountToPay;
    public List<Violation> getViolations() {
		return violations;
	}

	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}

	private List <Violation> violations;
    private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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