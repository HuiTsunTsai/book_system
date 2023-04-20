package com.example.book_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book_system.entity.BookSystem;

@Repository
public interface BookSystemDao extends JpaRepository<BookSystem, String>{

	//書籍分類搜尋
	List<BookSystem> findByType(String type);
	
	//消費者/書籍商搜尋
	List<BookSystem> findByNameOrIsbnOrWriter(String name, String isbn, String writer);
	
	//更新書籍資料(庫存量)
	List<BookSystem> findByNameOrStock(String name, int stock);
	
	//更新書籍資料(價格)
	List<BookSystem> findByNameOrPrice(String name, int price);
	
	//暢銷書排行榜
	List<BookSystem> findTop5ByOrderBySalesDesc();
}
