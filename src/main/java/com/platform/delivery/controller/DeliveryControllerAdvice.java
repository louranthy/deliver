package com.platform.delivery.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.platform.delivery.error.ApiError;

@RestControllerAdvice
public class DeliveryControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ApiError.class)
	@ResponseBody
    public ResponseEntity<Object> handleException(ApiError e) {
        return ResponseEntity.status(e.getStatus()).body(e.getError());
    }     
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		 List<ObjectError> objectErrors =  ex.getBindingResult().getAllErrors();
		 List<String> errorMessages = new ArrayList<>();
		 for(ObjectError objectError: objectErrors) {
			 errorMessages.add(objectError.getDefaultMessage());
		 }
		  return ResponseEntity.status(status).body(errorMessages);
	}
}	
