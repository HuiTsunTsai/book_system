package com.example.book_system.vo;

import java.util.List;
import java.util.Map;

import com.example.book_system.entity.BookSystem;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookSystemRequest {

	// BookSystem
	private BookSystem bookSystem;

	// BookSystem 的清單
	@JsonProperty("book_list")
	private List<BookSystem> BookList;

	// 書籍銷售的 Map
	@JsonProperty("order_book")
	private Map<String, Integer> orderMap;

	// 分類
	private String type;

	// 書籍名稱
	private String name;

	// 書籍國際碼
	private String isbn;

	// 作者
	private String writer;

	// 庫存量
	private int stock;

	// 價錢
	private int price;

	// 銷售量
	private int sales;

	// 購買數量
	private int amount;

	public BookSystemRequest() {
	}

	public BookSystemRequest(BookSystem bookSystem, List<BookSystem> bookList) {
		super();
		this.bookSystem = bookSystem;
		BookList = bookList;
	}

	public BookSystemRequest(String name, String isbn, String writer) {
		super();
		this.name = name;
		this.isbn = isbn;
		this.writer = writer;
	}

	public BookSystem getBookSystem() {
		return bookSystem;
	}

	public void setBookSystem(BookSystem bookSystem) {
		this.bookSystem = bookSystem;
	}

	public List<BookSystem> getBookList() {
		return BookList;
	}

	public void setBookList(List<BookSystem> bookList) {
		BookList = bookList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Map<String, Integer> getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(Map<String, Integer> orderMap) {
		this.orderMap = orderMap;
	}

}
