package com.globalhackv.app.domain;

import java.util.List;


public class PaymentResponse {

    private String test;
    private List<Violation> violations;

	public List<Violation> getViolations() {
		return violations;
	}

	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
}