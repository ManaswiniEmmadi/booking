package com.example.eventbooking.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    
	private String message;
    private String details;
    private LocalDateTime timestamp;
    
    
}
