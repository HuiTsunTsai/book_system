package com.example.book_system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BookRankingResponse {

	// 書籍名稱
	private String name;

	// 書籍國際碼
	private String isbn;

	// 作者
	private String writer;

	// 價錢
	private int price;

	public BookRankingResponse() {
	}

	public BookRankingResponse(String name, String isbn, String writer, int price) {
		super();
		this.name = name;
		this.isbn = isbn;
		this.writer = writer;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
