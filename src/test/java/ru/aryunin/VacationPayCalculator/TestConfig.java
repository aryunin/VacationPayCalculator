package ru.aryunin.VacationPayCalculator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.aryunin.VacationPayCalculator.Services.VacationPayService;
import ru.aryunin.VacationPayCalculator.Utils.WorkingDaysCalendar;

@Configuration
public class TestConfig {
    @Bean
    public WorkingDaysCalendar workingDaysCalendar() {
        return new WorkingDaysCalendar();
    }

    @Bean
    public VacationPayService vacationPayService(WorkingDaysCalendar wdc) {
        return new VacationPayService(wdc);
    }
}
