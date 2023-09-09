
package com.bookstore.bookstoreapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bookstore.bookstoreapplication.dto.LoginDto;
import com.bookstore.bookstoreapplication.dto.UserDto;
import com.bookstore.bookstoreapplication.exception.BookException;
import com.bookstore.bookstoreapplication.model.UserModel;
import com.bookstore.bookstoreapplication.repository.IUserRepository;
import com.bookstore.bookstoreapplication.util.EmailSender;
import com.bookstore.bookstoreapplication.util.TokenUtil;

import jakarta.validation.Valid;

@Service
public class UserService implements IUserService{

	@Autowired
	IUserRepository repository;
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	EmailSender emailSender;

	//Register user
	@Override
	public UserModel register(UserDto model)
	{
		UserModel user = new UserModel(model);
		repository.save(user);
		String token = tokenUtil.createToken(user.getUserId());
		System.out.println("Token : "+token);
		emailSender.sendEmail(user.getEmail(), "Email for BookStoreApplication",
				"Hello "+user.getFirstName()+"\n\n"+"User address : "+user.getAddress()
				+"\nUser DOB :"+user.getDob()+"\nToken :"+token);
   	return user;
	}

	//List of all users
	@Override
	public List<UserModel> findAll()
	{
		return repository.findAll();
	}

    //User by id 
	@Override
	public UserModel findById(int id)
	{
		Optional<UserModel> user = repository.findById(id);
		if(user.isPresent())
		{
			return user.get();
		}
		else throw new BookException("Not Found");	
	}

   //Delete user by id
	@Override
	public String deleteById(int id) {
		Optional<UserModel> user = repository.findById(id);
		if(user.isPresent())
		{
			repository.deleteById(id);
			emailSender.sendEmail(user.get().getEmail(), "Welcome to BookStore","User details deleted "+id);
			return "Deleted Succefully!";
		}
		else
		{
			throw new BookException("ID Not Found");
		}
	}

    //Find user by email
	@Override
	public UserModel findByEmail(String email) {
		Optional<UserModel> user = repository.findByEmail(email);
		if(user.isPresent())
		{
			return user.get();
		}
		else throw new BookException("User Not Found");
	}

    //update user by email
	@Override
	public UserModel updateUserByEmail(String email, UserDto model) {
		Optional<UserModel> user = repository.findByEmail(email);
		if(user.isPresent())
		{
			user.get().setFirstName(model.getFirstName());
			user.get().setLastName(model.getLastName());
			user.get().setAddress(model.getAddress());
			user.get().setEmail(model.getEmail());
			user.get().setDob(model.getDob());
			repository.save(user.get());
			emailSender.sendEmail(user.get().getEmail(), "Email for BookStoreApplication",
					"Hello "+user.get().getFirstName()+"\n\n"+"User address : "+user.get().getAddress()
					+"\nUser DOB :"+user.get().getDob()+"\nPerson details updated"+user.get().getEmail());
			return user.get();
		}
		else throw new BookException(" User Not Found!");
	}
	
	//update user by id
	@Override
	public UserModel updateUserById(int id, UserDto model) {
		Optional<UserModel> user = repository.findById(id);
		if(user.isPresent())
		{
			user.get().setFirstName(model.getFirstName());
			user.get().setLastName(model.getLastName());
			user.get().setAddress(model.getAddress());
			user.get().setEmail(model.getEmail());
			user.get().setDob(model.getDob());
			repository.save(user.get());
			emailSender.sendEmail(user.get().getEmail(), "Email for BookStoreApplication",
					"Hello "+user.get().getFirstName()+"\n\n"+"User address : "+user.get().getAddress()
					+"\nUser DOB :"+user.get().getDob()+"\nPerson details updated"+user.get().getUserId());
			return user.get();
		}
		return null;
	}

    //find user by token
	@Override
	public UserModel findByToken(String token) {
		int userId = tokenUtil.decodeToken(token);
		return repository.findById(userId).get();
		
	}

    //login user
	@Override
	public String loginUser(LoginDto model) {
		Optional<UserModel> user = repository.findByEmail(model.getEmail());
		if(user.isPresent() && user.get().getPassword().equals(model.getPassword()))
		{
			emailSender.sendEmail(user.get().getEmail(), "Email for BookStoreApplication",
					"Hello "+user.get().getFirstName()+"Login Succesful!");
			return "Login successful.";
		}
		else throw new BookException("username or password is incorrect");
	}

    //forgot user
	@Override
	public String forgotPassword(LoginDto model) {
		Optional<UserModel> user = repository.findByEmail(model.getEmail());
		if(user.isPresent())
		{
			user.get().setPassword(model.getPassword());
			repository.save(user.get());
			return "Password changed";
		}
		else throw new BookException("Something went wrong!");
	}

   //Reset password
	@Override
	public String resetPassword(String newPassword, LoginDto model) {
		Optional<UserModel> user = repository.findByEmail(model.getEmail());
		if(user.isPresent() && user.get().getPassword().equals(model.getPassword()))
		{
			user.get().setPassword(newPassword);
			repository.save(user.get());
			return "Password reset";
		}
		else throw new BookException("Incorrect username or password");
		
	}

    //find by token id
	@Override
	public UserModel findByTokenId(String token, int id) {
		int userId = tokenUtil.decodeToken(token);
		Optional<UserModel> user = repository.findById(userId);
		
		if(user.get().getUserId() == id)
		{
			return user.get();
		}
		else throw new BookException("Not found");
	}









	
}
