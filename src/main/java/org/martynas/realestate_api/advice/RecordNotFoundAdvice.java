package org.martynas.realestate_api.advice;

import org.martynas.realestate_api.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RecordNotFoundAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String RecordNotFoundExceptionHandler(RecordNotFoundException ex) {
        return ex.getMessage();
    }

}
