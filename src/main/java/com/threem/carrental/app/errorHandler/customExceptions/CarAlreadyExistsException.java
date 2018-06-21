package com.threem.carrental.app.errorHandler.customExceptions;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author Marika Grzebieniowska on 31.05.2018
 * @project car-rental-app
 */
public class CarAlreadyExistsException extends DataIntegrityViolationException {

    public CarAlreadyExistsException(String msg) {
        super(msg);
    }

}
