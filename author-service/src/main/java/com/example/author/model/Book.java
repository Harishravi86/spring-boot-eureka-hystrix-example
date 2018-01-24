package com.example.author.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Book implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String name;
	private String author;
	@JsonIgnore
	private String iddn;
	private Boolean isPulitzerPrizeWinner;
	
	/*Note: If defining custom constructors make sure to add the default no param constructor
	 * This is used by the JSON while parsing the object and will error out if not present */
	public Book() {
		
	}
		
	public Book(String name, String author, String iddn, Boolean isPulitzerPrizeWinner) {
		super();
		this.name = name;
		this.author = author;
		this.iddn = iddn;
		this.isPulitzerPrizeWinner = isPulitzerPrizeWinner;
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
	
	public Boolean getIsPulitzerPrizeWinner() {
		if(isPulitzerPrizeWinner == null) {
			isPulitzerPrizeWinner = Boolean.FALSE;
		}
		return isPulitzerPrizeWinner;
	}

	public void setIsPulitzerPrizeWinner(Boolean isPulitzerPrizeWinner) {
		this.isPulitzerPrizeWinner = isPulitzerPrizeWinner;
	}

	
}
