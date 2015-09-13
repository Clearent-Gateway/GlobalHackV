package com.globalhackv.app.domain;

import java.util.List;

public class SMSResponse {
 
    private String citationNumber;
    private String message;
    private List<Citation> citations;

    public List<Citation> getCitations() {
        return citations;
    }

    public void setCitations(List<Citation> citations) {
        this.citations = citations;
    }

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