package com.phamthaihoang.bookservice.command.model;

public class BookRequestModel {
	private String bookId;
	private String name;
	private String author;
	private Boolean isReady;
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
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
	public Boolean getIsReady() {
		return isReady;
	}
	public void setIsReady(Boolean isReady) {
		this.isReady = isReady;
	}

	@Override
	public String toString() {
		return "BookRequestModel{" +
				"bookId='" + bookId + '\'' +
				", name='" + name + '\'' +
				", author='" + author + '\'' +
				", isReady=" + isReady +
				'}';
	}
}
