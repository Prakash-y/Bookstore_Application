package com.bookstore.bookstoreapplication.service;

import java.util.List;

import com.bookstore.bookstoreapplication.dto.BookDto;
import com.bookstore.bookstoreapplication.model.BookModel;



public interface IBookService {

	BookModel insertBook(BookDto bookDto);

	List<BookModel> getAll();

	BookModel getById(int id);

	String deleteBookById(int id);

	BookModel getByBookName(String bookName);

	BookModel updatebookById(int id, BookDto dto);

	List<BookModel> getSortedAsce();

	List<BookModel> getSortedDsce();

	BookModel updateQuantity(int id, int quantity);

}
