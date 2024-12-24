package com.ecommerce.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.ecommerce.service.CustomUserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {
	
	private final CustomUserServiceImpl customUserServiceImpl;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	public AppConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomUserServiceImpl customUserServiceImpl) {
        this.customUserServiceImpl = customUserServiceImpl;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
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
	            .securityContext(securityContext -> securityContext.requireExplicitSave(false))  //This means automatic saving of the SecurityContext will be enabled whenever it is modified during the processing of a request.
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            
	            // Configure request authorization
	            .authorizeHttpRequests(authorize -> authorize
	                .requestMatchers("/api/**").authenticated()  //Matches all HTTP requests where the path starts with /api/ ,,,.. .authenticated() ensures that only authenticated users (i.e., users who have successfully logged in or provided valid credentials) can access these endpoints.
	                .anyRequest().permitAll()  // Matches any other request that doesn’t fall under the /api/** pattern.    .permitAll() allows access to these endpoints without any authentication
	            )
	            
	            // Add your custom filters (update null placeholders as needed)... used to insert a custom filter into Spring Security's filter chain before a specified filter (in this case, BasicAuthenticationFilter).
	            .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
	            
	            // Disable CSRF (not recommended unless necessary)
	            .csrf(csrf -> csrf.disable())
	            
	            // Configure CORS settings
	            .cors(cors -> cors.configurationSource(request -> {
	                CorsConfiguration corsConfig = new CorsConfiguration();
	                corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000")); //Specifies which domains can access the backend. This allows only the frontend running on http://localhost:3000 to make requests.
	                corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Allows all HTTP methods (GET, POST, PUT, DELETE, etc.).
	                corsConfig.setAllowCredentials(true); //Indicates that cookies, authorization headers, or TLS certificates can be sent in requests.
	                corsConfig.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization")); //Permits all HTTP headers in the request.
	                corsConfig.setExposedHeaders(Arrays.asList("Authorization"));  //Exposes specific headers (like Authorization) to the frontend.
	                corsConfig.setMaxAge(3600L);  //Caches the CORS preflight request (OPTIONS) response for 1 hour.
	                return corsConfig;
	            }))
	            
	            // Configure HTTP basic authentication. A simple authentication mechanism where the username and password are sent in the Authorization header in the format Basic base64(username:password).
	            .httpBasic(httpBasic -> {})
	            
	            // Configure form login (if required). A traditional login mechanism where a form is presented to the user for entering their credentials. It’s included here as part of the configuration but might not be used if the application is purely API-based.
	            .formLogin(formLogin -> {})
	            
	            // Build the SecurityFilterChain
	            .build();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserServiceImpl).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
