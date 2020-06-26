package org.martynas.realestate_api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class ConstraintViolationAdvice {

//    @ExceptionHandler(ConstraintViolationException.class)
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, Object> ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
            Map<String, Object> response = new HashMap<>();
            Map<String, String> errors = new HashMap<>();
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                errors.put(constraintViolation.getPropertyPath().toString() , constraintViolation.getMessage());
            }

            response.put("constraint violation errors", errors);

        return response;
    }
}
