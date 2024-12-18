package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;

@Service
public class CustomUserServiceImpl implements UserDetailsService{

	private UserRepository userRepository;
	
	public CustomUserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Loading user by username: " + username);
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found with the email " + username);
		}
		// If found, print out the password to verify
	    System.out.println("Loaded user password: " + user.getPassword());
	    
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), authorities);
	}

}
