package com.example.book_system.vo;

import java.util.List;

import com.example.book_system.entity.BookSystem;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookSystemResponse {

	// BookSystem
	private BookSystem bookSystem;

	// BookSystem 的清單
	@JsonProperty("book_list")
	private List<BookSystem> bookList;

	// 訊息
	private String message;

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

	// 銷售量
	private Integer sales;

	// 分類
	private String type;

	public BookSystemResponse(BookSystem bookSystem, String message) {
		super();
		this.bookSystem = bookSystem;
		this.message = message;
	}

	public BookSystemResponse(String name, String isbn, String writer, Integer price, Integer stock) {
		super();
		this.name = name;
		this.isbn = isbn;
		this.writer = writer;
		this.price = price;
		this.stock = stock;
	}

	public BookSystemResponse(List<BookSystem> bookList, String message) {
		super();
		this.bookList = bookList;
		this.message = message;
	}

	public BookSystemResponse(BookSystem bookSystem) {
		super();
		this.bookSystem = bookSystem;
	}

	public BookSystemResponse(String message) {
		super();
		this.message = message;
	}

	public List<BookSystem> getBookList() {
		return bookList;
	}

	public void setBookList(List<BookSystem> bookList) {
		this.bookList = bookList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BookSystem getBookSystem() {
		return bookSystem;
	}

	public void setBookSystem(BookSystem bookSystem) {
		this.bookSystem = bookSystem;
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

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
