package org.martynas.realestate_api.advice;

import org.martynas.realestate_api.exception.OwnerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class OwnerNotFoundAdvice {

    @ExceptionHandler(OwnerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String OwnerNotFoundExceptionHandler(OwnerNotFoundException ex) {
        return ex.getMessage();
    }

}
