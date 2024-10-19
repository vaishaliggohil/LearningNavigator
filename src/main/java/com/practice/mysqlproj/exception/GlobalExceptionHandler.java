package com.practice.mysqlproj.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(404).body(message);
    }
}
