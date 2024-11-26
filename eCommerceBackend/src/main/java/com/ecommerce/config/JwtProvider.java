package com.ecommerce.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
//JWT Token Generation: The JwtProvider is used effectively to generate tokens during signup and login.
	SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	public String generateToken(Authentication auth) {
		String jwt = Jwts.builder().setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+86400000)) //24hrs = 86,400,000 milliseconds
				.claim("email", auth.getName())
				.signWith(key).compact();
		return jwt;
	}
	
	public String getEmailFromToken(String jwt) {
		jwt = jwt.substring(7);
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();  //The claims represent the data stored in the JWT payload. The JWT is parsed and validated using the Jwts.parserBuilder() from the JJWT library. If the token is invalid or the signature doesn't match, an exception is thrown.
		String email = String.valueOf(claims.get("email"));
		return email;
	}
}
