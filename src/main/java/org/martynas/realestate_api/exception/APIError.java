package org.martynas.realestate_api.exception;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.*;

/**
 * A Custom API Error Message
 */
@Data
public class APIError {

    @Value("${spring.custom.api.version}")
    private String apiVersion = "0.1";
    private HttpStatus status;
    private final LocalDateTime timestamp;
    private Map<String, Object> error = new LinkedHashMap<>();
    private String path;

    private APIError() {
        this.timestamp = LocalDateTime.now();
    }

    public APIError(HttpStatus status, String exMessage, String exType, String path) {
        this();
        this.status = status;
        this.path = path;
        error.put("type", exType);
        error.put("message", exMessage);
    }

    public APIError(HttpStatus status, String exMessage, String exType, List<String> detailsList, String path) {
        this();
        this.status = status;
        this.path = path;
        error.put("type", exType);
        error.put("message", exMessage);
        error.put("detail", detailsList);
    }

    public APIError(HttpStatus status, String exMessage, String exType, Map<String, Object> detailsMap, String path) {
        this();
        this.status = status;
        this.path = path;
        error.put("type", exType);
        error.put("message", exMessage);
        error.put("detail", detailsMap);

    }

//    public ApiError(HttpStatus status, String message, Map<String, Object> errorsMap, String path) {
//        this();
//        this.status = status;
//        this.message = message;
//        this.errorsMap = errorsMap;
//        this.path = path;
//    }
//
//    public ApiError(HttpStatus status, String message, List<String> errors, String path) {
//        this();
//        this.status = status;
//        this.message = message;
//        this.errors = errors;
//        this.path = path;
//    }
//
//    public ApiError(HttpStatus status, String message, String error, String path) {
//        this();
//        this.status = status;
//        this.message = message;
//        this.errors = Collections.singletonList(error);
//        this.path = path;
//    }

    public String getStatus() {
        return status.toString();
    }

//    public Object getErrors() {
//        return errors == null ? errorsMap.entrySet() : errors;

//        Map<String, String> errorMap = new HashMap<>();
//        errors.put("reason", reason);
//        errors.put("message", message);
//        Map<String, Object> outsideMap = new HashMap<>();
//        outsideMap.put("errors", errors);
//        return errors;
//        return Collections.singletonMap("errors", errors);
//    }
}
