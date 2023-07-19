package com.aryunin.VacationPayCalculator.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VacationPayService {
    public double calculate(double salary, int days) {
        return 1.1234;
    }

    public double calculate(double salary, int days, LocalDate date) {
         return 1.1234;
    }
}
