package com.bookstore.bookstoreapplication.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bookstoreapplication.dto.OrderDto;
import com.bookstore.bookstoreapplication.exception.OrderException;
import com.bookstore.bookstoreapplication.model.BookModel;
import com.bookstore.bookstoreapplication.model.OrderModel;
import com.bookstore.bookstoreapplication.model.UserModel;
import com.bookstore.bookstoreapplication.repository.BookRepository;
import com.bookstore.bookstoreapplication.repository.IUserRepository;
import com.bookstore.bookstoreapplication.repository.OrderRepository;
import com.bookstore.bookstoreapplication.util.EmailSender;

@Service
public class OrderService implements IOrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	EmailSender emailSender;
	
	@Autowired
	UserModel userModel;

	@Autowired
	IUserRepository userRepo;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	//Insert order
	@Override
	public OrderModel insert(OrderDto orderDto) {
		
		OrderModel user = new OrderModel(orderDto);
		orderRepository.save(user);
		
	    UserModel userToSendEmailTo = userRepo.findByUserId(orderDto.getUserId());
	    System.out.println(userToSendEmailTo);
		
		emailSender.sendEmail(userToSendEmailTo.getEmail(),"Email for BookStoreappliction", "Hello, Your order has been placed "
		+"\n\nYour order id is :"+user.getUserId()+"\nBook id :"+user.getBookId()+"\nAddress :"+user.getAddress()+
		"\nTotal Price :"+user.getTotalPrice()+"\nThank You for using BookStore!");
		return user;
	}

	//List of all orders
	@Override
	public List<OrderModel> getAll() {
		
		return orderRepository.findAll();
	}

	//find order by id
	@Override
	public OrderModel findById(int id) {
		Optional<OrderModel> user = orderRepository.findById(id);
		if(user.isPresent())
		{
			return user.get();
		}
		else
		{
			throw new OrderException("Not found");
		}
	}

	//Delete order by id
	@Override
	public String deleteOrderById(int id) {
		Optional<OrderModel> user = orderRepository.findById(id);
		if(user.isPresent())
		{
			orderRepository.deleteById(id);
			return "Order Cancel!";
		}
		throw new OrderException("Not found");
	}

	//update order by id 
	@Override
	public OrderModel updateOrderById(int id, OrderDto dto) {
		Optional<OrderModel> user = orderRepository.findById(id);
		if(user.isPresent())
		{
			OrderModel mapper = modelMapper.map(user, OrderModel.class);
			mapper.setOrderId(id);
			
//			user.get().setAddress(dto.getAddress());
//			user.get().setBookId(dto.getBookId());
//			user.get().setQuantity(dto.getQuantity());
//			user.get().setTotalPrice(dto.getTotalPrice());
//			user.get().setUserId(dto.getUserId());
//			user.get().setLocalDate(dto.getLocalDate());
			
			orderRepository.save(mapper);
			 UserModel userToSendEmailTo = userRepo.findByUserId(dto.getUserId());
			emailSender.sendEmail(userToSendEmailTo.getEmail(),"Email for BookStoreappliction", "Hello, Your order has been updated! "
					+"\n\nYour order id is :"+user.get().getUserId()+"\nBook id :"+user.get().getBookId()+"\nAddress :"+user.get().getAddress()+
					"\nTotal Price :"+user.get().getTotalPrice()+"\nThank You for using BookStore!");
			return mapper;
		}
		else throw new OrderException("Order not found");
	}

	//Cancel order by id
	@Override
	public OrderModel cancelOrderById(int id) {
		Optional<OrderModel> user = orderRepository.findById(id);
		if(user.isPresent())
		{
			Optional<BookModel> book = bookRepository.findById(user.get().getBookId());
			book.get().setQuantity(0);
			user.get().setCancel(true);
			orderRepository.save(user.get());
			 return user.get();
		}
		else throw new OrderException("Order Not found!");
	}
}
