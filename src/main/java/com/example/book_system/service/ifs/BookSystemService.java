package com.example.book_system.service.ifs;

import java.util.List;

import com.example.book_system.entity.BookSystem;
import com.example.book_system.vo.BookListResponse;
import com.example.book_system.vo.BookRankingResponse;
import com.example.book_system.vo.BookSystemRequest;
import com.example.book_system.vo.BookSystemResponse;
import com.example.book_system.vo.BuyingResponse;

public interface BookSystemService {

	//新增書籍
	public BookSystemResponse addBook(BookSystemRequest request);
	
	//修改書籍
	public BookSystemResponse setBook(BookSystemRequest request);
	
	//書籍分類搜尋
	public List<BookListResponse> findByType(String type);
	
	//書籍搜尋(消費者/書籍商)：
	//消費者
	public List<BookListResponse> findByNameOrFindByIsbnOrFindByWriter(String name, String isbn, String writer);
	//書籍商
	public List<BookSystem> findByNameOrIsbnOrWriterSeller(String name, String isbn, String writer);
	
	//更新書籍資料(庫存量)
	public List<BookListResponse> updateBookStock(String name, int stock);
	
	//更新書籍資料(價格)
	public List<BookListResponse> updateBookPrice(String name, int price);
	
	//書籍銷售
	public BuyingResponse buyingBook(BookSystemRequest request);
	
	//暢銷書排行榜
	public List<BookRankingResponse> bookRanking(int sales);
}
