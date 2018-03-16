package com.cryptoconverter.api.application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.cryptoconverter.api.infrastructure.AuthorizedUser;
import com.cryptoconverter.api.infrastructure.AuthorizedUserRepository;
/**
 * Business Logic for Controller (presentation layer) for  multiple connection over user name and password of JPA class
 * [bonus 2 Behavior of the application]
 *
 */
@RestController 
public class MultipleSecurityLogic {
	@Autowired
	private  AuthorizedUserRepository authorizedUserRepo;
	//adding new user (AuthorizedUser) with the possibility to use the api
	public  boolean addUser(String userName, String password, String role) { //true if the user is correctly created (create or modify user)
		AuthorizedUser authorizedUser = new AuthorizedUser(userName,password,role);
		authorizedUserRepo.save(authorizedUser);
		return true; 
	}
}
