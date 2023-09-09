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

import com.bookstore.bookstoreapplication.dto.CartDto;
import com.bookstore.bookstoreapplication.dto.ResponseDto;
import com.bookstore.bookstoreapplication.model.CartModel;
import com.bookstore.bookstoreapplication.service.ICartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController 
{
    @Autowired
	ICartService cartService;
    
    //Insert cart
    @PostMapping("/insert")
	public ResponseEntity<ResponseDto> insert(@RequestBody CartDto cartDto)
	{
		CartModel user = cartService.insert(cartDto);
		ResponseDto response = new ResponseDto(user,"Added.");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.CREATED);		
	}
    
    //All carts
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> findAll()
	{
		List<CartModel> user = cartService.getAll();
		ResponseDto response = new ResponseDto(user,"Getting all cart! ");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.ACCEPTED);		
	}
    
    //Get cart by id
    @GetMapping("/getByid/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable int id )
	{
		CartModel user = cartService.getById(id);
		ResponseDto response = new ResponseDto(user,"Getting cart by id!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
	}
    
    //Delete cart by id
    @DeleteMapping("/deleteById/{id}")
	public ResponseEntity<ResponseDto> deleteCart(@PathVariable int id)
	{
		String user = cartService.deleteCartById(id);
		ResponseDto response = new ResponseDto(user,"Deleting cart by id!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.ACCEPTED);		
	}
    
    //Update cart by id
    @PutMapping("/updateCartById/{id}")
	public ResponseEntity<ResponseDto> updateCartByid(@PathVariable int id, @RequestBody CartDto dto)
	{
		CartModel user = cartService.updateCartById(id, dto);
		ResponseDto response = new ResponseDto(user, "updating cart by id!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
    
    //Update quantity by id
    @PutMapping("/updateQuantity/{id}")
	public ResponseEntity<ResponseDto> updateQuantityByid(@PathVariable int id, @RequestHeader int quantity)
	{
		CartModel user = cartService.updateQuantity(id, quantity);
		ResponseDto response = new ResponseDto(user, "Updating quantity by id!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
	
    
    
    
}

