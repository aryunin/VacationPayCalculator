package com.aryunin.VacationPayCalculator.util;

import com.aryunin.VacationPayCalculator.exception.NegativeDaysException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

public class ProductionCalendar {
    private static final Set<LocalDate> holidays = new HashSet<>();

    // 2023
    static {
        int year = 2023;
        holidays.add(LocalDate.of(year, Month.JANUARY, 2));
        holidays.add(LocalDate.of(year, Month.JANUARY, 3));
        holidays.add(LocalDate.of(year, Month.JANUARY, 4));
        holidays.add(LocalDate.of(year, Month.JANUARY, 5));
        holidays.add(LocalDate.of(year, Month.JANUARY, 6));
        holidays.add(LocalDate.of(year, Month.FEBRUARY, 23));
        holidays.add(LocalDate.of(year, Month.FEBRUARY, 24));
        holidays.add(LocalDate.of(year, Month.MARCH, 8));
        holidays.add(LocalDate.of(year, Month.MAY, 1));
        holidays.add(LocalDate.of(year, Month.MAY, 8));
        holidays.add(LocalDate.of(year, Month.MAY, 9));
        holidays.add(LocalDate.of(year, Month.JUNE, 12));
        holidays.add(LocalDate.of(year, Month.NOVEMBER, 6));
    }

    // 2024
    static {
        int year = 2023;
        holidays.add(LocalDate.of(year, Month.JANUARY, 1));
        holidays.add(LocalDate.of(year, Month.JANUARY, 2));
        holidays.add(LocalDate.of(year, Month.JANUARY, 3));
        holidays.add(LocalDate.of(year, Month.JANUARY, 4));
        holidays.add(LocalDate.of(year, Month.JANUARY, 5));
        holidays.add(LocalDate.of(year, Month.JANUARY, 8));
        holidays.add(LocalDate.of(year, Month.FEBRUARY, 23));
        holidays.add(LocalDate.of(year, Month.MARCH, 8));
        holidays.add(LocalDate.of(year, Month.APRIL, 29));
        holidays.add(LocalDate.of(year, Month.APRIL, 30));
        holidays.add(LocalDate.of(year, Month.MAY, 1));
        holidays.add(LocalDate.of(year, Month.MAY, 9));
        holidays.add(LocalDate.of(year, Month.MAY, 10));
        holidays.add(LocalDate.of(year, Month.JUNE, 12));
        holidays.add(LocalDate.of(year, Month.NOVEMBER, 4));
        holidays.add(LocalDate.of(year, Month.DECEMBER, 30));
        holidays.add(LocalDate.of(year, Month.DECEMBER, 31));
    }

    private ProductionCalendar() {
    }

    public static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public static boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }

    public static int countWorkingDays(LocalDate start, LocalDate end) {
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
