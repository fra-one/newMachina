package com.cryptoconverter.api.application;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CryptoUpgradeThread {
	@Autowired
	private CryptoConverterLogic cryptoLogic;
	
	//---- TASK 1 & 2 of the project ----//
	//Update DB with a selection of Crypto Currencies to store
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	@Scheduled(fixedDelay = 10000)
	public void cryptoCompareRepositoryUpdate() {
		System.out.println("cryptoCompareRepositoryUpdate at "+ dateFormat.format(new Date()));
		cryptoLogic.updateCryptoRecord("BTC");
		cryptoLogic.updateCryptoRecord("ETH"); //extra crypto currency
		cryptoLogic.updateCryptoRecord("LTC"); //extra crypto currency
		cryptoLogic.updateCryptoRecord("ETC"); //extra crypto currency
	}
}
