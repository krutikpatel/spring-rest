package com.myrest.restapi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myrest.restapi.model.User;

@Repository
@Transactional
public class UserRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;

	public User findById(Long id) {
		return em.find(User.class, id);
	}

	public User save(User user) {
		if (user.getId() == null) {
			em.persist(user);
		} else {
			em.merge(user);
		}
		return user;
	}

	public void deleteById(Long id) {
		User student = findById(id);
		em.remove(student);
	}

	public List<User> retrieveAllUsers() {
		return em.createQuery("select u from User u", User.class).getResultList();
	}
}
