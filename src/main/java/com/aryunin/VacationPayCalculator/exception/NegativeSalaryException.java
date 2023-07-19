package com.aryunin.VacationPayCalculator.exception;

public class NegativeSalaryException extends RuntimeException {
    public NegativeSalaryException() {
        super("negative salary");
    }
}
