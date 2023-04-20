package com.example.book_system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.book_system.entity.BookSystem;
import com.example.book_system.repository.BookSystemDao;
import com.example.book_system.service.ifs.BookSystemService;
import com.example.book_system.vo.BookListResponse;
import com.example.book_system.vo.BookRankingResponse;
import com.example.book_system.vo.BookSystemRequest;
import com.example.book_system.vo.BookSystemResponse;
import com.example.book_system.vo.BuyingResponse;

@Service
public class BookSystemServiceImpl implements BookSystemService {

	@Autowired
	private BookSystemDao bookSystemDao; // 導入書籍資料庫

	// 新增(修改)書籍
	@Override
	public BookSystemResponse addBook(BookSystemRequest request) {
		List<BookSystem> errorBookList = new ArrayList<>(); // new出裝重複書籍的空間
		List<BookSystem> bookList = request.getBookList(); // 取得所有輸入的資訊
		String pattern = "(\\d{3}-){4}[X0-9]"; // isbn格式
		for (BookSystem item : bookList) { // 遍歷
			if (!StringUtils.hasText(item.getName()) || !StringUtils.hasText(item.getType())
					|| !StringUtils.hasText(item.getWriter())) { // 防呆：若名稱、類型、作者為空
				return new BookSystemResponse("輸入資料不得為空"); // 回傳"輸入資料不得為空"
			}
			if (item.getPrice() < 0 || item.getStock() < 0 || item.getSales() < 0) { // 防呆：若價錢、庫存、銷量小於0
				return new BookSystemResponse("數量錯誤"); // 回傳"數量錯誤"
			}
			if (!Pattern.matches(pattern, item.getIsbn())) { // 防呆：如果isbn格式比對錯誤
				return new BookSystemResponse("ISBN 格式錯誤"); // 回傳"ISBN 格式錯誤"
			}
			if (bookSystemDao.existsById(item.getName())) { // 防呆：若資料庫裡已有重複的書名
				errorBookList.add(item); // errorBookList加入存在的書籍名稱
			}
		}
		if (!errorBookList.isEmpty()) { // 若errorBookList不為空
			return new BookSystemResponse(errorBookList, "此書已存在"); // 回傳所有存在書籍、"此書已存在"
		}
		return new BookSystemResponse(bookList, "新增成功"); // 回傳所有新增資訊、"新增成功"
	}

	// 修改書籍
	@Override
	public BookSystemResponse setBook(BookSystemRequest request) {
		List<BookSystem> bookList = request.getBookList();
		; // 取得所有輸入的資訊
		String pattern = "(\\d{3}-){4}[X0-9]"; // isbn格式
		for (BookSystem item : bookList) { // 遍歷
			if (!StringUtils.hasText(item.getName()) || !StringUtils.hasText(item.getType())
					|| !StringUtils.hasText(item.getWriter())) { // 防呆：若名稱、類型、作者為空
				return new BookSystemResponse("輸入資料不得為空"); // 回傳"輸入資料不得為空"
			}
			if (item.getPrice() < 0 || item.getStock() < 0 || item.getSales() < 0) { // 防呆：若價錢、庫存、銷量小於0
				return new BookSystemResponse("數量錯誤"); // 回傳"數量錯誤"
			}
			if (!Pattern.matches(pattern, item.getIsbn())) { // 防呆：如果isbn格式比對錯誤
				return new BookSystemResponse("ISBN 格式錯誤"); // 回傳"ISBN 格式錯誤"
			}
			if (item.getName().equals(request)) { // 若輸入的書名等於資料庫裡的書名
				bookSystemDao.save(item); // 儲存其資訊
			}
		}
		bookSystemDao.saveAll(bookList); // 儲存所有資訊
		return new BookSystemResponse(bookList, "修改成功"); // 回傳所有修改資訊、"修改成功"
	}

