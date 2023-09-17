package com.rest.calculator.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rest.calculator.controller.datamodel.CalculationResult;
import com.rest.calculator.exception.LimitNotSetException;
import com.rest.calculator.services.CalculatorService;

import jakarta.servlet.ServletContext;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTests {

	@InjectMocks
	CalculatorService service;
	
	@Mock
	ServletContext context;
	
	@Test
	void test_whenUpperLimitnotSet_then_Exception() 
	{ 
		Mockito.when(context.getAttribute("upperLimit")).thenReturn(null);
		Assertions.assertThrows( LimitNotSetException.class, ()->service.calculateNumbersAndRange());	
		
	}
	
	@Test
	void test_whenUpperLimitSet_then_CalculatedResult() throws LimitNotSetException 
	{ 
		Mockito.when(context.getAttribute("upperLimit")).thenReturn(15);
		CalculationResult result = service.calculateNumbersAndRange(); 
		Assertions.assertEquals("360360 is the smallest number that can be divided by 1 to 15", result.getMessage());
		
	}
	
}
