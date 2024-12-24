package com.ecommerce.config;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;

@Service
public class JwtProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
//JWT Token Generation: The JwtProvider is used effectively to generate tokens during signup and login.
	SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	//public static final String SECRET_KEY = System.getenv("JWT_SECRET");
	public String generateToken(Authentication auth) {
		String jwt = Jwts.builder()
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+86400000)) //24hrs = 86,400,000 milliseconds
				.claim("email", auth.getName())
				.signWith(key)
				.compact();
		return jwt;
	}
	public Claims parseToken(String jwt) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jwt)
            .getBody();
    }
	public boolean validateToken(String jwt) {
        try {
        	Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt); // If parsing succeeds, token is valid.
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
            logger.debug("Invalid token: {}...", jwt.substring(0,30));
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token format: {}", e.getMessage());
            logger.debug("Invalid token: {}...", jwt.substring(0, 30));
        } catch (ExpiredJwtException e) {
            logger.warn("Expired JWT token: {}", e.getMessage()); // Use warn level for expired tokens
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
        }
	
	public String getEmailFromToken(String jwt) {
		try {
		if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        } else {
            throw new IllegalArgumentException("Invalid token format");
        }
        Claims claims = parseToken(jwt);
        System.out.println("Claims: " + claims);
        return claims.get("email", String.class);
		} catch (JwtException | IllegalArgumentException e) {
	        throw new IllegalArgumentException("Invalid token format", e);
	    }  
	}
	
//	public boolean validateToken(String jwt) {
//        try {
//            Jwts.parserBuilder()  // This creates a builder object to configure a JwtParser.  A JwtParser is used to parse and validate JWTs.
//                    .setSigningKey(key)  //Sets the secret key or public key that will be used to validate the signature of the JWT. key can be: 1) A byte array, for symmetric algorithms (e.g., HMAC SHA256).  (2) A public key, for asymmetric algorithms (e.g., RSA or EC)..
//                    .build() //Builds the JwtParser instance using the configurations provided (like the signing key). The parser is ready for use only after calling build().
//                    .parseClaimsJws(jwt); // Validate token structure and signature. Parses the JWT and verifies its signature. 
//            jwt is the compact serialized JWT string you want to parse and validate.
//            The JWT typically consists of three parts:
//            Header: Contains metadata about the token (e.g., algorithm, type).
//            Payload (Claims): Contains user data or "claims" (e.g., username, roles, expiration time).
//            Signature: Ensures the token's integrity and authenticity.
//            Output:
//            Returns a Jws<Claims> object if the token is valid. This object contains:
//            The claims (decoded payload) in the JWT.
//            The header.
//            The signature.
//            Why it's needed: Validates the signature to ensure the token is authentic.
//            Extracts claims (like user information or token expiration time) for further processing.
//
//            return true;
//        } catch (SignatureException e) {
//            System.out.println("Invalid JWT signature: " + e.getMessage());
//        } catch (MalformedJwtException e) {
//            System.out.println("Invalid JWT token: " + e.getMessage());
//        } catch (ExpiredJwtException e) {
//            System.out.println("JWT token is expired: " + e.getMessage());
//        } catch (IllegalArgumentException e) {
//            System.out.println("JWT claims string is empty: " + e.getMessage());
//        }
//        return false;
//    }
}
