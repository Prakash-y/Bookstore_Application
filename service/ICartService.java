package com.bookstore.bookstoreapplication.service;

import java.util.List;

import com.bookstore.bookstoreapplication.dto.CartDto;
import com.bookstore.bookstoreapplication.model.CartModel;


public interface ICartService {

	CartModel insert(CartDto cartDto);

	List<CartModel> getAll();

	CartModel getById(int id);

	String deleteCartById(int id);

	CartModel updateCartById(int id, CartDto dto);

	CartModel updateQuantity(int id, int quantity);

}
