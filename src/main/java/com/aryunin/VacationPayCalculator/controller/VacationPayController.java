package com.aryunin.VacationPayCalculator.controller;

import com.aryunin.VacationPayCalculator.service.VacationPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class VacationPayController {
    private final VacationPayService vacationPayService;

    @GetMapping(value = "/calculate")
    private String calculate(@RequestParam Double salary,
                             @RequestParam Integer days,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        return null;
    }
}
