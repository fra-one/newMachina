package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController  
public class Controller {
	@RequestMapping("/page1")  
    public String page1(){  
        return"nice page1";  
    }  
	@RequestMapping("/page2")  
    public String page2(){  
        return"nice page1";  
    }  
    @RequestMapping("/")  
    public String home(){  
        return"nice home";  
    }  
    
    @RequestMapping( method=RequestMethod.GET)  
    public @ResponseBody MyBean retunJson(){  
        return new MyBean(12.2);  
    } 
}
