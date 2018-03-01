package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("francesco").password("exmachina").roles("USER");
    }
    
/*
@Autowired
private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .withUser("francesco").password("exmachina")
      .authorities("ROLE_USER");
}

@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/securityNone").permitAll()
      .anyRequest().authenticated()
      .and()
      .httpBasic()
      .authenticationEntryPoint(authenticationEntryPoint);

//    http.addFilterAfter(new CustomFilter(),BasicAuthenticationFilter.class);
}
*/
}
