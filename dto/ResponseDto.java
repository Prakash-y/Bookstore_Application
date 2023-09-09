package com.bookstore.bookstoreapplication.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto 
{

    private String message;
	
	private Object data;
	
	public ResponseDto(Object data, String message)
	{
		this.data = data;
		this.message = message;
	}
}
