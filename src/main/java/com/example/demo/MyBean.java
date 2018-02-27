package com.example.demo;

/**
 * class used to contain information for web communication over currency 
 * Leave meta name for learning over legibility
 * @author User
 *
 */
public class MyBean {
	private final double amount;
	
	public MyBean(double amount) {
		this.amount=amount;
	}
	
	public double getAmount() {
		return amount;
	}
}
