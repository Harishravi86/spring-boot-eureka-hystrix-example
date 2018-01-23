package com.example.book.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.entity.Book;

@RestController
public class BookController {
	
    @GetMapping("/book/{name}")
    public Book getBookByName(@PathVariable(value="name") String name) {
    	System.out.println("In getBookByName");
    	return new Book(name, "n.spark", UUID.randomUUID().toString());
    }
    
    @RequestMapping(value = "/book/author/{name}", method = RequestMethod.GET)
    public List<Book> getBooksByAuthorName(@PathVariable(value="name") String name) {
    	List<Book> listOfBooksByAuthor = createBooks(name);
    	return listOfBooksByAuthor;
    }
    
    @RequestMapping(value = "async/book/author/{name}", method = RequestMethod.GET)
    public List<Book> getBooksByAuthorNameAsync(@PathVariable(value="name") String name) throws InterruptedException {
    	List<Book> listOfBooksByAuthor = createBooks(name);
    	Thread.sleep(2000l);
    	return listOfBooksByAuthor;
    }
    
    private List<Book> createBooks(String name){
    	List<Book> listOfBooksByAuthor = new ArrayList<Book>();
    	for(int i = 1; i < 6; i++) {
    		listOfBooksByAuthor.add(new Book("dazzle sparkle : "+ i,  name, UUID.randomUUID().toString()));
    	}
    	
    	return listOfBooksByAuthor;
    }
    
    @RequestMapping(value = "/book/iddn/{iddn}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable(value="iddn") String iddn) {
    	Book book = new Book("dazzle sparkle",  "nspark", iddn);
    	return book;
    }
    
}
