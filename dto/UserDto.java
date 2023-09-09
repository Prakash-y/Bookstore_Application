package com.bookstore.bookstoreapplication.dto;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	@Pattern(regexp="^[A-Z]{1}[a-zA-Z]{3,}$", message=" First name should be as per standard")
	private String firstName;
	@Pattern(regexp="^[A-Z]{1}[a-zA-Z]{3,}$", message=" Last name should be as per standard")
	private String lastName;
	@Pattern(regexp="^[A-Za-z0-9+_.-]+@(.+)$", message="Email id should be as per standard")
	private String email;
	@NotEmpty
	private String address;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date dob;
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message="Password should be as per standard")
	private String password;
}

