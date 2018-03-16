package com.cryptoconverter.api.application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.cryptoconverter.api.infrastructure.AppUserDetailService;
import com.cryptoconverter.api.infrastructure.AuthorizedUserRepository;
import com.cryptoconverter.api.infrastructure.IAuthorizedUserDAO;

@Configuration
@EnableWebSecurity
public class MultipleWebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AppUserDetailService appUserDetailService;	
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	
	@Autowired
	private MultipleSecurityLogic multipleSecurityLogic;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.anyRequest().authenticated()
		.and().httpBasic()
		.authenticationEntryPoint(authEntryPoint);
		
		multipleSecurityLogic.addUser("francesco", "exmachina", "USER");	// possibility to have the same starting user
	} 
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
             auth.userDetailsService(appUserDetailService);
	}
        
    	

} 