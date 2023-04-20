package com.example.book_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_system.entity.BookSystem;
import com.example.book_system.service.ifs.BookSystemService;
import com.example.book_system.vo.BookListResponse;
import com.example.book_system.vo.BookRankingResponse;
import com.example.book_system.vo.BookSystemRequest;
import com.example.book_system.vo.BookSystemResponse;
import com.example.book_system.vo.BuyingResponse;

@RestController
public class BookSystemController {

	// 導入書籍BookService
	@Autowired
	private BookSystemService bookSystemService;
	
	//新增書籍
	@PostMapping("add_book")
	public BookSystemResponse addBook(@RequestBody BookSystemRequest request) {
		return bookSystemService.addBook(request);
	}
	
	//修改書籍
	@PostMapping("set_book")
	public BookSystemResponse setBook(@RequestBody BookSystemRequest request) {
		return bookSystemService.setBook(request);
	}
	
	//書籍分類搜尋
	@PostMapping("find_book")
	public List<BookListResponse> findByType(@RequestBody BookSystemRequest request){
		return bookSystemService.findByType(request.getType());
	}
	
	//消費者搜尋
	@PostMapping("constmer_find_book")
	public List<BookListResponse> findByNameOrFindByIsbnOrFindByWriter(@RequestBody BookSystemRequest request){
		return bookSystemService.findByNameOrFindByIsbnOrFindByWriter(request.getName(),
				request.getIsbn(), request.getWriter());
	}
	
	//書籍商搜尋
	@PostMapping("bookseller_find_book")
	public List<BookSystem> findByNameOrIsbnOrWriterSeller(@RequestBody BookSystemRequest request){
		return bookSystemService.findByNameOrIsbnOrWriterSeller(request.getName(),
				request.getIsbn(), request.getWriter());
	}
	
	//更新書籍資料(庫存量)
	@PostMapping("update_book_stock")
	public List<BookListResponse> updateBookStock(@RequestBody BookSystemRequest request){
		return bookSystemService.updateBookStock(request.getName(), request.getStock());
	}
	
	//更新書籍資料(價格)
	@PostMapping("update_book_price")
	public List<BookListResponse> updateBookPrice(@RequestBody BookSystemRequest request){
		return bookSystemService.updateBookPrice(request.getName(), request.getPrice());
	}
	
	//書籍銷售
	@PostMapping("buying_book")
	public BuyingResponse buyingBook(@RequestBody BookSystemRequest request){
		return bookSystemService.buyingBook(request);
	}
	
	//暢銷書排行榜
	@PostMapping("book_ranking")
	public List<BookRankingResponse> bookRanking(@RequestBody BookSystemRequest request){
		return bookSystemService.bookRanking(request.getSales());
	}
}
