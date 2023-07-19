package com.aryunin.VacationPayCalculator.exception;

public class NegativeSalaryException extends Exception {
    public NegativeSalaryException(String message) {
        super("negative salary");
    }
}
