package com.aryunin.VacationPayCalculator.service;

import com.aryunin.VacationPayCalculator.util.ProductionCalendar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class VacationPayService {
    private final double avgDaysInMonth = 29.3;

    private final ProductionCalendar productionCalendar;

    public double calculate(double salary, int days) {
        double result = salary / avgDaysInMonth * days;
        log.info("calculation result (without date) is " + result);
        return result;
    }

    public double calculate(double salary, int days, LocalDate startDate) {
        var endDate = startDate.plusDays(days);
        int workingDays = productionCalendar.countWorkingDays(startDate, endDate);
        log.info("count of working days: " + workingDays);
        double result = salary / avgDaysInMonth * (workingDays);
        log.info("calculation result (with date) is " + result);
        return result;
    }
}