	// 書籍分類搜尋
	@Override
	public List<BookListResponse> findByType(String type) {
		List<BookSystem> bookList = bookSystemDao.findByType(type); // 用bookList裝資料庫裡"類型"的資料
		List<BookListResponse> resAllBook = new ArrayList<>(); // new出包裹所有書的空間
		if (!StringUtils.hasText(type)) { // 防呆：若輸入的類型為空
			List<BookListResponse> errorList = new ArrayList<>(); // new出裝錯誤訊息的清單
			BookListResponse error = new BookListResponse(); // new出裝錯誤訊息的空間
			error.setMessage("輸入資料不得為空"); // 將訊息設定給error
			errorList.add(error); // 把 error 加入至清單
			return errorList; // 回傳裝錯誤訊息的清單
		}
		for (BookSystem item : bookList) { // 遍歷
			BookListResponse resBook = new BookListResponse(); // new出放書的空間
			resBook.setName(item.getName()); // 取出名稱，放進resBook
			resBook.setIsbn(item.getIsbn()); // 取出isbn，放進resBook
			resBook.setWriter(item.getWriter()); // 取出作者，放進resBook
			resBook.setPrice(item.getPrice()); // 取出價錢，放進resBook
			resBook.setStock(item.getStock()); // 取出庫存量，放進resBook
			resAllBook.add(resBook); // 將全部資料加到包裹所有書的空間
		}
		return resAllBook; // 回傳包裹所有書的空間
	}

	// 消費者搜尋
	@Override
	public List<BookListResponse> findByNameOrFindByIsbnOrFindByWriter(String name, String isbn, String writer) {
		List<BookSystem> bookList = bookSystemDao.findByNameOrIsbnOrWriter(name, isbn, writer); // 用bookList裝資料庫裡"名稱"、"isbn"、"作者"的資料
		List<BookListResponse> resAllBook = new ArrayList<>(); // new出包裹所有書的空間
		List<BookListResponse> errorList = new ArrayList<>(); // new出裝錯誤訊息的清單
		BookListResponse error = new BookListResponse(); // new出裝錯誤訊息的空間
		if (!StringUtils.hasText(name) && !StringUtils.hasText(isbn) && !StringUtils.hasText(writer)) { // 防呆：若輸入的名稱、isbn、作者為空
			error.setMessage("輸入資料不得為空"); // 將訊息設定給error
			errorList.add(error); // 把 error 加入至清單
			return errorList; // 回傳裝錯誤訊息的清單
		}
		if(StringUtils.hasText(name)) { // 如果有輸入名稱的話
			if(!bookSystemDao.existsById(name)) { // 防呆：若輸入錯誤書籍名稱
				error.setName("此書不存在"); // 將訊息設定給error
				errorList.add(error); // 把 error 加入至清單
				return errorList; // 回傳裝錯誤訊息的清單
			}
		}
		for (BookSystem item : bookList) { // 遍歷
			BookListResponse resBook = new BookListResponse(); // new出放書的空間
			resBook.setName(item.getName()); // 取出名稱，放進resBook
			resBook.setIsbn(item.getIsbn()); // 取出isbn，放進resBook
			resBook.setWriter(item.getWriter()); // 取出作者，放進resBook
			resBook.setPrice(item.getPrice()); // 取出價錢，放進resBook
			resAllBook.add(resBook); // 將全部資料加到包裹所有書的空間
		}
		return resAllBook; // 回傳包裹所有書的空間
	}

	// 書籍商搜尋
	@Override
	public List<BookSystem> findByNameOrIsbnOrWriterSeller(String name, String isbn, String writer) {
		List<BookSystem> errorList = new ArrayList<>(); // new出裝錯誤訊息的清單
		BookSystem error = new BookSystem(); // new出裝錯誤訊息的空間
		if (!StringUtils.hasText(name)&&!StringUtils.hasText(isbn)&&!StringUtils.hasText(writer)) { // 防呆：若輸入的名稱、isbn、作者為空
			error.setName("輸入資料不得為空"); // 將訊息設定給error
			errorList.add(error); // 把 error 加入至清單
			return errorList; // 回傳裝錯誤訊息的清單
		}
		if(StringUtils.hasText(name)) { // 如果有輸入名稱的話
			if(!bookSystemDao.existsById(name)) { // 防呆：若輸入錯誤書籍名稱
				error.setName("此書不存在"); // 將訊息設定給error
				errorList.add(error); // 把 error 加入至清單
				return errorList; // 回傳裝錯誤訊息的清單
			}
		}
		return bookSystemDao.findByNameOrIsbnOrWriter(name, isbn, writer); // 回傳資料庫
	}

