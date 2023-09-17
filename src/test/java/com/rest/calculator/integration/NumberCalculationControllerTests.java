package com.rest.calculator.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.calculator.controller.datamodel.CalculationResult;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class NumberCalculationControllerTests {

	private static final String EXPECTED_MESSAGE = "360360 is the smallest number that can be divided by 1 to 15";
 
	
	@Autowired
	MockMvc mockMvc; 
	
	@Autowired
	ObjectMapper mapper;
		 
	
	@Test
	void test_whenIncorrectLimitSet_thensuccess() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/setupperlimit/15"));
		 
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/getdivisiblenumber"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn();		
	 
		CalculationResult calculation= mapper.readValue(result.getResponse().getContentAsString(),new TypeReference<>(){});
		 
		Assertions.assertEquals(EXPECTED_MESSAGE, calculation.getMessage());
	}
	
	@Test
	void test_whenAcceptHeaderIsJson_thenResponseJson() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/setupperlimit/15"));
		
		 HttpHeaders httpHeaders = new HttpHeaders();
		 httpHeaders.add(HttpHeaders.ACCEPT, "application/json");
		 
		 mockMvc.perform(MockMvcRequestBuilders.get("/getdivisiblenumber").headers(httpHeaders))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(results -> Assertions.assertTrue(results.getResponse().getContentType().contains("application/json")));	 
	}
	
	@Test
	void test_whenAcceptHeaderIsXml_thenResponseXml() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/setupperlimit/15"));
		
		 HttpHeaders httpHeaders = new HttpHeaders();
		 httpHeaders.add(HttpHeaders.ACCEPT, "application/xml");
		 
		 mockMvc.perform(MockMvcRequestBuilders.get("/getdivisiblenumber").headers(httpHeaders))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(results -> Assertions.assertTrue(results.getResponse().getContentType().contains("application/xml"))); 
		 
	}
}
