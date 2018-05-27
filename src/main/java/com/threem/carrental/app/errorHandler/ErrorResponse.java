package com.threem.carrental.app.errorHandler;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author based on code from Radoslaw Domanski, SDA Classess
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<ValidationError> validationErrors;

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

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @Getter
    static class ValidationError {
        private String fieldName;
        private String message;
        private Object rejectedValue;
    }
}