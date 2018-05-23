package com.threem.carrental.app.errorHandler;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author based on code from Radoslaw Domanski, SDA Classess
 */
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<ValidationError> validationErrors;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path, List<ValidationError> validationErrors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.validationErrors = validationErrors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    static ErrorResponse of(HttpStatus status, String message, String path){
        return of(status, message, path, new ArrayList<>());
    }

    static ErrorResponse of(HttpStatus status, String message, String path, List<ValidationError> validationErrors){
        return new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path,
                validationErrors
        );
    }

    static class ValidationError {
        private String fieldName;
        private String message;
        private Object rejectedValue;

        public ValidationError(String fieldName, String message, Object rejectedValue) {
            this.fieldName = fieldName;
            this.message = message;
            this.rejectedValue = rejectedValue;
        }

        public ValidationError() {
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getMessage() {
            return message;
        }

        public Object getRejectedValue() {
            return rejectedValue;
        }
    }
}