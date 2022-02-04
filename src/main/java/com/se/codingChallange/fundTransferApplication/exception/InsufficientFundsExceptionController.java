package com.se.codingChallange.fundTransferApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class InsufficientFundsExceptionController {
	@ExceptionHandler(value = InsufficientFundsException.class)
	   public ResponseEntity<Object> exception(InsufficientFundsException exception) {
	      return new ResponseEntity<>("No sufficient funds to transfer", HttpStatus.BAD_REQUEST);
	   }
}
