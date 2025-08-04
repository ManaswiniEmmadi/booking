package com.example.eventbooking.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleBookingNotFound(BookingNotFoundException ex) {
        ExceptionResponse error = new ExceptionResponse(ex.getMessage(), "Booking Not Found", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationError(MethodArgumentNotValidException ex) {
        String msg = ex.getFieldErrors().stream()
                       .map(err -> err.getField() + ": " + err.getDefaultMessage())
                       .collect(Collectors.joining(", "));
        ExceptionResponse error = new ExceptionResponse(msg, "Validation Failed", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception ex) {
    	ExceptionResponse error = new ExceptionResponse(ex.getMessage(), "Internal Server Error", LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
