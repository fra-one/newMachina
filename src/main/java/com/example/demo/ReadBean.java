package com.example.demo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * class used to contain information from RESTapi from  www.cryptocompare.com
 * Leave meta name for learning over legibility
 * @author User
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReadBean {
	@JsonProperty("BTC")
	private Double BTC;
	@JsonProperty("USD")
	private Double  USD;
	@JsonProperty("EUR")
	private Double  EUR;
	
	public ReadBean() {super();}
	
	public ReadBean(Double BTC, Double USD, Double  EUR) {
		super();
		this.BTC = BTC;
		this.USD = USD;
		this.EUR = EUR;
	}
	
	public Double  getBTC() {
		return BTC;
	}
	public void setBTC(Double BTC) {
		this.BTC = BTC;
	}
	public Double  getUSD() {
		return USD;
	}
	public void setUSD(Double  USD) {
		this.USD = USD;
	}
	public Double  getEUR() {
		return EUR;
	}
	public void setEUR(Double  EUR) {
		this.EUR = EUR;
	}
	@Override
	public String toString() {
		return "ReadBean [BTC=" + BTC + ", USD=" + USD + ", EUR=" + EUR + "]";
	}
	
	
	
}
