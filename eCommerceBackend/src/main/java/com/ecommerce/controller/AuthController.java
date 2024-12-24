package com.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.exception.UserException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.User;
import com.ecommerce.request.LoginRequest;
import com.ecommerce.response.AuthResponse;
import com.ecommerce.service.AuthService;
import com.ecommerce.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtProvider jwtProvider;
    private final CartService cartService;

    public AuthController(AuthService authService, JwtProvider jwtProvider, CartService cartService) {
        this.authService = authService;
        this.jwtProvider = jwtProvider;
        this.cartService=  cartService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@Valid @RequestBody User user) {
    	try {
    	// Sign-up logic
    	User createdUser = authService.signUp(user);
    	Cart cart = cartService.createCart(createdUser);
    	
        //Authenticate newly created user (auto-login)
        Authentication authentication = new UsernamePasswordAuthenticationToken(createdUser.getEmail(), createdUser.getPassword());
        System.out.println("Authentication Success: " + authentication.isAuthenticated());
        SecurityContextHolder.getContext().setAuthentication(authentication);

     // Generate JWT token for newly authenticated user
        String token =   jwtProvider.generateToken(authentication);// Reusing the login method to generate the token
        AuthResponse authResponse = new AuthResponse(token, "Signup Successful");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    	} catch (UserException e) {
            // Handle exception and return a suitable response
            return new ResponseEntity<>(new AuthResponse(e.getMessage(), "Signup Failed!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@Valid @RequestBody LoginRequest loginRequest) {
    	try {
    	// Login logic: authenticate and generate token
    	String token = authService.login(loginRequest);
        AuthResponse authResponse = new AuthResponse(token, "Login Successful!");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    	} catch (UserException e) {
            // Handle the exception and return a suitable response
            return new ResponseEntity<>(new AuthResponse(e.getMessage(), "Login Failed!"), HttpStatus.UNAUTHORIZED);
        }
    }
}
