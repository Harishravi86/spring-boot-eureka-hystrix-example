package com.example.author.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.example.author.model.Address;
import com.example.author.model.Author;
import com.example.author.model.Award;
import com.example.author.model.Book;
import com.example.author.service.AuthorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="Author Information", description="This provides the API for retrieving author information")
@RestController
public class AuthorController {
	
	private static final Logger logger = Logger.getLogger(AuthorController.class);
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	private AsyncRestTemplate asyncRestTemplate;
	
	@Autowired
	private AuthorService authorService;
	
	/* From postman use http://localhost:8092/author/nspark
	 * When making call from the postman or any external system we need to know the IP address and port and cannot use the microservice app name to call
	 */
	// this is an example to make synchronous calls to other microservices
    @RequestMapping(value = "/author/{name}", method = RequestMethod.GET)
    public Author getAuthorInformationWithBooksSynchronous(@PathVariable(value="name") String name) {
    	Author author = new Author(name, 35L, 'M');
    	
    	/* For communication between microservices we can use the microservice application name as defined in the application.yml if that microservice is registered with the Eureka Server.
    	 * This way microservice doesn't need to know on which IP address/port the book microservice is running, and can make the call to get the information needed */  
    	
    	/*Note: The java type Book had to be defined again in the author-service. Since JSON uses this to parse the response into the object, it needs to have the schema as the Book defined in the book-service microservice. 
    	 * It can have additional attributes but cannot have lesser attributes. We can also add annotations to ignore values while being parsed etc.
    	 * Please look at the Book type in author-service in detail.*/
    	
    	List<Book> books = restTemplate.exchange(
                "http://book-service/book/author/{name}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {}, 
                name).getBody();
    	
    	author.setBooksAuthored(books);

    	/* Note: 
    	 *  Since this is a very simple example where the return type is a simple collection of books or a book like the commented out section, we can use the ParameterizedTypeReference with List<Book> 
    	 *  However, most real world examples are complex types which contain multiple collections of objects
    	 *  In such case, we must create another POJO to encapsulate all the information needed and have that be the return type if we were the ones writing the API in book-service
    	 * */
    	
    	List<Award> awardsReceived = restTemplate.exchange(
                "http://award-service/award/list/winner/name/{name}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Award>>() {}, 
                name).getBody();
    	
    	author.setAwardsReceived(awardsReceived);
    	
    	return author;
    }
    
    
    /* From postman use http://localhost:8092/async/author/nspark
	 * When making call from the postman or any external system we need to know the IP address and port and cannot use the microservice app name to call
	 */
    // this is an example of making asychrnous calls when the method being invoked is not annotated with @Async
    @ApiOperation(value = "View author information including books authored and awards received given author name", response = Author.class)
    @ApiResponses(value = {
				            @ApiResponse(code = 200, message = "Successfully retrieved author"),
				            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
				            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
				            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
				           }
    )
    @RequestMapping(value = "async/author/{name}", method = RequestMethod.GET)
    public Author getAuthorInformationWithBooksAsynchronous(@PathVariable(value="name") String name) throws InterruptedException, ExecutionException {
    	Author author = new Author(name, 35L, 'M');
    	StopWatch stopWatch = new StopWatch();
    	stopWatch.start();
    	ListenableFuture<ResponseEntity<List<Book>>> future = asyncRestTemplate.exchange(
                "http://book-service/async/book/author/{name}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {}, 
                name);
    	

    	ListenableFuture<ResponseEntity<List<Award>>> awardsReceived = asyncRestTemplate.exchange(
                "http://award-service/async/award/list/winner/name/{name}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Award>>() {}, 
                name);
    	// https://stackoverflow.com/a/35293277
    	// the getBody method is the blocking call and must be made after make all the calls else the main thread will wait for the response
    	author.setBooksAuthored(future.get().getBody());
    	author.setAwardsReceived(awardsReceived.get().getBody());
    	stopWatch.stop();
    	logger.info("Time taken to return the response is "+stopWatch.getTime());
    	return author;
    }
    
    // this is an example of making parallel asynchronous calls using @Async and waiting for the response from all the calls to continue processing
    @RequestMapping(value = "async/author/information/{name}", method = RequestMethod.GET)
    public Author getAuthorInformationWithBooksAsyncAnnotation(@PathVariable(value="name") String name) throws InterruptedException, ExecutionException {
    	logger.info("Starting stopWatch");
    	StopWatch stopWatch = new StopWatch();
    	stopWatch.start();
    	
    	CompletableFuture<Author> authorInfo = authorService.getAuthorDemographics(name);
    	CompletableFuture<Address> addressInfo = authorService.getAuthorAddress(name);
    	CompletableFuture<List<Book>> booksAuthored = authorService.getAuthoredBooks(name);
    	CompletableFuture.allOf(authorInfo, addressInfo, booksAuthored).join();
    	Author author = authorInfo.get();
    	author.setAddress(addressInfo.get());
    	author.setBooksAuthored(booksAuthored.get());
    	
    	stopWatch.stop();
    	logger.info("Time taken to return the response is "+stopWatch.getTime());
    	return author;
    }
    
    // this is an example of rest end point which results in an Exception and calls the AsycnExceptionHandler
    @RequestMapping(value = "async/author/information/exception/{name}", method = RequestMethod.GET)
    public Author getAuthorDemographicsWithException(@PathVariable(value="name") String name) throws InterruptedException, ExecutionException {
    	logger.info("Starting stopWatch");
    	StopWatch stopWatch = new StopWatch();
    	stopWatch.start();
    	
    	CompletableFuture<Author> authorInfo = authorService.getAuthorDemographics(name);
    	authorService.updateAuthorName(name);
    	CompletableFuture.allOf(authorInfo).join();
    	Author author = authorInfo.get();

    	stopWatch.stop();
    	logger.info("Time taken to return the response is "+stopWatch.getTime());
    	return author;
    }
    
    // This calls a method which has a fallBack enabled with Hystrix
    @RequestMapping(value = "/author/book/iddn/{iddn}", method = RequestMethod.GET)
    public Author getAuthorInformationWithBookIddn(@PathVariable(value="iddn") String iddn) {
    	return authorService.getAuthoredBook(iddn);
    }
    
    // tries to get author information from cache is present in cache, else makes calls to the book-service and award-service
    @RequestMapping(value = "cahce/author/name/{name}", method = RequestMethod.GET)
    public Author getAuthorInformationFromCache(@PathVariable(value="name") String name) {
    	return authorService.getAuthorWithBooks(name);
    }
    
    // adds author information to cache
    @RequestMapping(value = "cahce/author", method = RequestMethod.POST)
    public Author insertAuthorInformation(@Valid @RequestBody Author author) {
    	return authorService.addAuthorInformation(author.getName(), author.getAge(), author.getGender());
    }

}
