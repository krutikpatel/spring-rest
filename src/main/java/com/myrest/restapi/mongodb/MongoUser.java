package com.myrest.restapi.mongodb;

import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

/*
 * knote: the resulting collection/table name is : mongoUser (so it is camle-case with first letter small)
 * 		-This class is schema for us. If any filed here is not present in DB, it will be shown null.
 * 		-AS done below, we can add Collection param to change name
 */
@Document(collection = "mongoUser")
public class MongoUser {
	
	@Id
	private String id;

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

	protected MongoUser() {

	}

	public MongoUser(String id, String name, Date birthDate, String email) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
