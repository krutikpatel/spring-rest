package com.myrest.restapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.myrest.restapi.mongodb.MongoUser;
import com.myrest.restapi.mongodb.MongoUserRepository;

@RestController
public class MongoUserController {

	@Autowired
	private MongoUserRepository userRepository;
	
	@GetMapping("/musers")
	public ResponseEntity<Object> getAllUsers() {
		List<MongoUser> list = userRepository.findAll();
				
		return ResponseEntity.ok(list);
	}
	
	/*
	 * knote: mongoDB id normally is String
	 */
	@GetMapping("/musers/{id}")
	public ResponseEntity<Object> getUser(@PathVariable String id) {
		Optional<MongoUser> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("User not found : id-"+ id);
		
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/musers_email/{email}")
	public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
		MongoUser user = userRepository.findUserByEmail(email);
		
		if(user == null)
			throw new UserNotFoundException("User not found : email-"+ email);
		
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/musers/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		Optional<MongoUser> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("User not found : id-"+ id);
		
		userRepository.deleteById(id);		
		return ResponseEntity.noContent().build();
	}
	
	/*
	 * knote: various ways and things to return.
	 * 		-ref: https://www.baeldung.com/spring-boot-json
	 * 		-using ResponseEntity
	 */
	@PostMapping("/musers")
	public ResponseEntity<Object> createUser(@Valid @RequestBody MongoUser user) {
		MongoUser savedUser = userRepository.save(user);
		
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
