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
 * Application Controller with the unique user security functionality of the app (log out, test)  
 * [used for define the behavior of the bonus 1 project specifications]
 * @author User
 *
 */
@RestController  
public class UniqueSecurityController {
	//---- EXTRA CODE use for testing ----//
	//logout method
	@RequestMapping(value="/logmeout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		if(UniqueSecurityLogic.logOutUser(request,response)) {
			return "redirect:/login";
		}
		else { return"redirect:/problemLogout";}
	}

	//connection test
	@RequestMapping(path="/testProtection", method = RequestMethod.GET)
	public ResponseEntity<String>  testProtecion(){
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
}
