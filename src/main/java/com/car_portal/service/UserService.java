/**
 * 
 */
package com.car_portal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car_portal.exception.InvalidUserException;
import com.car_portal.model.User;
import com.car_portal.repository.UserRepository;

/**
 * author: chittebabu
 */

@Service
@Transactional
public class UserService {
	// get access to repository object
	@Autowired
	UserRepository userRepository;
	
	// save user
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	// find all users
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	// find user by id
	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(RuntimeException::new);
	}
	
	// delete user by id
	public void deleteUserById(Long userId) {
		userRepository.deleteById(userId);
	}
	
	// login
	public User login(User user) throws InvalidUserException {
		// get login user email
		User loginUser = userRepository.findByUserEmail(user.getUserEmail());
		
		// check if user is null
		if (loginUser == null) {
			throw new InvalidUserException("Invalid email or password...");
		}
		
		// return login user
		return loginUser;
	}
}
