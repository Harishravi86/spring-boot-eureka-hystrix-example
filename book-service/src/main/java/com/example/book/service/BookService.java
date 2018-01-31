package com.example.book.service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.book.model.Book;

@Service
public class BookService {

	public Book getBookByName(String name) {
    	return new Book(name, "n.spark", UUID.randomUUID().toString());
    }
    
    public List<Book> getBooksByAuthorName(String name) {
    	List<Book> listOfBooksByAuthor = createBooks(name);
    	return listOfBooksByAuthor;
    }
    
    public List<Book> getBooksByAuthorNameExpensive(String name) throws InterruptedException {
    	List<Book> listOfBooksByAuthor = createBooks(name);
    	Thread.sleep(2000l);
    	return listOfBooksByAuthor;
    }
    
    private List<Book> createBooks(String name) {
    	List<Book> listOfBooksByAuthor = new ArrayList<Book>();
    	for(int i = 1; i < 6; i++) {
    		listOfBooksByAuthor.add(new Book("dazzle sparkle : "+ i,  name, UUID.randomUUID().toString()));
    	}
    	
    	return listOfBooksByAuthor;
    }
    
    public Book getBookById(String iddn) {
    	Book book = new Book("dazzle sparkle",  "nspark", iddn);
    	return book;
    }
    
    public  Book saveBook(Book book) {
    	System.out.println("Made it here +++++++++++++++++++++++++++++++++++++++++++++++");
    	book.setIddn(UUID.randomUUID().toString());
		return book;
	}
}
