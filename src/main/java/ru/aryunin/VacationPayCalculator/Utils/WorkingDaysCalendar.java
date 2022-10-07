package ru.aryunin.VacationPayCalculator.Utils;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Calendar with checking of working and non-working days
 */
@Component
public class WorkingDaysCalendar {
    private final Calendar calendar = new GregorianCalendar();
    private final Date[] holidays = {
            new Date(120, Calendar.JANUARY, 1),
            new Date(120, Calendar.JANUARY, 2),
            new Date(120, Calendar.JANUARY, 3),
            new Date(120, Calendar.JANUARY, 4),
            new Date(120, Calendar.JANUARY, 5),
            new Date(120, Calendar.JANUARY, 6),
            new Date(120, Calendar.JANUARY, 7),
            new Date(120, Calendar.JANUARY, 8),
            new Date(120, Calendar.JANUARY, 1),
            new Date(120, Calendar.FEBRUARY, 23),
            new Date(120, Calendar.MARCH, 8),
            new Date(120, Calendar.MAY, 1),
            new Date(120, Calendar.MAY, 9),
            new Date(120, Calendar.JUNE, 12),
            new Date(120, Calendar.NOVEMBER, 4)
    };

    public WorkingDaysCalendar() {
        Date date = new Date();
        calendar.setTime(date);
    }

    public void setDate(Date date) {
        calendar.setTime(date);
    }

    public Date getDate() {
        return calendar.getTime();
    }

    public boolean isHoliday() {
        return Arrays.stream(holidays).anyMatch(
                d -> d.getMonth() == calendar.get(Calendar.MONTH) &&
                d.getDate() == calendar.get(Calendar.DATE)
        );
    }

    public boolean isWeekday() {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    public boolean isWorkingDay() {
        return !isHoliday() && !isWeekday();
    }

    public void nextDay() {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
    }
}
