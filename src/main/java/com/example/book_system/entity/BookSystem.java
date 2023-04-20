package com.example.book_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BookSystem")
public class BookSystem {

	// 書名
	@Id
	@Column(name = "name")
	private String name;

	// 國際碼
	@Column(name = "isbn")
	private String isbn;

	// 作者
	@Column(name = "writer")
	private String writer;

	// 價格
	@Column(name = "price")
	private int price;

	// 庫存量
	@Column(name = "stock")
	private int stock;

	// 銷量
	@Column(name = "sales")
	private int sales;

	// 分類
	@Column(name = "type")
	private String type;
	
	public BookSystem() {
	}

	public BookSystem(String name, String isbn, String writer, int price, int stock, int sales, String type) {
		super();
		this.name = name;
		this.isbn = isbn;
		this.writer = writer;
		this.price = price;
		this.stock = stock;
		this.sales = sales;
		this.type = type;
	}

	public BookSystem(String name, String isbn, String writer, int price, int stock) {
		super();
		this.name = name;
		this.isbn = isbn;
		this.writer = writer;
		this.price = price;
		this.stock = stock;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
