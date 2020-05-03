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

knote: MongoTemplate vs MongoRepository
	MongoTemplate provides a lot more control when it comes to querying data and what data to pull from database.
	Spring Data repositories provide us a convenient outlook on how to fetch data.
	MongoTemplate is database dependent. What this means is, with Spring Data repositories, 
	you can easily switch to a different database altogether by simply using a different Spring Data repositories
	 for MySQL or Neo4J or anything else. This is not possible with MongoTemplate.		
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