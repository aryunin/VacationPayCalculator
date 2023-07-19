package com.aryunin.VacationPayCalculator.service;

import com.aryunin.VacationPayCalculator.util.ProductionCalendar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class VacationPayServiceTest {
    @Mock
    private ProductionCalendar productionCalendar;
    @InjectMocks
    private VacationPayService vacationPayService;

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
        LocalDate endDate = startDate.plusDays(days);
        double expected = salary / 29.3 * (days - 10);

        Mockito.when(productionCalendar.countWorkingDays(startDate, endDate)).thenReturn(5);
        double actual = vacationPayService.calculate(salary, days, startDate);

        assertEquals(expected, actual);
    }
}