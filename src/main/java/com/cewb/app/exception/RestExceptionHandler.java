package com.cewb.app.exception;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cewb.app.utility.AppUtility;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = AccessDeniedException.class)
	public void handleConflict(HttpServletResponse response) throws IOException {
		response.sendError(403, "You do not have permission to do the action.");
	}

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

	@ExceptionHandler
	public ResponseEntity<Object> handleException(MethodArgumentNotValidException exc) {
		exc.printStackTrace();

		Map<String, String> messages = new HashMap<>();

		exc.getBindingResult().getAllErrors().forEach((error) -> {
			String field = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			messages.put(field, message);
		});

		Map<String, Object> errors = new HashMap<>();
		errors.put("timestamp", new Date());
		errors.put("status", HttpStatus.BAD_REQUEST);
		errors.put("errors", Arrays.asList(messages));

		// return ResponseEntity
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
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
