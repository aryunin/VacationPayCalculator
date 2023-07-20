package com.aryunin.VacationPayCalculator.util;

import com.aryunin.VacationPayCalculator.exception.NegativeDaysException;
import com.aryunin.VacationPayCalculator.service.ResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ProductionCalendar {
    private Set<LocalDate> holidays = new HashSet<>();

    public ProductionCalendar(ResourcesService resourcesService) {
        log.info("filling holidays");
        fillHolidaysFromString(resourcesService.getHolidaysAsString());
        log.info("holidays filled");
    }

    private void fillHolidaysFromString(String resourcesString) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        holidays = resourcesString.lines().map(l -> LocalDate.parse(l, formatter)).collect(Collectors.toSet());
    }

    public boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }

    public int countWorkingDays(LocalDate start, LocalDate end) {
        if(end.isBefore(start)) throw new NegativeDaysException();
        int counter = 0;
        while (!start.equals(end)) {
            if(!isHoliday(start) && !isWeekend(start)) counter++;
            start = start.plusDays(1);
        }
        return counter;
    }
}
