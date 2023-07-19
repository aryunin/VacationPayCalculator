package com.aryunin.VacationPayCalculator.util;

import com.aryunin.VacationPayCalculator.exception.NegativeDaysException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Component
public class ProductionCalendar {
    private final Set<LocalDate> holidays = new HashSet<>();

    public ProductionCalendar() {
        String resourcePath = "classpath:static/holidays.txt";
        Resource resource = loadHolidaysFromResource(resourcePath);
        parseHolidaysFromResource(resource);
    }

    private Resource loadHolidaysFromResource(String resourcePath) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        return resourceLoader.getResource(resourcePath);
    }

    private void parseHolidaysFromResource(Resource resource) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            InputStream stream = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                LocalDate date = LocalDate.parse(line, formatter);
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
        LocalDate current = start;
        int counter = 0;
        while (!current.equals(end)) {
            if(!isHoliday(current) && !isWeekend(current)) counter++;
            current = current.plusDays(1);
        }
        return counter;
    }
}
