package com.globalhackv.app.domain;

import com.google.gson.Gson;

public class Transaction {

    private String code;
    private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}