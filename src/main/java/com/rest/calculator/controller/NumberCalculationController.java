package com.rest.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.calculator.controller.datamodel.CalculationResult;
import com.rest.calculator.exception.LimitNotSetException;
import com.rest.calculator.services.CalculatorService;

@RestController
public class NumberCalculationController {
	
	@Autowired
	CalculatorService service;   
	
	@GetMapping("/getdivisiblenumber")
	public  CalculationResult getResult() throws LimitNotSetException{  	 	 
		return service.calculateNumbersAndRange();		
	}

}
