/**
 * 
 */
package com.car_portal.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * author: chittebabu
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	// method to allow unauthenticated access
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		// for all endpoints
		http.authorizeRequests()
			.anyRequest()
			.permitAll();
		
		// disable csrf
		http.csrf().disable();
	}
}
