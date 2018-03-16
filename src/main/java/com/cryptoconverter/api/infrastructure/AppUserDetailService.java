package com.cryptoconverter.api.infrastructure;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
/**
 * class used for connect AuthorizedUser to the Spring.Security.User
 * @author User
 *
 */
@Component
public class AppUserDetailService implements UserDetailsService {
	@Autowired
	private IAuthorizedUserDAO userInfoDAO;
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		AuthorizedUser authorizedUser = userInfoDAO.getAuthorizedUser(userName);
		GrantedAuthority authority = new SimpleGrantedAuthority(authorizedUser.getRole());
		UserDetails userDetails = (UserDetails)new User(authorizedUser.getUserName(),
				authorizedUser.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
} 