package com.aryunin.VacationPayCalculator.controller;

import com.aryunin.VacationPayCalculator.exception.NegativeDaysException;
import com.aryunin.VacationPayCalculator.exception.NegativeSalaryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final String prefix = "ERROR: ";

    @ExceptionHandler(NegativeDaysException.class)
    public ProblemDetail handleNegativeDaysException(RuntimeException ex) {
        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, prefix + ex.getMessage());
        body.setTitle("Invalid days format");
        return body;
    }

    @ExceptionHandler(NegativeSalaryException.class)
    public ProblemDetail handleNegativeSalaryException(RuntimeException ex) {
        ProblemDetail body = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, prefix + ex.getMessage());
        body.setTitle("Invalid salary format");
        return body;
    }
}
