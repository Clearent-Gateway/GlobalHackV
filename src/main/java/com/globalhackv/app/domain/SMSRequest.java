package com.globalhackv.app.domain;

public class SMSRequest {
  
    private String phoneNumber;
    private String searchString;
    
    public SMSRequest() {
    	 
    }

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
    
    

}