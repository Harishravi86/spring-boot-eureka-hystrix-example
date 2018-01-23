# spring-boot-eureka-hystrix-example
Projects that provide examples on creating microservices, registering with eureka server, use eureka service discovery to talk to each other. Also has examples on using Hystrix for adding fall back methods 

This example contains the examples on the following

1. creating/configuring Eureka server
2. creating a microservice and registering it with Eureka server
3. microservices interacting with each other using eureka discovery
4. making synchronous/asynchronous(both AsyncRestTemplate and @Async) between microservices
5. adding custom executors, custom exception handlers, callback methods for asynchronous calls
6. adding fall back method using Hystrix

The above are all illustrated through simple end points in the AuthorController. The an overarching theme for this example is to have author microservice 
talk to book-service and award-service to retrieve information needed to display the author information. The REST end points exposed in the AuthorController 
are the entry points for all the examples. 

To keep the examples simple and focused, JPA/DB layer is not introduced. The service end points return static data mimicing interactions with a datasource.

Projects

1. eureka-server
   This is the eureka-server
   Starts on the default port of eureka-server, 8761
   All other microservices register to this server
   
2. award-service
   This is the the microservice to retrieve information about awards
   
3. book-service
   This is the microservice to retrieve information about books.
   
4. author-service
   This is the microservice to retrieve information about authors. The AuthorController has the entry points for the all examples defined above
   
