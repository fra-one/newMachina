package com.cryptoconverter.api.domain;

import com.cryptoconverter.api.infrastructure.CryptoRecord;

/** 
 * class used for increase control over conversation phase  
 * @author User
 *
 */
public class DataConverter {
	
	public static CryptoRecord cryptoCompareReply2CryptoRecord(CryptoCompareReply cryptoCompareReply) {
		CryptoRecord cryptoRecord= new CryptoRecord();
		cryptoRecord.setCurrencyName(cryptoCompareReply.getCurrencyName());
		cryptoRecord.setEUR(cryptoCompareReply.getEUR());
		cryptoRecord.setUSD(cryptoCompareReply.getUSD());
		return cryptoRecord;
	}
	
	public static ConversionResult conversion2ConversionResult(Double conversionValue) {
		return new ConversionResult(conversionValue);
	}

}
