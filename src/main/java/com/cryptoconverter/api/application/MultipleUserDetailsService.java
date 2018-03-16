package com.cryptoconverter.api.application;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.cryptoconverter.api.infrastructure.AuthorizedUser;
import com.cryptoconverter.api.infrastructure.IAuthorizedUserDAO;
/**
 * class used for connect AuthorizedUser to the Spring.Security.User
 * @author User
 *
 */
@Service
public class MultipleUserDetailsService implements UserDetailsService {
	@Autowired
	private IAuthorizedUserDAO authorizedUserDAO;
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		AuthorizedUser authorizedUser = authorizedUserDAO.getAuthorizedUser(userName);
		GrantedAuthority authority = new SimpleGrantedAuthority(authorizedUser.getRole());
		UserDetails userDetails = (UserDetails)new User(authorizedUser.getUserName(),
				authorizedUser.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
} 