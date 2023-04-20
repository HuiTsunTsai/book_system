package com.example.book_system.vo;

import com.example.book_system.entity.BookSystem;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookListResponse {
	
	// 書籍名稱
	private String name;

	// 書籍國際碼
	private String isbn;

	// 作者
	private String writer;

	// 價錢
	private Integer price;

	// 庫存量
	private Integer stock;

	// 分類
	private String type;
	
	// 訊息
	private String message;

	public BookListResponse() {
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BookListResponse(String message) {
		super();
		this.message = message;
	}

	
	
	

}
