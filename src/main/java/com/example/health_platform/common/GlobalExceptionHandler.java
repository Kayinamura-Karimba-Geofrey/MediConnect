package com.example.health_platform.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntime(RuntimeException ex) {
        // Log the exception details on the server (using stdout for now, could use a logger)
        System.err.println("Runtime Exception: " + ex.getMessage());
        ex.printStackTrace();
        
        ApiResponse<Object> r = new ApiResponse<>(false, null, "An internal error occurred. Please try again later.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(r);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAll(Exception ex) {
        ApiResponse<Object> r = new ApiResponse<>(false, null, "Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(r);
    }
}
