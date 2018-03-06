package com.cryptoconverter.api.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.cryptoconverter.api.application.UniqueSecurityLogic;

/**
 * class used for define unique security Behavior
 * @author User
 *
 */
@RestController  
public class UniqueSecurityController {
	@RequestMapping(value="/logmeout")//logout method
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		if(UniqueSecurityLogic.logOutUser(request,response)) {
			return "redirect:/login";
		}
		else { return"redirect:/problemLogout";}
	}



	//-- extra code for testing--//
	@RequestMapping(path="/testProtection", method = RequestMethod.GET)
	public ResponseEntity<String>  testProtecion(){
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
}
