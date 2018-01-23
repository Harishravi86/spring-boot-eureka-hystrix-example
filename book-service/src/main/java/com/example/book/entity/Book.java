package com.example.book.entity;

import java.io.Serializable;

public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String author;
	private String iddn;

	public Book() {

	}

	public Book(String name, String author, String iddn) {
		super();
		this.name = name;
		this.author = author;
		this.iddn = iddn;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIddn() {
		return iddn;
	}
	public void setIddn(String iddn) {
		this.iddn = iddn;
	}


}
