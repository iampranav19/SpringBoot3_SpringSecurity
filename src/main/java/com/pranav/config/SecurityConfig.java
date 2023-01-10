package com.pranav.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
					.authorizeHttpRequests((auth)-> auth.anyRequest().authenticated())
					.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails user1 = User.builder()
			.username("ramu")
			.password(passwordEncoder().encode("test123"))
			.roles("ADMIN")
			.build();
		UserDetails user2 = User.builder()
				.username("chaman")
				.password(passwordEncoder().encode("test123"))
				.roles("TEST")
				.build();
		UserDetails user3 = User.builder()
				.username("naman")
				.password(passwordEncoder().encode("test123"))
				.roles("PAID")
				.build();
		return new InMemoryUserDetailsManager(user1,user2,user3);
	}
	
	
}
