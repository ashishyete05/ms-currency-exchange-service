package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(value = InvalidDataProvidedException.class)
	public final ResponseEntity<Object> handleInvalidDataProvidedException(Exception ex, WebRequest request) throws Exception {
		 ExceptionResponse exceptionResponse = new  ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		 return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		 ExceptionResponse exceptionResponse = new  ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		 return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
