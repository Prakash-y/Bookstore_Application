package com.bookstore.bookstoreapplication.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto 
{

	private int userId;
	private int bookId;
	private int quantity;
}
