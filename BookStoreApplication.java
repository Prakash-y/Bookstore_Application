package com.bookstore.bookstoreapplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.bookstoreapplication.model.UserModel;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
		System.out.println("Project Running....");
	}
	
	@Bean
	public ModelMapper getModelMapper()
	{
		return new ModelMapper();
	}
	
	@Bean
	public UserModel getUser()
	{
		return new UserModel();
	}

}
