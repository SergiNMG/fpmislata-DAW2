package com.fpmislata.movies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler ({
            ResourceNotFoundException.class
    })
    @ResponseBody
    public ErrorMessage notFoundRequest (Exception exception){
        return new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}
