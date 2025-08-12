package com.example.course.exception;

import com.example.course.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleCourseNotFound(CourseNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(Instant.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(Instant.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EnrollmentAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleEnrollmentAlreadyExists(EnrollmentAlreadyExistsException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(Instant.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(Instant.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
