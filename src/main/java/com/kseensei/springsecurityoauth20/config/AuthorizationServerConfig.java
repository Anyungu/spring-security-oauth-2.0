
package com.kseensei.springsecurityoauth20.config;

import java.util.UUID;

import com.kseensei.springsecurityoauth20.services.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keys.KeyManager;
import org.springframework.security.crypto.keys.StaticKeyGeneratingKeyManager;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

@Order
@EnableWebSecurity
@Import(OAuth2AuthorizationServerConfiguration.class)
public class AuthorizationServerConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;

	@Bean
	public RegisteredClientRepository registeredClientRepository() {
		// @formatter:off
		RegisteredClient registeredClient = RegisteredClient
				.withId(UUID.randomUUID().toString())
				.clientId("messaging-client").clientSecret("secret")
				.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.redirectUri("http://localhost:8080/authorized")
				.scope("message.read")
				.scope("message.write")
				// .clientSettings(clientSettings -> clientSettings.requireUserConsent(true))
				.build();
		return new InMemoryRegisteredClientRepository(registeredClient);
		// @formatter:on
	}

	@Bean
	public KeyManager keyManager() {
		return new StaticKeyGeneratingKeyManager();
	}

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
			"/h2-console", 
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