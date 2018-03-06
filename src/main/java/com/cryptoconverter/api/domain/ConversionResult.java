package com.cryptoconverter.api.domain;

/**
 * web return class used to contain information for conversion (user request) between currencies
 * @author User
 *
 */
public class ConversionResult {
	private final double amount;
	
	public ConversionResult(double amount) {
		this.amount=amount;
	}
	
	public double getAmount() {
		return amount;
	}
}
