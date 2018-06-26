package com.threem.carrental.app.errorHandler.customExceptions;

/**
 * @author marek_j on 2018-06-26
 */
public class CarDoesNotExistsException extends RuntimeException {
    public CarDoesNotExistsException(String msg) { super(msg); }
}
