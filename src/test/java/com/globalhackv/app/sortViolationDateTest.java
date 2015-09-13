package com.globalhackv.app;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.junit.Test;

import com.globalhackv.app.domain.PaymentRequest;
import com.globalhackv.app.domain.PaymentResponse;
import com.globalhackv.app.domain.SubmitTransaction;
import com.globalhackv.app.domain.Violation;
import com.globalhackv.app.service.PaymentService;

public class sortViolationDateTest {

    @Ignore
	@Test
	public void sortViolationList() {
		Violation oldViolation = new Violation();
		oldViolation.setStatusDate("01/15/2000");
		oldViolation.setViolationNumber("1111111");
		
		Violation newViolation = new Violation();
		newViolation.setStatusDate("01/15/2015");
		newViolation.setViolationNumber("222222");
		
		List<Violation> violations =  new ArrayList<Violation>();
		violations.add(newViolation);
		violations.add(oldViolation);
		
		List<Violation> sortedViolations = PaymentService.sortViolationsByDate(violations);
		
		System.out.println("first violation in List " + sortedViolations.get(0).getStatusDate());
		System.out.println("second violation in List " + sortedViolations.get(1).getStatusDate());
		
		
		assertTrue(sortedViolations.get(0).getStatusDate().equals("01/15/2000"));
		assertTrue(sortedViolations.get(1).getStatusDate().equals("01/15/2015"));

	}
}
