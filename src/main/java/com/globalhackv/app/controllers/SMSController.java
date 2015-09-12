package com.globalhackv.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SMSController {
	@RequestMapping("/sms")
	public String search(HttpServletRequest request) {
		return "hacked response";
	}
}
