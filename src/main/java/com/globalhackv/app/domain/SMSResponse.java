package com.globalhackv.app.domain;

public class SMSResponse {
 
    private String citationNumber;
    private String message;
    
    public SMSResponse() {
    	
    } 

	public String getCitationNumber() {
		return citationNumber;
	}

	public void setCitationNumber(String citationNumber) {
		this.citationNumber = citationNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}