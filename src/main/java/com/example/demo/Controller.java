package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  
public class Controller {
	@RequestMapping("/page1")  
    public String page1(){  
        return"nice page1";  
    }  
    @RequestMapping("/")  
    public String home(){  
        return"nice home";  
    }  
}
