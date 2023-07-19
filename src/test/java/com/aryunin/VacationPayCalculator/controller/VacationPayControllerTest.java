package com.aryunin.VacationPayCalculator.controller;

import com.aryunin.VacationPayCalculator.exception.NegativeDaysException;
import com.aryunin.VacationPayCalculator.exception.NegativeSalaryException;
import com.aryunin.VacationPayCalculator.service.VacationPayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VacationPayController.class)
class VacationPayControllerTest {
    private final String calculateEndpoint = "/calculate";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VacationPayService vacationPayService;

    @Test
    void calculate_withoutStartDate_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get(calculateEndpoint)
                        .param("salary", "1200000.00")
                        .param("days", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void calculate_withStartDate_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get(calculateEndpoint)
                        .param("salary", "1200000.00")
                        .param("days", "1")
                        .param("startDate", "2020-01-01"))
                .andExpect(status().isOk());
    }

    @Test
    void calculate_negativeSalary_badRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get(calculateEndpoint)
                        .param("salary", "-1")
                        .param("days", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NegativeSalaryException))
                .andExpect(result -> assertEquals(
                        "negative salary", Objects.requireNonNull(result.getResolvedException()).getMessage()
                ));
    }

    @Test
    void calculate_negativeDays_badRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get(calculateEndpoint)
                        .param("salary", "1200000.00")
                        .param("days", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NegativeDaysException))
                .andExpect(result -> assertEquals(
                        "negative days", Objects.requireNonNull(result.getResolvedException()).getMessage()
                ));
    }
}