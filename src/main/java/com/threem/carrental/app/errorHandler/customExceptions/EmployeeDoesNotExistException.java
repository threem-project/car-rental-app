package com.threem.carrental.app.errorHandler.customExceptions;

/**
 * @author marek_j on 2018-05-27
 */
public class EmployeeDoesNotExistException extends RuntimeException {

    public EmployeeDoesNotExistException(String message) {
        super(message);
    }

}
