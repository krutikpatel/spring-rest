package com.myrest.restapi.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myrest.restapi.model.User;
import com.myrest.restapi.repository.UserRepository;

/*
 * knote: it is possible to actually execute http requests, just that need to make sure DB (and other components) are actually connected
 */

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
    private UserRepository userRepo;
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Test
	public void getAllUsers()
	  throws Exception {

		//knote: mockito prepare data
		User user = new User(1L,"krutik",new Date(), "abc@def.com");
		List<User> allUsers = Arrays.asList(user);
		when(userRepo.retrieveAllUsers()).thenReturn(allUsers);
		
		//knote: actual REST API call test via mock
		mockMvc.perform(get("/users")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())			      
			      .andExpect(jsonPath("$[0].name", is(user.getName())));	//knote: notice how json array ekem is addressed with $[0]	
	}
	
	@Test
	public void getUserById()
	  throws Exception {		
		User user = new User(1L,"krutik",new Date(), "abc@def.com");		
		when(userRepo.findById(user.getId())).thenReturn(user);
				
		mockMvc.perform(get("/users/1")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())			      
			      .andExpect(jsonPath("name", is(user.getName())))
				  .andExpect(jsonPath("email", is(user.getEmail())));
	}
	
	//@Test
	public void postUser()
	  throws Exception {		
		User user = new User(1L,"krutik",new Date(), "abc@def.com");		
		when(userRepo.save(user)).thenReturn(user);
				
		mockMvc.perform(post("/users")
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())			      
				.andExpect(jsonPath("name", is(user.getName())))
				.andExpect(jsonPath("email", is(user.getEmail())));
	}
}