	// 更新書籍資料(庫存量)
	@Override
	public List<BookListResponse> updateBookStock(String name, int stock) {
		List<BookSystem> bookList = bookSystemDao.findAll(); // 用bookList裝資料庫裡所有的資料
		List<BookListResponse> resAllBook = new ArrayList<>(); // new出包裹所有書的空間
		List<BookListResponse> errorList = new ArrayList<>(); // new出裝錯誤訊息的清單
		BookListResponse error = new BookListResponse(); // new出裝錯誤訊息的空間
		if (!StringUtils.hasText(name)) { // 防呆：若輸入的名稱為空
			error.setMessage("輸入資料不得為空"); // 將訊息設定給error
			errorList.add(error); // 把 error 加入至清單
			return errorList; // 回傳裝錯誤訊息的清單
		}
		if(!bookSystemDao.existsById(name)) { // 防呆：若輸入錯誤書籍名稱
			error.setMessage("此書不存在"); // 將訊息設定給error
			errorList.add(error); // 把 error 加入至清單
			return errorList; // 回傳裝錯誤訊息的清單
		}
		if(stock < 0) { // 防呆：若輸入的庫存小於0
			error.setMessage("輸入資料錯誤"); // 將訊息設定給error
			errorList.add(error); // 把 error 加入至清單
			return errorList; // 回傳裝錯誤訊息的清單
		}
		for (BookSystem item : bookList) { // 遍歷
			if (item.getName().equals(name)) { // 若輸入名稱比對相同
				BookListResponse resBook = new BookListResponse(); // new出放書的空間
				resBook.setName(item.getName()); // 取出名稱，放進resBook
				resBook.setIsbn(item.getIsbn()); // 取出isbn，放進resBook
				resBook.setWriter(item.getWriter()); // 取出作者，放進resBook
				resBook.setPrice(item.getPrice()); // 取出價錢，放進resBook
				item.setStock(item.getStock() + stock); // 設定取出的庫存加上輸入的庫存
				resBook.setStock(item.getStock()); // 取出設定好的庫存，放進resBook
				bookSystemDao.save(item); // 資料庫儲存資訊
				resAllBook.add(resBook); // 將全部資料加到包裹所有書的空間
			}
		}
		return resAllBook; // 回傳包裹所有書的空間
	}

	// 更新書籍資料(價格)
	@Override
	public List<BookListResponse> updateBookPrice(String name, int price) {
		List<BookSystem> bookList = bookSystemDao.findAll(); // 用bookList裝資料庫裡所有的資料
		List<BookListResponse> resAllBook = new ArrayList<>(); // new出包裹所有書的空間
		List<BookListResponse> errorList = new ArrayList<>(); // new出裝錯誤訊息的清單
		BookListResponse error = new BookListResponse(); // new出裝錯誤訊息的空間
		if (!StringUtils.hasText(name)) { // 防呆：若輸入的名稱為空
			error.setMessage("輸入資料不得為空"); // 將訊息設定給error
			errorList.add(error); // 把 error 加入至清單
			return errorList; // 回傳裝錯誤訊息的清單
		}
		if(!bookSystemDao.existsById(name)) { // 防呆：若輸入錯誤的書名
			error.setMessage("無此書籍"); // 將訊息設定給error
			errorList.add(error); // 把 error 加入至清單
			return errorList; // 回傳裝錯誤訊息的清單
		}
		if(price < 0) { // 防呆：若輸入的價格小於0
			error.setMessage("輸入價格錯誤"); // 將訊息設定給error
			errorList.add(error); // 把 error 加入至清單
			return errorList; // 回傳裝錯誤訊息的清單
		}
		for (BookSystem item : bookList) { // 遍歷
			if (item.getName().equals(name)) { // 若輸入名稱比對相同
				BookListResponse resBook = new BookListResponse(); // new出放書的空間
				resBook.setName(item.getName()); // 取出名稱，放進resBook
				resBook.setIsbn(item.getIsbn()); // 取出isbn，放進resBook
				resBook.setWriter(item.getWriter()); // 取出作者，放進resBook
				item.setPrice(price); // 設定新價格
				resBook.setPrice(item.getPrice()); // 取出新價格，放進resBook
				resBook.setStock(item.getStock()); // 取出庫存，放進resBook
				bookSystemDao.save(item); // 資料庫儲存資訊
				resAllBook.add(resBook); // 將全部資料加到包裹所有書的空間
			}
		}
		return resAllBook; // 回傳包裹所有書的空間
	}
	
