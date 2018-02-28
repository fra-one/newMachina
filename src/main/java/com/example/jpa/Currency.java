package com.example.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="PUBLIC",name="currency")
public class Currency {
	private long realId;
	private String currencyName;
	private double inEUR;
	private double inUSD;
	
	public Currency() {
		super();
	}
	public Currency( String currencyName, double inEUR, double inUSD) {
		super();
		this.currencyName=currencyName;
		this.inEUR=inEUR;
		this.inUSD=inUSD;
	}
	
	@Id
	@Column(name="currency_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getRealId() {
		return realId;
	}
	public void setRealId(long realId) {
		this.realId = realId;
	}
	
	@Column(name="currency_name")
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	
	@Column(name="in_eur")
	public double getInEUR() {
		return inEUR;
	}
	public void setInEUR(double inEUR) {
		this.inEUR = inEUR;
	}
	
	@Column(name="in_usd")
	public double getInUSD() {
		return inUSD;
	}
	public void setInUSD(double inUSD) {
		this.inUSD = inUSD;
	}

	@Override
	public String toString() {
		return "Currency [realId=" + realId + ", currencyName=" + currencyName + ", inEUR=" + inEUR + ", inUSD=" + inUSD
				+ "]";
	}
	
		
	
}
