package ru.aryunin.VacationPayCalculator.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aryunin.VacationPayCalculator.Models.VacationPay;
import ru.aryunin.VacationPayCalculator.Utils.WorkingDaysCalendar;

import java.util.*;

@Service
@AllArgsConstructor
public class VacationPayService {
    private final WorkingDaysCalendar workingDaysCalendar;

    private int getWorkingDaysCount(Date startDate, Date endDate) {
        int counter = 0;
        workingDaysCalendar.setDate(startDate);
        while (!workingDaysCalendar.getDate().after(endDate)) {
            if(workingDaysCalendar.isWorkingDay()) counter++;
            workingDaysCalendar.nextDay();
        }
        workingDaysCalendar.setDate(new Date());
        return counter;
    }

    public VacationPay calculate(Date startDate,
                                 Date endDate,
                                 double averageSalary) {
        VacationPay vacationPay = new VacationPay();
        int workingDaysCount = getWorkingDaysCount(startDate, endDate);
        vacationPay.setAmount((int)((averageSalary/29.3)*workingDaysCount*100)/100.);
        return vacationPay;
    }
}
