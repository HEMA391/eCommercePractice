package com.ecommerce.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.controller.UserController;
import com.ecommerce.exception.UserException;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	
	public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}

	@Override
	public User findUserById(Long userId) throws UserException {
//		Optional<User> user = userRepository.findById(userId);
//		if(user.isPresent()) {
//			return user.get();
//		}
//		throw new UserException("user not found with id: " + userId);
		return userRepository.findById(userId)
                .orElseThrow(() -> {
                    String message = "User not found with id: " + userId;
                    logger.error(message); // Log the error
                    return new UserException(message);
                });
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
//		String email = jwtProvider.getEmailFromToken(jwt);
//		User user = userRepository.findByEmail(email);
//		if(user == null) {
//			throw new UserException("user not found with email " + email);
//		}
//		return user;
		try {
            String email = jwtProvider.getEmailFromToken(jwt);
            logger.info("Extracted email from JWT: {}", email); // Log the extracted email

            User user = userRepository.findByEmail(email);
            if (user == null) {
                String message = "User not found with email: " + email;
                logger.error(message); // Log the error
                throw new UserException(message);
            }
            return user;
        } catch (Exception e) {
            logger.error("Error processing JWT: {}", e.getMessage(), e); // Log the full stack trace
            throw new UserException("Invalid or expired JWT token.");
        }
	}

}
