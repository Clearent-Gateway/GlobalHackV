package com.globalhackv.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.globalhackv.app.domain.Violation;
import com.globalhackv.app.service.PaymentService;

public class sortViolationDateTest {

	@Test
	public void sortViolationList() {
		Violation oldViolation = new Violation();
		oldViolation.setStatus_Date("01/15/2000");
		oldViolation.setViolation_Number("1111111");
		
		Violation newViolation = new Violation();
		newViolation.setStatus_Date("01/15/2015");
		newViolation.setViolation_Number("222222");
		
		List<Violation> violations =  new ArrayList<Violation>();
		violations.add(newViolation);
		violations.add(oldViolation);
		
		List<Violation> sortedViolations = PaymentService.sortViolationsByDate(violations);
		
		System.out.println("first violation in List " + sortedViolations.get(0).getStatus_Date());
		System.out.println("second violation in List " + sortedViolations.get(1).getStatus_Date());
		
		
		assertTrue(sortedViolations.get(0).getStatus_Date().equals("01/15/2000"));
		assertTrue(sortedViolations.get(1).getStatus_Date().equals("01/15/2015"));

	}

}
