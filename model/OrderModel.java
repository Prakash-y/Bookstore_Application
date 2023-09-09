package com.bookstore.bookstoreapplication.model;

import java.util.Date;
import java.util.List;

import com.bookstore.bookstoreapplication.dto.OrderDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int orderId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date localDate;
	private int totalPrice;
	private int quantity;
	private String address;
	private int userId;
	private int bookId;
	private boolean cancel;
	
	public OrderModel(OrderDto model)
	{
		this.localDate=model.getLocalDate();
		this.totalPrice=model.getTotalPrice();
		this.quantity=model.getQuantity();
		this.address=model.getAddress();
		this.userId=model.getUserId();
		this.bookId=model.getBookId();
}
}
