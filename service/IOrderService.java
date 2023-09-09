package com.bookstore.bookstoreapplication.service;

import java.util.List;

import com.bookstore.bookstoreapplication.dto.OrderDto;
import com.bookstore.bookstoreapplication.model.OrderModel;


public interface IOrderService {

	OrderModel insert(OrderDto orderDto);

	List<OrderModel> getAll();

	OrderModel findById(int id);

	String deleteOrderById(int id);

	OrderModel updateOrderById(int id, OrderDto dto);
	
    OrderModel cancelOrderById(int id);

	

}
