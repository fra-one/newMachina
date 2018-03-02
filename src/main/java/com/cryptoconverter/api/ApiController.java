package com.cryptoconverter.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController  
public class ApiController {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private CryptoCompareRepository cryptoCompareRepository;

	//---- TASK 1 & 2 of the project ----//
	
	
	//Update DB with a selection of Crypto Currencies to store
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	@Scheduled(fixedDelay = 10000)
	public void cryptoCompareRepositoryUpdate() {
		System.out.println("cryptoCompareRepositoryUpdate at "+ dateFormat.format(new Date()));
		getStore("BTC");
		getStore("ETH"); //extra crypto currency
		getStore("LTC"); //extra crypto currency
		getStore("ETC"); //extra crypto currency
	}
	@Bean 
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	@GetMapping("/store") //Rest call on store default record inside DB
	public String getStore() {
		return getStore("BTC");
	}
	@GetMapping("/store/NAME") //Rest call on specific default record inside DB
	public String getStore(@RequestParam(value = "NAME", defaultValue = "BTC") String currencyName) {
		currencyName=currencyName.trim().toUpperCase();
		RestTemplate restTemplate = new RestTemplate();
		
		CryptoCompareRecord cryptoCompareRecord = restTemplate.getForObject("https://min-api.cryptocompare.com/data/price?fsym="+currencyName+"&tsyms="+currencyName+",USD,EUR", CryptoCompareRecord.class);
		cryptoCompareRecord.setCurrencyName(currencyName);
		cryptoCompareRecord.setBTC(1.0); 
		//DELETE
		CryptoCompareRecord oldCryptoCompareRecord =cryptoCompareRepository.findOne(cryptoCompareRecord.getCurrencyName());
	    if(oldCryptoCompareRecord != null) {
	        cryptoCompareRepository.delete(oldCryptoCompareRecord);
	    }
		//SAVE
	    cryptoCompareRepository.save(cryptoCompareRecord);
		return cryptoCompareRecord.toString();
	}
	
	//---- TASK 3 of the project ----// 
	//conversion over currency with specific Json return type 
	@GetMapping("/convert/{startValue}/{startingName}/{finalName}")   //http://localhost:8080/task3/25/BTC/USD
	public  @ResponseBody ConversionResult  doTask3(	@PathVariable(value = "startValue", required  = true) Double startValue,
											@PathVariable(value = "startingName", required  = true) String startingName,
											@PathVariable(value = "finalName", required  = true) String finalName) {
		//same fast control over input 
		startingName=startingName.trim().toUpperCase();
		finalName=finalName.trim().toUpperCase();
			
		//-- conversion start with the same start name and final name
		if(startingName.equals(finalName)) {
			return  new ConversionResult(startValue);
		}
		
		//-- conversion start from USD or EUR to CRYPTO CURRENCY or USD or EUR
		if(startingName.equals("EUR")) {
			if(finalName.equals("USD")) {
				CryptoCompareRecord cange =getCryptoRecordFromRepo("BTC");
				return new ConversionResult(startValue/cange.getEUR()*cange.getUSD());  // 1/(1 BTC in €) * startValue * (1 BTC in $)
			}else {
				return new ConversionResult(getCryptoRecordFromRepo(finalName).getEUR()/startValue);
			}
		}
		if(startingName.equals("USD")) {
			if(finalName.equals("EUR")) {
				CryptoCompareRecord cange =getCryptoRecordFromRepo("BTC");
				return new ConversionResult(startValue/cange.getUSD()*cange.getEUR());  // 1/(1 BTC in $) * startValue * (1 BTC in €)
			}else {
				return new ConversionResult(getCryptoRecordFromRepo(finalName).getEUR()/startValue);
			}
		}
		
		//-- conversion start form CRYPTO CURRENCY to EUR or USD or CRYPO CURRENCY
		CryptoCompareRecord startingC =getCryptoRecordFromRepo(startingName);
		if(startingC!=null) {
			if(finalName.equals("EUR")) {
				return new ConversionResult(startingC.getEUR()*startValue);
			}
			if(finalName.equals("USD")) {
				return new ConversionResult(startingC.getUSD()*startValue);
			}
			CryptoCompareRecord finalC =getCryptoRecordFromRepo(finalName);
			if (finalC!=null) {
				return new ConversionResult(startingC.getUSD()/finalC.getUSD()*startValue); // startValue* (value in $ start currency) / (value in $ final currency)
			}
		}
		
		// Return in not evaluate situation
		return new ConversionResult(0);
	}
	
	//method used internally for task3
	private CryptoCompareRecord getCryptoRecordFromRepo(String currencyName) {   // CRITICISM IN RETURNING NULL!!!!
		CryptoCompareRecord readBean = cryptoCompareRepository.findOne(currencyName);
	    if(readBean == null) {
	    	 return null;
	    }
	    return readBean;
	}
	
	
	//---- EXTRA CODE use for testing and rapid access ----//
	@RequestMapping(value={"/help","/"})  //behavior help home
	public String getHelp(){  
		String explanation ="";
		explanation+="MEssaggio usato come help-home |||";
		explanation+="Task3 digita: http:/localhost:8080/task3/1/BTC/USD per sapere quanto vale 1 BTC in DOLLARI. ";
		explanation+="Puoi decidere di cambiare valore e di cambiare le valute nell'ordine che preferisci. ";
		explanation+="I nomi delle valute accettate sono: BTC USD EUR ETH LTC ETC ";
		
		//explanation autentication
		explanation+="||| comment @ComponentScan({\"com.example.demo\",\"com.example.security2\"}) for disable Task1 EXTRA";
		explanation+="||| Digita: http:/localhost:8080/logmeout per effettuare il log out";
		explanation+="||| Digita: http:/localhost:8080/currencies per ricevere una veloce rappresentazione delle valute memorizzate";
		return explanation;  
	}  
	
	// Get All crypto currency
		@RequestMapping("/currencies") //@GetMapping  //localhost:8080/currencies
		public List<CryptoCompareRecord> getAllNotes() {
		    return cryptoCompareRepository.findAll();
		}
	
	//Print a message from a consuming service 
	@GetMapping("/simpleApiRead") 
	public String getAvailableOperations() {
		RestTemplate restTemplate = new RestTemplate();
		CryptoCompareRecord readBean = restTemplate.getForObject("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=BTC,USD,EUR", CryptoCompareRecord.class);
		return readBean.toString();
	}
	
	
	

	
	
}
