package com.cryptoconverter.api.domain;
import com.cryptoconverter.api.infrastructure.CryptoRecord;

/** 
 * class used for increase control over conversation phase between storage and reading of currency value  
 * @author User
 *
 */
public class DataConverter {
	//conversion between the result of a cryptocompare call to our persistence class
	public static CryptoRecord cryptoCompareReply2CryptoRecord(CryptoCompareReply cryptoCompareReply) {
		CryptoRecord cryptoRecord= new CryptoRecord();
		cryptoRecord.setCurrencyName(cryptoCompareReply.getCurrencyName());
		cryptoRecord.setEUR(cryptoCompareReply.getEUR());
		cryptoRecord.setUSD(cryptoCompareReply.getUSD());
		return cryptoRecord;
	}
	//return the value of a conversion between two currencies as a type prepared for this work
	//exp: 35 -> {"amount": 35} 
	public static ConversionResult conversion2ConversionResult(Double conversionValue) {
		return new ConversionResult(conversionValue);
	}
}
