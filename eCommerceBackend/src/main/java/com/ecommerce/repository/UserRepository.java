package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.exception.UserException;
import com.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	
    public User findUserById(Long userId) throws UserException;
	
	//public User findUserProfileByJwt(String Jwt) throws UserException;
}
