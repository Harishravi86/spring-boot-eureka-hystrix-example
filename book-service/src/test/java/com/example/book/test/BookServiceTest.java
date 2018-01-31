package com.example.book.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.book.controller.BookController;
import com.example.book.model.Book;
import com.example.book.service.BookService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BookController.class, BookService.class})
@RestClientTest(BookService.class)
public class BookServiceTest {

	//private static final Logger logger = Logger.getLogger(BookControllerTests.class);
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void getBookByName() {
		try {
			Book toSaveObj = new Book();
			toSaveObj.setAuthor("nspark");
			toSaveObj.setName("romcom101");
			assertTrue(toSaveObj.getIddn() == null);
			Book bookResponse = this.bookService.saveBook(toSaveObj);
			assertTrue(bookResponse.getIddn() != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
