package com.example.book_system.vo;

import java.util.List;

import com.example.book_system.entity.BookSystem;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyingResponse {

	// 書籍名稱
	private String name;

	// 書籍國際碼
	private String isbn;

	// 作者
	private String writer;

	// 價錢
	private Integer price;

	// 購買數量
	private Integer amount;

	// 購買總價格
	private Integer total;

	// 訊息
	private String message;

	// 所有購買書籍的清單
	private List<BuyingResponse> AllBook;

	// 所有書籍的清單
	private List<BookSystem> bookList;

	public BuyingResponse() {
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BuyingResponse(String message) {
		super();
		this.message = message;
	}

	public List<BuyingResponse> getAllBook() {
		return AllBook;
	}

	public void setAllBook(List<BuyingResponse> allBook) {
		AllBook = allBook;
	}

	public BuyingResponse(List<BuyingResponse> allBook) {
		super();
		AllBook = allBook;
	}

	public List<BookSystem> getBookList() {
		return bookList;
	}

	public void setBookList(List<BookSystem> bookList) {
		this.bookList = bookList;
	}

	public BuyingResponse(String message, List<BookSystem> bookList) {
		super();
		this.message = message;
		this.bookList = bookList;
	}

	
}