	// 書籍銷售
	@Override
	public BuyingResponse buyingBook(BookSystemRequest request) {
		Map<String, Integer> orderBook = request.getOrderMap(); // 將輸入的資料賦予給 oderBook
		int totalPrice = 0; // 總金額
		List<BuyingResponse> AllBook = new ArrayList<>(); // new 出包裹所有書的空間
		for(Entry<String, Integer> item : orderBook.entrySet()) { // 遍歷 Map
			String bookItem = item.getKey(); // 將輸入的名稱設定給 bookItem
			Optional<BookSystem> bookOp = bookSystemDao.findById(bookItem); // 找出資料庫裡"輸入的名稱"的資訊 -> bookOp
			BookSystem book = bookOp.get(); // 將"輸入的名稱"的所有資訊取出 -> book
			if(!bookOp.isPresent()) { // 防呆：如果書籍不存在
				return new BuyingResponse("書籍不存在"); // 回傳"書籍不存在"
			}
			int price = book.getPrice(); // price = "輸入的名稱"的價格
			int itemTotalPrice = price * item.getValue(); // itemTotalPrice = 價格＊輸入的數量
			totalPrice += itemTotalPrice; // totalPrice(總金額) 加上 itemTotalPrice
			book.setSales(book.getSales() + item.getValue()); // 設定新銷售量 = 原銷量 + 購買數量
			if(item.getValue() > book.getStock()) {  // 防呆：如果購買量大於庫存量
				return new BuyingResponse("庫存不足"); // 回傳"庫存不足"
			}
			book.setStock(book.getStock() - item.getValue()); // 設定新庫存量 = 原庫存 - 購買數量
			bookSystemDao.save(book); // 資料庫儲存新資訊
			BuyingResponse bookList = new BuyingResponse(); // new 出放書的空間
			bookList.setName(book.getName()); // 取出名稱，放進 bookList
			bookList.setAmount(item.getValue()); // 從輸入的 Map 取出 Value(購買數量)，放進bookList
			bookList.setTotal(totalPrice);; // 總金額，放進 bookList
			bookList.setIsbn(book.getIsbn()); // 取出isbn，放進 bookList
			bookList.setWriter(book.getWriter()); // 取出作者，放進 bookList
			bookList.setPrice(book.getPrice()); // 取出價格，放進 bookList
			AllBook.add(bookList); // 將 bookList 資料加到包裹所有書的空間

		}
		return new BuyingResponse(AllBook); // 回傳包裹所有書的空間
	}

	// 暢銷書排行榜
	@Override
	public List<BookRankingResponse> bookRanking(int sales) {
		List<BookSystem> bookList = bookSystemDao.findTop5ByOrderBySalesDesc(); // 用bookList裝資料庫裡"銷售"的資料
		List<BookRankingResponse> resAllBook = new ArrayList<>(); // new出包裹所有書的空間
		for (BookSystem item : bookList) { // 遍歷
			BookRankingResponse resBook = new BookRankingResponse(); // new出放書的空間
			resBook.setName(item.getName()); // 取出名稱，放進resBook
			resBook.setIsbn(item.getIsbn()); // 取出isbn，放進resBook
			resBook.setWriter(item.getWriter()); // 取出作者，放進resBook
			resBook.setPrice(item.getPrice()); // 取出價格，放進resBook
			resAllBook.add(resBook); // 將全部資料加到包裹所有書的空間
		}
		return resAllBook; // 回傳包裹所有書的空間
	}
	}
