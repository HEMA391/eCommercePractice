package com.ecommerce.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppConfig {

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//		.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
//		.addFilterBefore(null, null).csrf().disable()
//		.cors().configurationSource(new CorsConfigurationSource() {
//			
//			@Override
//			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//				CorsConfiguration corsConfig = new CorsConfiguration();
//				corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//				corsConfig.setAllowedMethods(Collections.singletonList("*")); //all http methods from frontend
//				corsConfig.setAllowCredentials(true);
//				corsConfig.setAllowedHeaders(Collections.singletonList("*"));
//				corsConfig.setExposedHeaders(Arrays.asList("Authorization"));
//				corsConfig.setMaxAge(3600L);
//				return corsConfig;
//			}
//		}).and()
//		.httpBasic().and()http.formLogin()	;
//		return http.build();
//	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http
	            // Set session management policy to stateless
	            .securityContext(securityContext -> securityContext.requireExplicitSave(false))
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            
	            // Configure request authorization
	            .authorizeHttpRequests(authorize -> authorize
	                .requestMatchers("/api/**").authenticated()
	                .anyRequest().permitAll()
	            )
	            
	            // Add your custom filters (update null placeholders as needed)
	            .addFilterBefore(new JwtAuthenticationFilter(), BasicAuthenticationFilter.class)
	            
	            // Disable CSRF (not recommended unless necessary)
	            .csrf(csrf -> csrf.disable())
	            
	            // Configure CORS settings
	            .cors(cors -> cors.configurationSource(request -> {
	                CorsConfiguration corsConfig = new CorsConfiguration();
	                corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
	                corsConfig.setAllowedMethods(Collections.singletonList("*")); // Allow all HTTP methods
	                corsConfig.setAllowCredentials(true);
	                corsConfig.setAllowedHeaders(Collections.singletonList("*"));
	                corsConfig.setExposedHeaders(Arrays.asList("Authorization"));
	                corsConfig.setMaxAge(3600L);
	                return corsConfig;
	            }))
	            
	            // Configure HTTP basic authentication
	            .httpBasic(httpBasic -> {})
	            
	            // Configure form login (if required)
	            .formLogin(formLogin -> {})
	            
	            // Build the SecurityFilterChain
	            .build();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
