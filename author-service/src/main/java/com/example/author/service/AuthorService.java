package com.example.author.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.author.model.Address;
import com.example.author.model.Author;
import com.example.author.model.Award;
import com.example.author.model.Book;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class AuthorService {

	private static final Logger logger = Logger.getLogger(AuthorService.class);
	
	@Autowired
	private RestTemplate restTemplate;

	@Async("author-executor")
	public CompletableFuture<Author> getAuthorDemographics(String name) throws InterruptedException {
		Thread.sleep(5000l);
		return CompletableFuture.supplyAsync(() -> authorDemographics(name)).
				whenComplete(new BiConsumer<Author, Throwable>() {
					@Override
					public void accept(Author t, Throwable u) {
						logger.info("Executed when complete with author object "+t.getName());
						t.setName(t.getName()+"11");
					}
				})

				.exceptionally(new Function<Throwable, Author>() {
					@Override
					public Author apply(Throwable t) {
						t.printStackTrace();
						return null;
					}
				}
						).thenApplyAsync(new Function<Author, Author>() {
							@Override
							public Author apply(Author o) {
								System.out.println("Last action of all!");
								return o;
							}
						});
	}

	@Async("author-executor")
	public CompletableFuture<Address> getAuthorAddress(String name) throws InterruptedException {
		Address address = new Address("Mystic world", null, "Alice", "Wonderland", "123456");
		Thread.sleep(3000l);
		return CompletableFuture.completedFuture(address);
	}

	@Async("author-executor")
	public CompletableFuture<List<Book>> getAuthoredBooks(String name) throws InterruptedException {
		List<Book> books = restTemplate.exchange(
				"http://book-service/book/author/{name}",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Book>>() {}, 
				name).getBody();
		Thread.sleep(1000l);
		return CompletableFuture.completedFuture(books);
	}

	// This method is used as an example for uncaught exception
	@Async("author-executor")
	public void updateAuthorName(String name){
		int i = 1/0;
		logger.info("Should never come here, exception should have been thrown"+i);
	}

	private Author authorDemographics(String name) {
		Author author = new Author(name, 35L, 'M');
		// int i = 1/0;
		return author;
	}


	@HystrixCommand(fallbackMethod = "getBookByIdReliable")
	public Author getAuthoredBook(String iddn) {
		Author author = new Author("nspark", 35L, 'M');

		Book book = restTemplate.exchange(
				"http://book-service/book/iddn/{iddn}",
				HttpMethod.GET,
				null,
				Book.class, 
				iddn).getBody();

		List<Book> books = new ArrayList<Book>();
		books.add(book);
		author.setBooksAuthored(books);

		return author;
	}

	public Author getBookByIdReliable(String iddn){
		Author author = new Author("default", 35L, 'M');
		Book book = new Book("default", "default", "default", null);
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		author.setBooksAuthored(books);
		return author;
	}

	// will try to retrieve information from cache first, if not present will make the service calls
	@Cacheable("author-information")
	public Author getAuthorWithBooks(String name) {
		logger.info("Getting information from the method and not the cache");
		Author author = new Author(name, 35L, 'M');
		List<Book> books = restTemplate.exchange(
				"http://book-service/book/author/{name}",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Book>>() {}, 
				name).getBody();

		author.setBooksAuthored(books);

		List<Award> awardsReceived = restTemplate.exchange(
				"http://award-service/award/list/winner/name/{name}",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Award>>() {}, 
				name).getBody();

		author.setAwardsReceived(awardsReceived);

		return author;
	}
	
	// add author created to cache
	@CachePut(cacheNames = "author-information", key = "#result.name")
	public Author addAuthorInformation(String name, Long age, Character gender) {
		return new Author(name, age, gender);
	}
	 
}
