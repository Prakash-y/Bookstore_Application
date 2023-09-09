package com.bookstore.bookstoreapplication.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bookstore.bookstoreapplication.dto.ResponseDto;


public class BookHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<ResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
	 {
	  List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
	  List<String> errMsg = errorList.stream()
	    .map(objErr -> objErr.getDefaultMessage())
	    .collect(Collectors.toList());
	  ResponseDto response = new ResponseDto(errMsg,"Exception while processing REST Request");
	  return new ResponseEntity<ResponseDto>(response,HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(BookException.class)
	 public ResponseEntity<ResponseDto> handleAddressBookException (BookException exception){
	 
	  ResponseDto response = new ResponseDto("Exception while processing REST Request",exception.getMessage());
	  return new ResponseEntity<ResponseDto>(response,HttpStatus.BAD_REQUEST);
	 }
}
