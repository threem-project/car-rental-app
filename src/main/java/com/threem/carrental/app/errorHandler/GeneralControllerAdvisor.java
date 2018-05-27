package com.threem.carrental.app.errorHandler;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author marek_j on 2018-05-23
 */
@RestControllerAdvice
public class GeneralControllerAdvisor {

    //todo - refactor this part as a ConotrollerAdvice which is connected to the persistence layer
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(InvalidDataAccessResourceUsageException e, ServletWebRequest request) {
        return ErrorResponse.of(HttpStatus.NOT_FOUND, "General error with application", request.getRequest().getRequestURI());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(CannotCreateTransactionException e, ServletWebRequest request) {
        return ErrorResponse.of(HttpStatus.NOT_FOUND, "General error with application", request.getRequest().getRequestURI());
    }

    //todo - refactor this part as a ConotrollerAdvice which is connected to JSON Dto objects
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handle(HttpMessageNotReadableException e, ServletWebRequest request) {
        return ErrorResponse.of(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid JSON format", request.getRequest().getRequestURI());
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidatorErrors(MethodArgumentNotValidException e, ServletWebRequest request) {
        List<ErrorResponse.ValidationError> resultErrorsList = new ArrayList<>();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        for (ObjectError objectError : errors) {
            Object rejectedValue = ((FieldError) objectError).getRejectedValue();
            resultErrorsList.add(new ErrorResponse.ValidationError(objectError.getObjectName(),objectError.getDefaultMessage(),rejectedValue));
        }
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, "Invalid field (or fields)", request.getRequest().getRequestURI(),resultErrorsList);
    }

}
