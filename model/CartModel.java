package com.bookstore.bookstoreapplication.model;

import java.util.List;

import com.bookstore.bookstoreapplication.dto.CartDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CartModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int cartId;
	
	private int userId;
	private int bookId;
	private int quantity;
	
	public CartModel(CartDto model)
	{
		this.userId=model.getUserId();
		this.bookId=model.getBookId();
		this.quantity=model.getQuantity();
	}
}
