package com.aryunin.VacationPayCalculator.util;

import com.aryunin.VacationPayCalculator.exception.NegativeDaysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class ProductionCalendar {
    private final Set<LocalDate> holidays = new HashSet<>();

    public ProductionCalendar() {
        var resourcePath = "classpath:static/holidays.txt";
        log.info("loading holidays from " + resourcePath);
        var resource = loadHolidaysFromResource(resourcePath);
        log.info("holidays loaded, parsing");
        parseHolidaysFromResource(resource);
        log.info("holidays parsed");
    }

    private Resource loadHolidaysFromResource(String resourcePath) {
        var resourceLoader = new DefaultResourceLoader();
        return resourceLoader.getResource(resourcePath);
    }

    private void parseHolidaysFromResource(Resource resource) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            var stream = resource.getInputStream();
            var br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                var date = LocalDate.parse(line, formatter);
                holidays.add(date);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        var current = start;
        int counter = 0;
        while (!current.equals(end)) {
            if(!isHoliday(current) && !isWeekend(current)) counter++;
            current = current.plusDays(1);
        }
        return counter;
    }
}
