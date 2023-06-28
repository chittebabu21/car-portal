/**
 * 
 */
package com.car_portal.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car_portal.exception.InvalidUserException;
import com.car_portal.model.User;
import com.car_portal.response.DeleteResponse;
import com.car_portal.service.UserService;

/**
 * author: chittebabu
 */

@RestController
@RequestMapping("/users")
public class UserController {
	// get access to service object
	@Autowired
	UserService userService;
	
	// test message for localhost port 9090
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello Spring Boot!";
	}
	
	// get all users from database
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	// get user by id
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable Long userId) {
		return userService.getUserById(userId);
	}
	
	// create new user
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) throws URISyntaxException {
		User newUser = userService.saveUser(user);
		return ResponseEntity.created(new URI("/users/" + newUser.getUserId())).body(newUser);
	}
	
	// update user by id
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long userId) {
		// get user by id
		User existingUser = userService.getUserById(userId);
		
		// check if user exists
		if (existingUser != null) {
			// set properties from request body
			existingUser.setUserName(user.getUserName());
			existingUser.setUserEmail(user.getUserEmail());
			existingUser.setUserPassword(user.getUserPassword());
			
			// save user
			User updatedUser = userService.saveUser(existingUser);
			
			// send response entity
			return ResponseEntity.ok(updatedUser);
		} else {
			// error handling
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	// delete user by id
	@DeleteMapping("/{userId}")
	public ResponseEntity<DeleteResponse> deleteUserById(@PathVariable Long userId) {
		userService.deleteUserById(userId);
		DeleteResponse response = new DeleteResponse("User", userId);
		return ResponseEntity.ok(response);
	}
	
	// login with post request
	@PostMapping("/login")
	public User onLogin(@RequestBody User user) {
		try {
			return userService.login(user);
		} catch (InvalidUserException e) {
			e.printStackTrace();
			return null;
		}
	}
}
