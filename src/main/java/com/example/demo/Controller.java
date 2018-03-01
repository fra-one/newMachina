package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.jpa.Currency;
import com.example.jpa.CurrencyRepository;

@RestController  
public class Controller {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private ReadBeanRepository readBeanRepository;

	//---- TASK 1 & 2 of the project ----//
	//Update DB with record delete and record create
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	@Scheduled(fixedDelay = 10000)
	public void scheduleFixedDelayTask() {
		System.out.println("Regular task OVER JPA performed at "+ dateFormat.format(new Date()));
		getStore("BTC");
		getStore("ETH"); //extra
		getStore("LTC"); //extra
		getStore("ETC"); //extra
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
		
		ReadBean newReadBean = restTemplate.getForObject("https://min-api.cryptocompare.com/data/price?fsym="+currencyName+"&tsyms="+currencyName+",USD,EUR", ReadBean.class);
		newReadBean.setCurrencyName(currencyName);
		newReadBean.setBTC(1.0); 
		//DELETE
		ReadBean oldReadBean =readBeanRepository.findOne(newReadBean.getCurrencyName());
	    if(oldReadBean != null) {
	        readBeanRepository.delete(oldReadBean);
	    }
		//SAVE
	    readBeanRepository.save(newReadBean);
		return newReadBean.toString();
	}
	
	//---- TASK 3 of the project ----// 
	//conversion over currency with specific Json return type 
	@GetMapping("/task3/{SVAL}/{SCOD}/{FCOD}")   //http://localhost:8080/task3/25/BTC/USD
	public  @ResponseBody MyBean  doTask3(	@PathVariable(value = "SVAL", required  = true) Double startValue,
											@PathVariable(value = "SCOD", required  = true) String startingName,
											@PathVariable(value = "FCOD", required  = true) String finalName) {
		//same fast control over input 
		startingName=startingName.trim().toUpperCase();
		finalName=finalName.trim().toUpperCase();
			
		//-- conversion start with the same start name and final name
		if(startingName.equals(finalName)) {
			return  new MyBean(startValue);
		}
		
		//-- conversion start from USD or EUR to CRYPTO CURRENCY or USD or EUR
		if(startingName.equals("EUR")) {
			if(finalName.equals("USD")) {
				ReadBean cange =getBeanInternal("BTC");
				return new MyBean(startValue/cange.getEUR()*cange.getUSD());  // 1/(1 BTC in €) * startValue * (1 BTC in $)
			}else {
				return new MyBean(getBeanInternal(finalName).getEUR()/startValue);
			}
		}
		if(startingName.equals("USD")) {
			if(finalName.equals("EUR")) {
				ReadBean cange =getBeanInternal("BTC");
				return new MyBean(startValue/cange.getUSD()*cange.getEUR());  // 1/(1 BTC in $) * startValue * (1 BTC in €)
			}else {
				return new MyBean(getBeanInternal(finalName).getEUR()/startValue);
			}
		}
		
		//-- conversion start form CRYPTO CURRENCY to EUR or USD or CRYPO CURRENCY
		ReadBean startingC =getBeanInternal(startingName);
		if(startingC!=null) {
			if(finalName.equals("EUR")) {
				return new MyBean(startingC.getEUR()*startValue);
			}
			if(finalName.equals("USD")) {
				return new MyBean(startingC.getUSD()*startValue);
			}
			ReadBean finalC =getBeanInternal(finalName);
			if (finalC!=null) {
				return new MyBean(startingC.getUSD()/finalC.getUSD()*startValue); // startValue* (value in $ start currency) / (value in $ final currency)
			}
		}
		
		// Return in not evaluate situation
		return new MyBean(0);
	}
	
	//method used internally for task3
	private ReadBean getBeanInternal(String currencyName) {
		ReadBean readBean = readBeanRepository.findOne(currencyName);
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
		public List<ReadBean> getAllNotes() {
		    return readBeanRepository.findAll();
		}
	
	//Print a message from a consuming service 
	@GetMapping("/simpleApiRead") 
	public String getAvailableOperations() {
		RestTemplate restTemplate = new RestTemplate();
		ReadBean readBean = restTemplate.getForObject("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=BTC,USD,EUR", ReadBean.class);
		return readBean.toString();
	}
	
	
	

	
	
}
