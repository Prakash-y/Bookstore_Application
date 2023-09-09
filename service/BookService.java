package com.bookstore.bookstoreapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.bookstore.bookstoreapplication.dto.BookDto;
import com.bookstore.bookstoreapplication.exception.BookException;
import com.bookstore.bookstoreapplication.model.BookModel;
import com.bookstore.bookstoreapplication.repository.BookRepository;
import com.bookstore.bookstoreapplication.util.TokenUtil;

import jakarta.validation.Valid;

@Service
public class BookService implements IBookService {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	TokenUtil tokenUtil;
	
    //Insert book
	@Override
	public BookModel insertBook(@Valid BookDto bookDto) {
		BookModel user = new BookModel(bookDto);
	    bookRepository.save(user);
	    String token = tokenUtil.createToken(user.getBookId());
	    System.out.println("Token: "+token);
	    return user;
	}

	//List of books
	@Override
	public List<BookModel> getAll() 
	{
		return bookRepository.findAll();
	}

	//Book by id
	@Override
	public BookModel getById(int id) {
		Optional<BookModel> user = bookRepository.findById(id);
		if(user.isPresent())
		{
			return user.get();
		}
		throw new BookException("Book not found!");
	}

	//Delete book by id
	@Override
	public String deleteBookById(int id) {
		Optional<BookModel> user = bookRepository.findById(id);
		if(user.isPresent())
		{
			bookRepository.deleteById(id);
			return "Book deleted!";
		}
		else throw new BookException("Book not found!");
		
	}

	//Get book by name 
	@Override
	public BookModel getByBookName(String bookName) {
		Optional<BookModel> user = bookRepository.findByBookName(bookName);
		if(user.isPresent())
		{
			return user.get();
		}
		else throw new BookException("Book Not Found");
	}

	//update book by id
	@Override
	public BookModel updatebookById(int id, BookDto dto) {
		
		Optional<BookModel> user = bookRepository.findById(id);
		if(user != null)
		{
			user.get().setBookName(dto.getBookName());
			user.get().setAutherName(dto.getAutherName());
			user.get().setBookDescription(dto.getBookDescription());
			user.get().setBookImg(dto.getBookImg());
			user.get().setPrice(dto.getPrice());
			user.get().setQuantity(dto.getQuantity());
			bookRepository.save(user.get());
			return user.get();
		}
		else throw new BookException("Book not found");
	}

	//Sort books in ascending order
	@Override
	public List<BookModel> getSortedAsce() {
		
		return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "bookId"));
	}

	//Sort book in descending order
	@Override
	public List<BookModel> getSortedDsce() {
		
		return bookRepository.findAll(Sort.by(Sort.Direction.DESC, "bookId"));
	}

	//Update quantity by id
	@Override
	public BookModel updateQuantity(int id, int quantity) {
		Optional<BookModel> user = bookRepository.findById(id);
		if(user.isPresent())
		{
			user.get().setQuantity(quantity);
			bookRepository.save(user.get());
			return user.get();
		}
		else throw new BookException("Book not found");
	}
	
}
