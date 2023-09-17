package com.rest.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.calculator.exception.InvalitlimitException;
import com.rest.calculator.services.CalculatorService;
 
@RestController
public class RangeLimitController {
	
	@Autowired
	CalculatorService service;   	
	
	@PostMapping("/setupperlimit/{upperlimit}")
	public void setUpperLimit(@PathVariable("upperlimit") Integer upperLimit)  throws InvalitlimitException
	{ 	 
		  service.configureLimit(upperLimit);
	 		 
	} 
	
	
	


}
