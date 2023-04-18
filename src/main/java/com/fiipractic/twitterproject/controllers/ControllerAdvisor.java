package com.fiipractic.twitterproject.controllers;

import com.fiipractic.twitterproject.exceptions.EntityNotFoundException;
import com.fiipractic.twitterproject.exceptions.LikeFoundException;
import com.fiipractic.twitterproject.exceptions.LikeNotFoundException;
import com.fiipractic.twitterproject.exceptions.UsernameExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvisor {
    @ResponseBody
    @ExceptionHandler({UsernameExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String usedEmailHandler(RuntimeException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundEntityHandler(RuntimeException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({LikeNotFoundException.class, LikeFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String likeExceptionHandler(RuntimeException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String badCredentialsHandler(RuntimeException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({HttpClientErrorException.Unauthorized.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unauthorizedHandler(RuntimeException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
