package com.rest.calculator.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.calculator.controller.datamodel.CalculationResult;
import com.rest.calculator.exception.InvalitlimitException;
import com.rest.calculator.exception.LimitNotSetException;

import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalculatorService {


	
	@Autowired
	ServletContext context;

	
	/**
	 * Method validates and sets the UpperLimit for the range.
	 * It uses ServletContext to set the value.
	 * @param upper 
	 * @throws InvalitlimitException
	 */
	public void configureLimit(Integer limit) throws InvalitlimitException {
		if (isValidLimit(limit)) {
			context.setAttribute(Constants.UPPER_LIMIT, limit);
			log.debug("CalculatorService.configureLimit : " + (int)context.getAttribute(Constants.UPPER_LIMIT));
		} else {
			throw new InvalitlimitException( String.format(Constants.PLEASE_ENTER_VALID_INTEGER, Constants.RANGE_LIMIT));
		}		
	}

	private boolean isValidLimit(Integer limit) {
		return (limit != null && limit > 0 && limit < Constants.RANGE_LIMIT);
	}
	  

	/**
	 * Method uses the Upper Limit set on the serveletContext.
	 * smallest divisible number by the range is calculated  by finding 
	 * first the prime factors for each number in range and then
	 * LCM for the given range.
	 *  
	 * @return CalculationResult
	 * @throws LimitNotSetException 
	 */
	public CalculationResult calculateNumbersAndRange() throws  LimitNotSetException{
		long start = System.currentTimeMillis();

	    if(context.getAttribute(Constants.UPPER_LIMIT) == null && !(context.getAttribute(Constants.UPPER_LIMIT) instanceof Integer))
	    	 throw new LimitNotSetException(Constants.UPPER_LIMIT_IS_NOT_SET);
	    	
	   
	    int limit =(int) context.getAttribute(Constants.UPPER_LIMIT);

		Map<Integer, Integer> factorsMap = initializePrimeFactorsMap(limit);
		Integer result = getNumberDivisibleByGivenRange(factorsMap); 
		
		long calculationTime = System.currentTimeMillis() - start;
		log.debug("Took time : " + calculationTime + Constants.MILLISECONDS); 
	    	 
		return new CalculationResult(String.format(Constants.SMALLEST_NUMBER_THAT_CAN_BE_DIVIDED_BY_RANGE, result,
				context.getAttribute(Constants.UPPER_LIMIT)), (calculationTime + Constants.MILLISECONDS));
	}

	
	/**
	 * Initialize the hashMap which stores the prime factors and the maximum power 
	 * @param limit
	 * @return factorsMap
	 */
	private Map<Integer, Integer> initializePrimeFactorsMap(int limit) {
		Map<Integer, Integer> factorsMap = new HashMap<>();
		for (int num = 1; num <= limit; num++) {
			createPrimefactorsMap(factorsMap, num);
		}
		return factorsMap;
	}

	/**
	 * Method calculates prime factors and its power for provided element
	 * prime factors map is updated in case they have higher power than before
	 * @param factorsMap
	 * @param num
	 */
	private void createPrimefactorsMap(Map<Integer, Integer> factorsMap, int num) {
		int number = num;
		number = calculatefactorAndItsPower(factorsMap, number, 2);

		for (int factor = 3; factor <= num; factor += 2) {
			number = calculatefactorAndItsPower(factorsMap, number, factor);
		}
		
		if (number > 2) {
			updateMap(factorsMap, number, 1);
		}
	}

	/**
	 * Based on the prime factors and their value method multiplies each to get the smallest divisible number for given range
	 * @param factorsMap
	 * @return
	 */
	private Integer getNumberDivisibleByGivenRange(Map<Integer, Integer> factorsMap) {
		Integer result = 1;
		for (Map.Entry<Integer, Integer> entry : factorsMap.entrySet()) {
			Integer powervalue = (int) Math.pow(entry.getKey(), entry.getValue());
			result = powervalue * result;
		}
		return result;
	}

	
	private int calculatefactorAndItsPower(Map<Integer, Integer> factorsMap, int number, int factor) {

		int power = 0;
		while (number % factor == 0) {
			power++;
			number = number / factor;
		}
		updateMap(factorsMap, factor, power);
		return number;
	}

	private void updateMap(Map<Integer, Integer> factorsMap, int factor, int powerOfFactor) {
		Integer power = factorsMap.get(factor);
		if (power == null) {
			power = 0;
		}
		if (powerOfFactor > power)
			factorsMap.put(factor, powerOfFactor);
	}

}
