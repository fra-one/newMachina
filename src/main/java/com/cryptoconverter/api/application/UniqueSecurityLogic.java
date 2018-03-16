package com.cryptoconverter.api.application;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Business Logic for Controller (presentation layer) for  1 connection over user name and password
 * [bonus 1 Behavior of the application]
 * @author User
 *
 */
@Component
public class UniqueSecurityLogic {
	//logout method
	public static boolean logOutUser(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){ 
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return true;
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() { 
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
				.allowedHeaders("*");
			}
		};
	}
	
}
