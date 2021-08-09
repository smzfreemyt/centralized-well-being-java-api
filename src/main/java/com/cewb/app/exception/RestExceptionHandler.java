package com.cewb.app.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cewb.app.utility.AppUtility;

@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleException(EntityNotFoundException ex) {
        // Create RestErrorResponse
        RestErrorResponse error = new RestErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleException(ConstraintViolationException exc) {
		List<String> errors = exc.getConstraintViolations().stream().map(violation -> {
				return violation.getMessage();
			}).collect(Collectors.toList());
		
        // Create RestErrorResponse
    	RestErrorResponse error = new RestErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.value(),
                AppUtility.arrayToString(errors, ", "),
                System.currentTimeMillis()
        );

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }
	
	@ExceptionHandler
	public ResponseEntity<RestErrorResponse> handleException(TransactionSystemException exc) throws Throwable {
		if(exc.getRootCause() instanceof ConstraintViolationException) {
			return handleException((ConstraintViolationException) exc.getRootCause());
		}
		return handleException(new Exception(exc.getRootCause()));
	}
	
	//Generic error response for exceptions
    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleException(Exception exc) {
    	exc.printStackTrace();
        // Create RestErrorResponse
    	RestErrorResponse error = new RestErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis()
        );

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
