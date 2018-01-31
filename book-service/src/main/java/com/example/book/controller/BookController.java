package com.example.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.domain.BookResponse;
import com.example.book.model.Book;
import com.example.book.service.BookService;

import io.netty.handler.codec.http.HttpResponseStatus;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/book/{name}",  method = RequestMethod.GET)
    public Book getBookByName(@PathVariable(value="name")String name) {
    	return bookService.getBookByName(name);
    }
	
    @RequestMapping(value = "list/book/author/{name}", method = RequestMethod.GET)
    public List<Book> getBooksByAuthorName(@PathVariable(value="name") String name) {
    	return bookService.getBooksByAuthorName(name);
    }
    
    @RequestMapping(value = "async/list/book/author/{name}", method = RequestMethod.GET)
    public List<Book> getBooksByAuthorNameAsync(@PathVariable(value="name") String name) throws InterruptedException {
    	return bookService.getBooksByAuthorNameExpensive(name);
    }
    
    @RequestMapping(value = "/book/iddn/{iddn}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable(value="iddn") String iddn) {
    	return bookService.getBookById(iddn);
    }
    
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public BookResponse saveBook(@RequestParam(value = "book") Book book) {
    	BookResponse response = new BookResponse();
    	Book bookSaved = bookService.saveBook(book);
    	response.setBook(bookSaved);
    	response.setStatus(HttpResponseStatus.OK.toString());
    	
    	return response;
    }
    
}
