package com.bookstore.bookstoreapplication.model;


import java.util.Date;

import com.bookstore.bookstoreapplication.dto.UserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private Date dob;
	private String password;
	
	public UserModel(UserDto model)
	{
		this.firstName=model.getFirstName();
		this.lastName=model.getLastName();
		this.email=model.getEmail();
		this.address=model.getAddress();
		this.dob=model.getDob();
		this.password=model.getPassword();
	}
}
