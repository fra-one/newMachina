package com.cryptoconverter.api.domain;
/**
 * class used to contain information for conversion between currencies 
 * (user request of conversation over our service return this type of return)
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
