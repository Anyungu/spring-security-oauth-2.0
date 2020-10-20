
package com.kseensei.springsecurityoauth20.config;

import com.kseensei.springsecurityoauth20.services.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Order
@EnableWebSecurity
public class AuthorizationServerConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;


	/**
	 * 
	 * @return
	 */
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		daoAuthenticationProvider.setUserDetailsService(authenticationService);
		return daoAuthenticationProvider;
	}

	/**
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// @formatter:off
		web
		.ignoring()
		.antMatchers(
			"/webjars/**", 
			"/h2-console/**", 
			"/swagger-ui.html", 
			"/authenticate",
			"/authenticateuser", 
			"/registeruser", 
			"/swagger-ui/index.html?url=/api-docs&validatorUrl=#/",
			"/swagger-ui/**", 
			"/v3/api-docs/**", 
			"/swagger-resources/**", 
			"/swagger-resources",
			"/api/clients/createclient"
		);
		// @formatter:on
	}

}