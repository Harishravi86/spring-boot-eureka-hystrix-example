package com.example.author.model;

import java.io.Serializable;

public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String streetAddressLine1;
	private String streetAddressLine2;
	private String city;
	private String state;
	private String zipCode;
	
	public Address() {
		
	}
	
	@Override
	public String toString() {
		return "Address [streetAddressLine1=" + streetAddressLine1 + ", streetAddressLine2=" + streetAddressLine2
				+ ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + "]";
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((streetAddressLine1 == null) ? 0 : streetAddressLine1.hashCode());
		result = prime * result + ((streetAddressLine2 == null) ? 0 : streetAddressLine2.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (streetAddressLine1 == null) {
			if (other.streetAddressLine1 != null)
				return false;
		} else if (!streetAddressLine1.equals(other.streetAddressLine1))
			return false;
		if (streetAddressLine2 == null) {
			if (other.streetAddressLine2 != null)
				return false;
		} else if (!streetAddressLine2.equals(other.streetAddressLine2))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	
	
	
}
