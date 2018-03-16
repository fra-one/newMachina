package com.cryptoconverter.api.infrastructure;
/**
 * Interface class for provide a logic interface from stored data (AuthorizedUser) to logic business (App User) in order to have extra flexibility 
 * @author User
 *
 */
public interface IAuthorizedUserDAO {
	AuthorizedUser getAuthorizedUser(String userName);
}
