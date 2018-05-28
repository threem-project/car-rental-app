package com.threem.carrental.app.errorHandler.customExceptions;

/**
 * @author marek_j on 2018-05-27
 */
public class EmployeeAlreadyExistException extends RuntimeException {
    public EmployeeAlreadyExistException(String message) {
        super(message);
    }

    public EmployeeAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
