package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FirstController {

	@RequestMapping(method=RequestMethod.GET , path = "/test")
	public String testController() {
		
		return "first";
	}
	
	@RequestMapping(method=RequestMethod.GET , path = "/login")
	public String loginController() {
		
		return "loginform";
	}
	
	@RequestMapping(method=RequestMethod.GET , path = "/register")
	public String registerController() {
		
		return "registerview";
	}
	
}
