package com.aryunin.VacationPayCalculator.exception;

public class NegativeDaysException extends Exception {
    public NegativeDaysException(String message) {
        super("negative days");
    }
}
