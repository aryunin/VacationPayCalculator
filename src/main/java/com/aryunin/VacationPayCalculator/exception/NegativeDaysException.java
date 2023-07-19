package com.aryunin.VacationPayCalculator.exception;

public class NegativeDaysException extends RuntimeException {
    public NegativeDaysException() {
        super("negative days");
    }
}
