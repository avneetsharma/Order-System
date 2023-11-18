package com.aks.order.mgmt.customer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

	
	private static final Logger logger = LogManager.getLogger(ControllerExceptionHandler.class);
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
    	logger.error("Exception caught in handleRuntimeException :  {} " , ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){
    	logger.error("Exception caught in handleRuntimeException :  {} " , ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }


}
