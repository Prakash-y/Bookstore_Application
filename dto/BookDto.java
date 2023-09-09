package com.bookstore.bookstoreapplication.dto;


import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BookDto
{
	@Pattern(regexp="^[A-Z]{1}[a-zA-Z]{3,}$", message=" Name should be as per standard")
	private String bookName;
	@Pattern(regexp="^[A-Z]{1}[a-zA-Z]{3,}$", message=" Auther name should be as per standard")
	private String autherName;
	private String bookDescription;
	private String bookImg;
	@Min(value=100, message="book price shouble be more than 100")
	private int price;
	private int quantity;
}
