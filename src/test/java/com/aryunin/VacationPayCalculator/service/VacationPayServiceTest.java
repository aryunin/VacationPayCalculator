package com.aryunin.VacationPayCalculator.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VacationPayServiceTest {
    private final VacationPayService vacationPayService = new VacationPayService();

    @Test
    void calculate_withoutStartDate_success() {
        int days = 10;
        double salary = 45000;
        double expected = salary / 29.3 * days;

        double actual = vacationPayService.calculate(salary, days);

        assertEquals(expected, actual);
    }

    @Test
    void calculate_withStartDate_success() {
        int days = 15;
        double salary = 45000;
        LocalDate startDate = LocalDate.of(2024, Month.JANUARY, 1);
        double expected = salary / 29.3 * (days - 10);

        double actual = vacationPayService.calculate(salary, days, startDate);

        assertEquals(expected, actual);
    }
}