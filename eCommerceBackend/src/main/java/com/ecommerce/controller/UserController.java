package com.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.exception.UserException;
import com.ecommerce.model.User;
import com.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
    private UserService userService;
	
//	@GetMapping("/profile")
//    public ResponseEntity<User> getUserProfileById(@RequestParam Long userId) {
//        try {
//            User user = userService.findUserById(userId);
//            return ResponseEntity.ok(user);
//        } catch (UserException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
	
	@GetMapping("/profile")
    public ResponseEntity<User> getUserProfileByJwt(@RequestHeader("Authorization") String authorizationHeader) {
        try {
        	// Validate and extract JWT from 'Authorization' header
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                logger.error("Invalid Authorization header format");
                return ResponseEntity.status(400).body(null);  // Bad Request
            }
        	// Extract JWT from 'Authorization' header
            String jwt = authorizationHeader.substring(7);
            logger.info("JWT received: {}", jwt);
            User user = userService.findUserProfileByJwt(jwt);
            return ResponseEntity.ok(user);
        } catch (UserException e) {
            logger.error("User not found or invalid JWT", e);
            return ResponseEntity.status(401).body(null);  // Unauthorized
        } catch (Exception e) {
            logger.error("Unexpected error occurred", e);
            return ResponseEntity.status(500).body(null);  // Internal Server Error
        }
    }
}
