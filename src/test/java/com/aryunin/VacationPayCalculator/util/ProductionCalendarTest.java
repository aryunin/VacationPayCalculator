package com.aryunin.VacationPayCalculator.util;

import com.aryunin.VacationPayCalculator.service.ResourcesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ProductionCalendar.class,
        ResourcesService.class
})
class ProductionCalendarTest {
    @Autowired
    private ProductionCalendar productionCalendar;

    @Test
    void countWorkingDays_success() {
        var start = LocalDate.of(2024, Month.JANUARY, 1);
        var end = LocalDate.of(2024, Month.FEBRUARY, 1);
        int expected = 17;

        int actual = productionCalendar.countWorkingDays(start, end);

        assertEquals(expected, actual);
    }
}