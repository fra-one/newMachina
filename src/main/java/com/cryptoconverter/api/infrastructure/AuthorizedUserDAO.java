package com.cryptoconverter.api.infrastructure;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * Implementation class for provide a logic interface from stored data (AuthorizedUser) to logic business (App User) in order to have extra flexibility 
 * @author User
 *
 */
@Repository
@Transactional
public class AuthorizedUserDAO implements IAuthorizedUserDAO {
	@Autowired
	private AuthorizedUserRepository authorizedUserRepository;	
	
	
	@PersistenceContext	
	private EntityManager entityManager;
	public AuthorizedUser getAuthorizedUser(String userName) {
		return authorizedUserRepository.findOne(userName); // no real usage 		
	}
} 