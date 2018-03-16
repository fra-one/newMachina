package com.cryptoconverter.api.presentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cryptoconverter.api.application.MultipleSecurityLogic;
/**
 * Application Controller with the multiple user security functionality of the app (over write a user)  
 * [used for define the behavior of the bonus 2 project specifications]
 * UniqueWebSecurityConfig is not used and MultipleWebSecurityConfig used in its place
 * @author User
 *
 */
@RestController  
public class MultipleSecurityController {
	@Autowired
	private MultipleSecurityLogic multipleSecurityLogic;
	
	//---- EXTRA CODE use for testing ----//
	//adding new user (AuthorizedUser) with the possibility to use the api
	@RequestMapping("user/{userName}/{password}/{role}")
	public ResponseEntity<String> addAuthorizedUser(@PathVariable(value = "userName", required  = true) String userName,
													@PathVariable(value = "password", required  = true) String password,
													@PathVariable(value = "role", required  = true) String role) {
		boolean isResultCorrect = multipleSecurityLogic.addUser(userName, password, role) ;
		if(isResultCorrect) {
			return new ResponseEntity<String>("Created user", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("PROBLEM: Created user", HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
