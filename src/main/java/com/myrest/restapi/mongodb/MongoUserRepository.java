package com.myrest.restapi.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/*
 * knote: to add methods which needs implementation,
 * 		-autowire following in Impl class
 * 
 * 		@Autowired
    	MongoTemplate mongoTemplate; //==> This is like EntityManager of Hibernate, it supports all CRUD and other fancy methods.

		Example at: https://mkyong.com/spring-boot/spring-boot-spring-data-mongodb-example/
 */
@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser, String> {

	/* knote: example of Aggregation query, simillarly we can add other custom query methods
	@Aggregation("{ $group : { _id : $customerId, total : { $sum : 1 } } }")
	List<MongoUser> totalOrdersPerCustomer(Sort sort);
	
	//Supports native JSON query string
    @Query("{email:'?0'}")
    Domain findUserByEmail(String email);

    @Query("{domain: { $regex: ?0 } })")
    List<Domain> findCustomByRegExDomain(String domain);
    
	*/
	//Supports native JSON query string
    @Query("{email:'?0'}")
    MongoUser findUserByEmail(String email);
}