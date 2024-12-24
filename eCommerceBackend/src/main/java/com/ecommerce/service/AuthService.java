package com.ecommerce.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.exception.UserException;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.request.LoginRequest;

@Service
public class AuthService {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private CustomUserServiceImpl customUserServiceImpl;
    

    public AuthService(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder,
            CustomUserServiceImpl customUserServiceImpl) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.customUserServiceImpl = customUserServiceImpl;
    }

    public User signUp(User user) throws UserException {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) {
            throw new UserException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String login(LoginRequest loginRequest) throws UserException {
        String userName = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UserDetails userDetails = customUserServiceImpl.loadUserByUsername(userName);
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password!");
        }

        Authentication authentication = authenticate(userName, password);
        return jwtProvider.generateToken(authentication);
    }
    
    public Authentication authenticate(String userName, String password) throws UserException {
        UserDetails userDetails = customUserServiceImpl.loadUserByUsername(userName);
        
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password!");
        }
        
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
        	System.out.println("Password mismatch: " + password + " vs " + userDetails.getPassword());
            throw new BadCredentialsException("Invalid username or password!");
        }
        
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
