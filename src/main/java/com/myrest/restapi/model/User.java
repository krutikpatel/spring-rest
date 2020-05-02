package com.myrest.restapi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;

	/*
	 * knote: Javax validation
	 */
	@NotNull
	@Email
	@NotEmpty
	String email;
	
	/*
	 * knote: Javax validation
	 */
	@Size(min=2, message="Name should have atleast 2 characters")
	private String name;

	/*
	 * knote: Javax validation
	 */
	@Past
	private Date birthDate;

	protected User() {

	}

	public User(Long id, String name, Date birthDate, String email) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, email=%s, birthDate=%s]", id, name, email, birthDate);
	}
}
