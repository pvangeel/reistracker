package org.goldflower.config;

import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import org.goldflower.account.UserService;

@Configuration
@ImportResource(value = "classpath:spring-security-context.xml")
class SecurityConfig {
	
	@Bean
	public UserService userService() {
		return new UserService();
	}
//
//	@Bean
//	public TokenBasedRememberMeServices rememberMeServices() {
//		return new TokenBasedRememberMeServices("remember-me-key", userService());
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}
}