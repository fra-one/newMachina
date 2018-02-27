package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController  
public class Controller {
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/page1")  //page1 behavior
	public String page1(){  
		return"nice page1";  
	}  

	@RequestMapping("/")  //empty page
	public String home(){  
		return"nice home";  
	}  

	@RequestMapping( method=RequestMethod.GET)  //behavior over GET action
	public @ResponseBody MyBean retunJson(){  
		return new MyBean(12.2);  
	} 
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	@GetMapping("/simpleRead") //Consuming a service by GET method
	public String getAvailableOperations() {
		RestTemplate restTemplate = new RestTemplate();
		ReadBean readBean = restTemplate.getForObject("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=BTC,USD,EUR", ReadBean.class);
		return readBean.toString();
	}
}
