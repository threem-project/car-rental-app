package com.threem.carrental.app.errorHandler.customExceptions;

/**
 * @author marek_j on 2018-05-27
 */
public class IncorrectBranchException extends RuntimeException {

    public IncorrectBranchException(String message) {
        super(message);
    }

    public IncorrectBranchException(String message, Throwable cause) {
        super(message, cause);
    }
}
