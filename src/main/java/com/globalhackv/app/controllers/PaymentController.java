package  com.globalhackv.app.controllers;

import com.globalhackv.app.domain.PaymentRequest;
import com.globalhackv.app.domain.PaymentResponse;
import com.globalhackv.app.service.PaymentService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@RequestMapping(method = RequestMethod.POST)
	public PaymentResponse pay(@RequestBody PaymentRequest request) {
		PaymentResponse response = new PaymentResponse();
		response = PaymentService.pay(request);
		return response;
	}
}