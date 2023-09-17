package com.rest.calculator.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.rest.calculator.exception.InvalitlimitException;
import com.rest.calculator.services.CalculatorService;
import com.rest.calculator.services.Constants;

@SpringBootTest
@AutoConfigureMockMvc
  class RangeLimitControllerTests {	 
 

	@Autowired
	CalculatorService service;
	
	@Autowired
	MockMvc mockMvc;
	  
	@Test
	void test_whenCorrectRangeLimitProvided_noerror() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/setupperlimit/25"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void test_whenNegativeRangeLimitProvided_thenexception() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/setupperlimit/-1"))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof InvalitlimitException))
		.andExpect(result ->Assertions.assertEquals(String.format(Constants.PLEASE_ENTER_VALID_INTEGER, Constants.RANGE_LIMIT),result.getResolvedException().getMessage()));;
	}
	
	@Test
	void test_whenAlphanumericRangeLimitProvided_thenexception() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/setupperlimit/random33"))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());		 
	}
	
	@Test
	void test_whenBeyondRangeLimitProvided_thenexception() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/setupperlimit/1003"))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());		 
	}
	
	
}
