package com.bookstore.bookstoreapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.bookstore.bookstoreapplication.dto.BookDto;
import com.bookstore.bookstoreapplication.dto.ResponseDto;
import com.bookstore.bookstoreapplication.model.BookModel;
import com.bookstore.bookstoreapplication.service.IBookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	IBookService bookService;
	
	//Inserting books
	@PostMapping("/insert")
	public ResponseEntity<ResponseDto> insert(@Valid @RequestBody BookDto bookDto)
	{
		BookModel user = bookService.insertBook(bookDto);
		ResponseDto response = new ResponseDto(user,"Book added.");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.CREATED);		
	}
	
	//Get all books
	@GetMapping("/getAllBooks")
	public ResponseEntity<ResponseDto> getAll()
	{
		List<BookModel> user = bookService.getAll();
		ResponseDto response = new ResponseDto(user,"All books!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
	}
	
	//Get by id
	@GetMapping("/getBookById/{id}")
	public ResponseEntity<ResponseDto> getBYId(@PathVariable int id)
	{
		BookModel user = bookService.getById(id);
		ResponseDto response = new ResponseDto(user, "Book by id");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.ACCEPTED);
	}
	
	//Delete book by id
	@DeleteMapping("/deleteBookById/{id}")
	public ResponseEntity<ResponseDto> deleteBook(@PathVariable int id)
	{
		String user = bookService.deleteBookById(id);
		ResponseDto response = new ResponseDto(user,"Deleting book by id!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.ACCEPTED);		
	}
	
	//Search by book name 
	@GetMapping("/searchByBookName/{bookName}")
	public ResponseEntity<ResponseDto> getByBook(@PathVariable String bookName )
	{
		BookModel user = bookService.getByBookName(bookName);
		ResponseDto response = new ResponseDto(user, "Book by Name");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
	
	//Update book by id
	@PutMapping("/updateBookById/{id}")
	public ResponseEntity<ResponseDto> updateBookByid(@PathVariable int id, @Valid @RequestBody BookDto dto)
	{
		BookModel user = bookService.updatebookById(id, dto);
		ResponseDto response = new ResponseDto(user, "updating book by id!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
	
	//Sorting books in ascending
	@GetMapping("/sortAsce")
	public ResponseEntity<ResponseDto> getSortingAsce()
	{
		List<BookModel> user = bookService.getSortedAsce();
		ResponseDto response = new ResponseDto(user,"Sotring books in ascending orders !");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
	}
	
	//Sorting books in descending
	@GetMapping("/sortDsce")
	public ResponseEntity<ResponseDto> getSortingDsce()
	{
		List<BookModel> user = bookService.getSortedDsce();
		ResponseDto response = new ResponseDto(user,"Sotring books in Descending orders !");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
	}
	
	//Update quantity by id
	@PutMapping("/updateQuantity/{id}")
	public ResponseEntity<ResponseDto> updateQuantityByid(@PathVariable int id, @RequestHeader int quantity)
	{
		BookModel user = bookService.updateQuantity(id, quantity);
		ResponseDto response = new ResponseDto(user, "Updating quantity by id!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
	
	
}
