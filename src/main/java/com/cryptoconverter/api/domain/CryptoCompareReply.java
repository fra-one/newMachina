package com.cryptoconverter.api.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * class used to contain information (reply) from www.cryptocompare.com
 * @author User
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoCompareReply {
	private String currencyName;
	@JsonProperty("USD")
	private Double  USD;
	@JsonProperty("EUR")
	private Double  EUR;
	
	public CryptoCompareReply() {super();}
	public CryptoCompareReply( Double USD, Double  EUR) { 
		super();
		this.USD = USD;
		this.EUR = EUR;
	}
	
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
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
	
	public String toStringPartial() {
		return "CryptoCompareReply [USD=" + USD + ", EUR=" + EUR + "]";
	}

	@Override
	public String toString() {
		return "CryptoCompareReply [currencyName=" + currencyName + ", USD=" + USD + ", EUR=" + EUR + "]";
	}
}