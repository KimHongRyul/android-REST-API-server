package com.example.project05.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

import com.example.project05.config.jwt.JwtAuthenticationFilter;
import com.example.project05.config.jwt.JwtAuthorizationFilter;
import com.example.project05.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final CorsFilter corsFilter;
	private final UserRepository userRepository;
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	//.antMatchers("/**").authenticated()
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.addFilter(corsFilter)
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.formLogin().disable()
		.httpBasic().disable()
		.addFilter(new JwtAuthenticationFilter(authenticationManager())) //AuthenticationManager
		.addFilter(new JwtAuthorizationFilter(authenticationManager(),userRepository))
		.authorizeRequests()
		.antMatchers("/user/**")
		.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/manager/**")
		.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/admin/**")
		.access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll();

	}

}
