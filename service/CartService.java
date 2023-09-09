package com.bookstore.bookstoreapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bookstoreapplication.dto.CartDto;
import com.bookstore.bookstoreapplication.exception.CartException;
import com.bookstore.bookstoreapplication.model.CartModel;
import com.bookstore.bookstoreapplication.repository.CartRepository;
import com.bookstore.bookstoreapplication.util.TokenUtil;


@Service
public class CartService implements ICartService{

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	TokenUtil tokenUtil;

	//Insert cart
	@Override
	public CartModel insert(CartDto cartDto) {
		CartModel user = new CartModel(cartDto);
		cartRepository.save(user);
		String token = tokenUtil.createToken(user.getCartId());
	    System.out.println("Token: "+token);
		return user;
	}

	//Get all cart
	@Override
	public List<CartModel> getAll() {
		
		return cartRepository.findAll();
	}

	//Get cart by id
	@Override
	public CartModel getById(int id) {
		Optional<CartModel> user = cartRepository.findById(id);
		if(user.isPresent())
		{
			return user.get();
		}
		throw new CartException("Cart not found!");
	}

	//Delete cart by id
	@Override
	public String deleteCartById(int id) {
		
		Optional<CartModel> user = cartRepository.findById(id);
		if(user.isPresent())
		{
			cartRepository.deleteById(id);
			return "Cart deleted!";
		}
		throw new CartException("Cart not found!");
	}
	
    //Update cart by id
	@Override
	public CartModel updateCartById(int id,CartDto dto) {
		Optional<CartModel> user = cartRepository.findById(id);
		if(user.isPresent())
		{
			user.get().setBookId(dto.getBookId());
			user.get().setUserId(dto.getUserId());
			user.get().setQuantity(dto.getQuantity());
		cartRepository.save(user.get());
		return user.get();
		}
		else
		{
			throw new CartException("Cart not found!");
		}
	}

	//update quantity by id
	@Override
	public CartModel updateQuantity(int id, int quantity) {
		Optional<CartModel> user = cartRepository.findById(id);
		if(user.isPresent())
		{
			user.get().setQuantity(quantity);
			cartRepository.save(user.get());
			return user.get();
		}
		else throw new CartException("Cart not found");
	}
}
