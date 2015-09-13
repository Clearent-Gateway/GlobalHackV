package  com.globalhackv.app.controllers;

import com.globalhackv.app.domain.PaymentRequest;
import com.globalhackv.app.domain.PaymentResponse;
import com.globalhackv.app.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	
	@Autowired
	PaymentService paymentService;
	@RequestMapping(method = RequestMethod.POST)
	public PaymentResponse pay(@RequestBody PaymentRequest request) {
		PaymentResponse response = new PaymentResponse();
		response = paymentService.pay(request);
		return response;
	}
}