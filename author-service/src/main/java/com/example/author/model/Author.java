package com.example.author.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(value=Include.NON_NULL)
public class Author implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Long age;
	private char gender;
	private List<Book> booksAuthored;
	private List<Award> awardsReceived;
	private Address address;
	
	public Author() {
		/*needed for 
		 * "exception": "org.springframework.http.converter.HttpMessageNotReadableException",
  			"message": "JSON parse error: Can not construct instance of com.example.author.model.Author: no suitable constructor found, can not deserialize from Object value (missing default constructor or creator, or perhaps need to add/enable type information?); nested exception is com.fasterxml.jackson.databind.JsonMappingException: Can not construct instance of com.example.author.model.Author: no suitable constructor found, can not deserialize from Object value (missing default constructor or creator, or perhaps need to add/enable type information?)\n at [Source: java.io.PushbackInputStream@23ba043a; line: 1, column: 2]",
		 */
	}
	
	
	
	@Override
	public String toString() {
		return "Author [name=" + name + ", age=" + age + ", gender=" + gender + ", booksAuthored=" + booksAuthored
				+ ", awardsReceived=" + awardsReceived + ", address=" + address + "]";
	}



	public Author(String name, Long age, char gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public List<Book> getBooksAuthored() {
		return booksAuthored;
	}

	public void setBooksAuthored(List<Book> booksAuthored) {
		this.booksAuthored = booksAuthored;
	}

	public List<Award> getAwardsReceived() {
		return awardsReceived;
	}

	public void setAwardsReceived(List<Award> awardsReceived) {
		this.awardsReceived = awardsReceived;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}