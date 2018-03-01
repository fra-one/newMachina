package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController  
public class Controller {
		
	@RequestMapping("/help")  //behavior help home
	public String getHelp(){  
		return "okok!";  
	}  
}
