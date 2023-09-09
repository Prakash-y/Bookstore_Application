package com.bookstore.bookstoreapplication.service;

import java.util.List;

import com.bookstore.bookstoreapplication.dto.LoginDto;
import com.bookstore.bookstoreapplication.dto.UserDto;
import com.bookstore.bookstoreapplication.model.UserModel;

public interface IUserService {

	UserModel register(UserDto model);

	List<UserModel> findAll();

	UserModel findById(int id);

	String deleteById(int id);

	UserModel findByEmail(String email);

	UserModel updateUserByEmail(String email, UserDto model);

	UserModel findByToken(String token);

	String loginUser(LoginDto model);

	String forgotPassword(LoginDto model);

	String resetPassword(String newPassword, LoginDto model);

	UserModel findByTokenId(String token, int id);

	UserModel updateUserById(int id, UserDto model);


}
