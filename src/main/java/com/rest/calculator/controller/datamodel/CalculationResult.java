package com.rest.calculator.controller.datamodel;

public class CalculationResult {	
	
    String message;
	String calculationtime;		

	public CalculationResult(String message,String calculationTime2) {
		super();
		this.message = message;		 
		this.calculationtime =calculationTime2;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCalculationtime() {
		return calculationtime;
	}

	public void setCalculationtime(String calculationtime) {
		this.calculationtime = calculationtime;
	}

	@Override
	public String toString() {
		return "CalculationResult [message=" + message + ", calculationtime=" + calculationtime + "]";
	}
	 

}
