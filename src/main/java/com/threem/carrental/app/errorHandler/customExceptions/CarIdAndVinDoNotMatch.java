package com.threem.carrental.app.errorHandler.customExceptions;

/**
 * @author marek_j on 2018-06-21
 */
public class CarIdAndVinDoNotMatch extends RuntimeException {

    public CarIdAndVinDoNotMatch(String msg) {
        super(msg);
    }

}
