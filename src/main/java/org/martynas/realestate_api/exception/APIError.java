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

    @Value("${custom.api.version}")
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
        this(status, exMessage, exType, path);
        error.put("detail", detailsList);
    }

    public APIError(HttpStatus status, String exMessage, String exType, Map<String, Object> detailsMap, String path) {
        this(status, exMessage, exType, path);
        error.put("detail", detailsMap);

    }

    // Overload HttpStatus getter with toString version for more verbose status format to include status code and phrase (404 NOT_FOUND)
    public String getStatus() {
        return status.toString();
    }

}
