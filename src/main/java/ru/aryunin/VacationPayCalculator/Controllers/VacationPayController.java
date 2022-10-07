package ru.aryunin.VacationPayCalculator.Controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aryunin.VacationPayCalculator.DTO.VacationPayDTO;
import ru.aryunin.VacationPayCalculator.Models.VacationPay;
import ru.aryunin.VacationPayCalculator.Services.VacationPayService;

import java.util.Date;

@RestController
@AllArgsConstructor
public class VacationPayController {
    private final VacationPayService vacationPayService;
    private final ModelMapper modelMapper;

    @GetMapping("/calculate")
    public VacationPayDTO calculate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                    @RequestParam double averageSalary) {
        VacationPay vacationPay = vacationPayService.calculate(startDate, endDate, averageSalary);
        return modelMapper.map(vacationPay, VacationPayDTO.class);
    }
}
