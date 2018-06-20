package com.threem.carrental.app.errorHandler;

import com.threem.carrental.app.errorHandler.customExceptions.CarAlreadyExistsException;
import com.threem.carrental.app.errorHandler.customExceptions.EmployeeAlreadyExistException;
import com.threem.carrental.app.errorHandler.customExceptions.EmployeeDoesNotExistException;
import com.threem.carrental.app.errorHandler.customExceptions.IncorrectBranchException;
import org.hibernate.exception.ConstraintViolationException;
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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handle(ConstraintViolationException e, ServletWebRequest request) {
        return ErrorResponse.of(HttpStatus.UNPROCESSABLE_ENTITY, "Fails due to breaking DB data integrity or constraints", request.getRequest().getRequestURI());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(CannotCreateTransactionException e, ServletWebRequest request) {
        return ErrorResponse.of(HttpStatus.NOT_FOUND, "General error with application", request.getRequest().getRequestURI());
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(MethodArgumentTypeMismatchException e, ServletWebRequest request) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, "Incorrect parameter or parameters", request.getRequest().getRequestURI());
    }

    //todo - refactor this stage as a part of ServiceLayer exceptions
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handle(HttpMessageNotReadableException e, ServletWebRequest request) {
        return ErrorResponse.of(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid JSON format", request.getRequest().getRequestURI());
    }

    //todo - refactor this part as a ConotrollerAdvice which is connected to JSON Dto objects
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handle(IncorrectBranchException e, ServletWebRequest request) {
        return ErrorResponse.of(HttpStatus.UNPROCESSABLE_ENTITY, "Wrong branch ID", request.getRequest().getRequestURI());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handle(EmployeeAlreadyExistException e, ServletWebRequest request) {
        return ErrorResponse.of(HttpStatus.UNPROCESSABLE_ENTITY, "Employee with this ID already exists in DB. Change ID or use PUT instead", request.getRequest().getRequestURI());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handle(EmployeeDoesNotExistException e, ServletWebRequest request) {
        return ErrorResponse.of(HttpStatus.UNPROCESSABLE_ENTITY, "Employee doesn't exist", request.getRequest().getRequestURI());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handle(CarAlreadyExistsException e, ServletWebRequest request) {
        return ErrorResponse.of(HttpStatus.UNPROCESSABLE_ENTITY, "Car with this vin number is already in DB", request.getRequest().getRequestURI());
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
