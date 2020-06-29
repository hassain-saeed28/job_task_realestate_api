package org.martynas.realestate_api.advice;

import org.martynas.realestate_api.exception.AddressNotFoundException;
import org.martynas.realestate_api.exception.APIError;
import org.martynas.realestate_api.exception.OwnerNotFoundException;
import org.martynas.realestate_api.exception.RecordNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class GlobalAPIExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        String exType = ex.getClass().getSimpleName();
        String exMessage = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        APIError apiError = new APIError(
                httpStatus,
                exMessage,
                exType,
                request.getDescription(false).substring(4)
        );
        return new ResponseEntity<>(apiError, new HttpHeaders(), httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        String exType = ex.getClass().getSimpleName();
        String exMessage = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        APIError apiError = new APIError(
                httpStatus,
                exMessage,
                exType,
                request.getDescription(false).substring(4)
        );
        return new ResponseEntity<>(apiError, new HttpHeaders(), httpStatus);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

        Map<String, Object> outsideDetailsMap = new HashMap<>();
        Map<String, String> detailsMap = new HashMap<>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            detailsMap.put(violation.getPropertyPath().toString(), violation.getMessage());
            outsideDetailsMap.put(violation.getRootBeanClass().getSimpleName().toLowerCase(), detailsMap);
        }

        String exType = ex.getClass().getSimpleName();
        String exMessage = "Validation failed, see details for constrain violation list";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        APIError apiError = new APIError(
                httpStatus,
                exMessage,
                exType,
                outsideDetailsMap,
                request.getDescription(false).substring(4)
        );
        return new ResponseEntity<>(apiError, new HttpHeaders(), httpStatus);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String exType = ex.getClass().getSimpleName();
        String exMessage = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        APIError apiError = new APIError(
                httpStatus,
                exMessage,
                exType,
                request.getDescription(false).substring(4)
        );
        return new ResponseEntity<>(apiError, new HttpHeaders(), httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        String exType = ex.getClass().getSimpleName();
        String exMessage = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        APIError apiError = new APIError(
                httpStatus,
                exMessage,
                exType,
                request.getDescription(false).substring(4)
        );
        return new ResponseEntity<>(apiError, new HttpHeaders(), httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<String> supportedMethods = new ArrayList<>();
        Map<String, Object> detailsMap = new HashMap<>();
        Objects.requireNonNull(ex.getSupportedHttpMethods()).forEach(method -> supportedMethods.add(method.toString()));
        detailsMap.put("supportedMethods", supportedMethods);

        String exType = ex.getClass().getSimpleName();
        String exMessage = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        APIError apiError = new APIError(
                httpStatus,
                exMessage,
                exType,
                detailsMap,
                request.getDescription(false).substring(4)
        );
        return new ResponseEntity<>(apiError, new HttpHeaders(), httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<String> supportedMediaTypes = new ArrayList<>();
        Map<String, Object> detailsMap = new HashMap<>();
        ex.getSupportedMediaTypes().forEach(mediaType -> supportedMediaTypes.add(mediaType.toString()));
        detailsMap.put("supportedMediaTypes", supportedMediaTypes);

        String exType = ex.getClass().getSimpleName();
        String exMessage = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        APIError apiError = new APIError(
                httpStatus,
                exMessage,
                exType,
                detailsMap,
                request.getDescription(false).substring(4)
        );
        return new ResponseEntity<>(apiError, new HttpHeaders(), httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        String exType = ex.getClass().getSimpleName();
        String exMessage = (Objects.requireNonNull(ex.getMessage())).substring(0, ex.getMessage().indexOf(";"));
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        APIError apiError = new APIError(
                httpStatus,
                exMessage,
                exType,
                request.getDescription(false).substring(4)
        );
        return new ResponseEntity<>(apiError, new HttpHeaders(), httpStatus);
    }

    @ExceptionHandler({RecordNotFoundException.class, OwnerNotFoundException.class, AddressNotFoundException.class})
    protected ResponseEntity<Object> handleCustomNotFoundException(RuntimeException ex, WebRequest request) {
        String exType = ex.getClass().getSimpleName();
        String exMessage = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        APIError apiError = new APIError(
                httpStatus,
                exMessage,
                exType,
                request.getDescription(false).substring(4)
        );
        return new ResponseEntity<>(apiError, new HttpHeaders(), httpStatus);
    }


    /**
     * Fallback handler to catch-all type of other exceptions that don't have specific handlers defined
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        String exType = ex.getClass().getSimpleName();
        String exMessage = "Undefined error occurred";
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        APIError apiError = new APIError(
                httpStatus,
                exMessage,
                exType,
                request.getDescription(false).substring(4)
        );
        return new ResponseEntity<>(apiError, new HttpHeaders(), httpStatus);
    }


}
