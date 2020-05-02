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

/*
 * knote: 
 * 		-The @RestController annotation from Spring Boot is basically a quick shortcut that saves us from 
 * 			always having to define @ResponseBody.
 * 		-Writing a JSON REST service in Spring Boot is simple, as that's its default opinion when Jackson is on the classpath.
 * 
 * 		-Spring Controllers : https://www.baeldung.com/spring-controllers
 * 		-JSON CRUD : https://www.baeldung.com/spring-boot-json
 * 
 * 		-By default : contentType is JSON, if we return object
 */
@RestController
public class UserController {
	/* no need of service yet
	@Autowired
	private UserDaoService service;
	*/
	
	@Autowired
	private UserRepository userRepository;

	/*
	 * TODO: may be add the content type of this request
	 */
	@GetMapping("/users")
	public ResponseEntity<Object> retrieveAllUsers() {
		List<User> list = userRepository.retrieveAllUsers();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Object> getUser(@PathVariable long id) {
		User user = userRepository.findById(id);
		
		if(user == null)
			throw new UserNotFoundException("User not found : id-"+ id);
		
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable long id) {
		User user = userRepository.findById(id);
		if(user==null)
			throw new UserNotFoundException("User not found : id-"+ id);
		
		userRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	/*
	 * knote: various ways and things to return.
	 * 		-ref: https://www.baeldung.com/spring-boot-json
	 * 		-using ResponseEntity
	 */
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		//knote: this is located under response headers -> location
		//return ResponseEntity.created(location).build();

		//knote: to return created user object Json, do this:
		return ResponseEntity.created(location).body(savedUser);
	}
}
