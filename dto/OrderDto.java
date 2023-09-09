package com.bookstore.bookstoreapplication.dto;

import java.util.Date;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {

	private Date LocalDate;
	private int totalPrice;
	private int quantity;
	private String address;
	private int userId;
	private int bookId;
	private boolean cancel;
}
