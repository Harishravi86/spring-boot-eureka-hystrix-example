package com.example.author.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class Author {

	private String name;
	private Long age;
	private char gender;
	private List<Book> booksAuthored;
	private List<Award> awardsReceived;
	private Address address;
	
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