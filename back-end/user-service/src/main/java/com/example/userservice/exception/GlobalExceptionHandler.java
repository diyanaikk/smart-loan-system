//catches validation error and returns proper message


package com.example.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
//applies to all controllers
//handles exception globally
//automatically returns JSON response


public class GlobalExceptionHandler {

    // ONLY handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //this gets triggered when @Valid fails in the Controller
    
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        //creating error map
        //example:
		/*
		 * { 
		 * 		"email": "must be valid", 
		 * 		"name": "cannot be empty" 
		 * }
		 */

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        
        //Sends:

				/*
				 * ->  JSON error messages 
				 * ->  HTTP status = 400 (Bad Request)
				 */
    }
}