package ru.aryunin.VacationPayCalculator.Services;

import org.springframework.stereotype.Service;
import ru.aryunin.VacationPayCalculator.Models.VacationPay;

import java.util.Date;

@Service
public class VacationPayService {
    public VacationPay calculate(Date startDate,
                                 Date endDate,
                                 double averageSalary) {
        VacationPay vacationPay = new VacationPay();
        vacationPay.setAmount(0); // TODO
        return vacationPay;
    }
}
