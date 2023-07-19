package com.aryunin.VacationPayCalculator.controller;

import com.aryunin.VacationPayCalculator.exception.NegativeDaysException;
import com.aryunin.VacationPayCalculator.exception.NegativeSalaryException;
import com.aryunin.VacationPayCalculator.service.VacationPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class VacationPayController {
    private final VacationPayService vacationPayService;

    @GetMapping(value = "/calculate")
    private String calculate(@RequestParam Double salary,
                             @RequestParam Integer days,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        if(salary < 0) throw new NegativeSalaryException();
        if(days < 0) throw new NegativeDaysException();
        var result = (startDate == null) ?
                vacationPayService.calculate(salary, days) : vacationPayService.calculate(salary, days, startDate);
        var formatter = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        return formatter.format(result);
    }
}
