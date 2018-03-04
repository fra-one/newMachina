package com.cryptoconverter.api;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * class used to contain information from www.cryptocompare.com in the DB
 * @author User
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(schema="PUBLIC",name="currency")
public class CryptoCompareRecord {
	
	private String currencyName;
	@JsonProperty("BTC")
	private Double BTC;
	@JsonProperty("USD")
	private Double  USD;
	@JsonProperty("EUR")
	private Double  EUR;
	
	public CryptoCompareRecord() {super();}
	
	public CryptoCompareRecord(Double BTC, Double USD, Double  EUR) {
		super();
		this.BTC = BTC;
		this.USD = USD;
		this.EUR = EUR;
	}
	
	@Id
	@Column(name="currency_name")
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	@Column(name="value")
	public Double  getBTC() {
		return BTC;
	}
	public void setBTC(Double BTC) {
		this.BTC = BTC;
	}
	@Column(name="in_usd")
	public Double  getUSD() {
		return USD;
	}
	public void setUSD(Double  USD) {
		this.USD = USD;
	}
	@Column(name="in_eur")
	public Double  getEUR() {
		return EUR;
	}
	public void setEUR(Double  EUR) {
		this.EUR = EUR;
	}
	
	
	public String toStringPartial() {
		return "CryptoCompareRecord [BTC=" + BTC + ", USD=" + USD + ", EUR=" + EUR + "]";
	}

	@Override
	public String toString() {
		return "CryptoCompareRecord [currencyName=" + currencyName + ", BTC=" + BTC + ", USD=" + USD + ", EUR=" + EUR + "]";
	}
	
	
	
}