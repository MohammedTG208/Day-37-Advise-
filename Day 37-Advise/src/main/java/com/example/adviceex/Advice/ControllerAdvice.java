package com.example.adviceex.Advice;

import com.example.adviceex.Api.ApiException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLIntegrityConstraintViolationException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    //NOTES:
    // I did not mention where I use to get the Exception
    // because it will work for all method.

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    //thrown when an attempt to execute an SQL statement fails to map the given data in
    // any table or controller response
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    //when a method argument fails validation perhaps as a result of @Valid style validation,
    // or perhaps because it is required
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());
    }

    //it is Exception that indicates that a method argument has not the expected type
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    //occurs when there is a mismatch between the expected and method had arguments.
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    //this for occurs when trying to use a variable that does not point to an object and refers to nothing or null
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity NullPointerException(NullPointerException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    // Raised when can not find a resource or path.
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity NoResourceFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    //this exception handle the duplicate unique value in DB
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    //this Exception if I want to getMapping as to get "allUser" but I selected 'Put' from 'postman'
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(400).body(e.getDetailMessageArguments());
    }
}
