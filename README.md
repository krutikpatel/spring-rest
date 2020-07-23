# spring-rest (with RDBMS(JPA) and MongoDB)
Springboot REST API with JPA and MongoDB examples

Spring Initialzer deps : devtools, webMvc, H2, JPA, Mongo

Features:
* REST controllers
* JPA data saving support
* Exception handlers for API
  - more details : https://www.baeldung.com/exception-handling-for-rest-with-spring
* Javax Validations (more info : https://www.baeldung.com/javax-validation )
* Swagger documentation
* Unit testing REST API (and may be covered individual components)
  - test guides : 
    - https://spring.io/guides/gs/testing-web/
    - https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
* MongoDB support
  - Good link: https://www.journaldev.com/18156/spring-boot-mongodb
  - Official doc : https://spring.io/projects/spring-data-mongodb#overview
    - https://docs.spring.io/spring-data/mongodb/docs/2.2.7.RELEASE/reference/html/#reference
    - https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/html/howto.html#howto-use-spring-data-jpa--and-mongo-repositories
  - Official samples: https://github.com/spring-projects/spring-data-examples/tree/master/mongodb 
  - Query details: https://docs.spring.io/spring-data/mongodb/docs/2.2.7.RELEASE/reference/html/#mongo.repositories
  - Using multiple fields to query: easy too : https://www.baeldung.com/queries-in-spring-data-mongodb --> JSON query methods
  - Indexing : to improve performance on queries, use field by which you query as "index". 
    - Use @Indexed annotation for single field index
    - Use @CompoundIndexes and @CompoundIndex on model class, for compound index, index with multiple fields.
    - Links:
      - https://www.baeldung.com/spring-data-mongodb-index-annotations-converter
      - https://docs.spring.io/spring-data/data-mongodb/docs/current/api/org/springframework/data/mongodb/core/index/CompoundIndex.html
      - https://docs.mongodb.com/manual/tutorial/create-indexes-to-support-queries/
      - https://docs.mongodb.com/manual/tutorial/analyze-query-plan/
      - https://docs.atlas.mongodb.com/performance-advisor/schema-suggestions/
      - https://docs.mongodb.com/manual/indexes/
      - http://learnmongodbthehardway.com/schema/indexes/#indexes
      
      
* Docker file for docker image creation
  - Ref: https://www.udemy.com/course/deploy-java-spring-boot-to-aws-amazon-web-service/learn/lecture/15443874#overview
  - https://www.baeldung.com/dockerizing-spring-boot-application
  - sample run command
    - docker run -d --publish=9999:5000 12653b725371
    - 9999 is host port, 5000 is port in container where springboot server is listening

* Dockerrun.aws.json file for running it on Elastic Beanstalk

## Useful Links  
- Lots of Spring REST tutorials : https://www.baeldung.com/rest-with-spring-series
- Official Spring all Database examples: https://github.com/spring-projects/spring-data-examples/tree/master/
