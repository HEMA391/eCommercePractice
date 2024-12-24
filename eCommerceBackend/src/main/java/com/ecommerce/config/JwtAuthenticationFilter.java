package com.ecommerce.config;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
//Spring guarantees that the OncePerRequestFilter is executed only once for a given request.
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }
	
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
	
        String jwt = extractToken(request); // Extract the JWT from the request
//        if (jwt != null && jwtProvider.validateToken(jwt)) {
        if (jwt != null) {
            try {
                Claims claims = jwtProvider.parseToken(jwt); // Includes validation
                String email = claims.get("email", String.class);
                String authorities = claims.get("authorities", String.class);

                List<GrantedAuthority> auths = authorities != null
                        ? AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
                        : Collections.emptyList();
                
             // Create an authentication object and set it in the SecurityContext
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (ExpiredJwtException e) {
                logger.error("JWT token expired: {}", e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
                return;
            } catch (JwtException e) {
                logger.error("Invalid JWT token: {}", e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            } catch (Exception e) {
                logger.error("Unexpected error during token processing: {}", e.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader(JwtConstant.JWT_HEADER);
        return (header != null && header.startsWith("Bearer ")) ? header.substring(7) : null;
    }
	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		
//		String jwt = request.getHeader(JwtConstant.JWT_HEADER);
//		
//		if(jwt != null && jwt.startsWith("Bearer ")) {
//			//Bearer token - word of bearer+1space is 7
//			jwt = jwt.substring(7); //JWT tokens are typically sent with the Bearer prefix in the Authorization header, e.g., Bearer <JWT_TOKEN>. The code removes the first 7 characters ("Bearer "), leaving only the raw token
//			if(jwtProvider.validateToken(jwt)) {
//			try {
//				SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes()); //A secret key is created using JwtConstant.SECRET_KEY (the key used to sign the JWT during creation).
//				Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();  //The claims represent the data stored in the JWT payload. The JWT is parsed and validated using the Jwts.parserBuilder() from the JJWT library. If the token is invalid or the signature doesn't match, an exception is thrown.
//				String email = String.valueOf(claims.get("email"));
//				String authorities = String.valueOf(claims.get("authorities"));
//				
//				List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities); //Converts the authorities string into a list of GrantedAuthority objects. These are used by Spring Security to determine access levels.
//				Authentication authentication = new UsernamePasswordAuthenticationToken(email,null, auths); //Creates an Authentication object with the user's email and authorities. Stores the Authentication in the SecurityContextHolder, which makes it accessible throughout the application.
//				
//				SecurityContextHolder.getContext().setAuthentication(authentication);
//			}
//			catch(Exception e) {
//				//Catches any exceptions that occur during JWT validation (e.g., token expiration, tampering). Throws a BadCredentialsException, which Spring Security interprets as a failed authentication attempt.
//				throw new BadCredentialsException("Invalid token... from jwt authentication filter");
//			}
//			}
//			}
//		filterChain.doFilter(request, response);	//Passes the request and response to the next filter in the filter chain after setting the security context.
//		}	
	}
	
