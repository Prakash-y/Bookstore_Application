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

import com.bookstore.bookstoreapplication.dto.LoginDto;
import com.bookstore.bookstoreapplication.dto.ResponseDto;
import com.bookstore.bookstoreapplication.dto.UserDto;
import com.bookstore.bookstoreapplication.service.IUserService;

import jakarta.validation.Valid;

import com.bookstore.bookstoreapplication.model.UserModel;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService service;
	
	//Register
	@PostMapping("/register")
	public ResponseEntity<ResponseDto> register(@Valid @RequestBody UserDto model)
	{
		UserModel user = service.register(model);
		ResponseDto response = new ResponseDto(user,"Your account has been successfully created.");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.CREATED);		
	}
	
	//Get all users
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDto> getAll()
	{
		List<UserModel> user = service.findAll();
		ResponseDto response = new ResponseDto(user,"All users!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
	}
	
    //Get by id user
	@GetMapping("/getById/{id}")
	public ResponseEntity<ResponseDto> getById(@PathVariable int id)
	{
		UserModel user = service.findById(id);
		ResponseDto response = new ResponseDto(user,"User by id!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
	}
	
    //Delete by id user
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<ResponseDto> deleteById(@PathVariable int id)
	{
		String user = service.deleteById(id);
		ResponseDto response = new ResponseDto(user,"Deleting user by id!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.ACCEPTED);		
	}
	
	//Get by email user
	@GetMapping("/getByEmail/{email}")
	public ResponseEntity<ResponseDto> getByEmail(@PathVariable String email)
	{
		UserModel user = service.findByEmail(email);
		ResponseDto response = new ResponseDto(user,"User by email!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
	}
	
	//update user by email
	@PutMapping("/updateByEmail/{email}")
	public ResponseEntity<ResponseDto> updateByEmail(@PathVariable String email,@Valid @RequestBody UserDto model)
	{
		UserModel user = service.updateUserByEmail(email, model);
		ResponseDto response = new ResponseDto(user,"Updating user by email!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
	}
	//update user by id
	@PutMapping("/updateByid/{id}")
	public ResponseEntity<ResponseDto> updateById(@PathVariable int id, @Valid @RequestBody UserDto model)
	{
		UserModel user = service.updateUserById(id, model);
		ResponseDto response = new ResponseDto(user,"Updating user by Id!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
	}
	
	//Get by token user
	@GetMapping("/getByToken/{token}")
	public ResponseEntity<ResponseDto> getByToken(@PathVariable String token)
	{
		UserModel user = service.findByToken(token);
		ResponseDto response = new ResponseDto(user,"User by token!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
	}
	
	//Login user
	@GetMapping("/login")
	public ResponseEntity<ResponseDto> login(@RequestBody LoginDto model)
	{
		String user = service.loginUser(model);
		ResponseDto response = new ResponseDto(user,"Login User!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.ACCEPTED);		
	}
	
	//Forgot password
	@PutMapping("/forgotPassword")
	public ResponseEntity<ResponseDto> forgotPassword(@RequestBody LoginDto model)
	{
		String user = service.forgotPassword(model);
		ResponseDto response = new ResponseDto(user,"Password Updated !");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
	}
	
	//Reset password
	@PutMapping("/resetPassword")
	public ResponseEntity<ResponseDto> resetPassword( @RequestHeader String newPassword , @RequestBody LoginDto model)
	{
		String user = service.resetPassword(newPassword, model);
		ResponseDto response = new ResponseDto(user,"Password reset !");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
	}
	
	//Get by token Id
	@GetMapping("/GetByTokenId")
	public ResponseEntity<ResponseDto> getByTokenId(@PathVariable String token, int id)
	{
		UserModel user = service.findByTokenId(token,id);
		ResponseDto response = new ResponseDto(user,"User by token!");
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);		
	}
	
	
	
	
	
	
	
	
	
	
}
