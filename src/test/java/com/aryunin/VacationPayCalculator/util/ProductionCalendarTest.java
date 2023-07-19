package com.aryunin.VacationPayCalculator.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class ProductionCalendarTest {
    private final ProductionCalendar productionCalendar = new ProductionCalendar();

    @Test
    void countWorkingDays_success() {
        var start = LocalDate.of(2024, Month.JANUARY, 1);
        var end = LocalDate.of(2024, Month.JANUARY, 31);
        int expected = 17;

        int actual = productionCalendar.countWorkingDays(start, end);

        assertEquals(expected, actual);
    }
}