package com.example.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/", "/users/login", "/error", "/events/view/{id}", "/admin/users/logout", "/users/create").permitAll()
				.requestMatchers("/webjars/**", "/css/**", "/js/**").permitAll()
				.anyRequest().authenticated())
				.formLogin(form -> form
						.loginProcessingUrl("/users/login")
						.loginPage("/users/login")
						.defaultSuccessUrl("/admin")
						.failureUrl("/users/login?error"))
				.logout(logout -> logout
						.logoutUrl("/admin/users/logout")
                        .logoutSuccessUrl("/logout")
                        .deleteCookies("JSESSIONID"))
				.csrf()
				.disable();
		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// userDetailsServiceを使用して、DBからユーザを参照できるようにします
		auth
		.userDetailsService(new UserDetailsServiceImpl())
		.passwordEncoder(passwordEncoder());
	}
}
