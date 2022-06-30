package com.example.tradestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * @author Sapna Girdhani
 *
 */
@ControllerAdvice
public class CustomizedExceptionHandler {
	
	@ExceptionHandler(InvalidTradeException.class)
	   public ResponseEntity<String> exception(InvalidTradeException exception) {
	      return new ResponseEntity<>(exception.getMsg(),HttpStatus.NOT_ACCEPTABLE);
	   }

}
