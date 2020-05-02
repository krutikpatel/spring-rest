package com.myrest.restapi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myrest.restapi.model.User;
import com.myrest.restapi.repository.UserRepository;

@SpringBootApplication
public class RestapiApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		User user1 = new User(111L,"krutik",new Date(), "abc@def.com");
		
		userRepository.save(user1);
	}
}
