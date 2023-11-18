package com.aks.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ProductExceptionHandler {
	/**
	 * 
	 * @param ex
	 * @return
	 */
	 @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
	       
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	    }

	    /**
	     * 
	     * @param ex
	     * @return
	     */
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleException(Exception ex){
	      
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	    }

}
