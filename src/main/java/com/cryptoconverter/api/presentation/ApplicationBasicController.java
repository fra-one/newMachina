package com.cryptoconverter.api.presentation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.cryptoconverter.api.application.CryptoConverterLogic;
import com.cryptoconverter.api.domain.ConversionResult;
import com.cryptoconverter.api.infrastructure.CryptoRecord;
/**
 * Application Controller with the basic functionality of the app (convert currencies,help, test)  
 * [Basic = word used for define the core behavior of the project specifications]
 * @author User
 *
 */

@RestController  
public class ApplicationBasicController {
	@Autowired
	RestTemplate restTemplate;  //used only for a raw test: testCryptoCompareWebSite  http://localhost:8080/provaidertest
	@Autowired
	private CryptoConverterLogic cryptoLogic;
	
	//---- TASK 3 of the project ----// 
	//conversion between currency replay from user, return with specific Json type 
	@GetMapping("/convert/{startValue}/{startingName}/{finalName}")   //http://localhost:8080/task3/25/BTC/USD
	public  @ResponseBody ConversionResult  doTask3(	@PathVariable(value = "startValue", required  = true) Double startValue,
											@PathVariable(value = "startingName", required  = true) String startingName,
											@PathVariable(value = "finalName", required  = true) String finalName) {
		return cryptoLogic.makeConversion(startValue,startingName,finalName);
	}
	
	
	//---- EXTRA CODE use for testing and rapid access ----//
	//message for help or home call
	@RequestMapping(value={"/help","/"})  
	public String getHelp(){  
		String explanation ="";
		explanation+="Messaggio usato come help-home |||";
		explanation+="Task3 digita: http:/localhost:8080/convert/1/BTC/USD per sapere quanto vale 1 BTC in DOLLARI. ";
		explanation+="Puoi decidere di cambiare valore e di cambiare le valute nell'ordine che preferisci. ";
		explanation+="I nomi delle valute accettate sono: BTC USD EUR ETH LTC ETC ";
		//explanation autentication
		explanation+="||| Digita: http:/localhost:8080/logmeout per effettuare il log out";
		explanation+="||| Digita: http:/localhost:8080/user/luca/123/user per aggiornare l'utente luca con password 123 e ruolo user ";
		//test
		explanation+="||| Digita: http:/localhost:8080/provaidertest per leggere il risultato di una chiamata fatta a cryptocompare";
		explanation+="||| Digita: http:/localhost:8080/currencies o http:/localhost:8080/currencies/BTC per ricevere una veloce rappresentazione delle valute/a memorizzate";
		return explanation;  
	}  
	// Get All cryptocurrency
	@GetMapping(value="/currencies") //localhost:8080/currencies
		public List<CryptoRecord> getAllCurrencies() {
		    return cryptoLogic.getCryptoRecordsFromRepo();
		}
	// Get one specific cryptocurrency
	@GetMapping(value="/currencies/{currencyName}") //localhost:8080/currencies
		public CryptoRecord getOneCurrency(@PathVariable(value = "currencyName", required  = true) String currencyName) {
		    return cryptoLogic.getCryptoRecordFromRepo(currencyName);
		}
	//Print a message from a consuming service  
	@GetMapping(value="/provaidertest")  //localhost:8080/provaidertest
	public String testCryptoCompareWebSite() {
		RestTemplate restTemplate = new RestTemplate();
		CryptoRecord readBean = restTemplate.getForObject("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=BTC,USD,EUR", CryptoRecord.class);
		return readBean.toString();
	}
}
