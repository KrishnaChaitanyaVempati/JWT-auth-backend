package com.firstApi.cloudVendor.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// private final SecurityFilterChain securityFilterChain;

	@Autowired
	JwtFilter jwtFilter;

//	SecurityConfig(SecurityFilterChain securityFilterChain) {
//		this.securityFilterChain = securityFilterChain;
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// return httpSecurity.csrf().disable().build();

		// if we dont disable csrf, then we need to generate & pass csrf token in each
		// request

		// we disable it and make http stateless -> gen new sessionId for every request
		return httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.csrf(customizer -> customizer.ignoringRequestMatchers("/h2console/**").disable())

				// Allow access to /h2console/** without authentication
				.authorizeHttpRequests(request -> request
						.requestMatchers("/h2console/**", "/cloudVendor/registerUser", "/cloudVendor/loginUser")
						.permitAll()

						// will not show form login but display unathorised
						.anyRequest().authenticated())

				// .headers(headers -> headers.frameOptions(frameOptions ->
				// frameOptions.disable()))
				.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
				// .formLogin(Customizer.withDefaults())// browser
				.httpBasic(Customizer.withDefaults())// postman
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(Long.parseLong("3600"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
