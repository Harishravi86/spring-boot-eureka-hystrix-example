package com.example.author.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Award implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String bookName;
	private String authorName;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	private Date awardedTS;
	
	public Award() {
		
	}
	
	public Award(String bookName, String authorName, Date awardedTS) {
		super();
		this.bookName = bookName;
		this.authorName = authorName;
		this.awardedTS = awardedTS;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Date getAwardedTS() {
		return awardedTS;
	}

	public void setAwardedTS(Date awardedTS) {
		this.awardedTS = awardedTS;
	}
	

}


