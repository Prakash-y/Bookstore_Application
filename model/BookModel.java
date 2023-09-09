package com.bookstore.bookstoreapplication.model;

import java.util.List;

import com.bookstore.bookstoreapplication.dto.BookDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int bookId;
	private String bookName;
	private String autherName;
	private String bookDescription;
	private String bookImg;
	private int price;
	private int quantity;
	
	public BookModel(BookDto model)
	{
		this.bookName=model.getBookName();
		this.autherName=model.getAutherName();
		this.bookDescription=model.getBookDescription();
		this.bookImg=model.getBookImg();
		this.price=model.getPrice();
		this.quantity=model.getQuantity();
	}
}
