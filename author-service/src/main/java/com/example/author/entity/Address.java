package com.example.author.entity;

public class Address {

	private String streetAddressLine1;
	private String streetAddressLine2;
	private String city;
	private String state;
	private String zipCode;
	
	public Address() {
		
	}
	
	public Address(String streetAddressLine1, String streetAddressLine2, String city, String state, String zipCode) {
		super();
		this.streetAddressLine1 = streetAddressLine1;
		this.streetAddressLine2 = streetAddressLine2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public String getStreetAddressLine1() {
		return streetAddressLine1;
	}

	public void setStreetAddressLine1(String streetAddressLine1) {
		this.streetAddressLine1 = streetAddressLine1;
	}

	public String getStreetAddressLine2() {
		return streetAddressLine2;
	}

	public void setStreetAddressLine2(String streetAddressLine2) {
		this.streetAddressLine2 = streetAddressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
