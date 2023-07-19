package com.aryunin.VacationPayCalculator.service;

import com.aryunin.VacationPayCalculator.util.ProductionCalendar;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VacationPayService {
    private final double avgDaysInMonth = 29.3;

    public double calculate(double salary, int days) {
        return salary / avgDaysInMonth * days;
    }

    public double calculate(double salary, int days, LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(days);
        return salary / avgDaysInMonth * (ProductionCalendar.countWorkingDays(startDate, endDate));
    }
}
