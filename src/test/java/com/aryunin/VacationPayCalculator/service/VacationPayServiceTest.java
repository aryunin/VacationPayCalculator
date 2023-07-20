package com.aryunin.VacationPayCalculator.service;

import com.aryunin.VacationPayCalculator.util.ProductionCalendar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = VacationPayService.class)
class VacationPayServiceTest {
    @MockBean
    private ProductionCalendar productionCalendar;
    @Autowired
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
        var startDate = LocalDate.of(2024, Month.JANUARY, 1);
        var endDate = startDate.plusDays(days - 1);
        double expected = salary / 29.3 * (days - 10);

        Mockito.when(productionCalendar.countWorkingDays(startDate, endDate)).thenReturn(5);
        double actual = vacationPayService.calculate(salary, days, startDate);

        assertEquals(expected, actual);
    }
}