package com.myrest.restapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myrest.restapi.exception.UserNotFoundException;
import com.myrest.restapi.model.User;
import com.myrest.restapi.repository.UserRepository;

@RestController
public class UserController {
	/* no need of service yet
	@Autowired
	private UserDaoService service;
	*/
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRepository.retrieveAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable long id) {
		User user = userRepository.findById(id);
		
		if(user == null)
			throw new UserNotFoundException("User not found : id-"+ id);
		
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable long id) {
		User user = userRepository.findById(id);
		if(user==null)
			throw new UserNotFoundException("User not found : id-"+ id);
		
		userRepository.deleteById(id);		
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		// CREATED
		// /user/{id}     savedUser.getId()
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
}
