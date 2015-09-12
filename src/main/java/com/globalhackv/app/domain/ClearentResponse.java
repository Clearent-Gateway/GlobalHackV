package com.globalhackv.app.domain;


public class ClearentResponse {

    private String result;
    private String transactionId;
    
	public ClearentResponse() {
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
}