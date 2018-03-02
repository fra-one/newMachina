package com.cryptoconverter.api;

/**
 * class used to contain information for web communication over currency 
 * Leave meta name for learning over legibility
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
