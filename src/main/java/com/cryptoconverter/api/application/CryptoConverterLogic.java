package com.cryptoconverter.api.application;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.cryptoconverter.api.domain.ConversionResult;
import com.cryptoconverter.api.domain.CryptoCompareReply;
import com.cryptoconverter.api.domain.DataConverter;
import com.cryptoconverter.api.infrastructure.CryptoRecord;
import com.cryptoconverter.api.infrastructure.CryptoRepository;
/**
 * Application layer for basic Behavior of the application
 * @author User
 *
 */
@Component
public class CryptoConverterLogic {
	@Autowired
	private CryptoRepository cryptoRepository;	
	@Bean 
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// take from  cryptocompare.com a specific currency record and store it in persistence infrastructure   
	public String updateCryptoRecord( String currencyName) { 
		currencyName=currencyName.trim().toUpperCase();
		RestTemplate restTemplate = new RestTemplate();
		CryptoCompareReply cryptoCompareReply = restTemplate.getForObject("https://min-api.cryptocompare.com/data/price?fsym="+currencyName+"&tsyms="+currencyName+",USD,EUR", CryptoCompareReply.class);
		cryptoCompareReply.setCurrencyName(currencyName);
		CryptoRecord cryptoRecord= DataConverter.cryptoCompareReply2CryptoRecord(cryptoCompareReply);
		//DELETE
		CryptoRecord oldCryptoRecord =cryptoRepository.findOne(cryptoRecord.getCurrencyName());
		if(oldCryptoRecord != null) {
			cryptoRepository.delete(oldCryptoRecord);
		}
		//SAVE
		cryptoRepository.save(cryptoRecord);
		return cryptoRecord.toString();
	}
	//conversion over currency with specific Json return type
	public  ConversionResult  makeConversion(Double startValue,String startingName, String finalName) {
		//same fast control over input 
		startingName=startingName.trim().toUpperCase();
		finalName=finalName.trim().toUpperCase();
		//-- conversion start with the same start name and final name
		if(startingName.equals(finalName)) {
			return  DataConverter.conversion2ConversionResult(startValue);
		}
		//-- conversion start from USD or EUR to CRYPTO CURRENCY or USD or EUR
		if(startingName.equals("EUR")) {
			if(finalName.equals("USD")) {
				CryptoRecord cange =getCryptoRecordFromRepo("BTC");
				return   DataConverter.conversion2ConversionResult(startValue/cange.getEUR()*cange.getUSD());  // 1/(1 BTC in €) * startValue * (1 BTC in $)
			}else {
				return   DataConverter.conversion2ConversionResult(1/getCryptoRecordFromRepo(finalName).getEUR()*startValue);
			}
		}
		if(startingName.equals("USD")) {
			if(finalName.equals("EUR")) {
				CryptoRecord cange =getCryptoRecordFromRepo("BTC");
				return   DataConverter.conversion2ConversionResult(startValue/cange.getUSD()*cange.getEUR());  // 1/(1 BTC in $) * startValue * (1 BTC in €)
			}else {
				return   DataConverter.conversion2ConversionResult(1/getCryptoRecordFromRepo(finalName).getEUR()*startValue);
			}
		}
		//-- conversion start form CRYPTO CURRENCY to EUR or USD or CRYPO CURRENCY
		CryptoRecord startingC =getCryptoRecordFromRepo(startingName);
		if(startingC!=null) {
			if(finalName.equals("EUR")) {
				return   DataConverter.conversion2ConversionResult(startingC.getEUR()*startValue);
			}
			if(finalName.equals("USD")) {
				return   DataConverter.conversion2ConversionResult(startingC.getUSD()*startValue);
			}
			CryptoRecord finalC =getCryptoRecordFromRepo(finalName);
			if (finalC!=null) {
				return   DataConverter.conversion2ConversionResult(startingC.getUSD()/finalC.getUSD()*startValue); // startValue* (value in $ start currency) / (value in $ final currency)
			}
		}

		// Return in not evaluate situation
		return   DataConverter.conversion2ConversionResult(0.0);
	}
	//method used access record from repository
	public CryptoRecord getCryptoRecordFromRepo(String currencyName) {   // CRITICISM IN RETURNING NULL!!!!
		CryptoRecord readBean = cryptoRepository.findOne(currencyName);
		if(readBean == null) {
			return null;
		}
		return readBean;
	}
	//method used for obtain all the records form Crypto Repository
	public  List<CryptoRecord> getCryptoRecordsFromRepo() {   // CRITICISM IN RETURNING NULL!!!!
		return cryptoRepository.findAll();
	}
}
