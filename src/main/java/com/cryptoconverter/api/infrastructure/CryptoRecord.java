package com.cryptoconverter.api.infrastructure;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * class used to contain information  of cryptocurrencies in the DB, every currency with a conversion in  USD and EUR 
 * @author User
 *
 */
@Entity
@Table(schema="PUBLIC",name="currency")
public class CryptoRecord {
	private String currencyName;
	private Double  USD;
	private Double  EUR;
	
	public CryptoRecord() {super();}
	public CryptoRecord( Double USD, Double  EUR) {
		super();
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
		
	@Override
	public String toString() {
		return "CryptoRecord [currencyName=" + currencyName +  ", USD=" + USD + ", EUR=" + EUR + "]";
	}
	public String toStringPartial() {
		return "CryptoRecord [USD= " + USD + ", EUR=" + EUR + "]";
	}
}
