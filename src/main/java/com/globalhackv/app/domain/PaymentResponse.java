package com.globalhackv.app.domain;

import java.util.List;


public class PaymentResponse {

    private String code;
    private List<Violation> violations;

	public List<Violation> getViolations() {
		return violations;
	}

	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}