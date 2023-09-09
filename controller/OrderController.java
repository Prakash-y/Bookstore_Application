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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.bookstoreapplication.dto.OrderDto;
import com.bookstore.bookstoreapplication.dto.ResponseDto;
import com.bookstore.bookstoreapplication.model.OrderModel;
import com.bookstore.bookstoreapplication.service.IOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	IOrderService orderService;
	
	//Insert order
	@PostMapping("/insert")
	public ResponseEntity<ResponseDto> insert(@RequestBody OrderDto orderDto)
	{
		OrderModel user = orderService.insert(orderDto);
		ResponseDto response = new ResponseDto(user,"Book order succesful.");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.CREATED);		
	}
	
	//Get all orders
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> findAll()
	{
		List<OrderModel> user = orderService.getAll();
		ResponseDto response = new ResponseDto(user,"List of orders! ");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.ACCEPTED);		
	}
    
    //Get order by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable int id)
    {
    	OrderModel user = orderService.findById(id);
		ResponseDto response = new ResponseDto(user,"Order by id!.");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
    }
    
    //Delete order by id
    @DeleteMapping("/deleteOrderById/{id}")
   	public ResponseEntity<ResponseDto> deleteOrder(@PathVariable int id)
   	{
   		String user = orderService.deleteOrderById(id);
   		ResponseDto response = new ResponseDto(user,"Deleting order by id!");
   		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
   	}
    
    //Update order by id
    @PutMapping("/updateOrderById/{id}")
	public ResponseEntity<ResponseDto> updateCartByid(@PathVariable int id, @RequestBody OrderDto dto)
	{
		OrderModel user = orderService.updateOrderById(id, dto);
		ResponseDto response = new ResponseDto(user, "updating Order by id!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
    
    //Cancel order by id
    @GetMapping("/cancelOrderById/{id}")
   	public ResponseEntity<ResponseDto> cancelOrder(@PathVariable int id)
   	{
   		OrderModel user = orderService.cancelOrderById(id);
   		ResponseDto response = new ResponseDto(user,"Order Cancel!");
   		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
   	}
    
    
}
