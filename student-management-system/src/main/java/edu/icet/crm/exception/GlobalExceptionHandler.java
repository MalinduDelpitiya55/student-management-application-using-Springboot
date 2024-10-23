package edu.icet.crm.exception;

import edu.icet.crm.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    ResponseEntity<ErrorResponse> handleCustomerNotFoundException(StudentNotFoundException ex){
        ErrorResponse errorResponse = ErrorResponse.builder().error(ex.getMessage()).build();
        return ResponseEntity.ok().body(errorResponse);
    }
}
